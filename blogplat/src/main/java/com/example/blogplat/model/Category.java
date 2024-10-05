package com.example.blog.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "category")
	private List<BlogPost> blogPosts;

}







