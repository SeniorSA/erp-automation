package senior.erp.testes.suprimentos.notasFiscaisEntrada;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste001_EntradaCFOP1253 {
 
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
    MetodosComuns.selecionarEmpresaFilial(1, 1);		
	SistemaSenior.abrirTela("NF440GNE_SRNF");
	SistemaSenior.teclar(Tecla.TAB);
	SistemaSenior.preencherCampo("ECgcCpf", "4368898000106");
	SistemaSenior.preencherCampo("ENumNfc", "74901");
	SistemaSenior.preencherCampo("ECodSnf", "1");
	SistemaSenior.teclar(1, Tecla.TAB);	
	SistemaSenior.preencherCampo("EVlrInf", "800,00");
	SistemaSenior.teclar(4, Tecla.TAB);
	SistemaSenior.preencherCampo("ETnsPro", " ");
	SistemaSenior.teclar(Tecla.TAB);
	SistemaSenior.preencherCampo("ETnsSer", "1253");
	SistemaSenior.selecionarGuia("TNItens", "Serviços");
	SistemaSenior.preencherLinhaCorrenteGrade("GridSer", "Transação","1253","Filial O.C.","0","Serviço","70021.0007","Qtde. Recebida","1","Preço Unitário","1000","Valor Desconto","200");
	SistemaSenior.posicionarLinhaGradePorValor("SwwDBGridContas","Financeira","1.620","Contábil","0");
	SistemaSenior.clicar("Ok");//&Ok
	SistemaSenior.selecionarGuia("TNItens", "Parcelas");
	SistemaSenior.posicionarLinhaGradePorValor("GridPar","Parcela","1","Num Título","74901.01","Tipo Título","ENE");
	SistemaSenior.preencherLinhaCorrenteGrade("GridPar", "Vencimento","31/01/2025");
	SistemaSenior.clicar("Fechar");//&Fechar	
	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
	SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Fechada com Sucesso. ", "Ok");
	SistemaSenior.clicar("Sair");// &Sair	
	
	SistemaSeniorComTransacao.executarSQLQuery("select * from e440isc "
			+ "where CodEmp = 1 "
			+ "and CodFil = 1 "
			+ "and NumNfc = 74901 "
			+ "and CodSnf = '1' "
			+ "and SeqIsc = 1"
			+ "and PerIcm = 29",
			1);	
	
	SistemaSeniorComTransacao.executarSQLQuery("select * from e440isc "
			+ "where CodEmp = 1 "
			+ "and CodFil = 1 "
			+ "and NumNfc = 74901 "
			+ "and CodSnf = '1' "
			+ "and SeqIsc = 1"
			+ "and vlrBic = 800",
			1);		
	
	} 
}
