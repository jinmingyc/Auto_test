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
        //���ñ���
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //���action����
        String act=request.getParameter("act");
        //�����ǰ�Ķ�����ɾ��
        if(act.equals("delete"))
        {
            //���URL��Ҫɾ����ͼ����
            int id=Integer.parseInt(request.getParameter("id"));
            BookDao dao=new BookDaoImpl();
            if(dao.deleteById(id)>0){  //ִ��ɾ�����ɹ�
                request.setAttribute("msg","ɾ���ɹ���");
            }else{
                request.setAttribute("msg","ɾ��ʧ�ܣ�");
            }
            //ת����index.jspҳ��
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
