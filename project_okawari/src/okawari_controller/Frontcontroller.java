package okawari_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_action.Action;
import okawari_action.AddMenuAction;
import okawari_action.AddMenuFormAction;
import okawari_action.AddStockHead;
import okawari_action.AddStockHeadFormAction;
import okawari_action.CustomerMenuListAction;
import okawari_action.CustomerMenuOrderAction;
import okawari_action.CustomerOrderCancle;
import okawari_action.LoginAction;
import okawari_action.LogoutAction;
import okawari_action.MainPageAction;
import okawari_action.OrderApproveAction;
import okawari_action.OrderBranchAction;
import okawari_action.OrderBranchFormAction;
import okawari_action.OrderBranchListAction;
import okawari_action.RegisterBranchAction;
import okawari_action.RegisterBranchFormAction;
import okawari_action.ShowHeadSalesAction;
import okawari_action.StockBranchAction;
import okawari_action.StockHeadAction;
import okawari_action.UserListAction;
import okawari_action.UserModifyAction;
import okawari_action.UserModifyFormAction;
import okawari_action.UserProfitAction;
import okawari_action.UserRemoveAction;
import okawari_model.ActionForward;

/**
 * Servlet implementation class Frontcontroller
 */
@WebServlet("*.okawari")
public class Frontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Frontcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward af = null;

		if (command.equals("/login.okawari")) {
			action = new LoginAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 로그인 하면 본사 또는 지점관리자가 첫페이지로 이동
		else if (command.equals("/mainPage.okawari")) {
			action = new MainPageAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 주문하기 각 지사가 -> 본사로..

		else if (command.equals("/orderBranchForm.okawari")) {
			action = new OrderBranchFormAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/okwr_order_branch.okawari")) {
			action = new OrderBranchAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/orderBranchList.okawari")) {
			action = new OrderBranchListAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/approve.okawari")) {
			action = new OrderApproveAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 고객 주문 ----
		else if (command.equals("/customerPage.okawari")) {
			// 고객메뉴리스트 (판매 메뉴들 불러오기, 장바구니 추가, 감산, 삭제)

			action = new CustomerMenuListAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/customerMenuOrder.okawari")) {
			// 고객 메뉴 주문 확인
			action = new CustomerMenuOrderAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 재고확인
		else if (command.equals("/showBranchStock.okawari")) {
			action = new StockBranchAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/showHeadStock.okawari")) {
			action = new StockHeadAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 본사 재고 추가하기
		else if (command.equals("/addStockHeadForm.okawari")) {
			action = new AddStockHeadFormAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/addStockHead.okawari")) {
			action = new AddStockHead();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 지점추가하기
		else if (command.equals("/registerBranchForm.okawari")) {
			action = new RegisterBranchFormAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/registerBranch.okawari")) {
			action = new RegisterBranchAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/userList.okawari")) {
			action = new UserListAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/userModifyForm.okawari")) {
			action = new UserModifyFormAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/userModify.okawari")) {
			action = new UserModifyAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/userRemove.okawari")) {
			action = new UserRemoveAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 본사 매출 조회
		else if (command.equals("/showHeadSales.okawari")) {
			action = new ShowHeadSalesAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/userProfit.okawari")) {
			action = new UserProfitAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/customerOrderCancle.okawari")) {
			action = new CustomerOrderCancle();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/logout.okawari")) {
			action = new LogoutAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/addMenuForm.okawari")) {
			action = new AddMenuFormAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/addMenu.okawari")) {
			action = new AddMenuAction();
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// step4. 응답처리
		if (af != null) {
			if (af.isRedirect()) {
				response.sendRedirect(af.getResUrl());
			} else {
				RequestDispatcher rdp = request.getRequestDispatcher(af.getResUrl());
				rdp.forward(request, response);
			}
		}

	}
}