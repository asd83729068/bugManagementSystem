package com.cqj.project.service;

import java.util.List;

import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.TesterAddRequestBean;
import com.cqj.project.controller.request.TesterChangePwdRequestBean;
import com.cqj.project.controller.request.TesterUpdateRequestBean;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Tester;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.TesterVo;

public interface TesterService {

	//测试人员
	public ResponseEntity<TesterVo> getOneTester(Integer id);
	
	public ResponseEntity<List<TesterVo>> getAllTesters();
	
	public ResponseEntity<String> deleteTester(Integer id);
	
	public boolean check(String username);
	
	public ResponseEntity<Tester> login(String username,String password);

	public ResponseEntity<String> add(TesterAddRequestBean bean);

	public ResponseEntity<String> update(TesterUpdateRequestBean bean);
	
	public ResponseEntity<String> deleteSomeTesters(String ids);
	
	public ResponseEntity<List<TesterVo>> selectLikeByUserame(String username);
	
	public ResponseEntity<String> changePassword(TesterChangePwdRequestBean bean);
}
