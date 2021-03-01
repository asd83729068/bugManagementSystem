package com.cqj.project.service;

import java.util.List;

import com.cqj.project.controller.request.ManagerAddRequestBean;
import com.cqj.project.controller.request.ManagerChangePwRequestBean;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.ManagerUpdateRequestBean;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.ManagerVo;

public interface ManagerService{
	
	public ResponseEntity<Manager> login(String username,String password);
	
	public ResponseEntity<String> delete(Integer id);
	
	public ResponseEntity<List<ManagerVo>> getAllManagers();
	
	public ResponseEntity<ManagerVo> getOneManager(Integer id);
	
	public boolean check(String username);
				
	public ResponseEntity<String> deleteSomeManagers(String ids);

	public ResponseEntity<String> update(ManagerUpdateRequestBean bean);

	public ResponseEntity<String> insert(ManagerAddRequestBean bean);
	
	public ResponseEntity<String> changePassword(ManagerChangePwRequestBean bean);
	
	public ResponseEntity<List<ManagerVo>> selectLikeByUserame(String username);
}