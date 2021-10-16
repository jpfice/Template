package com.system.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

/**
 * hibernate-validatorの使う方
@AssertFalse 验证注解的元素值是 false
@AssertTrue 验证注解的元素值是 true
@DecimalMax（value=x） 验证注解的元素值小于等于指定的十进制value 值
@DecimalMin（value=x） 验证注解的元素值大于等于指定的十进制value 值
@Digits(integer=整数位数, fraction=小数位数)验证注解的元素值的整数位数和小数位数上限
@Future 验证注解的元素值（日期类型）比当前时间晚
@Max（value=x） 验证注解的元素值小于等于指定的 value值
@Min（value=x） 验证注解的元素值大于等于指定的 value值
@NotNull 验证注解的元素值不是 null
@Null 验证注解的元素值是 null
@Past 验证注解的元素值（日期类型）比当前时间早
@Pattern(regex=正则表达式) 验证注解的元素值不指定的正则表达式匹配
@Size(min=最小值, max=最大值) 验证注解的元素值的在 min 和 max （包含）指定区间之内，如字符长度、集合大小
@Valid 该注解主要用于字段为一个包含其他对象的集合或map或数组的字段，或该字段直接为一个其他对象的引用，这样在检查当前对象的同时也会检查该字段所引用的对象。
@NotEmpty 验证注解的元素值不为 null 且不为空（字符串长度不为 0、集合大小不为 0）
@Range(min=最小值, max=最大值)验证注解的元素值在最小值和最大值之间
@NotBlank 验证注解的元素值不为空（不为 null、去除首位空格后长度为 0），不同于@NotEmpty， 
@NotBlank 只应用于字符串且在比较时会去除字符串的空格
@Length(min=下限, max=上限) 验证注解的元素值长度在 min 和 max 区间内
@Email 验证注解的元素值是 Email，也可以通过正则表达式和 flag 指定自定义的 email 格式
 */

/**
 * 客户资源表(公司)实体
 * 
 * @author jpf
 *
 */
@Component
public class Company {

	//UUID
	private String uuid = "";
	
	//公司名称
	@NotNull(message= "公司名称")
	private String comname = "";
	
	//公司地址
	@Range(min=1, max=5)
	private String comaddress = "";
	
	//公司网址
	private String comurl = "";
	
	//公司座机
	private String comtelephone = "";
	
	//公司成立日期
	private String establishdate = "";
	
	//员工人数
	private int employeenumber = 0;
	
	//产值
	@Digits(integer=15, fraction=2)
	private float totaloutput = 0f;
	
	//公司简介
	private String comdesc = "";
	
	//运营状态
	private String comstatus = "";
	
	//联系人名称
	private String contactname = "";
	
	//联系人手机号
	private String contactmobile = "";
	
	//联系人邮箱
	@Email
	private String contactemail = "";

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getComaddress() {
		return comaddress;
	}

	public void setComaddress(String comaddress) {
		this.comaddress = comaddress;
	}

	public String getComurl() {
		return comurl;
	}

	public void setComurl(String comurl) {
		this.comurl = comurl;
	}

	public String getComtelephone() {
		return comtelephone;
	}

	public void setComtelephone(String comtelephone) {
		this.comtelephone = comtelephone;
	}

	public String getEstablishdate() {
		return establishdate;
	}

	public void setEstablishdate(String establishdate) {
		this.establishdate = establishdate;
	}

	public int getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(int employeenumber) {
		this.employeenumber = employeenumber;
	}

	public float getTotaloutput() {
		return totaloutput;
	}

	public void setTotaloutput(float totaloutput) {
		this.totaloutput = totaloutput;
	}

	public String getComdesc() {
		return comdesc;
	}

	public void setComdesc(String comdesc) {
		this.comdesc = comdesc;
	}

	public String getComstatus() {
		return comstatus;
	}

	public void setComstatus(String comstatus) {
		this.comstatus = comstatus;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactmobile() {
		return contactmobile;
	}

	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}

	@Override
	public String toString() {
		return "Company [uuid=" + uuid + ", comname=" + comname + ", comaddress=" + comaddress + ", comurl=" + comurl
				+ ", comtelephone=" + comtelephone + ", establishdate=" + establishdate + ", employeenumber="
				+ employeenumber + ", totaloutput=" + totaloutput + ", comdesc=" + comdesc + ", comstatus=" + comstatus
				+ ", contactname=" + contactname + ", contactmobile=" + contactmobile + ", contactemail=" + contactemail
				+ "]";
	}

}

