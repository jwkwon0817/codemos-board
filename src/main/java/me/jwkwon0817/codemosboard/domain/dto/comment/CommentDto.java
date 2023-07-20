package me.jwkwon0817.codemosboard.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
	private Long id;
	
	private String content;
	
	private Long boardId;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
}
