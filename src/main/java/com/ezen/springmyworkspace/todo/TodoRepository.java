package com.ezen.springmyworkspace.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ������ ���� �������̽� ����

// �������̽��� �ν��Ͻ� ������ �ȵȴ�.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	// Page<Todo> findByMemo(Pageable page, String memo);

	Page<Todo> findByMemoContaining(Pageable page, String memo);
}
