package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatriculaTest {

	@Test
	void test() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(10l));
		m.registrarNota2(BigDecimal.valueOf(10l));
		m.registrarNota3(BigDecimal.valueOf(10l));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.APR, m.status());
	}

}
