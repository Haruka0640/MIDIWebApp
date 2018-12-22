package basicservlet;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	boolean check(HttpServletRequest req);
	String execute(HttpServletRequest req) throws ActionException;
}
