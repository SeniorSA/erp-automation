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

public class TCFRCR0007_InclusaoChequeApop {

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
    public void testInclusaoChequeApop() {
        MetodosComuns.selecionarEmpresaFilial(1, 1);

    	String codigoCliente = "304931";
    	int numeroTitulo = MetodosComuns.retornaNumero();
    	MetodosComuns.selecionarEmpresaFilial(1, 1);
    	SistemaSenior.abrirTela("F301TCR_FRCR");
        SistemaSenior.preencherCampo("DENumTit1", Integer.toString(numeroTitulo), Tecla.TAB); //Nº Título
        SistemaSenior.preencherCampo("DECodTpt1", "CHQ", Tecla.TAB); //Tipo Título
        SistemaSenior.preencherCampo("DECodTns1", "90312", Tecla.TAB); //Transação
        SistemaSenior.preencherCampo("DEDatEmi1", SistemaSenior.dataAtual(), Tecla.TAB); //Data Emissão
        SistemaSenior.preencherCampo("DEDatEnt1", SistemaSenior.dataAtual(), Tecla.TAB); //Data Entrada
        SistemaSenior.preencherCampo("DECodCli1", codigoCliente, Tecla.TAB); //Cliente
        SistemaSenior.teclar(10, Tecla.TAB);
        SistemaSenior.preencherCampo("DEVlrOri1", "1000.00", Tecla.TAB); //Valor Original
        SistemaSenior.teclar(25, Tecla.TAB);
        SistemaSenior.preencherCampo("DECheBan1", "001", Tecla.TAB);	
        SistemaSenior.preencherCampo("DECheAge1", "859", Tecla.TAB);
        SistemaSenior.preencherCampo("DECheCta1", "25445", Tecla.TAB);
        SistemaSenior.preencherCampo("DECheNum1", "125633", Tecla.TAB);
        SistemaSenior.teclar(10, Tecla.TAB);
        SistemaSenior.conferirCaixaMensagem("Advertência", "Regra 89: Título APOP incluído com sucesso!", "&OK");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Regra 89: Imprimir Recibo? ", "&Não");
        MetodosComuns.fecharTela("F301TCR_FRCR");
        
        /**
         * Validação da geração do Título a Receber tipo 'CHQ'
         */
        SistemaSeniorComTransacao.executarSQLQuery("select * from e301tcr where CodEmp = 1 and CodFil = 1 and NumTit = ? and CodCli = ? and CodTpt = 'CHQ' and VlrOri = 1000.00 and datent=?" , 1, SistemaSenior.parametroSql(TipoParametroSQL.STRING, Integer.toString(numeroTitulo)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
     
        /**
         * Validação da geração do Título a Pagar tipo 'APO'
         */
        SistemaSeniorComTransacao.executarSQLQuery("select * from E501Tcp where CodEmp = 1 and CodFil = 1 and NumTit = ? and CodFor = ? and CodTpt = 'APO' and VlrOri = 1000.00 and datent=?" , 1, SistemaSenior.parametroSql(TipoParametroSQL.STRING, Integer.toString(numeroTitulo)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        
    }
}


