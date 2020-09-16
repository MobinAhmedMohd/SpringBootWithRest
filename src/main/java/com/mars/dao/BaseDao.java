package com.mars.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseDao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
}
