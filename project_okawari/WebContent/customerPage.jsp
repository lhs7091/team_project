<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注文</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="apple-touch-icon" sizes="76x76"
   href="/project_okawari/bootstrap/assets/img/apple-icon.png">
<link href="https://fonts.googleapis.com/css?family=Cookie"
   rel="stylesheet">
<link rel="icon" type="image/png"
   href="/project_okawari/bootstrap/assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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

<script>
   //주문리스트 버튼
   function orderSubmit(index) {
      //버튼 반환값 1일 경우 주문확인으로 DB에 주문목록저장
      //버튼 반환값 2일경우 주문 내역 날리기

      if (index == 1) {
         document.orderList.action = 'customerMenuOrder.okawari';
      }
      if (index == 2) {
         document.orderList.action = 'customerPage.okawari?cancle_all=100000';
         /* document.orderList.action='customerPage.okawari'; */
      }
      document.orderList.submit();
   }
</script>

<style>


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
            <ul class="nav" style = "margin-bottom: 150px;">
               
               <!-- *******************************고객 메뉴 주문_html_시작********************************************** -->
               
               
                     <form name="orderList" method="post">
                        <h4 class="card-title" align="center">注文リスト</h4>
                        <table class="table">
                           <thead class=" text-primary">
                              <th class="text-center">メニュー</th>
                              <th class="text-center">値段</th>
                              <th class="text-center">数量</th>
                           </thead>
                           <tbody>
                              <c:forEach var="cart" items="${cartList }" varStatus="status">
                                 <tr>
                                    <%--    <td><img src="menu-images/${cart.menu_image }" id="productImage"/></td> --%>
                                    <td><a
                                       href="customerPage.okawari?menu_num_dec=${cart.menu_num }">${cart.menu_name }</a><br>
                                    </td>
                                    <td>${cart.menu_price }</td>
                                    <td>${cart.qty }</td>
                                 </tr>

                              </c:forEach>
                              <tr>
                                 <td colspan="3" style ="text-align: right; padding-right: 23px;">総額 : ${totalPrice }</td>
                              </tr>
                              <tr>
                                 <td colspan="3" align="center"><input type="button"
                                    value="注文" onclick='orderSubmit(1)' class="btn btn-primary btn-round"/> <input type='button'
                                    value="キャンセル" onclick='orderSubmit(2)' class="btn btn-primary btn-round"/></td>
                              </tr>
                           </tbody>
                        </table>
                     </form>
               
                  </ul>
               
               </div>
               <!-- ********************************고객 메뉴 주문_html_끝********************************************** -->

            </ul>
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
                        <h5 class="card-title">メニュー</h5>
                     </div>
                     <div class="card-body">
                        
                     <!-- ******************************************************************* -->   
                        
                        <div>

                        <table class="table">            
                              <!-- 오른쪽에 메뉴 리스트 들어갈 자리 -->
                              <td colspan="2" style =" border: none; padding: 0px;">
                                 <table>
                                    <tr>
                                       <c:forEach var="menu" items="${menuList }" varStatus="status">
                                          <td>   
                                             <div class="col-lg-12">
                                             
                                                <div class="card card-stats">
            
                                                   <table>
                                                      <tr>
                                                         <c:if test="${menu.menu_count eq 0}">
                                                            <td rowspan="2"><img
                                                               src="menu-img/${menu.menu_image }" id="productImage" />
                                                            </td>
            
                                                            <td><font size="2">商品名 : ${menu.menu_name }</font>
                                                            </td>
                                                      </tr>
                                                      <tr>
                                                         <td><font size="2">値&nbsp;&nbsp;段 :
                                                               ${menu.menu_price }</font></td>
                                                         </c:if>
            
                                                         <c:if test="${menu.menu_count ne 0}">
                                                            <td rowspan="2"><a
                                                               href="customerPage.okawari?menu_num_add=${menu.menu_num }"><img
                                                                  src="menu-img/${menu.menu_image }" id="productImage" /></a>
                                                            </td>
            
                                                            <td><font size="2">商品名 : ${menu.menu_name }</font>
                                                            </td>
                                                      </tr>
                                                      <tr>
                                                         <td><font size="2">値&nbsp;&nbsp;段
                                                               :${menu.menu_price }</font></td>
                                                         </c:if>
                                                      </tr>
                                                   </table>
            
                                                </div>
                                             </div>
            
                                          </td>
                                          <c:if test="${((status.index+1) mod 2) == 0}">
                                    </tr>
                                    <tr>
                                       </c:if>
                                       </c:forEach>
                                    </tr>
                                 </table>
                              
                           </tr>
                        </table>
                     </div>      
                        
                        
                     <!-- ******************************************************************* -->   
                        
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
</div>
</body>
</html>