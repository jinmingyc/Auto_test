package com.ycm.springmvc02.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ycm.entities.Product;
import com.ycm.entities.User;

/**
 * 定义控制器
 */
// BarController类的实例是一个控制器，会自动添加到Spring上下文中

@Controller
//@RequestMapping("/bar")
public class BarController {
	// 映射访问路径
	@RequestMapping("/bar")
	public String index(Model model) {
		// Spring MVC会自动实例化一个Model对象用于向视图中传值
		model.addAttribute("message", "这是通过注解定义的一个控制器中的Action");
		// 返回视图位置
		return "foo/index";
	}
	
	/**
	 * 如果方法参数名与http中请求的参数名称相同时会进行自动映射
	 * @param model
	 * @param id
	 * @param name
	 * @return
	 */
	
	@RequestMapping("/action0")
	public String action0(Model model,int id,String name) {
		model.addAttribute("message","name="+name+",id="+id);
		return "foo/index";
	}

	/**
	 * 自定义数据类型
	 * @param model
	 * @param product
	 * @return
	 */
	@RequestMapping("/action1")
	public String action1(Model model,Product product) {
		model.addAttribute("message",product);
		return "foo/index";
	}
	
	/**
	 * 复杂数据类型
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/action2")
	public String action2(Model model,User user) {
		model.addAttribute("message", user.getUsername() + "," + user.getProduct().getName());
        return "foo/index";
	}
	
	/**
	 * @RequestParam参数绑定
	 * 基本数据类型绑定与注解属性
	 */
	@RequestMapping("/action3")
	public String action3(Model model,@RequestParam(required=true,defaultValue="99") int id) {
		model.addAttribute("message",id);
		return "foo/index";
	}
	
	@RequestMapping("/action4")
	public String action4(Model model,@RequestParam("id") List<Integer> ids) {
		model.addAttribute("message",Arrays.deepToString(ids.toArray()));
		return "foo/index";
	}
	
	@RequestMapping("/action5")
	public String action4(Model model) {
		//model.addAttribute("message",Arrays.deepToString(ids.toArray()));
		return "myform2";
	}
	
	// List与数组直接绑定自定义数据类型与AJAX
    @RequestMapping("/action21")
    public void action21(@RequestBody List<Product> products, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        System.out.println(Arrays.deepToString(products.toArray()));
        response.getWriter().write("添加成功");
    }
}
