package me.jwkwon0817.codemosboard.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.codemosboard.domain.dto.comment.CommentDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	public void update(String content) {
		this.content = content;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public CommentDto toDto() {
		return CommentDto.builder()
			.id(id)
			.boardId(board.getId())
			.content(content)
			.createdDate(super.getCreatedDate())
			.modifiedDate(super.getModifiedDate())
			.build();
	}
}
