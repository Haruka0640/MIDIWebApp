package basicservlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet("/control")
public class Control extends HttpServlet {

//		/*
//		 * 機能；最初のリクエスト時に入力画面を表示するメソッド
//		 * 引数；HttpSerletRequest　セッションの取得に使用
//		 *		HttpSerletREsponse オーバーライドに使用
//		 * 解説；まずセッションオブジェクトの有無を確認し、セッションがあればそれを破棄する。
//		 * 		その後入力画面に遷移する
//		 */
//	public void doGet(HttpServletRequest req,HttpServletResponse res)
//		throws ServletException,IOException{
//		HttpSession session = req.getSession(false);
//		if(session !=null) {
//			session.invalidate();
//		}
//		RequestDispatcher rd = req.getRequestDispatcher('/index.html');
//		rd.forward(req, res);
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		String actionNaame = req.getParameter("action");
		SoundForm form = new SoundForm();
		form.setRootKey(req.getParameter("ROOTKEY"));
		try {
			int accidental = Integer.parseInt(req.getParameter("ACCIDENTAL"));
			form.setAccidental(accidental);
		}catch (NumberFormatException e){
			System.out.println("Accidental must be integer");
		}
		form.setChordName(req.getParameter("CHORDNAME"));
		try {
			int octave = Integer.parseInt(req.getParameter("OCTAVE"));
			form.setAccidental(octave);
		}catch (NumberFormatException e){
			System.out.println("Octave must be integer");
		}
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
			


		}
}

