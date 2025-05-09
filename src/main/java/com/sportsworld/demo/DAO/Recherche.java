package com.example.demo.DAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Recherche {
	private String Keyword;
	private LocalDate date;

}
