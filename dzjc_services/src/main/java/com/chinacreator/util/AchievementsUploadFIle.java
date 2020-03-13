package com.chinacreator.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.c2.fs.FileInput;
import com.chinacreator.c2.fs.UploadProcess;
import com.chinacreator.c2.fs.web.Result;
import com.chinacreator.dzjc_complain.TaJcComplainAttachinfo;
import com.chinacreator.dzjc_complain.web.rest.TaJcComplainAttachinfoController;
import com.chinacreator.dzjc_performance.EvalComplainReviewe;
import com.chinacreator.dzjc_performance.EvalEnclosure;
import com.chinacreator.dzjc_performance.web.rest.EvalEnclosureController;

public class AchievementsUploadFIle extends UploadProcess {

	private S3StorageServices s3Sevice = new S3StorageServices();

	public AchievementsUploadFIle(String processName) {
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

			// 考核对象名称
			String[] evalObjectName = (String[]) params.get("evalObjectName");
			String [] id=  (String[]) params.get("id");
			// 业务类型
			//todbdir
			String url = null;

			if (fileList.size() > 0) {

				FileInput fileInput = fileList.get(0);

				//文件名
				String fileName ;
				//
				String fileNameString = fileInput.getFileMetadata().getName();
						fileName= fileNameString;
						fileInput.getFileMetadata().setName(fileName);
			

				url = s3Sevice.UploadFile(fileInput);
			}

			if (url != null) {// 上传成功
				EvalEnclosure attachinfo = new EvalEnclosure();
				String uuid = UUID.randomUUID().toString().replaceAll("-",""); 
 				attachinfo.setPathUrl(url);
				attachinfo.setEvalobjectName(evalObjectName[0]);
				attachinfo.setEnclosureId(uuid);
				attachinfo.setId(id[0]);
				java.sql.Date uploadTime = new java.sql.Date(System.currentTimeMillis());  
				attachinfo.setUploadTime(uploadTime);
				List<EvalEnclosure> resultlist=	DaoFactory.create(EvalEnclosure.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalEnclosureMapper.selectByList");
				if(resultlist.size()>0){
					attachinfo.setIsShow("N");
					DaoFactory.create(EvalEnclosure.class).getSession().update("com.chinacreator.dzjc_performance.EvalEnclosureMapper.updateByid",attachinfo);
				}
					attachinfo.setIsShow("Y");
				// 材料表记录信息
				new EvalEnclosureController().insert(attachinfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("上传文件失败:" + e.getMessage());
		}

		// 返回文件上传统一结果集
		return uploadResult;
	}
}
