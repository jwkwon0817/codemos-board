package me.jwkwon0817.codemosboard.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardDto;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardRequestDto;
import me.jwkwon0817.codemosboard.domain.entities.Board;
import me.jwkwon0817.codemosboard.domain.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public BoardDto createBoard(final BoardRequestDto boardDto) {
		Board savedBoard = boardRepository.save(boardDto.toEntity());
		
		return savedBoard.toDto();
	}
	
	public BoardDto updateBoarder(final Long id, final BoardRequestDto boardDto) {
		Board board = retrieve(id);
		board.update(boardDto.getTitle(), boardDto.getContent());
		
		Board savedBoard = boardRepository.save(board);
		
		return savedBoard.toDto();
	}
	
	public void deleteBoard(final Long id) {
		boardRepository.delete(retrieve(id));
	}
	
	public List<BoardDto> retrieveAll() {
		return boardRepository.findAllByOrderByIdDesc().stream().map(Board::toDto).toList();
	}
	
	public Board retrieve(final Long id) {
		return boardRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + id));
	}
}