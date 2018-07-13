<%@ page import="com.tax.dao.BookDao" %>
<%@ page import="com.tax.dao.BookDaoImpl" %>
<%@ page import="com.tax.model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BookDao dao = new BookDaoImpl();
    List<Book> books = dao.getAllBooks();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书列表</title>
    <style>
        body{
            font-size:14px;
        }
        #tabBooks {
            width: 80%;
        }

        #tabBooks, #tabBooks td {
            border-collapse: collapse;
        }
        .red{
            color:red;
        }
        .green{
            color:green;
        }
    </style>
</head>
<body>
<h2>图书列表</h2>
<table border="1" id="tabBooks">
    <tr>
        <td>
            <input type="checkbox" id="chbAll"/>
        </td>
        <td>序号</td>
        <td>书名</td>
        <td>价格</td>
        <td>类型</td>
        <td>状态</td>
        <td>操作</td>
    </tr>
    <%for (int i = 0; i < books.size(); i++) {%>
    <tr>
        <td>
            <input type="checkbox" name="id" value=""/>
        </td>
        <td>
            <%=i + 1%>
        </td>
        <td><%=books.get(i).getTitle()%>
        </td>
        <td><%=books.get(i).getPrice()%>
        </td>
        <td><%=books.get(i).getTypename()%>
        </td>
        <td class="<%=books.get(i).getState().equals("未借出")?"green":"red"%>">
            <%=books.get(i).getState()%>
        </td>
        <td>
            <a href="BookServlet?act=delete&id=<%=books.get(i).getId()%>" class="delete" onclick="return isDel()">删除</a>
        </td>
    </tr>
    <%}%>
</table>
<script>
//    var items=document.querySelectorAll(".delete");
//    for(var i=0;i<items.length;i++){
//        items[i].onclick=function () {
//            return confirm("您确定要删除吗？");
//        }
//    }

    function isDel() {
        return confirm('您确定要删除吗？');
    }
</script>
</body>
</html>
