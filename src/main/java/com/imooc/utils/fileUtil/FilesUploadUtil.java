package com.imooc.utils.fileUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * <p>
 * Description: 文件上传工具类
 * </p>
 * @author shenghuayao
 * @version 1.0
 * Create Date: 2013-3-20 下午3:42:26
 * Project Name: crm
 *
 * <pre>
 * Modification History: 
  *             Date                                Author                   Version          Description 
 * -----------------------------------------------------------------------------------------------------------  
 * LastChange: $Date:: 2013-03-22 #$      $Author: shenghuayao $          $Rev: 2478 $         
 * </pre>
 *
 */
public class FilesUploadUtil {
	
	private Logger log = Logger.getLogger(FilesUploadUtil.class);

	/**
	 * <p>
	 * 文件上传（支持多文件上传）
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-5 上午9:53:58
	 * @param request 请求
	 * @param uploadDirPath
	 *            上传文件夹名,方法内会自动在uploadDir前添加根路径。格式: "upload/tmp/..."
	 * @return Map<"msg",String> 上传成功返回null,失败返回错误信息
	 * @throws IOException 处理过程中出现异常
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> upload(HttpServletRequest request, String uploadDirPath)
			throws IOException {
		Map<String,String> errorMsg=null;
		try {
			// 解析 request，判断是否有上传文件
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				if(uploadDirPath==null||"".equals(uploadDirPath)){
					uploadDirPath="UPLOAD";
				}
				// 获得上传路径
				String webRootPath = this.getClass().getClassLoader()
						.getResource("").getPath();
				// 创建磁盘工厂，利用构造器实现内存数据储存量和临时储存路径
				DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,
						new File(webRootPath));
				// 设置最多只允许在内存中存储的数据,单位:字节
				factory.setSizeThreshold(4096);
				// 设置文件临时存储路径
				// factory.setRepository(new File("D:\\Temp"));
				// 产生一新的文件上传处理程式
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 设置路径、文件名的字符集
				upload.setHeaderEncoding("UTF-8");
				// 设置允许用户上传文件大小,单位:字节
				try {
					upload.setSizeMax(1024 * 1024 * 30);
				} catch (Exception e) {
					errorMsg=new HashMap<String, String>();
					errorMsg.put("msg", "上传文件大小最大为30M");
					log.info("****************上传文件大小最大为30M！");
					e.printStackTrace();
					return errorMsg;
				}
				// 解析请求，开始读取数据
				// Iterator<FileItem> iter = (Iterator<FileItem>)
				// upload.getItemIterator(request);
				// 得到所有的表单域，它们目前都被当作FileItem
				List<FileItem> fileItems = upload.parseRequest(request);
				// 依次处理请求
				Iterator<FileItem> iter = fileItems.iterator();
				String uploadPath = "";
				if (webRootPath.indexOf("WEB-INF") > 0) {
					uploadPath = webRootPath.substring(0,
							webRootPath.indexOf("WEB-INF/classes"));
				}

				// 解决路径中的空格、中文
				uploadPath = URLDecoder.decode(uploadPath, "UTF-8");
				uploadPath += uploadDirPath;
				log.info("文件上传路径：" + uploadPath);
				File dir = new File(uploadPath);
				if (dir.exists() && dir.isDirectory()) {
				} else {
					try {
						if (dir.mkdirs()) {
							log.info("上传文件夹创建成功！");
						}
					} catch (Exception e) {
						errorMsg=new HashMap<String, String>();
						errorMsg.put("msg", "创建文件夹失败！");
						log.info("****************创建文件夹失败！");
						e.printStackTrace();
						return errorMsg;
					}
				}
				StringBuffer filePath = new StringBuffer();
				while (iter.hasNext()) {
					filePath.setLength(0);
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 如果item是正常的表单域
						String name = item.getFieldName();
						String value = item.getString("UTF-8");
						log.info("表单域名为:" + name + "表单域值为:" + value);
					} else {
						// 如果item是文件上传表单域
						// 获得文件名及路径
						String fileName = item.getName();
						if (fileName != null) {
							// 如果文件存在则上传
							File fullFile = new File(item.getName());
							filePath.append(uploadPath).append("/")
									.append(fullFile.getName());
							File fileOnServer = new File(filePath.toString());
							item.write(fileOnServer);
							log.info("文件" + fileOnServer.getName()
									+ "上传成功!");
						}
					}
				}
			}
		} catch (Exception e) {
			errorMsg=new HashMap<String, String>();
			errorMsg.put("msg", "文件上传失败！");
			log.info("****************文件上传失败！");
			e.printStackTrace();
			return errorMsg;
		}
		return errorMsg;
	}



}
