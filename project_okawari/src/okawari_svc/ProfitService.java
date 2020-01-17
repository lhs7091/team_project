package okawari_svc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.SalesBranchDTO;

public class ProfitService {
	OkawariDAO dao = new OkawariDAO();

	// 정산 내용 비어있으면 새로운 값 넣기
	public boolean branchProfitInsert(SalesBranchDTO dto, int commission) {
		// 지점 매출 정보 넣기
		boolean check = false;
		check = dao.branchProfitInsert(dto, commission);

		return check;
	}

	// 하루 날짜에 대한 내용보기
	public SalesBranchDTO branchProfitSelect(String day6, String day, String user_id) {
		// 지점 매출 정보 추려오기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date selectDay = null;
		Date test = null;
		SalesBranchDTO dto = null;
		try {

			selectDay = new Date(sdf.parse(day).getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto = dao.orderCustomerAllSelect(day6, selectDay, user_id);

		return dto;
	}

	// 기간 사이의 정산 값들 불러오기,
	public ArrayList<SalesBranchDTO> selectUserProfitEachDate(String user_id, Date start, Date end) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<SalesBranchDTO> list = dao.selectSalesBranch(start, end, user_id);

		return list;
	}

	// 정산 내용이 있으면 업데이트 해주는 곳
	public boolean branchProfitUpdate(SalesBranchDTO salesDTO, int commision) {
		OkawariDAO dao = new OkawariDAO();
		boolean check = false;
		check = dao.branchProfitUpdate(salesDTO, commision);

		return check;

	}

	// 정산 내용 값 총합하기
	public SalesBranchDTO summitProfit(ArrayList<SalesBranchDTO> salesList) {
		SalesBranchDTO dto = new SalesBranchDTO();

		// 값을 누적하기
		for (int i = 0; i < salesList.size(); i++) {
			dto.setSales_branch_id(salesList.get(i).getSales_branch_id());
			dto.setSales_cost(dto.getSales_cost() + salesList.get(i).getSales_cost());
			dto.setSales_total(dto.getSales_total() + salesList.get(i).getSales_total());
			dto.setSales_origin(dto.getSales_origin() + salesList.get(i).getSales_origin());
			dto.setSales_date(dto.getSales_date());
			dto.setSales_commission(dto.getSales_commission() + salesList.get(i).getSales_commission());
		}
		// 커미션 평균치 구하기
		dto.setSales_commission(dto.getSales_commission() / salesList.size());

		return dto;
	}

}