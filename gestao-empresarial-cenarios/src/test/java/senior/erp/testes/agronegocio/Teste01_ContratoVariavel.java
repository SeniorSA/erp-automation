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

		SistemaSenior.preencherNovaLinhaGrade("GridCcp", "Produto","10006.0001", "Deriva��o","001", 
				"Qtde UM Fornecedor", "50.000,00000", "Pre�o UM Fornecedor", "1", "Pre�o UM Fornecedor", "1,0000000000", 
				"Valor Cota��o", "0,0000000000");
		SistemaSenior.clicar("BtAcrescFinanc");// Acr�sc. Financ. (&7)
		SistemaSenior.posicionarLinhaGradePorValor("GridIxA", "Sel.", CaixaAtribuicao.DESMARCADO, "Item Classifica��o",
				"101", "Descri��o (Item Classifica��o)", "Complemento de Pre�o", "Sit.", "I", "Usu�rio Gera��o", "0");
		
		SistemaSenior.preencherLinhaCorrenteGrade("GridIxA", "Sel.", CaixaAtribuicao.MARCADO);	
		
		SistemaSenior.posicionarLinhaGradePorValor("GridIxA", "Sel.", CaixaAtribuicao.MARCADO, "Item Classifica��o",
				"101");
	
		SistemaSenior.posicionarLinhaGradePorValor("GridFxI");

		
		SistemaSenior.preencherNovaLinhaGrade("GridFxI", "Tipo", "V", "Valor Acr�sc. Finan.", "1,00000", "Uni. Acr�s. Financ.", "SC");
	
		SistemaSenior.clicar("BtnProcessar");// &Processar
		
     	SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma processamento?", "&Sim");

     	SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Processamento realizado com sucesso!", "&Ok");

		SistemaSenior.clicar("BtnSair");// &Sair
		SistemaSenior.clicar("Aprovar");// Liberar
    	SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Contrato liberado para aprova��o com sucesso!", "Ok");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Contrato est� em an�lise.", "Ok");	
    	
    	
	}
}