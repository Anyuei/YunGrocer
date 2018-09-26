<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>YunGrocer登录</title>
	</head>

	<body>
		<!--网站头部  -->
		<%-- <jsp:include page='/jsp/header.jsp'></jsp:include> --%>
		<%@ include file="/header.jsp" %>
		<!--主体部分  -->
		<c:set var="searchMessage" value="&productName=${productName}&lowPrice=${lowPrice}&highPrice=${highPrice}" scope="session"></c:set>

		<div class="container">
			<br>
			<br>
			<h2>云购商城欢迎您${sessionScope.usernameLog}！！</h2>
			<br>
			<br>
			<br>
			<div class="row-fluid">
				<div class="span5 offsetHalf block" style="padding: 40px 30px 40px 30px; height: auto;">
					<div align="center">
						<h1>图书类</h1></div>
					<div class="row">
					<s:iterator value="#request.products">
						
							<div class="col-md-4">
								<div align="center"><img src="${pageContext.request.contextPath}<s:property value="picpath"/>" style="width: 50%;height: 225px;"/></div>
								<div class="row" align="center">
									<div><s:property value="id"/>
									</div>
									<div align="center"><s:property value="productName"/>
									</div>
									<div align="center"><s:property value="price"/>
									</div>
									<div align="center">
										<a href='${pageContext.request.contextPath}/admin/CartAction?productId=<s:property value="id"/>'>添加购物车</a>
									</div>
									
								</div>
							</div>
						
						</s:iterator>
					</div>

				</div>
				<div align="center">
					<nav aria-label="Page navigation">
					<ul class="pagination">
						<!-- 当前总页面小于5页时，直接遍历输出总页码 -->
					<s:if test="#request.pages<=5"> 
							<!-- 当前页大于第一页时，上一页按钮显示 -->
							<s:if test="#request.currentPage>1">
								<li>
								<a href="${pageContext.request.contextPath}/FindByPriceRange?currentPage=<s:property value="#request.currentPage-1"/>${sessionScope.searchMessage}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
								</li>
							</s:if>
							
							<s:iterator begin="1" end="#request.pages" status="p" >
									<s:if test="#request.currentPage==#p.index+1">
										<li >
										<span style="background-color: deepskyblue;" >
											
											<a href="${pageContext.request.contextPath}/FindByPriceRange?currentPage=<s:property value="#p.index+1"/>${sessionScope.searchMessage}" ><s:property value="#p.index+1" />
										    </a>
									   
										</span> </li>
									</s:if>
									<s:else>
										<li>
										   <a href="${pageContext.request.contextPath}/FindByPriceRange?currentPage=<s:property value="#p.index+1"/>${sessionScope.searchMessage}"><s:property value="#p.index+1"/>
										   </a>
										</li>
									</s:else>	
							</s:iterator>
							<s:if test="#request.currentPage<#request.pages">
							<li>
								<a href="${pageContext.request.contextPath}/FindByPriceRange?currentPage=<s:property value="#request.currentPage+1"/>${sessionScope.searchMessage}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
							</s:if>
					</s:if> 
					
					
					
					
						<!--当总页面大于5页时，只显示五页，根据当前页，输出当前页的前后两页，和当前页，共五页  -->
							<!-- 当前页大于第一页时，上一页按钮显示 -->
						<s:if test="#request.pages>5">
							<s:if test="#request.currentPage>1">
								<li>
								<a href="${pageContext.request.contextPath}/YunGrocer?currentPage=${requestScope.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
								</li>
							</s:if>
							<!--当前页小于3页时 不使当前页始终处于5页的中间页 -->
							<s:if test="#request.currentPage<3">
								<c:forEach begin="1" end="5" var="p">
									<li><a href="${pageContext.request.contextPath}/YunGrocer?currentPage=${p}">${p}</a></li>
								</c:forEach>
							</s:if>
							<!--当前页大于等于3页时 使当前页始终处于5页的中间页 -->
							<s:if test="#request.currentPage>=3">
								<c:forEach begin="${requestScope.currentPage-2}" end="${requestScope.currentPage+2}" var="p">
									<li><a href="${pageContext.request.contextPath}/YunGrocer?currentPage=${p}">${p}</a></li>
								</c:forEach>
							</s:if>
							<li>
								<a href="${pageContext.request.contextPath}/YunGrocer?currentPage=${requestScope.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</s:if>
					</ul>
				</nav>
				</div>
				

			</div>
		</div>
	</body>

</html>