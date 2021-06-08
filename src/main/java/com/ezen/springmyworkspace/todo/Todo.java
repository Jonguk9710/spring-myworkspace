package com.ezen.springmyworkspace.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

// @Entity = 데이터베이스의 테이블과 연결함(mapping)
// ORM(object Relational Mapping)
// : 객체와 테이블을 맵핑한다

@Data
@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long createdTime;

}
