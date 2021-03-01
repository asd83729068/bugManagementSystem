package com.cqj.project.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.AmaldarAddRequestBean;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.AmaldarUpdateRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.mapper.AmaldarMapper;
import com.cqj.project.service.AmaldarService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;

@Service
public class AmaldarServiceImpl implements AmaldarService{

	@Resource
	private AmaldarMapper amaldarMapper;
	
	@Override
	public ResponseEntity<AmaldarVo> getOne(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Amaldar amaldar=amaldarMapper.selectByPrimaryKey(id);
		if (amaldar==null) {
			return ResponseEntityUtil.fail("没有该经理用户！");
		}
		AmaldarVo amaldarVo=this.assembleAmaldarVo(amaldar);
		return ResponseEntityUtil.success(amaldarVo);
	}

	@Override
	public ResponseEntity<List<AmaldarVo>> getAll() {
		List<Amaldar> amaldars=amaldarMapper.selectAllAmaldars();
		List<AmaldarVo> amaldarVos=new ArrayList<AmaldarVo>();
		if (amaldars.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Amaldar amaldar : amaldars) {
			AmaldarVo amaldarVo=this.assembleAmaldarVo(amaldar);
			amaldarVos.add(amaldarVo);
		}
		return ResponseEntityUtil.success(amaldarVos);
	}

	@Override
	public ResponseEntity<String> delete(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Amaldar amaldar=amaldarMapper.selectByPrimaryKey(id);
		amaldar.setDel(1);
		int keyCount=amaldarMapper.updateByPrimaryKey(amaldar);
		if (keyCount>0) {
			return ResponseEntityUtil.success("删除成功！");
		}
		return ResponseEntityUtil.fail("删除失败！");
	}

	@Override
	public ResponseEntity<String> update(AmaldarUpdateRequestBean bean) {		//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Amaldar amaldar2=new Amaldar();
		amaldar2.setId(bean.getId());
		amaldar2.setBirth(bean.getBirth());
		amaldar2.setEmail(bean.getEmail());
		amaldar2.setPhone(bean.getPhone());
		amaldar2.setSex(bean.getSex());
		amaldar2.setName(bean.getName());
		amaldar2.setUpdatedate(new Date());
		int keyCount=amaldarMapper.updateByPrimaryKeySelective(amaldar2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新成功！");
		}
		return ResponseEntityUtil.fail("更新失败！");
	}

	@Override
	public ResponseEntity<String> add(AmaldarAddRequestBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Amaldar amaldar=new Amaldar();
		if (!this.check(bean.getUsername())) {
			return ResponseEntityUtil.fail("账号名已被使用！");
		}
		amaldar.setUsername(bean.getUsername());
		amaldar.setCreatedate(new Date());
		amaldar.setBirth(bean.getBirth());
		amaldar.setName(bean.getName());
		amaldar.setEmail(bean.getEmail());
		amaldar.setPassword(bean.getPassword());
		amaldar.setPhone(bean.getPhone());
		amaldar.setSex(bean.getSex());
		int keyCount=amaldarMapper.insertSelective(amaldar);
		if (keyCount>0) {
			return ResponseEntityUtil.success("添加经理成功！");
		}
		return ResponseEntityUtil.fail("添加经理失败！");
	}

	@Override
	public boolean check(String username) {
		Amaldar amaldar=amaldarMapper.check(username);
		if (amaldar==null) {
			return true;
		}
		return false;
	}
	
	private AmaldarVo assembleAmaldarVo(Amaldar amaldar) {
		AmaldarVo amaldarVo=new AmaldarVo();
		amaldarVo.setBirth(amaldar.getBirth());
		amaldarVo.setEmail(amaldar.getEmail());
		amaldarVo.setName(amaldar.getName());
		amaldarVo.setPhone(amaldar.getPhone());
		amaldarVo.setSex(amaldar.getSex());
		amaldarVo.setUsername(amaldar.getUsername());
		amaldarVo.setCreatedate(amaldar.getCreatedate());
		amaldarVo.setId(amaldar.getId());
		return amaldarVo;
	}

	@Override
	public ResponseEntity<Amaldar> login(String username, String password) {
		if (username==null || password==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Amaldar amaldar=amaldarMapper.login(username, password);
		if (amaldar==null) {
			return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR.label);
		}
		return ResponseEntityUtil.success(amaldar);
	}

	@Override
	public ResponseEntity<String> changePassword(AmaldarChangePwdRequestBean bean) {
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Amaldar amaldar=amaldarMapper.selectByPrimaryKey(bean.getId());
	
		if (!amaldar.getPassword().equals(bean.getOldPassword())) {
			return ResponseEntityUtil.fail("旧密码错误！");
		}
		int keyCount=amaldarMapper.changeManagerPassword(bean.getId(),bean.getNewPassword());
		if (keyCount>0) {
			return ResponseEntityUtil.success("修改密码成功！");
		}
		return ResponseEntityUtil.fail("修改密码失败！");
	}

	@Override
	public ResponseEntity<String> deleteSomeAmaldars(String ids) {
		String params[] = ids.split(",");//参数jie()
		List<Integer> list=new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
         list.add(Integer.valueOf(params[i]));
        }
        int keyCount=amaldarMapper.deleteSomeAmaldars(list);
        if (keyCount>0) {
			return ResponseEntityUtil.success("批量删除成功！");
		}
		return ResponseEntityUtil.fail("批量删除失败！");
	}

	@Override
	public ResponseEntity<List<AmaldarVo>> selectLikeByUserame(String username) {
		List<Amaldar> amaldars=amaldarMapper.selectLikeByUsername(username);
		List<AmaldarVo> amaldarVos=new ArrayList<AmaldarVo>();
		if (amaldars.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Amaldar amaldar : amaldars) {
			AmaldarVo amaldarVo=this.assembleAmaldarVo(amaldar);
			amaldarVos.add(amaldarVo);
		}
		return ResponseEntityUtil.success(amaldarVos);
	}

}
