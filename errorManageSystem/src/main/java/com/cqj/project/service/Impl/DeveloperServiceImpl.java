package com.cqj.project.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.DeveloperAddRequestBean;
import com.cqj.project.controller.request.DeveloperChangePwdRequestBean;
import com.cqj.project.controller.request.DeveloperUpdateRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.mapper.DeveloperMapper;
import com.cqj.project.service.DeveloperService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;
@Service
public class DeveloperServiceImpl implements DeveloperService{

	@Resource
	private DeveloperMapper developerMapper;
	
	@Override
	public ResponseEntity<DeveloperVo> getOneDeveloper(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Developer developer=developerMapper.selectByPrimaryKey(id);
		if (developer==null) {
			return ResponseEntityUtil.fail("该开发人员不存在");
		}
		return ResponseEntityUtil.success(this.assembleDeveloperVo(developer));
	}

	@Override
	public ResponseEntity<List<DeveloperVo>> getAllDevelopers() {
		List<Developer> developers=developerMapper.selectAllDevelopers();
		List<DeveloperVo> developerVos=new ArrayList<DeveloperVo>();
		if (developers.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Developer developer : developers) {
			DeveloperVo developerVo=this.assembleDeveloperVo(developer);
			developerVos.add(developerVo);
		}
		return ResponseEntityUtil.success(developerVos);
		
	}

	@Override
	public ResponseEntity<String> deleteDeveloper(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Developer developer=developerMapper.selectByPrimaryKey(id);
		developer.setDel(1);
		int keyCount=developerMapper.updateByPrimaryKey(developer);
		if (keyCount>0) {
			return ResponseEntityUtil.success("删除成功！");
		}
		return ResponseEntityUtil.fail("删除失败！");
	}

	@Override
	public ResponseEntity<String> add(DeveloperAddRequestBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		if (!this.check(bean.getUsername())) {
			return ResponseEntityUtil.fail("账号名已被使用！");
		}
		Developer developer=new Developer();
		developer.setBirth(bean.getBirth());
		developer.setCreatedate(new Date());
		developer.setEmail(bean.getEmail());
		developer.setName(bean.getName());
		developer.setPassword(bean.getPassword());
		developer.setPhone(bean.getPhone());
		developer.setSex(bean.getSex());
		developer.setUsername(bean.getUsername());
		int keyCount=developerMapper.insertSelective(developer);
		if (keyCount>0) {
			return ResponseEntityUtil.success("添加开发人员成功！");
		}
		return ResponseEntityUtil.fail("添加开发人员失败！");
	}

	@Override
	public ResponseEntity<String> update(DeveloperUpdateRequestBean bean) {	//暂定
		Developer developer2=new Developer();
		developer2.setId(bean.getId());
		developer2.setBirth(bean.getBirth());
		developer2.setEmail(bean.getEmail());
		developer2.setPhone(bean.getPhone());
		developer2.setName(bean.getName());
		developer2.setSex(bean.getSex());
		developer2.setUpdatedate(new Date());
		int keyCount=developerMapper.updateByPrimaryKeySelective(developer2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新成功！");
		}
		return ResponseEntityUtil.fail("更新失败！");
	}

	@Override
	public boolean check(String username) {
		Developer developer=developerMapper.check(username);
		if (developer==null) {
			return true;
		}
		return false;
	}
	
	private DeveloperVo assembleDeveloperVo(Developer developer) {
		DeveloperVo developerVo=new DeveloperVo();
		developerVo.setId(developer.getId());
		developerVo.setBirth(developer.getBirth());
		developerVo.setEmail(developer.getEmail());
		developerVo.setName(developer.getName());
		developerVo.setPhone(developer.getPhone());
		developerVo.setSex(developer.getSex());
		developerVo.setUsername(developer.getUsername());
		developerVo.setCreatedate(developer.getCreatedate());
		return developerVo;
	}

	@Override
	public ResponseEntity<Developer> login(String username, String password) {
		if (username==null || password==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Developer developer=developerMapper.login(username, password);
		if (developer==null) {
			return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR.label);
		}
		return ResponseEntityUtil.success(developer);
	}
	

	@Override
	public ResponseEntity<String> deleteSomeDevelopers(String ids) {
		String params[] = ids.split(",");//参数jie()
		List<Integer> list=new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
         list.add(Integer.valueOf(params[i]));
        }
        int keyCount=developerMapper.deleteSomeDevelopers(list);
        if (keyCount>0) {
			return ResponseEntityUtil.success("批量删除成功！");
		}
		return ResponseEntityUtil.fail("批量删除失败！");
	}

	@Override
	public ResponseEntity<List<DeveloperVo>> selectLikeByUserame(String username) {
		List<Developer> developers=developerMapper.selectLikeByUsername(username);
		List<DeveloperVo> developerVos=new ArrayList<DeveloperVo>();
		if (developers.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Developer developer : developers) {
			DeveloperVo developerVo=this.assembleDeveloperVo(developer);
			developerVos.add(developerVo);
		}
		return ResponseEntityUtil.success(developerVos);
	}
	
	@Override
	public ResponseEntity<String> changePassword(DeveloperChangePwdRequestBean bean) {
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Developer developer=developerMapper.selectByPrimaryKey(bean.getId());
		
		if (!developer.getPassword().equals(bean.getOldPassword())) {
			return ResponseEntityUtil.fail("旧密码错误！");
		}
		int keyCount=developerMapper.changePassword(bean.getId(),bean.getNewPassword());
		if (keyCount>0) {
			return ResponseEntityUtil.success("修改密码成功！");
		}
		return ResponseEntityUtil.fail("修改密码失败！");
	}

}
