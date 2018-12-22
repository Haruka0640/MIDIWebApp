package basicservlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet("/control")
public class Control extends HttpServlet {

//		/*
//		 * �@�\�G�ŏ��̃��N�G�X�g���ɓ��͉�ʂ�\�����郁�\�b�h
//		 * �����GHttpSerletRequest�@�Z�b�V�����̎擾�Ɏg�p
//		 *		HttpSerletREsponse �I�[�o�[���C�h�Ɏg�p
//		 * ����G�܂��Z�b�V�����I�u�W�F�N�g�̗L�����m�F���A�Z�b�V����������΂����j������B
//		 * 		���̌���͉�ʂɑJ�ڂ���
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

