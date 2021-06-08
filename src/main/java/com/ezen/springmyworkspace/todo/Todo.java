package com.ezen.springmyworkspace.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

// @Entity = �����ͺ��̽��� ���̺�� ������(mapping)
// ORM(object Relational Mapping)
// : ��ü�� ���̺��� �����Ѵ�

@Data
@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long createdTime;

}
