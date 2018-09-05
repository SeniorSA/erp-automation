package senior.erp.testes.suprimentos.faturamentoDoLeite;

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
*/
import senior.erp.componentNames.mercado.Form140CANComponentNames;
import senior.erp.componentNames.suprimentos.Form070FR2ComponentNames;

/**
 * Testar a geração de Nota Fiscal do Faturamento do Leite
 *
 */

public class TCSUP0001_ValidarNotaLeite {

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
     * Inicializa transação para cada cenário de teste.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação para cada cenário de teste.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao();
    }
    
    /**
     * Gerar nova Nota Fiscal de Leite.
     * Carregar a tela com o ultimo processmento, mudar a situação da nota para 4 e limpar os dados da Nota fiscal
     * Clicar e Farurar e sair da tela
     */
    @Test
    public void testValidacaoNFLeite() {
    	MetodosComuns.selecionarEmpresaFilial(1, 1);
    	SistemaSenior.abrirTela(Form070FR2ComponentNames.FR_NOME);
    	SistemaSenior.preencherCampo(Form070FR2ComponentNames.FD_MES_REF, "06/2017", Tecla.TAB);
    	SistemaSenior.preencherCampo(Form070FR2ComponentNames.FD_CONDICAO_FAT, "N", Tecla.TAB);
    	SistemaSenior.preencherCampo(Form070FR2ComponentNames.FD_UNIDADE, "3", Tecla.TAB);
    	SistemaSenior.preencherCampo(Form070FR2ComponentNames.FD_LINHA, "1", Tecla.TAB);
    	SistemaSenior.preencherCampo(Form070FR2ComponentNames.FD_LATICINIO, "10", Tecla.TAB);
    	SistemaSenior.preencherLinhaCorrenteGrade("Grd_Usu_T070FR2", "Situacao", "4", "Filial Nfc", "", "Fornec Nfc", "", "Num Nfc", "", "Serie Nfc", "");
    	SistemaSenior.clicar(Form070FR2ComponentNames.BT_ALTERAR);
    	SistemaSenior.clicar(Form070FR2ComponentNames.BT_FATURAR);
    	
    	SistemaSenior.clicar(Form070FR2ComponentNames.BT_SAIR);
    	
    	/**
        * Validação da nota gerada
        */
    	                                            
    	SistemaSeniorComTransacao.executarSQLQuery("select * from usu_t070fr2 where Usu_CodEmp = 1 and Usu_CodUni = 3 and Usu_CodLin =1 and Usu_CodLat = 10 and usu_mesRef =? and usu_datger = ? and Usu_ImpFri = 5" , 1,SistemaSenior.parametroSql(TipoParametroSQL.DATE, "01/06/2017"), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
    	
    	/**
         * Emissão da Nota na tela de Mercadp
        */
    	String[][] notaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("Select NumNfv from E140nfv where codemp=1 and codfil=1 and codsnf='NFE' and datemi = ? and TnsPro = '1102L' and codcli =105616 and UsuGer = 16",  SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
    	String numeroNotaGerada = notaGerada[0][0];
    	
    	SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);
    	SistemaSenior.teclar(Tecla.TAB);
    	SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, numeroNotaGerada, Tecla.TAB);
    	SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, numeroNotaGerada, Tecla.TAB);
    	SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
    	SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração do arquivo de Nota Eletrônica?", "&Sim");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "O arquivo do documento eletrônico foi gerado com sucesso.", "&OK");
     	SistemaSenior.fecharTela(Form140CANComponentNames.FR_NOME);
    }
}
