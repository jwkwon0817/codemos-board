package me.jwkwon0817.codemosboard.domain.repositories;

import me.jwkwon0817.codemosboard.domain.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<Board> findAllByOrderByIdDesc();
}
