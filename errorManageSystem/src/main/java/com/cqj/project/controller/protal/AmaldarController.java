package com.cqj.project.controller.protal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cqj.project.contants.Const;
import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.AmaldarErrorWritingUpdateBean;
import com.cqj.project.controller.request.AmaldarLoginBean;
import com.cqj.project.controller.request.AmaldarUpdateRequestBean;
import com.cqj.project.controller.request.TesterErrorWritingUpdateBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.ErrorWriting;
import com.cqj.project.dao.entity.Tester;
import com.cqj.project.service.AmaldarService;
import com.cqj.project.service.ErrorWritingService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.ErrorWritingVo;
import com.cqj.project.vo.TesterVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="前台经理人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Amaldar/protal/")
public class AmaldarController {

	@Autowired
	private AmaldarService amaldarService;
	@Autowired
	private ErrorWritingService errorWritingService;
	
	@ApiOperation(value = "经理人员登录操作接口",notes = "经理人员登录")
	@PostMapping(value="login.do")
	public ResponseEntity<Amaldar> login(@RequestBody AmaldarLoginBean bean,HttpSession session){
		ResponseEntity<Amaldar> amaldar=amaldarService.login(bean.getUsername(), bean.getPassword());
		if (amaldar.isSuccess()) {
			session.setAttribute(Const.AMALDAR_USER, amaldar.getData());
		}
		return amaldar;
	}
	
	@ApiOperation(value = "更新经理人员操作接口",notes = "更新一个经理人员")
	@PutMapping(value="update.do")
	public ResponseEntity<String> update(@RequestBody AmaldarUpdateRequestBean bean){
		return amaldarService.update(bean);
	}
	
	@ApiOperation(value = "查看经理人员自己操作接口",notes = "查找经理人员自己")
	@GetMapping(value="getOwn.do")
	public ResponseEntity<AmaldarVo> getOwn(Integer id){
		ResponseEntity<AmaldarVo> amaldarVo=amaldarService.getOne(id);
		if (amaldarVo.isSuccess()) {
			return ResponseEntityUtil.success(amaldarVo.getData());
		}
		return amaldarVo;
	}
	
	@ApiOperation(value = "显示错误报告通过状态的操作接口",notes = "显示错误报告通过状态")
	@GetMapping(value="getErrorByStatus.do")
	public ResponseEntity<List<ErrorWritingVo>> getErrorByStatus(Integer status) {
		return errorWritingService.showErrorByStatus(status);
	}
	
	
	@ApiOperation(value = "更新一个错误报告操作接口",notes = "更新一个错误报告")
	@PutMapping(value="updateError.do")
	public ResponseEntity<String> update(@RequestBody AmaldarErrorWritingUpdateBean bean) {
		return errorWritingService.amaldarUpdate(bean);
	}
	
	@ApiOperation(value = "更新一个经理的密码操作接口",notes = "更新一个经理的密码")
	@PutMapping(value="updatePassword.do")
	public ResponseEntity<String> updatePassword(@RequestBody AmaldarChangePwdRequestBean bean) {
		return amaldarService.changePassword(bean);
	}
	
}
