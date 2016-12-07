package com.shundian.frame.api.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class City {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String state;

	@Transient
	private Integer page = 1;

	@Transient
	private Integer rows = 10;

}
