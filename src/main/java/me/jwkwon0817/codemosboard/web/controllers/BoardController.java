package me.jwkwon0817.codemosboard.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardDto;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardRequestDto;
import me.jwkwon0817.codemosboard.domain.services.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {
		BoardDto retrieve = boardService.retrieve(id).toDto();
		log.info("Retrieved DTO: {}", retrieve);
		
		return ResponseEntity.ok(retrieve);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> createBoard(@RequestBody @Valid BoardRequestDto boardDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("Binding Result: {}", bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
		}
		
		
		BoardDto createdBoard = boardService.createBoard(boardDto);
		
		log.info("Created DTO: {}", createdBoard);
		
		return ResponseEntity.ok(createdBoard);
	}
	
	@PutMapping("/board/{id}")
	public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody @Valid BoardRequestDto boardDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("Binding Result: {}", bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
		}
		
		BoardDto updatedBoard = boardService.updateBoarder(id, boardDto);
		
		log.info("Updated DTO: {}", updatedBoard);
		
		return ResponseEntity.ok(updatedBoard);
	}
	
	@DeleteMapping("/board/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
		
		boardService.deleteBoard(id);
		
		log.info("Deleted id: {}", id);
		
		return ResponseEntity.ok("Successfully deleted id: " + id);
	}
	
	@GetMapping("/boards")
	public ResponseEntity<?> getBoards() {
		
		log.info("Retrieved all boards");
		
		return ResponseEntity.ok(boardService.retrieveAll());
	}
}
