<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>YunGrocer登录</title>

</head>

<body>
	<!--网站头部  -->
	<%@ include file="/header.jsp"%>
	<%-- <jsp:include page='/jsp/header.jsp'></jsp:include> --%>
	<!--登录页面  -->
	<div class="container">
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="row-fluid">

			<div class="span5 offsetHalf block"
				style="padding: 40px 30px 40px 30px; height: auto;">
				<br>
				<div class="row">
					<div class="col-sm-4"></div>
					<div class="col-sm-4">

						<h1>
							<a href="">会员登录</a>
						</h1>
						<hr color="red" />
						<form action="${pageContext.request.contextPath}/Login"
							method="post">
							<div class="form-group">
								<label for="username">用户名</label> <input type="text"
									class="form-control" value='${cookie.username.value}'
									placeholder="请输入用户名" name="username" required="required">

							</div>
							<div class="form-group">
								<label for="password">密码</label> <input type="password"
									class="form-control" value='${cookie.password.value}'
									placeholder="请输入密码" name="password" required="required"> <small
									id="emailHelp" class="form-text text-muted">We'll never
									share your password with anyone else.</small>
							</div>
							<div class="form-group">
								<label for="Captcha">请输入验证码</label> <img
									src="${pageContext.request.contextPath}/Kaptcha.jpg"
									alt="图片加载失败" width="100" height="40" /> <input type="text"
									class="form-control" name="kaptcha" required="required">
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">Check me out</label>

							</div>
							<button type="submit" class="btn btn-primary" value="登陆">登录</button>
							<button type="reset" class="btn btn-primary" value="重置">重置</button>
						</form>
					</div>
					<div class="col-sm-4">
						<div id="logImg"
							style="width: 100%; float：left; margin-bottom: -100px;">
							<img src="image/buybuybuy.png" style="width: 100%; bottom: 0px;" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>