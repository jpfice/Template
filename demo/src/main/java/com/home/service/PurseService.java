package com.home.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.home.entity.Purse;

/**
 * �û���ֵ
 * @author Administrator
 *
 */
@Service
public interface PurseService {

	/**
	 * ��ֵ�ύ
	 * @param address
	 * @return
	 */
	public int add(Purse purse);
	
	/**
	 * ����id��ѯ��ֵ�û�
	 * @param id
	 * @return
	 */
	public List<Purse> findById(Long id);
	/**
	 * ��ѯ��ֵ��¼
	 * @param userId
	 * @return
	 */
	public List<Purse> findRecord(Map<String,Object> queryMap);
	
	/**
	 * ����Ҫ���ѯ��ֵ�б�
	 * @param map
	 * @return
	 */
	public List<Purse> findList(Map<String,Object> queryMap);
	
	/**
	 * ������
	 * @param queryMap
	 * @return
	 */
	public int getTotal(Map<String,Object> queryMap);

	
	/**
	 * ��̨��˳ɹ�
	 */
	public int examine(Purse purse);
	
	/**
	 * ����ids��ѯ��ֵ�б�
	 * @param queryMap
	 * @return
	 */
	public Purse findListByid(Integer id);

	/**
	 * ȷ�϶����� �����û����
	 * @param queryMap
	 * @return
	 */
	public int editBalance(Map<String,Object> paramMap);
	
}
