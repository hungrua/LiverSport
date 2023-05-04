package com.liversportweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liversportweb.DTO.CommentDTO;
import com.liversportweb.converter.CommentConverter;
import com.liversportweb.entity.CommentEntity;
import com.liversportweb.entity.SportFieldEntity;
import com.liversportweb.entity.UserEntity;
import com.liversportweb.repository.CommentRepository;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.repository.UserRepository;
import com.liversportweb.service.ICommentService;

@Service
public class CommentService implements ICommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SportFieldRepository sportFieldRepository;

	@Autowired
	private CommentConverter commentConverter;

	public CommentDTO saveOrUpdate(CommentDTO comment, Long id) {
		CommentEntity commentEntity = new CommentEntity();
		// thêm comment mơi
		if(comment.getId() == null) {
			commentEntity = save(comment, id);
		}
		// TH chinh su comment
		else {
			commentEntity = update(comment,id);

		}
		commentRepository.save(commentEntity);
		return commentConverter.toDto(commentEntity);
	}

	// add comment
	private CommentEntity save(CommentDTO comment, Long id) {
		// get user
		UserEntity user = gettUser();
		SportFieldEntity sportField = sportFieldRepository.findOne(id);
		CommentEntity entity = commentConverter.toEntity(comment);
		entity.setSportField(sportField);
		entity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
		entity.setUser(user);
		return entity;
	}

	// update comment
	private CommentEntity update(CommentDTO comment, Long id) {
		CommentEntity newComment = commentRepository.findOne(comment.getId());
		newComment.setContent(comment.getContent());
		newComment.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
		return newComment;
	}

	// xóa comment
	public void delete(Long id) {
		commentRepository.delete(id);
	}

	private UserEntity gettUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String userName = loggedInUser.getName();
		UserEntity user = userRepository.findOneByUserName(userName);
		return user;
	}

	public List<CommentDTO> findAll() {
		List<CommentEntity> listEntity = commentRepository.findAll();
		List<CommentDTO> listResult = new ArrayList<>();
		for(CommentEntity entity : listEntity) {
			CommentDTO dto = commentConverter.toDto(entity);
			listResult.add(dto);
		}
		return listResult;
	}

	@Override
	public List<CommentDTO> findAllCommentBySportField(Long id) {
		List<CommentEntity> entities = commentRepository.findAllBySportFieldId(id); 
		List<CommentDTO> listResult = new ArrayList<>();
		for(CommentEntity entity : entities) {
			CommentDTO dto = commentConverter.toDto(entity);
			listResult.add(dto);
		}
		return listResult;
	}
}