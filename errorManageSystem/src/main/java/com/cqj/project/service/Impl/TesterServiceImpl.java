package com.cqj.project.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.TesterAddRequestBean;
import com.cqj.project.controller.request.TesterChangePwdRequestBean;
import com.cqj.project.controller.request.TesterUpdateRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Tester;
import com.cqj.project.dao.mapper.TesterMapper;
import com.cqj.project.service.TesterService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.TesterVo;
@Service
public class TesterServiceImpl implements TesterService{

	@Resource
	private TesterMapper testerMapper;
	
	@Override
	public ResponseEntity<TesterVo> getOneTester(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Tester tester=testerMapper.selectByPrimaryKey(id);
		if (tester==null) {
			return ResponseEntityUtil.fail("该测试人员不存在");
		}
		return ResponseEntityUtil.success(this.assembleTesterVo(tester));
	}

	@Override
	public ResponseEntity<List<TesterVo>> getAllTesters() {
		List<Tester> testers=testerMapper.selectAllTesters();
		if (testers.size()==0) {
			return ResponseEntityUtil.fail("数据不存在！");
		}
		List<TesterVo> testerVos=new ArrayList<TesterVo>();
		for (Tester tester : testers) {
			TesterVo testerVo=this.assembleTesterVo(tester);
			testerVos.add(testerVo);
		}
		return ResponseEntityUtil.success(testerVos);
	}

	@Override
	public ResponseEntity<String> deleteTester(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Tester tester=testerMapper.selectByPrimaryKey(id);
		tester.setDel(1);
		int keyCount=testerMapper.updateByPrimaryKey(tester);
		if (keyCount>0) {
			return ResponseEntityUtil.success("删除成功！");
		}
		return ResponseEntityUtil.fail("删除失败！");
	}

	@Override
	public boolean check(String username) {
		Tester tester=testerMapper.check(username);
		if (tester==null) {
			return true;
		}
		return false;
	}

	@Override	
	public ResponseEntity<String> add(TesterAddRequestBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		if (!this.check(bean.getUsername())) {
			return ResponseEntityUtil.fail("账号名已被使用！");
		}
		Tester tester=new Tester();
		tester.setCreatedate(new Date());
		tester.setBirthday(bean.getBirth());
		tester.setEmail(bean.getEmail());
		tester.setName(bean.getName());
		tester.setPassword(bean.getPassword());
		tester.setPhone(bean.getPhone());
		tester.setUsername(bean.getUsername());
		tester.setSex(bean.getSex());
		
		int keyCount=testerMapper.insertSelective(tester);
		if (keyCount>0) {
			return ResponseEntityUtil.success("添加测试人员成功！");
		}
		return ResponseEntityUtil.fail("添加测试人员失败！");
	}

	@Override
	public ResponseEntity<String> update(TesterUpdateRequestBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Tester tester2=new Tester();
		tester2.setId(bean.getId());
		tester2.setBirthday(bean.getBirth());
		tester2.setEmail(bean.getEmail());;
		tester2.setPhone(bean.getPhone());
		tester2.setSex(bean.getSex());
		tester2.setUpdatedate(new Date());
		tester2.setName(bean.getName());
		int keyCount=testerMapper.updateByPrimaryKeySelective(tester2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新成功！");
		}
		return ResponseEntityUtil.fail("更新失败！");
	}
	
	private TesterVo assembleTesterVo(Tester tester){
		TesterVo testerVo=new TesterVo();
		testerVo.setId(tester.getId());
		testerVo.setBirthday(tester.getBirthday());
		testerVo.setEmail(tester.getEmail());
		testerVo.setName(tester.getName());
		testerVo.setPhone(tester.getPhone());
		testerVo.setUsername(tester.getUsername());
		testerVo.setSex(tester.getSex());
		testerVo.setCreatedate(tester.getCreatedate());
		return testerVo;
	}

	@Override
	public ResponseEntity<Tester> login(String username, String password) {
		if (username==null || password==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Tester tester=testerMapper.login(username, password);
		if (tester==null) {
			return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR.label);
		}
		return ResponseEntityUtil.success(tester);
	}

	@Override
	public ResponseEntity<String> deleteSomeTesters(String ids) {
		String params[] = ids.split(",");//参数jie()
		List<Integer> list=new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
         list.add(Integer.valueOf(params[i]));
        }
        int keyCount=testerMapper.deleteSomeTesters(list);
        if (keyCount>0) {
			return ResponseEntityUtil.success("批量删除成功！");
		}
		return ResponseEntityUtil.fail("批量删除失败！");
	}

	@Override
	public ResponseEntity<List<TesterVo>> selectLikeByUserame(String username) {
		List<Tester> testers=testerMapper.selectLikeByUsername(username);
		if (testers.size()==0) {
			return ResponseEntityUtil.fail("数据不存在！");
		}
		List<TesterVo> testerVos=new ArrayList<TesterVo>();
		for (Tester tester : testers) {
			TesterVo testerVo=this.assembleTesterVo(tester);
			testerVos.add(testerVo);
		}
		return ResponseEntityUtil.success(testerVos);
	}

	@Override
	public ResponseEntity<String> changePassword(TesterChangePwdRequestBean bean) {
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Tester tester=testerMapper.selectByPrimaryKey(bean.getId());
		
		if (!tester.getPassword().equals(bean.getOldPassword())) {
			return ResponseEntityUtil.fail("旧密码错误！");
		}
		int keyCount=testerMapper.changePassword(bean.getId(),bean.getNewPassword());
		if (keyCount>0) {
			return ResponseEntityUtil.success("修改密码成功！");
		}
		return ResponseEntityUtil.fail("修改密码失败！");
	}
}
