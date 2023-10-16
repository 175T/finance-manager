package com.github.greatwqs.app.service.impl;

import com.github.greatwqs.app.domain.dto.SubjectDto;
import com.github.greatwqs.app.domain.po.SubjectPo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.manager.OrderManager;
import com.github.greatwqs.app.mapper.SubjectlistMapper;
import com.github.greatwqs.app.service.SubjectService;
import com.github.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author greatwqs
 * Create on 2020/6/27
 */
@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubjectlistMapper subjectlistMapper;

    @Autowired
    private OrderManager orderManager;

    @Override
    public List<SubjectVo> getAllSubjects() {
        List<SubjectPo> subjectPoList = subjectlistMapper.selectAll();
        if (Lists.isEmpty(subjectPoList)) {
            return Lists.newLinkedList();
        }
        return subjectPoList.stream()
                .map(po -> modelMapper.map(po, SubjectVo.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long create(SubjectDto subjectDto) {
        SubjectPo subjectPo = new SubjectPo();
        subjectPo.setId(null);
        subjectPo.setName(subjectDto.getName());
        subjectPo.setSchool(subjectDto.getSchool());
        subjectPo.setCreateuserid(subjectDto.getCreateuserid());
        subjectPo.setCreatetime(new Date());
        subjectPo.setUpdateuserid(subjectDto.getUpdateuserid());
        subjectPo.setUpdatetime(new Date());
        subjectlistMapper.insertSelective(subjectPo);
        return subjectPo.getId();
    }

    @Override
    @Transactional
    public void update(SubjectDto subjectDto) {
        SubjectPo subjectPo = new SubjectPo();
        subjectPo.setId(subjectDto.getId());
        subjectPo.setName(subjectDto.getName());
        subjectPo.setUpdateuserid(subjectDto.getUpdateuserid());
        subjectPo.setUpdatetime(new Date());
        subjectlistMapper.updateByPrimaryKeySelective(subjectPo);
    }

    @Override
    @Transactional
    public Boolean delete(Long subjectId, Long userId) {
        if (orderManager.isSubjectIdOrderExist(subjectId)) {
            log.warn("subject delete, subjectId exist order, can not delete! subjectId: " + subjectId);
            return false;
        }

        SubjectPo subjectPo = new SubjectPo();
        subjectPo.setId(subjectId);
        subjectPo.setIsvalid(false);
        subjectPo.setUpdateuserid(userId);
        subjectPo.setUpdatetime(new Date());
        subjectlistMapper.updateByPrimaryKeySelective(subjectPo);
        return true;
    }
}
