package com.chinacreator.dzjc_complain.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.c2.fs.FileInput;
import com.chinacreator.c2.fs.UploadProcess;
import com.chinacreator.c2.fs.web.Result;
import com.chinacreator.dzjc_complain.TaJcComplainTopheadlines;
import com.chinacreator.util.S3StorageServices;
import com.chinacreator.util.StringUtil;

public class UploadPicFIle extends UploadProcess {

	private S3StorageServices s3Sevice = new S3StorageServices();

	public UploadPicFIle(String processName) {
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

			// 图片编号
			String[] topIds = (String[]) params.get("topId");
			String url = null;
			for (FileInput fileInput : fileList) {
				//url = s3Sevice.UploadFile(fileInput);
				if(topIds[0] != null){
					TaJcComplainTopheadlines pic = DaoFactory.create(TaJcComplainTopheadlines.class).selectByID(topIds[0]);
					InputStream inputStream = fileInput.getInputStream();
					ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
			        byte[] buff = new byte[100];  
			        int rc = 0;  
			        while ((rc = inputStream.read(buff, 0, 100)) > 0) {  
			        	swapStream.write(buff, 0, rc);  
			        }  
			        byte[] in2b = swapStream.toByteArray();  
					pic.setTopPic(in2b);
					DaoFactory.create(TaJcComplainTopheadlines.class).update(pic);
				}
			}
			/*if (url != null && topIds[0] != null) {// 上传成功

				// 查询已保存的新闻图片信息
				TaJcComplainTopheadlines pic = DaoFactory.create(TaJcComplainTopheadlines.class).selectByID(topIds[0]);
				//pic.setTopPic(url);

				DaoFactory.create(TaJcComplainTopheadlines.class).update(pic);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("上传文件失败:" + e.getMessage());
		}

		// 返回文件上传统一结果集
		return uploadResult;
	}
}
