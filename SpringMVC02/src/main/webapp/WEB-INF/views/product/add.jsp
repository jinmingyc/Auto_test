<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/main.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>新增产品</span>
		</h2>
		<form:form action="addSave" modelAttribute="product">
			<fieldset>
				<legend>产品</legend>
				<p>
					<label for="name">产品名称：</label>
					<form:input path="name" />
				</p>
				<p>
					<label for="title">产品类型：</label>
					<form:select path="productType.id" items="${productTypes}"
						itemLabel="name" itemValue="id"></form:select>
				</p>
				<p>
					<label for="price">产品价格</label>
					<form:input path="price" />
				</p>
				<p>
					<input type="submit" value="保存" class="btn out">
				</p>
			</fieldset>
		</form:form>
		<p style="color: red">${message}</p>
		<p>
			<a href="<c:url value="/" />" class="abtn out">返回列表</a>
		</p>
	</div>

</body>
</html>