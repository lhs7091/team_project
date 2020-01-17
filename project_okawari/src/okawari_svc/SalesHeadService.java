package okawari_svc;

import java.sql.Date;
import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.SalesHeadDTO;

public class SalesHeadService {

	public ArrayList<SalesHeadDTO> showSalesHead(Date start, Date end) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<SalesHeadDTO> salesList = dao.selectSalesHeadEachDate(start, end);
		return salesList;
	}

	public ArrayList<SalesHeadDTO> showSalesBranch(Date start, Date end, String user_id) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<SalesHeadDTO> salesList = dao.selectSalesBranchEachDate(start, end, user_id);
		return salesList;
	}

	public boolean checkSalesHead(String order_branch_id, Date date) {
		OkawariDAO dao = new OkawariDAO();
		boolean check = dao.checkSalesHead(order_branch_id, date);
		return check;
	}

	public void addSalesHead(String order_branch_id, String date) {
		OkawariDAO dao = new OkawariDAO();
		dao.insertSalesHead(order_branch_id, date);

	}

	public void updateSalesHead(SalesHeadDTO shd) {
		OkawariDAO dao = new OkawariDAO();
		dao.updateSalesHead(shd);

	}

	// commission update 하기..
	// 다음날 각 지점 매출 갱신되면 이거 갱신해주기..
	public void updateCommission(String sales_branch_id, Date sales_date, int commission) {
		// 만약에 해당 지점 id와 날짜가 존재한다면? update
		// 존재의 유무를 알기 위해서 먼저 조회를 한다. 조회 결과에 따라 있으면 update, 없으면 insert
		OkawariDAO dao = new OkawariDAO();
		boolean result = checkSalesHead(sales_branch_id, sales_date);
		System.out.println(result);
		if (result) {// 없을경우임, insert
			dao.insertCommission(sales_branch_id, sales_date, commission);
		} else {// 있을경우, update
			dao.updateCommission(sales_branch_id, sales_date, commission);
		}

	}

}
