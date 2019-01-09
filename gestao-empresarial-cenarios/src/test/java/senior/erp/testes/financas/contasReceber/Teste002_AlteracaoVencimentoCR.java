package senior.erp.testes.financas.contasReceber;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste002_AlteracaoVencimentoCR {

	@BeforeClass  /** Inicializa o sistema. */
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}
	
	@AfterClass  /** Finaliza o sistema. */
	public static void tearDownClass() {
		SistemaSenior.finalizarSistema();
	}
	
	@Before     /** Inicializa transa��o para cada cen�rio de teste. */
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}
	
	@After     /** Reverte transa��o para cada cen�rio de teste. */
	public void tearDown() {
		MetodosComunsNucleo.reverterTransacao();
	}

	@Test
	public void AlteracaoVencimentoCR() {
		MetodosComuns.selecionarEmpresaFilial(1, 1);
		SistemaSenior.abrirTela("NF301AVP_FRCR");
		SistemaSenior.preencherCampo("ECodCli", "120442", Tecla.TAB);
		SistemaSenior.teclar(7, Tecla.TAB);
		SistemaSenior.teclar(Tecla.F4);	
		SistemaSenior.teclar(Tecla.ENTER);			
	 //SistemaSenior.preencherCampo("ENovVct", "08/09/2018");
		SistemaSenior.teclar(3, Tecla.TAB);
		SistemaSenior.clicar("Selecao");// Sele��o
		SistemaSenior.preencherCampo("SAVCodFil", "1", Tecla.TAB);
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("SAVCodTpt", "DUP", Tecla.TAB);
		SistemaSenior.clicar("Ok");// &Ok
		SistemaSenior.clicar("Mostrar");// &Mostrar
		SistemaSenior.preencherLinhaCorrenteGrade("GridAvp", "Sel", CaixaAtribuicao.MARCADO);
		SistemaSenior.clicar("Processar");// &Processar
		SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma processamento?", "&Sim"); // Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Processamento realizado com sucesso!", "Ok"); 			
		SistemaSenior.fecharTela("F301AVP");
	}

}