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
	void AprovadoPorNota() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(5));
		m.registrarNota2(BigDecimal.valueOf(3));
		m.registrarNota3(BigDecimal.valueOf(8));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.APRN, m.status());
	}

	@Test
	void RecuperacaoPorNota() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(6));
		m.registrarNota2(BigDecimal.valueOf(8));
		m.registrarNota3(BigDecimal.valueOf(2));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REC, m.status());
	}

	@Test
	void RecuperacaoPorMedia() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(4));
		m.registrarNota2(BigDecimal.valueOf(5));
		m.registrarNota3(BigDecimal.valueOf(4));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REC, m.status());
	}	

	@Test
	void RecuperacaoPorMediaENota() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(2));
		m.registrarNota2(BigDecimal.valueOf(3));
		m.registrarNota3(BigDecimal.valueOf(4));
		m.registrarFrequencia(100);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REC, m.status());
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
	
	@Test
	void ReprovadoPorFalta() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(10));
		m.registrarNota2(BigDecimal.valueOf(9));
		m.registrarNota3(BigDecimal.valueOf(9));
		m.registrarFrequencia(25);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REPF, m.status());
	}

	@Test
	void ReprovadoPorMediaEFalta() {
		Matricula m = new Matricula();
		
		m.registrarNota1(BigDecimal.valueOf(1));
		m.registrarNota2(BigDecimal.valueOf(2));
		m.registrarNota3(BigDecimal.valueOf(1));
		m.registrarFrequencia(25);
		
		m.consolidarParcialmente();
		
		Assertions.assertEquals(StatusAprovacao.REMF, m.status());
	}
}
