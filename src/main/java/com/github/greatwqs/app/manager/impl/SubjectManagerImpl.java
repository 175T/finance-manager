package com.github.greatwqs.app.manager.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.po.SubjectPo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.manager.SubjectManager;
import com.github.greatwqs.app.mapper.SubjectlistMapper;
import com.github.greatwqs.app.utils.collection.Lists;
import com.github.greatwqs.app.utils.collection.Maps;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
@Slf4j
@Component
public class SubjectManagerImpl implements SubjectManager {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubjectlistMapper subjectlistMapper;

    @Override
    @Transactional
    public void insert(SubjectPo subjectPo) {
        subjectlistMapper.insertSelective(subjectPo);
    }

    @Override
    public SubjectVo getVo(Long subjectId) {
        SubjectPo subjectPo = subjectlistMapper.selectByPrimaryKey(subjectId);
        if (subjectPo == null || !subjectPo.getIsvalid()) {
            log.warn("getVo subjectPo null, subjectId: " + subjectId);
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        }
        return modelMapper.map(subjectPo, SubjectVo.class);
    }

    @Override
    public List<SubjectPo> getPoList(List<Long> subjectIdList) {
        if (Lists.isEmpty(subjectIdList)) {
            return Lists.newLinkedList();
        }

        return this.subjectlistMapper.selectByIdList(subjectIdList);
    }

    @Override
    public List<SubjectVo> getVoList(List<Long> subjectIdList) {
        if (Lists.isEmpty(subjectIdList)) {
            return Lists.newLinkedList();
        }

        List<SubjectPo> subjectPoList = this.getPoList(subjectIdList);
        return subjectPoList.stream().map(po -> poToVo(po)).collect(Collectors.toList());
    }

    @Override
    public Map<Long, SubjectVo> getVoMap(List<Long> subjectIdList) {
        if (Lists.isEmpty(subjectIdList)) {
            return Maps.newHashMap();
        }

        List<SubjectVo> subjectVoList = this.getVoList(subjectIdList);
        Map<Long, SubjectVo> subjectVoMap = Maps.newHashMap();
        subjectVoList.forEach(vo -> subjectVoMap.put(vo.getId(), vo));
        return subjectVoMap;
    }


    private SubjectVo poToVo(SubjectPo subjectPo) {
        return modelMapper.map(subjectPo, SubjectVo.class);
    }

    @Override
    public Map<String, SubjectVo> getNameVoMap() {
        List<SubjectPo> subjectPoList = subjectlistMapper.selectAll();
        if (Lists.isEmpty(subjectPoList)){
            return Maps.newHashMap();
        }

        Map<String, SubjectVo> nameToVoMap = Maps.newHashMap();
        for (SubjectPo subjectPo : subjectPoList){
            nameToVoMap.put(subjectPo.getName(), poToVo(subjectPo));
        }
        return nameToVoMap;
    }

    @Override
    @Transactional
    public void update(SubjectPo subjectPo) {
        subjectlistMapper.updateByPrimaryKeySelective(subjectPo);
    }

    @Override
    @Transactional
    public void delete(Long subjectId, Long userId) {
        SubjectPo subjectPo = new SubjectPo();
        subjectPo.setIsvalid(false);
        subjectPo.setId(subjectId);
        subjectPo.setUpdateuserid(userId);
        subjectlistMapper.updateByPrimaryKeySelective(subjectPo);
    }
}
