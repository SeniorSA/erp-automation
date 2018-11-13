package senior.erp.testes.mercado.pedidos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.mercado.Form120DIVComponentNames;
import senior.erp.componentNames.mercado.Form120GPDComponentNames;
import senior.erp.componentNames.mercado.Form120LIBComponentNames;
import senior.erp.componentNames.mercado.Form120PDGComponentNames;
import senior.erp.componentNames.mercado.Form121CPDComponentNames;
import senior.erp.utils.FacadeMercado;

/**
 * Emissão pedido tipo PED
 * 
 */

public class TCRVPE0001_PedidoAgrupadoPED {

    /**
     * Inicializa o sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
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

    @Test
    public void testecenario001(){
    	String codigoCliente = "106518";
    	
    	MetodosComuns.selecionarEmpresaFilial(1, 1);
    	SistemaSenior.abrirTela("F120GPD_RVPE");
    	SistemaSenior.teclar(Tecla.TAB);    	
		SistemaSenior.preencherCampo("ECodCli", codigoCliente, Tecla.TAB);
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62:", "&OK");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62:", "&OK");
    	SistemaSenior.clicar("BAltPed");
    	SistemaSenior.preencherLinhaCorrenteGrade("GridPro", "Produto", "60012.0043" , "Derivação", "001", "Qtde. Pedida" , "1");
    	SistemaSenior.teclar(Tecla.SETA_BAIXO);
    	SistemaSenior.teclar(Tecla.TAB);
    	SistemaSenior.teclar(Tecla.F3);
    	SistemaSenior.teclar(Tecla.TAB);
    	SistemaSenior.preencherCampo("ECodDep", "", Tecla.TAB);
    	SistemaSenior.preencherCampo("EDesPro", "terra cortril", Tecla.TAB);
    	SistemaSenior.clicar("Mostrar");
    	SistemaSenior.preencherLinhaCorrenteGrade("GridPro", "Quantidade", "1");
    	SistemaSenior.clicar("Ok");    	
    	SistemaSenior.clicar("BFecPed");
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento do pedido?", "&Sim");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 63: Para Pessoas Físicas, informe o Endereço de Entrega no Botão [Diversos]", "&OK");
    	SistemaSenior.clicar("BDivPed");
    	SistemaSenior.preencherCampo("ESeqEnt", "1", Tecla.TAB);
    	SistemaSenior.preencherCampo("MobsPed", "Fabio", Tecla.TAB);    
    	SistemaSenior.clicar("Processar");
    	SistemaSenior.clicar("BDivPed");
    	SistemaSenior.conferirCampo("ESeqEnt", "1");    	
    	SistemaSenior.clicar("Processar");
    	SistemaSenior.clicar("BPerPed");
    	SistemaSenior.preencherCampo("DEUsu_CifFob1", "X", Tecla.TAB);
    	SistemaSenior.clicar("BtnAlterar");
    	SistemaSenior.clicar("BtnFechar");
    	SistemaSenior.clicar("BFecPed");
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento do pedido?", "&Sim");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Pedido fechado com Sucesso", "&OK");
    	SistemaSenior.clicar("BFatPed");
    //	SistemaSenior.clicar("CBEmitirNota");
    	SistemaSenior.clicar("Processar");
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Processamento?", "&Sim");
    	SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com Sucesso", "&OK");
    	SistemaSenior.clicar("Sair");
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja realmente sair?", "&Sim");    	
    	//TCBaseERP.fecharTela("F120GPD_RVPE");
    	
    }
    
   @Test
   public void testecenario002() {
      // insere um pedido tipo PED para teste de geração de parcelas
	   
	  MetodosComuns.selecionarEmpresaFilial(1, 1); 
	  String codigocliente = "105713";
	  InserirCabecalhoPedido(codigocliente);
	  SistemaSenior.preencherLinhaCorrenteGrade("GridPro", "Produto","30026.0050","Derivação","001","Qtde. Pedida", "1");
	  SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_VALOR_ENTRADA, "1", Tecla.TAB);	  
	  SistemaSenior.clicar("Ok");
	  SistemaSenior.clicar(Form120DIVComponentNames.FR_DIVERSOS);
	  SistemaSenior.preencherCampo(Form120DIVComponentNames.FD_SAFRA, "2017/2017", Tecla.TAB);
	  SistemaSenior.preencherCampo(Form120DIVComponentNames.FD_ENDERECO_ENTREGA, "1", Tecla.TAB); 
	  SistemaSenior.preencherCampo(Form120DIVComponentNames.FD_OBSERVACAO_PEDIDO, "Wurschke", Tecla.TAB);	  
	  SistemaSenior.clicar(Form120DIVComponentNames.BT_PROCESSAR);
	  SistemaSenior.clicar(Form120PDGComponentNames.FR_PERSONALIZADO);		  
	  SistemaSenior.preencherCampo("DEUSU_CifFob1", "X", Tecla.TAB);
	  SistemaSenior.clicar(Form120PDGComponentNames.BT_ALTERAR);
	  SistemaSenior.clicar(Form120PDGComponentNames.BT_FECHAR);
	  SistemaSenior.clicar(Form120GPDComponentNames.BT_FECHAR);	
	  SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento", "&Sim");	  
	  SistemaSenior.conferirCaixaMensagem("Aviso", "Pedido fechado porém Bloqueado.", "&Ok");  
	  
	  MetodosComuns.fecharTela("F120GPD_RVPE");
	  
	  String[][] PedidoGerado = SistemaSeniorComTransacao.recuperaValoresBaseDados("SELECT numped FROM E120PED "
	  		+ "WHERE CODEMP = 1 "
	  		+ "  AND CODFIL = 1 "
	  		+ "  AND DATEMI = ? "
	  		+ "  AND obsped = 'Wurschke' "
	  		+ "  AND CODCLI = 105713", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
	  System.out.println("PEDIDO" + PedidoGerado);
	  String numeropedido = PedidoGerado [0][0]; 
	  

	  /**
	   * validar geração pedido	  
	   */
	  
