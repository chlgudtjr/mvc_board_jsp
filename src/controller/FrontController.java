package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.ContentViewCommand;
import command.DeleteCommand;
import command.ListCommand;
import command.ModifyCommand;
import command.ReplyCommand;
import command.WriteCommand;
import command.replyViewCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");

		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		Command command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cp = uri.substring(conPath.length());

		System.out.println("uri : " + uri);
		System.out.println("conPath : " + conPath);
		System.out.println("cp : " + cp);

		if (cp.equals("/list.do")) {
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		} else if (cp.equals("/contentView.do")) {
			command = new ContentViewCommand();
			command.execute(request, response);
			viewPage = "contentView.jsp";
		} else if (cp.equals("/delete.do")) {
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage = "/list.do";
		} else if (cp.equals("/modify.do")) {
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage = "/list.do";
		} else if (cp.equals("/reply.do")) {
			command = new ReplyCommand();
			command.execute(request, response);
			viewPage = "/list.do";
		} else if (cp.equals("/replyView.do")) {
			command = new replyViewCommand();
			command.execute(request, response);
			viewPage = "replyView.jsp";
		} else if (cp.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "/list.do";
		} else if (cp.equals("/writeView.do")) {
			viewPage = "writeView.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
