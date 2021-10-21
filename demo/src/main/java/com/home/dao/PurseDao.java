package com.home.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.home.entity.Purse;

/**
 * 用户充值dao层
 * @author jpf
 *
 */
@Repository
public interface PurseDao {
	/**
	 * 充值提交
	 * @param address
	 * @return
	 */
	public int add(Purse purse);
	
	/**
	 * 根据id查询充值用户
	 * @param id
	 * @return
	 */
	public List<Purse> findById(Long id);
	/**
	 * 查询充值记录
	 * @param userId
	 * @return
	 */
	public List<Purse> findRecord(Map<String,Object> queryMap);
	
	/**
	 * 根据要求查询充值列表
	 * @param map
	 * @return
	 */
	public List<Purse> findList(Map<String,Object> queryMap);
	
	/**
	 * 总条数
	 * @param queryMap
	 * @return
	 */
	public int getTotal(Map<String,Object> queryMap);
	
	/**
	 * 后台审核结果
	 */
	public int examine(Purse purse);
	

	/**
	 * 根据ids查询充值列表
	 * @param queryMap
	 * @return
	 */
	public Purse findListByid(Integer id);

	
	/**
	 * 确认订单后 更新用户余额
	 * @param queryMap
	 * @return
	 */
	public int editBalance(Map<String,Object> paramMap);
	
}
