package com.chinacreator.util;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.dzjc_log.LogAudit;

/**
 * 日志审计工具类
 * 
 * @author zyz818
 * 
 */
public class AuditLogUtil {

	private AuditLogUtil() {
	}

	public static void AuditLogToDB(String moduleName, String methodName,
			String businessKey, String opType, String opContent) {

		try {
			LogAudit alog = new LogAudit();
			alog.setSysName(ConstantUtil.SYS_NAME);// 系统名称
			alog.setModuleName(moduleName);// 模块名称

			if (!StringUtil.nullOrBlank(methodName)) {
				alog.setMethodName(methodName);// 方法名称
			}
			if (!StringUtil.nullOrBlank(businessKey)) {
				alog.setBusinessKey(businessKey);// 关键性的业务数据
			}
			if (!StringUtil.nullOrBlank(opContent)) {
				alog.setOpContent(opContent);// 操作内容
			}
			User user = (User) ((WebOperationContext) OperationContextHolder
					.getContext()).getUser();
			alog.setOpType(opType);
			alog.setUserId(user.getId());
			alog.setUserName(user.getRealname());
			alog.setOpTime(new java.sql.Date(new java.util.Date().getTime()));// 产生时间默认为当前时间
			DaoFactory.create(LogAudit.class).insert(alog);// 插入日志到数据表
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
