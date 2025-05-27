<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<%-- <%  --%>
//    CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
<%-- %> --%>
<% 
   CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
   if (couponVO == null) {
%>
   <h3 style="color:red;">錯誤：沒有取得要修改的優惠券資料！</h3>
   <a href="select_page.jsp">回首頁</a>
<% 
     return;
   }
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>優惠券資料修改 - update_coupon_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>優惠券資料修改 - update_coupon_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="coupon.do" name="form1">
<table>
	<tr>
		<td>優惠券編號:<font color=red><b>*</b></font></td>
		<td><%=couponVO.getCouponId()%></td>
	</tr>
	<tr>
		<td>優惠券代碼:</td>
		<td><input type="TEXT" name="couponCode" value="<%=couponVO.getCouponCode()%>" size="45"/></td>
	</tr>
	<tr>
		<td>折扣金額:</td>
		<td><input type="TEXT" name="discountValue"   value="<%=couponVO.getDiscountValue()%>" size="45"/></td>
	</tr>
	<tr>
		<td>最低消費金額:</td>
		<td><input type="TEXT" name="minSpend"   value="<%=couponVO.getMinSpend()%>" size="45"/></td>
	</tr>
	<tr>
		<td>啟用日期:</td>
		<td><input name="startDate" id="startDate" type="text" ></td> 
	</tr>
	<tr>
		<td>停用日期:</td>
		<td><input name="endDate" id="endDate" type="text" ></td> 
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="couponId" value="<%=couponVO.getCouponId()%>">
<input type="submit" value="送出修改"></FORM>
</body>




</html>