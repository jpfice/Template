package com.home.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.entity.Account;
import com.home.entity.Favorite;
import com.home.entity.Product;
import com.home.service.FavoriteService;
import com.home.service.ProductCategoryService;
import com.home.service.ProductService;
import com.util.MenuUtil;

/**
 * ǰ̨�ղؿ�����
 * @author jpf
 *
 */
@RequestMapping("/favorite")
@Controller
public class FavoriteController {

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FavoriteService favoriteService;
	/**
	 * �ղ��б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model,Integer page,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(page == null || page.intValue() <= 0){
			page = 1;
		}
		queryMap.put("offset", (page -1) * 16);
		queryMap.put("pageSize", 16);
		queryMap.put("userId", onlineAccount.getId());
		queryMap.put("orderBy", "createTime");
		queryMap.put("sort", "desc");
		model.addObject("favoriteList", favoriteService.findList(queryMap));
		model.addObject("currentUser", "current_");
		model.addObject("page", page);
		model.setViewName("home/favorite/list");
		return model;
	}
	
	
	/**
	 * ����ղ�
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Favorite favorite,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(favorite == null){
			ret.put("msg", "��ѡ����ȷ����Ʒ��Ϣ");
			return ret;
		}
		if(favorite.getProductId() == null){
			ret.put("msg", "��ѡ��Ҫ��ӵ���Ʒ��");
			return ret;
		}
		Product product = productService.findById(favorite.getProductId());
		if(product == null){
			ret.put("msg", "��Ʒ������");
			return ret;
		}
		//������Ʒ���û�ȥ��ѯ����Ʒ�Ƿ��ѱ���ӵ��ղ�
		Map<String, Long> queryMap = new HashMap<String, Long>();
		queryMap.put("userId", onlineAccount.getId());
		queryMap.put("productId", product.getId());
		Favorite existFavorite = favoriteService.findByIds(queryMap);
		if(existFavorite != null){
			//��ʾ�����Ʒ�Ѿ�����ӵ��ղ�
			ret.put("msg", "��Ʒ�ѱ���ӵ��ղ�!");
			return ret;
		}
		favorite.setImageUrl(product.getImageUrl());
		favorite.setName(product.getName());
		favorite.setPrice(product.getPrice());
		favorite.setUserId(onlineAccount.getId());
		favorite.setCreateTime(new Date());
		if(favoriteService.add(favorite) <= 0){
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	
	/**
	 * ɾ���ղ���Ʒ
	 * @param favoriteId
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long favoriteId){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "error");
		if(favoriteId == null){
			ret.put("msg", "��ѡ��Ҫɾ������Ʒ");
			return ret;
		}
		if(favoriteService.delete(favoriteId) <= 0){
			ret.put("msg", "ɾ����������ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
}
