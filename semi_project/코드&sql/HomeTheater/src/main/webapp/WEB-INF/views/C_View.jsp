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
<title>Home Theater</title>

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
						<a href="index"> <img src="resources/img/logo5-1.png" alt="">
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
									<li><a href="#" class="search-switch"><span
											class="icon_search"></span></a></li>

									<li><a href="#"><span class="icon_profile"><span
												class="arrow_carrot-down"></span></span></a>
										<ul class="dropdown">
											<li><a href="M_loginForm">로그인</a></li>
											<li><a href="M_joinForm">회원가입</a></li>
										</ul></li>
								</ul>
							</c:when>
							<c:otherwise>
								<ul>
									<li><a href="#" class="search-switch"><span
											class="icon_search"></span></a></li>

									<li><a href="#"><span class="icon_profile"><span
												class="arrow_carrot-down"></span></span></a>
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

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12"></div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="anime__details__content">
				<div class="row">
					<div class="col-lg-3">
						<div class="anime__details__pic set-bg"
							data-setbg="resources/CPoster/${CView.CPoster}">
							<div class="view">
								<i class="fa fa-eye"></i>${CView.CHit}</div>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="anime__details__text">
							<div class="anime__details__title">
								<h3>${CView.CName}</h3>
								<span>${CView.CDate}</span>
							</div>
							<div class="anime__details__rating">
								<span> <c:if test="${sessionScope.loginId eq 'admin'}">
										<button class="btn btn-outline-success"
											onclick="location.href='C_modiForm?CNum=${CView.CNum}'">수정</button>
										<button class="btn btn-outline-danger"
											onclick="location.href='C_delete?CNum=${CView.CNum}'">삭제</button>
									</c:if>
								</span>
							</div>
							<p style="height: 280px;">${CView.CSummery}</p>
							<div class="anime__details__btn" style="display: flex;">

								<p id="plike">${like}&nbsp;&nbsp;</p>
								<input style="height: 40px; font-weight: 200;"
									class="btn btn-success" type="button" value="좋아요"
									onclick="like()">

								<p id="pdislike">&nbsp;&nbsp;${dislike} &nbsp;</p>
								<input style="height: 40px; font-weight: 400;"
									class="btn btn-danger" type="button" value="별로에요"
									onclick="dislike()">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8" style="width: 800px;">
					<div class="anime__details__review">
						<div class="section-title">
							<h5>Reviews</h5>
						</div>
						<div id="reviewArea"></div>
					</div>
				</div>
				<div class="anime__details__form">
					<div class="section-title">
						<h5>Your Comment</h5>
					</div>
					<textarea rows="5" cols="35" id="rv_Area"
						placeholder="Your Comment"></textarea>
					<br />
					<button id="rvBtn" class="btn btn-outline-info">
						<i class="fa fa-location-arrow"></i> Review
					</button>
				</div>
			</div>
		</div>

	</section>
	<input type="hidden" id="KID" value="${sessionScope.loginId}">
	<input type="hidden" id="KNUM" value="${CView.CNum}">
	<!-- Anime Section End -->

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
									<a href="M_loginForm">로그인</a>
								</c:if> <!-- 로그인 페이지로 --> <c:if test="${!empty sessionScope.loginId}">
									<a href="M_logout">로그아웃</a>
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

<!-- Js Plugins -->
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/player.js"></script>
<script src="resources/js/jquery.nice-select.min.js"></script>
<script src="resources/js/mixitup.min.js"></script>
<script src="resources/js/jquery.slicknav.js"></script>
<script src="resources/js/owl.carousel.min.js"></script>
<script src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/jquery3.6.0.js"></script>
<script type="text/javascript">
	function like() {
		let KID = document.getElementById("KID").value;
		let KNUM = document.getElementById("KNUM").value;
		let plike = document.getElementById("plike");

		if (KID == "") {
			alert("로그인 해주세요!")
		} else {
			$.ajax({
				type : "POST",
				url : "C_like",
				data : {
					"KID" : KID,
					"KNUM" : KNUM
				},
				async : false,
				dataType : "text",

				success : function(data) {
					$("#plike").replaceWith(
							"<p id='plike'>" + data + "&nbsp;&nbsp;</p>");
				},
				error : function() {
					alert("C_like 함수 통신 실패!");
				}
			});
		}
	}

	function dislike() {
		let KID = document.getElementById("KID").value;
		let KNUM = document.getElementById("KNUM").value;
		let pdislike = document.getElementById("pdislike");

		if (KID == "") {
			alert("로그인 해주세요!")
		} else {
			$.ajax({
				type : "POST",
				url : "C_dislike",
				data : {
					"KID" : KID,
					"KNUM" : KNUM
				},
				async : false,
				dataType : "text",

				success : function(data) {
					$("#pdislike").replaceWith(
							"<p id='pdislike'>&nbsp;&nbsp;" + data
									+ "&nbsp;</p>");
				},
				error : function() {
					alert("C_dislike 함수 통신 실패!");
				}
			});
		}
	}

	$(document).ready(function() {
		let RCNUM = document.getElementById("KNUM").value;
		let RID = "${sessionScope.loginId}";

		$.ajax({
			type : "POST",
			url : "R_list",
			data : {
				"RCNUM" : RCNUM
			},
			dataType : "json",

			success : function(result) {
				reviewList(result);
			},
			error : function() {
				alert("댓글 리스트 불러오기 실패")
			}
		});
	})

	function reviewList(result) {
		let output = "";

		for ( var i in result) {
			output += "<div class='anime__review__item'>";
			output += "<div class='anime__review__item__text'>";
			output += "<h6>" + result[i].RID + "<span> - " + result[i].RDATE
					+ "</span></h6>";
			output += "<p>" + result[i].RCOMMENT + "</p>";
			output += "</div>";
			output += "</div>";
		}

		$("#reviewArea").html(output);
	}

	$("#rvBtn").click(function() {

		let rv_Area = $("#rv_Area").val();
		let RCNUM = document.getElementById("KNUM").value;
		let RID = "${sessionScope.loginId}";
		if (RID == "") {
			alert("로그인 해주세요!")
		} else {
			$.ajax({
				type : "POST",
				url : "R_write",
				data : {
					"RID" : RID,
					"RCOMMENT" : rv_Area,
					"RCNUM" : RCNUM
				},
				dataType : "json",

				success : function(result) {
					reviewList(result);
					$("#rv_Area").val("");
				},
				error : function() {
					alert("댓글 작성 실패");
				}

			});
		}
	})
</script>
</html>