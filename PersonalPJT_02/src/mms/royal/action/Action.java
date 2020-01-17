package mms.royal.action;

import java.util.Scanner;

import mms.royal.vo.MemberDTO;

public interface Action {
	
	public void execute(Scanner sc, MemberDTO dto);
	
}
