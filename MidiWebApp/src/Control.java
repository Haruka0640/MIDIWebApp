

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import control.action.*;
import control.midi.*;
import control.xmlreader.*;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/control")
public class Control extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		
		String actionName = req.getParameter("action");
		Action action = createAction(actionName);
		
		if (action != null) {
			try {
				if(action.check(req)){
					String path = action.execute(req);
					rd = req.getRequestDispatcher(path);
				}else {
					rd = req.getRequestDispatcher("/WebContent/HTML/checkerror.html");
				}
			}catch(XMLException ex){
				rd = req.getRequestDispatcher("/WebContent/HTML/xmlerror.html");
			}catch(MIDIException ex) {
				rd = req.getRequestDispatcher("/WebContent/HTML/midierror.html");
			}
		}else {
			rd = req.getRequestDispatcher("/WebContent/HTML/unhandlederror.html");
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

