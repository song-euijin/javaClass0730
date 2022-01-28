<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Content Add</title>

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
<style>
table, tr, td, th {
	border: 1px solid backgroundcolor;
	color:white;
}

tr {
	height: 45px;
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



	<form name="CModiForm" action="C_modify" method="POST"
		enctype="multipart/form-data"
		style="text-align: center; display: flex; justify-content: center; align-items: center; margin-top: 30px;">
		<table>
			<tr>
				<th colspan="3"><strong style="font-size: 20px;">Content
						Modfiy</strong></th>
			</tr>

			<tr>
				<th>Content Number :</th>
				<td>${CModi.CNum}</td>
			</tr>
			<tr>
				<th>Poster :</th>
				<td><input type="file" name="CFile"></td>
			</tr>
			<tr>
				<th>Title :</th>
				<td><input type="text" class="form-control" name="CName"
					id="CName" placeholder="${CModi.CName}"></td>
			</tr>
			<tr>
				<th>Summery :</th>
				<td><textarea rows="10" class="form-control" name="CSummery"
						placeholder="${CModi.CSummery}"></textarea></td>
			</tr>
			<tr>
				<th>Come Out:</th>
				<td><input type="date" name="CDate" id="CDate"></td>
			</tr>
			<tr>
				<td colspan="3"><input style="font-weight: 400;"
					class="btn btn-outline-success" type="button" value="Modfiy"
					onclick="DoubleCheck()"> <input style="font-weight: 400;"
					class="btn btn-outline-danger" type="reset" value="Reset">
					<input style="font-weight: 400;" class="btn btn-outline-info"
					type="reset" onclick="location.href=''" value="Back"></td>
			</tr>
		</table>
		<input type="hidden" name="CNum" value="${CModi.CNum}">
	</form>
	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="page-up">
			<a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="footer__logo">
						<a href="index"><img src="resources/img/logo5-1.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer__nav">
						<ul>
							<li class="active"><a href="index">Homepage</a></li>
							<li><a href="B_board">게시판</a></li>
							<!-- 여기도 로딩 컨트롤러로 -->
							<li><c:if test="${empty sessionScope.loginId}">
									<a href="loginForm">로그인</a>
								</c:if> <!-- 로그인 페이지로 --> <c:if test="${!empty sessionScope.loginId}">
									<a href="logout">로그아웃</a>
								</c:if> <!-- 로그아웃 페이지로 --></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3">
					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						인천일보아카데미 &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						| This Project is made with <i class="fa fa-heart"
							aria-hidden="true"></i> by HomeTheater
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
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form action="C_search" class="search-model-form">
				<input type="text" name="keyword" placeholder="제목을 입력해주세요">
			</form>
		</div>
	</div>
	<!-- Search model end -->


</body>
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/player.js"></script>
<script src="resources/js/jquery.nice-select.min.js"></script>
<script src="resources/js/mixitup.min.js"></script>
<script src="resources/js/jquery.slicknav.js"></script>
<script src="resources/js/owl.carousel.min.js"></script>
<script src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/jquery3.6.0.js"></script>
<script type="text/javascript" src="resources/js/jquery3.6.0.js"></script>
<script type="text/javascript">
	// 제목 + 개봉일 정규식
	function DoubleCheck() {
		let Title = document.getElementById("CName").value;
		let CODate = document.getElementById("CDate").value;
		let idPass = false;

		$.ajax({
			type : "POST",
			url : "DoubleCheck",
			data : {
				"Title" : Title,
				"CODate" : CODate
			},
			async : false,
			dataType : "text",
			success : function(data) {
				// 성공시
				if (data == "OK") {
					alert("등록가능한 콘텐츠입니다.");
					CModify();
				} else {
					alert("이미 등록된 콘텐츠입니다.");
				}
			},
			error : function() {
				// 실패시
				alert("DoubleCheck 함수 통신 실패!");
			}
		});
	}

	function CModify() {
		CModiForm.submit()
	}
</script>
</html>