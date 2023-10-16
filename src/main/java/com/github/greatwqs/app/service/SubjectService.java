package com.github.greatwqs.app.service;

import com.github.greatwqs.app.domain.dto.SubjectDto;
import com.github.greatwqs.app.domain.vo.SubjectVo;

import java.util.List;

/**
 *
 * 项目模块
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
public interface SubjectService {

    List<SubjectVo> getAllSubjects();

    Long create(SubjectDto subjectDto);

    void update(SubjectDto subjectDto);

    /***
     * 返回 Boolean:
     * true: 删除成功
     * false: 未删除 (exist order)
     * @param subjectId
     * @param userId
     * @return
     */
    Boolean delete(Long subjectId, Long userId);
}
