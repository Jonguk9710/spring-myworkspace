package com.ezen.springmyworkspace.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ������ ���� �������̽� ����

// �������̽��� �ν��Ͻ� ������ �ȵȴ�.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
