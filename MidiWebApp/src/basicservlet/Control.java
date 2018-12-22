package basicservlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

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
					String path = action.execute(path);
					rd = req.getRequestDispatcher(path);
				}else {
					rd = req.getRequestDispatcher("/WebContent/HTML/checkerror.html");
				}
			}catch(ChordSimulatorException e){
				e.printStackTrace();
				rd = req.getRequestDispatcher("/WebContent/HTML/chordsimulatorerror.html");
			}
		}else {
			rd = req.getRequestDispatcher("/WebContent/HTML/systemerror.html");
		}
		
		private Action createAction(String name) {
			if(name.equals("chordsimulator"){
				return new ChordSimulatorAction();
			}else {
				return new MenuAction();
			}
		}
		

			


		}
}

