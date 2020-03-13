package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.Constant;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.util.HttpClientUtil;

/**
 * 
 * @author liaolong
 * @date 2018-10-30 
 */
public class SendMsgYuHuaQuServiceImpl implements
	SendMsgServiceIfc {

	// 短信接口地址。
	private static String MSG_URL = ConfigManager.getInstance().getConfig("msg_url");
	
	// 短信接口appid
	private static String APP_ID = ConfigManager.getInstance().getConfig("msg_appid");
	
	private static String SRC_ID = "12";
	
	@Override
	public void doStartup() {
		if(StringUtils.isBlank(MSG_URL) || StringUtils.isBlank(APP_ID)){
			return;
		}
		MsgRecord msg = new MsgRecord();
		List<MsgRecord> list = getAllMsgList(msg);
		//由于雨花区的接口限制，1秒钟只能调10次接口，所以不能用多线程，只能单线程调用，并且每调10次，休眠1秒钟
		runSendMsg(list);
	}

	private void runSendMsg(List<MsgRecord> list) {
		try {
			//调用次数，每调用10次，休眠1秒钟
			int count = 0;
			//已发送列表
			List<MsgRecord> sendList = new ArrayList<MsgRecord>();
			MsgRecord msg = null;
			//调用短信接口
			for(MsgRecord send : list){
				count++;
				String content = send.getSendContent();
				content = content.length() > 500 ? content.substring(0, 499) : content;
				content = URLEncoder.encode(content, "UTF-8");
				String msgUrl = MSG_URL+"?appid="+APP_ID+"&mobile="+send.getSendMobile()+"&srcid="+SRC_ID+"&content="+content+"&encoding=UTF-8";
				//调用接口
				String result = HttpClientUtil.doGet(msgUrl);
				
				msg = new MsgRecord();
				msg.setMsgId(send.getMsgId());
				msg.setSendTime(new java.sql.Date(System.currentTimeMillis()));
				//如果接口返回值不为空，则说明提交成功
				if(StringUtils.isNotBlank(result)){
					msg.setSendFlag(Constant.YES);
					msg.setSendMsgId(result);
				}
				else {
					msg.setSendFlag(Constant.NO);
				}
				sendList.add(msg);
				if(count == 10){
					//批量修改短信发送状态
					DaoFactory.create(MsgRecord.class).updateBatch(sendList);
					Thread.sleep(1000);
					count = 0;
					sendList = new ArrayList<MsgRecord>();
				}
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
	
	private List<MsgRecord> getAllMsgList(MsgRecord msg){
		List<MsgRecord> list = new ArrayList<MsgRecord>();
		list = DaoFactory.create(MsgRecord.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.MsgRecordMapper.selectAllNotSend",msg);
		return list;
	}
	
}
