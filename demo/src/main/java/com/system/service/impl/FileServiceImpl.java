package com.system.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.entity.UpLoadFile;
import com.system.mapper.FileMapper;
import com.system.service.IFileService;

/**
首先，我在tomcat的server.xml配置文件配置两句话 。
以后我就把上传的图片放到本地服务器D盘的fdream文件夹下面的img目录中，
文件就放到D盘的fdream文件夹下面的file目录中。详情可以看下昨天的文章。
<Context docBase="D:\fdream\file"  path="/fdream/file" debug="0" reloadable="true"/>   
<Context docBase="D:\fdream\img"  path="/fdream/img" debug="0" reloadable="true"/> 
*/


/**
 * 文件服务层接口实现
 * @author jpf
 *
 */
@Service  
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class FileServiceImpl implements IFileService {

	@Resource
	private FileMapper fileMapper;
	
	@Override
	public boolean uplodeFile(UpLoadFile lf) throws Exception {
		return fileMapper.uplodeFile(lf);
	}

}
