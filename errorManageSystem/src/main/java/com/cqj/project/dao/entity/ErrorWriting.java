package com.cqj.project.dao.entity;

import java.util.Date;

public class ErrorWriting {
    private Integer id;

    private Integer status;

    private String errortheme;

    private Date createdate;

    private Date dealdate;

    private Date enddate;

    private String demoname;

    private Integer ponderance;

    private Integer reporterid;

    private Integer dealerid;

    private Integer amaldarid;

    private Integer priority;

    private String note;

    private String version;

    private Date updatedate;

    private String errorofwriting;

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

    public String getErrortheme() {
        return errortheme;
    }

    public void setErrortheme(String errortheme) {
        this.errortheme = errortheme == null ? null : errortheme.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getDealdate() {
        return dealdate;
    }

    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDemoname() {
        return demoname;
    }

    public void setDemoname(String demoname) {
        this.demoname = demoname == null ? null : demoname.trim();
    }

    public Integer getPonderance() {
        return ponderance;
    }

    public void setPonderance(Integer ponderance) {
        this.ponderance = ponderance;
    }

    public Integer getReporterid() {
        return reporterid;
    }

    public void setReporterid(Integer reporterid) {
        this.reporterid = reporterid;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getErrorofwriting() {
        return errorofwriting;
    }

    public void setErrorofwriting(String errorofwriting) {
        this.errorofwriting = errorofwriting == null ? null : errorofwriting.trim();
    }
}