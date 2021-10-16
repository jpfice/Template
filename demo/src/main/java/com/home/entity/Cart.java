package com.home.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ���ﳵʵ��
 * @author Administrator
 *
 */
@Component
public class Cart {
	
	private Long id;//id
	
	private Long productId;//��Ʒid
	
	private Long userId;//�û�id
	
	private String name;//��Ʒ����
	
	private String imageUrl;//��Ʒ��ͼ
	
	private Double price;//��Ʒ�۸�
	
	private int num;//��Ʒ����
	
	private Double money;//��Ʒ�۸�
	
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	
	
	
	
}
