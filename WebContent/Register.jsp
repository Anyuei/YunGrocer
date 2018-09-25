<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎注册</title>
</head>
<body>
	<!--导航 头部  -->
	<%@ include file="/header.jsp"%>
	<!--注册页面  -->
	<div class="container">
		<br> <br> <br> <br> <br>
		<div class="row-fluid">

			<div class="span5 offsetHalf block"
				style="padding: 40px 30px 40px 30px; height: auto;">
				<br>
				<div class="row">
					<div class="col-sm-4"></div>
					<div class="col-sm-4">

						<h1>
							<a href="">会员注册</a>
						</h1>
						<hr color="red" />
						<form action="${pageContext.request.contextPath}/Register"
							method="post">
							<!--username  -->
							<div class="form-group">
								<label for="username">用户名</label> 
								<input type="text"
									class="form-control" 
									value='${cookie.username.value}'
									placeholder="请输入昵称" 
									name="username"
									required="required">
									<c:if test="${sessionScope.registerError!=null}">
										<small class="form-text text-muted">
										${sessionScope.registerError}
										</small>
									</c:if>
							</div>
							<!--password  -->
							<div class="form-group">
								<label for="password">密码</label> 
								<input type="password"
									class="form-control" 
									value='${cookie.password.value}'
									placeholder="请输入密码" 
									name="password"
									required="required"> 
									<small class="form-text text-muted">
									We'll never share your password with anyone else.
									</small>
							</div>
							<!--realname  -->
							<div class="form-group">
								<label for="realname">真实姓名</label> 
								<input type="text"
									class="form-control" 
									value='${cookie.realname.value}'
									placeholder="请输入真实姓名" 
									name="realname"
									required="required"> 
									<small class="form-text text-muted">
									We'll never share your realname with anyone else.
									</small>
							</div>
							<!--zipcode  -->
							<div class="form-group">
								<label for="zipcode">邮编（6位数字）</label> 
								<input type="text"
									class="form-control" value='${cookie.zipcode.value}'
									placeholder="请输入邮政编码（6位数字）" name="zipcode" pattern="[0-9]{6}"
									required="required">
							</div>
							<!--address  -->
							<div class="form-group">
								<label for="address">地址</label> 
								<input type="text"
									class="form-control" value='${cookie.address.value}'
									placeholder="请输入收货地址" name="address" 
									required="required">
							</div>
							<button type="submit" class="btn btn-primary" value="登陆">注册</button>
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