package me.jwkwon0817.codemosboard.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.codemosboard.domain.entities.Board;
import me.jwkwon0817.codemosboard.domain.entities.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
	
	@NotNull
	private Long boardId;
	@NotBlank
	private String content;
	
	public Comment toEntity() {
		Board board = Board.builder()
			.id(boardId)
			.build();
		
		return Comment.builder()
			.board(board)
			.content(content)
			.build();
	}
}
