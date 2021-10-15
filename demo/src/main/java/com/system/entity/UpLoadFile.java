package com.system.entity;

/**
 * Upload文件实体
 * @author jpf
 */
public class UpLoadFile {

	// 文件id
	private int fileid;
	// 文件名
	private String filename;
	// 文件上传地址
	private String fileurl;
	// 文件上传时间
	private String uplodetime;

	public UpLoadFile() {

	}

	public UpLoadFile(int fileid, String filename, String fileurl, String uplodetime) {
		super();
		this.fileid = fileid;
		this.filename = filename;
		this.fileurl = fileurl;
		this.uplodetime = uplodetime;
	}

	public UpLoadFile(String filename, String fileurl, String uplodetime) {
		this.filename = filename;
		this.fileurl = fileurl;
		this.uplodetime = uplodetime;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public String getUplodetime() {
		return uplodetime;
	}

	public void setUplodetime(String uplodetime) {
		this.uplodetime = uplodetime;
	}
}
