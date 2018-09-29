<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>我的云购</title>
	</head>
	<body>
		<!--网站头部  -->
		<%@ include file="/header.jsp" %>
		<div class="container">
			<br>
			<br>
			<h2>个人信息</h2>
			<h5><a href="">修改个人信息</a></h5>
			<br>
			<br>
			<br>
			<div class="row-fluid">
				<div class="span5 offsetHalf block" style="padding: 40px 30px 40px 30px; height: auto;">
					<div class="row">
						<!-- 个人信息 -->
						<div class="col-md-4">
							<form action="${pageContext.request.contextPath}/ModifyMyGro" method="post">
								<ul>
									<li>
										<label for="username">昵称:</label>
										<input type="text" class="form-control" value='<s:property value="#session.username"/>' placeholder="请输入更改后的昵称" name="username" required="required">
									</li>
									<br>
									<li>

										<label for="name">真实姓名:</label>
										<s:property value="#request.name" /></li><br>
									<li>

										<label for="zip">邮编:</label>
										<input type="text" class="form-control" value='${requestScope.zip}' placeholder="请输入更改后的邮编" name="zip" required="required">
									</li><br>
									<li>
										<label for="address">收货地址:</label>
										<input type="text" class="form-control" value='' placeholder="请输入更改后的收货地址" name="address" required="required">
										<s:property value="#request.address"/>
									</li><br>

									<li>
										<label for="tel">电话:</label>
										<input type="text" class="form-control" placeholder="请输入更改后的电话" name="tel" required="required">
										<s:property value="#request.tel"/>
									</li><br>
									<hr>
									<li>
										<a href="${pageContext.request.contextPath}/ShowMyCart">我的购物车</a>
									</li><br>
									<li>
										<a href="404.jsp">已买到的宝贝</a>
									</li><br>
									<li>
										<a href="404.jsp">购买过的店铺</a>
									</li><br>
									<li>
										<a href="404.jsp">我的收藏</a>
									</li><br>
								</ul>
								<div id="">
									<a href="404.jsp">购物信息
								</div>
							</form>
						</div>
						<div class="col-md-4"></div>
						<!-- 头像 -->
						<div class="col-md-4">
							<div id="avatar" style="height: 70%;">
								<img src="image/defaultAvatar.jpg" style="width: 100%; bottom: 0px;" />
							</div>
							<!--训练文件上传下载-->
							<div style="height: 70%;">
								<h5>修改头像</h5></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>