	  SistemaSenior.abrirTela(Form121CPDComponentNames.FR_CONSULTA_DADOS_GERAIS);
	  SistemaSenior.preencherCampo(Form121CPDComponentNames.FD_CLIENTE, codigocliente, Tecla.TAB);
	  SistemaSenior.preencherCampo(Form121CPDComponentNames.FD_NUMERO_PEDIDO, numeropedido, Tecla.TAB);
	  SistemaSenior.clicar(Form121CPDComponentNames.BT_MOSTRAR);
      SistemaSenior.conferirLinhaGrade("GridCpd", 0, "Tns. Prod.", "90117", "Cliente", "105.713", "Cond. Pagto.", "030");
	  MetodosComuns.fecharTela("F121CPD_RVPE");	  
	  
	  
	  SistemaSeniorComTransacao.executarSQLQuery("SELECT * FROM E120PED WHERE CODEMP = 1 "
	  		+ "AND CODFIL = 1 AND DATEMI = ? AND obsped = 'Wurschke' "
	  		+ "and numped = ? AND CODCLI = 105713", 1, 	  		
	  		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()),
	  		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));
	  
	  SistemaSeniorComTransacao.executarSQLQuery("SELECT * FROM E301TCR WHERE CODEMP = 1 "
		  		+ "AND CODFIL = 1 AND DATEMI = ? and numped = ? AND CODCLI = 105713", 1, 	  		
		  		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()),
		  		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));      
	  
	  /**
	   * Liberar pedido bloqueado
	   */
	  SistemaSenior.abrirTela("F120LIB_RVPE");
	  SistemaSenior.preencherCampo(Form120LIBComponentNames.FD_CLIENTE, codigocliente, Tecla.TAB);
	  SistemaSenior.preencherCampo(Form120LIBComponentNames.FD_NUMERO_DO_PEDIDO, numeropedido, Tecla.TAB);
	  SistemaSenior.preencherCampo(Form120LIBComponentNames.FD_EMISSAO_INICIAL, SistemaSenior.dataAtual(), Tecla.TAB);
	  SistemaSenior.preencherCampo(Form120LIBComponentNames.FD_EMISSAO_FINAL, SistemaSenior.dataAtual(), Tecla.TAB);
	  SistemaSenior.clicar(Form120LIBComponentNames.BT_MOSTRAR);
	  SistemaSenior.preencherLinhaCorrenteGrade(Form120LIBComponentNames.GD_PEDIDOS, "Sel.", CaixaAtribuicao.MARCADO);
	  SistemaSenior.preencherLinhaCorrenteGrade(Form120LIBComponentNames.GD_OBSERVACAO_BLOQUEIO, "Aprovação", "A");
	  SistemaSenior.clicar(Form120LIBComponentNames.BT_PROCESSAR);
	  SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento dos pedidos", "&Sim");	  
	  SistemaSenior.conferirCaixaMensagem("Confirmação", "Processamento efetuado com os ", "&Não");		
	  MetodosComuns.fecharTela("F120LIB_RVPE");
	  
	  
	  /** 
	   *  Gera nota fiscal a partir do pedido
	   */
      FacadeMercado.inserirCabecalhoNFSaidaF140PRE(codigocliente, "", numeropedido);
      FacadeMercado.processarNotaFiscalSaidaF140PRE();
      
      SistemaSeniorComTransacao.executarSQLQuery("select 1 from E140NFV "
      		+ "where CODEMP = '1' "
      		+ "  and CODFIL = '1' "
      		+ "  and CODSNF = 'NFE' "
      		+ "  and CODCLI = ?  "
      		+ "  and CODCPG = '030' "
      		+ "  and CIFFOB = 'X' "
      		+ "  and SITNFV = '2' "
      		+ "  and exists (select * from E140IPV where E140NFV.codemp=E140IPV.codemp "
      		+ "  and e140NFV.codfil=E140IPV.codfil and E140NFV.codsnf=E140IPV.codsnf "
      		+ "  and E140NFV.numnfv=E140IPV.numnfv and E140IPV.numped = ?)", 1, 
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigocliente),
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));
      
      SistemaSeniorComTransacao.executarSQLQuery("select 2 from E140NFV "
      		+ "where CODEMP = '1' "
      		+ "  and CODFIL = '1' "
      		+ "  and CODSNF = 'NFE' "
      		+ "  and TIPNFS = '1' "
      		+ "  and CODCLI = ? "
      		+ "  and CODCPG = '030'"
      		+ "  and QTDITP = '1' "
      		+ "  and SITNFV = '2' "
      		+ "  and exists ( select * from E140IPV where E140NFV.codemp=E140IPV.codemp "
      		+ "  and e140NFV.codfil=E140IPV.codfil and E140NFV.codsnf=E140IPV.codsnf "
      		+ "  and E140NFV.numnfv=E140IPV.numnfv and E140IPV.numped = ?)", 1,
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigocliente),
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));

     SistemaSeniorComTransacao.executarSQLQuery("select 3 from E301TCR "
     		+ "where CODEMP= '1' "
     		+ "  and CODFIL= '1' "
     		+ "  and CODCLI= ? "
     		+ "  and CODTPT= 'DUP' "
     		+ "  and CODTNS= '90301'"
     		+ "  and SITTIT= 'AS' "
     		+ "  and CODCLI= '1'"
     		+ "  and CODREP= '1' "
     		+ "  and VLRORI= '1859.45' "
     		+ "  and VLRABE= '1859.45' "
     		+ "  and CODPOR= 'UN01' "
     		+ "  and CODCRT= '99'  "
     		+ "  and exists ( select * from E140IPV where E301TCR.codemp=E140IPV.codemp "
     		+ "  and E301TCR.filnfv=E140IPV.codfil and E301TCR.codsnf=E140IPV.codsnf "
     		+ "  and E301TCR.numnfv=E140IPV.numnfv and E140IPV.numped = ?)", 1,
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigocliente),
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));

     SistemaSeniorComTransacao.executarSQLQuery("select 4 from E140IPV "
     		+ "where CODEMP = '1' "
     		+ "  and CODFIL = '1' "
     		+ "  and CODSNF = 'NFE' "
     		+ "  and SEQIPV = '1' "
     		+ "  and NUMPED = ? "
     		+ "  and SEQIPD = '1' "
     		+ "  and CODPRO = '30026.0053' "
     		+ "  and CODDER = '001' "
     		+ "  and CODFAM = '30026' "
     		+ "  and CODCLF <> ' ' "
     		+ "  and CODDEP = 'D001-03'"
     		+ "  and QTDFAT = '4' "
     		+ "  and UNIMED = 'KG' "
     		+ "  and PERIPI = '0' "
     		+ "  and VLRBIP = '0' "
     		+ "  and VLRIPI = '0' "
     		+ "  and VLRLIQ = '1859.45' "
     		+ "  and VLRFIN = '1859.45')", 1,
      		SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeropedido));
   }

   private void InserirCabecalhoPedido(String codigocliente) {
	   SistemaSenior.abrirTela("F120GPD_RVPE");
	   SistemaSenior.teclar(Tecla.TAB);
	   SistemaSenior.preencherCampo("ETnsPro", "90117", Tecla.TAB);
	   SistemaSenior.preencherCampo("ECodCli", codigocliente, Tecla.TAB);
	   SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62", "Ok");
	   SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62", "Ok");
	   SistemaSenior.preencherCampo("ECodCpg", "030", Tecla.TAB);
	   SistemaSenior.clicar("BAltPed");
   }
    
    
}