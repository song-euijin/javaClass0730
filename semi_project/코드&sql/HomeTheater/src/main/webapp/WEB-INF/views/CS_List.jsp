<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Anime | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/elegant-icons.css">
    <link rel="stylesheet" href="resources/css/plyr.css">
    <link rel="stylesheet" href="resources/css/nice-select.css">
    <link rel="stylesheet" href="resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="resources/css/slicknav.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>

<body>


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


    <!-- Hero Section End -->

<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="trending__product">
						<div class="row">
							<div class="col-lg-8 col-md-8 col-sm-8">
									<div class="section-title">
									<h4>검색어 : ${keyword}</h4></div>
							</div>
						</div>
						<div class="row">
							<c:forEach var="list" items="${sList}">
								<div class="col-lg-3 col-md-6 col-sm-6">
									<div class="product__item">
										<div class="product__item__pic set-bg">
											<a href="C_view?CNum=${list.CNum}&page=${paging.page}">
											<img src="C:/Users/djand/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/HomeTheater/src/main/webapp/resources/CPoster/${list.CPoster}" class="product__item__pic set-bg" style="width:262.5px;"></a>	<!-- 포스터 파일이름 -->
											
											<div class="view"><i class="fa fa-eye"></i> ${list.CHit}</div><!-- 조회 수 -->
										</div>
										<div class="product__item__text">
											<ul>

											</ul>
											<h5>
												<a href="C_view?CNum=${list.CNum}&page=${paging.page}">${list.CName}</a>
											</h5>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->

<!-- Footer Section Begin -->
<footer class="footer" id="footer" style="margin-top:125px;">
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
                        <li class="active"><a href="">Homepage</a></li>	
                        <li><a href="B_board">게시판</a></li>		<!-- 여기도 로딩 컨트롤러로 -->
                        <li>
                        	<c:if test="${empty sessionScope.loginId}"><a href="M_loginForm">로그인</a></c:if>		<!-- 로그인 페이지로 -->
                        	<c:if test="${!empty sessionScope.loginId}"><a href="M_logout">로그아웃</a></c:if>	<!-- 로그아웃 페이지로 -->
                        </li>
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

<script type="text/javascript" src="resources/js/jquery3.6.0.js"></script>
<script type="text/javascript">
if(${sList.isEmpty()}){
	let footer = document.getElementById('footer');
	footer.style = "position:absolute;bottom:0;width:100%;left:0;";
}
</script>
	

</html>