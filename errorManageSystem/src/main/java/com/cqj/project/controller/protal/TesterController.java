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
import com.cqj.project.controller.request.TesterChangePwdRequestBean;
import com.cqj.project.controller.request.TesterErrorWritingAddBean;
import com.cqj.project.controller.request.TesterErrorWritingUpdateBean;
import com.cqj.project.controller.request.TesterLoginBean;
import com.cqj.project.controller.request.TesterUpdateRequestBean;
import com.cqj.project.dao.entity.ErrorWriting;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Tester;
import com.cqj.project.service.ErrorWritingService;
import com.cqj.project.service.TesterService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.ErrorWritingVo;
import com.cqj.project.vo.TesterVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="前台测试人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Tester/protal/")
public class TesterController {

	@Autowired
	private TesterService testerService;
	@Autowired
	private ErrorWritingService errorWritingService;
	
	@ApiOperation(value = "测试人员登录操作接口",notes = "测试人员登录")
	@PostMapping(value="login.do")
	public ResponseEntity<Tester> login(@RequestBody TesterLoginBean bean,HttpSession session){
		ResponseEntity<Tester> tester=testerService.login(bean.getUsername(), bean.getPassword());
		if (tester.isSuccess()) {
			session.setAttribute(Const.TEST_USER,tester.getData());
		}
		return tester;
	}
	
	@ApiOperation(value = "更新测试人员操作接口",notes = "更新一个测试人员")
	@PutMapping(value="update.do")
	public ResponseEntity<String> update(@RequestBody TesterUpdateRequestBean bean){
		return testerService.update(bean);
	}
	
	@ApiOperation(value = "查看测试人员自己操作接口",notes = "查找测试人员自己")
	@GetMapping(value="getOwn.do")
	public ResponseEntity<TesterVo> getOwn(Integer id){
		ResponseEntity<TesterVo> testerVo=testerService.getOneTester(id);
		if (testerVo.isSuccess()) {
			return ResponseEntityUtil.success(testerVo.getData());
		}
		return testerVo;
	}
	
	@ApiOperation(value = "添加一个错误报告操作接口",notes = "添加一个错误报告")
	@PostMapping(value="add.do")
	public ResponseEntity<String> add(@RequestBody TesterErrorWritingAddBean bean) {
		return errorWritingService.add(bean);
	}
	
	
	@ApiOperation(value = "更新一个错误报告操作接口",notes = "更新一个错误报告")
	@PutMapping(value="updateError.do")
	public ResponseEntity<String> update(@RequestBody TesterErrorWritingUpdateBean bean) {
		return errorWritingService.testerUpdate(bean);
	}
	
	@ApiOperation(value = "显示错误报告通过状态的操作接口",notes = "显示错误报告通过状态")
	@GetMapping(value="getErrorByStatus.do")
	public ResponseEntity<List<ErrorWritingVo>> getErrorByStatus(Integer status) {
		return errorWritingService.showErrorByStatus(status);
	}
	
	@ApiOperation(value = "更新一个测试人员的密码操作接口",notes = "更新一个测试人员的密码")
	@PutMapping(value="updatePassword.do")
	public ResponseEntity<String> updatePassword(@RequestBody TesterChangePwdRequestBean bean) {
		return testerService.changePassword(bean);
	}
}
