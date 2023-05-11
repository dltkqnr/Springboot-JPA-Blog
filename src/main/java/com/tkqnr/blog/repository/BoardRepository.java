package com.tkqnr.blog.repository;

import com.tkqnr.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}
