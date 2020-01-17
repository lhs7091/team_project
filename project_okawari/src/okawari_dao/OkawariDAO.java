package okawari_dao;

import static okawari_db.JdbcUtils.close;
import static okawari_db.JdbcUtils.commit;
import static okawari_db.JdbcUtils.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okawari_model.CartDTO;
import okawari_model.DeliveryDTO;
import okawari_model.MenuDTO;
import okawari_model.OrderBranchDTO;
import okawari_model.OrderDTO;
import okawari_model.SalesBranchDTO;
import okawari_model.SalesHeadDTO;
import okawari_model.StockBranchDTO;
import okawari_model.StockHeadDTO;
import okawari_model.UserDTO;

public class OkawariDAO {
	private GetDateTime getDateTime = new GetDateTime();

	public UserDTO login(String user_id, String user_pass) {
		String sql = "select * from okwr_user where user_id=? and user_pass=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new UserDTO(user_id, user_pass, rs.getInt("user_auth"), rs.getString("user_name"),
						rs.getString("user_manager"), rs.getString("user_addr"), rs.getString("user_phone"),
						rs.getString("user_businum"), rs.getInt("user_commission"), rs.getString("user_opendate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}

	// 지점 재고 조회
	// 1.고객주문메뉴에서 본사가 지정해둔 메뉴와 지점창고에 담긴 재고를 파악해 판매가능인지 불가인 파악하기 위해 사용
	// 2.고객주문시 고객이 주문한 수량만큼 있는지 확인함.
	public ArrayList<StockBranchDTO> StockBranchList(String stock_branch_id) {
		String sql = "select * from okwr_stock_branch where stock_branch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StockBranchDTO> list = new ArrayList<StockBranchDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stock_branch_id.substring(0, stock_branch_id.length() - 1));
			rs = pstmt.executeQuery();

			while (rs.next()) {

				StockBranchDTO dto = new StockBranchDTO();
				dto.setStock_branch_id(rs.getString("stock_branch_id"));
				dto.setStock_menu_num(rs.getInt("stock_menu_num"));
				dto.setStock_count(rs.getInt("stock_count"));
				dto.setStock_cost(rs.getInt("stock_cost"));
				dto.setStock_price(rs.getInt("stock_price"));
				dto.setStock_expiredate(rs.getString("stock_expiredate"));
				dto.setStock_junk(rs.getString("stock_junk"));

				list.add(dto);

			}
			// 유통기한 날짜로 정렬시키기 위해 컬렉션소트 이용. 유통기한이 가장 오래된 순부터
			Collections.sort(list, new Comparator<StockBranchDTO>() {
				@Override
				public int compare(StockBranchDTO a, StockBranchDTO b) {
					// TODO Auto-generated method stub
					return a.getStock_expiredate().compareTo(b.getStock_expiredate());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;

	}

	// 본사 재고 조회
	public ArrayList<StockHeadDTO> StockHeadList(String stock_head_id) {
		String sql = "select * from okwr_stock_head where stock_head_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StockHeadDTO> list = new ArrayList<StockHeadDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stock_head_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				StockHeadDTO dto = new StockHeadDTO();
				dto.setStock_head_id(rs.getString("stock_head_id"));
				dto.setStock_menu_num(rs.getInt("stock_menu_num"));
				dto.setStock_count(rs.getInt("stock_count"));
				dto.setStock_cost(rs.getInt("stock_cost"));
				dto.setStock_price(rs.getInt("stock_price"));
				dto.setStock_expiredate(rs.getString("stock_expiredate"));
				dto.setStock_junk(rs.getString("stock_junk"));

				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	// 유저 권한 체크
	public boolean authorityCheck(int user_auth) {
		String sql = "select * from okwr_user where user_auth=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_auth);
			rs = pstmt.executeQuery();

			if (user_auth == 1) {
				check = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return check;
	}

	// 모든 유저 아이디 조회

	public ArrayList<UserDTO> showAllUser() {
		String sql = "select user_id from okwr_user ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String id = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
				UserDTO dto = new UserDTO();
				dto.setUser_id(id);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public String selectStockId(String user_id) {
		String sql = "select user_id from okwr_user where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("user_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return id;
	}

	public int selectLastOrderNum() {
		String sql = "select max(order_num) from okwr_order_branch";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int lastOrderNum = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastOrderNum = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return lastOrderNum;
	}

	public int selctMenuPrice(int order_menu_num) {
		String sql = "select menu_price from okwr_menu where menu_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int menu_price = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_menu_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				menu_price = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return menu_price;
	}

	public int insertOrderBranch(OrderBranchDTO dto) {
		String sql = "insert into okwr_order_branch" + "(order_num, order_branch_id, order_date, order_time, "
				+ "order_menu_num, order_count, order_perprice, order_totprice)" + "values(?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getOrder_num());
			pstmt.setString(2, dto.getOrder_branch_id());
			pstmt.setString(3, getDateTime.getDate());
			pstmt.setString(4, getDateTime.getTime());
			pstmt.setInt(5, dto.getOrder_menu_num());
			pstmt.setInt(6, dto.getOrder_count());
			pstmt.setInt(7, dto.getOrder_perprice());
			pstmt.setInt(8, dto.getTotprice());
			insertResult = pstmt.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return insertResult;
	}

	public ArrayList<OrderBranchDTO> selectOrderBranchList() {
		String sql = "select * from okwr_order_branch order by order_date, order_time";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderBranchDTO dto = null;
		ArrayList<OrderBranchDTO> list = new ArrayList<OrderBranchDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new OrderBranchDTO(rs.getInt("order_num"), rs.getString("order_branch_id"),
						rs.getString("order_date"), rs.getString("order_time"), rs.getInt("order_menu_num"),
						rs.getInt("order_count"), rs.getInt("order_perprice"), rs.getInt("order_totprice"),
						rs.getString("order_complete_branch"), rs.getString("order_complete_branch_date"),
						rs.getString("order_complete_branch_time"), rs.getString("order_complete_head"),
						rs.getString("order_complete_date"), rs.getString("order_complete_time"),
						rs.getString("order_expiredate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	// 각 지점의 본사 주문 리스트를 갖고 온다.
	public ArrayList<OrderBranchDTO> selectEachOrderBranchList(String user_id) {
		String sql = "select * from okwr_order_branch where order_branch_id=? order by order_date, order_time";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderBranchDTO dto = null;
		ArrayList<OrderBranchDTO> list = new ArrayList<OrderBranchDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new OrderBranchDTO(rs.getInt("order_num"), rs.getString("order_branch_id"),
						rs.getString("order_date"), rs.getString("order_time"), rs.getInt("order_menu_num"),
						rs.getInt("order_count"), rs.getInt("order_perprice"), rs.getInt("order_totprice"),
						rs.getString("order_complete_branch"), rs.getString("order_complete_branch_date"),
						rs.getString("order_complete_branch_time"), rs.getString("order_complete_head"),
						rs.getString("order_complete_date"), rs.getString("order_complete_time"),
						rs.getString("order_expiredate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	// 메뉴 리스트 빼오기
	public List<MenuDTO> menuList(String check) { // mr. oh
		// String sql = "select distinct m.menu_num, m.menu_name, m.menu_image,
		// m.menu_price,"
		// + " (select sum(stock_count) from okwr_stock_branch"
		// + " where stock_menu_num = m.menu_num) from okwr_stock_branch s, okwr_menu m
		// where s.stock_menu_num = m.menu_num"
		// + " and m.menu_check = ? and s.stock_count > 0";
		// String sql = "select * from okwr_menu where menu_check=?";

		String sql = "select distinct m.menu_num, m.menu_name, m.menu_image, m.menu_price,"
				+ " (select sum(stock_count) from okwr_stock_branch where stock_menu_num = m.menu_num) from okwr_stock_branch s, okwr_menu m where m.menu_check = ? order by m.menu_num asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MenuDTO> list = new ArrayList<MenuDTO>();
		Connection conn = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, check);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MenuDTO menu = new MenuDTO();
				menu.setMenu_num(rs.getInt(1));
				menu.setMenu_name(rs.getString(2));
				menu.setMenu_image(rs.getString(3));
				menu.setMenu_price(rs.getInt(4));
				menu.setMenu_count(rs.getInt(5));
				// MenuDTO menu = new MenuDTO();
				// menu.setMenu_num(rs.getInt("menu_num"));
				// menu.setMenu_name(rs.getString("menu_name"));
				// menu.setMenu_image(rs.getString("menu_image"));
				// menu.setMenu_price(rs.getInt("menu_price"));
				if (menu.getMenu_count() == 0) {
					menu.setMenu_image("soldout.gif");
				}
				list.add(menu);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	public int findNumberCheck() { // 날짜로 불러서 , 주문 카운트 값 가져오기
		// max(값) 함수 : 가장 높은값 가져오기 // mr.oh
		String sql = "select max(order_num) from okwr_order_customer where order_date = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int order_Num = 0;
		Connection conn = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getDateTime.getDate());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_Num = rs.getInt(1);
				System.out.println(order_Num + "番目の注文です。");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return order_Num;

	}

	// 고객이 주문한 내역을 DB에 저장
	// findNumberCheck() 메서드를 사용하여 주문번호를 부여받아 넣음.
	public int insertOrderCustomer(List<OrderDTO> customerOrderList) { // mr.oh
		String sql = "insert into okwr_order_customer values(?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
		boolean check = false;
		PreparedStatement pstmt = null;
		int order_Count = findNumberCheck() + 1;
		Connection conn = null;
		int insertSuccess = -1;

		try {
			conn = getConnection();
			for (int i = 0; i < customerOrderList.size(); i++) {
				System.out.println(customerOrderList.get(i).getOrder_menu_name().length());
				System.out.println(customerOrderList.get(i).getOrder_menu_name());
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, order_Count);
				pstmt.setString(2, customerOrderList.get(i).getOrder_branch_id());
				pstmt.setString(3, getDateTime.getDate());
				pstmt.setString(4, getDateTime.getTime());
				pstmt.setInt(5, customerOrderList.get(i).getOrder_menu_num());
				pstmt.setString(6, customerOrderList.get(i).getOrder_menu_name());
				pstmt.setInt(7, customerOrderList.get(i).getOrder_count());
				pstmt.setInt(8, customerOrderList.get(i).getOrder_perprice());
				pstmt.setInt(9, customerOrderList.get(i).getOrder_oriprice());
				pstmt.setInt(10, customerOrderList.get(i).getOrder_totprice());

				insertSuccess = pstmt.executeUpdate();

			}

			if (insertSuccess > 0) {
				insertSuccess = order_Count;
				commit(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return insertSuccess;
	}

	// 487번 C 없앰
	// 고객주문취소
	// 주문취소 고객이 주문한 내역을 날짜와 주문번호를 입력하여 DB 내역을 찾아 삭제한다.
	public boolean cancleOrderCustomer(String user_id, String day, int orderNumber) { // mr.oh
		String sql = "delete okwr_order_customer where order_num = ? and order_date = ? and order_branch_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteSuccess = -1;
		boolean check = false;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNumber);
			pstmt.setString(2, day);
			pstmt.setString(3, user_id);
			deleteSuccess = pstmt.executeUpdate();

			if (deleteSuccess > 0) {
				check = true;
				commit(conn);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return check;
	}

	// 여기 id 부분 바꿈
	// 고객 주문 내역이 DB에 입력이 되면 지점창고 Table의 내역도 차감시키기 위해 구현
	public boolean decOrderStockBranch(String stock_branch_id, List<StockBranchDTO> stockCheckList) {
		boolean check = false;
		String sql = "update okwr_stock_branch set stock_count=? where stock_menu_num = ? and stock_branch_id = ? and stock_expiredate = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {

			for (int i = 0; i < stockCheckList.size(); i++) {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, stockCheckList.get(i).getStock_count());
				pstmt.setInt(2, stockCheckList.get(i).getStock_menu_num());
				pstmt.setString(3, stock_branch_id.substring(0, stock_branch_id.length() - 1));
				pstmt.setString(4, stockCheckList.get(i).getStock_expiredate());
				result = pstmt.executeUpdate();
				if (result > 0) {
					commit(conn);
					if (stockCheckList.size() == (i + 1)) {
						check = true;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return check;
	}

	// 카트에 담을 메뉴의 정보를 불러보아요..
	public CartDTO selectCartMenu(int menu_num) { // mr .oh
		String sql = "select * from okwr_menu where menu_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menu_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new CartDTO(rs.getInt("menu_num"), rs.getString("menu_image"), rs.getString("menu_name"),
						rs.getInt("menu_price"), 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}

	public int updateCompleteOrderHead(String user_id, int order_num, String order_expired_date) {
		String sql = "update okwr_order_branch set order_complete_head='y',order_complete_date=?, order_complete_time=?, order_expiredate=? where order_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getDateTime.getDate());
			pstmt.setString(2, getDateTime.getTime());
			pstmt.setString(3, order_expired_date);
			pstmt.setInt(4, order_num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return result;

	}

	public int updateCompleteOrderBranch(String user_id, int order_num) {
		String sql = "update okwr_order_branch set order_complete_branch='y', order_complete_branch_date=?, order_complete_branch_time=? where order_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getDateTime.getDate());
			pstmt.setString(2, getDateTime.getTime());
			pstmt.setInt(3, order_num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return result;
	}

	public OrderBranchDTO selectOneOrderNumber(int order_num) {
		String sql = "select * from okwr_order_branch where order_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderBranchDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new OrderBranchDTO(rs.getInt("order_num"), rs.getString("order_branch_id"),
						rs.getString("order_date"), rs.getString("order_time"), rs.getInt("order_menu_num"),
						rs.getInt("order_count"), rs.getInt("order_perprice"), rs.getInt("order_totprice"),
						rs.getString("order_complete_branch"), rs.getString("order_complete_branch_date"),
						rs.getString("order_complete_branch_time"), rs.getString("order_complete_head"),
						rs.getString("order_complete_date"), rs.getString("order_complete_time"),
						rs.getString("order_expiredate"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}

	public int selectTotalStock(int order_menu_num) {
		String sql = "select sum(stock_count) from okwr_stock_head where stock_menu_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalStock = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_menu_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalStock = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return totalStock;
	}

	//
	public ArrayList<StockHeadDTO> StockHeadList(int order_menu_num) {
		String sql = "select * from okwr_stock_head where stock_menu_num=? order by stock_expiredate";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StockHeadDTO dto = null;
		ArrayList<StockHeadDTO> list = new ArrayList<StockHeadDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_menu_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				dto = new StockHeadDTO();
				dto.setStock_head_id(rs.getString("stock_head_id"));
				dto.setStock_menu_num(rs.getInt("stock_menu_num"));
				dto.setStock_count(rs.getInt("stock_count"));
				dto.setStock_cost(rs.getInt("stock_cost"));
				dto.setStock_price(rs.getInt("stock_price"));
				dto.setStock_expiredate(rs.getString("stock_expiredate"));
				dto.setStock_junk(rs.getString("stock_junk"));

				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	// 본사의 재고 수량을 감소시켜줍니다.
	public void updateStockCountHead(int stock_menu_num, int stock_count, String stock_expiredate) {
		String sql = "update okwr_stock_head set stock_count=? where stock_menu_num=? and stock_expiredate=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock_count);
			pstmt.setInt(2, stock_menu_num);
			pstmt.setString(3, stock_expiredate);
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	// 본사의 재고를 삭제합니다.
	public void deleteStockHead(int stock_menu_num, String stock_expiredate) {
		String sql = "delete okwr_stock_head where stock_menu_num=? and stock_expiredate=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock_menu_num);
			pstmt.setString(2, stock_expiredate);
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	// 본사로 부터 받은 재고를 지사의 정보로 삽입합니다.
	public int updateStockBranch(DeliveryDTO deliveryDTO) {
		String sql = "update okwr_stock_branch set stock_count=stock_count+? "
				+ "where stock_branch_id=? and stock_menu_num=? and stock_expiredate=? ";
		// String sql = "insert into okwr_stock_branch values(?,?,?,?,?,?,'n')";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deliveryDTO.getStock_count());
			pstmt.setString(2, deliveryDTO.getStock_branch_id());
			pstmt.setInt(3, deliveryDTO.getStock_menu_num());
			pstmt.setString(4, deliveryDTO.getStock_expiredate());
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return result;
	}

	public int insertStockBranch(DeliveryDTO deliveryDTO) {
		String sql = "insert into okwr_stock_branch values(?,?,?,?,?,?,'n')";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deliveryDTO.getStock_branch_id());
			pstmt.setInt(2, deliveryDTO.getStock_menu_num());
			pstmt.setInt(3, deliveryDTO.getStock_count());
			pstmt.setInt(4, deliveryDTO.getStock_cost());
			pstmt.setInt(5, deliveryDTO.getStock_price());
			pstmt.setString(6, deliveryDTO.getStock_expiredate());
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return result;
	}

	// 배달할 품목을 Delivery table에 임시로 저장합니다.
	public void insertDelivery(DeliveryDTO deliveryDTO) {

		String sql = "insert into okwr_stock_delivery values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deliveryDTO.getOrder_num());
			pstmt.setString(2, deliveryDTO.getStock_branch_id());
			pstmt.setInt(3, deliveryDTO.getStock_menu_num());
			pstmt.setInt(4, deliveryDTO.getStock_count());
			pstmt.setInt(5, deliveryDTO.getStock_cost());
			pstmt.setInt(6, deliveryDTO.getStock_price());
			pstmt.setString(7, deliveryDTO.getStock_expiredate());
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	public ArrayList<DeliveryDTO> selectDeliveryList(int order_num) {
		// String stock_branch_id, int stock_menu_num, int stock_count, int stock_cost,
		// int stock_price,
		// String stock_expiredate
		String sql = "select * from okwr_stock_delivery where order_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DeliveryDTO dto = null;
		ArrayList<DeliveryDTO> list = new ArrayList<DeliveryDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new DeliveryDTO(rs.getString("stock_branch_id"), rs.getInt("stock_menu_num"),
						rs.getInt("stock_count"), rs.getInt("stock_cost"), rs.getInt("stock_price"),
						rs.getString("stock_expiredate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	// delivery talbe에 해당 주문번호를 지운다.
	public void deleteDeliveryList(int order_num) {
		String sql = "delete okwr_stock_delivery where order_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	public ArrayList<StockHeadDTO> selectAllStockHead() {
		String sql = "select * from okwr_stock_head";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StockHeadDTO> list = new ArrayList<StockHeadDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StockHeadDTO(rs.getString("stock_head_id"), rs.getInt("stock_menu_num"),
						rs.getInt("stock_count"), rs.getInt("stock_cost"), rs.getInt("stock_price"),
						rs.getString("stock_expiredate"), rs.getString("stock_junk")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	// 본사재고 증가(메뉴번호하고 유통기한이 일치하는 경우)
	public int updateAddStockHead(StockHeadDTO stockHeadDTO) {
		String sql = "update okwr_stock_head set stock_count=stock_count+? where stock_menu_num=? and stock_expiredate=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stockHeadDTO.getStock_count());
			pstmt.setInt(2, stockHeadDTO.getStock_menu_num());
			pstmt.setString(3, stockHeadDTO.getStock_expiredate());
			updateResult = pstmt.executeUpdate();
			if (updateResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return updateResult;

	}

	public int insertAddStockHead(StockHeadDTO stockHeadDTO) {
		String sql = "insert into okwr_stock_head values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stockHeadDTO.getStock_head_id());
			pstmt.setInt(2, stockHeadDTO.getStock_menu_num());
			pstmt.setInt(3, stockHeadDTO.getStock_count());
			pstmt.setInt(4, stockHeadDTO.getStock_cost());
			pstmt.setInt(5, stockHeadDTO.getStock_price());
			pstmt.setString(6, stockHeadDTO.getStock_expiredate());
			pstmt.setString(7, stockHeadDTO.getStock_junk());
			insertResult = pstmt.executeUpdate();
			System.out.println(insertResult);
			if (insertResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return insertResult;
	}

	public int insertBranch(UserDTO dto) {
		String sql = "insert into okwr_user values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pass());
			pstmt.setInt(3, dto.getUser_auth());
			pstmt.setString(4, dto.getUser_name());
			pstmt.setString(5, dto.getUser_manager());
			pstmt.setString(6, dto.getUser_addr());
			pstmt.setString(7, dto.getUser_phone());
			pstmt.setString(8, dto.getUser_businum());
			pstmt.setInt(9, dto.getUser_commission());
			pstmt.setString(10, dto.getUser_opendate());
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return insertResult;
	}

	public ArrayList<UserDTO> selectAllUser() {
		String sql = "select * from okwr_user where user_auth=1 or user_auth=2 order by user_auth, user_opendate";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO(rs.getString("user_id"), rs.getString("user_pass"), rs.getInt("user_auth"),
						rs.getString("user_name"), rs.getString("user_manager"), rs.getString("user_addr"),
						rs.getString("user_phone"), rs.getString("user_businum"), rs.getInt("user_commission"),
						rs.getString("user_opendate"));
				userList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return userList;

	}

	public UserDTO selectOneUser(String user_id) {
		String sql = "select * from okwr_user where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new UserDTO(rs.getString("user_id"), rs.getString("user_pass"), rs.getInt("user_auth"),
						rs.getString("user_name"), rs.getString("user_manager"), rs.getString("user_addr"),
						rs.getString("user_phone"), rs.getString("user_businum"), rs.getInt("user_commission"),
						rs.getString("user_opendate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return dto;

	}

	public int updateOneUser(UserDTO dto) {
		String sql = "update okwr_user set user_pass=?, user_auth=?, user_name=?, user_manager=?, user_addr=?, "
				+ "user_phone=?, user_businum=?, user_commission=?,	user_opendate=? where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_pass());
			pstmt.setInt(2, dto.getUser_auth());
			pstmt.setString(3, dto.getUser_name());
			pstmt.setString(4, dto.getUser_manager());
			pstmt.setString(5, dto.getUser_addr());
			pstmt.setString(6, dto.getUser_phone());
			pstmt.setString(7, dto.getUser_businum());
			pstmt.setInt(8, dto.getUser_commission());
			pstmt.setString(9, dto.getUser_opendate());
			pstmt.setString(10, dto.getUser_id());
			updateResult = pstmt.executeUpdate();
			if (updateResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return updateResult;
	}

	public int removeUser(String user_id) {
		String sql = "update okwr_user set user_auth=4 where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int removeResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			removeResult = pstmt.executeUpdate();
			if (removeResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return removeResult;
	}

	// sales_branch_id varchar2(20), -- 판매지점
	// sales_date varchar2(20), -- 판매일
	// sales_total number, -- 판매총액
	// sales_cost number, -- 판매원가
	// sales_junk number, -- 폐기한 물품의 원가
	// sales_commission number, --
	// sales_origin number -- 순이익
	public ArrayList<SalesHeadDTO> selectSalesHeadEachDate(Date start, Date end) {
		String sql = "select sum(sales_total), sum(sales_cost), sum(sales_junk), "
				+ "sum(sales_commission), sum(sales_origin), sales_date from okwr_sales_head where sales_date between ? and ? GROUP BY sales_date";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesHeadDTO dto = null;
		ArrayList<SalesHeadDTO> salesList = new ArrayList<SalesHeadDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, start);
			pstmt.setDate(2, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SalesHeadDTO();
				dto.setSales_total(rs.getInt(1));
				dto.setSales_cost(rs.getInt(2));
				dto.setSales_junk(rs.getInt(3));
				dto.setSales_commission(rs.getInt(4));
				dto.setSales_origin(rs.getInt(5));
				dto.setSales_date(rs.getDate(6));
				salesList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return salesList;
	}

	public ArrayList<SalesHeadDTO> selectSalesBranchEachDate(Date start, Date end, String user_id) {
		String sql = "select * from okwr_sales_head where sales_branch_id=? and sales_date between ? and ? order by sales_date";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesHeadDTO dto = null;
		ArrayList<SalesHeadDTO> salesList = new ArrayList<SalesHeadDTO>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setDate(2, start);
			pstmt.setDate(3, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SalesHeadDTO(rs.getString("sales_branch_id"), rs.getDate("sales_date"),
						rs.getInt("sales_total"), rs.getInt("sales_cost"), rs.getInt("sales_junk"),
						rs.getInt("sales_commission"), rs.getInt("sales_origin"));

				salesList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return salesList;
	}

	// 각 지사로 물품 발주 후 매출 등록
	public void insertSalesHead(String order_branch_id, String date) {
		String sql = "insert into okwr_sales_head values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_branch_id);
			pstmt.setString(2, date);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
	}

	public boolean checkSalesHead(String order_branch_id, Date date) {
		String sql = "select * from okwr_sales_head where sales_branch_id=? and sales_date=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = true;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_branch_id);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return check;
	}

	public void updateSalesHead(SalesHeadDTO shd) {
		String sql = "update okwr_sales_head set sales_total=sales_total+?, sales_cost=sales_cost+? "
				+ "where sales_branch_id=? and sales_date=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shd.getSales_total());
			pstmt.setInt(2, shd.getSales_cost());
			pstmt.setString(3, shd.getSales_branch_id());
			pstmt.setDate(4, shd.getSales_date());
			updateResult = pstmt.executeUpdate();
			if (updateResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	// 입력받은 날짜를 토대로 정산된 내역을 불러오기
	public ArrayList<SalesBranchDTO> selectUserProfitList(String user_id, String startDate, String endDate) {
		String sql = "select sum(order_perprice), sum(order_oriprice) from okwr_order_customer where order_date = ? and order_branch_id = ? order by order_menu_num";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesBranchDTO dto = null;
		ArrayList<SalesBranchDTO> salesList = new ArrayList<SalesBranchDTO>();
		int commission = this.userCommission(user_id); // 커미션 받아오기

		long dateCount = (getDateTime.getMilisecond(endDate) - getDateTime.getMilisecond(startDate))
				/ getDateTime.getOneDay();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			while (true) {
				pstmt.setString(1, startDate);
				pstmt.setString(2, user_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					dto = new SalesBranchDTO();
					dto.setSales_branch_id(user_id);
					dto.setSales_cost(1);
					dto.setSales_total(2);
					dto.setSales_commission(commission);
					salesList.add(dto);
				}
				// 시작날짜에서 하루 더하고..
				startDate = getDateTime.getDateString(getDateTime.getMilisecond(startDate) + getDateTime.getOneDay());
				// 더한날짜가 마지막 날짜를 지나면 while 빠져나감.
				if (getDateTime.getMilisecond(startDate) > getDateTime.getMilisecond(endDate)) {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return salesList;

	}

	/// 정산 및 매출내역을 확인 및 순이익을 구하기위한 커미션 내용을 부르기 위한, 메서드
	public int userCommission(String user_id) {

		int commission = 0;
		String sql = "select user_commission from okwr_user where user_id = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				commission = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return commission;
	}

	// 날짜별 내역을 정산 내역을 확인 후 DB 저장시 쓰는 메서드
	public boolean branchProfitInsert(SalesBranchDTO dto, int commission) {
		String sql = "insert into okwr_sales_branch values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		boolean check = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSales_branch_id());
			pstmt.setDate(2, dto.getSales_date());
			pstmt.setInt(3, dto.getSales_total());
			pstmt.setInt(4, dto.getSales_cost());
			pstmt.setInt(5, dto.getSales_total()*commission/100);
			pstmt.setInt(6, dto.getSales_origin());
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return check;
	}

	// 정산입력시 내역이 있으면 그 내역에 업데이트 시키기
	public boolean branchProfitUpdate(SalesBranchDTO dto, int commission) {
		String sql = "update okwr_sales_branch set sales_total = ?, sales_cost = ?, sales_origin = ?, sales_commission=? where sales_branch_id = ? and sales_date = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		boolean check = false;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getSales_total());
			pstmt.setInt(2, dto.getSales_cost());
			pstmt.setInt(3, dto.getSales_origin());
			pstmt.setInt(4, dto.getSales_total()*commission/100);
			pstmt.setString(5, dto.getSales_branch_id());
			pstmt.setDate(6, dto.getSales_date());
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return check;
	}

	// 입력받은 날짜의 정산된 정보를 불러와 리스트를 출력하는 부분에서 내역내의 값의 총합의 값을 가져오기위한 메서드
	public SalesBranchDTO orderCustomerAllSelect(String day6, Date selectDay, String user_id) {
		String sql = "select sum(order_totprice), sum(order_oriprice) from okwr_order_customer where order_date = ? and order_branch_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesBranchDTO dto = null;

		int commission = this.userCommission(user_id); // 커미션 받아오기
		float commFloat = commission * 0.01f; // 커미션 소수화

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, day6);
			pstmt.setString(2, user_id + "c");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SalesBranchDTO();
				dto.setSales_total(rs.getInt(1));
				dto.setSales_cost(rs.getInt(2));
				// 순수 이익만
				if (dto.getSales_total() > 0) {
					// 순이익 계산해서 넣기
					int origin = dto.getSales_total() - dto.getSales_cost();
					int origintot = (int) (origin - ((Math.floor((origin * commFloat) * 10) / 10)));

					dto.setSales_origin(origintot);
					dto.setSales_date(selectDay);
					dto.setSales_commission(commission);
					dto.setSales_branch_id(user_id);
				} else {
					dto = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return dto;

	}

	// 주문내역 취소시 해당 주문번호를 불러오기 위한 메서드
	public ArrayList<OrderDTO> findCustomerOrder(String user_id, String day, int orderNumber) {
		String sql = "select * from okwr_order_customer where order_branch_id = ? and order_date = ? and order_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderDTO dto = null;
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id + "c");
			pstmt.setString(2, day);
			pstmt.setInt(3, orderNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new OrderDTO();
				dto.setOrder_branch_id(rs.getString("order_branch_id"));
				dto.setOrder_count(rs.getInt("order_count"));
				dto.setOrder_date(rs.getString("order_date"));
				dto.setOrder_menu_name(rs.getString("order_menu_name"));
				dto.setOrder_menu_num(rs.getInt("order_menu_num"));
				dto.setOrder_num(rs.getInt("order_num"));
				dto.setOrder_oriprice(rs.getInt("order_oriprice"));
				dto.setOrder_perprice(rs.getInt("order_perprice"));
				dto.setOrder_time(rs.getString("order_time"));
				dto.setOrder_totprice(rs.getInt("order_totprice"));
				orderList.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return orderList;

	}

	// 지점 두 날짜를 입력받아 해당 기간 사이의 전체적인 정산 내역리스트를 불러오기위한 메서드
	public ArrayList<SalesBranchDTO> selectSalesBranch(Date startDate, Date endDate, String user_id) {
		String sql = "select * from okwr_sales_branch where sales_branch_id=? and sales_date between ? and ? order by sales_date";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesBranchDTO dto = null;
		ArrayList<SalesBranchDTO> salesList = new ArrayList<SalesBranchDTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SalesBranchDTO(rs.getString("sales_branch_id"), rs.getDate("sales_date"),
						rs.getInt("sales_total"), rs.getInt("sales_cost"), rs.getInt("sales_commission"),
						rs.getInt("sales_origin"));
				salesList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return salesList;
	}

	// 지점별 고객 주문리스트를 부르기 위한 메서드
	   public List<MenuDTO> branchMenuList(String user_id, String check) {
	      String sql = "select distinct m.menu_num, m.menu_name, m.menu_image, m.menu_price,"
	            + " (select sum(stock_count) from okwr_stock_branch where stock_menu_num = m.menu_num and stock_branch_id = ?) from okwr_stock_branch s, okwr_menu m where m.menu_check = ? and s.stock_branch_id = ? order by m.menu_num asc";
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<MenuDTO> list = new ArrayList<MenuDTO>();
	      Connection conn = null;
	      System.out.println(user_id);
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, user_id);
	         pstmt.setString(2, check);
	         pstmt.setString(3, user_id);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            MenuDTO menu = new MenuDTO();
	            menu.setMenu_num(rs.getInt(1));
	            menu.setMenu_name(rs.getString(2));
	            menu.setMenu_image(rs.getString(3));
	            menu.setMenu_price(rs.getInt(4));
	            menu.setMenu_count(rs.getInt(5));
	            // MenuDTO menu = new MenuDTO();
	            // menu.setMenu_num(rs.getInt("menu_num"));
	            // menu.setMenu_name(rs.getString("menu_name"));
	            // menu.setMenu_image(rs.getString("menu_image"));
	            // menu.setMenu_price(rs.getInt("menu_price"));
	            if (menu.getMenu_count() == 0) {
	               menu.setMenu_image("soldout.gif");
	            }
	            list.add(menu);
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close(conn, pstmt, rs);
	      }

	      return list;
	   }

	// 마지막 메뉴번호 갖고와요
	public int getNextMenuNum() {
		String sql = "select MAX(menu_num) from okwr_menu";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}

	// 신규 메뉴 추가합니다.
	public int insertNewMenu(MenuDTO dto) {
		String sql = "insert into okwr_menu values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getMenu_num());
			pstmt.setString(2, dto.getMenu_name());
			pstmt.setInt(3, dto.getMenu_price());
			pstmt.setString(4, dto.getMenu_image());
			pstmt.setString(5, dto.getMenu_check());
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return insertResult;
	}

	public void insertCommission(String sales_branch_id, Date sales_date, int commission) {
		String sql = "insert into okwr_sales_head(sales_branch_id, sales_date, sales_commission) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sales_branch_id);
			pstmt.setDate(2, sales_date);
			pstmt.setInt(3, commission);
			insertResult = pstmt.executeUpdate();
			if (insertResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

	}

	public void updateCommission(String sales_branch_id, Date sales_date, int commission) {
		String sql = "update okwr_sales_head set sales_commission=? where sales_branch_id=? and sales_date=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commission);
			pstmt.setString(2, sales_branch_id);
			pstmt.setDate(3, sales_date);
			updateResult = pstmt.executeUpdate();
			if (updateResult > 0) {
				commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		

	}

}
