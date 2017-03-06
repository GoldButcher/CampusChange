package com.campus.controller;

import java.util.Date;
import java.util.List;import javax.xml.crypto.Data;

import org.compass.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campus.entity.Go;
import com.campus.entity.Good;
import com.campus.entity.Order;
import com.campus.entity.User;
import com.campus.service.IGoService;
import com.campus.service.IGoodsService;
import com.campus.service.IOrderService;
import com.campus.util.JsonUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
public class GoodsContoller {
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IGoService goService;
	/**
	 * 
	 * Title:获取addgoods页面
	 * CreatTime:2016年11月25日 下午2:44:27
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="showaddgoods")
	public ModelAndView showaddgoods()
	{
		return new ModelAndView("/goods/addgoods");
	}
	
	/**
	 * 
	 * Title:添加商品
	 * CreatTime:2016年11月29日 下午6:40:04
	 * @param good
	 * @param userId
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="addgoods")
	@ResponseBody
	public JSONObject addgoods(Good good,Integer userId)
	{
		//System.out.println(good);
		good.setCreateDate(new Date(new Date().getTime()));
		good.setModifyDate(good.getCreateDate());
		good.setUserId(userId);
		good.setIsOrdered(0);
		goodsService.save(good);
		return JsonUtil.success();
	}
	
	/**
	 * 
	 * Title:返回全部商品页面
	 * CreatTime:2016年11月27日 上午2:08:38
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="showallgoods")
	public ModelAndView showallgoods()
	{
		return new ModelAndView("/goods/allGoods");
	}
	
	/**
	 * 
	 * Title:显示全部商品
	 * CreatTime:2016年11月27日 上午2:09:05
	 * @returnv 
	 * List<Good>
	 */
	@RequestMapping(value="getAllgoods")
	@ResponseBody
	public List<Good> getAllgoods(Integer userId)
	{
		List<Good> goods = goodsService.getAllgoods(userId);	
		return goods;
	}
	
	@RequestMapping(value="showdetailgoods")
	public ModelAndView showdetailGood()
	{
		return new ModelAndView("/goods/gooddetails");
	}
	
	/**
	 * 
	 * Title:获取商品详情
	 * CreatTime:2016年11月27日 上午2:11:30
	 * @param goodId
	 * @return
	 * Good
	 */
	@RequestMapping(value="getgoods")
	@ResponseBody
	public Good getGood(Integer goodId)
	{
		return goodsService.getdetail(goodId);
	}
	
	/**
	 * 
	 * Title:显示我的商品
	 * CreatTime:2016年11月27日 下午9:01:42
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="showmygoods")
	public ModelAndView showmygoods()
	{
		return new ModelAndView("/goods/mygoods");
	}
	/**
	 * 
	 * Title:获取我的所有商品
	 * CreatTime:2016年11月27日 下午10:03:07
	 * @param userId
	 * @return
	 * List<Good>
	 */
	@RequestMapping(value="getmygoods")
	@ResponseBody
	public List<Good> getmygoods(Integer userId)
	{
		List<Good> goods = goodsService.getmygoods(userId);
		return goods;
	}
	/**
	 * 
	 * Title:删除商品
	 * CreatTime:2016年11月28日 下午12:18:02
	 * @param goodId
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="deletegoods")
	@ResponseBody
	public JSONObject deletegood(Integer goodId)
	{
		//goodsService.delete(goodId);
		return JsonUtil.success();
	}
	
	/**
	 * 
	 * Title:显示修改商品
	 * CreatTime:2016年11月28日 下午12:51:00
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="showeditgoods")
	public ModelAndView showdetailgood()
	{
		return new ModelAndView("/goods/editgood");
	}
	
	/**
	 * 
	 * Title:修改商品
	 * CreatTime:2016年11月28日 下午12:51:16
	 * @param newgood
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="editgoods")
	@ResponseBody
	public JSONObject editgoods(Good newgood)
	{
		Good oldgood = goodsService.get(newgood.getGoodId());
		oldgood.setGoodName(oldgood.getGoodName());
		oldgood.setGoodIntroduction(newgood.getGoodIntroduction());
		oldgood.setPrice(newgood.getPrice());
		oldgood.setModifyDate(new Date(new Date().getTime()));
		return JsonUtil.success();
	}
	/**
	 * 
	 * Title:商品购买函数
	 * CreatTime:2016年11月28日 下午7:10:59
	 * @param goodId
	 * @param userId
	 * @param ischange
	 * @return
	 * JSONObject
	 */
	
	@RequestMapping(value="buygoods")
	@ResponseBody
	public JSONObject buygoods(Integer goodId ,Integer userId)
	{
		Good good = goodsService.get(goodId);
		if(good.getIsOrdered()==1)
		{
			return JsonUtil.error();
		}
		good.setIsOrdered(1);
		goodsService.update(good);
		Order order = new Order();
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setCreateDate(new Date(new Date().getTime()));
		Integer id = orderService.save(order);
		Go sellgo = new Go(goodId, id, 0);
		Go buygo = new Go(goodId,id,1);
		goService.save(sellgo);
		goService.save(buygo);
		return JsonUtil.success();
	}
	
	/**
	 * 
	 * Title:交换商品函数
	 * CreatTime:2016年11月30日 下午3:52:27
	 * @param sellgoodId
	 * @param changegoodId
	 * @param userId
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="changegoods")
	@ResponseBody
	public JSONObject changegoods(Integer sellgoodId,Integer changegoodId,Integer userId)
	{
		System.out.println(sellgoodId);
		Assert.notNull(sellgoodId, "not found sellgoodId");
		Assert.notNull(changegoodId, "not found changegoodId");
		Assert.notNull(userId, "not found userId");
		Good sellgood = goodsService.get(sellgoodId);
		Good changegood = goodsService.get(changegoodId);
		sellgood.setIsOrdered(1);
		changegood.setIsOrdered(1);
		goodsService.update(changegood);
		goodsService.update(sellgood);
		Order order = new Order();
		order.setCreateDate(new Date(new Date().getTime()));
		order.setOrderStatus(0);
		order.setUserId(userId);
		Integer orderId = orderService.save(order);
		Go sellgo = new Go();
		sellgo.setGoodId(sellgoodId);
		sellgo.setIsChange(2);
		sellgo.setOrderId(orderId);
		Go buygo = new Go();
		buygo.setGoodId(changegoodId);
		buygo.setIsChange(3);
		buygo.setOrderId(orderId);
		goService.save(sellgo);
		goService.save(buygo);
		return JsonUtil.success();
	}
}
