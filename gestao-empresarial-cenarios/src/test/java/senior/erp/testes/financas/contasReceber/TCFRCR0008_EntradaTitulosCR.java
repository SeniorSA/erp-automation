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
import senior.erp.componentNames.financas.Form301TCRComponentNames;

/**
 * Cenário para entrada e manutenção de títulos do contas a receber;
 * 
 * Pré-condições:
 * Utilizar a base: erpfast;
 * Usuário e senha: financas1/financas1;
 * Empresa e filial: 151/1
 * Proprietária: padrão
 * 
 * @Tarefa: 352835
 * @author: Luciano.Marcos
 */

public class TCFRCR0008_EntradaTitulosCR {

    /**
     * 
     */
    @BeforeClass
    public static void preExecucaoCasodeTeste() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.MARIAELY);
    }

    /**
     * Descreva aqui o que você precisa fazer depois de executar todos os testes.
     */
    @AfterClass
    public static void posExecucaoCasodeTeste() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Descreva aqui o que você precisa fazer antes de executar cada teste.
     */
    @Before
    public void preExecucaoCenario() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Descreva aqui o que você precisa fazer depois de executar cada teste.
     */
    @After
    public void posExecucaoCenario() {
        MetodosComunsNucleo.reverterTransacao();
    }

    /**
     * 
     */
    @Test
    public void testScenario0001() {
    	String codigoCliente = "304931";
    	int numeroTitulo = MetodosComuns.retornaNumero();
    	MetodosComuns.selecionarEmpresaFilial(1, 1);
    	SistemaSenior.abrirTela(Form301TCRComponentNames.FR_NOME);
    	SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_TITULO, Integer.toString(numeroTitulo), Tecla.TAB); //Nº Título
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_TIPO_TITULO, "REC", Tecla.TAB); //Tipo Título
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_TRANSACAO, "90300", Tecla.TAB); //Transação
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_DATA_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB); //Data Emissão
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_DATA_ENTRADA, SistemaSenior.dataAtual(), Tecla.TAB); //Data Entrada
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_CLIENTE, codigoCliente, Tecla.TAB); //Cliente
        SistemaSenior.teclar(5, Tecla.TAB);
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_NAT_GASTO, "036", Tecla.TAB); //Natureza Gasto
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_CENTRO_CUSTOS, "3660", Tecla.TAB); //Centro de Custos
        SistemaSenior.teclar(1, Tecla.TAB);
        SistemaSenior.preencherCampo(Form301TCRComponentNames.FD_VALOR_ORIGINAL, "2500.00", Tecla.TAB); //Valor Original
        SistemaSenior.teclar(39, Tecla.TAB);
        MetodosComuns.fecharTela(Form301TCRComponentNames.FR_NOME);
        
        /**
         * Validação da geração do Título a Receber tipo 'CHQ'
         */
        SistemaSeniorComTransacao.executarSQLQuery("select * from e301tcr where CodEmp = 1 and CodFil = 1 and NumTit = ? and CodCli = ? and CodTpt = 'REC' and VlrOri = 2500.00 and datent=?" , 1, SistemaSenior.parametroSql(TipoParametroSQL.STRING, Integer.toString(numeroTitulo)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
     }

   
}
