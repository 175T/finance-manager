package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.dto.OrderDto;
import com.github.greatwqs.app.domain.dto.OrderSearchDto;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.OrderQueryParamsVo;
import com.github.greatwqs.app.domain.vo.UserVo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.service.OrderService;
import com.github.greatwqs.app.service.SubjectService;
import com.github.greatwqs.app.service.TicketTypeService;
import com.github.greatwqs.app.service.UserService;
import com.github.greatwqs.app.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import static com.github.greatwqs.app.common.AppConstants.PAGE_ORDER_QUERY;

/**
 * 订单API
 *
 * @author greatwqs
 * Create on 2020/7/5
 */
@Slf4j
@RequestMapping("")
@Controller
public class OrderController {

    @Autowired
    private RequestComponent requestComponent;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    /***
     * 订单搜索页面, 前端传参
     * http://cwgl.supintl.com/queryServlet?index=1
     *
     * startTime: 2020-07-01
     * endTime: 2020-07-04
     * queryName: OrderAccount
     * queryReceipt: TicketNo
     * queryFashion: PayType
     * queryGrade: OrderClass
     * queryRemark: OrderDes
     * business: {暂时找不到对应的字段}
     * queryProject: subjectId
     * queryMoneys: orderPriceType [ALL(0), INCOME(1), OUTCOME(2)]
     */
    @LoginRequired
    @RequestMapping(value = "order_query")
    public ModelAndView orderQuery(
            @RequestParam(name = "index", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "startTime", required = false) String createTimeStart,
            @RequestParam(name = "endTime", required = false) String createTimeEnd,
            @RequestParam(name = "queryName", required = false) String orderAccount,
            @RequestParam(name = "queryReceipt", required = false) String ticketNo,
            @RequestParam(name = "queryFashion", required = false) String payType,
            @RequestParam(name = "queryGrade", required = false) String orderClass,
            @RequestParam(name = "queryRemark", required = false) String orderDes,
            @RequestParam(name = "business", required = false) String business,
            @RequestParam(name = "queryProject", required = false) String subjectId,
            @RequestParam(name = "queryMoneys", required = false, defaultValue = "0") Integer orderPriceType
    ) throws IOException {
        UserPo userPo = requestComponent.getLoginUser();
        OrderSearchDto searchDto = new OrderSearchDto(
                createTimeStart, createTimeEnd, orderAccount, ticketNo,
                payType, orderClass, orderDes, subjectId, orderPriceType, pageIndex);
        log.info("order_query api, query params: " + JsonUtil.toJson(searchDto));
        OrderQueryParamsVo queryParamsVo = new OrderQueryParamsVo(
                createTimeStart, createTimeEnd, orderAccount, ticketNo,
                payType, orderClass, orderDes, subjectId, orderPriceType, pageIndex);
        ModelAndView modelAndView = new ModelAndView("manage_all");
        modelAndView.addObject("currentUser", modelMapper.map(userPo, UserVo.class));
        modelAndView.addObject("ticketTypeList", ticketTypeService.getAllTicketTypes());
        modelAndView.addObject("subjectList", subjectService.getAllSubjects());
        modelAndView.addObject("userList", userService.allUsers(userPo));
        modelAndView.addObject("orderPage", orderService.getOrderPageVo(searchDto, userPo));
        modelAndView.addObject("queryParams", queryParamsVo);
        return modelAndView;
    }

    /***
     * 订单创建, 前端传参
     * http://localhost:8080/addServlet?isHide=8
     *
     * name: orderaccount
     * grade: orderclass
     * project: 2
     * receipt: 2
     * receiptNum: ticketno
     * moneys: 8888
     * fashion: paytype
     * time: 2020-07-01
     * remark: orderdes
     */
    @LoginRequired
    @RequestMapping(value = "order_create")
    public void orderCreate(
            HttpServletResponse response,
            @RequestParam(name = "isHide", required = false) String isHide,
            @RequestParam(name = "name") String orderAccount,
            @RequestParam(name = "grade") String orderClass,
            @RequestParam(name = "project") Long subjectId,
            @RequestParam(name = "receipt") Long ticketTypeId,
            @RequestParam(name = "receiptNum") String ticketNo,
            @RequestParam(name = "moneys") BigDecimal orderPrice,
            @RequestParam(name = "fashion") String payType,
            @RequestParam(name = "time") String orderTime,
            @RequestParam(name = "remark") String orderDes
    ) throws ParseException, IOException {

        UserPo userPo = requestComponent.getLoginUser();
        OrderDto orderDto = new OrderDto(
                null, orderAccount, orderClass, subjectId,
                ticketTypeId, ticketNo, orderPrice, payType,
                DateUtils.parseDate(orderTime, "yyyy-MM-dd"),
                orderDes, userPo.getId(), userPo.getId());
        log.info("order_create api, params: " + JsonUtil.toJson(orderDto));

        Long createdOrderId = orderService.create(orderDto, userPo);
        log.info("order_create api, createdOrderId: " + createdOrderId);

        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(PAGE_ORDER_QUERY);
    }

    /***
     * 订单更新, 前端传参
     * http://localhost:8080/UpdateServlet
     *
     * id_update: d8e9adadea414ec8a04001f400008de5
     * business_id:
     * name_update: orderaccount
     * grade_update: orderclass
     * project_update: 2
     * receipt_update: 1
     * receiptNum_update: ticketno
     * moneys_update: 8888
     * fashion_update: paytype
     * time_update: 2020-07-05
     * remark_update: orderdes
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "order_update")
    public String orderUpdate(
            HttpServletResponse response,
            @RequestParam(name = "id_update") Long orderId,
            @RequestParam(name = "business_id", required = false) String business_id,
            @RequestParam(name = "name_update") String orderAccount,
            @RequestParam(name = "grade_update") String orderClass,
            @RequestParam(name = "project_update") Long subjectId,
            @RequestParam(name = "receipt_update") Long ticketTypeId,
            @RequestParam(name = "receiptNum_update") String ticketNo,
            @RequestParam(name = "moneys_update") BigDecimal orderPrice,
            @RequestParam(name = "fashion_update") String payType,
            @RequestParam(name = "time_update") String orderTime,
            @RequestParam(name = "remark_update") String orderDes
    ) throws ParseException, IOException {

        UserPo userPo = requestComponent.getLoginUser();
        OrderDto orderDto = new OrderDto(
                orderId, orderAccount, orderClass, subjectId,
                ticketTypeId, ticketNo, orderPrice, payType,
                DateUtils.parseDate(orderTime, "yyyy-MM-dd"),
                orderDes, userPo.getId(), userPo.getId());
        log.info("order_update api, params: " + JsonUtil.toJson(orderDto));

        orderService.update(orderDto, userPo);
        log.info("order_update api, success! orderId: " + orderId);
        return "1"; // 返回为1时, WEB 页面展示更新成功!
    }


    /***
     * 订单删除, 前端传参
     * http://localhost:8080/DeleteIncomeServlet
     *
     * id: d8e9adadea414ec8a04001f400008de5
     * name: 郭亮君
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "order_delete")
    public String orderDelete(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "id") Long orderId,
            @RequestParam(name = "name") String orderAccount
    ) throws IOException {

        UserPo userPo = requestComponent.getLoginUser();
        log.info("order_delete api, params, orderId: " + orderId
                + ", orderAccount: " + orderAccount
                + ", currentUserId: " + userPo.getId());
        orderService.delete(orderId, userPo);
        log.info("order_delete api, delete success: orderId: " + orderId);
        return "1"; // 返回为1时, WEB 页面展示删除成功!
    }
}
