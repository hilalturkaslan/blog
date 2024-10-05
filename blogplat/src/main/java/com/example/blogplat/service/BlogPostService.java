package com.example.blog.service;

import com.example.blog.model.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public List<BlogPost> getPostsByCategory(String categoryName) {
        return blogPostRepository.findByCategoryName(categoryName);
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public Optional<BlogPost> updatePost(Long id, BlogPost blogPost) {
        if (blogPostRepository.existsById(id)) {
            blogPost.setId(id);
            return Optional.of(blogPostRepository.save(blogPost));
        }
        return Optional.empty();
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}












