package com.example.blog.controller;

import com.example.blog.model.BlogPost;
import com.example.blog.model.Comment;
import com.example.blog.service.BlogPostService;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private CommentService commentService;

	@GetMapping
	public List<BlogPost> getAllPosts() {
		return blogPostService.getAllPosts();
	}

	@GetMapping("/category/{categoryName}")
	public List<BlogPost> getPostsByCategory(@PathVariable String categoryName) {
		return blogPostService.getPostsByCategory(categoryName);
	}


	@GetMapping("/{id}")
	public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
		Optional<BlogPost> post = blogPostService.getPostById(id);
		return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


	@GetMapping("/{postId}/comments")
	public List<Comment> getCommentsForPost(@PathVariable Long postId) {
		return commentService.getCommentsByPostId(postId);
	}


	@PostMapping
	public BlogPost createPost(@RequestBody BlogPost blogPost) {
		return blogPostService.createPost(blogPost);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
		Optional<BlogPost> updatedPost = blogPostService.updatePost(id, blogPost);
		return updatedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		blogPostService.deletePost(id);
		return ResponseEntity.noContent().build();
	}
}

