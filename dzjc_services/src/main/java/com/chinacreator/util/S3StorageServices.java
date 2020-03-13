package com.chinacreator.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.c2.fs.FileInput;
import com.chinacreator.c2.fs.FileMetadata;

@Service
public class S3StorageServices {
	private static Logger logger = LoggerFactory.getLogger(S3StorageServices.class);

	private static final String CONFIG_S3_URL_KEY = "c2_s3_url";
	private static final String CONFIG_S3_ACCESS_KEY = "c2_s3_accessKey";
	private static final String CONFIG_S3_SECRET_KEY = "c2_s3_secretKey";

	private static final String CONFIG_S3_ACCEPT_BUCKET = "c2_s3_accept_bucket";
	private static final String CONFIG_S3_ACCEPT_PATH = "c2_s3_accept_path";
	private static final String CONFIG_S3_ACCEPT_TOPDF_PATH = "c2_s3_accept_topdf_path";
	private static final String CONFIG_S3_ACCEPT_TOPDF_TEMP = "c2_s3_accept_topdf_temp";

	private String accept_bucket = null;
	private String accept_path = null;
	private String accept_topdf_path = null;
	private String accept_topdf_temp = null;

	public String getS3Url() {
		String strval = "";
		strval = ConfigManager.getInstance().getConfig(CONFIG_S3_URL_KEY);
		return strval;
	}

