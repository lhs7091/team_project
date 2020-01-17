package mms.royal.controller;

import java.util.Scanner;

import mms.royal.action.Action;
import mms.royal.gameplay.GamePlay;
import mms.royal.vo.MemberDTO;

public class FrontController {
	
	public void processRequest(Action ac, Scanner sc, MemberDTO dto) {
		
		ac.execute(sc, dto);
		
	}

	public void gamePlayRequest(GamePlay gp, Scanner sc, MemberDTO dto) {
		
		gp.execute(sc, dto);
	}

}
