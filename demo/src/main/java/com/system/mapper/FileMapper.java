package com.system.mapper;

import com.system.entity.UpLoadFile;

public interface  FileMapper {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public boolean uplodeFile(UpLoadFile lf) throws Exception;
}
