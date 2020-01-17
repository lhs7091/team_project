package okawari_svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import okawari_dao.OkawariDAO;
import okawari_model.CartDTO;

public class AddCartService {

	public CartDTO getCartMenu(int menu_num) {
		OkawariDAO dao = new OkawariDAO();
		CartDTO dto = dao.selectCartMenu(menu_num);
		return dto;
	}

	// 주문 추가 및 수량 증가
	public ArrayList<CartDTO> addCart(CartDTO dto, HttpServletRequest request) {
		HttpSession cartSession = request.getSession();
		ArrayList<CartDTO> cartList = (ArrayList<CartDTO>) cartSession.getAttribute("cartList");

		if (cartList == null) {
			cartList = new ArrayList<CartDTO>();
			cartSession.setAttribute("cartList", cartList);
		}

		// 지금 장바구니에 담은 항목이 새롭게 추가된 항목인지를 판단할 필요가 있다.
		// 기존에 장바구니에 존재하는 제품이라면 수량을 증가시키면 되고, 그렇지 않다면
		// 새롭게 객체를 장바구니에 추가하는 작업이 필요하기 때문이다.
		boolean isNewCart = true;

		for (int i = 0; i < cartList.size(); i++) {
			if (dto.getMenu_num() == cartList.get(i).getMenu_num()) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
				break;
			}
		}

		if (isNewCart) {
			CartDTO cart = new CartDTO(dto.getMenu_num(), dto.getMenu_image(), dto.getMenu_name(), dto.getMenu_price(),
					1);
			cartList.add(cart);
		}

		return cartList;

	}

	public ArrayList<CartDTO> decCart(CartDTO dto, HttpServletRequest request) {
		HttpSession cartSession = request.getSession();
		ArrayList<CartDTO> cartList = (ArrayList<CartDTO>) cartSession.getAttribute("cartList");

		if (cartList == null) {
			cartList = new ArrayList<CartDTO>();
			cartSession.setAttribute("cartList", cartList);
		}

		for (int i = 0; i < cartList.size(); i++) {
			if (dto.getMenu_num() == cartList.get(i).getMenu_num() && cartList.get(i).getMenu_num() != 0) {
				cartList.get(i).setQty(cartList.get(i).getQty() - 1);
				if (cartList.get(i).getQty() == 0) {
					cartList.remove(i);
				}
				break;
			}
		}
		return cartList;
	}

	public void clearCart(HttpServletRequest request) {
		HttpSession cartSession = request.getSession();
		cartSession.removeAttribute("cartList");
	}

}