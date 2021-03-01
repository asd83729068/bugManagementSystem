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
import com.cqj.project.controller.request.DeveloperChangePwdRequestBean;
import com.cqj.project.controller.request.DeveloperErrorWritingUpdateBean;
import com.cqj.project.controller.request.DeveloperLoginBean;
import com.cqj.project.controller.request.DeveloperUpdateRequestBean;
import com.cqj.project.controller.request.TesterErrorWritingUpdateBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.ErrorWriting;
import com.cqj.project.service.DeveloperService;
import com.cqj.project.service.ErrorWritingService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;
import com.cqj.project.vo.ErrorWritingVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="前台开发人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Developer/protal/")
public class DeveloperController {
	
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private ErrorWritingService errorWritingService;
	
	@ApiOperation(value = "开发人员登录操作接口",notes = "开发人员登录")
	@PostMapping(value="login.do")
	public ResponseEntity<Developer> login(@RequestBody DeveloperLoginBean bean,HttpSession session){
		ResponseEntity<Developer> developer=developerService.login(bean.getUsername(), bean.getPassword());
		if (developer.isSuccess()) {
			session.setAttribute(Const.AMALDAR_USER, developer.getData());
		}
		return developer;
	}
	
	@ApiOperation(value = "更新开发人员操作接口",notes = "更新一个开发人员")
	@PutMapping(value="update.do")
	public ResponseEntity<String> update(@RequestBody DeveloperUpdateRequestBean bean){
		return developerService.update(bean);
	}
	
	@ApiOperation(value = "查看开发人员自己操作接口",notes = "查找开发人员自己")
	@GetMapping(value="getOwn.do")
	public ResponseEntity<DeveloperVo> getOwn(Integer id){
		ResponseEntity<DeveloperVo> developerVo=developerService.getOneDeveloper(id);
		if (developerVo.isSuccess()) {
			return ResponseEntityUtil.success(developerVo.getData());
		}
		return developerVo;
	}
	
	
	@ApiOperation(value = "用状态去显示错误报告的操作接口",notes = "显示状态错误报告通过状态")
	@GetMapping(value="getStatusByStatus.do")
	public ResponseEntity<List<ErrorWritingVo>> getStatusByStatus(Integer status) {
		return errorWritingService.showErrorByStatus(status);
	}
	
	@ApiOperation(value = "更新一个错误报告操作接口",notes = "更新一个错误报告")
	@PutMapping(value="updateError.do")
	public ResponseEntity<String> update(@RequestBody DeveloperErrorWritingUpdateBean bean) {
		return errorWritingService.developerUpdate(bean);
	}
	
	@ApiOperation(value = "更新一个开发人员的密码操作接口",notes = "更新一个开发人员的密码")
	@PutMapping(value="updatePassword.do")
	public ResponseEntity<String> updatePassword(@RequestBody DeveloperChangePwdRequestBean bean) {
		return developerService.changePassword(bean);
	}
}
