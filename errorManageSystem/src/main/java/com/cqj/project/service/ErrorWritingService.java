package com.cqj.project.service;

import java.util.List;

import com.cqj.project.controller.request.AmaldarErrorWritingUpdateBean;
import com.cqj.project.controller.request.DeveloperErrorWritingUpdateBean;
import com.cqj.project.controller.request.TesterErrorWritingAddBean;
import com.cqj.project.controller.request.TesterErrorWritingUpdateBean;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.vo.ErrorWritingVo;
import com.cqj.project.controller.response.PageResponseBean;

public interface ErrorWritingService {
	//错误报告
		public ResponseEntity<ErrorWritingVo> getOneErrorWriting(Integer id);
				
		public ResponseEntity<List<ErrorWritingVo>> getAllErrorWritings();

		public ResponseEntity<List<ErrorWritingVo>> showErrorByStatus(Integer status);

		public ResponseEntity<String> add(TesterErrorWritingAddBean bean);

		public ResponseEntity<String> testerUpdate(TesterErrorWritingUpdateBean bean);

		public ResponseEntity<String> developerUpdate(DeveloperErrorWritingUpdateBean bean);

		public ResponseEntity<String> amaldarUpdate(AmaldarErrorWritingUpdateBean bean);
		
		public PageResponseBean<ErrorWritingVo> pageErrors(Integer pageNum, Integer pageSize, Integer status, String condition);

		public PageResponseBean<ErrorWritingVo> pageErrorsByDeveloperId(Integer pageNum, Integer pageSize, Integer status, Integer developerId, String condition);

		
}
