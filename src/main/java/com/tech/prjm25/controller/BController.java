package com.tech.prjm25.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.command.BCommand;
import com.tech.command.BContentCommand;
import com.tech.command.BDeleteCommand;
import com.tech.command.BListCommand;
import com.tech.command.BModifyCommand;
import com.tech.command.BModifyViewCommand;
import com.tech.command.BReplyCommand;
import com.tech.command.BReplyViewCommand;
import com.tech.command.BWriteCommand;
import com.tech.prjm25.dao.IDao;
import com.tech.prjm25.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	BCommand command;
	
	IDao iDao;
	@Autowired
	public BController(IDao iDao) {
		this.iDao = iDao;
	}
	
	@RequestMapping("/")
	public String init() {
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list() mybatis");
		ArrayList<BDto> list = iDao.list();
		model.addAttribute("list", list);
		
		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view() ctr");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request,
			Model model) {
		System.out.println("write() mybatis");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		iDao.write(bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,
			Model model) {
		System.out.println("content_view() mybatis");
		String bid=request.getParameter("bid");
		BDto dto = iDao.contentView(bid);
		model.addAttribute("content_view", dto);
		
		return "content_view";
	}
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request,
			Model model) {
		System.out.println("modify_view() mybatis");
		String bid=request.getParameter("bid");
		BDto bto = iDao.modifyView(bid);
		model.addAttribute("content_view", bto);
		
		return "modify_view";
	}
	
	@PostMapping("/modify")
	public String modify(HttpServletRequest request,
			Model model) {
		System.out.println("modify() mybatis");
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		iDao.modify(bid, bname, btitle, bcontent);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view() mybatis");
		String bid=request.getParameter("bid");
		BDto dto = iDao.replyView(bid);
		model.addAttribute("reply_view", dto);
		return "reply_view";
	}
	
	@PostMapping("/reply")
	public String reply(HttpServletRequest request,
			Model model) {
		System.out.println("reply() mybatis");
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		String bindent=request.getParameter("bindent");
		String bstep=request.getParameter("bstep");
		String bgroup=request.getParameter("bgroup");
		iDao.replyShape(bgroup, bstep);
		iDao.reply(bid, bname, btitle, bcontent, bindent, bgroup, bstep);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete() mybatis");
		String bid = request.getParameter("bid");
		iDao.delete(bid);
		
		return "redirect:list";
	}
	
}
