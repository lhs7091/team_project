package okawari_model;

/*
 *  컨트롤러 역할을 수행하는 서블릿에서 클라이언트의 요청을 받아서 비즈니스 로직을 처리한 후
 *  응답처리를 위해 이동할 페이지(view)로 포워딩을 시킨다. 이때, 이동할 페이지와 포워딩할 방법을
 *  담아서 객체화 시키는 클래스
 * */

public class ActionForward {
	private String resUrl; // 이동할 뷰 페이지
	private boolean isRedirect; // 포워딩 방식(디스패치 or 리다이렉트)

	public ActionForward(String resUrl, boolean isRedirect) {
		this.resUrl = resUrl;
		this.isRedirect = isRedirect;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}
