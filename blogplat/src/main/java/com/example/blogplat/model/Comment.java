package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime commentDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "blog_post_id")
	private BlogPost blogPost;

}







