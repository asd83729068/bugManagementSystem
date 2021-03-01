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
import com.cqj.project.controller.request.AmaldarAddRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.service.AmaldarService;
import com.cqj.project.service.DeveloperService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="后台经理人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Amaldar/Manager/")
public class AmaldarManagerController extends BaseController{
	
	@Autowired
	private AmaldarService amaldarService;
	
	
	@ApiOperation(value = "查找一个经理人员操作接口",notes = "查找一个经理人员")
	@PostMapping(value="getOne.do")
	public ResponseEntity<AmaldarVo> getOneAmaldar(Integer id){
		ResponseEntity<AmaldarVo> amaldarVo=amaldarService.getOne(id);
		if (amaldarVo.isSuccess()) {
			return ResponseEntityUtil.success(amaldarVo.getData());
		}
		return amaldarVo;
	}
	
	
	@ApiOperation(value = "经理人员显示操作接口",notes = "显示所有经理人员")
	@GetMapping(value="getAll.do")
	public ResponseEntity<List<AmaldarVo>> getAllAmaldars(){
		return amaldarService.getAll();
	}
	
	
	@ApiOperation(value = "删除一个经理人员操作接口",notes = "删除一个经理人员")
	@PostMapping(value="delete.do")
	public ResponseEntity<String> deleteAmaldar(Integer id){
		return amaldarService.delete(id);
	}
	
	
	@ApiOperation(value = "添加一个经理人员操作接口",notes = "添加一个经理人员")
	@PostMapping(value="add.do")
	public ResponseEntity<String> add(@RequestBody AmaldarAddRequestBean bean){
		return amaldarService.add(bean);
	}
	
	@ApiOperation(value="经理人员批量删除接口",notes="批量删除经理人员")
	@DeleteMapping(value="deleteSome.do")
	public ResponseEntity<String> deleteSome(String ids) {
		if (ids==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return amaldarService.deleteSomeAmaldars(ids);
	}
	
	@ApiOperation(value="经理人员模糊查询接口",notes="模糊查询经理人员")
	@GetMapping(value="selectLikeUsername.do")
	public ResponseEntity<List<AmaldarVo>> selectLikeUsername(String username) {
		if (username==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return amaldarService.selectLikeByUserame(username);
	}
//	@ApiOperation(value = "更新经理人员操作接口",notes = "更新一个经理人员")
//	@PutMapping(value="update.do")
//	public ResponseEntity<String> update(Amaldar amaldar,HttpSession session){
//		Amaldar	amaldat2=(Amaldar) session.getAttribute(Const.AMALDAR_USER);
//		if (amaldat2==null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN.label);
//		}
//		return amaldarService.update(amaldar);
//	}

}
