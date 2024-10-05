
package com.example.blog;

import com.example.blog.model.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlogPostServiceTest {

    @InjectMocks
    private BlogPostService blogPostService;

    @Mock
    private BlogPostRepository blogPostRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        BlogPost post1 = new BlogPost(1L, "Title 1", "Content 1");
        BlogPost post2 = new BlogPost(2L, "Title 2", "Content 2");
        when(blogPostRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<BlogPost> posts = blogPostService.findAll();

        assertEquals(2, posts.size());
        verify(blogPostRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        BlogPost post = new BlogPost(1L, "Title 1", "Content 1");
        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(post));

        Optional<BlogPost> foundPost = blogPostService.findById(1L);

        assertEquals("Title 1", foundPost.get().getTitle());
        verify(blogPostRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        BlogPost post = new BlogPost(null, "Title 1", "Content 1");
        when(blogPostRepository.save(post)).thenReturn(new BlogPost(1L, "Title 1", "Content 1"));

        BlogPost savedPost = blogPostService.save(post);

        assertEquals(1L, savedPost.getId());
        verify(blogPostRepository, times(1)).save(post);
    }

    @Test
    void testDelete() {
        Long postId = 1L;
        doNothing().when(blogPostRepository).deleteById(postId);

        blogPostService.delete(postId);

        verify(blogPostRepository, times(1)).deleteById(postId);
    }
}



