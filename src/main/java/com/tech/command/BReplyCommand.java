package com.tech.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.tech.prjm25.dao.BDao;
import com.tech.prjm25.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

public class BReplyCommand implements BCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		String bindent=request.getParameter("bindent");
		String bstep=request.getParameter("bstep");
		String bgroup=request.getParameter("bgroup");
		
		BDao dao=new BDao();
		dao.reply(bid, bname, btitle, bcontent, bindent, bgroup, bstep);
	}
}
