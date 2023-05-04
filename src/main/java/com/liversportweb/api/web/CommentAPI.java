package com.liversportweb.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liversportweb.DTO.CommentDTO;
import com.liversportweb.service.ICommentService;

@RestController
public class CommentAPI {
	@Autowired
	private ICommentService commentService;

	@PostMapping("/user/comment/{id}")
	public CommentDTO postComment(@PathVariable("id") String id, @RequestBody CommentDTO comment) {
		CommentDTO res = commentService.saveOrUpdate(comment, Long.valueOf(id));
		return res;
	}	

	@PutMapping("/user/comment/{id}")
	public CommentDTO putComment(@PathVariable("id") Long id, @RequestBody CommentDTO comment) {
		CommentDTO res = commentService.saveOrUpdate(comment, id);
		return res;
	}	

//	@GetMapping("/user/comment")
//	public List<CommentDTO> getList() {
//		List<CommentDTO> res = commentService.findAll();
//		return res;
//	}
	@GetMapping("user/comment/{id}")
	public List<CommentDTO> getListBySportField(@PathVariable("id") Long id){
		List<CommentDTO> res = commentService.findAllCommentBySportField(id);
		return res;
	}

	@DeleteMapping("/user/comment/{id}")
	public String deleteComment(@PathVariable("id") String id) {
		commentService.delete(Long.valueOf(id));
		return "success";
	}
}