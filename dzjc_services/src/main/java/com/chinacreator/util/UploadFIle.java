package com.chinacreator.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.c2.fs.FileInput;
import com.chinacreator.c2.fs.UploadProcess;
import com.chinacreator.c2.fs.web.Result;
import com.chinacreator.dzjc_complain.TaJcComplainAttachinfo;
import com.chinacreator.dzjc_complain.web.rest.TaJcComplainAttachinfoController;

public class UploadFIle extends UploadProcess {

	private S3StorageServices s3Sevice = new S3StorageServices();

	public UploadFIle(String processName) {
		super(processName);
	}

	@Override
	public boolean exist(String arg0, Map<String, Object> arg1) throws Exception {
		return false;
	}

	@Override
	public boolean processDelete(String arg0, Map<String, Object> arg1) throws Exception {
		return false;
	}

	@Override
	public DownResult processDown(String arg0, Map<String, Object> arg1) throws Exception {
		return null;
	}

	@Override
	public Result processUpload(List<FileInput> fileList, Map<String, Object> params) throws Exception {
		Result uploadResult = null;
		try {

			// 投诉编号
			String[] complainIds = (String[]) params.get("complainId");
			// 业务类型
			String[] businessTypes = (String[]) params.get("businessType");
			String[] attachnames = (String[]) params.get("attachname");
			//todbdir
			String url = null;

			if (fileList.size() > 0) {

				FileInput fileInput = fileList.get(0);

				//文件名
				String fileName ;
				//
				String fileNameString = fileInput.getFileMetadata().getName();
				if(attachnames != null){
					if(StringUtil.isBlank(attachnames[0])){
						fileName= fileNameString;
					}else{
						String substring = fileNameString.substring(fileNameString.lastIndexOf("."));
						fileName=attachnames[0]+substring;
						fileInput.getFileMetadata().setName(fileName);
					}
				}
				else {
					fileName= fileNameString;
				}
				Map<String, String> map = new HashMap<>();
				map.put("complainId", complainIds[0]);
				map.put("fileName", fileName);
				map.put("businessType", businessTypes == null ? "1" : businessTypes[0]);

				Integer rows = 0;

				try {
					rows = DaoFactory
							.create(Integer.class)
							.getSession()
							.selectOne("com.chinacreator.dzjc_complain.TaJcComplainAttachinfoMapper.selectFileName",
									map);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (rows > 0) {
					throw new Exception("材料附件名不能相同，请检查");
				}

				url = s3Sevice.UploadFile(fileInput);
			}

			if (url != null) {// 上传成功
				TaJcComplainAttachinfo attachinfo = new TaJcComplainAttachinfo();
				new EntityUtil().entityPK(attachinfo);// / 生成材料主键UUID
				attachinfo.setComplainId(complainIds[0]);// 投诉ID
				attachinfo.setAttachName(url);//url.substring(url.lastIndexOf("/") + 1)
				attachinfo.setDatasource("2");// 附件来源(1.外网 2.内网)
				attachinfo.setCreaterUserId(StringUtil.getUserInfo().get("id"));// 用户ID
				attachinfo.setBusinessType(businessTypes == null ? "1" : businessTypes[0]);
				System.out.println(attachinfo.getAttachId());

				// 材料表记录信息
				new TaJcComplainAttachinfoController().insert(attachinfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("上传文件失败:" + e.getMessage());
		}

		// 返回文件上传统一结果集
		return uploadResult;
	}
}
