package senior.erp.testes.agronegocio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComuns;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.suprimentos.Form460PFOComponentNames;

public class Teste01_EntradaRomaneio {

	@BeforeClass
	public static void preExecucaoCasodeTeste() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
		MetodosComuns.selecionarEmpresaFilial(1, 4);
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
		MetodosComuns.reverterTransacao();
	}

	@Test
	public void testScenario01() {
		SistemaSenior.abrirTela("MFSelFil");
		SistemaSenior.clicar("BtnOk");// &Ok
		SistemaSenior.abrirTela("USERMENU_0_1_NF460PFO_SCTR");
		SistemaSenior.teclar(Tecla.ENTER);
		SistemaSenior.preencherCampo("DECodFor", "120.034");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.teclar(3, Tecla.TAB);
		SistemaSenior.preencherCampo("DETipVlz", "1");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEConQtd", "S");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECtrPrg", "S");
		SistemaSenior.teclar(6, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodFcr", "120");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodMoe", "120");
		SistemaSenior.preencherCampo("DECodApl", "2");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEIniVig", "13/11/2018");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEFimVig", "31/12/2018");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEVctTit", "05/01/2019");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodTra", "100");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DETnsPro", "90421");
		SistemaSenior.preencherCampo("DECodSaf", "2018/2018");
		SistemaSenior.teclar(7, Tecla.TAB);
		SistemaSenior.preencherCampo("DEObsMot", " ");
		SistemaSenior.selecionarGuia(Form460PFOComponentNames.TS_AGRUPADOR_DAS_ABAS, "Produtos");
		SistemaSenior.preencherNovaLinhaGrade("GridCcp", "Produto","10006.0001", "Derivação","001", 
				"Qtde UM Fornecedor", "50.000,00000", "Preço UM Fornecedor", "1", "Preço UM Fornecedor", "1,0000000000",
				"Valor Cotação", "0,5000000000");	
//		SistemaSenior.teclar(39, Tecla.SETA_CIMA);
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("Aprovar");// Liber&ar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.clicar("Aprovar");// &Aprovar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.preencherCampo("DEObjCtr", " ");
	}

}