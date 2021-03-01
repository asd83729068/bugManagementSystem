package com.cqj.project.controller.backup;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cqj.project.contants.Const;
import com.cqj.project.contants.Errors;
import com.cqj.project.controller.BaseController;
import com.cqj.project.controller.request.TesterAddRequestBean;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Tester;
import com.cqj.project.service.DeveloperService;
import com.cqj.project.service.TesterService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;
import com.cqj.project.vo.TesterVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="后台测试人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Tester/Manager/")
public class TesterManagerController extends BaseController{
	
	@Autowired
	private TesterService testerService;
	
	
	@ApiOperation(value = "查找一个测试人员操作接口",notes = "查找一个测试人员")
	@PostMapping(value="getOne.do")
	public ResponseEntity<TesterVo> getOneTester(Integer id){
	
		ResponseEntity<TesterVo> tester=testerService.getOneTester(id);
		if (tester.isSuccess()) {
			return ResponseEntityUtil.success(tester.getData());
		}
		return tester;
	}
	
	
	@ApiOperation(value = "测试人员显示操作接口",notes = "显示所有测试人员")
	@GetMapping(value="getAll.do")
	public ResponseEntity<List<TesterVo>> getAllTesters(){
		
		return testerService.getAllTesters();
	}
	
	
	@ApiOperation(value = "删除一个测试人员操作接口",notes = "删除一个测试人员")
	@PostMapping(value="delete.do")
	public ResponseEntity<String> deleteTester(Integer id){
		return testerService.deleteTester(id);
	}
	
	
	@ApiOperation(value = "添加一个测试人员操作接口",notes = "添加一个测试人员")
	@PostMapping(value="add.do")
	public ResponseEntity<String> add(@RequestBody TesterAddRequestBean bean){
		return testerService.add(bean);
	}

	@ApiOperation(value="测试人员批量删除接口",notes="批量删除测试人员")
	@DeleteMapping(value="deleteSome.do")
	public ResponseEntity<String> deleteSome(String ids) {
		if (ids==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return testerService.deleteSomeTesters(ids);
	}
	 
	@ApiOperation(value="测试人员模糊查询接口",notes="模糊查询测试人员")
	@GetMapping(value="selectLikeUsername.do")
	public ResponseEntity<List<TesterVo>> selectLikeUsername(String username) {
		if (username==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return testerService.selectLikeByUserame(username);
	}
//	@ApiOperation(value = "更新测试人员操作接口",notes = "更新一个测试人员")
//	@PutMapping(value="update.do")
//	public ResponseEntity<String> update(Tester tester,HttpSession session){
//		Tester tester2=(Tester) session.getAttribute(Const.TEST_USER);
//		if (tester2==null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN.label);
//		}
//		return testerService.update(tester);
//	}

}
