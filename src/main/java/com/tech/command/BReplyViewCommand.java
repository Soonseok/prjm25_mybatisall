package com.tech.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.tech.prjm25.dao.BDao;
import com.tech.prjm25.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

public class BReplyViewCommand implements BCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		BDao dao=new BDao();
		BDto dto=dao.replyView(bid);

		model.addAttribute("reply_view",dto);
	}

}
