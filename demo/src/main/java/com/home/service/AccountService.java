package com.home.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.home.entity.Account;

/**
 * �ͻ��ӿ�
 * @author Administrator
 *
 */
@Service
public interface AccountService {
	
	/**
	 * ��ӿͻ�
	 * @param account
	 * @return
	 */
	public int add(Account account);
	
	/**
	 * �༭�ͻ�
	 * @param account
	 * @return
	 */
	public int edit(Account account);
	
	/**
	 * ɾ���ͻ�
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����������ʲ�ѯ�ͻ�
	 * @param queMap
	 * @return
	 */
	public List<Account> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ�ͻ�
	 * @param id
	 * @return
	 */
	public Account findById(Long id);
	
	/**
	 * �����û������ҿͻ�
	 * @param name
	 * @return
	 */
	public Account findByName(String name);
	
	/**
	 * �����û����
	 * @param queryMap
	 * @return
	 */
	public int editBalance(Account account);

}
