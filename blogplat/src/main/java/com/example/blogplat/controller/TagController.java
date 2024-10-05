package com.example.blog.controller;

import com.example.blog.model.Tag;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	@Autowired
	private TagService tagService;


	@GetMapping
	public List<Tag> getAllTags() {
		return tagService.getAllTags();
	}


	@GetMapping("/{id}")
	public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
		Optional<Tag> tag = tagService.getTagById(id);
		return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PostMapping
	public Tag createTag(@RequestBody Tag tag) {
		return tagService.createTag(tag);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
		tagService.deleteTag(id);
		return ResponseEntity.noContent().build();
	}
}
