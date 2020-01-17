<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="apple-touch-icon" sizes="76x76"
	href="/project_okawari/bootstrap/assets/img/apple-icon.png">
<link rel="icon" type="image/png"
	href="/project_okawari/bootstrap/assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>メインページ</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<!-- CSS Files -->
<link href="/project_okawari/bootstrap/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="/project_okawari/bootstrap/assets/css/paper-dashboard.css?v=2.0.0"
	rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="/project_okawari/bootstrap/assets/demo/demo.css"
	rel="stylesheet" />
</head>


<!-- 슬라이드_script_시작 -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<script language="javascript" type="text/javascript">
	var j = $.noConflict(true); // $.noConflict(true) 를 사용해서 $ -> 변수로 선언한 j로 바꾸니 해결!

	j(document).ready(function() {

		var main = j('.bxslider').bxSlider({

			mode : 'fade',
			auto : true, //자동으로 슬라이드 
			controls : true, //좌우 화살표	
			autoControls : true, //stop,play 
			pager : true, //페이징 
			pause : 3000,
			autoDelay : 0,
			slideWidth : 800,
			speed : 300,
			stopAutoOnclick : true

		});

		j(".bx-stop").click(function() { // 중지버튼 눌렀을때 

			main.stopAuto();
			j(".bx-stop").hide();
			j(".bx-start").show();

			return false;

		});

		j(".bx-start").click(function() { //시작버튼 눌렀을때 

			main.startAuto();
			j(".bx-start").hide();
			j(".bx-stop").show();

			return false;

		});

		j(".bx-start").hide(); //onload시 시작버튼 숨김. 

	});
</script>

<!-- 슬라이드_script_끝 -->

<style>

