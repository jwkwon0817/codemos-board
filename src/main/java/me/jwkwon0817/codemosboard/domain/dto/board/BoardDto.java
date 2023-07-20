package me.jwkwon0817.codemosboard.domain.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
}
