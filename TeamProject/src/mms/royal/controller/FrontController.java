package mms.royal.controller;

import java.util.Scanner;

import mms.royal.action.Action;
import mms.royal.gameplay.GamePlay;

public class FrontController {
	
	public void processRequest(Action ac, Scanner sc, int index) {
		
		ac.execute(sc,index);
		
	}

	public void gamePlayRequest(GamePlay gp, Scanner sc, int index) {
		
		gp.execute(sc,index);
	}

}
