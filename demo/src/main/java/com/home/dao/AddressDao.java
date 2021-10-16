package com.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.home.entity.Address;

/**
 * AddressDao
 * @author jpf
 *
 */
@Repository
public interface AddressDao {
	/**
	 * ����ջ���ַ
	 * @param address
	 * @return
	 */
	public int add(Address address);
	
	/**
	 * �༭�ջ���ַ
	 * @param address
	 * @return
	 */
	public int edit(Address address);
	
	/**
	 * ɾ���ջ���ַ
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����������ʲ�ѯ�ջ���ַ
	 * @param queMap
	 * @return
	 */
	public List<Address> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ�ջ���ַ
	 * @param id
	 * @return
	 */
	public Address findById(Long id);
}
