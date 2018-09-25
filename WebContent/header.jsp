<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>YunGrocer找好货上云货</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/video-js.css" />
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/image/favicon.ico" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tupianlunbo/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap/js/holder.min.js"></script>
	</head>
	<style>
		<c:if test="${sessionScope.usernameLog==null}"> body {
			background-image: url(image/loginOut.jpg);
			no-repeat;
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
			background-attachment: fixed;
			background-position: center bottom;
			color: #fff;
			background-color: #333;
			font-family: 'microsoft yahei', Arial, sans-serif;
		}
		
		</c:if> <c:if test="${sessionScope.usernameLog!=null}">body {
			background-image: url(image/login.jpg);
			no-repeat;
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
			background-attachment: fixed;
			background-position: center bottom;
			color: #fff;
			background-color: #333;
			font-family: 'microsoft yahei', Arial, sans-serif;
		}
		
		</c:if>
		
		.navbar-form input,
		.form-inline input {
			width: auto;
		}
		header {
			height: 480px;
		}
		#nav.affix {
			position: fixed;
			top: 0;
			width: 100%;
			z-index: 10;
		}
		
		#sidebar.affix-top {
			position: static;
		}
		
		#sidebar.affix {
			position: fixed;
			top: 180px;
		}
		/*a标签 */
		
		a:link,
		a:visited {
			color: #eee;
			font-weight: bold;
		}
		
		a:hover {
			color: #BBFFFF;
			font-weight: bold;
		}
		
		.block {
			background-color: rgba(0, 0, 0, 0.2);
			height: 370px;
			padding-left: 12px;
			padding-right: 12px;
		}
		
		.block-sm {
			height: 180px;
		}
		
		.btn-flat {
			border-radius: 0px;
			border-width: 0;
			background-image: none;
			padding: 16px;
			margin: 0 auto;
			margin-top: 15px;
			width: 70%;
			font-size: 20pt;
		}
	</style>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #51b6d3; border-bottom-width: 0px;">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/YunGrocer" style="font-weight: bold; font-size: 40px; color: #A0FFFF;">YunGro</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #FFFFFF;"> 快捷访问 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath}/404.jsp" target="_blank">卖家登录</a>
								</li>
								<li>
									<a href="#" target="_blank">买家登录</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/404.jsp">物流查询</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/ShowMyCart">购物车</a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/FindByPriceRange?currentPage=1" method="post">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search" name='productName' value="${productName}">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="最低价格" name='lowPrice' value='${lowPrice}' style="width:100px;height:23px;">
						</div>
						▬
						<div class="form-group">
							<input type="text" class="form-control" placeholder="最高价格" name='highPrice' value='${highPrice}' style="width:100px;height:23px;">
						</div>
						<button type="submit" class="btn btn-danger">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<c:if test="${sessionScope.usernameLog!=null}">
							<li>
								<a href="${pageContext.request.contextPath}/LogOut" target="_blank">注销</a>
							</li>

						</c:if>
						<c:if test="${sessionScope.usernameLog==null}">
							<li>
								<a href="${pageContext.request.contextPath}/Login.jsp" target="_blank">欢迎登录</a>
							</li>
						</c:if>
						<li>
							<a href="${pageContext.request.contextPath}/Register.jsp" target="_blank">注册</a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</body>

</html>