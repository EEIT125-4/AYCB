<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%><!-- 標準寫法,詳細參考網站https://www.javatpoint.com/jstl-sql-setdatasource-tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<sql:setDataSource var="ds" dataSource="jdbc/AYCBDB" />
<sql:query sql="select top 20 * from Product" var="rs"
	dataSource="${ds}" />
<c:set var="resultSet" value="${rs}.getMetaData" />





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Shortcut Icon" type="image/x-icon" href="myProject/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://use.edgefonts.net/arizonia:n4:default.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="myProject/style/basic.css">
<link rel="stylesheet" href="myProject/style/home.css">
<title>FavoritesTest</title>
</head>
<body>

	<script type="text/javascript" src="myProject/js/init.js"></script>

	<div class="mid">


		<aside id='left'>
		<ul>
			<li>A</li>
			<li>B</li>
			<li>C</li>
			<li>D</li>
			<li>E</li>
		</ul>
		</aside>
		<main> <c:forEach var="colName" items="${rs.columnNames}">
			<div>"${colName}"</div>
		</c:forEach> 
		<!--<c:forEach var="i" begin="1" end="${rs.rowCount}" step="1">
			<p>
				Item
				<c:out value="${i}" />
			<p>
		</c:forEach>-->
		<b />
		<input type="submit" value="加入購物車"> 
		<input type="submit" value="加入收藏">
		<hr> 
		
		<table class='tb'> 
		<thead>
		<tr>	
		<th>Ref</th>
		<th>Product_id</th>
		<th>Product_name</th>
		<th>Product_series</th>
		<th colspan="2">動作</th>
		</tr>
		</thead>
		<%
			int index = 0;
		%> <c:forEach var="row" items="${rs.rows}">
			<%
				index++;
			%>

			<tr>
				<td><input type="checkbox" value="1" name="selected"><%=index%></td>
				<td>${row.product_no}</td>
				<td style="">${row.Product_Name}</td>
				<td>${row.Product_Series}</td>
				<form action=".\TestServlet" method="post">
					<td><input type="submit" name="submit" value="加入購物車"></td>
				</form>
				<td><input type="submit" value="加入收藏"></td>
			</tr>

		</c:forEach>
		</table>
		
	

	</main>
	</div>


	<footer class="footer">
	<div class="absolute1" style="left: 100px;">
		<img src="myProject/images/ProjectLogo.jpg" width="100px">

	</div>

	<div class="absolute1"
		style="left: 300px; text-align: center; line-height: 100px;">
		All Rights Reserved Quality Art Technology CO.網站所有資源為資策會報告專案所用</div>

	</footer>
</body>
</html>