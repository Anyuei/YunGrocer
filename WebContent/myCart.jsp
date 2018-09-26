<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="path" value="${pageContext.request.contextPath }">
</c:set>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${path }/js/shopcar.js"></script>
<title>我的购物车</title>
</head>
<body>
	<!--导航 头部  -->
	<%@ include file="/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<br>

	<br>
	<br>
	<form action="${path }/admin/CartDeleteAction" method="post">
		<table border="1" bordercolor="blue" cellspacing="0" align="center"
			width="80%">
			<tbody id="tbody">
				<tr bgcolor="lightblue" align="center">
					<td><b>&nbsp;</b></td>
					<td><b>商品编号</b></td>
					<td><b>商品名称</b></td>
					<td><b>图片</b></td>
					<td><b>商品单价</b></td>
					<td><b>数量</b></td>
					<td><b>总价</b></td>
					<td><b>删除</b></td>
				</tr>
				<s:iterator value="#session.cart">
					<tr align="center">
						<td><input type="checkbox" name="productIds"
							value="<s:property value="value.product.id"/>" /></td>
						<td><s:property value="value.product.id"/></td>
						<td><s:property value="value.product.productName"/></td>
						<td><img
							src="${pageContext.request.contextPath}<s:property value="value.product.picpath"/>" /></td>
						<td><s:property value="value.product.price"/></td>
						<td>
							<input type="button" value="-" onclick="sub(this);" />
							<input type="text" value="<s:property value="value.amount"/>" size="1" maxlength="1" name="1" style="color: black;" />
							<input type="button" value="+" onclick="add(this);" />
						</td>
						<td><s:property value="value.totalPrice"/></td>
						<td><input type="button" value="delete"/></td>
					</tr>
				</s:iterator>
				<tr align="center">
					<td colspan="7"><input type="button" value="选中所有行"
						onclick="selectAll();" /> <input type="button" value="取消选中"
						onclick="quxiao();" /> <input type="button" value="删除选中的行"
						onclick="deleteSelected();" /></td>
				</tr>
			</tbody>
		</table>
		<center>
			<p>
				<input type="submit" value="提交修改" />
			</p>
		</center>
	</form>
	<center>
		<hr />
		<a href="">提交订单</a>
	</center>
</body>
</html>