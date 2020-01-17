<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
   href="/project_okawari/bootstrap/assets/img/apple-icon.png">
<link rel="icon" type="image/png"
   href="/project_okawari/bootstrap/assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>ログイン</title>
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
<link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css"
   rel="stylesheet" />


<!-- 슬라이드_script_시작 -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"> 
<script src="https://code.jquery.com/jquery-1.11.3.js"></script> 
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script> 

<script language="javascript" type="text/javascript"> 


var j = $.noConflict(true); // $.noConflict(true) 를 사용해서 $ -> 변수로 선언한 j로 바꾸니 해결!

j(document).ready(function(){ 

    var main = j('.bxslider').bxSlider({ 

    mode: 'fade', 
    auto: true,   //자동으로 슬라이드 
    controls : true,   //좌우 화살표   
    autoControls: true,   //stop,play 
    pager:true,   //페이징 
    pause: 3000, 
    autoDelay: 0,   
    slideWidth: 800, 
    speed: 300,
    stopAutoOnclick:true 

}); 



j(".bx-stop").click(function(){   // 중지버튼 눌렀을때 

    main.stopAuto(); 
    j(".bx-stop").hide(); 
    j(".bx-start").show(); 

    return false; 

}); 



j(".bx-start").click(function(){   //시작버튼 눌렀을때 

    main.startAuto(); 
    j(".bx-start").hide(); 
    j(".bx-stop").show(); 

    return false; 

}); 



j(".bx-start").hide();   //onload시 시작버튼 숨김. 

}); 

</script>

<!-- 슬라이드_script_끝 -->



<style type="text/css">
/**************************슬라이드_CSS_시작*******************************/
#main_slide {
   position: relative;
   
   
}

img {
   position: relative;
   display: table-cell;
   vertical-align: middle;
   text-align: center;
   height: 100%;
}


/**************************슬라이드_CSS_끝*******************************/

/**************************로그인_CSS_시작*******************************/
.form-4 {
   /* Size and position */

   position: relative;
   /* Font styles */
   font-family: 'Raleway', 'Lato', Arial, sans-serif;
   color: white;
   text-shadow: 0 2px 1px rgba(0, 0, 0, 0.3);
}

.form-4 h1 {
   font-size: 22px;
   padding-bottom: 20px;
}

.form-4 input[type=text], .form-4 input[type=password] {
   /* Size and position */
   width: 100%;
   padding: 8px 4px 8px 10px;
   margin-bottom: 15px;
   /* Styles */
   border: 1px solid #4e3043; /* Fallback */
   border: 1px solid rgba(78, 48, 67, 0.8);
   background: rgba(0, 0, 0, 0.15);
   border-radius: 2px;
   box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2), inset 0 1px 1px
      rgba(0, 0, 0, 0.1);
   -webkit-transition: all 0.3s ease-out;
   -moz-transition: all 0.3s ease-out;
   -ms-transition: all 0.3s ease-out;
   -o-transition: all 0.3s ease-out;
   transition: all 0.3s ease-out;
   /* Font styles */
   font-family: 'Raleway', 'Lato', Arial, sans-serif;
   color: #fff;
   font-size: 13px;
}

