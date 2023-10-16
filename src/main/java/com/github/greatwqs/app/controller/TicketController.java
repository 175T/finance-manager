package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.dto.TicketTypeDto;
import com.github.greatwqs.app.domain.po.TicketTypePo;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.manager.TicketTypeManager;
import com.github.greatwqs.app.service.TicketTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 票据类型
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketTypeManager ticketTypeManager;

    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private RequestComponent requestComponent;

    /**
     * 插入票据类型
     * http://cwgl.supintl.com/VerifyServlet
     * request, receipt: 20200629
     * response, 00
     */
    @LoginRequired
    @RequestMapping(value = "type/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "receipt") String ticketTypeName) {
        UserPo userPo = requestComponent.getLoginUser();
        TicketTypeDto ticketTypeDto = new TicketTypeDto();
        ticketTypeDto.setId(null);
        ticketTypeDto.setName(StringUtils.trimToEmpty(ticketTypeName));
        ticketTypeDto.setCreateuserid(userPo.getId());
        ticketTypeDto.setUpdateuserid(userPo.getId());
        ticketTypeService.add(ticketTypeDto);
        return "00";
    }

    /**
     * 插入票据类型确认
     * http://cwgl.supintl.com/AddNewProjAndBill
     * request, receiptInput: 20200629
     * response, ac0775fc34e74584a21e50f86714d784
     */
    @RequestMapping(value = "type/add-confirm", method = RequestMethod.POST)
    public String addConfirm(@RequestParam(value = "receiptInput") String ticketTypeName) {
        final TicketTypePo ticketTypePo = ticketTypeManager.getPo(ticketTypeName);
        return ticketTypePo != null ? ticketTypePo.getId().toString() : "";
    }

    /**
     * 检查票据名字是否存在,
     * http://cwgl.supintl.com/VerifyServlet
     * 不存在返回0, 存在返回1
     * receipt: 111
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "check_name_exists", method = RequestMethod.POST)
    public String subjectNameCheck(
            @RequestParam(name = "receipt", required = true) String ticketTypeName) {
        final TicketTypePo ticketTypePo = ticketTypeManager.getPo(ticketTypeName);
        return ticketTypePo != null ? "1" : "0";
    }

    /**
     * 插入票据类型
     * http://cwgl.supintl.com/AddNewProjAndBill
     * receiptInput: 2020062
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "type/create", method = RequestMethod.POST)
    public String typeCreate(@RequestParam(value = "receiptInput") String ticketTypeName) {
        final Long currentUserId = requestComponent.getLoginUser().getId();
        TicketTypeDto ticketTypeDto = new TicketTypeDto();
        ticketTypeDto.setId(null);
        ticketTypeDto.setName(StringUtils.trimToEmpty(ticketTypeName));
        ticketTypeDto.setCreateuserid(currentUserId);
        ticketTypeDto.setUpdateuserid(currentUserId);
        final Long ticketTypeId = ticketTypeService.add(ticketTypeDto);
        return String.valueOf(ticketTypeId);
    }

    /**
     * 更新票据类型
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "type/update", method = RequestMethod.POST)
    public String typeUpdate(@RequestParam(value = "ticket_type_id") String ticket_type_id,
                             @RequestParam(value = "update_ticket_type_name") String update_ticket_type_name) {
        final Long currentUserId = requestComponent.getLoginUser().getId();
        TicketTypeDto ticketTypeDto = new TicketTypeDto();
        ticketTypeDto.setId(Long.valueOf(ticket_type_id));
        ticketTypeDto.setName(StringUtils.trimToEmpty(update_ticket_type_name));
        ticketTypeDto.setUpdateuserid(currentUserId);
        ticketTypeService.update(ticketTypeDto);
        return String.valueOf(1);
    }

    /**
     * 删除票据类型
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "type/delete", method = RequestMethod.POST)
    public String typeDelete(@RequestParam(value = "ticket_type_id") String ticket_type_id,
                             @RequestParam(value = "ticket_type_name") String ticket_type_name) {
        final Long currentUserId = requestComponent.getLoginUser().getId();
        final Boolean deleteOk = ticketTypeService.delete(Long.valueOf(ticket_type_id), currentUserId);
        return deleteOk ? String.valueOf(1) : String.valueOf(0); // 返回为1时, WEB 页面展示删除成功!
    }

    /**
     * 所有项目
     */
    @RequestMapping(value = "type/all", method = RequestMethod.GET)
    public List<TicketTypeVo> typeAll() {
        return ticketTypeService.getAllTicketTypes();
    }
}