/**************************슬라이드_CSS_시작*******************************/
img {
	position: relative;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

/**************************슬라이드_CSS_끝*******************************/
</style>

<body>
	<div class="wrapper ">
		<div class="sidebar" data-color="white" data-active-color="danger">
			<!--
	        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
	    -->
			<
			<div class="logo">
				<a href="mainPage.jsp" class="simple-text logo-normal"> <img
					src="/project_okawari/bootstrap/assets/img/okawari.png" />
				</a>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<c:if test="${session.user_auth == 2 }">
						<li><a href="/project_okawari/orderBranchForm.okawari"> <i
								class="nc-icon nc-bank"></i>
								<p>発注（支店->本店）</p>
						</a></li>
					</c:if>

					<li><a href="/project_okawari/orderBranchList.okawari"> <i
							class="nc-icon nc-diamond"></i>
							<p>発注リスト（支店）</p>
					</a></li>

					<c:if test="${session.user_auth == 2 }">
						<li><a
							href="/project_okawari/showBranchStock.okawari?user_id=${session.user_id}">
								<i class="nc-icon nc-pin-3"></i>
								<p>支店ストック</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 1 }">
						<li><a
							href="/project_okawari/showHeadStock.okawari?user_id=${session.user_id}">
								<i class="nc-icon nc-bell-55"></i>
								<p>本店ストック</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 1 }">
						<li><a href="/project_okawari/addMenuForm.okawari"> <i
								class="nc-icon nc-single-02"></i>
								<p>新メニュー登録</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 1 }">
						<li><a href="/project_okawari/addStockHeadForm.okawari">
								<i class="nc-icon nc-tile-56"></i>
								<p>外部発注（本店）</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 1 }">
						<li><a href="/project_okawari/registerBranchForm.okawari">
								<i class="nc-icon nc-caps-small"></i>
								<p>支店登録</p>
						</a></li>
					</c:if>

					<li><a href="/project_okawari/userList.okawari"> <i
							class="nc-icon nc-spaceship"></i>
							<p>お店をさがす</p>
					</a></li>

					<c:if test="${session.user_auth == 1 }">
						<li><a href="/project_okawari/showHeadSales.okawari"> <i
								class="nc-icon nc-bell-55"></i>
								<p>売り上げ</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 2 }">
						<li><a href="/project_okawari/userProfit.okawari"> <i
								class="nc-icon nc-bell-55"></i>
								<p>売り上げ</p>
						</a></li>
					</c:if>

					<c:if test="${session.user_auth == 2 }">
						<li><a href="/project_okawari/customerOrderCancle.okawari">
								<i class="nc-icon nc-bell-55"></i>
								<p>取り消し</p>
						</a></li>
					</c:if>


				</ul>
			</div>
		</div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
			<div class="container-fluid">
				<div class="navbar-wrapper">
					<div class="navbar-toggle">
						<button type="button" class="navbar-toggler">
							<span class="navbar-toggler-bar bar1"></span> <span
								class="navbar-toggler-bar bar2"></span> <span
								class="navbar-toggler-bar bar3"></span>
						</button>
					</div>
					<a class="navbar-brand" href="mainPage.jsp">孤独のグルメ</a>
				</div>
				<div>
					<a href="logout.okawari"
						style="float: right; text-decoration: none;">ログアウト</a>
				</div>
			</div>
			</nav>
			<!-- End Navbar -->
			<!-- <div class="panel-header">
	  
	  
	  <div class="header text-center">
	      <h2 class="title">Notifications</h2>
	      <p class="category">Handcrafted by our friend <a target="_blank" href="https://github.com/mouse0270">Robert McIntosh</a>. Please checkout the <a href="http://bootstrap-notify.remabledesigns.com/" target="_blank">full documentation.</a></p>
	  </div>
	  
	</div> -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h5 class="card-title">メインページ</h5>
							</div>
							<div class="card-body">
								<!-- *************************************슬라이드 _html_시작***************************************************** -->
								<div id="main_slide "
									style="position: relative; text-align: -webkit-center;">
									<div class="home__slider" style="width: 800px;">

										<div class="bxslider">

											<div>
												<img src="main_slide_img/beefpillaff.jpg" alt="그림1">
											</div>
											<div>
												<img src="main_slide_img/chikenomulet.jpg" alt="그림2">
											</div>
											<div>
												<img src="main_slide_img/culet&curry.jpg" alt="그림3">
											</div>
											<div>
												<img src="main_slide_img/cutlet&omulet.jpg" alt="그림4">
											</div>

										</div>
									</div>
								</div>
								<!-- ****************************************슬라이드_html_끝****************************************************** -->
							</div>



						</div>
					</div>
				</div>

			</div>
			<footer class="footer footer-black  footer-white ">
			<div class="container-fluid">
				<div class="row">
					<nav class="footer-nav">
					<ul>
						<li><a href="https://www.creative-tim.com" target="_blank">Creative
								Tim</a></li>
						<li><a href="http://blog.creative-tim.com/" target="_blank">Blog</a>
						</li>
						<li><a href="https://www.creative-tim.com/license"
							target="_blank">Licenses</a></li>
					</ul>
					</nav>
					<div class="credits ml-auto">
						<span class="copyright"> © <script>
							document.write(new Date().getFullYear())
						</script>, made with <i class="fa fa-heart heart"></i> Okawari
						</span>
					</div>
				</div>
			</div>
			</footer>
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="/project_okawari/bootstrap/assets/js/core/jquery.min.js"></script>
	<script src="/project_okawari/bootstrap/assets/js/core/popper.min.js"></script>
	<script
		src="/project_okawari/bootstrap/assets/js/core/bootstrap.min.js"></script>
	<script
		src="/project_okawari/bootstrap/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
	<!--  Google Maps Plugin    -->
	<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
	<!-- Chart JS -->
	<script
		src="/project_okawari/bootstrap/assets/js/plugins/chartjs.min.js"></script>
	<!--  Notifications Plugin    -->
	<script
		src="/project_okawari/bootstrap/assets/js/plugins/bootstrap-notify.js"></script>
	<!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
	<script
		src="/project_okawari/bootstrap/assets/js/paper-dashboard.min.js?v=2.0.0"
		type="text/javascript"></script>
	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="/project_okawari/bootstrap/assets/demo/demo.js"></script>

</body>
</html>