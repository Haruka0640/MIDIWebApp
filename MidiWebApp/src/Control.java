

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/control")
public class Control extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		String WEBINFPath = this.getServletContext().getRealPath("WEB-INF");

		String actionName = req.getParameter("action");
		Action action = createAction(actionName);
		
		if (action != null) {
			try {
				if(action.check(req)){
					String path = action.execute(req,WEBINFPath);
					rd = req.getRequestDispatcher(path);
				}else {
					rd = req.getRequestDispatcher("HTML/checkerror.html");
				}
			}catch(XMLException ex){
				rd = req.getRequestDispatcher("HTML/xmlerror.html");
			}catch(MIDIException ex) {
				rd = req.getRequestDispatcher("HTML/midierror.html");
			}
		}else {
			rd = req.getRequestDispatcher("HTML/unhandlederror.html");
		}
		rd.forward(req, res);
	}
		
	private Action createAction(String name) {
		if(name.equals("chordsimulator")) {
			return new ChordSimulatorAction();
		}else {
			return null;
		}
	}
		
}

