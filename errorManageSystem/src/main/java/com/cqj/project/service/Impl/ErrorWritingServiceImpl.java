package com.cqj.project.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;
import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.AmaldarErrorWritingUpdateBean;
import com.cqj.project.controller.request.DeveloperErrorWritingUpdateBean;
import com.cqj.project.controller.request.TesterErrorWritingAddBean;
import com.cqj.project.controller.request.TesterErrorWritingUpdateBean;
import com.cqj.project.controller.response.PageResponseBean;
import com.cqj.project.dao.entity.ErrorWriting;
import com.cqj.project.dao.mapper.AmaldarMapper;
import com.cqj.project.dao.mapper.DeveloperMapper;
import com.cqj.project.dao.mapper.ErrorWritingMapper;
import com.cqj.project.dao.mapper.TesterMapper;
import com.cqj.project.service.ErrorWritingService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.ErrorWritingVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ErrorWritingServiceImpl implements ErrorWritingService{

	@Resource
	private ErrorWritingMapper errorWritingMapper;
	
	@Resource
	private TesterMapper testerMapper;
	
	@Resource
	private DeveloperMapper developerMapper;
	
	@Resource
	private AmaldarMapper amaldarMapper;
	
	@Override
	public ResponseEntity<ErrorWritingVo> getOneErrorWriting(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		ErrorWriting errorWriting=errorWritingMapper.selectByPrimaryKey(id);
		if (errorWriting==null) {
			return ResponseEntityUtil.fail("该错误报告不存在");
		}
		return ResponseEntityUtil.success(this.assembleErrorWritingVo(errorWriting));
	}
	

	@Override
	public ResponseEntity<List<ErrorWritingVo>> getAllErrorWritings() {
		List<ErrorWriting> errorWritings=errorWritingMapper.selectAllErrorWritings();
		if (errorWritings.size()==0) {
			return ResponseEntityUtil.fail("数据不存在！");
		}
		List<ErrorWritingVo> errorWritingVos=new ArrayList<ErrorWritingVo>();
		for (ErrorWriting errorWriting : errorWritings) {
			ErrorWritingVo errorWritingVo=this.assembleErrorWritingVo(errorWriting);
			errorWritingVos.add(errorWritingVo);
		}
		return ResponseEntityUtil.success(errorWritingVos);
	}

	@Override
	public ResponseEntity<String> add(TesterErrorWritingAddBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		ErrorWriting errorWriting=new ErrorWriting();
		errorWriting.setDemoname(bean.getDemoname());
		errorWriting.setErrorofwriting(bean.getErrorofwriting());
		errorWriting.setErrortheme(bean.getErrortheme());
		errorWriting.setNote(bean.getNote());
		errorWriting.setPonderance(bean.getPonderance());
		errorWriting.setPriority(bean.getPriority());
		errorWriting.setStatus(bean.getStatus());
		errorWriting.setVersion(bean.getVersion());
		errorWriting.setReporterid(bean.getReporterid());
		errorWriting.setCreatedate(new Date());
		int keyCount=errorWritingMapper.insertSelective(errorWriting);
		if (keyCount>0) {
			return ResponseEntityUtil.success("添加错误报告成功！");
		}
		return ResponseEntityUtil.fail("添加错误报告失败！");
	}

	@Override
	public ResponseEntity<String> testerUpdate(TesterErrorWritingUpdateBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		ErrorWriting errorWriting=errorWritingMapper.selectByPrimaryKey(bean.getId());
		ErrorWriting errorWriting2=new ErrorWriting();
		errorWriting2.setId(bean.getId());
		errorWriting2.setDemoname(bean.getDemoname());
		errorWriting2.setErrorofwriting(bean.getErrorofwriting());
		errorWriting2.setErrortheme(bean.getErrortheme());
		errorWriting2.setNote(bean.getNote());
		errorWriting2.setPonderance(bean.getPonderance());
		errorWriting2.setPriority(bean.getPriority());
		errorWriting2.setVersion(bean.getVersion());
		errorWriting2.setStatus(bean.getStatus());
		errorWriting2.setUpdatedate(new Date());
		if (testerMapper.getOne(errorWriting.getReporterid())==null){
			errorWriting2.setReporterid(bean.getReporterid());
		}
		if (bean.getStatus()==0) {
			errorWriting2.setEnddate(new Date());
		}
		int keyCount=errorWritingMapper.updateByPrimaryKeySelective(errorWriting2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新错误报告成功！");
		}
		return ResponseEntityUtil.fail("更新错误报告失败！");
	}
	
	@Override
	public ResponseEntity<String> developerUpdate(DeveloperErrorWritingUpdateBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		ErrorWriting errorWriting2=new ErrorWriting();
		errorWriting2.setId(bean.getId());
		errorWriting2.setStatus(bean.getStatus());
		errorWriting2.setUpdatedate(new Date());
		int keyCount=errorWritingMapper.updateByPrimaryKeySelective(errorWriting2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新错误报告成功！");
		}
		return ResponseEntityUtil.fail("更新错误报告失败！");
	}
	
	@Override
	public ResponseEntity<String> amaldarUpdate(AmaldarErrorWritingUpdateBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		ErrorWriting errorWriting2=new ErrorWriting();
		errorWriting2.setId(bean.getId());
		errorWriting2.setAmaldarid(bean.getAmaldarid());
		errorWriting2.setDealerid(bean.getDealerid());
		errorWriting2.setStatus(bean.getStatus());
		errorWriting2.setUpdatedate(new Date());
		int keyCount=errorWritingMapper.updateByPrimaryKeySelective(errorWriting2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新错误报告成功！");
		}
		return ResponseEntityUtil.fail("更新错误报告失败！");
	}


	
	private ErrorWritingVo assembleErrorWritingVo(ErrorWriting errorWriting) {
		ErrorWritingVo errorWritingVo=new ErrorWritingVo();
		errorWritingVo.setId(errorWriting.getId());
		errorWritingVo.setCreatedate(errorWriting.getCreatedate());
		errorWritingVo.setDealdate(errorWriting.getDealdate());
		errorWritingVo.setDemoname(errorWriting.getDemoname());
		errorWritingVo.setErrorofwriting(errorWriting.getErrorofwriting());
		errorWritingVo.setErrortheme(errorWriting.getErrortheme());
		errorWritingVo.setNote(errorWriting.getNote());
		errorWritingVo.setPonderance(errorWriting.getPonderance());
		errorWritingVo.setPriority(errorWriting.getPriority());
		errorWritingVo.setStatus(errorWriting.getStatus());
		errorWritingVo.setVersion(errorWriting.getVersion());
		errorWritingVo.setEnddate(errorWriting.getEnddate());
		if (errorWriting.getAmaldarid()==null) {
			errorWritingVo.setAmaldarName("");
		}else {
			if (amaldarMapper.getOne(errorWriting.getAmaldarid())==null) {
				errorWritingVo.setAmaldarName(amaldarMapper.selectByPrimaryKey(errorWriting.getAmaldarid()).getName()+"（已离职）");
			}
			else {
				errorWritingVo.setAmaldarName(amaldarMapper.selectByPrimaryKey(errorWriting.getAmaldarid()).getName());
			}	
		}
		if (errorWriting.getDealerid()==null) {
			errorWritingVo.setDealerName("");
		}
		else {
			if (developerMapper.getOne(errorWriting.getDealerid())==null) {
				errorWritingVo.setDealerName(developerMapper.selectByPrimaryKey(errorWriting.getDealerid()).getName()+"（已离职）");	
			}
			else {
				errorWritingVo.setDealerName(developerMapper.selectByPrimaryKey(errorWriting.getDealerid()).getName());	
			}
		}
		
		if (testerMapper.getOne(errorWriting.getReporterid())==null) {
			errorWritingVo.setReporterName(testerMapper.selectByPrimaryKey(errorWriting.getReporterid()).getName()+"（已离职）");	
		}
		else {
			errorWritingVo.setReporterName(testerMapper.selectByPrimaryKey(errorWriting.getReporterid()).getName());
		}
		return errorWritingVo;
		
	}


	@Override
	public ResponseEntity<List<ErrorWritingVo>> showErrorByStatus(Integer status) {
		List<ErrorWriting> errorWritings=errorWritingMapper.selectErrorWritingsByStatus(status);
		if (errorWritings.size()==0) {
			return ResponseEntityUtil.fail("数据不存在！");
		}
		List<ErrorWritingVo> errorWritingVos=new ArrayList<ErrorWritingVo>();
		for (ErrorWriting errorWriting : errorWritings) {
			ErrorWritingVo errorWritingVo=this.assembleErrorWritingVo(errorWriting);
			errorWritingVos.add(errorWritingVo);
		}
		return ResponseEntityUtil.success(errorWritingVos);
	}


	@Override
	public PageResponseBean<ErrorWritingVo> pageErrors(Integer pageNum, Integer pageSize, Integer status, String condition) {
		PageHelper.startPage(pageNum, pageSize);
		//List<ErrorWriting> errorWritings=(List<ErrorWriting>) errorWritingMapper.selectErrorWritingsByStatus(status);
		List<ErrorWriting> errorWritings = errorWritingMapper.selectErrorWritingsByStatusAndCondition(status,condition);
		List<ErrorWritingVo> errorWritingVos=new ArrayList<>();
		for (ErrorWriting errorWriting: errorWritings) {
			ErrorWritingVo errorWritingVo=this.assembleErrorWritingVo(errorWriting);
			errorWritingVos.add(errorWritingVo);
		}
		PageInfo pageInfo=new PageInfo(errorWritings);
		pageInfo.setList(errorWritingVos);
		return new PageResponseBean<ErrorWritingVo>(pageInfo);
	}

	@Override
	public PageResponseBean<ErrorWritingVo> pageErrorsByDeveloperId(Integer pageNum, Integer pageSize,
																	Integer status, Integer developerId, String condition) {
		PageHelper.startPage(pageNum, pageSize);
		List<ErrorWriting> errorWritings=(List<ErrorWriting>) errorWritingMapper.selectErrorWritingsByStatusAndDealerIdAndCondition(status,developerId, condition);
		List<ErrorWritingVo> errorWritingVos=new ArrayList<>();
		for (ErrorWriting errorWriting: errorWritings) {
			ErrorWritingVo errorWritingVo=this.assembleErrorWritingVo(errorWriting);
			errorWritingVos.add(errorWritingVo);
		}
		PageInfo pageInfo=new PageInfo(errorWritings);
		pageInfo.setList(errorWritingVos);
		return new PageResponseBean<ErrorWritingVo>(pageInfo);
	}



}
