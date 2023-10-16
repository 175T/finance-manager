package com.github.greatwqs.app.manager;

import com.github.greatwqs.app.domain.po.SubjectPo;
import com.github.greatwqs.app.domain.vo.SubjectVo;

import java.util.List;
import java.util.Map;

/**
 * 项目
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
public interface SubjectManager {

    void insert(SubjectPo subjectlist);

    SubjectVo getVo(Long subjectId);

    List<SubjectPo> getPoList(List<Long> subjectIdList);

    List<SubjectVo> getVoList(List<Long> subjectIdList);

    Map<Long, SubjectVo> getVoMap(List<Long> subjectIdList);

    Map<String, SubjectVo> getNameVoMap();

    void update(SubjectPo subjectlist);

    void delete(Long subjectId, Long userId);
}
