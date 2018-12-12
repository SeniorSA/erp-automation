package senior.erp.testes.cadastros.controladoria;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;

import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste004_AlteracaoParametrosFiscais {
 	
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
	public void testScenario01(){ 
	SistemaSenior.abrirTela("NF075PFF");
	SistemaSenior.preencherCampo("ECodPro", "60012.0043");
	SistemaSenior.clicar("Mostrar");//&Mostrar
	SistemaSenior.posicionarLinhaGradePorValor("GridPpf");
 	SistemaSenior.preencherLinhaCorrenteGrade("GridPpf", "Ori. Fiscal Merc.","5", "C�d. ICMS Especial","017");
	SistemaSenior.posicionarLinhaGradePorValor("GridPpf","Gravar",CaixaAtribuicao.MARCADO,"Produto","60012.0043");
	SistemaSenior.clicar("Processar");//&Processar
    SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma Atualiza��o dos Produtos/Deriva��es Marcadas?", "&Sim");
	SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Atualiza��o do Produtos/Deriva��es Ok! ", "&Ok");
	SistemaSenior.fecharTela("F075PFF");	 
	
	SistemaSeniorComTransacao.executarSQLQuery("select * from e075pro "
			+ "where CodEmp = 1 "
			+ "and CodPro = '60012.0043' "
			+ "and OriMer = '5' "
			+ "and CodTic = '017' ",
			1);	
	} 

    @Test 
	public void testScenario02(){ 
	SistemaSenior.abrirTela("NF075PFF");
	SistemaSenior.preencherCampo("ECodPro", "50058.0002");
	SistemaSenior.clicar("Mostrar");//&Mostrar
	SistemaSenior.posicionarLinhaGradePorValor("GridPpf");
		SistemaSenior.preencherLinhaCorrenteGrade("GridPpf", "Ori. Fiscal Merc.","0", "C�d. ICMS Especial","069","Red. Impostos"," ");
	SistemaSenior.posicionarLinhaGradePorValor("GridPpf","Gravar",CaixaAtribuicao.MARCADO,"Produto","50058.0002");
	SistemaSenior.clicar("Processar");//&Processar
	SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma Atualiza��o dos Produtos/Deriva��es Marcadas?", "&Sim");
	SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Atualiza��o do Produtos/Deriva��es Ok! ", "&Ok");
	SistemaSenior.fecharTela("F075PFF");	 
	
	SistemaSeniorComTransacao.executarSQLQuery("select * from e075pro "
			+ "where CodEmp = 1 "
			+ "and CodPro = '50058.0002' "
			+ "and OriMer = '0' "
			+ "and CodTic = '069' "
			+ "and CodTrd = ' ' ",
			1);	
	} 
}
