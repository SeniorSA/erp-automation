package senior.erp.testes.financas.contasReceber;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.financas.Form301TCRComponentNames;

/**
 * Cenário para entrada e manutenção de títulos do contas a receber;
 * 
 */

public class TCFRCR0001_EntradaManual {

	/**
	 * 
	 */
	@BeforeClass
	public static void preExecucaoCasodeTeste() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.WURSCHKE);
	}

	@AfterClass
	public static void posExecucaoCasodeTeste() {
		SistemaSenior.finalizarSistema();
	}

	@Before
	public void preExecucaoCenario() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	@After
	public void posExecucaoCenario() {
		MetodosComunsNucleo.reverterTransacao();
	}

	@Test
	public void EntradaManual() {
		MetodosComuns.selecionarEmpresaFilial(1, 1);
		SistemaSenior.abrirTela("NF301TCR_FRCR");
		SistemaSenior.preencherCampo("DENumTit1", "TESTE001");
		SistemaSenior.preencherCampo("DECodTpt1", "REC");
		SistemaSenior.teclar(4, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodCli1", "404709");
		SistemaSenior.teclar(5, Tecla.TAB);
		SistemaSenior.preencherCampo("DMObsTcr1", "TESTE DE ENTRADA MANUAL PARA O FAST", Tecla.TAB);
		SistemaSenior.preencherCampo("DECodNtg1", "168", Tecla.TAB);
		SistemaSenior.preencherCampo("DECtaFin1", "1150", Tecla.TAB);
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECodCcu1", "3660", Tecla.TAB);
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEVlrOri1", "1500.00", Tecla.TAB);
		SistemaSenior.teclar(38, Tecla.TAB);
		SistemaSenior.clicar("BtnAlterar");// &Inserir

		SistemaSeniorComTransacao.executarSQLQuery("select * from e301tcr " + "where CodEmp = 1 " + "and CodFil = 1 "
				+ "and NumTit = 'TESTE001' " + "and CodCli = 404709 " + "and CodTpt = 'REC' " + "and VlrOri = 1500.00 ",
				1);

		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.clicar("BtnExcluir");// &Excluir
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.

		SistemaSeniorComTransacao.executarSQLQuery("select * from e301tcr " + "where CodEmp = 1 " + "and CodFil = 1 "
				+ "and NumTit = 'TESTE001' " + "and CodCli = 404709 " + "and CodTpt = 'REC' " + "and VlrOri = 1500.00 ",
				0);
	}
}