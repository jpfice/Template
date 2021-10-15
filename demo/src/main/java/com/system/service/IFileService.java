package com.system.service;

import com.system.entity.UpLoadFile;

/**
 * 文件服务层接口
 * @author jpf
 *
 */
public interface IFileService {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public boolean uplodeFile(UpLoadFile lf) throws Exception;
}
