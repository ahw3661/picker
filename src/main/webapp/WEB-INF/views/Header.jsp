<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="wrap">
			<div class="centerBlock header_block">
				<div id="logo_div"><img id="logoimg" src="resources/image/logo/logo_header.png" alt="logo" onclick="location.href='section'"></div>
				<div id="menu_div">
					<ul class="stretchBlock menu">
						<li class="dd"><a href="introPage">THEPICKER</a></li>
						<li class="dd"><a href="noticeList">NOTICE</a></li>
						<li class="dd"><a href="goList?i_category=living">LIVING</a></li>
						<li class="dd"><a href="goList?i_category=kitchen">KITCHEN</a></li>
						<li class="dd"><a href="goList?i_category=bathroom">BATHROOM</a></li>
						<li class="dd"><a href="goList?i_category=office">OFFICE</a></li>
						<li class="dd"><a href="goList?i_category=market">MARKET</a></li>
						<li class="dd"><a href="goList?i_category=travel">TRAVEL</a></li>
					</ul>
				</div>
				<div id="buttondiv">
					<c:if test="${u_id == null}">
						<a href="loginPage">
							<span id="login" data-tooltip-text="로그인"><img alt="login_image" src="resources/image/icon/login.png"></span>
						</a>
						<a href="joinAgree">
							<span id="join" data-tooltip-text="회원가입"><img alt="user_image" src="resources/image/icon/user.png"></span>
						</a>
						<a href="cartList">
							<span id="cart" data-tooltip-text="장바구니"><img alt="cart_image" src="resources/image/icon/shopping_cart.png">
								<c:if test="${empty cnt || cnt == null}">
									<span id="fs">(0)</span>
								</c:if>
								<c:if test="${not empty cnt }">
									<span id="fs">(${cnt })</span>
								</c:if>
							</span>
						</a>
					</c:if>
					<c:if test="${u_id != null }">
						<a href="logout">
							<span id="logout" data-tooltip-text="로그아웃"><img alt="logout_image" src="resources/image/icon/logout.png"></span>
						</a>
						<c:if test="${u_type == 0 }">
							<a href="adminPage">
								<span id="admin" data-tooltip-text="관리페이지"><img alt="admin_image" src="resources/image/icon/admin.png"></span>
							</a>
							<a href="cartList">
								<span id="cart" data-tooltip-text="장바구니">
									<img alt="cart_image" src="resources/image/icon/shopping_cart.png">
									<c:if test="${empty cnt || cnt == null}">
										<span id="fs">(0)</span>
									</c:if>
									<c:if test="${not empty cnt }">
										<span id="fs">(${cnt })</span>
									</c:if>
								</span>
							</a>
						</c:if>
						<c:if test="${u_type == 1 }">
							<a href="myPage">
								<span id="mypagemenu" data-tooltip-text="마이페이지"><img alt="user_image" src="resources/image/icon/user.png"></span>
							</a>
							<a href="cartList">
								<span id="cart" data-tooltip-text="장바구니">
									<img alt="cart_image" src="resources/image/icon/shopping_cart.png">
									<c:if test="${empty cnt || cnt == null}">
										<span id="fs">(0)</span>
									</c:if>
									<c:if test="${not empty cnt }">
										<span id="fs">(${cnt })</span>
									</c:if>
								</span>
							</a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</header>
</body>
</html>