package com.home.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ����ʵ��
 * @author Administrator
 *
 */
@Component
public class Comment {
	
	private Long id;//����id
	
	private Long productId;//������Ʒid
	
	private Product product;
	
	private Long userId;//�����û�id
	
	private Account account;
	
	private int type;//��������,0:������1��������2������
	
	private String content;//��������
	
	private Date createTime;//����ʱ��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	
	
	
}
