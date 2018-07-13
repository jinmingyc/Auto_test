<%@page import="com.domain.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.dao2.BookDao"%>
<%@page import="com.dao2.BookDaoImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	BookDao dao = new BookDaoImpl();
	List<Book> books = dao.getAllBooks();
%>

<html>
<head>
<meta charset="utf-8">
<title>图书列表</title>


</head>

<body>
	<h2>图书列表</h2>

	<table border="1" id="tabBooks">
		<tr>
			<td><input type="checkbox" id="chbAll" /></td>
			<td>序号</td>
			<td>书名</td>
			<td>价格</td>
			<td>类型</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<%
			for (int i = 0; i < books.size(); i++) {
		%>
		<tr>
			<td><input type="checkbox" name="id" value=""></td>
			<td><%=i + 1%></td>
			<td><%=books.get(i).getTitle()%></td>
			<td><%=books.get(i).getPrice()%></td>
			<td><%=books.get(i).getTypeName()%></td>
			<td><%=books.get(i).getState()%></td>
			<td><%=books.get(i).getTitle()%></td>
			<td><a
				href="BookServlet?act=delete&id=<%=books.get(i).getId()%>"
				class="delete" onclick="return isDel()">删除</a></td>
		</tr>
		<%}%>

	</table>

	<script>
		function idDel() {
			return confirm('queding shanchu?')
		}
	</script>
</body>
</html>
