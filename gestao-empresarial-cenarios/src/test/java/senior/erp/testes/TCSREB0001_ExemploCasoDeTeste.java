package senior.erp.testes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.AssertivaERP;
import senior.erp.ReexecutarTeste.Reexecutar;
import senior.erp.SystemMessageTitles;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.componentNames.mercado.Form140PREComponentNames;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;
import senior.erp.utils.FacadeSuprimentos;

/**
 * Cenário que trata o assunto XYZ.
 * 
 * Pré-condições: XYZ.
 *
 */
public class TCSREB0001_ExemploCasoDeTeste {

    @Rule
    public Reexecutar reexecutar = new Reexecutar(3);

    /**
     * Inicializa sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        try {
            SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
        } catch (Exception e) {
            if (e.getMessage().contains("Timeout")) {
                SistemaSenior.finalizarSistema();
                SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
            }
        }
    }

    /**
     * Finaliza sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transação dos cenários.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação dos cenários.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
    }

    /**
     * Cenário destinado ao assunto XYZ.
     */
    @Test
    public void testScenario0001_ExemploTeste() {
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);

		SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "CEN-0001", Tecla.TAB);
		SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "JOSE", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, "333", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DEPOSITO, "1", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PRODUTO, "CEV0001", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DERIVACAO, "0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SAFRA, "2014", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90409", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "11000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "6000", Tecla.TAB);
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Nº Interno", "14");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CONTRATOS, "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Contrato", "14", "Fornecedor Part.", "333");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Produção própria", "S", "Chave NFP-e", "42171148254353000144551230000006941875738151", "Emissão", SistemaSenior.dataAtual());
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Contrato", "14", "Fornecedor Part.", "334");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Produção própria", "S", "Chave NFP-e", "42171148254353000144551230000006931875738151", "Emissão", SistemaSenior.dataAtual());
        FacadeSuprimentos.processarEntradaSaidaBalanca(true);

        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "CEN-0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "1000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90409", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nenhum modelo de relatório ligado a esta tela, ou todo(s) o(s) modelos ligados estão inativos.", "OK");
        MetodosComuns.fecharTela(Form435CCCComponentNames.FR_NOME);
        //Validações
    }
    
    /**
     * 
     */
    @Test
    public void testScenario0009() {
        MetodosComunsNucleo.selecionarEmpresaFilial(120, 1);

        SistemaSenior.abrirTela(Form140PREComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_TIPO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_PEDIDO, "115", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_TRANS_PROD, "5101", Tecla.TAB);
        SistemaSenior.clicar(Form140PREComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140PREComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Transferência dos Itens?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Produto do item 1 não possui classificação fiscal!", "Ok");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Transferência realizada com sucesso. Deseja fechar Nota Fiscal?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Nota Fechada com Sucesso. Deseja Imprimir?", "Não");
        MetodosComuns.fecharTela(Form140PREComponentNames.FR_NOME);

        SistemaSeniorComTransacao.executarSQLQuery("select 1 from E140NFV where   CODEMP='120' and  CODFIL='1' and  CODSNF='NFE' and  TIPNFS='1' and  CODEDC='01' and  TNSPRO='5101' and  CODCLI='3' and  CODREP='1' and  CODCPG='1' and  CODMOE='01' and  VLRBPR='821.6' and  VLRBIC='821.6' and  VLRICM='147.89' and  VLRBSI='1150.24' and  VLRSIC='287.56' and  VLRBCO='1109.16' and  VLRLPR='1109.16' and  VLRLIQ='1109.16' and  VLRFIN='1109.16' and  SITNFV='2'  and exists ( select * from E140IPV where E140NFV.codemp=E140IPV.codemp and e140NFV.codfil=E140IPV.codfil and E140NFV.codsnf=E140IPV.codsnf and E140NFV.numnfv=E140IPV.numnfv and E140IPV.numped=115) ", 1);
        AssertivaERP.executaValidacao("SELECT 2", "E140IPV", "CODEMP='120' ,  CODFIL='1' ,  CODSNF='NFE' ,  SEQIPV='1', TNSPRO='5101' ,  FILPED='1' ,  NUMPED='115' ,  SEQIPD='1' ,  CODPRO='PAC0013' ,  CODDER='AZL'", "CODFAM='PAC' ,  CODCLF=' ' ,  CODSTR='010' ,  CODTST='SBT' ,  CODDEP='DEPPAC2' ,  QTDFAT='13' ,  UNIMED='PC' ,  PREUNI='63.2' ,  PREBAS='5.63' ,  PERICM='18' ,  SALCAN='F' ,  VLRBRU='821.6' ,  VLRBIC='821.6' ,  VLRICM='147.89' ,  VLRBSI='1150.24' ,  VLRICS='287.56' ,  VLRBCO='1109.16' ,  VLRIIP='821.6' ,  VLRLPR='1109.16' ,  VLRLIQ='1109.16' ,  VLRFIN='1109.16' ,  PREBRU='63.2'", 1);
        SistemaSeniorComTransacao.executarSQLQuery("select 3 from E140PAR where   CODEMP='120' and  CODFIL='1' and  CODSNF='NFE' and  CODPAR='1' and  CODTPT='01' and  CODFCR='01' and  VLRPAR='1109.16' and  CODPOR='PRP' and  CODCRT='01' and exists ( select * from E140IPV where E140PAR.codemp=E140IPV.codemp and e140PAR.codfil=E140IPV.codfil and E140PAR.codsnf=E140IPV.codsnf and E140PAR.numnfv=E140IPV.numnfv and E140IPV.numped=115) ", 1);
        SistemaSeniorComTransacao.executarSQLQuery("select 4 from E301TCR where   CODEMP='120' and  CODFIL='1' and  CODTPT='01' and  CODTNS='90301' and  SITTIT='AB' and  CODCLI='3' and  CODREP='1' and  VLRORI='1109.16' and  VLRABE='1109.16' and  CODMOE='01' and  VLRBCO='1109.16' and  CODPOR='PRP' and  CODCRT='01' and  FILNFV='1' and  CODSNF='NFE'  and exists ( select * from E140IPV where E301TCR.codemp=E140IPV.codemp and E301TCR.filnfv=E140IPV.codfil and E301TCR.codsnf=E140IPV.codsnf and E301TCR.numnfv=E140IPV.numnfv and E140IPV.numped=115)  ", 1);
        SistemaSeniorComTransacao.executarSQLQuery("select 5 from E301MCR where   CODEMP='120' and  CODFIL='1' and  CODTPT='01' and  SEQMOV='1' and  CODTNS='90301' and  VLRABE='1109.16' and  VLRMOV='1109.16' and  VLRLIQ='1109.16' and  VLRBCO='1109.16' and  CODPOR='PRP' and  CODCRT='01' and  LCTFIN='S' and  TIPCOF='1'  and exists ( select * from E140IPV,E301TCR where E301TCR.codemp=E140IPV.codemp and E301TCR.filnfv=E140IPV.codfil and E301TCR.codsnf=E140IPV.codsnf and E301TCR.numnfv=E140IPV.numnfv and E140IPV.numped=115 and E301MCR.codemp=E301TCR.codemp and E301MCR.codfil=E301TCR.codfil and E301TCR.codtpt=E301TCR.codtpt and E301MCR.numtit=E301TCR.numtit) ", 1);
        SistemaSeniorComTransacao.executarSQLQuery("select 6 from E210MVP where   CODEMP='120' and  CODPRO='PAC0013' and  CODDER='AZL' and  CODDEP='DEPPAC2' and  CODTNS='90270' and  ESTEOS='S' and  QTDMOV='13' and  VLRMOV='73.19' and  CODFIL='1' and  CODSNF='NFE' and  SEQIPV='1' and  CODCLI='3' and exists ( select * from E140IPV where E210MVP.codemp=E140IPV.codemp and E210MVP.codfil=E140IPV.codfil and E210MVP.codsnf=E140IPV.codsnf and E210MVP.numnfv=E140IPV.numnfv and E140IPV.numped=115)", 1);
        AssertivaERP.executaValidacao("SELECT 7", "E120PED", "codemp='120' , codfil='1' , numped ='115'", "sitped='4'", 1);
        AssertivaERP.executaValidacao("SELECT 8", "E120IPD", "codemp='120' , codfil='1' , numped ='115'", "sitipd='4'", 1);
    }
    
    /**
     * Recebimento de mercadorias com pesagem via ordem de compra.
     */
    @Test
    public void teste0001_EntradaBalancaViaOrdemCompra() {
        MetodosComunsNucleo.selecionarEmpresaFilial(50, 1);

        SistemaSenior.abrirTela("F435COS_SREB");
        SistemaSenior.preencherCampo("EPlaVei", "AAA9999", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("ENomMot", "TESTE", Tecla.TAB); //Placa/Motorista
        SistemaSenior.preencherCampo("ECodFor", "60", Tecla.TAB); //Fornecedor	
        SistemaSenior.preencherCampo("ESeqOrm", "1", Tecla.TAB); //Seq. Ori. Merc.		
        SistemaSenior.preencherCampo("ECodSaf", "2013-2014A", Tecla.TAB); //Safra	
        SistemaSenior.preencherCampo("ECodFil", "1", Tecla.TAB); // Filial/O.Compra	
        SistemaSenior.preencherCampo("ENumOcp", "15", Tecla.TAB); // Filial/O.Compra

        SistemaSenior.preencherCampo("ECodDep", "DEP04", Tecla.TAB); //Depósito
        SistemaSenior.preencherCampo("EPesInf", "48000", Tecla.TAB); //Peso
        SistemaSenior.clicar("Mostrar");

        //Grid ENTRADA
        SistemaSenior.posicionarLinhaGradePorValor("GridItens", "Produto", "T04TB0001", "Seq. Ori.", "1");
        SistemaSenior.preencherLinhaCorrenteGrade("GridItens", SistemaSenior.criarSequenciaTeclas(Tecla.TAB), "Sel.", CaixaAtribuicao.MARCADO);

        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "OK");

        SistemaSenior.preencherCampo("EPlaVei", "AAA9999", Tecla.TAB);//Placa/Motorista:
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo("EPesInf", "15000", Tecla.TAB); //. Peso
        SistemaSenior.clicar("BtNota");// a tela F435SSN será aberta		
        SistemaSenior.preencherCampo("SENumNfc", "500123", Tecla.TAB); //Número NFE
        SistemaSenior.preencherCampo("SECodSnf", "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo("SECodSel", "1", Tecla.TAB);//Série/Subsérie Legal
        SistemaSenior.preencherCampo("SECodSsl", "1", Tecla.TAB); //Série/Subsérie Legal
        SistemaSenior.preencherCampo("SEDatEmi", SistemaSenior.dataAtual(), Tecla.TAB); //Emissão
        SistemaSenior.preencherCampo("SECodTns", "1102", Tecla.TAB); //Trans.Prod.
        SistemaSenior.clicar("Ok");
        SistemaSenior.clicar("Ok");
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso", "OK");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nenhum modelo de relatório ligado a esta tela, ou todo(s) o(s) modelos ligados estão inativos.", "OK");
        SistemaSenior.clicar("Sair");

        SistemaSeniorComTransacao.executarSQLQuery("SELECT 1 FROM E115CES WHERE CODEMP = 50 AND CODFIL = 1 AND DATENT = ? AND ORICES = '3' AND PLAVEI = 'AAA9999' AND CODFOR = 60 AND CODTRA = 50 AND NOMMOT = 'TESTE' AND CIFFOB = 'X' AND PESENI = 48000.000 AND PESSAI = 15000.000 AND SITCES = 2 AND FILNFC = 1 AND NUMNFC = 500123 AND SNFNFC = 'NFE' AND CODSAF = '2013-2014A' AND SEQORM = 1", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 2 FROM E115PRO WHERE CODEMP = 50 AND CODFIL = 1 AND DATENT = ? AND FILOCP = 1 AND NUMOCP = 15 AND CODPRO = 'T04TB0001' AND CODDEP = 'DEP04' AND QTDINF = 33000.000 AND BRUINF = 33000.000 AND PREUNI = 100.00", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 3 FROM E440NFC WHERE CODEMP = 50 AND CODFIL = 1 AND CODFOR = 60 AND NUMNFC = 500123 AND CODSNF = 'NFE' AND TIPNFE = 1 AND DATENT = ? AND TNSPRO = '1102' AND DATEMI = ? AND VLRBPR = 3300000.00 AND VLRLIQ = 3300000.00 AND CODSAF = '2013-2014A'", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 4 FROM E440IPC WHERE CODEMP = 50 AND CODFIL = 1 AND CODFOR = 60 AND NUMNFC = 500123 AND CODSNF = 'NFE' AND SEQIPC = 1 AND TNSPRO = '1102' AND NOPPRO = '1102' AND FILOCP = 1 AND NUMOCP = 15 AND SEQIPO = 1 AND CODPRO = 'T04TB0001' AND CODFAM = 'TB' AND CODDEP = 'DEP04' AND QTDREC = 33000 AND UNINFC = 'KG' AND UNIMED = 'KG' AND PREUNI = 100 AND PREEST = 100 AND VLRBRU = 3300000 AND VLRLIQ = 3300000 AND VLRFIN = 3300000", 1);
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 5 FROM E440PAR WHERE CODEMP = 50 AND CODFIL = 1 AND CODFOR = 60 AND NUMNFC = 500123 AND CODSNF = 'NFE' AND CODPAR = 1 AND NUMTIT = '500123/01' AND CODTPT = 01 AND VLRPAR = 1930500 AND CODPOR = '999' AND CODCRT = '99'", 1);
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 6 FROM E440PAR WHERE CODEMP = 50 AND CODFIL = 1 AND CODFOR = 60 AND NUMNFC = 500123 AND CODSNF = 'NFE' AND CODPAR = 2 AND NUMTIT = '500123/02' AND CODTPT = 01 AND VLRPAR = 1369500 AND CODPOR = '999' AND CODCRT = '99'", 1);
    }

}
