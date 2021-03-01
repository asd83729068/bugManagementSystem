package com.cqj.project.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cqj.project.contants.Const;
import com.cqj.project.contants.Errors;
import com.cqj.project.controller.request.ManagerAddRequestBean;
import com.cqj.project.controller.request.ManagerChangePwRequestBean;
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.ManagerUpdateRequestBean;
import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.mapper.ManagerMapper;
import com.cqj.project.service.ManagerService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.ManagerVo;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Resource
	private ManagerMapper managerMapper;
	
	@Override
	public ResponseEntity<Manager> login(String username, String password) {
		if (username==null || password==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Manager manager=managerMapper.login(username, password);
		if (manager==null) {
			return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR.label);
		}
		return ResponseEntityUtil.success(manager);
	}

	@Override
	public ResponseEntity<String> delete(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Manager manager=managerMapper.selectByPrimaryKey(id);
		manager.setDel(Const.DelFlagEnum.DELETED);
		int keyCount=managerMapper.updateByPrimaryKey(manager);
		if (keyCount>0) {
			return ResponseEntityUtil.success("删除成功！");
		}
		return ResponseEntityUtil.fail("删除失败！");
	}

	@Override
	public ResponseEntity<String> insert(ManagerAddRequestBean bean) {	//暂定
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		if (!this.check(bean.getUsername())) {
			return ResponseEntityUtil.fail("账号名已被使用！");
		}
		Manager manager=new Manager();
		manager.setCreatedate(new Date());
		manager.setBirthday(bean.getBirth());
		manager.setEmail(bean.getEmail());
		manager.setName(bean.getName());
		manager.setPassword(bean.getPassword());
		manager.setSex(bean.getSex());
		manager.setPhone(bean.getPhone());
		manager.setUsername(bean.getUsername());
		int keyCount=managerMapper.insertSelective(manager);
		if (keyCount>0) {
			return ResponseEntityUtil.success("插入管理员成功！");
		}
		return ResponseEntityUtil.fail("插入管理员失败！");
	}

	@Override
	public ResponseEntity<List<ManagerVo>> getAllManagers() {
		List<Manager> managers=managerMapper.selectAllManagers();
		List<ManagerVo> managerVos=new ArrayList<ManagerVo>();
		if (managers.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Manager manager : managers) {
			ManagerVo managerVo=this.assembleManagerVo(manager);
			managerVos.add(managerVo);
		}
		return ResponseEntityUtil.success(managerVos);
	}

	@Override
	public ResponseEntity<ManagerVo> getOneManager(Integer id) {
		if (id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR.label);
		}
		Manager manager=managerMapper.selectByPrimaryKey(id);
		if (manager==null) {
			return ResponseEntityUtil.fail("没有该管理员用户！");
		}
		ManagerVo managerVo=this.assembleManagerVo(manager);
		return ResponseEntityUtil.success(managerVo);
	}

	@Override
	public boolean check(String username) {
		Manager manager=managerMapper.check(username);
		if (manager==null) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<String> update(ManagerUpdateRequestBean bean) {	//暂定
		Manager manager2=new Manager();
		manager2.setId(bean.getId());
		manager2.setBirthday(bean.getBirth());
		manager2.setEmail(bean.getEmail());
		manager2.setPhone(bean.getPhone());
		manager2.setSex(bean.getSex());
		manager2.setUpdatedate(new Date());
		int keyCount=managerMapper.updateByPrimaryKeySelective(manager2);
		if (keyCount>0) {
			return ResponseEntityUtil.success("更新成功！");
		}
		return ResponseEntityUtil.fail("更新失败！");
	}

	private ManagerVo assembleManagerVo(Manager manager) {
		ManagerVo managerVo=new ManagerVo();
		managerVo.setId(manager.getId());
		managerVo.setName(manager.getName());
		managerVo.setUsername(manager.getUsername());
		managerVo.setPhone(manager.getPhone());
		managerVo.setBirthday(manager.getBirthday());
		managerVo.setEmail(manager.getEmail());
		managerVo.setSex(manager.getSex());
		managerVo.setPassword(manager.getPassword());
		return managerVo;
	}

	@Override
	public ResponseEntity<String> deleteSomeManagers(String ids) {
		String params[] = ids.split(",");//参数jie()
		List<Integer> list=new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
         list.add(Integer.valueOf(params[i]));
        }
        int keyCount=managerMapper.deleteSomeManagers(list);
        if (keyCount>0) {
			return ResponseEntityUtil.success("批量删除成功！");
		}
		return ResponseEntityUtil.fail("批量删除失败！");
        
	}
	
	@Override
	public ResponseEntity<String> changePassword(ManagerChangePwRequestBean bean) {
		if (bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
			Manager manager=managerMapper.selectByPrimaryKey(bean.getId());
			
			if (!manager.getPassword().equals(bean.getOldPassword())) {
				return ResponseEntityUtil.fail("旧密码错误！");
			}
			int keyCount=managerMapper.changeManagerPassword(bean.getId(),bean.getNewPassword());
			if (keyCount>0) {
				return ResponseEntityUtil.success("修改密码成功！");
			}
			return ResponseEntityUtil.fail("修改密码失败！");
		
	}

	@Override
	public ResponseEntity<List<ManagerVo>> selectLikeByUserame(String username) {
		List<Manager> managers=managerMapper.selectLikeByUsername(username);
		List<ManagerVo> managerVos=new ArrayList<ManagerVo>();
		if (managers.size()==0) {
			return ResponseEntityUtil.fail("没有任何信息！");
		}
		for (Manager manager : managers) {
			ManagerVo managerVo=this.assembleManagerVo(manager);
			managerVos.add(managerVo);
		}
		return ResponseEntityUtil.success(managerVos);
	}

}
