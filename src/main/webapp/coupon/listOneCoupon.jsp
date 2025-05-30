<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.coupon.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); 
%>

<html>
<head>
<title>優惠券資料 - listOneCoupon.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>優惠券資料 - listOneCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠券編號</th>
		<th>優惠券代碼</th>
		<th>折扣金額</th>
		<th>最低消費金額</th>
		<th>啟用日期</th>
		<th>停用日期</th>
	</tr>
	<tr>
		<td><%=couponVO.getCouponId()%></td>
		<td><%=couponVO.getCouponCode()%></td>
		<td><%=couponVO.getDiscountValue()%></td>
		<td><%=couponVO.getMinSpend()%></td>
		<td><%=couponVO.getStartDate()%></td>
		<td><%=couponVO.getEndDate()%></td>
	</tr>
</table>

</body>
</html>