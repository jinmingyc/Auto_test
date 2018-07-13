package com.ycm.springmvc04.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycm.entities.Product;
import com.ycm.springmvc04.entities.Person;

@Controller
@RequestMapping("/bar")
public class BarController2 {
    @RequestMapping("/action11")
    public String action11(Model model){
        //向模型中添加一个名为product的对象，用于渲染视图
        model.addAttribute("product", new Product("Meizu note1", 999));
        return "bar/action11";
    }
    
  //checkbox
    @RequestMapping("/action41")
    public String action21(Model model){
        model.addAttribute("person", new Person());
        return "bar/action41";
    }
    
    @RequestMapping("/action42")
    @ResponseBody
    public Person action22(HttpServletResponse response,Person person){
        return person;
    }
    
    @RequestMapping("/action43")
    public String action31(Model model){
        model.addAttribute("person", new Person());
        return "bar/action43";
    }
    
    @RequestMapping("/action44")
    @ResponseBody
    public Person action32(HttpServletResponse response,Person person){
        return person;
    }
    
}
