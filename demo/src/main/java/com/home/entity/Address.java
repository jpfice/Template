package com.home.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * �ջ���ַʵ��
 * @author Administrator
 *
 */
@Component
public class Address {
	
	private Long id;//id
	
	private Long userId;//�û�id
	
	private String name;//�ջ�������
	
	private String address;//��ϸ��ַ
	
	private String phone;//�ֻ�����
	
	private Date createTime;//���ʱ��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	
	
	
	
}
