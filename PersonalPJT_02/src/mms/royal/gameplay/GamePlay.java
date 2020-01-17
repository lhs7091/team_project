package mms.royal.gameplay;

import java.util.Scanner;

import mms.royal.vo.MemberDTO;

public interface GamePlay {

	public abstract void execute(Scanner sc, MemberDTO dto);

}
