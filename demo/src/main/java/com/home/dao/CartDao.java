package com.home.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.home.entity.Cart;

/**
 * CartDao
 * @author jpf
 *
 */
@Repository
public interface CartDao {
	/**
	 * ��ӹ��ﳵ
	 * @param cart
	 * @return
	 */
	public int add(Cart cart);
	
	/**
	 * �༭���ﳵ
	 * @param cart
	 * @return
	 */
	public int edit(Cart cart);
	
	/**
	 * ɾ�����ﳵ
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����û�idɾ��
	 * @param userId
	 * @return
	 */
	public int deleteByUid(Long userId);
	
	/**
	 * �����������ʲ�ѯ���ﳵ
	 * @param queMap
	 * @return
	 */
	public List<Cart> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ���ﳵ
	 * @param id
	 * @return
	 */
	public Cart findById(Long id);
	
	/**
	 * ����ids��ѯ���ﳵ
	 * @param id
	 * @return
	 */
	public Cart findByIds(Map<String, Long> queryMap);
}
