package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		return commentService.getCommentById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PostMapping
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}
}

