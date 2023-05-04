package com.liversportweb.service;

import java.util.List;

import com.liversportweb.DTO.CommentDTO;

public interface ICommentService {
	CommentDTO saveOrUpdate(CommentDTO comment, Long id);
	void delete(Long id);
	List<CommentDTO> findAllCommentBySportField(Long id);
	
}
