package senior.erp.testes.suprimentos.notasFiscaisEntrada;

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
import senior.erp.componentNames.suprimentos.Form420GOCComponentNames;
import senior.erp.componentNames.suprimentos.Form420VALComponentNames;
import senior.erp.componentNames.suprimentos.Form440GNEComponentNames;
import senior.erp.componentNames.suprimentos.Form440NFVComponentNames;



public class TCSRNF0015_NotasFiscaisEntradaAgrupada {

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
    public void testDevolucaoVendaDireta0001() {

    	 //altera a serie NFE para emissão sem geração de arquivo XML
    	 SistemaSeniorComTransacao.executarSQL("update e020snf "
    	 		                              +   "set codedc = '01',"
    	 		                              +      " disaut = 0,"
    	 		                              +      " modrel = 'RFNF009.NFS'"
    	 		                              + "where codemp = 1 "
    	 		                              +   "and codfil = 2 "
    	 		                              +   "and codsnf = 'NFE'");
    	 
     	String codCliente = "106518";
     	
     	//busca CGC/CPF do cliente
        final String[][] ObtercgcCpfCliente = SistemaSeniorComTransacao.recuperaValoresBaseDados("select cgccpf from e095for where codfor = ?",
                SistemaSenior.parametroSql(TipoParametroSQL.STRING, codCliente));
        final String cgcCpfCliente = ObtercgcCpfCliente[0][0];
     	
     	//gerar o pedido
     	MetodosComuns.selecionarEmpresaFilial(1, 2);
     	inserirCabecalhoPedido(codCliente);
     	SistemaSenior.preencherLinhaCorrenteGrade("GridPro", "Produto", "60122.0088", "Derivação", "001", "Qtde. Pedida", "1");
     	SistemaSenior.clicar("BFecPed"); 
     	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento do pedido?", "&Sim");
     	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 63: Para Pessoas Físicas, informe o Endereço de Entrega no Botão [Diversos] ", "&Ok");
     	SistemaSenior.clicar("BDivPed");
     	SistemaSenior.preencherCampo("ESeqEnt", "1", Tecla.TAB);
     	SistemaSenior.preencherCampo("MObsPed", "Cleiton", Tecla.TAB);
     	SistemaSenior.clicar("Processar");
     	SistemaSenior.clicar("BDivPed");
     	SistemaSenior.conferirCampo("ESeqEnt", "1");
     	SistemaSenior.clicar("Sair");
     	SistemaSenior.clicar("BPerPed");
     	SistemaSenior.preencherCampo("DEUSU_CifFob1", "X", Tecla.TAB);
     	SistemaSenior.clicar("BtnFechar");
     	SistemaSenior.clicar("BFecPed");
     	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento do pedido?", "&Sim");
     	SistemaSenior.conferirCaixaMensagem("Aviso", "Pedido fechado com Sucesso.", "&Ok");
     	
     	//recuperar numero do pedido gerado
     	String[][] pedidoGerado = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numped "
     			                                                                   +   "from e120ped "
     			                                                                   +  "where codemp = 1 "
     			                                                                   +    "and codfil = 2 "
     			                                                                   +    "and obsped = 'Cleiton' "
     			                                                                   +    "and datger = ? "
     			                                                                   +    "and codcli = ?",
     			                                                                  SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()),
     			                                                                  SistemaSenior.parametroSql(TipoParametroSQL.STRING, codCliente));
     	String numeroPedidoGerado = pedidoGerado[0][0];
     	
     	//faturamento do pedido
     	SistemaSenior.clicar("BFatPed");
     	SistemaSenior.clicar("Processar");
     	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Processamento?", "&Sim");
     	SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com Sucesso", "&Ok");
     	SistemaSenior.clicar("Marcar");
     	SistemaSenior.clicar("Emitir");
     	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Emissão das Notas?", "&Sim");
     	SistemaSenior.clicar("BtnOk");
     	MetodosComuns.fecharTela("PrevForm");
     	SistemaSenior.clicar("Sair");
     	SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja realmente sair?", "&Sim");
     	SistemaSenior.clicar("Sair");
     	
     	//recuperar numero da nota fiscal emitida
     	String[][] notaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numnfv "
     			                                                                  +  "from e140nfv "
     			                                                                  + "where codemp = 1 "
     			                                                                  +   "and codfil = 2 "
     			                                                                  +   "and codcli = ? "
     			                                                                  +   "and datger = ?",
     			                                                                  SistemaSenior.parametroSql(TipoParametroSQL.STRING, codCliente),
     			                                                                  SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
     	String numeroNotaGerada = notaGerada[0][0];
     	
     	//devolução de venda
     	SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, "3", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_CGCCPF, cgcCpfCliente, Tecla.TAB);
        SistemaSenior.teclar(3, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_VALOR_LIQUIDO, "13,59", Tecla.TAB);
        SistemaSenior.clicar(Form440GNEComponentNames.BT_NOTA_SAIDA);
        SistemaSenior.preencherCampo(Form440NFVComponentNames.FD_NOTA_FISCAL_SAIDA, numeroNotaGerada, Tecla.TAB);
        SistemaSenior.clicar(Form440NFVComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form440NFVComponentNames.BT_PROCESSAR);
        SistemaSenior.clicar(Form440NFVComponentNames.BT_SAIR);
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS,"Observações");
        SistemaSenior.preencherLinhaCorrenteGrade("GridObs", 
                                                  "Observação"  , "Observação teste nota de devolução",
                                                  "Motivo"      , "11");        
        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 36:", "&Ok");
        
        //aprova a devolução da nota
   	    SistemaSeniorComTransacao.executarSQL("update usu_t440cpl "
   	 	     	                          +      "set usu_aceest = 'S',"
   	 	     	                          +         " usu_juscpl = 'teste',"
   	 	     	                          +         " usu_datapr = ?,"
   	 	     	                          +         " usu_usuapr = 1,"
   	 	     	                          +         " usu_horapr = 1,"
   	 	     	                          +         " usu_idnapr = 'S' "
   	 		                              +    "where usu_codemp = 1 "
   	 		                              +      "and usu_codfil = 2 "
   	 		                              +      "and usu_numnfc = ? ",
   	 		                          SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()),
   	 		                              SistemaSenior.parametroSql(TipoParametroSQL.STRING, numeroNotaGerada));
   	    
        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
   	    
        //recuperar numero da nota fiscal de devolução
     	String[][] notaDevolucao = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numnfc "
     			                                                                    +   "from e440nfc "
     			                                                                    +  "where codemp = 1 "
     			                                                                    +    "and codfil = 2 "
     			                                                                    +    "and tipnfe = 3 "
     			                                                                    +    "and datger = ?",
     			                                                                   SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
     	String numeroNotaDevolucao = notaDevolucao[0][0];
     	
     	
     }
    
    /**
     * 
     */
    @Test
    public void testNotaFiscalCompradeProdutor00001() {

    	boolean temCC = false;
    	boolean temFCGR = false;
    	String fornecedor = "106518";
    	
        MetodosComunsNucleo.selecionarEmpresaFilial(1, 10);
        
        final String[][] ObtercgcCpfFornecedor = SistemaSeniorComTransacao.recuperaValoresBaseDados("select cgccpf from e095for where codfor = ?",
        		                        SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor));
        final String cgcCpfFornecedor = ObtercgcCpfFornecedor[0][0];
        
        //busca se o cliente tem Cota Capital e/ou FCGR
        final String [][] obterCCeFCGR = SistemaSeniorComTransacao.recuperaValoresBaseDados("select CODTCC "
        		                                                                          +   "from e085cxc "
        		                                                                          +  "where codcli = ? "
        		                                                                          +    "and sitcxc = 'A' "         
        		                                                                          +    "and codtcc in ('007','008')",
        		                                                                          SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor));
   
        if (obterCCeFCGR.length == 1){
        	if (obterCCeFCGR[0][0].equals("007")) {temCC = true;}
            if (obterCCeFCGR[0][0].equals("008")) {temFCGR = true;}	
        }
        if (obterCCeFCGR.length == 2){
        	if (obterCCeFCGR[1][0].equals("007")) {temCC = true;}
            if (obterCCeFCGR[1][0].equals("008")) {temFCGR = true;}	
        }
        
        
        //criar ordem de compra
        SistemaSenior.abrirTela(Form420GOCComponentNames.FR_NOME);
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_TRANSACAO_PRODUTOS, "90400", Tecla.TAB);
        SistemaSenior.teclar(1, Tecla.TAB);
		SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_ORIGEM_MERCADORIA, "1", Tecla.TAB);
        SistemaSenior.clicar(Form420GOCComponentNames.BT_VALORES);
        SistemaSenior.preencherCampo(Form420VALComponentNames.FD_FRETE_CIF_OU_FOB, "F", Tecla.TAB);
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Ordem de compra será recalculada", "&Sim");
        SistemaSenior.selecionarGuia("Guias", "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade("GridIpo",
        		                                  "Produto",     "55062.0010",
        		                                  "Deriv." ,            "001",
        		                                  "Qtde. Pedida",  "10,00000",
        		                                  "Preço Unitário","10,00000");
        SistemaSenior.clicar("Fechar");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Ordem de Compra fechada com Sucesso! Deseja enviá-la", "&Não");
        
        final String[][] obterOrdemDeCompraGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("SELECT NUMOCP "
        	                                                                                           + "FROM E420OCP "
        	                                                                                          + "WHERE CODEMP='1' "
        	                                                                                            + "AND CODFIL='10' "
        	                                                                                            + "AND TNSPRO='90400' "
        	                                                                                            + "AND CODFOR=? "
        	                                                                                            + "AND DATGER=? "
        	                                                                                            + "AND VLRLIQ=100 ",
        	                                        SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor),
        	                                        SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroOrdemDeCompraGerada = obterOrdemDeCompraGerada[0][0];
        SistemaSenior.fecharTela("F420GOC");
        
        
        //receber nota fiscal via ordem de compra
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, "6", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_CGCCPF, cgcCpfFornecedor, Tecla.TAB);
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_VALOR_LIQUIDO, "100,00", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TRANS_PRODUTO, "1102", Tecla.TAB);
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS,"Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade("GridPro", 
        		                                  "Filial O.C."  , "10",
        		                                  "Ordem de Compra ", numeroOrdemDeCompraGerada,
        		                                  "Seq."         ,"1");
        SistemaSenior.clicar(Form440GNEComponentNames.BT_DOC_FISCAL);
        SistemaSenior.preencherLinhaCorrenteGrade("GridProdutor",
        		                                   "Fornecedor Pro. Rural", fornecedor,
        		                                   "Mod. Documento", "01",
        		                                   "Série Pro. Rural", "1",
        		                                   "Nro. Doc. Fiscal", "12345");
        SistemaSenior.clicar("OK");
        SistemaSenior.clicar("Fechar");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja gerar o arquivo da Nota Fiscal de Saída Eletrônica?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Fechada com Sucesso.", "&Ok");
        
        final String[][] obterNotaFiscalGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numnfc "
        	                                                                                       +  "from e440nfc "
        	                                                                                       + "where codemp = '1' "
        	                                                                                       +   "and codfil = '10' "
        	                                                                                       +   "and tnspro = '1102' "
        	                                                                                       +   "and codfor = '106518' "
        	                                                                                       +   "and datger = ?",
                                                 SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroNotaFiscalGerada = obterNotaFiscalGerada[0][0];
        SistemaSenior.fecharTela(Form440GNEComponentNames.FR_NOME);
        
        //validar geração de cota capital e/ou fcgr
        if (temCC == true){
        	SistemaSeniorComTransacao.executarSQLQuery("select * "
        			                                 +   "from e600mcc "
        			                                 +  "where empnfc = 1 "
        			                                 +    "and filnfc = 10 "
        			                                 +    "and codfor = ? "
        			                                 +    "and numnfc = ? "
        			                                 +    "and numcco like '%COT%' "
        			                                 +    "and datmov = ?", 1,
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor),
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, numeroNotaFiscalGerada),
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        }
        if (temFCGR == true){
        	SistemaSeniorComTransacao.executarSQLQuery("select * "
        			                                 +   "from e600mcc "
        			                                 +  "where empnfc = 1 "
        			                                 +    "and filnfc = 10 "
        			                                 +    "and codfor = ? "
        			                                 +    "and numnfc = ? "
        			                                 +    "and numcco like '%FCG%' "
        			                                 +    "and datmov = ?", 1,
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor),
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, numeroNotaFiscalGerada),
        			                                 SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        }        
        
    }

 	private void inserirCabecalhoPedido(String codCliente) {
 		SistemaSenior.abrirTela("F120GPD_RVPE");
     	SistemaSenior.teclar(Tecla.TAB);
     	SistemaSenior.preencherCampo("ETnsPro", "90110", Tecla.TAB);
 		SistemaSenior.preencherCampo("ECodCli", codCliente, Tecla.TAB);
     	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62", "Ok");
     	SistemaSenior.conferirCaixaMensagem("Aviso", "Regra 62", "Ok");
     	SistemaSenior.clicar("BAltPed");
 	}

}
