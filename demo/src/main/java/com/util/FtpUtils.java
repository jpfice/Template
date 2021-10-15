package com.util;

import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.system.entity.UpLoadFile;
import com.system.service.IFileService;

public class FtpUtils {

	/**
	 * 利用JSch包实现SFTP上传文件
	 * 
	 * @param bytes    文件字节流
	 * @param fileName 文件名
	 * @throws Exception
	 */
	public static void sshSftp(byte[] bytes, String fileName) throws Exception {
		// 指定的服务器地址
		String ip = "服务器ip地址";
		// 用户名
		String user = "用户名";
		// 密码
		String password = "密码";
		// 服务器端口 默认22
		int port = 22;
		// 上传文件到指定服务器的指定目录 自行修改
		String path = "/root";

		Session session = null;
		Channel channel = null;

		JSch jsch = new JSch();

		if (port <= 0) {
			// 连接服务器，采用默认端口
			session = jsch.getSession(user, ip);
		} else {
			// 采用指定的端口连接服务器
			session = jsch.getSession(user, ip, port);
		}

		// 如果服务器连接不上，则抛出异常
		if (session == null) {
			throw new Exception("session is null");
		}

		// 设置登陆主机的密码
		session.setPassword(password);// 设置密码
		// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "no");
		// 设置登陆超时时间
		session.connect(30000);

		OutputStream outstream = null;
		try {
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(1000);
			ChannelSftp sftp = (ChannelSftp) channel;

			// 进入服务器指定的文件夹
			sftp.cd(path);

			// 列出服务器指定的文件列表
			// Vector v = sftp.ls("*");
			// for(int i=0;i<v.size();i++){
			// System.out.println(v.get(i));
			// }

			// 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
			outstream = sftp.put(fileName);
			//下载
			//InputStream is = sftp.get(path);
			outstream.write(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关流操作
			if (outstream != null) {
				outstream.flush();
				outstream.close();
			}
			if (session != null) {
				session.disconnect();
			}
			if (channel != null) {
				channel.disconnect();
			}
		}
	}
	
	@Autowired
	private IFileService fileService;
	
	/**
	 * 上传文件
	 * 
	 * @param lf
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String uplodeFile(UpLoadFile lf, HttpServletRequest request) throws Exception {
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = "D:/fdream/file/" + file.getOriginalFilename();
					// 上传
					file.transferTo(new File(path));
					String fileurl = "http://localhost:88/fdream/file" + file.getOriginalFilename();
					UpLoadFile learnfile = new UpLoadFile(file.getOriginalFilename(), fileurl, lf.getUplodetime());
					fileService.uplodeFile(learnfile);
				} else {
					System.out.println("上传文件不能为空");
				}

			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("上传所花时间：" + String.valueOf(endTime - startTime) + "ms");
		return "/index";
	}
}
