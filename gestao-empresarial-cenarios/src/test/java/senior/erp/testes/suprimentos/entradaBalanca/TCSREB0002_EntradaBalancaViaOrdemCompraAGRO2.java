package senior.erp.testes.suprimentos.entradaBalanca;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;

/**
 * Cen�rio que trata o recebimento de mercadorias com pesagem em balan�a via Ordem de Compra.
 * 
 * Pr�-condi��es:
 * Utilizar a base: erpfast;
 * Usu�rio e senha: suprimentos/suprimentos;
 * Empresa e filial: 50/1
 * Propriet�ria: padr�o
 * 
 * @author: Carlos Erig
 * @Data: 10/08/2017
 */

public class TCSREB0002_EntradaBalancaViaOrdemCompraAGRO2 {

    /**
	 * 
	 */
    @BeforeClass
    public static void preExecucaoCasodeTeste() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
    }

    /**
     * Descreva aqui o que voc� precisa fazer depois de executar todos os testes.
     */
    @AfterClass
    public static void posExecucaoCasodeTeste() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Descreva aqui o que voc� precisa fazer antes de executar cada teste.
     */
    @Before
    public void preExecucaoCenario() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Descreva aqui o que voc� precisa fazer depois de executar cada teste.
     */
    @After
    public void posExecucaoCenario() {
        MetodosComunsNucleo.reverterTransacao();
    }

    /**
     * Recebimento de mercadorias com pesagem.
     */
    @Test
    public void teste0001_EntradaBalanca() {
        MetodosComunsNucleo.selecionarEmpresaFilial(1, 19);
        SistemaSenior.abrirTela("F435CCC");
        SistemaSenior.conferirCaixaMensagem("Aviso", "DLL da Interface com a balan�a n�o foi carregada", "OK");
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "ASD0000", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("ECpfMot", "000.000.000-00", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("ENomMot", "TESTE", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("ECodFor", "104903", Tecla.TAB); //Fornecedor	
        SistemaSenior.preencherCampo("ESeqOrm", "1", Tecla.TAB); //Seq. Ori. Merc.		
        SistemaSenior.preencherCampo("ECodDep", "D019-04", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ECodPro", "10006.0001", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ECodDer", "001", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ENfcPro", "200", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ESnfPro", "1", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ETnsPro", "90421", Tecla.TAB); //Dep�sito
        SistemaSenior.preencherCampo("ECodSaf", "2017/2017", Tecla.TAB); //Safra	
        SistemaSenior.preencherCampo("ECodTrg", "", Tecla.TAB); //Safra	
        SistemaSenior.marcarCaixaAtribuicao("CBEmitirNota"); // Emitir nota
        SistemaSenior.clicar("Mostrar");
        SistemaSenior.preencherCampo("EPesInf", "20000", Tecla.TAB); //Peso
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Processado com sucesso!", "OK");
        MetodosComuns.fecharTela("PrevForm");
        SistemaSenior.preencherCampo("EPlaVei", "ASD0000", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("EPesInf", "5000", Tecla.TAB); //. Peso
        //Grid Classifica��o
        SistemaSenior.posicionarLinhaGradePorValor("GridIcl", "Item Classifica��o", "2");
        SistemaSenior.preencherLinhaCorrenteGrade("GridIcl", "% Apurado", "15,0000");
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Processado com sucesso!", "OK");
        
        //Valida��o
        /*
        SistemaSeniorComTransacao.executarSQLQuery("select * from e115ces where codemp=1 and codfil=19 and plavei='ASD0000' and codfor='104903'  and peseni='20.000' and pessai='5.000' and codsaf='2017/2017' and tnspro='90421' and sitces=1 and numocp=0 and filocp=0 and numtic=(select max (numtic) from e115ces where codemp=1 and codfil=19) and datent=?", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        SistemaSeniorComTransacao.executarSQLQuery("select 4 from e115pro where codemp=1 and codfil=19 and codpro='10006.0001' and codder='001' and coddep='D019-004' and qtdinf='5.00000' and bruinf='5.00000' and numocp=0 and filocp=0 and datent=?", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        SistemaSeniorComTransacao.executarSQLQuery("select 5 from e440nfc where codemp=1 and codfil=19 and codfor=104903 and datent=?", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        */
    }
}
