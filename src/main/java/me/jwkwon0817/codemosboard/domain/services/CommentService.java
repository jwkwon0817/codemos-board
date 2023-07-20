package me.jwkwon0817.codemosboard.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jwkwon0817.codemosboard.domain.dto.comment.CommentDto;
import me.jwkwon0817.codemosboard.domain.dto.comment.CommentRequestDto;
import me.jwkwon0817.codemosboard.domain.entities.Board;
import me.jwkwon0817.codemosboard.domain.entities.Comment;
import me.jwkwon0817.codemosboard.domain.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final BoardService boardService;
	private final CommentRepository commentRepository;
	
	public CommentDto createComment(final CommentRequestDto commentDto) {
		Board board = boardService.retrieve(commentDto.getBoardId());
		
		Comment savedComment = commentRepository.save(commentDto.toEntity());
		savedComment.setBoard(board);
		
		return savedComment.toDto();
	}
	
	public CommentDto updateComment(final Long id, final CommentRequestDto commentDto) {
		Comment comment = retrieve(id);
		
		comment.update(commentDto.getContent());
		
		Comment savedComment = commentRepository.save(comment);
		
		return savedComment.toDto();
	}
	
	public void deleteComment(final Long id) {
		commentRepository.delete(retrieve(id));
	}
	
	public List<CommentDto> retrieveAll() {
		return commentRepository.findAll().stream().map(Comment::toDto).toList();
	}
	
	public Comment retrieve(final Long id) {
		return commentRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("해당 댓글이 없습니다. id=" + id));
	}
	
	public List<CommentDto> retrieveByBoardId(final Long boardId) {
		return commentRepository.findByBoardIdOrderByIdDesc(boardId).stream().map(Comment::toDto).toList();
	}
}