package com.example.blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime publishDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

}





