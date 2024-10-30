package com.br.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.br.spring.dao.BoardDao;
import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;

	@Override
	public int selectBoardListCount() {
		return boardDao.selectBoardListCount();
	}

	@Override
	public List<BoardDto> selectBoardList(PageInfoDto pi) {
		return boardDao.selectBoardList(pi);
	}

	@Override
	public int selectSearchListCount(Map<String, String> search) {
		return boardDao.selectSearchListCount(search);
	}

	@Override
	public List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi) {
		return boardDao.selectSearchList(search, pi);
	}

	@Override
	public int insertBoard(BoardDto b) {
		
		int result = boardDao.insertBoard(b);
		
		List<AttachDto> list = b.getAttachList();
		if(result > 0 && !list.isEmpty()) {
			result = 0;
			
			for(AttachDto attach : list) {
				result += boardDao.insertAttach(attach);
			}
			
		}
		
		return result;
	}

	@Override
	public int updateIncreaseCount(int boardNo) {
		return 0;
	}

	@Override
	public BoardDto selectBoard(int boardNo) {
		return boardDao.selectBoard(boardNo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return 0;
	}

	@Override
	public List<ReplyDto> selectReplyList(int boardNo) {
		return null;
	}

	@Override
	public int insertReply(ReplyDto r) {
		return 0;
	}
	
}