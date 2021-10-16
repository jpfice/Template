package com.home.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.home.entity.Favorite;

/**
 * �ղؽӿ�
 * @author Administrator
 *
 */
@Service
public interface FavoriteService {
	
	/**
	 * ����ղ�
	 * @param favorite
	 * @return
	 */
	public int add(Favorite favorite);
	
	
	/**
	 * ɾ���ղ�
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����������ʲ�ѯ�ղ�
	 * @param queMap
	 * @return
	 */
	public List<Favorite> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ�ղ�
	 * @param id
	 * @return
	 */
	public Favorite findById(Long id);
	
	/**
	 * ����ids��ѯ�ղ�
	 * @param id
	 * @return
	 */
	public Favorite findByIds(Map<String, Long> queryMap);
	
}
