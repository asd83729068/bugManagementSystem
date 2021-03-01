package com.cqj.project.controller.backup;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import com.cqj.project.controller.request.AmaldarChangePwdRequestBean;
import com.cqj.project.controller.request.ManagerAddRequestBean;
import com.cqj.project.controller.request.ManagerChangePwRequestBean;
import com.cqj.project.controller.request.ManagerLoginBean;
import com.cqj.project.controller.request.ManagerUpdateRequestBean;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.service.ManagerService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.ManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="后台管理员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Manager/")
public class ManagerController extends BaseController{
	
	@Autowired
	private ManagerService managerService;
	
	@ApiOperation(value = "管理员登录操作接口",notes = "管理员登录")
	@PostMapping(value="login.do")
	public ResponseEntity<Manager> login(@RequestBody ManagerLoginBean bean,HttpSession session){
		ResponseEntity<Manager> manager=managerService.login(bean.getUsername(),bean.getPassword());
		if (manager.isSuccess()) {
			session.setAttribute(Const.MANAGE_USER, manager.getData());
		}
		return manager;
	}
	

	@ApiOperation(value = "显示所有管理员操作接口",notes = "显示管理员")
	@GetMapping(value="getList.do")
	public ResponseEntity<List<ManagerVo>> getAllManagers(){
//		Manager manager=(Manager) session.getAttribute(Const.MANAGE_USER);
//		if (manager==null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN.label);
//		}
		ResponseEntity<List<ManagerVo>> managerVos=(ResponseEntity<List<ManagerVo>>) managerService.getAllManagers();
		if (managerVos.isSuccess()) {
			return ResponseEntityUtil.success(managerVos.getData());
		}
		return managerVos;
	}
	
	@ApiOperation(value = "插入一个管理员操作接口",notes = "添加一个管理员")
	@PostMapping(value="add.do")
	public ResponseEntity<String> insertManager(@RequestBody ManagerAddRequestBean bean){
		return managerService.insert(bean);
	}
	
	@ApiOperation(value = "删除一个管理员操作接口",notes = "通过id删除一个管理员")
	@DeleteMapping(value="delete.do")
	public ResponseEntity<String> deleteManager(Integer id){
		return managerService.delete(id);
	}
	
	@ApiOperation(value = "更新一个管理员操作接口",notes = "更新一个管理员")
	@PutMapping(value="update.do")
	public ResponseEntity<String> updateManager(@RequestBody ManagerUpdateRequestBean bean){
		return managerService.update(bean);
	}
	
	@ApiOperation(value = "查找一个管理员操作接口",notes = "查找一个管理员")
	@GetMapping(value="getOne.do")
	public ResponseEntity<ManagerVo> getOneManager(Integer id){
		
//		Manager manager2=(Manager) session.getAttribute(Const.MANAGE_USER);
//		if (manager2==null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN.code,Errors.SYSTEM_NOT_LOGIN.label);
//		}
		return managerService.getOneManager(id);
	}

	
	@ApiOperation(value = "用户名校验操作接口",notes = "对输入的用户名进行校验")
	@PostMapping(value="check.do")
	public boolean check(String username){
		return managerService.check(username);
	}

	@ApiOperation(value = "更新一个管理员的密码操作接口",notes = "更新一个管理员的密码")
	@PutMapping(value="updatePassword.do")
	public ResponseEntity<String> updatePassword(@RequestBody ManagerChangePwRequestBean bean) {
		return managerService.changePassword(bean);
	}
	
	@ApiOperation(value="管理人员批量删除接口",notes="批量删除管理人员")
	@DeleteMapping(value="deleteSome.do")
	public ResponseEntity<String> deleteSome(String ids) {
		if (ids==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return managerService.deleteSomeManagers(ids);
	}
	
	@ApiOperation(value="管理人员模糊查询接口",notes="模糊查询管理人员")
	@GetMapping(value="selectLikeUsername.do")
	public ResponseEntity<List<ManagerVo>> selectLikeUsername(String username) {
		if (username==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return managerService.selectLikeByUserame(username);
	}
	
}
