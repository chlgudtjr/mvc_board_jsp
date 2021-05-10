package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto;

public class ContentViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");

		Dao dao = new Dao();
		Dto contentView = dao.contentView(bId);

		request.setAttribute("contentView", contentView);

	}

}
