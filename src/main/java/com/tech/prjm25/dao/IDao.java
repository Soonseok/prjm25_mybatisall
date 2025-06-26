package com.tech.prjm25.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.prjm25.dto.BDto;

@Mapper
public interface IDao {
	public ArrayList<BDto> list();
	public void write(String bname, String btitle,
			String bcontent);
}
