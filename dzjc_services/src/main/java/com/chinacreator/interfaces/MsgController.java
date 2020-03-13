package com.chinacreator.interfaces;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import com.chinacreator.dzjc_ruleEngine.web.rest.MsgRecordController;


@Controller
@Path("msgInterface/")
public class MsgController {
	/*@Autowired*/
	private static MsgRecordController msgRecordController = new MsgRecordController();
	
	@GET
	@Path("msg")
	public String msgReport() {
		
		/*@QueryParam("mobile") String mobile,
		@QueryParam("msgid") String msgid,@QueryParam("status") String status,
		@QueryParam("result") String result*/
		try{
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			System.out.println("111"); 
			/*MsgRecord entity =new MsgRecord();
			entity.setSendMobile(mobile);
			entity.setMsgId(msgid);
			entity = msgRecordController.getMsgRecord(entity);
			if(entity!=null){
				if("0".equals(result)){
					entity.setSendFlag("S");
				}else{
					entity.setSendFlag("F");
					status = URLDecoder.decode(status, "utf-8");
					System.out.println(status);
					if(!StringUtil.isBlank(status)){
						if(status.length()>100){
							status=status.substring(0, 99);
						}
					}
					entity.setResultDes(status);
				}
				msgRecordController.update(entity);
			}*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	
}
