package me.jwkwon0817.codemosboard.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.codemosboard.domain.dto.board.BoardDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Board extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;
	
	private String title;
	
	private String content;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public BoardDto toDto() {
		return BoardDto.builder()
			.id(id)
			.title(title)
			.content(content)
			.createdDate(super.getCreatedDate())
			.modifiedDate(super.getModifiedDate())
			.build();
	}
}
