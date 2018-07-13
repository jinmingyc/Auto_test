package com.tax.action;

import com.tax.dao.BookDao;
import com.tax.dao.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //获得action类型
        String act=request.getParameter("act");
        //如果当前的动作是删除
        if(act.equals("delete"))
        {
            //获得URL中要删除的图书编号
            int id=Integer.parseInt(request.getParameter("id"));
            BookDao dao=new BookDaoImpl();
            if(dao.deleteById(id)>0){  //执行删除并成功
                request.setAttribute("msg","删除成功！");
            }else{
                request.setAttribute("msg","删除失败！");
            }
            //转发到index.jsp页面
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if(act.equals("edit")){

        }else{
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
