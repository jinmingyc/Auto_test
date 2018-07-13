package com.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao2.BookDao;
import com.dao2.BookDaoImpl;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BookServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获取action类型
		String act= request.getParameter("act");
		
		if (act.equals("delete")) {
			//获取要删除的图书ID
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("************************"+id);
			BookDao dao = new BookDaoImpl();
			if(dao.deleteById(id)>0) {
				request.setAttribute("msg", "删除成功");
			}else {
				request.setAttribute("msg", "删除失败");
			}
			//转发到index页面
			request.getRequestDispatcher("index.jsp").forward(request,response);
			
		}else if(act.equals("edit")) {
			
		}else {
			
		}
		
		
	}

}
