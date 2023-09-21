package teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatriculaTest {

	@Test
	void Aprovado() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(7));
		m.registrarNota2(BigDecimal.valueOf(8));
		m.registrarNota3(BigDecimal.valueOf(9));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.APR, m.status());
	}

	@Test
	void Reprovado() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(1));
		m.registrarNota2(BigDecimal.valueOf(2));
		m.registrarNota3(BigDecimal.valueOf(1));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REP, m.status());
	}
}
