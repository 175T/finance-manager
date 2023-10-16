package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.dto.SubjectDto;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.service.SubjectService;
import com.github.greatwqs.app.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 项目
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
@Slf4j
@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private RequestComponent requestComponent;

    /**
     * 所有项目
     */
    @LoginRequired
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ModelAndView allSubjects() {
        log.info("subject all api, params: " + JsonUtil.toJson(null));
        ModelAndView modelAndView = new ModelAndView("subject_all");
        List<SubjectVo> subjectVoList = subjectService.getAllSubjects();
        modelAndView.addObject("subjectVoList", subjectVoList);
        return modelAndView;
    }

    /**
     * 所有项目, WEB页面根据 businessname 查询
     * http://cwgl.supintl.com/QueryProjectByBusiness?businessname=6e11b4451de14244aaf27c20cd4f338a
     * 70336372f7a74b5a9ca52508732462c2:住宿费,
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "query_biz", method = RequestMethod.GET)
    public String allSubjectsByBusiness(
            @RequestParam(name = "businessname", required = false) String businessname
    ) {
        log.info("subject query_biz api, params: " + JsonUtil.toJson(businessname));
        List<SubjectVo> subjectVoList = subjectService.getAllSubjects();
        StringBuilder stringBuilder = new StringBuilder();
        for (SubjectVo subjectVo : subjectVoList) {
            stringBuilder.append(subjectVo.getId()).append(":").append(subjectVo.getName()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 检查项目名字是否存在,
     * http://cwgl.supintl.com/VerifyServlet
     * 不存在返回0, 存在返回1
     * project: 住宿费
     * schoolid: 6e11b4451de14244aaf27c20cd4f338a
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "check_name_exists", method = RequestMethod.POST)
    public String subjectNameCheck(
            @RequestParam(name = "project", required = true) String subjectName,
            @RequestParam(name = "schoolid", required = false) String schoolid
    ) {
        log.info("subject check_name_exists api, params: " + JsonUtil.toJson(subjectName));
        List<SubjectVo> subjectVoList = subjectService.getAllSubjects();
        for (SubjectVo subjectVo : subjectVoList) {
            if (StringUtils.equalsIgnoreCase(subjectVo.getName(), subjectName)) {
                return "1";
            }
        }
        return "0";
    }

    /**
     * 项目创建
     * http://localhost:8080/AddNewProjAndBill
     * projectInput: 新增项目
     * 返回 createdSubjectId
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String subjectCreate(
            @RequestParam(name = "projectInput", required = true) String subjectName
    ) {
        final Long currentUserId = requestComponent.getLoginUser().getId();
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(null);
        subjectDto.setSchool("默认校区");
        subjectDto.setName(subjectName);
        subjectDto.setCreateuserid(currentUserId);
        subjectDto.setUpdateuserid(currentUserId);
        log.info("subject create api, params: " + JsonUtil.toJson(subjectDto));
        final Long createdSubjectId = subjectService.create(subjectDto);
        log.info("subject create api, success, createdSubjectId: " + createdSubjectId);
        return String.valueOf(createdSubjectId); // 返回创建的ID
    }

    /**
     * 项目名称更新
     * http://localhost:8080/UpdateFormsServlet
     * bs: 1
     * project_update_manager: 2
     * update_text_businessid: 2
     * update_text_project: 项目2更新
     * <p>
     * 返回1
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String subjectUpdate(
            @RequestParam(name = "update_text_project", required = true) String subjectName,
            @RequestParam(name = "project_update_manager", required = true) Long subjectId,
            @RequestParam(name = "bs", required = false) String bs,
            @RequestParam(name = "update_text_businessid", required = false) String update_text_businessid
    ) {
        final Long currentUserId = requestComponent.getLoginUser().getId();
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectId);
        subjectDto.setName(subjectName);
        subjectDto.setUpdateuserid(currentUserId);
        log.info("subject update api, params: " + JsonUtil.toJson(subjectDto));
        subjectService.update(subjectDto);
        log.info("subject update api, success, subjectId: " + subjectId);
        return "1"; // 返回为1时, WEB 页面展示删除成功!
    }

    /**
     * 删除项目
     * http://localhost:8080/DeleteProjectServlet
     * id: 2
     * name: 项目2
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String subjectDelete(
            @RequestParam(name = "id", required = true) Long subjectId,
            @RequestParam(name = "name", required = false) String subjectName
    ) {
        log.info("subject delete api, params: " + JsonUtil.toJson(subjectId));
        final Long currentUserId = requestComponent.getLoginUser().getId();
        final Boolean deleteOk = subjectService.delete(subjectId, currentUserId);
        return deleteOk ? String.valueOf(1) : String.valueOf(0); // 返回为1时, WEB 页面展示删除成功!
    }
}
