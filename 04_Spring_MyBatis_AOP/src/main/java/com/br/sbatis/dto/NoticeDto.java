package com.br.sbatis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class NoticeDto {

	private int no;
	private String title;
	private String content;
	
}
