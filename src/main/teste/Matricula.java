package teste;

import java.math.BigDecimal;

public class Matricula {
	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private BigDecimal mediaParcial;
	
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

	public BigDecimal mediaParcial() {
		return this.mediaParcial;
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

	public void consolidarParcialmente() {
		this.registrarMediaParcial();

		if (this.estaAprovado()) 
		{
			this.status = StatusAprovacao.APR;
		} 
		else if (this.estaAprovadoPorNota()) 
		{
			this.status = StatusAprovacao.APRN;
		}
		else if (this.estaEmRecuperacao()) 
		{
			this.status = StatusAprovacao.REC;
		} 
		else if (this.estaReprovado()) 
		{
			this.status = StatusAprovacao.REP;
		}
		else if (this.estaReprovadoPorMediaEFalta()) 
		{
			this.status = StatusAprovacao.REMF;
		}
		else {
			this.status = StatusAprovacao.REPF;	
		}
	}

	public Integer frequencia() {
		return frequencia;
	}

	public void registrarFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	private void registrarMediaParcial() {
		BigDecimal notaSum = this.nota1().add(this.nota2()).add(this.nota3());		
        BigDecimal media = notaSum.divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);
		this.mediaParcial = media;
	}

	/**
	 * Média parcial igual ou superior a 7,0 com assiduidade (frequência) igual ou superior a 75% 
	 */
	private Boolean estaAprovado() {
		return this.mediaParcial().compareTo(BigDecimal.valueOf(7)) >= 0 && this.frequencia() >= 75;
	}

	/**
	 * Média parcial igual ou superior a 5,0 e menor que 7,0 com nota igual ou superior a 3,0 em todas as unidades
	 */
	private Boolean estaAprovadoPorNota() {
		BigDecimal tres = BigDecimal.valueOf(3);
		BigDecimal cinco = BigDecimal.valueOf(5);
		BigDecimal sete = BigDecimal.valueOf(7);

		return this.mediaParcial().compareTo(cinco) >= 0 &&
		this.mediaParcial().compareTo(sete) < 0 &&
		this.nota1().compareTo(tres)>= 0 && this.nota2().compareTo(tres)>= 0 && this.nota3().compareTo(tres) >= 0 &&
 		this.frequencia() >= 75;
	}

	/**
	 * Recuperação por:
	 * Nota: Média parcial igual ou superior a 5,0 e menor que 7,0 com nota inferior a 3,0 em pelo menos uma das unidades
	 * Média: Média parcial igual ou superior a 3,0 e menor do que 5,0 com nota igual ou superior a 3,0 em todas as unidades
	 * Média e Nota: Média parcial igual ou superior a 3,0 e menor do que 5,0 com nota inferior a 3,0 em pelo menos uma das unidades
	 */
	private Boolean estaEmRecuperacao() {
		BigDecimal tres = BigDecimal.valueOf(3);
		BigDecimal cinco = BigDecimal.valueOf(5);
		BigDecimal sete = BigDecimal.valueOf(7);
		
		Boolean emRecuperacaoPorNota = this.mediaParcial().compareTo(cinco) >= 0 && this.mediaParcial().compareTo(sete) < 0 && (this.nota1().compareTo(tres) < 0 || this.nota2().compareTo(tres) < 0 || this.nota3().compareTo(tres) < 0);
		Boolean emRecuperacaoPorMedia = this.mediaParcial().compareTo(tres) >= 0 && this.mediaParcial().compareTo(cinco) < 0 && (this.nota1().compareTo(tres) >= 0 && this.nota2().compareTo(tres) >= 0 && this.nota3().compareTo(tres) >= 0);
		Boolean emRecuperacaoPorMediaENota = this.mediaParcial().compareTo(tres) >= 0 && this.mediaParcial().compareTo(cinco) < 0 && (this.nota1().compareTo(tres) < 0 || this.nota2().compareTo(tres) < 0 || this.nota3().compareTo(tres) < 0);
		return (emRecuperacaoPorMedia || emRecuperacaoPorNota || emRecuperacaoPorMediaENota) && this.frequencia() >= 75;
	}

	/**
	 * Média parcial inferior a 3,0 e assiduidade igual ou superior a 75%
	 */
	private Boolean estaReprovado() {
		return this.mediaParcial().compareTo(BigDecimal.valueOf(3)) < 0 && this.frequencia() >= 75;
	}

	/**
	 * Média parcial inferior a 3,0 e frequência inferior a 75%
	 */
	private Boolean estaReprovadoPorMediaEFalta() {
		return this.mediaParcial().compareTo(BigDecimal.valueOf(3)) < 0 && this.frequencia() < 75;
	}
}
