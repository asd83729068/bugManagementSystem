package com.cqj.project.service;

import java.util.List;

import com.cqj.project.controller.request.AmaldarAddRequestBean;
import com.cqj.project.controller.request.AmaldarUpdateRequestBean;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.AmaldarVo;

public interface AmaldarService {
	//经理人员
	public ResponseEntity<AmaldarVo> getOne(Integer id);
	
	public ResponseEntity<List<AmaldarVo>> getAll();
	
	public ResponseEntity<String> delete(Integer id);
	
	public boolean check(String username);
	
	public ResponseEntity<Amaldar> login(String username,String password);

	public ResponseEntity<String> add(AmaldarAddRequestBean bean);

	public ResponseEntity<String> update(AmaldarUpdateRequestBean bean);
	
	public ResponseEntity<String> changePassword(AmaldarChangePwdRequestBean bean);
	
	public ResponseEntity<String> deleteSomeAmaldars(String ids);
	
	public ResponseEntity<List<AmaldarVo>> selectLikeByUserame(String username);
}
