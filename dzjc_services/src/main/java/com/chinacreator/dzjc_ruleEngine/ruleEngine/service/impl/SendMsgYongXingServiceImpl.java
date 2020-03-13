package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.Constant;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.SmsUtil;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.StringUtil;

public class SendMsgYongXingServiceImpl implements SendMsgServiceIfc {
	
	@Override
	public void doStartup() {
		MsgRecord msg = new MsgRecord();
		List<MsgRecord> list =SmsUtil.getAllMsgList(msg);
		runSendMsg(list);

	}
	private void runSendMsg(List<MsgRecord> list) {
		try {
			List<MsgRecord> sendList = new ArrayList<MsgRecord>();
			MsgRecord msg = null;
			for (MsgRecord send : list) {
				//调用接口
				//String returnvalue = SmsUtil.sendMessageWebserviceInfo(send.getSendContent(), send.getSendMobile());
				String returnvalue = SmsUtil.sendMessageHttp(send.getSendContent(), send.getSendMobile());
				msg = new MsgRecord();
				msg.setMsgId(send.getMsgId());
				msg.setSendTime(new java.sql.Date(System.currentTimeMillis()));
				if(!StringUtil.isBlank(returnvalue)){
					//	现场返回测试只有 成功:0  失败:其他
					if("0".equals(returnvalue)){
						msg.setSendFlag(Constant.YES);
					}else{
						msg.setSendFlag(Constant.NO);
					}
					msg.setResultDes(returnvalue);
				}else {
					msg.setSendFlag(Constant.NO);
					msg.setResultDes("接口调用失败");
				}
				sendList.add(msg);
			}
			if(sendList.size() > 0){
				//批量修改短信发送状态
				DaoFactory.create(MsgRecord.class).updateBatch(sendList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("短信引擎启动失败......");
		}
	}

}