	public String getS3AccesKey() {
		String strval = "";
		strval = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCESS_KEY);
		return strval;
	}

	public String getS3SecretKey() {
		String strval = "";
		strval = ConfigManager.getInstance().getConfig(CONFIG_S3_SECRET_KEY);
		return strval;
	}

	public String getAccept_bucket() {
		if (null == accept_bucket) {
			accept_bucket = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCEPT_BUCKET);
		}
		if (StringUtils.isBlank(accept_bucket)) {
			accept_bucket = "zwfw";
		}
		return accept_bucket;
	}

	public String getAccept_path() {
		if (null == accept_path) {
			accept_path = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCEPT_PATH);
		}
		if (StringUtils.isBlank(accept_path)) {
			accept_bucket = "accept";
		}
		return accept_path;
	}

	public String getAccept_topdf_path() {
		if (null == accept_topdf_path) {
			accept_topdf_path = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCEPT_TOPDF_PATH);
		}
		if (StringUtils.isBlank(accept_topdf_path)) {
			accept_topdf_path = "accept_pdf";
		}
		return accept_topdf_path;
	}

	public String getAccept_topdf_temp() {
		if (null == accept_topdf_temp) {
			accept_topdf_temp = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCEPT_TOPDF_TEMP);
		}
		if (StringUtils.isBlank(accept_topdf_temp)) {
			accept_topdf_temp = "/opt/pdftemp/";
		}
		return accept_topdf_temp;
	}

	/*
	 * 下载文件 到 指定路径下 url : 下载路径 filepath ： 下载保存的路径 返回值：0 为成功
	 */
	public int downloadFile(String url, String filepath) throws IOException {

		int iret = 1;
		if (StringUtils.isBlank(url) || StringUtils.isBlank(filepath)) {
			return iret;
		}

		File downfile = new File(filepath);
		AmazonS3 client = new S3StorageServices().getClient();
		String bucket = getAccept_bucket();
		S3Object s3Object = null;

		if (StringUtils.isBlank(bucket)) {
			iret = 2;
			return iret;
		}

		if (null == client) {
			iret = 3;
			return iret;
		}

		s3Object = client.getObject(new GetObjectRequest(bucket, url));

		if (!downfile.exists()) {
			downfile.createNewFile();
		}

		FileOutputStream wf = new FileOutputStream(downfile);
		S3ObjectInputStream s3input = s3Object.getObjectContent();
		int count = 0;
		int n = 1024;
		byte buffer[] = new byte[n];

		count = s3input.read(buffer, 0, n);
		while (count != -1) {
			wf.write(buffer, 0, count);
			count = s3input.read(buffer, 0, n);
		}

		iret = 0;
		wf.close();

		return iret;
	}

	/**
	 * 获取一个可用的对象名称
	 * 
	 * @param bucket
	 * @param fileName
	 *            原始文件名
	 * @return
	 */
	public String getObjectName(String bucket, String fileName) {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);

		String base = new StringBuilder().append(year).append("/").append(month).append("/").append(day).append("/")
				.toString();
		String objectName = base + fileName;

		AmazonS3 client = getClient();
		boolean exits = true;
		int index = 1;
		while (exits) {
			objectName = base + index + "-" + fileName;

			exits = client.doesObjectExist(bucket, objectName);
			index++;
		}
		return objectName;
	}

	public String getUrl(String bucket, String key) {
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, key);
		// 设置过期时间
		urlRequest.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000));
		// 生成公用的url
		URL url = getClient().generatePresignedUrl(urlRequest);
		return url.toString();
	}

	private static AmazonS3 client;
	private static String url;

	public AmazonS3 getClient() {
		if (client == null) {
			url = ConfigManager.getInstance().getConfig(CONFIG_S3_URL_KEY);
			String accessKey = ConfigManager.getInstance().getConfig(CONFIG_S3_ACCESS_KEY);
			String secretKey = ConfigManager.getInstance().getConfig(CONFIG_S3_SECRET_KEY);

			AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

			ClientConfiguration clientConfig = new ClientConfiguration();
			// S3指定签名版本（兼容爱数存储）
			// clientConfig.setSignerOverride("S3SignerType");
			clientConfig.setProtocol(Protocol.HTTP);

			// "http://172.17.89.22:32559"
			client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withClientConfiguration(clientConfig)
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withEndpointConfiguration(new EndpointConfiguration(url, "")).build();
		}
		return client;
	}

	/**
	 * 确保bucket已存在，不存在则创建并设为可读
	 * 
	 * @param bucket
	 */
	public void touchBucket(String bucket) {
		AmazonS3 client = getClient();
		boolean isExist = client.doesBucketExist(bucket);
		if (!isExist) {
			CreateBucketRequest request = new CreateBucketRequest(bucket);
			client.createBucket(request);
		}
	}

	/*
	 * public BufferedImage compressImage(BufferedImage img,int size){
	 * BufferedImage newImg = null; int width = img.getWidth(); int height =
	 * img.getHeight(); if(width>size || height>size){ newImg =
	 * Scalr.resize(img,Method.AUTOMATIC,Mode.AUTOMATIC,size); }else{ newImg =
	 * Scalr.resize(img,Method.AUTOMATIC,Mode.AUTOMATIC,width,height); }
	 * img.flush(); return newImg; }
	 */

	public BufferedImage thumbnails(BufferedImage originalImage, int maxWidth, int maxHeight) throws IOException {
		int width = originalImage.getWidth() > maxWidth ? maxWidth : originalImage.getWidth();
		int height = originalImage.getHeight() > maxHeight ? maxHeight : originalImage.getHeight();
		BufferedImage thumbnailImageBuffer = Thumbnails.of(originalImage).size(width, height).asBufferedImage();
		return thumbnailImageBuffer;
	}

	// 文件名改名（xxx.jpg -> xxx_XXX.jpg）

	public String pointInsertString(String base, String insertString) {
		int insertPoint = base.lastIndexOf(".");
		return insertPoint == -1 ? base + insertString : base.substring(0, insertPoint) + insertString
				+ base.substring(insertPoint, base.length());
	}

	/*
	 * public static void main(String[] args) throws IOException { InputStream
	 * fileIn = new FileInputStream("E:/C2.png"); BufferedImage img =
	 * ImageIO.read(fileIn); img.flush(); int size = 200;
	 * 
	 * int width = img.getWidth()>size?size:img.getWidth(); int height =
	 * img.getHeight()>size?size:img.getHeight(); Thumbnails.of(img).size(width,
	 * height).toFile(new File("E:/thumbnail.png")); // BufferedImage newImg =
	 * Scalr.resize(img,Method.AUTOMATIC,Mode.AUTOMATIC,100,200); //
	 * ImageIO.write(newImg, "png", new File("E:/1.png")); }
	 */

	/**
	 * 二维码图片上传
	 */
	public String UploadQRCodeImage(InputStream fileInput, String fileName) {
		AmazonS3 client = new S3StorageServices().getClient();
		ObjectMetadata metadata = new ObjectMetadata();

		String bucket = "zwfw";
		String savePath = "accept/QRCode/";
		String fileRealPath = savePath + "QRCode$$" + fileName;
		PutObjectResult re = client.putObject(new PutObjectRequest("zwfw", fileRealPath, fileInput, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return fileRealPath;
	}

	public String UploadFile(FileInput fileInput) {
		AmazonS3 client = new S3StorageServices().getClient();
		ObjectMetadata metadata = new ObjectMetadata();

		String bucket = "zwfw";
		String newFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String savePath = "accept/" + newFolder + "/";
		long times = new Date().getTime();
		String filename = fileInput.getFileMetadata().getName();
		String fileRealPath = savePath + times + "$$" + fileInput.getFileMetadata().getName();
		PutObjectResult re = client.putObject(new PutObjectRequest("zwfw", fileRealPath, fileInput.getInputStream(),
				metadata).withCannedAcl(CannedAccessControlList.PublicRead));
		return fileRealPath;
	}

	/*
	 * 上传转换后的PDF文件
	 */
	public String Upload_PdfFile(String locfile, String urlfile) {
		AmazonS3 client = new S3StorageServices().getClient();
		File fileInput = new File(locfile);

		if (!fileInput.exists()) {
			return "";
		}

		String bucket = getAccept_bucket();

		String fileRealPath = urlfile;

		PutObjectResult re = client.putObject(new PutObjectRequest(bucket, fileRealPath, fileInput)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		return fileRealPath;
	}

	public String UploadFile(DiskFileItem fileInput) throws IOException {
		AmazonS3 client = new S3StorageServices().getClient();
		ObjectMetadata metadata = new ObjectMetadata();

		String bucket = "zwfw";
		String newFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String savePath = "accept/" + newFolder + "/";
		long times = new Date().getTime();
		String filename = fileInput.getName();
		String fileRealPath = savePath + times + "$$" + fileInput.getName();

		PutObjectResult re = client.putObject(new PutObjectRequest("zwfw", fileRealPath, fileInput.getInputStream(),
				metadata).withCannedAcl(CannedAccessControlList.PublicRead));

		return fileRealPath;
	}

	public DownResult downloadFile(String url) {
		DownResult downResult = new DownResult();
		AmazonS3 client = new S3StorageServices().getClient();
		String bucket = "zwfw";
		S3Object s3Object = client.getObject(new GetObjectRequest("zwfw", url));
		downResult.setInputStream(s3Object.getObjectContent());
		String filename = url.split("\\$\\$")[1];
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setName((filename));
		downResult.setFileMetadata(fileMetadata);
		return downResult;
	}

	/*
	 * public static void main(String[] args) throws FileNotFoundException {
	 * AmazonS3 client = new S3StorageServices().getClient(); String fileName =
	 * "angular.min.js"; File file = new File("D:\\用户目录\\下载\\angular.min.js");
	 * 
	 * ObjectMetadata metadata = new ObjectMetadata();
	 * metadata.setContentLength(file.length());
	 * 
	 * String bucket = "icons"; PutObjectResult re = client.putObject(new
	 * PutObjectRequest("zwfw", "test/"+fileName, file)); String url =
	 * client.getUrl("zwfw", "test/"+fileName).toString();
	 * //client.setObjectAcl("icons", "2017/0/19/1-ceph_logo.png",
	 * CannedAccessControlList.PublicRead); System.out.println("done"); }
	 */
}
