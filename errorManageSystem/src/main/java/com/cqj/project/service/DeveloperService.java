package com.cqj.project.service;

import java.util.List;

import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.DeveloperAddRequestBean;
import com.cqj.project.controller.request.DeveloperChangePwdRequestBean;
import com.cqj.project.controller.request.DeveloperUpdateRequestBean;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;

public interface DeveloperService {

	//开发人员
	public ResponseEntity<DeveloperVo> getOneDeveloper(Integer id);
	
	public ResponseEntity<List<DeveloperVo>> getAllDevelopers();
	
	public ResponseEntity<String> deleteDeveloper(Integer id);
	
	public boolean check(String username);
	
	public ResponseEntity<Developer> login(String username,String password);

	public ResponseEntity<String> add(DeveloperAddRequestBean bean);

	public ResponseEntity<String> update(DeveloperUpdateRequestBean bean);
	
	public ResponseEntity<String> deleteSomeDevelopers(String ids);
	
	public ResponseEntity<List<DeveloperVo>> selectLikeByUserame(String username);
	
	public ResponseEntity<String> changePassword(DeveloperChangePwdRequestBean bean);

}
