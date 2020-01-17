package mms.royal.action;

import java.util.Scanner;

import mms.royal.dao.GameResultDAO;
import mms.royal.vo.MemberDTO;

public class ShowGameResAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		//게임전적조회
		//db에 접근하여 전적 조회
		GameResultDAO dao = new GameResultDAO();
		dao.showGameResult(dto);

	}

}
