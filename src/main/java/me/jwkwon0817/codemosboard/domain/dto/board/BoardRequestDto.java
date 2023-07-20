package me.jwkwon0817.codemosboard.domain.dto.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.codemosboard.domain.entities.Board;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	public Board toEntity() {
		return Board.builder()
			.title(title)
			.content(content)
			.createdDate(LocalDateTime.now())
			.modifiedDate(LocalDateTime.now())
			.build();
	}
}