.form-4 input::-webkit-input-placeholder {
   color: rgba(37, 21, 26, 0.5);
   text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input:-moz-placeholder {
   color: rgba(37, 21, 26, 0.5);
   text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input:-ms-input-placeholder {
   color: rgba(37, 21, 26, 0.5);
   text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input[type=text]:hover, .form-4 input[type=password]:hover {
   border-color: #333;
}

.form-4 input[type=text]:focus, .form-4 input[type=password]:focus,
   .form-4 input[type=submit]:focus {
   box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2), inset 0 1px 1px
      rgba(0, 0, 0, 0.1), 0 0 0 3px rgba(255, 255, 255, 0.15);
   outline: none;
}

/* Fallback */
.no-boxshadow .form-4 input[type=text]:focus, .no-boxshadow .form-4 input[type=password]:focus
   {
   outline: 1px solid white;
}

.form-4 input[type=submit] {
   /* Size and position */
   width: 100%;
   padding: 8px 5px;
   /* Styles */
   background: linear-gradient(rgba(99, 64, 86, 0.5), rgba(76, 49, 65, 0.7));
   border-radius: 5px;
   border: 1px solid #4e3043;
   box-shadow: inset 0 1px rgba(255, 255, 255, 0.4), 0 2px 1px
      rgba(0, 0, 0, 0.1);
   cursor: pointer;
   transition: all 0.3s ease-out;
   /* Font styles */
   color: white;
   text-shadow: 0 1px 0 rgba(0, 0, 0, 0.3);
   font-size: 16px;
   font-weight: bold;
   font-family: 'Raleway', 'Lato', Arial, sans-serif;
}

.form-4 input[type=submit]:hover {
   box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), inset 0 20px 30px
      rgba(99, 64, 86, 0.5);
}

/* Fallback */
.no-boxshadow .form-4 input[type=submit]:hover {
   background: #594642;
}

.form-4 label {
   display: none;
   padding: 0 0 5px 2px;
   cursor: pointer;
}

.form-4 label:hover ~ input {
   border-color: #333;
}

.no-placeholder .form-4 label {
   display: block;
}

/**************************로그인_CSS_끝*******************************/
</style>

</head>

<!-- ***************************************************************** -->
<body class="" onLoad=auto();>
   <div class="wrapper ">


      <!-- Navbar -->
      <nav
         class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">

         <div class="navbar-wrapper">
            <a class="navbar-brand" href="#">孤独のグルメ</a>
         </div>

      </nav>
      <!-- End Navbar -->
      <!-- <div class="panel-header panel-header-sm">
  
  
</div> -->
      <div class="content">
         <div class="row" style = " margin: unset;">
            <div class="col-md-12" style = "padding: 0px; height: -webkit-fill-available;">
               <div class="card demo-icons">
                  <div class="card-header">
                     <h5 class="card-title"></h5>
                     <p class="card-category"></p>
                  </div>
                  <div class="card-body all-icons" style = "padding-top: 148px;">
                     <!-- *************************************슬라이드 _html_시작***************************************************** -->
                        <div id ="main_slide "  style = "position: absolute;   top:50%; left:50%; margin: 50px 0 0 -393.5px;">      
                           <div class="home__slider" style ="width: 800px; "> 
 
                               <div class="bxslider" > 
                            
                                   <div><img src="index_slide_img/beefpillaff.png" alt="그림1"></div>
                                   <div><img src="index_slide_img/chikenomulet.png" alt="그림2"></div>
                                   <div><img src="index_slide_img/culet&curry.png" alt="그림3"></div>
                                   <div><img src="index_slide_img/cutlet&omulet.png" alt="그림4"></div>
                            
                               </div> 
                           </div>
                        </div>      
                     <!-- ****************************************슬라이드_html_끝****************************************************** -->

                     <!-- *************************************로그인_시작***************************************************** -->
                     
                     <div id="login" style = " position: absolute; width: 200px; height: 200px; top: 50%; left: 50%; margin: 150px 0 0 -100px;">
                        <form action="login.okawari" method=post class="form-4">

                           <input class="box_style" type="text" name="user_id"
                              placeholder="ID" /><br /> <br /> <input class="box_style"
                              type=password name="user_pass" placeholder="Password" /><br />
                           <br /> <input type=submit value="ログイン"
                              style="margin-right: 15px;" />

                        </form>
                     </div>
                     <!-- *************************************로그인_끝***************************************************** -->

                  </div>
               </div>
            </div>
         </div>
      </div>
      <footer class="foot" style ="text-align: center; bottom: 0px; position: fixed;">
         <div class="credits ml-auto">
                  <span class="copyright" style = "position: relative; "> © <script>
                     document.write(new Date().getFullYear())
                  </script>, made with <i class="fa fa-heart heart"></i> Okawari

                  </span>
         </div>
      </footer>
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


   <script>
      function SelectText(element) {
         var doc = document, text = element, range, selection;
         if (doc.body.createTextRange) {
            range = document.body.createTextRange();
            range.moveToElementText(text);
            range.select();
         } else if (window.getSelection) {
            selection = window.getSelection();
            range = document.createRange();
            range.selectNodeContents(text);
            selection.removeAllRanges();
            selection.addRange(range);
         }
      }
      window.onload = function() {
         var iconsWrapper = document.getElementById('icons-wrapper'), listItems = iconsWrapper
               .getElementsByTagName('li');
         for (var i = 0; i < listItems.length; i++) {
            listItems[i].onclick = function fun(event) {
               var selectedTagName = event.target.tagName.toLowerCase();
               if (selectedTagName == 'p' || selectedTagName == 'em') {
                  SelectText(event.target);
               } else if (selectedTagName == 'input') {
                  event.target.setSelectionRange(0,
                        event.target.value.length);
               }
            }

            var beforeContentChar = window.getComputedStyle(
                  listItems[i].getElementsByTagName('i')[0], '::before')
                  .getPropertyValue('content').replace(/'/g, "").replace(
                        /"/g, ""), beforeContent = beforeContentChar
                  .charCodeAt(0).toString(16);
            var beforeContentElement = document.createElement("em");
            beforeContentElement.textContent = "\\" + beforeContent;
            listItems[i].appendChild(beforeContentElement);

            //create input element to copy/paste chart
            var charCharac = document.createElement('input');
            charCharac.setAttribute('type', 'text');
            charCharac.setAttribute('maxlength', '1');
            charCharac.setAttribute('readonly', 'true');
            charCharac.setAttribute('value', beforeContentChar);
            listItems[i].appendChild(charCharac);
         }
      }}
      
   </script>
</body>

</html>