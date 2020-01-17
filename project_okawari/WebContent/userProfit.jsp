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
<title>本店売り上げ</title>
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
<!-- calendar CSS -->
<link rel="stylesheet"
   href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
   type="text/css" />
<!-- jQuery 기본 js파일 -->
<script
   src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--  jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

</head>


<script>
   $(function() {
      $("#day").datepicker(
            {
               /*  numberOfMonths: [2,2], */

               changeMonth : true,
               changeYear : true,
               nextText : '先月',
               prevText : '来月',

               changeMonth : true,
               dayNames : [ '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日',
                     '日曜日' ],
               dayNamesMin : [ '月', '火', '水', '木', '金', '土', '日' ],
               monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
                     '9', '10', '11', '12' ],
               monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
                     '8月', '9月', '10月', '11月', '12月' ],

               showButtonPanel : true,
               currentText : '今日',
               closeText : 'クローズ',
               dateFormat : "yy-mm-dd",

            });
      $("#day2").datepicker(
            {
               /*  numberOfMonths: [2,2], */

               changeMonth : true,
               changeYear : true,
               nextText : '先月',
               prevText : '来月',

               changeMonth : true,
               dayNames : [ '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日',
                     '日曜日' ],
               dayNamesMin : [ '月', '火', '水', '木', '金', '土', '日' ],
               monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
                     '9', '10', '11', '12' ],
               monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
                     '8月', '9月', '10月', '11月', '12月' ],

               showButtonPanel : true,
               currentText : '今日',
               closeText : 'クローズ',
               dateFormat : "yy-mm-dd",

            });
      $("#day3").datepicker(
            {
               /*  numberOfMonths: [2,2], */

               changeMonth : true,
               changeYear : true,
               nextText : '先月',
               prevText : '来月',

               changeMonth : true,
               dayNames : [ '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日',
                     '日曜日' ],
               dayNamesMin : [ '月', '火', '水', '木', '金', '土', '日' ],
               monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
                     '9', '10', '11', '12' ],
               monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
                     '8月', '9月', '10月', '11月', '12月' ],

               showButtonPanel : true,
               currentText : '今日',
               closeText : 'クローズ',
               dateFormat : "yy-mm-dd",

            });
   });
</script>

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
                  <li><a href="/project_okawari/addMenu.okawari"> <i
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


               <c:if test="${session.user_auth == 2 }">
                  <li><a href="/project_okawari/userProfit.okawari"> <i
                        class="nc-icon nc-bell-55"></i>
                        <p>支店別売り上げ</p>
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
                      <h4 class="card-title"> 支店別売り上げ</h4>
                    </div>
                    <div class="card-body">
                       <form action="userProfit.okawari" method="post">
                     <input type="text" name="day" id="day" placeholder="날짜입력(click)">&nbsp;&nbsp; <input
                        type="submit" value="売り上げ閲覧" />
                  </form><br>
                  <c:if test="${salesDTO != null }">
                  <form action="userProfit.okawari?dayUpdate=${salesDTO.sales_date }" method="post">
                  <h5>${salesDTO.sales_branch_id }&nbsp;売り上げ閲覧</h5>
                      <div class="table-responsive">
                        <table class="table">
                          <thead class=" text-primary">
                              <th>[販売日]</th>
                        <th>[販売総額]</th>
                        <th>[卸売価格]</th>
                        <th>[手数料]</th>
                        <th>[純益]</th>
                          </thead>
                          <tbody>
                             <tr>
                                  <td>[${salesDTO.sales_date  }]</td>
                           <td>[${salesDTO.sales_total }]</td>
                           <td>[${salesDTO.sales_cost  }]</td>
                           <td>[${salesDTO.sales_commission }]</td>
                           <td>[${salesDTO.sales_origin }]</td>
                               </tr>
                               <tr><td colspan="5" align="center"><input type="submit" value="売り上げ精算" /></td></tr>
                          </tbody>
                        </table>
                      </div>
                      </form>
                      </c:if>
                    </div>
                  </div>
            
                
                
             <div class="row">
            <div class="col-md-12">
                  <div class="card">
                    <div class="card-header">
                      <h4 class="card-title"> 日付による閲覧</h4>
                    </div>
                    <div class="card-body">
                       <form action="userProfit.okawari" method="post">
                     <table>
                        <tr>
                           <td><input type="text" name="start" id="day2" placeholder="시작날짜입력(click)"></td>
                           <td><input type="text" name="end" id="day3" placeholder="종료날짜입력(click)"></td> 
                           <td><input type="submit" value="閲覧" /></td>
                        </tr>
                     </table>
                  </form>
                  <c:if test="${salesList != null }">
                  <h5 class="card-title">${session.user_id }&nbsp;の売り上げ </h5>
                      <div class="table-responsive">
                        <table class="table">
                          <thead class=" text-primary">
                                <th>[販売日]</th>
                        <th>[販売総額]</th>
                        <th>[卸売価格]</th>
                        <th>[手数料]</th>
                        <th>[純益]</th>
                          </thead>
                          <tbody>
                          <c:forEach var="list" items="${salesList}">
                             <tr>
                                  <td>[${list.sales_date  }]</td>
                           <td>[${list.sales_total }]</td>
                           <td>[${list.sales_cost  }]</td>
                           <td>[${list.sales_commission }]</td>
                           <td>[${list.sales_origin }]</td>
                               </tr>
                          </c:forEach>                            
                          </tbody>
                          
                          <thead>
                             <th>総計</th>
                        <th>[総売り上げ]</th>
                        <th>[総小売価格売り上げ]</th>
                        <th>[総手数料]</th>
                        <th>[総純益]</th>
                          </thead>
                          <tbody>
                             <th></th>
                        <td>[${salesListTot.sales_total }]</td>
                        <td>[${salesListTot.sales_cost  }]</td>
                        <td>[${salesListTot.sales_commission }]</td>
                        <td>[${salesListTot.sales_origin }]</td>
                          </tbody>
                        </table>
                      </div>
                      </c:if>
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
   <!-- jQuery 기본 js파일 -->
   <script
      src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
   <!--  jQuery UI 라이브러리 js파일 -->
   <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

</body>
</html>