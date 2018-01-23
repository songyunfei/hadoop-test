package com.linkGap.projectManage.dao;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.entity.DefaultStep;
import com.linkGap.projectManage.model.view.DefaultStepView;

public interface DefaultStepMapper {
    int deleteByPrimaryKey(Integer stepId);

    int insert(DefaultStep record);

    int insertSelective(DefaultStep record);

    DefaultStep selectByPrimaryKey(Integer stepId);

    int updateByPrimaryKeySelective(DefaultStep record);

    int updateByPrimaryKey(DefaultStep record);
    
    List<DefaultStepView> list(Map map);
    
    int deleteByCustomerId(Integer customerId);
    
    
}