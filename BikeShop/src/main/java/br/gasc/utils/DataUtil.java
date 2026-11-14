package br.gasc.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	private static final DateTimeFormatter PT_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static String dataFormatada(LocalDate data) {
		return data.format(PT_BR);
	}
	
}