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

/**
 * Testar a inclus�o de cheque com gera�ao de t�tulo do APOP
 *
 */

public class TCFRCR0007_InclusaoChequeApop {

    /**
     * Inicializa o sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.MARIAELY);
    }

    /**
     * Finaliza o sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transa��o para cada cen�rio de teste.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transa��o para cada cen�rio de teste.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao();
    }
    
    /**
     * Inclus�o de cheque para aplica��o de apop.
     * Utilizar transa��o espec�fica 90312 para gera��o do t�tulo a pagar tipo APO
     */
    @Test
    public void testInclusaoChequeApop() {
        MetodosComuns.selecionarEmpresaFilial(1, 1);

    	String codigoCliente = "304931";
    	int numeroTitulo = MetodosComuns.retornaNumero();
    	MetodosComuns.selecionarEmpresaFilial(1, 1);
    	SistemaSenior.abrirTela("F301TCR_FRCR");
        SistemaSenior.preencherCampo("DENumTit1", Integer.toString(numeroTitulo), Tecla.TAB); //N� T�tulo
        SistemaSenior.preencherCampo("DECodTpt1", "CHQ", Tecla.TAB); //Tipo T�tulo
        SistemaSenior.preencherCampo("DECodTns1", "90312", Tecla.TAB); //Transa��o
        SistemaSenior.preencherCampo("DEDatEmi1", SistemaSenior.dataAtual(), Tecla.TAB); //Data Emiss�o
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
        SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Regra 89: T�tulo APOP inclu�do com sucesso!", "&OK");
        SistemaSenior.conferirCaixaMensagem("Confirma��o", "Regra 89: Imprimir Recibo? ", "&N�o");
        MetodosComuns.fecharTela("F301TCR_FRCR");
        
        /**
         * Valida��o da gera��o do T�tulo a Receber tipo 'CHQ'
         */
        SistemaSeniorComTransacao.executarSQLQuery("select * from e301tcr where CodEmp = 1 and CodFil = 1 and NumTit = ? and CodCli = ? and CodTpt = 'CHQ' and VlrOri = 1000.00 and datent=?" , 1, SistemaSenior.parametroSql(TipoParametroSQL.STRING, Integer.toString(numeroTitulo)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
     
        /**
         * Valida��o da gera��o do T�tulo a Pagar tipo 'APO'
         */
        SistemaSeniorComTransacao.executarSQLQuery("select * from E501Tcp where CodEmp = 1 and CodFil = 1 and NumTit = ? and CodFor = ? and CodTpt = 'APO' and VlrOri = 1000.00 and datent=?" , 1, SistemaSenior.parametroSql(TipoParametroSQL.STRING, Integer.toString(numeroTitulo)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        
    }
}


