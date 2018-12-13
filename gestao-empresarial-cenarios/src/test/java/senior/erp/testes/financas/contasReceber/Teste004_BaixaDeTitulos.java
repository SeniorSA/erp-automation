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

import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste004_BaixaDeTitulos {
 	
	@BeforeClass  /** Inicializa o sistema. */
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}
	
	@AfterClass  /** Finaliza o sistema. */
	public static void tearDownClass() {
		SistemaSenior.finalizarSistema();
	}
	
	@Before     /** Inicializa transação para cada cenário de teste. */
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}
	
	@After     /** Reverte transação para cada cenário de teste. */
	public void tearDown() {
		MetodosComunsNucleo.reverterTransacao();
	}

	@Test 
	public void testScenario01(){ 
		SistemaSenior.abrirTela("NF301BCD_FRCR");
		SistemaSenior.teclar(3, Tecla.TAB);
		SistemaSenior.preencherCampo("ECodCli", "105.713");
		SistemaSenior.clicar("Mostrar");//&Mostrar
		SistemaSenior.posicionarLinhaGradePorValor("GridBai","Sel.",CaixaAtribuicao.DESMARCADO,"Filial","1","Cliente","105.713","Título","1118514-01","Tipo ","DUP");
		SistemaSenior.preencherLinhaCorrenteGrade("GridBai", "Sel.",CaixaAtribuicao.MARCADO);
		SistemaSenior.selecionarGuia("Orelhas", "Cheques");
		SistemaSenior.preencherNovaLinhaGrade("GridChe", "Cliente","105713","Cliente","105.713","Filial","1","Banco","104","Agência","0968","Conta","33455","Cheque","12345","Valor","500","Valor","500,00","CNPJ/CPF Sacado","24102954953","CNPJ/CPF Sacado","00024102954953","Vencimento","17/12/2018");
		SistemaSenior.posicionarLinhaGradePorValor("GridChe","Cliente","105.713","Filial","1","Banco","104","Agência","0968","Conta","33455","Cheque","12345","Valor","500,00");
		SistemaSenior.clicar("Selecao");//S&eleção
		SistemaSenior.preencherCampo("ECtaDpr", "01CAIXA 01");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.clicar("Ok");//&Ok
		SistemaSenior.clicar("Processar");//&Processar
	    SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja devolver saldo ao cliente?", "&Sim");
	    SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "&Sim");
	    SistemaSenior.conferirCaixaMensagem("Confirmação", "Baixas processadas com sucesso. Lote Gerado: \"13122018F301BCD1\". Deseja imprimir recibo para o cliente? ", "&Nao");
	    SistemaSenior.conferirCaixaMensagem("Advertência", "Baixas processadas com sucesso! Lote gerado: 13122018F301BCD1", "&Ok");
		SistemaSenior.fecharTela("F301BCD");
	}  
}