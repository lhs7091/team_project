<%@page import="java.util.ArrayList"%>
<%@page import="okawari_model.StockHeadDTO"%>
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
<title>発注リスト（支店）</title>
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


<body class="">
	<div class="wrapper ">
		<div class="sidebar" data-color="white" data-active-color="danger">
			<!--
	        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
	    -->
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
			<!-- <div class="panel-header panel-header-sm">
	  
	  
	</div> -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">発注リスト（支店）</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class=" text-primary">
											<th class="text-center">発注番号</th>
											<th class="text-center">発注支店</th>
											<th class="text-center">発注商品</th>
											<th class="text-center">発注日付</th>
											<th class="text-center">発注時間</th>
											<th class="text-center">数量</th>
											<th class="text-center">値段</th>
											<th class="text-center">総額</th>
											<th class="text-center">現況</th>
											<th class="text-center">賞味期限</th>
											<th class="text-center">受け取り</th>
										</thead>
										<tbody>
											<c:forEach var="list" items="${list }">
												<tr>
													<td align="center">${list.order_num }</td>
													<td align="center">${list.order_branch_id }</td>
													<td align="center">${list.order_menu_num }</td>
													<td align="center">${list.order_date }</td>
													<td align="center">${list.order_time }</td>
													<td align="center">${list.order_count }</td>
													<td align="center">${list.order_perprice }</td>
													<td align="center">${list.totprice }</td>
													<c:choose>
														<c:when
															test="${session.user_auth==1 && list.order_complete_head=='n'}">
															<td align="center">${list.order_complete_head }</td>
															<td>
																<form action="approve.okawari" method="post">
																	<input type="hidden" name="order_branch_id"
																		value="${list.order_branch_id }" /> <input
																		type="hidden" name="order_num"
																		value="${list.order_num }" /> <input type="hidden"
																		name="order_menu_num" value="${list.order_menu_num }" />
																	<input type="hidden" name="order_count"
																		value="${list.order_count }" /><br> <input
																		type="submit" value="承認" />
																</form>
															</td>
															<td align="center">${list.order_expired_date }</td>
															<td align="center">${list.order_complete_branch }</td>
														</c:when>
														<c:when
															test="${session.user_auth==1 && list.order_complete_head=='y'}">
															<td align="center">${list.order_complete_head }<br>
																${list.order_complete_date }<br>
																${list.order_complete_time }
															</td>
															<td align="center">${list.order_expired_date }</td>
															<td align="center">${list.order_complete_branch }</td>
														</c:when>
														<c:when
															test="${session.user_auth==2 && list.order_complete_branch=='n' && list.order_complete_head=='n'}">
															<td align="center">${list.order_complete_head }<br>
																${list.order_complete_date }<br>
																${list.order_complete_time }
															</td>
															<td align="center">${list.order_expired_date }</td>
															<td align="center">${list.order_complete_branch }</td>

														</c:when>
														<c:when
															test="${session.user_auth==2 && list.order_complete_branch=='n' && list.order_complete_head=='y'}">
															<td align="center">${list.order_complete_head }<br>
																${list.order_complete_date }<br>
																${list.order_complete_time }
															</td>
															<td align="center">${list.order_expired_date }</td>
															<td align="center">
																<form action="approve.okawari" method="post">
																	<input type="hidden" name="order_branch_id"
																		value="${list.order_branch_id }" /> <input
																		type="hidden" name="order_num"
																		value="${list.order_num }" /> <input type="hidden"
																		name="order_menu_num" value="${list.order_menu_num }" />
																	<input type="hidden" name="order_count"
																		value="${list.order_count }" />
																	${list.order_complete_branch }<br> <input
																		type="submit" value="受け取り確認" />
																</form>
															</td>

														</c:when>
														<c:when
															test="${session.user_auth==2 && list.order_complete_branch=='y'}">
															<td align="center">${list.order_complete_head }<br>
																${list.order_complete_date }<br>
																${list.order_complete_time }
															</td>
															<td align="center">${list.order_expired_date }</td>
															<td align="center">${list.order_complete_branch }<br>
																${list.order_complete_branch_date }<br>
																${list.order_complete_branch_time }
															</td>
														</c:when>
													</c:choose>


												</tr>
											</c:forEach>

										</tbody>
									</table>

								</div>
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