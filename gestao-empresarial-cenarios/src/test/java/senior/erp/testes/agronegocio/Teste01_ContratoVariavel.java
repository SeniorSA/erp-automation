package senior.erp.testes.agronegocio;

import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.MetodosComuns;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.suprimentos.Form460PFOComponentNames;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;


public class Teste01_ContratoVariavel {

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
		SistemaSenior.abrirTela("USERMENU_0_1_NF460PFO_SCTR");
		SistemaSenior.teclar(Tecla.ENTER);
		SistemaSenior.preencherCampo("DECodFor", "120.034");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.teclar(3, Tecla.TAB);
		SistemaSenior.preencherCampo("DETipVlz", "2");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEConQtd", "N");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECtrPrg", "N");
		SistemaSenior.teclar(6, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodFcr", "120");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodMoe", "120");
		SistemaSenior.preencherCampo("DECodApl", "1");
		SistemaSenior.teclar(Tecla.TAB);		
		SistemaSenior.preencherCampo("DEIniVig", "20/11/2018");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEFimVig", "05/12/2018");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECodCpg", "015");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodTra", "100");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DETnsPro", "90421");
		SistemaSenior.preencherCampo("DECodSaf", "2018/2018");
		SistemaSenior.teclar(7, Tecla.TAB);
		SistemaSenior.selecionarGuia(Form460PFOComponentNames.TS_AGRUPADOR_DAS_ABAS, "Produtos");

		SistemaSenior.preencherNovaLinhaGrade("GridCcp", "Produto","10006.0001", "Derivação","001", 
				"Qtde UM Fornecedor", "50.000,00000", "Preço UM Fornecedor", "1", "Preço UM Fornecedor", "1,0000000000", 
				"Valor Cotação", "0,0000000000");
		SistemaSenior.clicar("BtAcrescFinanc");// Acrésc. Financ. (&7)
		SistemaSenior.posicionarLinhaGradePorValor("GridIxA", "Sel.", CaixaAtribuicao.DESMARCADO, "Item Classificação",
				"101", "Descrição (Item Classificação)", "Complemento de Preço", "Sit.", "I", "Usuário Geração", "0");
			
		SistemaSenior.preencherNovaLinhaGrade("GridFxI", "Tipo", "V", "Descrição (Tipo)", "Valor",
				"Uni. Acrés. Financ.", "SC", "Valor Acrésc. Finan.", "1,00000", "% Acrésc. Financ.", "0,0000",
				"Agrupar itens", "N", "Uni. Acrés. Financ.", "K.G");
		
		SistemaSenior.conferirCaixaMensagem("Aviso", "É necessário informar o valor para o acréscimo financeiro.", "OK");
		
		SistemaSenior.posicionarLinhaGradePorValor("GridFxI", "Uni. Acrés. Financ.", "SC", "Valor Acrésc. Finan.", "1");
		
		/*
		 * Codigo abaixo comentado gerado pel o FAST original
		SistemaSenior.preencherNovaLinhaGrade("GridFxI", "Sel.", CaixaAtribuicao.MARCADO, "Tipo", "V",
				"Valor Acrésc. Finan.", "1", "Valor Acrésc. Finan.", "1,00000", "Agrupar itens", "N",
				"Uni. Acrés. Financ.", "K.G");
		
		SistemaSenior.preencherLinhaCorrenteGrade("GridFxI", "Uni. Acrés. Financ.", "SC", 
				"Valor Acrésc. Finan.", "1", "Tipo", "V");
				
		SistemaSenior.posicionarLinhaGradePorValor("GridFxI", "Tipo", "V", "Descrição (Tipo)", "Valor",
				"Uni. Acrés. Financ.", "SC", "Valor Acrésc. Finan.", "1,00000", "% Acrésc. Financ.", "0,0000",
				"Agrupar itens", "N", "Sit.", "A", "Tipo Faturamento", "2", "Descrição (Tipo Faturamento)",
				"Na fixação");
		*/	
		
		SistemaSenior.clicar("BtnProcessar");// &Processar
		SistemaSenior.clicar("BtnSair");// &Sair
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.clicar("Aprovar");// Liber&ar
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.clicar("Aprovar");// &Aprovar
		SistemaSenior.preencherCampo("DEObjCtr", " ");
	}

}