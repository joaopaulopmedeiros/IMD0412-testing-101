package teste;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matricula
{
	private static final BigDecimal TRES = BigDecimal.valueOf(3l);

	private static final BigDecimal QUATRO = BigDecimal.valueOf(4l);

	private static final BigDecimal SEIS = BigDecimal.valueOf(6l);

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
			else if (this.estaAprovadoPorNota()) 
			{
				this.status = StatusAprovacao.APRN;
			}
			else if(this.estaEmRecuperacao())
			{
				this.status = StatusAprovacao.REC;
			}
			else
			{
				this.status = StatusAprovacao.REP;
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

	public Boolean possuiFrequenciaInsuficiente() 
	{
		return this.frequencia < 75;
	}

	/**
	 * É considerado aprovado, quanto à avaliação do rendimento acadêmico, oestudanteque tem média parcial igual ou superior a 6,0 (seis)
	 * @return Boolean
	 */
	public Boolean estaAprovado() 
	{
		return this.mediaParcial.compareTo(SEIS) >= 0;
	}

	/**
	 * É considerado aprovado, quanto à avaliação de aprendizagem, o estudante com:
	 * rendimento acadêmico igual ou superiora 4,0(quatro) em todas as unidades.
	 * @return Boolean
	 */
	public Boolean estaAprovadoPorNota() 
	{		
		return this.nota1.compareTo(QUATRO) >= 0 && this.nota2.compareTo(QUATRO) >= 0 && this.nota3.compareTo(QUATRO) >= 0;
	}

	/**
	 * O estudante que não atinge os critérios de aprovação definidos no artigo 105 tem direito à realização de uma avaliação de reposição se tiver média parcial igual ou superior a 3,0 (três). 
	 * @return Boolean
	 */
	public Boolean estaEmRecuperacao() 
	{
		return !this.estaAprovado() && !this.estaAprovadoPorNota() && this.mediaParcial.compareTo(TRES) >= 0;
	}
	
	/**
	 * O estudante que não atinge os critérios de aprovação definidos no artigo 105 e que não pode realizar avaliação de reposição é considerado reprovado,
	 * @return Boolean
	 */
	public Boolean estaReprovado() 
	{
		return !this.estaAprovado() && !this.estaAprovadoPorNota() && !this.estaEmRecuperacao();
	}
}
