package com.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.home.entity.Comment;


/**
 * CommentDao
 * @author jpf
 *
 */
@Repository
public interface CommentDao {
	/**
	 * �������
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * �༭����
	 * @param comment
	 * @return
	 */
	public int edit(Comment comment);
	
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
	public List<Comment> findList(Map<String, Object> queryMap);
	
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
	public Comment findById(Long id);
}
