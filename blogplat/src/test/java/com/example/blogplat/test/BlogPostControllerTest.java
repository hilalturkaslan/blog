package com.example.blog;

import com.example.blog.controller.BlogPostController;
import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlogPostControllerTest {

    @InjectMocks
    private BlogPostController blogPostController;

    @Mock
    private BlogPostService blogPostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPosts() {
        BlogPost post1 = new BlogPost(1L, "Title 1", "Content 1");
        BlogPost post2 = new BlogPost(2L, "Title 2", "Content 2");
        when(blogPostService.findAll()).thenReturn(Arrays.asList(post1, post2));

        ResponseEntity<List<BlogPost>> response = blogPostController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(blogPostService, times(1)).findAll();
    }

    @Test
    void testGetPostById() {
        BlogPost post = new BlogPost(1L, "Title 1", "Content 1");
        when(blogPostService.findById(1L)).thenReturn(Optional.of(post));

        ResponseEntity<BlogPost> response = blogPostController.getPostById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title 1", response.getBody().getTitle());
        verify(blogPostService, times(1)).findById(1L);
    }

    @Test
    void testCreatePost() {
        BlogPost post = new BlogPost(null, "Title 1", "Content 1");
        when(blogPostService.save(post)).thenReturn(new BlogPost(1L, "Title 1", "Content 1"));

        ResponseEntity<BlogPost> response = blogPostController.createPost(post);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(blogPostService, times(1)).save(post);
    }

    @Test
    void testDeletePost() {
        Long postId = 1L;
        doNothing().when(blogPostService).delete(postId);

        ResponseEntity<Void> response = blogPostController.deletePost(postId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(blogPostService, times(1)).delete(postId);
    }
}







