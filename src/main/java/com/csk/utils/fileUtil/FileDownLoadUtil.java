package com.csk.utils.fileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: Cheng
 * @description: 下载工具类
 * @author: Mr.Cheng
 * @create: 2018-09-05 17:03
 **/
public class FileDownLoadUtil {

    private static Logger logger = LoggerFactory.getLogger(FilesUploadUtil.class);

    /**
     * @Description: 文件下载
     * @param: request
     * @param: response
     * @param: filePath 文件路径
     * @return: void
     * @Author: Mr.Cheng
     * @Date: 17:50 2018/10/8
     */
    public void fileDownLoad (HttpServletRequest request,
                              HttpServletResponse response, String filePath) throws Exception {
        if (response != null && request != null && filePath != null && !"".equals(filePath)) {
            final String userAgent = request.getHeader("USER-AGENT");
            // filePath是指欲下载的文件的路径。
            //filePath=new String(filePath.getBytes("GBK"),"ISO8859_1");//转码后就行了
            filePath = URLDecoder.decode(filePath, "UTF-8");
            File file = new File(filePath);
            if (!file.exists()) {
                logger.info("############ FilePath: " + filePath);
            }
            if (!file.isFile()) {
                logger.info("############ FilePath: " + filePath);
            }
            // 取得文件名。
            String fileName = file.getName();
            if (userAgent.contains("MSIE")) {// IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (userAgent.contains("Mozilla")) {// google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
            }
            // 提示框设置
            response.reset(); // reset the response
            // response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment; filename=\""
                    + fileName + "\"");

            //读出文件到i/o流
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream buff = new BufferedInputStream(fis);
            byte[] ary_byte = new byte[1024];//缓存
            long k = 0;//该值用于计算当前实际下载了多少字节
            // 输出流
            OutputStream out = response.getOutputStream();
            //开始循环下载
            while (k < file.length()) {
                int j = buff.read(ary_byte, 0, 1024);
                k += j;
                //将b中的数据写到客户端的内存
                out.write(ary_byte, 0, j);
            }
            // 关闭输出流
            if (out != null) {
                out.flush();
                out.close();
                fis.close();
                buff.close();
            }
            logger.info("文件下载完毕！");
        } else {
            new NullPointerException(
                    "HttpServletRequest Or HttpServletResponse Or fileName Is Null !");
        }
    }

}
