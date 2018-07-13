package com.ycm.springmvc02.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FooController implements Controller {

	/**
	 * 实现接口Controller定义控制器是较老的办法，缺点是：一个控制器中只有一个Action，如果要多个Action则需要定义多个Controller；定义的方式比较麻烦；Spring
	 * 2.5以后采用注解的方式定义解决这引起问题。
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return new ModelAndView("foo/index", "message", "Hello，我是通过实现接口定义的一个控制器");
	}

}
