<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

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

<!-- 정보수정 style -->
<style>
table, tr, td, th {
	border: 1px solid backgroundcolor;
	color: white;
	width: 700px;
}

tr {
	height: 45px;
}

td {
	margin-top: 5px;
}
</style>


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

	<!-- Blog Details Section Begin -->
	<section class="blog-details spad">
		<div class="container">
			<div class="row d-flex justify-content-center">

				<div class="col-lg-8">
					<div class="blog__details__content">

						<div class="blog__details__form">
							<form name="ModifyForm" action="M_modify" method="POST"
								style="text-align: center; display: flex; justify-content: center; align-items: center; margin-top: 30px;">
								<table>
									<tr>
										<th colspan="2"><strong style="font-size: 20px;">${modify.MId}님
												회원정보수정</strong></th>


									</tr>





									<tr>
										<td>
											<!-- 아이디 --> <input type="hidden" name="MId"
											value="${modify.MId}">
										</td>
										<!-- 비밀번호 -->
										<th>비밀번호</th>
										<td style="width: 400px;"><input type="password"
											name="MPw" id="MPw" onkeyup="pwRegExp()"
											placeholder="password">
											<p id="pwRegExp"></p></td>
									</tr>

									<!-- 비밀번호 확인 -->

									<tr>
										<th>비밀번호 확인</th>
										<td><input type="password" id="checkPw"
											onkeyup="pwOverlap()" placeholder="Password Check"> <br />
											<span id="confirmPw"></span></td>
									</tr>


									<!-- 이름 -->
									<tr>
										<th>이름</th>
										<td><input type="text" name="MName"
											placeholder="${modify.MName}"></td>
									</tr>

									<!-- 이메일 -->
									<tr>
										<th>이메일</th>
										<td><input type="email" name="MEmail"
											placeholder="Email address" id="MEmail"> <span
											id="emailConfirmText"><input type="button" value="인증"
												onclick="confirmEmail()" class="btn btn-danger"></span></td>
									</tr>


									<!-- 연락처 -->
									<tr>
										<th>연락처</th>
										<td><input type="text" name="MPhone"
											placeholder="${modify.MPhone}" id="MPhone"
											onkeyup="phoneOverlap()"><br /> <span
											id="confirmPhone"></span></td>
									</tr>

									<!-- 수정완료 버튼 -->
									<tr>
										<td colspan="2"><input class="btn btn-danger"
											type="button" onclick="mModify()" value="회원수정"></td>
									</tr>

								</table>
							</form>
						</div>
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
						<script>document.write(new Date().getFullYear());   </script>
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

	// 아이디 중복체크
	function idOverlap(){
		let idCheck = document.getElementById("MId").value;
		let confirmId = document.getElementById("confirmId");
		let idPass = false;
		
		$.ajax({
			type : "POST" ,
			url : "A_idoverlap" ,
			data : { "MId" : idCheck } ,		
			async : false,
			dataType : "text" ,
			success :  function(data){
				// 성공시
				if(data=="OK"){
					confirmId.style.color="#0000ff";
					confirmId.innerHTML = "사용 가능한 아이디";
					idPass = true;
					
					
				} else {
					confirmId.style.color="#ff0000";
					confirmId.innerHTML = "사용 불가능한 아이디";
					idPass = false;
				}
			} , 
			error : function(){
				// 실패시
				alert("idOverlap함수 통신실패!");
			}
		});
		
		return idPass;
	}
	
	// 비밀번호 정규식
	function pwRegExp(){
		let MPw = document.getElementById("MPw").value;
		let pwRegExp = document.getElementById("pwRegExp");
		let pwPass = false;
	
		let num = MPw.search(/[0-9]/g);
		let eng = MPw.search(/[a-z]/ig);
		let spe = MPw.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
		
		if(MPw.length < 8 || MPw.length > 15){
			pwRegExp.style.color="#ff0000";
			pwRegExp.innerHTML = "8자리 ~ 15자리 이내로 입력해주세요!";
		} else if(MPw.search(/\s/) != -1) {
			pwRegExp.style.color="#ff0000";
			pwRegExp.innerHTML = "비밀번호는 공백 없이 입력해주세요!";
		} else if(num < 0 || eng < 0 || spe < 0){
			pwRegExp.style.color="#ff0000";
			pwRegExp.innerHTML = "영문, 숫자, 특수문자를 혼합하여 입력해주세요!";
		} else {
			pwRegExp.style.color="#0000ff";
			pwRegExp.innerHTML = "적절한 비밀번호 입니다!";
			pwPass = true;
		}
		
		return pwPass;
	}
	
	// 비밀번호 일치여부 확인
	function pwOverlap(){
		let MPw = document.getElementById("MPw").value;
		let pwRegExp = document.getElementById("pwRegExp");
		
		let checkPw = document.getElementById("checkPw").value;
		let confirmPw = document.getElementById("confirmPw");
		
		let pwPass = false;
		
		pwRegExp.innerHTML = "";
		
		if(MPw==checkPw){
			confirmPw.style.color = "#0000ff";
			confirmPw.innerHTML = "비밀번호가 일치합니다!";
			pwPass = true;
		} else {
			confirmPw.style.color = "#ff0000";
			confirmPw.innerHTML = "비밀번호를 확인해주세요!";
		}
		return pwPass;
	}
	
	// 이메일 인증
	function confirmEmail(){
		let MEmail = document.getElementById("MEmail").value;
		let emailConfirmText = document.getElementById("emailConfirmText");
		
		
		
		$.ajax({
			type : "GET" ,
			url : "A_emailConfirm" , 
			data : {"MEmail" : MEmail} ,
			dataType : "text" ,
			success : function(data){
				alert("입력하신 이메일로 인증번호가 발송됐습니다!");
				emailConfirmText.innerHTML = "<input type='hidden' id='check2' value='" + data + "'>"
												+ "<br/><input type='text' placeholder='인증번호 입력' id='emailKey'>"
												+ " <input type='button' class='btn btn-danger' value='확인' onclick='keyCheck()'>"
												+ "<br/><span id='check1'></span>";
			} ,
			error : function(){
				alert("인증번호 발송에 실패했습니다!");
			}
		});

	}
	
	// 인증번호 확인
	function keyCheck(){
		let emailKey = document.getElementById("emailKey").value;
		let check2 = document.getElementById("check2").value;
		let check1 = document.getElementById("check1");
		
		let emailPass = false;
		
		if(emailKey==check2){
			check1.style.color ="#0000ff";
			check1.innerHTML = "인증번호가 일치합니다!";
			emailPass = true;
		} else {
			check1.style.color ="#ff0000";
			check1.innerHTML = "인증번호가 일치하지 않습니다!";
		}
		return emailPass;
	}

	// 회원가입 버튼
	function mModify() {
		console.log("비밀번호 체크1 : " + pwRegExp());
		console.log("비밀번호 체크2 : " + pwOverlap());
		console.log("이메일 체크2 : " + keyCheck());
		console.log("연락처 체크1 : " + phoneOverlap());
		
		if(!pwRegExp()){
			alert("비밀번호를 규칙에 맞게 입력해주세요.");
		} else if(!pwOverlap()){
			alert("비밀번호가 일치하지 않습니다.");
		} else if(!keyCheck()){
			alert("이메일 인증을 진행해주세요.");
		} else if(!phoneOverlap()){
			alert("중복된 연락처입니다. 연락처를 확인해주세요.")
		} else {
			ModifyForm.submit();
		}
		
		
	}
	
	// 휴대전화 정규식
	function phoneOverlap(){
		let phoneCheck = document.getElementById("MPhone").value;
		let confirmPhone = document.getElementById("confirmPhone");
		let phonePass = false;
		
		$.ajax({
			type : "POST" ,
			url : "A_phoneoverlap" ,
			data : { "MPhone" : phoneCheck } ,		
			async : false,
			dataType : "text" ,
			success :  function(data){
				// 성공시
				if(data=="OK"){
					confirmPhone.style.color="#0000ff";
					confirmPhone.innerHTML = "사용 가능한 연락처";
					phonePass = true;				
				} else {
					confirmPhone.style.color="#ff0000";
					confirmPhone.innerHTML = "사용 불가능한 연락처";
					phonePass = false;
				}
			} , 
			error : function(){
				// 실패시
				alert("phoneOverlap함수 통신실패!");
			}
		});		
		return phonePass;
	}
	
	
		
</script>


</html>