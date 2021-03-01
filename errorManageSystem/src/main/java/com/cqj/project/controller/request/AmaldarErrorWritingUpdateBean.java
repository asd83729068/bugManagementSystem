package com.cqj.project.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class AmaldarErrorWritingUpdateBean {
	@ApiModelProperty(value="报告id,必填",required=true)
	private Integer id;
	@ApiModelProperty(value="报告状态",required=true)
	private Integer status;
	@ApiModelProperty(value="处理人员id")
	private Integer dealerid;
	@ApiModelProperty(value="经理人员id",required=true)
    private Integer amaldarid;
	
	
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
	public Integer getDealerid() {
		return dealerid;
	}
	public void setDealerid(Integer dealerid) {
		this.dealerid = dealerid;
	}
	public Integer getAmaldarid() {
		return amaldarid;
	}
	public void setAmaldarid(Integer amaldarid) {
		this.amaldarid = amaldarid;
	}
	
}
