package teste;

import java.math.BigDecimal;

public class Matricula {
	private Turma turma;

	private Aluno aluno;

	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;
	
	private Integer frequencia;

	private StatusAprovacao status;

	public BigDecimal nota1() {
		return this.nota1;
	}

	public BigDecimal nota2() {
		return this.nota2;
	}

	public BigDecimal nota3() {
		return this.nota3;
	}

	public StatusAprovacao status() {
		return this.status;
	}

	public void registrarNota1(BigDecimal nota1) {
		this.nota1 = nota1;
	}

	public void registrarNota2(BigDecimal nota2) {
		this.nota2 = nota2;
	}

	public void registrarNota3(BigDecimal nota3) {
		this.nota3 = nota3;
	}

	/**
	 * Segue as regras estabelecidas pelos artigos do regulamento de graduação da UFRN: http://www.sistemas.ufrn.br/download/sigaa/public/regulamento_dos_cursos_de_graduacao.pdf
	 * 
	 * A partir do artigo 104
	 */
	public void consolidarParcialmente() {
		StatusAprovacao status;

		if (cond) {

		} else {

		}
		
		this.status = status;

	}

	public Integer frequencia() {
		return frequencia;
	}

	public void registrarFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}
}
