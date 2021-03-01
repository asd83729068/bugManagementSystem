package com.cqj.project.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class DeveloperErrorWritingUpdateBean {
	@ApiModelProperty(value="报告id,必填",required=true)
	private Integer id;
	@ApiModelProperty(value="报告状态",required=true)
	private Integer status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
