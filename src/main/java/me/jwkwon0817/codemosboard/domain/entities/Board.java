package me.jwkwon0817.codemosboard.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String content;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public BoardDto toDto() {
		return BoardDto.builder()
			.id(id)
			.title(title)
			.content(content)
			.createdDate(createdDate)
			.modifiedDate(modifiedDate)
			.build();
	}
}
