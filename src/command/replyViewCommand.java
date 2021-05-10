package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto;

public class replyViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");

		Dao dao = new Dao();
		Dto replyView = dao.replyView(bId);

		request.setAttribute("replyView", replyView);

	}

}
