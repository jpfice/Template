package com.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.home.entity.Order;
import com.home.entity.OrderItem;

/**
 * OrderDao
 * @author jpf
 *
 */
@Repository
public interface OrderDao {
	/**
	 * ��Ӷ���
	 * @param order
	 * @return
	 */
	public int add(Order order);
	
	/**
	 * ��Ӷ�������
	 * @param orderItem
	 * @return
	 */
	public int addItem(OrderItem orderItem);
	
	/**
	 * �༭����
	 * @param order
	 * @return
	 */
	public int edit(Order order);
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����������ʲ�ѯ����
	 * @param queMap
	 * @return
	 */
	public List<Order> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Order findById(Long id);
	
	/**
	 * ���ݶ����Ų�ѯ�����Ķ�������
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> findOrderItemList(Long orderId);
	
	/**
	 * ��ȡͳ����Ϣ
	 * @param queryMap
	 * @return
	 */
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);
}
