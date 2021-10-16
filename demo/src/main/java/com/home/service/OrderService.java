package com.home.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.home.entity.Order;
import com.home.entity.OrderItem;

/**
 * �����ӿ�
 * @author Administrator
 *
 */
@Service
public interface OrderService {
	
	/**
	 * ��Ӷ���
	 * @param order
	 * @return
	 */
	public int add(Order order);
	
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
