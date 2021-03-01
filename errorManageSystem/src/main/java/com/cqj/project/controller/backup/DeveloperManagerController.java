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
import com.cqj.project.controller.request.DeveloperAddRequestBean;
import com.cqj.project.dao.entity.Developer;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.service.DeveloperService;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import com.cqj.project.vo.AmaldarVo;
import com.cqj.project.vo.DeveloperVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="后台开发人员操作接口",produces = "application/json")
@RestController
@RequestMapping("/Developer/Manager/")
public class DeveloperManagerController extends BaseController{
	
	@Autowired
	private DeveloperService developerService;
	
	@ApiOperation(value = "查找一个开发人员操作接口",notes = "查找一个开发人员")
	@PostMapping(value="getOne.do")
	public ResponseEntity<DeveloperVo> getOneDeveloper(Integer id){
		ResponseEntity<DeveloperVo> developer2=developerService.getOneDeveloper(id);
		if (developer2.isSuccess()) {
			return ResponseEntityUtil.success(developer2.getData());
		}
		return developer2;
	}
	
	
	@ApiOperation(value = "开发人员显示操作接口",notes = "显示所有开发人员")
	@GetMapping(value="getAll.do")
	public ResponseEntity<List<DeveloperVo>> getAllDevelopers(){
		return developerService.getAllDevelopers();
	}
	
	
	@ApiOperation(value = "删除一个开发人员操作接口",notes = "删除一个开发人员")
	@PostMapping(value="delete.do")
	public ResponseEntity<String> deleteDeveloper(Integer id){
		return developerService.deleteDeveloper(id);
	}
	
	
	@ApiOperation(value = "添加一个开发人员操作接口",notes = "添加一个开发人员")
	@PostMapping(value="add.do")
	public ResponseEntity<String> add(@RequestBody DeveloperAddRequestBean bean){
		return developerService.add(bean);
	}
	
	@ApiOperation(value="开发人员批量删除接口",notes="批量删除开发人员")
	@DeleteMapping(value="deleteSome.do")
	public ResponseEntity<String> deleteSome(String ids) {
		if (ids==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return developerService.deleteSomeDevelopers(ids);
	}

	@ApiOperation(value="开发人员模糊查询接口",notes="模糊查询开发人员")
	@GetMapping(value="selectLikeUsername.do")
	public ResponseEntity<List<DeveloperVo>> selectLikeUsername(String username) {
		if (username==null) {
			return ResponseEntityUtil.fail(com.cqj.project.contants.Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		return developerService.selectLikeByUserame(username);
	}
	
}

