package okawari_action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;

public interface Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;

}