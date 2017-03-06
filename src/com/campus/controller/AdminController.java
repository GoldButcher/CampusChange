package com.campus.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campus.dto.OrderTableDto;
import com.campus.dto.UserTableDto;
import com.campus.entity.User;
import com.campus.model.DataTable2Order;
import com.campus.model.DataTable2User;
import com.campus.service.IGoodsService;
import com.campus.service.IOrderService;
import com.campus.service.IUserService;
import com.campus.util.DateUtil;

import net.sf.json.JSONArray;

@Controller
public class AdminController{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	
	
	@Autowired
	private IGoodsService goodsService;
	
	/**
	 * 
	 * Title:获取当前用户商品以及订单数量
	 * CreatTime:2016年11月24日 下午8:11:45
	 * @param request
	 * @return
	 * JSONArray
	 */
	@RequestMapping(value="getlist")
	@ResponseBody
	public JSONArray getList(HttpServletRequest request){
		JSONArray array = new JSONArray();
		long userCount = userService.getTotalCount();
		long orderCount = orderService.getTotalCount();
		long goodsCount = goodsService.getTotalCount();
		
		
		array.add(0, userCount);
		array.add(1, orderCount);
		array.add(2, goodsCount);		
		return array;
	}
	/**
	 * 
	 * Title:图表用函数
	 * CreatTime:2016年11月24日 下午8:11:29
	 * @param request
	 * @return
	 * JSONArray
	 */
	@RequestMapping(value = "getchart")
	@ResponseBody
	public JSONArray getChart(HttpServletRequest request){
		JSONArray array = new JSONArray();
		long manCount = userService.get4Sex("男");
		long womanCount = userService.get4Sex("女");
		long userCount = userService.getTotalCount();
		long orderCount = orderService.getTotalCount();
		long undoneOrderCount = orderService.getorder(0);
		long doneOrderCount = orderCount - undoneOrderCount;
		array.add(0,(int)manCount*100/userCount);
		array.add(1,(int)womanCount*100/userCount);
		array.add(2,(int)undoneOrderCount*100/orderCount);
		array.add(3,(int)doneOrderCount*100/orderCount);
		
		return array;
	}
	/**
	 * 
	 * Title:返回具体的用户
	 * CreatTime:2016年11月24日 下午8:12:21
	 * @param request
	 * @param id
	 * @return
	 * User
	 */
	@RequestMapping(value="/getUser4id")
	@ResponseBody
	public User getUser4id(HttpServletRequest request,int id)
	{
		User user = userService.get(id);
		if(user.getCreateDate()!=null)
		{
			user.setDateStr(DateUtil.date2String(user.getCreateDate()));
		}
		return user;
	}
	
	/**
	 * 
	 * Title:返回用户表所在的视图
	 * CreatTime:2016年10月31日 下午9:43:15
	 * @param request
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/usertable")
	public ModelAndView getUserTable(HttpServletRequest request){
		return new ModelAndView("/admin/User");
	}
	
	/**
	 * 
	 * Title:适用datatable,返回封装的数据
	 * CreatTime:2016年10月31日 下午9:42:51
	 * @param request
	 * @param response
	 * @param dataTables
	 * @throws IOException
	 * void
	 */
	@RequestMapping(value="/userlist")
	@ResponseBody
	public UserTableDto liststudent(HttpServletRequest request, HttpServletResponse response,DataTable2User dataTables) throws IOException{
		return userService.getUser4DT(dataTables);
	}
	
	/**
	 * 
	 * Title:返回订单表所在的视图
	 * CreatTime:2016年10月31日 下午9:43:15
	 * @param request
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/ordertable")
	public ModelAndView getOrdertable(HttpServletRequest request){
		return new ModelAndView("/admin/Order");
	}
	
	/**
	 * 
	 * Title:适用datatable,返回封装的数据
	 * CreatTime:2016年10月31日 下午9:42:51
	 * @param request
	 * @param response
	 * @param dataTables
	 * @throws IOException
	 * void
	 */
	@RequestMapping(value="/orderlist")
	@ResponseBody
	public OrderTableDto liststudent(HttpServletRequest request, HttpServletResponse response,DataTable2Order dataTables) throws IOException{
		return orderService.getOrder4DT(dataTables);
	}
}
