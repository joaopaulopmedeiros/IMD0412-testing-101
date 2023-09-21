package teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatriculaTest {

	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"7,8,9,75",
		"7,8,9,100"		
	})
	void Aprovado(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.APR, m.status());
	}

	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"3,5,8,75",
		"3,5,8,100"		
	})
	void AprovadoPorNota(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.APRN, m.status());
	}

	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"4,5,4,75",
		"6,8,2,75",	
		"2,3,4,75",
		"4,5,4,100",
		"6,8,2,100",	
		"2,3,4,100",			
	})
	void Recuperacao(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REC, m.status());
	}
		
	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"0,1,2,75",
		"0,1,2,100"		
	})
	void Reprovado(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REP, m.status());
	}

	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"10,9,8,0",
		"10,9,8,74"		
	})
	void ReprovadoPorFalta(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REPF, m.status());
	}

	@ParameterizedTest
	@CsvSource(delimiter = ',', value = {
		"1,2,1,0",
		"1,2,1,74"		
	})
	void ReprovadoPorMediaEFalta(Double nota1, Double nota2, Double nota3, Integer frequencia) {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(nota1));
		m.registrarNota2(BigDecimal.valueOf(nota2));
		m.registrarNota3(BigDecimal.valueOf(nota3));
		m.registrarFrequencia(frequencia);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REMF, m.status());
	}
}
