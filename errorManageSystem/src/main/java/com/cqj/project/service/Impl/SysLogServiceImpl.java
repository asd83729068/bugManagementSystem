package com.cqj.project.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cqj.project.dao.mapper.SyslogMapper;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Syslog;
import com.cqj.project.service.SysLogService;
import com.cqj.project.util.IdGen;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SyslogMapper syslogMapper;
	
	@Override
	public int add(Syslog sysLog,Manager manager) {
		sysLog.setId(IdGen.uuid());
		sysLog.setCreateBy(manager.getName());
		sysLog.setCreateDate(new Date());
		syslogMapper.insertSelective(sysLog);
		return 0;	
	}

}
