<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Anime | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/plyr.css" type="text/css">
<link rel="stylesheet" href="resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/style.css" type="text/css">

<style type="text/css">
.list {
	color: white;
	padding: 15px 18px 15px 18px;
	border-bottom: 1px solid grey;
	background-color: #4e4e5d;
}

.list_small {
	color: grey;
	font-size: small;
	text-align: right;
	width: 10%;
	border-bottom: 1px solid grey;
	background-color: #4e4e5d;
}

.list_head {
	background-color: #24242f;
	padding: 20px 15px 20px 15px;
	border-bottom: 2.5px solid white;
	border-top: 2.5px solid white;
	color: white;
}

a {
	padding: 15px 18px 15px 18px;
	color: white;
}
</style>

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-2">
					<div class="header__logo">
						<a href="index"> <img src="resources/img/logo5-1.png"
							alt="">
						</a>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="header__nav">
						<nav class="header__menu mobile-menu">
							<ul>
								<li class="active"><a href="index">메인화면</a></li>
								<!-- 이거도 로딩 컨트롤러로 -->
								<li><a href="#">작품목록<span class="arrow_carrot-down"></span></a>
									<ul class="dropdown">
										<li><a href="index?filter=1">인기순</a></li>
										<!-- href : 각 카테고리별 리스트 로딩하는 컨트롤러로 -->
										<li><a href="index?filter=2">이름순</a></li>
										<li><a href="index?filter=3">최신 개봉순</a></li>
									</ul></li>
								<li><a href="B_board">게시판</a></li>
								<c:if test="${sessionScope.loginId eq 'admin'}">
									<li><a href="C_addForm">콘텐츠 추가</a></li>
									<li><a href="M_list">회원목록</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="header__menu mobile-menu">
					<c:choose>
						<c:when test="${empty sessionScope.loginId}">
						<ul>
						<li><a href="#" class="search-switch"><span class="icon_search"></span></a></li>
						
						<li><a href="#"><span class="icon_profile"><span class="arrow_carrot-down"></span></span></a>	
									<ul class="dropdown">
										<li><a href="M_loginForm">로그인</a></li>
										<li><a href="M_joinForm">회원가입</a></li>
									</ul></li>
						</ul>						
						</c:when>
						<c:otherwise>
						<ul>
						<li><a href="#" class="search-switch"><span class="icon_search"></span></a></li>
						
						<li><a href="#"><span class="icon_profile"><span class="arrow_carrot-down"></span></span></a>	
									<ul class="dropdown">
										<li><a href="M_loginForm">로그아웃</a></li>
										<li><a href="M_view?MId=${sessionScope.loginId}">내정보보기</a></li>
									</ul></li>
						</ul>	
						</c:otherwise>
					</c:choose>
						
					</div>
				</div>
			</div>
			<div id="mobile-menu-wrap"></div>
		</div>
	</header>
	<!-- Header End -->

	<!-- Blog Details Section Begin -->
	<section class="blog-details spad" style="padding-top: 0;">
		<div class="container"
			style="margin-top: 35 px; padding-bottom: 30 px;">
			<div class="row d-flex justify-content-center">
				<div class="col-lg-8">
					<div class="blog__details__content" style="min-width: 500px;">
						<div>
							<button onclick="location.href='B_writeForm'"
								style="margin-top: 40px; border: 0; outline: 0; background-color: #0b0c2a; color: white; float: right">글
								작성하기</button>
						</div>

						<table style="width: 100%; border-collapse: collapse;">
							<tr class="list_head">
								<th class="list_head"></th>
								<th style="background-color: #24242f;" class="list_small"
									style="text-align: right;">작성자</th>
								<th style="background-color: #24242f;" class="list_small"
									style="text-align: right;">작성일</th>
								<th style="background-color: #24242f;" class="list_small"
									style="text-align: right;">조회수</th>
							</tr>

							<c:forEach var="boardList" items="${boardList}">
								<tr class="list">
									<td><a style="padding: 20px 10px 20px 10px;"
										href="B_view?BNO=${boardList.BNO}&page=${paging.page}">${boardList.BTITLE}</a></td>
									<td style="padding: 20px 10px 20px 10px;" class="list_small"
										style="text-align: right;">${boardList.BWRITER}</td>
									<td style="padding: 20px 10px 20px 10px;" class="list_small"
										style="text-align: right;">${boardList.BDATE}</td>
									<td style="padding: 20px 10px 25px 10px;" class="list_small"
										style="text-align: right;">${boardList.BHIT}</td>
								</tr>
							</c:forEach>

						</table>

						<table style="margin-left: auto; margin-right: auto;">
							<tr style="color: #82828c;">
								<th><c:if test="${paging.page <= 1}">[이전]</c:if> <c:if
										test="${paging.page > 1}">
										<a style="color: white" href="B_board?page=${paging.page-1}">[이전]</a>
									</c:if></th>
								<th>
									<!-- [숫자] --> <c:forEach begin="${paging.startPage}"
										end="${paging.endPage}" step="1" var="i">
										<c:choose>
											<c:when test="${paging.page eq i}">${i}</c:when>
											<c:otherwise>
												<a style="color: white" href="B_board?page=${i}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</th>
								<th><c:if test="${paging.page >= paging.maxPage}">[다음]</c:if>
									<c:if test="${paging.page < paging.maxPage}">
										<a style="color: white" href="B_board?page=${paging.page+1}">[다음]</a>
									</c:if></th>
							</tr>
						</table>
					</div>


					<!--------------------search---------------------->

					<div class="blog__details__text" style="margin-top: 15px">
						<form action="B_search">
							<div style="float: left;">
								<select name="selectVal"
									style="color: white; background-color: #2f2f3a;">
									<option value="BWRITER">작성자</option>
									<option value="BTITLE">제목</option>
									<option value="BCONTENT">내용</option>
								</select>
							</div>
							<div
								style="margin-left: 5px; width: auto; margin-left: 2px; height: 38px; border-radius: 5px; border: solid 1.5px rgb(255, 255, 255);">
								<input type="text" name="keyword"
									style="width: 68%; margin-left: 4px; color: white; background-color: #0b0c2a; line-height: 28px; border: 0; outline: 0;">
								<input type="submit" value="| 검색 |"
									style="float: right; height: 30px; line-height: 25px; color: white; background-color: #0b0c2a; border: 0; outline: 0;">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Details Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="page-up">
			<a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="footer__logo">
						<a href="index"><img src="resources/img/logo5-1.png"
							alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer__nav">
						<ul>
							<li class="active"><a href="index">Homepage</a></li>
							<li><a href="B_board">게시판</a></li>			<!-- 여기도 로딩 컨트롤러로 -->
							<li><c:if test="${empty sessionScope.loginId}">
									<a href="M_loginForm">로그인</a>
								</c:if> <!-- 로그인 페이지로 --> 
								<c:if test="${!empty sessionScope.loginId}">
									<a href="M_logout">로그아웃</a>
								</c:if> <!-- 로그아웃 페이지로 --></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3">
					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						인천일보아카데미 &copy;
						<script>document.write(new Date().getFullYear());   </script> |      
						This Project is made with <i
						class="fa fa-heart" aria-hidden="true"></i> by HomeTheater
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>

				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	 <!-- Search model Begin -->
  <div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form action="C_search" class="search-model-form">
            <input type="text" name="keyword"  placeholder="제목을 입력해주세요">
        </form>
    </div>
</div>
<!-- Search model end -->

	<!-- Js Plugins -->
	<script src="resources/js/jquery-3.3.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/player.js"></script>
	<script src="resources/js/jquery.nice-select.min.js"></script>
	<script src="resources/js/mixitup.min.js"></script>
	<script src="resources/js/jquery.slicknav.js"></script>
	<script src="resources/js/owl.carousel.min.js"></script>
	<script src="resources/js/main.js"></script>

</body>

</html>