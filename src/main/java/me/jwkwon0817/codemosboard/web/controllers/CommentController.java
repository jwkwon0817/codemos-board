package me.jwkwon0817.codemosboard.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jwkwon0817.codemosboard.domain.dto.comment.CommentDto;
import me.jwkwon0817.codemosboard.domain.dto.comment.CommentRequestDto;
import me.jwkwon0817.codemosboard.domain.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("/comment/{id}")
	public ResponseEntity<?> getComment(@PathVariable Long id) {
		CommentDto retrieve = commentService.retrieve(id).toDto();
		log.info("Retrieved DTO: {}", retrieve);
		
		return ResponseEntity.ok(retrieve);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequestDto commentDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("Binding Result: {}", bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
		}
		
		
		CommentDto createComment = commentService.createComment(commentDto);
		
		log.info("Created DTO: {}", createComment);
		
		return ResponseEntity.ok(createComment);
	}
	
	@PutMapping("/comment/{id}")
	public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody @Valid CommentRequestDto commentDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("Binding Result: {}", bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
		}
		
		CommentDto updatedComment = commentService.updateComment(id, commentDto);
		
		log.info("Updated DTO: {}", updatedComment);
		
		return ResponseEntity.ok(updatedComment);
	}
	
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		
		commentService.deleteComment(id);
		
		log.info("Deleted id: {}", id);
		
		return ResponseEntity.ok("Successfully deleted id: " + id);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<?> getComments() {
		
		log.info("Retrieved all comments");
		
		return ResponseEntity.ok(commentService.retrieveAll());
	}
	
	@GetMapping("/comments/{boardId}")
	public ResponseEntity<?> getCommentsByBoardId(@PathVariable Long boardId) {
		
		log.info("Retrieved all comments by boardId: {}", boardId);
		
		return ResponseEntity.ok(commentService.retrieveByBoardId(boardId));
	}
}
