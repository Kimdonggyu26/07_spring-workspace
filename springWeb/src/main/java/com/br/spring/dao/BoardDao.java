package com.br.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDao {
	
	private final SqlSessionTemplate sqlSession;
	
	public int selectBoardListCount() {
		return sqlSession.selectOne("boardMapper.selectBoardListCount");
	}
	
	public List<BoardDto> selectBoardList(PageInfoDto pi){
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() , pi.getBoardLimit());
		return sqlSession.selectList("boardMapper.selectBoardList", null, rowBounds);
	}
	
	public int selectSearchListCount(Map<String, String> search){
		return sqlSession.selectOne("boardMapper.selectSearchListCount", search);
	}
	
	public List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi){
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() , pi.getBoardLimit());
		return sqlSession.selectList("boardMapper.selectSearchList", search, rowBounds);
	}
	
	public int insertBoard(BoardDto b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}
	
	public int insertAttach(AttachDto at) {
		return sqlSession.insert("boardMapper.insertAttach", at);
	}
	
	public BoardDto selectBoard(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	
	
	
}