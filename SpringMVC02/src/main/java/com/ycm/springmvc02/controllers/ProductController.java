package com.ycm.springmvc02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ycm.springmvc02.entities.Product;
import com.ycm.springmvc02.services.ProductService;
import com.ycm.springmvc02.services.ProductTypeService;

@Controller
@RequestMapping
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTyeService;

	// 展示与搜索action
	@RequestMapping
	public String index(Model model, String searchKey) {
		model.addAttribute("products", productService.getProductsByName(searchKey));
		model.addAttribute("searchKey", searchKey);
		return "product/index";
	}

	// 删除，id为路径变量
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}

	// 多删除，ids的值为多个id参数组成
	@RequestMapping("/deletes")
	public String deletes(@RequestParam("id") int[] ids) {
		productService.deletesProduct(ids);
		return "redirect:/";
	}

	// 新增，渲染出新增界面
	@RequestMapping("/add")
	public String add(Model model) {
		// 与form绑定的模型
		model.addAttribute("product", new Product());
		model.addAttribute("productTypes", productTyeService.getAllProductTypes());
		return "product/add";
	}

	 // 新增保存，如果新增成功转回列表页，如果失败回新增页，保持页面数据
	@RequestMapping("/addSave")
	public String addSave(Model model, Product product) {
		try {
			 //根据类型的编号获得类型对象
			product.setProductType(productTyeService.getProductTypeById(product.getProductType().getId()));
			productService.addProduct(product);
			return "redirect:/";
		} catch (Exception e) {
		      // 与form绑定的模型
			model.addAttribute("product",product);
			// 用于生成下拉列表
			model.addAttribute("productTypes",productTyeService.getAllProductTypes());
			//错误消息
			model.addAttribute("message",e.getMessage());
			e.printStackTrace();
			return "product/add";
		}
	}
	
	// 编辑，渲染出编辑界面，路径变量id是用户要编辑的产品编号
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model,@PathVariable int id) {
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("productTypes",productTyeService.getAllProductTypes());
		return "product/edit";
	}

}



















