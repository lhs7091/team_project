package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.SalesBranchDTO;
import okawari_model.UserDTO;
import okawari_svc.ProfitService;
import okawari_svc.SalesHeadService;

public class UserProfitAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
      // userprofit 지점 매출현황 뽑아오기
      request.setCharacterEncoding("utf-8");
      ActionForward af = null;
      HttpSession session = request.getSession();
      UserDTO dto = (UserDTO) session.getAttribute("session");

      SalesBranchDTO salesDTO = null;
      ArrayList<SalesBranchDTO> salesList = null;

      ProfitService proService = new ProfitService();
      int open = 0;   // af 반환 주소값 하고 out.flush 와 부딪히는 것 같아, 설정 정확한 원인은 파악못함
      
      
      // 첫번째 날짜 매출보기 날짜가 입력되면 들어온다.
      if (request.getParameter("day") != null && request.getParameter("day") != "") {
         String day = request.getParameter("day");
         String day6 = day.substring(2).replace("-", "/");

         // 그날 매출 있는지 확인하기
         salesDTO = proService.branchProfitSelect(day6, day, dto.getUser_id());
         if (salesDTO != null) {
            request.setAttribute("salesDTO", salesDTO);
            return new ActionForward("userProfit.jsp", false);
            //바로 매출있는지 띄울수 있도록 해주기
         } else{
           open = 1;
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('해당 날짜 ご選択の" + day
                  + " 의 정보가 없습니다.の日付に関する情報がございません。');location.href='userProfit.okawari';");
            out.print("</script>");
            out.flush();
            
         }

      }

      // 일일 정산 올리기
      if (request.getParameter("dayUpdate") != null) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String day = request.getParameter("dayUpdate");
         String day6 = day.substring(2).replace("-", "/");
         Date dayUpdate = null;
         try {
            dayUpdate = new Date(sdf.parse(request.getParameter("dayUpdate")).getTime());
         } catch (Exception e) {
            // TODO: handle exception
         }
         // 해당 자료 있는 지 찾기
         salesDTO = proService.branchProfitSelect(day6, day, dto.getUser_id());
         if (salesDTO != null) {
            // 해당자료가 있는것 확인했으니 해당 자료를 담아오기
            salesList = proService.selectUserProfitEachDate(dto.getUser_id(), dayUpdate, dayUpdate);
            System.out.println("size = "+salesList.size());
            // 해당자료가 없으가 있으면 업데이트를 실시
            if (salesList.size() > 0) {
               response.setContentType("text/html; charset=utf-8");

               boolean check = proService.branchProfitUpdate(salesDTO, dto.getUser_commission());
               // commission 본사로 update 시키기
               SalesHeadService shs = new SalesHeadService();
               shs.updateCommission(salesDTO.getSales_branch_id(), salesDTO.getSales_date(), salesDTO.getSales_total()*dto.getUser_commission()/100);
              
               PrintWriter out = response.getWriter();
               out.print("<script>");
               out.print(
                     "alert('이미 정산 내역이 있습니다. 업데이트 하겠습니다.売り上げをアップデートします。');location.href='userProfit.okawari';");
               out.print("</script>");
               out.flush();
            } else {
               // 해당자료가 없으면 신규 값 넣기
               response.setContentType("text/html; charset=utf-8");
               boolean check = proService.branchProfitInsert(salesDTO, dto.getUser_commission());
               // commission 본사로 update 시키기
               SalesHeadService shs = new SalesHeadService();
               shs.updateCommission(salesDTO.getSales_branch_id(), salesDTO.getSales_date(), salesDTO.getSales_total()*dto.getUser_commission()/100);
               PrintWriter out = response.getWriter();
               out.print("<script>");
               out.print("alert('" + dayUpdate
                     + " 건의 정산 처리가 되었습니다.売り上げがアップデートできましあた。');location.href='userProfit.okawari';");
               out.print("</script>");
               out.flush();

            }
         } else {
           open = 1;
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('해당 날짜ご選択の " + day + " 의 정보가 없습니다.の情報がございません。');location.href='userProfit.okawari';");
            out.print("</script>");
            out.flush();
            // 그날 매출 있는지 확인하기
         }
      }

      // 두 날짜 사이의 정산된 정보 가져오기
      if (request.getParameter("start") != null && request.getParameter("end") != null) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date start = null;
         Date end = null;
         try {
            start = new Date(sdf.parse(request.getParameter("start")).getTime());
            end = new Date(sdf.parse(request.getParameter("end")).getTime());
         } catch (Exception e) {
            // TODO: handle exception
         }

         // 그날 매출 있는지 확인하기
         salesList = new ArrayList<SalesBranchDTO>();
         salesList = proService.selectUserProfitEachDate(dto.getUser_id(), start, end);


         if (salesList.size() > 0) {
             // 해당자료의 모든 총합하여 값을 종합하기
             salesDTO = proService.summitProfit(salesList);
            request.setAttribute("salesList", salesList);
            request.setAttribute("salesListTot", salesDTO);
            return af = new ActionForward("userProfit.jsp", false);
         } else {
            open = 1;
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('해당 날짜 ご選択の" + start + "から" + end
                  + " 間の情報がございません。사이의 정보가 없습니다.');location.href='userProfit.okawari';");
            out.print("</script>");
            out.flush();
         }
      }
      
      //af 담기는것과 flush와 부딪히지 않기위해서 설정해놓음. 정확한 원인은 못찾음.
      if(salesDTO == null && salesList == null && open == 0) {
          return new ActionForward("userProfit.jsp", false);
      }
      
//      return new ActionForward("userProfit.jsp", false);
      return null;
   }

}