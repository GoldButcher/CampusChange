package com.campus.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedHpsMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campus.dto.UserTableDto;
import com.campus.entity.User;
import com.campus.entity.User;
import com.campus.model.DataTable2User;
import com.campus.service.IUserInfoService;
import com.campus.service.IUserService;
import com.campus.util.CheckUtil;
import com.campus.util.FormUtil;
import com.campus.util.JsonUtil;

import net.sf.json.JSONObject;


@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@PostConstruct
	public void initAdmin() {
		boolean isExist = userService.isExist();
		if(!isExist){
			User user = new User();
			String defPassword = "123456";
			user.setUserName("admin");
			user.setUserPwd(DigestUtils.md5DigestAsHex(defPassword.getBytes()));
			user.setCreateDate(new Date(new Date().getTime()));
			user.setEmail("947538570@qq.com");
			userService.save(user);
		}
	}
	/**
	 * 
	 * Title:主页跳转函数
	 * CreatTime:2016年10月30日 下午3:17:17
	 * @param request
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/")
	public ModelAndView main(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		System.out.println("------------->"+session.getAttribute("user"));
		if(session.getAttribute("user")==null)
		{
			return new ModelAndView("redirect:pages/login.html");
		}else{
			String username = (String) session.getAttribute("user");
			
			if(username.trim().equals("admin"))
				return new ModelAndView("redirect:pages/admin/main.html");
		}
		return new ModelAndView("redirect:pages/main.html");
		
	}
	
	/**
	 * 
	 * Title:登陆验证函数
	 * CreatTime:2016年10月20日 下午7:46:44
	 * @param request
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/dologin")
	@ResponseBody
	public JSONObject dologin(HttpServletRequest request)
	{
		String username = FormUtil.getField(request, "username", "");
		String passowrd = FormUtil.getField(request, "password", "");
		
		String passwordMD5 = DigestUtils.md5DigestAsHex(passowrd.getBytes());
		
		User user = userService.get("userName", username);
		JSONObject result = new JSONObject();
		if(user!=null&&passwordMD5.equals(user.getUserPwd()))//登陆成功
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			session.setMaxInactiveInterval(6000);
			result.put("result", "OK");
			result.put("userId", user.getUserId());
			result.put("user", username);
		}else{
			result.put("result", "帐号名或密码错误");
		}
		return result;
	}
	
	/**
	 * 
	 * Title:验证用户名是否存在
	 * CreatTime:2016年10月20日 下午7:46:14
	 * @param request
	 * @param username
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/isExist")
	@ResponseBody
	public JSONObject isExist4Username(HttpServletRequest request,String username){
		JSONObject object = new JSONObject();
		if(!userService.isExist(username)){//存在
			object.put("result", "success");
		}else{
			object.put("result", "error");
		}
		return object;
	}
	
	/**
	 * 
	 * Title:用户获取验证码
	 * CreatTime:2016年10月20日 下午7:45:20
	 * @param request
	 * @param mobile
	 * @param check
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/getCheck")
	@ResponseBody
	public JSONObject getCheck(HttpServletRequest request,String mobile,String check){
		JSONObject object = new JSONObject();
		System.out.println(mobile);
	    CheckUtil.send(mobile,check);//获取验证码
	    
		HttpSession session = request.getSession(true);
		session.setAttribute("check", check);
		session.setMaxInactiveInterval(60);
		return JsonUtil.success();
	}
	/**
	 * 
	 * Title:用验证码的验证函数
	 * CreatTime:2016年10月21日 下午3:15:17
	 * @param request
	 * @param check
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public JSONObject check(HttpServletRequest request,String check){
		JSONObject object = new JSONObject();
		
		String checkmsg = (String) request.getSession().getAttribute("check");
		if(checkmsg==null){
			object.put("result", "验证码过期或不存在");
			object.put("success", 0);
		}else if(!checkmsg.trim().equals(check.trim())){
			object.put("result", "验证码不正确");
			object.put("success", 0);
		}else{
			object.put("result", "验证成功");
			object.put("success",1);
		}
		
		return object;
	}
	/**
	 * 
	 * Title:添加用户函数
	 * CreatTime:2016年10月21日 下午8:32:12
	 * @param request
	 * @param user
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/addUser")
	@ResponseBody
	public JSONObject adduser(HttpServletRequest request,User user){
		user.setUserPwd(DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes()));
		user.setCreateDate(new Date(new Date().getTime()));
		HttpSession session = request.getSession();
		session.setAttribute("user", user.getUserName());
		userService.save(user);
		return JsonUtil.success();
	}
	
	/**
	 * 
	 * Title:编辑用户函数
	 * CreatTime:2016年11月28日 下午6:53:21
	 * @param request
	 * @param id
	 * @param sex
	 * @param email
	 * @param phone
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/editUser")
	@ResponseBody
	public JSONObject editUser(HttpServletRequest request,Integer id,String sex,String email,String phone){
		User user = userService.get(id);
		user.setUserSex(sex);
		user.setEmail(email);
		user.setPhone(phone);
		userService.update(user);
		return JsonUtil.success();
	}
	
	
	/**
	 * 
	 * Title:用户注销函数 
	 * CreatTime:2016年10月21日 下午8:32:25
	 * @param request
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public JSONObject logout(HttpServletRequest request){
		System.out.println(request.getSession().getAttribute("user"));
		request.getSession().invalidate();
		System.out.println(request.getSession().getAttribute("user"));
		return JsonUtil.success();
	}
	
	/**
	 * 
	 * Title:删除用户函数
	 * CreatTime:2016年11月28日 下午6:53:33
	 * @param request
	 * @param id
	 * @return
	 * JSONObject
	 */
	@RequestMapping(value="/deluser")
	@ResponseBody
	public JSONObject delUser(HttpServletRequest request,int id){
		
		User user = userService.get(id);
		System.out.println(user);
		JSONObject object = new JSONObject();
		if(user==null)
		{
			object.put("result", "no");
		}else{
			if(user.getUserName().equals("admin"))
			{
				object.put("result", "no");
			}else{
			userService.delete(user);
			object.put("result", "ok");
			}
		}
		return object;
	}

	
	/**
	 * @Description: 展示个人资料页面
	 * @author jiang-weirong
	 * @date 2016年11月27日 下午8:23:57 
	 * @return  
	 * ModelAndView 
	*/
	@RequestMapping(value="showuserInfo")
	public ModelAndView showuserInfo()
	{
		return new ModelAndView("/userInfo");
	}

	/**
	 * @Description: 获取用户个人资料
	 * @author jiang-weirong
	 * @date 2016年11月27日 下午8:08:12 
	 * @param userId
	 * @return  
	 * List<User> 
	*/
	@RequestMapping(value="/getUserInfo")
	@ResponseBody
	public User getUserInfo(int userId){
		User sResult = null;
		try {
			sResult = userInfoService.getUserInfo(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}
	
	
	/**
	 * @Description: 更新用户资料
	 * @author ding
	 * @date 2016年11月28日 下午7:20:56 
	 * @param user
	 * @return  
	 * JSONObject 
	*/
	@RequestMapping(value = "/updataUserInfo")
	@ResponseBody
	public JSONObject updataUserInfo(User user)
	{
		try {
			 userInfoService.updataUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.success();
	}
	
	/**
	 * @Description: 修改密码
	 * @author ding
	 * @date 2016年11月29日 上午11:05:43 
	 * @param request
	 * @param id
	 * @param pwd
	 * @return  
	 * JSONObject 
	*/
	@RequestMapping(value="/editPwd")
	@ResponseBody
	public JSONObject editPwd(HttpServletRequest request,Integer id,String pwd){
		User user = userService.get(id);
		user.setUserPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()));
		userService.update(user);
		return JsonUtil.success();
	}
	
	@RequestMapping(value="/updateUserInfo")
	@ResponseBody
	public JSONObject updateUserInfo(HttpServletRequest request,Integer id,String email,String phone){
		User user = userService.get(id);
		user.setEmail(email);
		user.setPhone(phone);
		userService.update(user);
		return JsonUtil.success();
	}
	
	@RequestMapping(value="/judgePwd")
	@ResponseBody
	public  Integer judgePwd(HttpServletRequest request,Integer id,String pwd){
		String passwordMD5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
		User user = userService.get(id);
		System.out.println(passwordMD5);
		System.out.println(user.getUserPwd());
		if(passwordMD5.equals(user.getUserPwd()))//
		return 1;
		else
		return 0;
	}
}
