package com.system.entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;



/**
 * 充值中心
 * @author jpf
 *
 */

@Component
public class Purse {
	
	private Integer id;
	
	private Integer userId;
	
	private Double recharge;//充值
	
	private Integer state;//申请状态
	
	private Timestamp createTime;
	
	private Account account;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Double getRecharge() {
		return recharge;
	}
	
	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}
	
	@Override
	public String toString() {
		return "Purse [id=" + id + ", userId=" + userId + ", recharge=" + recharge + ", state=" + state
				+ ", createTime=" + createTime + ", account=" + account + "]";
	}
}
