package teste;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matricula
{
	private static final BigDecimal TRES = BigDecimal.valueOf(3l);

	private static final BigDecimal CINCO = BigDecimal.valueOf(5l);

	private static final BigDecimal SETE = BigDecimal.valueOf(7l);

	private Turma turma;

	private Aluno aluno;

	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private BigDecimal mediaParcial;

	private Integer frequencia;

	private StatusAprovacao status;

	public BigDecimal nota1()
	{
		return this.nota1;
	}

	public BigDecimal nota2()
	{
		return this.nota2;
	}

	public BigDecimal nota3()
	{
		return this.nota3;
	}

	public BigDecimal mediaParcial()
	{
		return this.mediaParcial;
	}

	public StatusAprovacao status()
	{
		return this.status;
	}

	public void registrarNota1(BigDecimal nota1)
	{
		this.nota1 = nota1;
	}

	public void registrarNota2(BigDecimal nota2)
	{
		this.nota2 = nota2;
	}

	public void registrarNota3(BigDecimal nota3)
	{
		this.nota3 = nota3;
	}

	/**
	 * Segue as regras estabelecidas pelos artigos do regulamento de graduação da
	 * UFRN:
	 * http://www.sistemas.ufrn.br/download/sigaa/public/regulamento_dos_cursos_de_graduacao.pdf
	 * 
	 * A partir do artigo 104
	 */
	public void consolidarParcialmente()
	{
		this.gerarMediaParcial();

		if (this.possuiFrequenciaInsuficiente()) 
		{
			if (this.estaReprovado())
			{
				this.status = StatusAprovacao.REMF;
			}
			else
			{
				this.status = StatusAprovacao.REPF;
			}
		}
		else
		{
			if(this.estaAprovado()) 
			{
				this.status = StatusAprovacao.APR;
			}
			else if(this.estaReprovado())
			{
				this.status = StatusAprovacao.REP;
			}
			else if(this.estaEmRecuperacao())
			{
				this.status = StatusAprovacao.REC;
			} else {
				this.status = StatusAprovacao.APRN;
			}
		}
	}

	public Integer frequencia()
	{
		return frequencia;
	}

	public void registrarFrequencia(Integer frequencia)
	{
		this.frequencia = frequencia;
	}

	public void gerarMediaParcial()
	{
		this.mediaParcial = nota1.add(nota2).add(nota3).divide(TRES, RoundingMode.HALF_EVEN);
	}

	public Boolean possuiFrequenciaInsuficiente() {
		return this.frequencia < 75;
	}

	public Boolean estaReprovado() {
		return this.mediaParcial.compareTo(TRES) < 0;
	}

	public Boolean estaEmRecuperacao() {
		Boolean possuiMediaDeRecuperacao = this.mediaParcial.compareTo(TRES) >= 0 && this.mediaParcial.compareTo(CINCO) < 0;
		Boolean possuiNotaInsuficiente = this.mediaParcial.compareTo(CINCO) >= 0 && this.mediaParcial.compareTo(SETE) < 0 && (nota1.compareTo(TRES) < 0 || nota2.compareTo(TRES) < 0 || nota3.compareTo(TRES) < 0);
		return possuiMediaDeRecuperacao || possuiNotaInsuficiente;
	}

	public Boolean estaAprovado() {
		return this.mediaParcial.compareTo(SETE) >= 0;
	}
}
