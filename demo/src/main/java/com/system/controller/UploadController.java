package com.system.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.util.FtpUtils;

/**
 * @author jpf
 * @Title: UploadController
 * @ProjectName springmvc-demo
 * @Description: TODO
 * @date 2021/10/15 15:00
 */

@Controller
public class UploadController {

	/**
	 * 上传文件到指定服务器（远程服务器、非项目当前所在服务器）
	 */
	@RequestMapping("/File")
	public void upload(HttpServletRequest httpServletRequest, MultipartFile file) throws Exception {

		Logger logger = Logger.getLogger(UploadController.class);
		logger.info("UploadController Start");
		logger.debug("UploadController Start");
		byte[] bytes = file.getBytes();
		FtpUtils.sshSftp(bytes, "1111.jpg");

		logger.info("UploadController End");
		logger.debug("UploadController End");
	}
}
