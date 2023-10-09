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
        "3.5, 3.5, 3.5, 70, REPF",
        "2.5, 2.5, 2.5, 75, REP",
        "4.5, 4.5, 4.5, 75, REC",
        "5.5, 4.5, 5.0, 75, APRN",
        "7.5, 7.5, 7.5, 75, APR"
    })
    @DisplayName("Teste consolidarParcialmente")
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
