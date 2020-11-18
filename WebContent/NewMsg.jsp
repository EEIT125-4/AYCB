<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%><!-- 標準寫法,詳細參考網站https://www.javatpoint.com/jstl-sql-setdatasource-tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="Shortcut Icon" type="image/x-icon"
	href="myProject/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://use.edgefonts.net/arizonia:n4:default.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="myProject/style/basic.css">
<link rel="stylesheet" href="myProject/style/home.css">
<title>NewMsg</title>

</head>

<body>
	<script type="text/javascript" src="myProject/js/init.js"></script>


	<div class="mid">
		<aside id="left">A</aside>
		<main>
			<div style="text-align: center;">

				<form action=".\MsgServlet" method="post">

					<fieldset>
						<legend>發布新訊息</legend>
						<table>
							<tr>
								<td>標題</td>
								<td><input type="text" name="title" placeholder="填入標題名稱"></td>
							</tr>

							<tr>
								<td>分類</td>
								<td><input type="text" name="type" placeholder="填入分類名稱">
								</td>
							</tr>
							<tr>
								<td>訊息內容</td>
								<td><textarea name="desc" cols="100" rows="10"
										placeholder="填入內容">
                            </textarea></td>
							</tr>
							<tr>
								<td colspan="2">
								<input type="submit" name="submit" value="送出"> 
								<input type="reset" value="清除">
								</td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
		</main>
	</div>



	<script type="text/javascript" src="myProject/js/footer.js"></script>

	



</body>

</html>