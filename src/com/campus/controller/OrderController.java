package com.campus.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campus.dto.OrderDto;
import com.campus.entity.Go;
import com.campus.entity.Good;
import com.campus.entity.Order;
import com.campus.service.IGoodsService;
import com.campus.service.IOrderService;
import com.campus.util.JsonUtil;

import net.sf.json.JSONObject;

@Controller
public class OrderController {

    @Resource
    private IOrderService orderService;
    
    @Resource
    private IGoodsService goodService;
    /**
     * 
     * Title:获取myOrders页面 CreatTime:2016年11月25日 下午2:44:27
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "showMyOrders")
    public ModelAndView showaddgoods() {
        return new ModelAndView("/orders/myOrders");
    }
    
    /**
     * 
     * Title:获取myOrders页面 CreatTime:2016年11月25日 下午2:44:27
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "showMysells")
    public ModelAndView showMysells() {
        return new ModelAndView("/orders/mySells");
    }
    
    /**
     * 
     * Title:获取我的购买订单
     * CreatTime:2016年11月29日 上午11:23:05
     * @param userId
     * @return
     * List<OrderDto>
     */
    @RequestMapping(value = "getMyOrders")
    @ResponseBody
    public List<OrderDto> getMyOrders(Integer userId) {
        List<OrderDto> result = null;
        try {
            result = orderService.getMyOrders(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 
     * Title:获取我的出售订单
     * CreatTime:2016年11月29日 上午11:23:05
     * @param userId
     * @return
     * List<OrderDto>
     */
    @RequestMapping(value = "getSellOrders")
    @ResponseBody
    public List<OrderDto> getSellOrders(Integer userId) {
        List<OrderDto> result = null;
        try {
            result = orderService.getSellOrders(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 
     * Title:买家订单支付
     * CreatTime:2016年11月29日 上午11:23:37
     * @param orderId
     * @param goodId
     * @return
     * JSONObject
     */
    @RequestMapping(value="paygoods")
    @ResponseBody
    public JSONObject paygoods(Integer orderId,Integer goodId)
    {
    	Order order = orderService.get(orderId);
    	order.setOrderStatus(1);
    	orderService.update(order);
    	return JsonUtil.success();
    }
    
    /**
     * 
     * Title:买家取消订单
     * CreatTime:2016年11月29日 下午3:59:30
     * @param orderId
     * @param goodId
     * @return
     * JSONObject
     */
    @RequestMapping(value="canclepay")
    @ResponseBody
    public JSONObject canclepay(Integer orderId,Integer goodId)
    {
    	Assert.notNull(orderId, "order not found");
    	Assert.notNull(goodId, "good not found");
    	Order order = orderService.get(orderId);
    	Good good = goodService.get(goodId);
    	order.setOrderStatus(2);
    	good.setIsOrdered(0);
    	orderService.update(order);
    	goodService.update(good);
    	return JsonUtil.success();
    }
    /**
     * 
     * Title:买家取消交换
     * CreatTime:2016年11月30日 下午3:58:21
     * @param orderId
     * @param goodId
     * @return
     * JSONObject
     */
    
    @RequestMapping(value="buyercancelchange")
    @ResponseBody
    public JSONObject buyercancelchange(Integer orderId,Integer goodId)
    {
    	Order order = orderService.get(orderId);
    	Good sellgood = goodService.get(goodId);
    	Good buygood = goodService.getchangegoods4orderId(orderId, 3);
    	order.setOrderStatus(2);
    	sellgood.setIsOrdered(0);
    	buygood.setIsOrdered(0);
    	orderService.update(order);
    	goodService.update(buygood);
    	goodService.update(sellgood);
    	return JsonUtil.success();
    }
    
    /**
     * 	
     * Title:卖家同意交换
     * CreatTime:2016年11月30日 下午3:10:48
     * @param goodId
     * @param orderId
     * @return
     * JSONObject
     */
    @RequestMapping(value="agreechange")
    @ResponseBody
    public JSONObject agreechange(Integer goodId,Integer orderId)
    {
    	Assert.notNull(goodId, "not found goodId");
    	Assert.notNull(orderId, "not found orderId");
    	Order order = orderService.get(orderId);
    	order.setOrderStatus(1);
    	orderService.update(order);
    	return JsonUtil.success();
    }
    
    /**
     * 
     * Title:卖家不同意交换
     * CreatTime:2016年11月30日 下午3:52:49
     * @param goodId
     * @param orderId
     * @return
     * JSONObject
     */
    @RequestMapping(value="sellerdisagreechange")
    @ResponseBody
    public JSONObject sellerdisagreechange(Integer goodId,Integer orderId)
    {
    	Assert.notNull(goodId, "not found goodId");
    	Assert.notNull(orderId, "not found orderId");
    	Order order = orderService.get(orderId);
    	Good sellgood = goodService.get(goodId);
    	Good changegood = goodService.getchangegoods4orderId(orderId,2);
    	order.setOrderStatus(2);
    	sellgood.setIsOrdered(0);
    	changegood.setIsOrdered(0);
    	goodService.update(changegood);
    	goodService.update(sellgood);
    	orderService.update(order);
    	
    	return JsonUtil.success();
    }
}