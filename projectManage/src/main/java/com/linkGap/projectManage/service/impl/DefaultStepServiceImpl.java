package com.linkGap.projectManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkGap.projectManage.dao.DefaultStepMapper;
import com.linkGap.projectManage.model.entity.DefaultStep;
import com.linkGap.projectManage.model.view.DefaultStepView;
import com.linkGap.projectManage.service.DefaultStepService;

@Service
public class DefaultStepServiceImpl implements DefaultStepService {
    
	@Autowired
	private DefaultStepMapper  efaultStepMapper;

	@Override
	public List<DefaultStepView> list(Map params) {		
		return efaultStepMapper.list(params);
	}

	@Override
	public void update(List<DefaultStep> list) {
		efaultStepMapper.deleteByCustomerId(list.get(0).getCustomerId());
		for(int i=0;i<list.size();i++){
			DefaultStep defaultStep=list.get(i);
			defaultStep.setStatus("1");
			defaultStep.setStepId(null);
			defaultStep.setCreateTime(new Date());
			efaultStepMapper.insert(defaultStep);
		}
	}


	
	

}
