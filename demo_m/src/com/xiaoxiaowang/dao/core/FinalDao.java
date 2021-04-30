package com.xiaoxiaowang.dao.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.xiaoxiaowang.dao.IBaseDao;
import com.xiaoxiaowang.dao.IEntityHQLDAO;
import com.xiaoxiaowang.dao.IEntitySQLDAO;

@Repository("finalDao")
public class FinalDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public FinalDao() {
		logger.info("正在加载。。。。。。");
	}

	@Autowired
	@Qualifier("iEntityHQLDAOImpl")
	private IEntityHQLDAO iEntityHQLDAO;
	
	@Autowired
	@Qualifier("iEntitySQLDAOImpl")
	private IEntitySQLDAO iEntitySQLDAO;
	
	@Autowired
	@Qualifier("iTemplateDaoImpl")
	private IBaseDao iBaseDAO;

	public IEntityHQLDAO getIEntityHQLDAO() {
		return iEntityHQLDAO;
	}

	public IEntitySQLDAO getIEntitySQLDAO() {
		return iEntitySQLDAO;
	}
	
	public IBaseDao getIBaseDAO() {
		return iBaseDAO;
	}
}
