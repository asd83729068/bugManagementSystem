package com.cqj.project.controller.backup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqj.project.dao.entity.ErrorWriting;
import com.cqj.project.service.ErrorWritingService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.ErrorWritingVo;
import com.cqj.project.controller.request.PageRequestBean;
import com.cqj.project.controller.response.PageResponseBean;
import com.cqj.project.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="错误报告操作接口",produces = "application/json")
@RestController
@RequestMapping("/ErrorWriting/Manager/")
public class ErrorWritingManagerController {
	
	@Autowired
	private ErrorWritingService errorWritingService;
	
	@ApiOperation(value = "查找一个错误报告操作接口",notes = "查找一个错误报告")
	@PostMapping(value="getOne.do")
	public ResponseEntity<ErrorWritingVo> getOne(Integer id) {
		return errorWritingService.getOneErrorWriting(id);
	}
	
	@ApiOperation(value = "显示所有错误报告操作接口",notes = "显示所有错误报告")
	@GetMapping(value="getAll.do")
	public ResponseEntity<List<ErrorWritingVo>> getAll() {
		return errorWritingService.getAllErrorWritings();
	}


	@ApiOperation(value = "显示给开发人员的错误报告操作接口",notes = "显示所有已分配给开发人员错误报告")
	@GetMapping(value="pageByDeveloperId.do")
	public ResponseEntity<PageResponseBean<ErrorWritingVo>> getAllByDeveloperId(Integer pageNum,Integer pageSize,
																				Integer status, Integer developerId, String condition) {
		return ResponseEntityUtil.success(errorWritingService.pageErrorsByDeveloperId(pageNum,pageSize,status,developerId,condition));
	}
	
	@ApiOperation(value = "分页列表",notes = "分页列表")
	@GetMapping(value="page.do")
	public ResponseEntity<PageResponseBean<ErrorWritingVo>> getList(Integer pageNum,Integer pageSize,Integer status, String condition){
	       return ResponseEntityUtil.success(errorWritingService.pageErrors(pageNum,pageSize,status,condition));
	}
}
