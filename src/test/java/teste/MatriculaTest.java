package teste;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatriculaTest {
	@ParameterizedTest
    @CsvSource({
        "2.5, 2.5, 2.5, 70, REMF",
        "2.5, 3.5, 3.0, 74, REPF",
        "1.0, 2.0, 3.0, 75, REP",
        "3.0, 4.5, 1.5, 80, REC",
        "6.0, 3.0, 7.0, 90, APRN",
        "6.0, 7.0, 8.0, 100, APR"
    })
    @DisplayName("Teste de consolidação parcial")
    void consolidarParcialmente(BigDecimal nota1, BigDecimal nota2, BigDecimal nota3, Integer frequencia, StatusAprovacao expectedStatus) {
        Matricula matricula = new Matricula();

		matricula.registrarNota1(nota1);
        matricula.registrarNota2(nota2);
        matricula.registrarNota3(nota3);
        matricula.registrarFrequencia(frequencia);

        matricula.consolidarParcialmente();

        assertEquals(expectedStatus, matricula.status());
    }
}
