package com.home.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * ����ʵ��
 * @author Administrator
 *
 */
@Component
public class Order {
	
	public static int ORDER_STATUS_WAITING = 0;//������
	
	public static int ORDER_STATUS_SENT = 1;//�ѷ���
	
	public static int ORDER_STATUS_FINISH = 2;//�����
	
	
	private Long id;//��������id
	
	private String sn;//�������
	
	private Long userId;//�����û�id
	
	private String address;//�ջ���ַ
	
	private Double money;//�����ܼ�
	
	private int productNum;//������Ʒ��
	
	private int status;//����״̬
	
	private String remark;//������ע
	
	private Date createTime;//���ʱ��
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	
	
	
}
