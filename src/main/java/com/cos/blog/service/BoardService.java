package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.repository.ReplyRepository;



@Service
public class BoardService {
  
	 @Autowired
	 private UserRepository userRepository;
	 @Autowired
	private BoardRepository boardRepository;
	 @Autowired
	 private ReplyRepository replyRepository;
	 
	 @Transactional
	 public void 글쓰기(Board board,User user) {
		 board.setCount(0);
		 board.setUser(user);
		boardRepository.save(board);
}
	 @Transactional(readOnly=true)
 public Page<Board>글목록(Pageable pageble){
	  return boardRepository.findAll(pageble);
 }
	 @Transactional(readOnly = true)
 public Board 글상세보기(int id) {
	 return boardRepository.findById(id)
			 .orElseThrow(()->{
				 return  new IllegalArgumentException("글상세보기 실패");
			 });
			 
 }
@Transactional
public void 글삭제하기(int id) {
	 boardRepository.deleteById(id);
			
			 
	
}
@Transactional
public void 글수정하기(int id, Board requestBoard) {
	Board board =boardRepository.findById(id)
			 .orElseThrow(()->{
				 return  new IllegalArgumentException("글수정실패");
			 });
	board.setTitle(requestBoard.getTitle() );
	board.setContent(requestBoard.getContent());
}
@Transactional
public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
	
	int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
}
public void 댓글삭제(int replyId) {
	// TODO Auto-generated method stub
	replyRepository.deleteById(replyId);
}
}