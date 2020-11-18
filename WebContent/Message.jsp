<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %><!-- 標準寫法,詳細參考網站https://www.javatpoint.com/jstl-sql-setdatasource-tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 
<sql:setDataSource var="ds" dataSource="jdbc/AYCBDB" />
<sql:query sql="select top 10 * from Product" var="rs" dataSource="${ds}" /> 
<c:set var="resultSet" value="${rs}.getMetaData"/>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://use.edgefonts.net/arizonia:n4:default.js" type="text/javascript"></script>
    <link rel="stylesheet" href="myProject/style/basic.css">
<title>FavoritesTest</title>
</head>
<body>
<header id="topNav" class="header">
        <!--style="background-image: url(images/header1.jpg);"-->
        <!-- <div class="title">All you can buy</div>-->
        <div class="logo">
            <img src="myProject/images/ProjectLogo.jpg" width="150px" height="150px">
        </div>
        <div class="menubox">
            <div class="title">
                All you can buy
            </div>
            <div class="menu">
                <ul class="list">
                    <li>
                        <div class="submenu">
                            <a href="">
                                最新活動</a>
                            <ul class='hidemenu'>
                                <li>A</li>
                                <li>B</li>
                                <li>C</li>
                                <li>D</li>
                                <li>E</li>

                            </ul>
                        </div>
                    </li>

                    <li>
                        <div class="submenu">
                            <a href="">美妝教學</a>
                            <ul class='hidemenu'>

                                <li>A</li>
                                <li>B</li>
                                <li>C</li>
                                <li>D</li>
                                <li>E</li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div class="submenu">
                            <a href=""> 熱銷商品</a>
                            <ul class='hidemenu'>
                                <li>A</li>
                                <li>B</li>
                                <li>C</li>
                                <li>D</li>
                                <li>E</li>
                            </ul>

                        </div>
                    </li>
                    <li>
                        <div class="submenu">
                            <a href="">特殊服務</a>
                        </div>
                    </li>
                    <li>
                        <div class="submenu">
                            <a href=""> 折扣優惠</a>

                        </div>
                    </li>
                    <!-- <li><select>
                    <option>下拉選單</option>
                    <option value="">A</option>
                    <option value="">B</option>
                    <option value="">C</option>

                    </select></li>-->
                    <div style="height: 100px;">
                        <li style="display: inline;">
                            <input type="text" name="search" id="serach" height="30px" placeholder="輸入關鍵字"></input>
                            <button class="search_bg" id="btn_search" type="submit"
                                style="background-image: url(myProject/images/serach.png);">
                            </button>
                        </li>
                    </div>
                </ul>

            </div>


    </header>

  <div class="content">

        <span class="left" style="background-image: url(myProject/images/background3.jpg);">
            <ul>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>D</li>
                <li>E</li>
            </ul>

        </span>
 
	<span class="main">
	
<c:forEach var="colName" items="${rs.columnNames}">
 <div>"${colName}"</div>
</c:forEach>

<c:forEach var="i" begin="1" end="${rs.rowCount}" step="1">
   <p>Item <c:out value="${i}"/><p>
</c:forEach><b/>

	  <input type="submit" value="加入購物車">
    <input type="submit" value="加入收藏">
  <tabale border="1" >
		
	<th><input style="background-color: red; ">Ref</th>
   <th>Product_id</th>
   <th>Product_name</th>
    <th>Product_series</th>
    <th colspan="2">動作</th>
      <%int index=0; %>
     
    <c:forEach var="row" items="${rs.rows}">
    <%index++;System.out.println(index); %>
 	
    <tr>
    <td> <input type="checkbox" value="1" name="selected"><%=index %></td>
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
  </span>
  </div>
    <footer class="footer">
        <div class="absolute1" style="left: 100px;">
            <img src="myProject/images/ProjectLogo.jpg" width="100px">

        </div>

        <div class="absolute1" style="left: 300px;text-align: center;line-height: 100px;">
            All Rights Reserved Quality Art Technology CO.網站所有資源為資策會報告專案所用
        </div>

    </footer>
</body>
</html>