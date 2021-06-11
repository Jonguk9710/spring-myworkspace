package com.ezen.springmyworkspace.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 데이터 접근 인터페이스 선언

// 인터페이스는 인스턴스 생성이 안된다.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	// Page<Todo> findByMemo(Pageable page, String memo);

	Page<Todo> findByMemoContaining(Pageable page, String memo);
}
