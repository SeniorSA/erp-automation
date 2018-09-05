package senior.erp.utils;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.SystemMessageButtons;
import senior.erp.SystemMessageTitles;
import senior.erp.componentNames.cadastros.Form000RPFComponentNames;
import senior.erp.componentNames.cadastros.Form070FVEComponentNames;
import senior.erp.componentNames.cadastros.Form099UVEComponentNames;
import senior.erp.componentNames.mercado.Form113REMComponentNames;
import senior.erp.componentNames.mercado.Form113UMDComponentNames;
import senior.erp.componentNames.mercado.Form115CARComponentNames;
import senior.erp.componentNames.mercado.Form115CLAComponentNames;
import senior.erp.componentNames.mercado.Form115COEComponentNames;
import senior.erp.componentNames.mercado.Form115GNFComponentNames;
import senior.erp.componentNames.mercado.Form115PRCComponentNames;
import senior.erp.componentNames.mercado.Form115TRFComponentNames;
import senior.erp.componentNames.mercado.Form120GPCComponentNames;
import senior.erp.componentNames.mercado.Form120GPDComponentNames;
import senior.erp.componentNames.mercado.Form135AEAComponentNames;
import senior.erp.componentNames.mercado.Form135APFComponentNames;
import senior.erp.componentNames.mercado.Form135APMComponentNames;
import senior.erp.componentNames.mercado.Form135ECAComponentNames;
import senior.erp.componentNames.mercado.Form135FCPComponentNames;
import senior.erp.componentNames.mercado.Form135FEMComponentNames;
import senior.erp.componentNames.mercado.Form135GNAComponentNames;
import senior.erp.componentNames.mercado.Form135SCAComponentNames;
import senior.erp.componentNames.mercado.Form140CANComponentNames;
import senior.erp.componentNames.mercado.Form140COLComponentNames;
import senior.erp.componentNames.mercado.Form140ENLComponentNames;
import senior.erp.componentNames.mercado.Form140GNFComponentNames;
import senior.erp.componentNames.mercado.Form140LOTComponentNames;
import senior.erp.componentNames.mercado.Form140PREComponentNames;
import senior.erp.componentNames.mercado.Form140VCBComponentNames;
import senior.erp.componentNames.mercado.Form145CMEComponentNames;
import senior.erp.componentNames.mercado.Form149GNCComponentNames;
import senior.erp.componentNames.mercado.Form149GNPComponentNames;

public class FacadeMercado {

	/**
     * Obtém o número de nota fiscal de saída gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a nota fiscal de saída gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
     * @param empresa
     * @param filial
     * @param serieNotaFiscal
     * @param codigoCliente
     * @return
     */
    public static String obterNumeroNotaFiscalGerada(String empresa, String filial, String serieNotaFiscal, String codigoCliente) {
        String[][] obterNumeroNotaFiscalGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select max(numnfv) from e140nfv where codemp=? and codfil=? and codsnf=? and codcli=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, serieNotaFiscal), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroNotaFiscalGerada = obterNumeroNotaFiscalGerada[0][0];
        return numeroNotaFiscalGerada;
    }
    
    /**
     * Obtém o número de nota fiscal de saída gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a nota fiscal de saída gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
     * @param empresa
     * @param filial
     * @param serieNotaFiscal
     * @param codigoCliente
     * @param tipoNotaFiscalGerada
     * @return
     */
    public static String obterNumeroNotaFiscalGerada(String empresa, String filial, String serieNotaFiscal, String codigoCliente, String tipoNotaFiscalGerada) {
        String[][] obterNumeroNotaFiscalGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select max(numnfv) from e140nfv where codemp=? and codfil=? and codsnf=? and codcli=? and tipnfs=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, serieNotaFiscal), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.STRING, tipoNotaFiscalGerada), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroNotaFiscalGerada = obterNumeroNotaFiscalGerada[0][0];
        return numeroNotaFiscalGerada;
    }
	
	public static String obterNumeroPedidoGerado(final String empresa, final String filial, final String codigoCliente) {
        String[][] obterNumeroPedidoGerado = SistemaSeniorComTransacao.recuperaValoresBaseDados("select max(numped) from e120ped where codemp=? and codfil=? and codcli=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroPedidoGerado = obterNumeroPedidoGerado[0][0];
        return numeroPedidoGerado;
    }
    
    /**
     * Obtém o número de pré-fatura/análise gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a pré-fatura gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
     * @param empresa
     * @param filial
     * @param produto
     * @param derivacao
     * @return
     */
    public static String[][] obterNumeroPreFaturaAnaliseGerada(final String empresa, final String filial, final String produto, final String derivacao) {
        String[][] obterNumeroPreFaturaAnaliseGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numpfa, numane from e135pes where codemp=? and codfil=? and codpro=? and codder=? and datprp=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, produto),SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        return obterNumeroPreFaturaAnaliseGerada;
    }
    
	public static int obterQtdeDisponivelEstoque(String codigoProduto, String derivacao, String deposito) {
		final String[][] obterQtdeDisponivelEstoque = SistemaSeniorComTransacao.recuperaValoresBaseDados(
				"select qtdest from E210EST " + "where codemp = 120 and codpro = ? and codder = ? and coddep = ?",
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoProduto),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, deposito));
		final int qtdeDisponivelEstoque = Integer.parseInt(obterQtdeDisponivelEstoque[0][0]);
		return qtdeDisponivelEstoque;
	}

	public static void definirQtdeDisponivelEstoque(String codigoProduto, String derivacao, String deposito, double quantidadeDisponivel) {
		SistemaSeniorComTransacao.executarSQL(
				"update E210EST set qtdest=? " + "where codemp = 120 and codpro = ? and codder = ? and coddep = ?",
				SistemaSenior.parametroSql(TipoParametroSQL.DOUBLE, Double.toString(quantidadeDisponivel)),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoProduto),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, deposito));
	}
	
    /**
     * Conferir totalizadores de pesos na formação de cargas.
     * @param pesoLiquido
     * @param pesoBruto
     * @param qtdPedidos
     * @param vlrLiquido
     * @param financeiroDoPedido
     */
    public void conferirTotalizadores(String pesoLiquido, String pesoBruto, String qtdPedidos, String vlrLiquido, String financeiroDoPedido) {
        SistemaSenior.conferirRotulo(Form135FCPComponentNames.LB_PESO_LIQUIDO, pesoLiquido);
        SistemaSenior.conferirRotulo(Form135FCPComponentNames.LB_PESO_BRUTO, pesoBruto);
        SistemaSenior.conferirRotulo(Form135FCPComponentNames.LB_QTD_PEDIDOS, qtdPedidos);
        SistemaSenior.conferirRotulo(Form135FCPComponentNames.LB_VLR_LIQUIDO, vlrLiquido);
        SistemaSenior.conferirRotulo(Form135FCPComponentNames.LB_FINANCEIRO_DO_PEDIDO, financeiroDoPedido);
    }
    
    /**
     * Processar faturamento tela F149GNP
     */
    public void processarFaturamento() {
        SistemaSenior.clicar(Form149GNPComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Processamento realizado com Sucesso", "Ok");
    }
    
    public void parametrizarNotaFiscalEletronica(final String ambienteNFe, final String integracaoNFe) {
        SistemaSeniorComTransacao.executarSQL("update e070ven set ambnfe=?, intnfe=? where codemp=170 and codfil=1", SistemaSenior.parametroSql(TipoParametroSQL.STRING, ambienteNFe), SistemaSenior.parametroSql(TipoParametroSQL.STRING, integracaoNFe));
    }

    public static void configuraNotaEletronicaTerceiraGeracao(final String integracaoNFe, final String softwareEmissorNFe) {
        SistemaSenior.abrirTela(Form070FVEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.selecionarGuia(Form070FVEComponentNames.TS_AGRUPADOR_DAS_ABAS, "Documentos Eletrônicos 3");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja cancelar as operações?", "Sim");
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_AMBIENTE_NFE, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_AMBIENTE_CTE, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_INTEGRACAO_NFE, integracaoNFe, Tecla.TAB);
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_SOFTWARE_EMISSOR_NFE, softwareEmissorNFe, Tecla.TAB);
        SistemaSenior.clicar(Form070FVEComponentNames.BT_ALTERAR);
        MetodosComuns.fecharTela(Form070FVEComponentNames.FR_NOME);
    }

    public static void parametrizarIPIDevolucoesDeCompra() {
        SistemaSeniorComTransacao.executarSQL("update e001tve set ipiout='N' where codemp=170 and codtns = '531'");
    }

    public static void prepararNFComValorOutrasDespesas() {
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_TIPO, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_CLIENTE, "170", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140PREComponentNames.FD_N_NOTA_FISCAL_ENTRADA, "1012", Tecla.TAB);

        SistemaSenior.clicar(Form140PREComponentNames.BT_MOSTRAR);

        SistemaSenior.selecionarGuia(Form140PREComponentNames.TB_PAGINAS, "Produtos");

        SistemaSenior.clicar(Form140PREComponentNames.BT_MARCAR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form140PREComponentNames.GD_PRODUTOS, "Qtde.a Devolver", "1");
        SistemaSenior.clicar(Form140PREComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Quantidades foram alteradas. Nota será recalculada. Continuar?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Transferência dos Itens?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Transferência realizada com sucesso. Deseja fechar Nota Fiscal?", "&Não");

        SistemaSenior.clicar(Form140PREComponentNames.BT_NF);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_CALCULOS_P);
    }

    public static void processarNotaFiscalSaidaF140PRE() {
        SistemaSenior.clicar(Form140PREComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Transferência dos Itens?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Transferência realizada com sucesso. Deseja fechar Nota Fiscal?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Nota Fechada com Sucesso. Deseja gerar o arquivo da NF-e?", "Não");
        MetodosComuns.fecharTela("F140PRE_RFNF");
    }

    public static void parametrizarRNTRCTransportadora() {
        SistemaSeniorComTransacao.executarSQL("update e070ftr set nrntrc='12345678' where codemp=120 and codfil=3");
    }

    public void emitirManifestoEletronico(final String numeroManifesto, final boolean encerrarManifesto, final boolean cancelarManifesto) {
        SistemaSenior.abrirTela(Form145CMEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form145CMEComponentNames.FD_SERIE_ENTRADA, "MDF", Tecla.TAB);
        SistemaSenior.preencherCampo(Form145CMEComponentNames.FD_MANIFESTO_INICIAL, numeroManifesto, Tecla.TAB);
        SistemaSenior.preencherCampo(Form145CMEComponentNames.FD_MANIFESTO_FINAL, numeroManifesto, Tecla.TAB);
        if (encerrarManifesto) {
            SistemaSenior.selecionarItem(Form145CMEComponentNames.RB_OPCOES, "E&ncerrar");
        }
        if (cancelarManifesto) {
            SistemaSenior.selecionarItem("rbOpcoes", "Cance&lar");
        }
        SistemaSenior.clicar(Form145CMEComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form145CMEComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form145CMEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Manifestos processados com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form145CMEComponentNames.FR_NOME);
    }
    


    public void parametrizarTabelaPrecoCliente(final String codigoTabela, final int codigoCliente, final int codigoEmpresa) {
        SistemaSeniorComTransacao.executarSQL("update e085hcl set codtpr='" + codigoTabela + "' where codcli=" + codigoCliente + " and codemp=" + codigoEmpresa + " and codfil=1");
    }

    public final String obterNotaFiscalGerada() {
        final String[][] obterNotaFiscalGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMNFV from e140ipv where codemp=175 and codfil=1 and codsnf='UNI' and codpro='MRC001' and codder=1 and filped=1 and numped in (7,8) and seqipv in (1,2)");
        final String numeroNotaGerada = obterNotaFiscalGerada[0][0];
        return numeroNotaGerada;
    }
    
    public void processarNotaFiscalViaBalanca(final boolean produtoComClassificacaoFiscal, final boolean modalidadeDevolucao) {
        SistemaSenior.clicar(Form115GNFComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        if (produtoComClassificacaoFiscal) {
            SistemaSenior.conferirCaixaMensagem("Advertência", "Produto do item 1 não possui classificação fiscal!", "Ok");
        }
        if (modalidadeDevolucao) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executados com sucesso!", "&Não");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executados com sucesso!", "&Ok");
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Processamento realizado com Sucesso", "&Ok");
            MetodosComuns.fecharTela(Form140ENLComponentNames.FR_NOME);
        }
        MetodosComuns.fecharTela(Form115GNFComponentNames.FR_NOME);
    }

    public static void gerarNotaFiscalEletronica(String numeroInicialNotaFiscal, String numeroFinalNotaFiscal) {
        SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, numeroInicialNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, numeroFinalNotaFiscal, Tecla.TAB);

        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração do arquivo de Nota Eletrônica", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "O arquivo do documento eletrônico foi gerado com sucesso.", "Ok");

        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
    }
    

    public void recalcularNotaFiscal() {
        SistemaSenior.clicar(Form140GNFComponentNames.BT_RECALCULAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja recalcular a nota?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja buscar os valores padrões(Preços/Impostos) para os itens?", SystemMessageButtons.BT_SIM);
    }

    public void gerarNotaFiscalSugestaoIRRF(final String codigoCliente, final String percentualIRRF) {
        SistemaSenior.abrirTela(Form140GNFComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_CLIENTE, codigoCliente, Tecla.TAB);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_INSERIR);

        SistemaSenior.preencherLinhaCorrenteGrade(Form140GNFComponentNames.GD_PRODUTOS, "Produto", "IRRF", "Qtde.Fatur.", "1", "Preço Unitário Líq.", "1000");
        SistemaSenior.conferirLinhaGrade(Form140GNFComponentNames.GD_PRODUTOS, 0, "% IRRF", percentualIRRF);

        SistemaSenior.selecionarGuia(Form140GNFComponentNames.TB_AGRUPADOR_DAS_ABAS, "Serviços (2)");
        SistemaSenior.preencherLinhaCorrenteGrade(Form140GNFComponentNames.GD_SERVICOS, "Serviço", "SIRRF", "Qtde. Faturada", "1", "Preço Unitário", "1000");
        SistemaSenior.conferirLinhaGrade(Form140GNFComponentNames.GD_SERVICOS, 0, "% IRRF", percentualIRRF);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_CANCELAR);
        MetodosComuns.fecharTela(Form140GNFComponentNames.FR_NOME);
    }

    public static void excluirNFSugestaoSituacaoTributariaICMSDiferido() {
        MetodosComuns.fecharTela("F140CIP");
        SistemaSenior.clicar(Form140GNFComponentNames.BT_EXCLUIR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma a exclusão da nota fiscal e seus itens?", "&Sim");
        MetodosComuns.fecharTela(Form140GNFComponentNames.FR_NOME);
    }

    public static void prepararNFSugestaoSituacaoTributariaICMSDiferido() {
        SistemaSenior.abrirTela(Form140GNFComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_SERIE, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_CLIENTE, "1", Tecla.TAB);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_INSERIR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form140GNFComponentNames.GD_PRODUTOS, "Produto", "SUGESDIF", "Derivação", "1", "Qtde.Fatur.", "1");
    }
    

    public void prepararNotaIE(final String enviaIE, final String tipoNFE, final String iE) {
        SistemaSeniorComTransacao.executarSQL("UPDATE E070CPR SET ENVIEP = '" + enviaIE + "' WHERE CODEMP = 170 AND CODFIL = 1");
        SistemaSeniorComTransacao.executarSQL("UPDATE E070VEN SET INTNFE = 6 WHERE CODEMP = 170 AND CODFIL = 1");
        SistemaSeniorComTransacao.executarSQL("UPDATE E085CLI SET INSEST = '" + iE + "' WHERE CODCLI = 18");
        SistemaSeniorComTransacao.executarSQL("UPDATE E440NFC SET tipnfe = " + tipoNFE + " WHERE CODFIL = 1 AND CODEMP = 170 AND NUMNFC = 19 AND CODSNF = 'NFE'");
    }

    public void retornarConfiguracaoIE() {
        MetodosComuns.removerArquivoXMLGerado("/NFe42");
        SistemaSeniorComTransacao.executarSQL("UPDATE E070CPR SET ENVIEP = 'S' WHERE CODEMP = 170 AND CODFIL = 1");
        SistemaSeniorComTransacao.executarSQL("UPDATE E085CLI SET INSEST = ' ' WHERE CODCLI = 18");
        SistemaSeniorComTransacao.executarSQL("UPDATE E070VEN SET INTNFE = 0 WHERE CODEMP = 170 AND CODFIL = 1");
    }

    public final void desativarRateioTransacao(final int formaRateio, final String atualizaProjetos) {
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=" + formaRateio + ", tnsapj='" + atualizaProjetos + "' where codtns='90353' and codemp=170");
    }

    public void parametrizarFilialNFe(final String tipoIntegracao) {
        SistemaSenior.abrirTela(Form070FVEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.selecionarGuia(Form070FVEComponentNames.TB_AGRUPADOR, "Documentos Eletrônicos 3");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja cancelar as operações?", "Sim");
        SistemaSenior.preencherCampo(Form070FVEComponentNames.FD_INTEGRACAO_NFS_E, tipoIntegracao, Tecla.TAB);
        SistemaSenior.clicar(Form070FVEComponentNames.BT_ALTERAR);
        MetodosComuns.fecharTela(Form070FVEComponentNames.FR_NOME);
    }

    public void conferirMensagemGeracaoXML() {
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração do arquivo de Nota Eletrônica?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "O arquivo do documento eletrônico foi gerado com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
    }

    public void reabilitarNotasFiscais() {
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Reabilitação das Notas?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "1 Notas Reabilitadas com sucesso de 1 Notas selecionadas.", "Ok");
    }

    public void processarNotasFiscaisEletronicas() {
        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
    }

    public void filtrarNotasFiscaisSaida(final String serieNF, final String nrInicialNF, final String nrFinalNF) {
        SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_SERIE, serieNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, nrInicialNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, nrFinalNF, Tecla.TAB);
    }

    public static void excluirNFSaidaViaCarga(String numeroMaiorNFSaida) {
        SistemaSenior.abrirTela(Form149GNCComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form149GNCComponentNames.FD_N_NF_SAIDA, numeroMaiorNFSaida, Tecla.TAB);
        SistemaSenior.clicar(Form149GNCComponentNames.BT_EXCLUIR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Tem certeza que deseja excluir a Nota Fiscal e seus Itens?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Excluída com Sucesso.", "&Ok");

        MetodosComuns.fecharTela(Form149GNCComponentNames.FR_NOME);
    }

    public static String faturarCargaIndividual() {
        SistemaSenior.abrirTela(Form149GNCComponentNames.FR_NOME);

        String[][] obterMaiorAnalise = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMANE from e135pfa where codemp=175 and codfil=1 and numane=(select max(numane) from e135pfa where codemp=175 and codfil=1)");
        String numeroMaiorAnalise = obterMaiorAnalise[0][0];
        SistemaSenior.preencherCampo(Form149GNCComponentNames.FD_CARGA, numeroMaiorAnalise, Tecla.TAB);
        SistemaSenior.preencherCampo(Form149GNCComponentNames.FD_PRE_FATURA, "1", Tecla.TAB);

        SistemaSenior.clicar(Form149GNCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Processada com sucesso. ", "&Ok");
        SistemaSenior.clicar(Form149GNCComponentNames.BT_FECHAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma Fechamento da Nota?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Nota Fechada com Sucesso. Deseja Imprimir?", "&Não");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Gerado o Romaneio de Acerto número", "&Ok");
        MetodosComuns.fecharTela(Form149GNCComponentNames.FR_NOME);
        return numeroMaiorAnalise;
    }

    public static String reabilitarNFSaida(String sel, String reabPed, String reabPreFatura) {
        SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_SERIE, "UNI", Tecla.TAB);

        String[][] obterMaiorNFSaida = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMNFV from e140nfv where codemp=175 and codfil=1 and codsnf='UNI' and numnfv=(select max(numnfv) from e140nfv where codemp=175 and codfil=1 and codsnf='UNI')");
        String numeroMaiorNFSaida = obterMaiorNFSaida[0][0];
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, String.valueOf(numeroMaiorNFSaida), Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, String.valueOf(numeroMaiorNFSaida), Tecla.TAB);

        SistemaSenior.clicar(Form140CANComponentNames.RB_REABILITAR_NOTAS);
        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form140CANComponentNames.GD_NOTAS_FISCAIS, "Sel.", sel, "Reab.Ped.", reabPed, "Reab.Pré-Fatura", reabPreFatura);
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma Reabilitação das Notas?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "1 Notas Reabilitadas com sucesso de 1 Notas selecionadas.", "&Ok");
        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
        return numeroMaiorNFSaida;
    }

    public static void formarCargaViaPedido(String abrangenciaPedidos, String cancelaSaldo, String... linhasGrade) {
        SistemaSenior.abrirTela(Form135FCPComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form135FCPComponentNames.FD_TRANSP_PLACA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form135FCPComponentNames.FD_PLACA, "AAA1111", Tecla.TAB);
        SistemaSenior.preencherCampo(Form135FCPComponentNames.FD_TRANSP_MOTORISTA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form135FCPComponentNames.FD_MOTORISTA, "1", Tecla.TAB);

        SistemaSenior.clicar(Form135FCPComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form135SCAComponentNames.FD_PEDIDO, abrangenciaPedidos, Tecla.TAB);
        SistemaSenior.clicar(Form135SCAComponentNames.BT_OK);
        SistemaSenior.clicar(Form135FCPComponentNames.BT_MOSTRAR);
        if (cancelaSaldo != null) {
            SistemaSenior.preencherCampoGrade(Form135FCPComponentNames.GD_PEDIDOS, "Canc. Saldo", 0, cancelaSaldo, Tecla.SETA_CIMA);
        }
        if (linhasGrade.length > 0) {
            if (linhasGrade.length % 2 > 0) {
                throw new RuntimeException("Favor informar parametros conforme especificado(Campo , valor)");
            }
            int aux = 0;
            for (int i = 0; i < linhasGrade.length; i += 2) {
                SistemaSenior.preencherCampoGrade(Form135FCPComponentNames.GD_PRODUTOS, linhasGrade[i], aux, linhasGrade[i + 1], Tecla.SETA_BAIXO);
                aux++;
            }
        }
        SistemaSenior.clicar(Form135FCPComponentNames.BT_PROCESSAR);
        confirmarFormacaoCarga(true);
        MetodosComuns.fecharTela(Form135FCPComponentNames.FR_NOME);
    }

    public static void confirmarFormacaoCarga(boolean fecharCarga) {
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma a formação da carga com os itens especificados?", SystemMessageButtons.BT_SIM);
        if (fecharCarga) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Fechar a carga", SystemMessageButtons.BT_SIM);
            SistemaSenior.conferirCaixaMensagem("Aviso", "fechada com sucesso.", "&Ok");
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Fechar a carga", " &Não");
        }
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Verificar pendências para essa carga?", "&Não");
    }

    public static void excluirCarga(String maiorPreFaturaGerada) {
        SistemaSenior.abrirTela(Form135ECAComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form135ECAComponentNames.FD_CARGA, maiorPreFaturaGerada, Tecla.TAB);
        SistemaSenior.clicar(Form135ECAComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form135ECAComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form135ECAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma a exclusão de todos os registros selecionados?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Cargas e pré-faturas selecionadas excluídas com sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form135ECAComponentNames.FR_NOME);
    }

    public static void expedicaoPedidoSimplificado(String numeroPedido, String... linhasGrade) {
        SistemaSenior.abrirTela(Form135APMComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form135APMComponentNames.FD_PEDIDO, numeroPedido, Tecla.TAB);
        if (linhasGrade.length > 0) {
            if (linhasGrade.length % 2 > 0) {
                throw new RuntimeException("Favor informar parametros conforme especificado(Campo , valor)");
            }
            int aux = 0;
            for (int i = 0; i < linhasGrade.length; i += 2) {
                SistemaSenior.preencherCampoGrade(Form135APMComponentNames.GD_ITENS_PEDIDO, linhasGrade[i], aux, linhasGrade[i + 1], Tecla.SETA_BAIXO);
                aux++;
            }
        }
        SistemaSenior.clicar(Form135APMComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma o processamento dos pedidos selecionados para a Análise", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Pré-fatura gerada com sucesso. Consultar os itens desta Pré-fatura?", "&Não");
        MetodosComuns.fecharTela(Form135APMComponentNames.FR_NOME);
    }

    public static void expedicaoPedidoAgrupado(String numerosDosPedidos) {
        SistemaSenior.abrirTela(Form135AEAComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form135AEAComponentNames.FD_PEDIDOS, numerosDosPedidos, Tecla.TAB);
        SistemaSenior.clicar(Form135AEAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja realmente processar?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "A opção 'Gravar a cada Pré-Fatura' não está ativa. Algumas tabelas ficarão travadas até a conclusão do processamento. Continuar?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Consultar as pré-faturas geradas?", "&Não");
        MetodosComuns.fecharTela(Form135AEAComponentNames.FR_NOME);
    }

    public static void agruparPreFaturas() {
        SistemaSenior.abrirTela(Form135APFComponentNames.FR_NOME);

        String[][] obterNumeroMaiorAnalise = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMANE from e135pfa where codemp=175 and codfil=1 and numane=(select max(numane) from e135pfa where codemp=175 and codfil=1) and numpfa=(select max(numpfa) from e135pfa where codemp=175 and codfil=1)");
        String numeroMaiorAnalise = obterNumeroMaiorAnalise[0][0];

        SistemaSenior.preencherCampo(Form135APFComponentNames.FD_ANALISE, String.valueOf(numeroMaiorAnalise), Tecla.TAB);
        SistemaSenior.clicar(Form135APFComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form135APFComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Processamento realizado com Sucesso. Deseja visualizar arquivo de Log? ", " &Não");
        MetodosComuns.fecharTela(Form135APFComponentNames.FR_NOME);
    }

    public static String formarEmbalagensPreFaturas() {
        String[][] obterPreFaturaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMANE from e135pfa where codemp=175 and codfil=1 and numane=(select max(numane) from e135pfa where codemp=175 and codfil=1) and numpfa=(select max(numpfa) from e135pfa where codemp=175 and codfil=1)");
        String maiorPreFaturaGerada = obterPreFaturaGerada[0][0];

        SistemaSenior.abrirTela(Form135FEMComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_ANALISE, maiorPreFaturaGerada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_PRE_FATURA, "1", Tecla.TAB);

        SistemaSenior.clicar(Form135FEMComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form135FEMComponentNames.GD_EMBALAGEM, "Embalagem", "1", "Quantidade", "1");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "1");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "2");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "3");

        SistemaSenior.clicar(Form135FEMComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma o processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento efetuado com sucesso.", "&Ok");

        MetodosComuns.fecharTela(Form135FEMComponentNames.FR_NOME);
        return maiorPreFaturaGerada;
    }

    public static String formarEmbalagensPreFaturas(int numeroPreFatura) {
        String[][] obterPreFaturaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select NUMANE from e135pfa where codemp=175 and codfil=1 and numane=(select max(numane) from e135pfa where codemp=175 and codfil=1) and numpfa=(select max(numpfa) from e135pfa where codemp=175 and codfil=1)");
        String maiorPreFaturaGerada = obterPreFaturaGerada[0][0];

        SistemaSenior.abrirTela(Form135FEMComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_ANALISE, maiorPreFaturaGerada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_PRE_FATURA, String.valueOf(numeroPreFatura), Tecla.TAB);

        SistemaSenior.clicar(Form135FEMComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form135FEMComponentNames.GD_EMBALAGEM, "Embalagem", "1", "Quantidade", "1");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "1");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "2");
        SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, "Seq. Item", "3");

        SistemaSenior.clicar(Form135FEMComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma o processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento efetuado com sucesso.", "&Ok");

        MetodosComuns.fecharTela(Form135FEMComponentNames.FR_NOME);
        return maiorPreFaturaGerada;
    }
    

    public static void faturarPreFatura(String maiorPreFaturaGerada) {
        SistemaSenior.abrirTela(Form135GNAComponentNames.FR_NOME);

        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_ANALISE_EMBARQUE, maiorPreFaturaGerada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_PRE_FATURA, "1", Tecla.TAB);

        SistemaSenior.clicar(Form135GNAComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form135GNAComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma o processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form140ENLComponentNames.FR_NOME);
        MetodosComuns.fecharTela(Form135GNAComponentNames.FR_NOME);
    }

    public static void faturarPreFatura(String maiorPreFaturaGerada, int preFatura) {
        SistemaSenior.abrirTela(Form135GNAComponentNames.FR_NOME);

        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_ANALISE_EMBARQUE, maiorPreFaturaGerada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_PRE_FATURA, String.valueOf(preFatura), Tecla.TAB);
        SistemaSenior.clicar(Form135GNAComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form135GNAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma o processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form140ENLComponentNames.FR_NOME);
        MetodosComuns.fecharTela(Form135GNAComponentNames.FR_NOME);
    }
    
    /**
     * <b>Realizar faturamento das Pré-Faturas geradas.</b>
     * 
     * @param analisePreFatura Número da Análise para realizar geração de nota fiscal de saída.
     * @author Marcel.Fortunato
     */
    public static void faturarPreFatura(String[][] analisePreFatura) {
        SistemaSenior.abrirTela(Form135GNAComponentNames.FR_NOME);

        for (int i = 0; i < analisePreFatura.length - 1; i++) {

            SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_ANALISE_EMBARQUE, analisePreFatura[i][0], Tecla.TAB);
            SistemaSenior.preencherCampo(Form135GNAComponentNames.FD_PRE_FATURA, analisePreFatura[i][1], Tecla.TAB);

            SistemaSenior.clicar(Form135GNAComponentNames.BT_MOSTRAR);
            SistemaSenior.clicar(Form135GNAComponentNames.BT_PROCESSAR);

            SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento?", "&Sim");
            SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com sucesso.", "&Ok");

            MetodosComuns.fecharTela(Form140ENLComponentNames.FR_NOME);
        }

        MetodosComuns.fecharTela(Form135GNAComponentNames.FR_NOME);
    }

    /**
     * <b>Realizar exclusão de Notas Fiscais de sáida.</b>
     * 
     * @param numeroNFSaida Número da Nota Fiscal de saída a excluir.
     * @author Marcel.Fortunato
     */
    public static void excluirNFSaida(String numeroNFSaida) {
        SistemaSenior.abrirTela(Form140GNFComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_SERIE, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_NF, numeroNFSaida, Tecla.TAB);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_EXCLUIR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a exclusão da nota fiscal e seus itens?", "&Sim");

        MetodosComuns.fecharTela(Form140GNFComponentNames.FR_NOME);
    }

    /**
     * <b>Realiza a exclusão de Carga gerada.</b>
     * 
     * @param numeroMaiorAnalise Número da Análise gerada para efetuar a exclusão da Carga.
     * @author Marcel.Fortunato
     */
    public static void excluirCarga(String[][] numeroMaiorAnalise) {

        SistemaSenior.abrirTela(Form135ECAComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form135ECAComponentNames.FD_CARGA, numeroMaiorAnalise[0][0], Tecla.TAB);

        SistemaSenior.clicar(Form135ECAComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form135ECAComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form135ECAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a exclusão de todos os registros selecionados?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Cargas e pré-faturas selecionadas excluídas com sucesso.", "&Ok");

        MetodosComuns.fecharTela(Form135ECAComponentNames.FR_NOME);

    }

    /**
     * <b>Realizar expedição de um único pedido.</b>
     * 
     * @param numeroPedido Número do pedido para expedição.
     * @param linhasGrade Colunas e linhas da grade a preencher para expedição parcial do pedido.
     * @param removerLotes Código dos lotes para remover.
     * @author Marcel.Fortunato
     */
    public static void expedicaoPedidoSimplificado(String numeroPedido, String removerLotes, String... linhasGrade) {
        SistemaSenior.abrirTela(Form135APMComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form135APMComponentNames.FD_PEDIDO, numeroPedido, Tecla.TAB);

        if (linhasGrade.length > 0) {
            if (linhasGrade.length % 2 > 0) {
                throw new RuntimeException("Favor Informar parametros conforme especificado(Campo, valor)");
            }
            int aux = 0;
            for (int i = 0; i < linhasGrade.length; i += 2) {
                SistemaSenior.preencherCampoGrade(Form135APMComponentNames.GD_ITENS_PEDIDO, linhasGrade[i], aux, linhasGrade[i + 1], Tecla.SETA_BAIXO);
                aux++;
            }

        }

        SistemaSenior.clicar(Form135APMComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento dos pedidos selecionados para a Análise", "&Sim");

        if (removerLotes != null) {
            SistemaSenior.posicionarLinhaGradePorValor("GridDist", "Lote Fab.", "L002");
            SistemaSenior.teclarCombinacao(Tecla.CTRL, Tecla.DELETE);
            SistemaSenior.conferirCaixaMensagem("Confirmação de exclusão", "Deseja excluir o registro?", "&Sim");

            SistemaSenior.posicionarLinhaGradePorValor("GridDist", "Lote Fab.", "L003");
            SistemaSenior.teclarCombinacao(Tecla.CTRL, Tecla.DELETE);
            SistemaSenior.conferirCaixaMensagem("Confirmação de exclusão", "Deseja excluir o registro?", "&Sim");
            SistemaSenior.clicar("btOk");

            SistemaSenior.posicionarLinhaGradePorValor("GridDist", "Nº Série", "S002");
            SistemaSenior.teclarCombinacao(Tecla.CTRL, Tecla.DELETE);
            SistemaSenior.conferirCaixaMensagem("Confirmação de exclusão", "Deseja excluir o registro?", "&Sim");

            SistemaSenior.posicionarLinhaGradePorValor("GridDist", "Nº Série", "S003");
            SistemaSenior.teclarCombinacao(Tecla.CTRL, Tecla.DELETE);
            SistemaSenior.conferirCaixaMensagem("Confirmação de exclusão", "Deseja excluir o registro?", "&Sim");
            SistemaSenior.clicar("btOk");

        }

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Pré-fatura gerada com sucesso. Consultar os itens desta Pré-fatura?", "&Não");

        MetodosComuns.fecharTela(Form135APMComponentNames.FR_NOME);
    }

    /**
     * <b>Realizar exclusão das Notas Fiscais de sáida via carga.</b>
     * 
     * @param numeroMaiorNFSaida maior número da Nota Fiscal de saída a excluir.
     * @author Marcel.Fortunato
     */
    public static void excluirNFSaidaViaCarga(String[][] numeroMaiorNFSaida) {

        SistemaSenior.abrirTela(Form149GNCComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form149GNCComponentNames.FD_N_NF_SAIDA, numeroMaiorNFSaida[0][0], Tecla.TAB);

        SistemaSenior.clicar(Form149GNCComponentNames.BT_EXCLUIR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Tem certeza que deseja excluir a Nota Fiscal e seus Itens?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Excluída com Sucesso.", "&Ok");

        MetodosComuns.fecharTela(Form149GNCComponentNames.FR_NOME);
    }

    /**
     * @param qtdPreFaturas Quantidade de Pré-faturas para formar embalagem
     * @param linhasGrade Linhas da grade a preencher
     * @return retorno
     * @author Marcel.Fortunato
     */
    static String[][] formacaoEmbalagensPreFaturas(int qtdPreFaturas, String... linhasGrade) {

        SistemaSenior.abrirTela(Form135FEMComponentNames.FR_NOME);

        String[][] retorno = new String[qtdPreFaturas][2];
        for (int i = 0; i < qtdPreFaturas - 1; i++) {
            String[][] numeroMaiorAnalise = SistemaSeniorComTransacao.recuperaValoresBaseDados("SELECT NUMANE FROM e135pfa WHERE codemp=175 AND codfil=1 AND numane=(SELECT MAX(numane) FROM e135pfa WHERE codemp=175 AND codfil=1) AND numpfa=(SELECT MAX(numpfa) FROM e135pfa WHERE codemp=175 AND codfil=1)");
            String[][] numeroMaiorPreFatura = SistemaSeniorComTransacao.recuperaValoresBaseDados("SELECT NUMPFA FROM e135pfa WHERE codemp=175 AND codfil=1 AND numane=(SELECT MAX(numane) FROM e135pfa WHERE codemp=175 AND codfil=1) AND numpfa=(SELECT MAX(numpfa-" + i + ") FROM e135pfa WHERE codemp=175 AND codfil=1)");

            retorno[i][0] = numeroMaiorAnalise[0][0];
            retorno[i][1] = numeroMaiorPreFatura[0][0];

            SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_ANALISE, numeroMaiorAnalise[0][0], Tecla.TAB);
            SistemaSenior.preencherCampo(Form135FEMComponentNames.FD_PRE_FATURA, numeroMaiorPreFatura[0][0], Tecla.TAB);

            SistemaSenior.clicar(Form135FEMComponentNames.BT_MOSTRAR);

            SistemaSenior.preencherLinhaCorrenteGrade(Form135FEMComponentNames.GD_EMBALAGEM, "Embalagem", "1", "Quantidade", "1");

            if (linhasGrade.length > 0) {
                if (linhasGrade.length % 2 > 0) {
                    throw new RuntimeException("Favor Informar parametros conforme especificado(Campo, valor)");
                }
                for (int x = 0; x < linhasGrade.length; x += 2) {
                    SistemaSenior.preencherNovaLinhaGrade(Form135FEMComponentNames.GD_ITENS_DA_EMBALAGEM, linhasGrade[x], linhasGrade[x + 1]);
                }

            }

            SistemaSenior.clicar(Form135FEMComponentNames.BT_PROCESSAR);

            SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento?", "&Sim");
            SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento efetuado com sucesso.", "&Ok");
        }

        MetodosComuns.fecharTela(Form135FEMComponentNames.FR_NOME);

        return retorno;
    }
    
    public void cancelarNotasFiscais(final boolean cancelarReceitaVinculada) {
        SistemaSenior.clicar(Form140CANComponentNames.BT_MARCAR);
        SistemaSenior.preencherLinhaCorrenteGrade(Form140CANComponentNames.GD_NOTAS_FISCAIS, "Motivo", "4");
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma cancelamento das Notas Fiscais?", "Sim");
        if (cancelarReceitaVinculada) {
            SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja cancelar a receita vinculada a esta nota?", "Sim");
        } else {
            SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja cancelar a receita vinculada a esta nota?", "&Não");
        }
        SistemaSenior.conferirCaixaMensagem("Aviso", "1 Nota(s) geraram Solicitação de Cancelamento/Inutilização para NF-Eletrônica com sucesso.", "Ok");

        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
    }

    public void filtrarNotasFiscais(final String numeroNotaFiscal, final boolean cancelarNotaFiscal) {
        SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, numeroNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, numeroNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        if (cancelarNotaFiscal) {
            SistemaSenior.clicar(Form140CANComponentNames.RB_CANCELAR_NOTAS);
        }
        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
    }

    public int obterNumeroReceitaGerada() {
        String[][] obterNumeroReceitaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numrec from e113rem where seqrem =(select max (seqrem) from E113REM where codemp=321 and codfil=1 and codcli= 173) and numrec =(select max (numrec) from E113REM where codemp=321 and codfil=1 and codcli= 173) and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final int numeroReceitaGerada = Integer.parseInt(obterNumeroReceitaGerada[0][0]);
        return numeroReceitaGerada;
    }

    public int obterNumeroNotaFiscalGerada() {
        String[][] obterNumeroNotaFiscalGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numnfv from e140ipv where codemp=321 and codfil=1 and codsnf='NFE' and numnfv=(select max (numnfv) from e140nfv where codemp=321 and codfil=1 and codsnf='NFE') and numped=2 and codpro='REC100' and cplipv='ABAMEX' and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final int numeroNotaFiscalGerada = Integer.parseInt(obterNumeroNotaFiscalGerada[0][0]);
        return numeroNotaFiscalGerada;
    }

    public static void distribuirRateiosCentrosDeCustos() {
        SistemaSenior.preencherLinhaCorrenteGrade(Form000RPFComponentNames.GD_PROJETOS, "Financeira", "3035");
        SistemaSenior.preencherLinhaCorrenteGrade(Form000RPFComponentNames.GD_CENTRO_CUSTO, "C.Custo", "230");
        SistemaSenior.clicar(Form000RPFComponentNames.BT_OK);
    }

    public static void fecharNFReceituario() {
        SistemaSenior.clicar(Form140GNFComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota?", "&Sim");
        distribuirRateiosCentrosDeCustos();
    }

    public static void digitarNFReceituario() {
        SistemaSenior.abrirTela(Form140GNFComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_SERIE, "NFE");
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_TRANS_PRODUTOS, "6102", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_TRANS_SERVICOS, " ", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_CONDICAO_PGTO, "001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140GNFComponentNames.FD_REPRES, "320", Tecla.TAB);
        SistemaSenior.clicar(Form140GNFComponentNames.BT_INSERIR);
    }

    public static void prepararEmissaoReceituario(final boolean receituarioNotaFiscal, final String transacaoProduto) {
        SistemaSenior.abrirTela(Form140VCBComponentNames.FR_NOME);
        if (receituarioNotaFiscal) {
            SistemaSenior.selecionarItem(Form140VCBComponentNames.CB_TIPO_EMISSAO, "Nota Fiscal");
        } else {
            SistemaSenior.selecionarItem(Form140VCBComponentNames.CB_TIPO_EMISSAO, "Pedido");
        }
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form140VCBComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140VCBComponentNames.FD_TAB_PRECO, "G001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140VCBComponentNames.FD_REPRES, "320", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140VCBComponentNames.FD_TNS_PROD, transacaoProduto, Tecla.TAB);
        if (!receituarioNotaFiscal) {
            SistemaSenior.preencherCampo(Form140VCBComponentNames.FD_TIPO_FRETE, "F", Tecla.TAB);
        }
    }

    public static void prepararEmissaoNFReceituarioAgrupada() {
        SistemaSenior.preencherCampo(Form140LOTComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form140LOTComponentNames.FD_DATA_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Alteração de Data de Emissão da Nota", "&Sim");
        SistemaSenior.preencherCampo(Form140LOTComponentNames.FD_DATA_SAIDA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form140LOTComponentNames.FD_TIPO, "1", Tecla.TAB);
        SistemaSenior.clicar(Form140LOTComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140LOTComponentNames.BT_DESMARCAR);
    }

    public static void processarEmissaoReceituario() {
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja Emitir Receitas para os Produtos?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja utilizar o Reaproveitamento da Numeração das Receitas?", "&Sim");
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Data receita", SistemaSenior.dataAtual(), "Código cliente", "173");
        SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Código  responsável", "1", "Cód. Tipo apl.", "1");
    }

    public static void finalizarEmissaoReceituario() {
        SistemaSenior.clicar(Form113REMComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Receitas Processadas com Sucesso.", "&Ok");
        SistemaSenior.clicar(Form113REMComponentNames.BT_EMITIR_RECEITAS);
        MetodosComuns.fecharTela(Form113REMComponentNames.FR_RELATORIO_DA_RECEITA);

        SistemaSenior.conferirCaixaMensagem("Aviso", "Receitas emitidas com sucesso.", "&Ok");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Nota Fechada com Sucesso. Deseja gerar o arquivo da Nota Eletrônica?", "&Não");
        MetodosComuns.fecharTela(Form140GNFComponentNames.FR_NOME);
    }

    public static void inserirProdutoReceituario(final boolean produtoREC100) {
        if (produtoREC100) {
            SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Produto", "REC100", "Quantidade", "2,00000");
            SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Embalagem", "1", "Cód. Espécie/Cultura", "14", "Cod. Praga/Problema", "1", "Cód. Diagnóstico", "111111111", "Cód. Tipo apl.", "1");
        } else {
            SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Produto", "REC200", "Quantidade", "2,00000");
            SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Embalagem", "2", "Cód. Espécie/Cultura", "59", "Cod. Praga/Problema", "485", "Cód. Diagnóstico", "519", "Cód. Tipo apl.", "1");
        }
    }

    public static void inserirProdutoNotaFiscal(final String produto) {
        if (produto.equals("REC100")) {
            SistemaSenior.preencherLinhaCorrenteGrade(Form140GNFComponentNames.GD_PRODUTOS, "Produto", "REC100", "Depósito", "7", "Qtde.Fatur.", "2", "Peso Bruto", "50", "Preço Bruto", "50");
            return;
        } else if (produto.equals("REC200")) {
            SistemaSenior.preencherNovaLinhaGrade(Form140GNFComponentNames.GD_PRODUTOS, "Produto", "REC200", "Depósito", "7", "Qtde.Fatur.", "2", "Peso Bruto", "100", "Preço Bruto", "100");
            return;
        } else {
            SistemaSenior.preencherNovaLinhaGrade(Form140VCBComponentNames.GD_PRODUTOS, "Produto", "REC300", "Qtd Pedida", "2", "Preço Bruto", "200,00000");
        }
    }

    public static void emitirNFReceituarioAgrupada() {
        SistemaSenior.conferirCaixaMensagem("Aviso", "Processamento realizado com Sucesso", "&Ok");
        SistemaSenior.clicar(Form140ENLComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form140ENLComponentNames.BT_EMITIR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Emissão das Notas?", "&Sim");
    }
    

    public void processarSaidaBalanca() {
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento e geração da Nota Fiscal?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "executado com sucesso!", "&Não");
        SistemaSenior.conferirCaixaMensagem("Advertência", "executado com sucesso!", "&Ok");
        SistemaSenior.clicar(Form115COEComponentNames.BT_CANCELAR_IMPRESSAO);
        MetodosComuns.fecharTela(Form115COEComponentNames.FR_NOME);
    }

    public void finalizarExpedicaoViaCarga(final String placaVeiculo, final String pesoSaida, final boolean isTicket) {
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PESO, pesoSaida, Tecla.TAB);
        if (isTicket) {
            SistemaSenior.selecionarItem(Form115CARComponentNames.RG_DOCUMENTO_A_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.selecionarItem(Form115CARComponentNames.RG_DOCUMENTO_A_EMITIR, "&Nota Fiscal");
        }
        SistemaSenior.clicar(Form115CARComponentNames.BT_CLASSI);
        SistemaSenior.posicionarLinhaGradePorValor(Form115CLAComponentNames.GD_CLASSIFICAO, "Item Classificação", "1");
        SistemaSenior.preencherLinhaCorrenteGrade(Form115CLAComponentNames.GD_CLASSIFICAO, "% Apurado", "5,0000");
        SistemaSenior.clicar(Form115CLAComponentNames.BT_OK);
        SistemaSenior.clicar(Form115CARComponentNames.BT_PROCESSAR);
        if (isTicket) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e impressão do Ticket?", "Sim");
            SistemaSenior.clicar(Form115CARComponentNames.BT_CANCELAR_IMPRESSAO);
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e geração da Nota Fiscal?", "Sim");
            SistemaSenior.clicar(Form140ENLComponentNames.BT_MARCAR);
            SistemaSenior.clicar(Form140ENLComponentNames.BT_PROCESSAR);
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja Processar notas fiscais?", "&Sim");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Processamento realizado com sucesso.", "&Ok");
            MetodosComuns.fecharTela(Form140ENLComponentNames.FR_NOME);
        }
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Processado com sucesso", "Ok");
        MetodosComuns.fecharTela(Form115CARComponentNames.FR_NOME);
    }

    public void prepararExpedicaoViaCarga(final String placaVeiculo, final String numeroAnalise, final String numeroPedido) {
        SistemaSenior.abrirTela(Form115CARComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_MOTORISTA, "marcio", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_CLIENTE, "333", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_ANALISE, numeroAnalise, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PRE_FATURA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PEDIDO, numeroPedido, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PRODUTO, "SOJ004", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_DERIVACAO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115CARComponentNames.FD_PESO, "200,000", Tecla.TAB);
        SistemaSenior.clicar(Form115CARComponentNames.BT_MOSTRAR);
    }

    public void processarExpedicaoViaCarga(final String numeroPedido) {
        SistemaSenior.posicionarLinhaGradePorValor(Form115CARComponentNames.GD_ENTRADA, "Pedido", numeroPedido);
        SistemaSenior.preencherLinhaCorrenteGrade(Form115CARComponentNames.GD_ENTRADA, SistemaSenior.criarGabaritoMensagens(SystemMessageTitles.MS_CONFIRMACAO, "Deseja Realmente Sair?", "&Não"), "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.clicar(Form115CARComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Processado com sucesso", "Ok");
    }

    public void prepararExpedicaoViaNotaEntrada(final String placaVeiculo) {
        SistemaSenior.abrirTela(Form115COEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_MOTORISTA, "luiz", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FORNECEDOR, "333", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_SERIE, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_NF_ENTRADA, "71", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PRODUTO, "SOJ004", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_DERIVACAO, "1", Tecla.TAB);
        SistemaSenior.clicar(Form115COEComponentNames.BT_MOSTRAR);
    }

    public void finalizarExpedicaoViaNotaEntrada(final String placaVeiculo, final boolean isTicket) {
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PESO, "345,000", Tecla.TAB);
        if (isTicket) {
            SistemaSenior.selecionarItem(Form115COEComponentNames.RG_DOCUMENTO_A_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.selecionarItem(Form115COEComponentNames.RG_DOCUMENTO_A_EMITIR, "&Nota Fiscal");
        }
        SistemaSenior.clicar(Form115COEComponentNames.BT_CLASSI);
        SistemaSenior.posicionarLinhaGradePorValor(Form115CLAComponentNames.GD_CLASSIFICAO, "Item Classificação", "1");
        SistemaSenior.preencherLinhaCorrenteGrade(Form115CLAComponentNames.GD_CLASSIFICAO, "% Apurado", "15,0000");
        SistemaSenior.clicar(Form115CLAComponentNames.BT_OK);
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        if (isTicket) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e impressão do Ticket?", "Sim");
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e geração da Nota Fiscal?", "Sim");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executado com sucesso!", "&Não");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executado com sucesso!", "&Ok");
            SistemaSenior.clicar(Form115COEComponentNames.BT_CANCELAR_IMPRESSAO);
        }
        MetodosComuns.fecharTela(Form115COEComponentNames.FR_NOME);
    }

    public void realizarSaidaExpedicao(final String placaVeiculo, final String pesoSaida) {
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PESO, pesoSaida, Tecla.TAB);
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento e geração da Nota Fiscal?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "executado com sucesso!", "&Não");
        SistemaSenior.conferirCaixaMensagem("Advertência", "executado com sucesso!", "&Ok");
        SistemaSenior.clicar(Form115COEComponentNames.BT_CANCELAR);
        MetodosComuns.fecharTela(Form115COEComponentNames.FR_NOME);
    }

    public void realizarEntradaExpedicao(final String placaVeiculo, final String codigoFornecedor, final String numeroNotaFiscal) {
        SistemaSenior.abrirTela(Form115COEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_MOTORISTA, "JOAO", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FILIAL, "", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_SERIE, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_NF_ENTRADA, numeroNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PRODUTO, "CEV0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_DERIVACAO, "0002", Tecla.TAB);
        SistemaSenior.clicar(Form115COEComponentNames.BT_MOSTRAR);
    }

    public void processarExpedicao() {
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "&Ok");
    }

    public void selecionarNotaFiscalEntradaExpedicao() {
        SistemaSenior.preencherLinhaCorrenteGrade(Form115COEComponentNames.GD_ENTRADA, "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_TRANSPORTADORA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PESO, "1000,000", Tecla.TAB);
    }



    public void prepararTransferenciaProdutorDestino() {
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_FORNECEDOR_DESTINO, "334", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_TRANSACAO_PARA_OC, "90408", Tecla.TAB);
        SistemaSenior.clicar(Form115TRFComponentNames.BT_MOSTRAR);
    }

    public void prepararTransferenciaProdutorOrigem(final String numeroNotaFiscal) {
        SistemaSenior.abrirTela(Form115TRFComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_FORNECEDOR_ORIGEM, "333", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_PRODUTO_ORIGEM, "CEV0004", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_DERIVACAO_ORIGEM, "0002", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_SERIE_ORIGEM, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115TRFComponentNames.FD_NF_ENTRADA, numeroNotaFiscal, Tecla.TAB);
    }

    public void processarExpedicaoViaNotaEntrada() {
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
    }

    public void processarExpedicaoViaNotaEntrada(final boolean ticket) {
        SistemaSenior.clicar(Form115COEComponentNames.BT_PROCESSAR);
        if (ticket) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e impressão do Ticket?", "&Sim");
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento e geração da Nota Fiscal?", "&Sim");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executado com sucesso!", "&Não");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "executado com sucesso!", "&Ok");
            MetodosComuns.clicar(Form115COEComponentNames.BT_CANCELAR_IMPRESSAO);
        }
        MetodosComuns.fecharTela(Form115COEComponentNames.FR_NOME);
    }

    public void selecionarNotaFiscalTransportadora(final String numeroNotaFiscal) {
        SistemaSenior.posicionarLinhaGradePorValor(Form115COEComponentNames.GD_ENTRADA, "Nr. NFE", numeroNotaFiscal);
        SistemaSenior.preencherLinhaCorrenteGrade(Form115COEComponentNames.GD_ENTRADA, "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_TRANSPORTADORA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PESO, "600,000", Tecla.TAB);
    }

    public void prepararExpedicaoViaNotaEntrada(final String placaVeiculo, final String numeroNotaFiscal) {
        SistemaSenior.abrirTela(Form115COEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_MOTORISTA, "luiz", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FORNECEDOR, "333", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_SERIE, "UNI", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_NF_ENTRADA, numeroNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PRODUTO, "CEV0002", Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_DERIVACAO, "0002", Tecla.TAB);
        SistemaSenior.clicar(Form115COEComponentNames.BT_MOSTRAR);
    }

    public void finalizarExpedicaoViaNotaEntrada(final String placaVeiculo, final String numeroNotaFiscal, final boolean ticket) {
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115COEComponentNames.FD_PESO, "1000,000", Tecla.TAB);
        if (ticket) {
            SistemaSenior.selecionarItem(Form115COEComponentNames.RG_DOCUMENTO_A_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.selecionarItem(Form115COEComponentNames.RG_DOCUMENTO_A_EMITIR, "&Nota Fiscal");
        }
        SistemaSenior.posicionarLinhaGradePorValor(Form115COEComponentNames.GD_ENTRADA, "Nr. NFE", numeroNotaFiscal);
        SistemaSenior.preencherLinhaCorrenteGrade(Form115COEComponentNames.GD_ENTRADA, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public void validarRotinaExpedicaoManual(final String numeroTicketGerado) {
        SistemaSenior.abrirTela(Form115GNFComponentNames.FR_NOME);
        final String periodoGeracao = SistemaSenior.dataAtual();
        SistemaSenior.preencherCampo(Form115GNFComponentNames.FD_PERIODO_INICIAL, periodoGeracao, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115GNFComponentNames.FD_PERIODO_FINAL, periodoGeracao, Tecla.TAB);
        SistemaSenior.preencherCampo(Form115GNFComponentNames.FD_CLIENTE, "333", Tecla.TAB);
        SistemaSenior.clicar(Form115GNFComponentNames.BT_MOSTRAR);
        if (numeroTicketGerado != null) {
            SistemaSenior.conferirQuantidadeLinhasGrade(Form115GNFComponentNames.GD_TICKETS, 1);
            SistemaSenior.conferirLinhaGrade(Form115GNFComponentNames.GD_TICKETS, 0, "Núm. Ticket", numeroTicketGerado);
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Nenhum Item encontrado!", "&Ok");
        }
        MetodosComuns.fecharTela(Form115GNFComponentNames.FR_NOME);
    }

    public void validarRotinaTransferenciaProdutores(final boolean isTicket) {
        SistemaSenior.abrirTela(Form115PRCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form115PRCComponentNames.FD_FORNECEDOR, "333", Tecla.TAB);
        SistemaSenior.clicar(Form115PRCComponentNames.BT_MOSTRAR);
        if (isTicket) {
            SistemaSenior.conferirQuantidadeLinhasGrade(Form115PRCComponentNames.GD_DOCUMENTOS_EMITIDOS, 1);
            SistemaSenior.conferirLinhaGrade(Form115PRCComponentNames.GD_DOCUMENTOS_EMITIDOS, 0, "Forn.", "0");
        } else {
            SistemaSenior.conferirQuantidadeLinhasGrade(Form115PRCComponentNames.GD_DOCUMENTOS_EMITIDOS, 2);
            SistemaSenior.conferirLinhaGrade(Form115PRCComponentNames.GD_DOCUMENTOS_EMITIDOS, 0, "Sequência", "1", "Tipo Processamento", "3");
            SistemaSenior.conferirLinhaGrade(Form115PRCComponentNames.GD_DOCUMENTOS_EMITIDOS, 1, "Sequência", "2", "Tipo Processamento", "9");
        }
        MetodosComuns.fecharTela(Form115PRCComponentNames.FR_NOME);
    }

    public void cancelarNotaFiscal() {
        SistemaSenior.clicar(Form140CANComponentNames.RB_CANCELAR_NOTAS);
        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma cancelamento das Notas Fiscais?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "1 Nota(s) Canceladas com sucesso de 1 Notas selecionadas.", "Ok");
        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
    }
    

    public static void fecharCTe() {
        SistemaSenior.clicar(Form140COLComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "O CT-e será processado e fechado. Deseja continuar?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Ct-e fechado com sucesso. Deseja gerar o arquivo do Conhecimento de Transporte?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Arquivo de conhecimento de transporte gerado com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form140COLComponentNames.FR_NOME);
    }

    public void fecharCTeViaColeta() {
        SistemaSenior.clicar(Form140COLComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "O CT-e via coleta/contrato será processado.", "Ok");
        SistemaSenior.clicar(Form140GNFComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Não foram informados os dados da composição do conhecimento de transporte. Deseja informá-los ?", "Não");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Nota Fechada com Sucesso. Deseja Imprimir?", "Não");
        MetodosComuns.fecharTela(Form140COLComponentNames.FR_NOME);
    }
    
    public void parametrizarCancelamentoPedido(String permiteCancelar) {
        SistemaSenior.abrirTela(Form099UVEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form099UVEComponentNames.FD_USUARIO, "19", Tecla.TAB);
        SistemaSenior.preencherCampo(Form099UVEComponentNames.FD_PERMITE_CANCELAR, permiteCancelar, Tecla.TAB);
        SistemaSenior.clicar(Form099UVEComponentNames.BT_ALTERAR);
        MetodosComuns.fecharTela(Form099UVEComponentNames.FR_NOME);
    }
    
    public void parametrizarRecalculoPedido(String buscaValoresRecalculoPedido) {
        SistemaSenior.abrirTela("F070FVE");
        SistemaSenior.preencherCampo("DECodFil1", "1", Tecla.TAB); //Filial
        SistemaSenior.selecionarGuia("Tabulador", "Vendas 2");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja cancelar as operações?", "Sim");
        SistemaSenior.preencherCampo("DEBusVal2", buscaValoresRecalculoPedido, Tecla.TAB); //Buscar valores no recálculo do pedido
        SistemaSenior.clicar("BtnAlterar"); //Alterar
        MetodosComuns.fecharTela("F070FVE");
    }
    

    public static void parametrizarReceitaAgronomicaViaPedido(final String emiteReceita) {
        SistemaSeniorComTransacao.executarSQL("update e070ven set orefep='" + emiteReceita + "' where codemp=321 and codfil=1");
    }

    public static void prepararEmissaoReceituario() {
        SistemaSenior.clicar(Form120GPCComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma fechamento do pedido?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja Emitir Receitas para os Produtos?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja utilizar o Reaproveitamento da Numeração das Receitas", "&Sim");

        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Data receita", SistemaSenior.dataAtual(), "Código cliente", "173");
        SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Código  responsável", "1", "Cód. Tipo apl.", "1");
    }

    public static void prepararEmissaoPedidoAvaliacaoDeProdutoReceituario() {
        SistemaSenior.abrirTela(Form120GPCComponentNames.FR_NOME);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPCComponentNames.FD_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPCComponentNames.FD_TRANS_PROD, "90100", Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPCComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPCComponentNames.FD_REPRES, "320", Tecla.TAB);

        SistemaSenior.selecionarGuia(Form120GPCComponentNames.TB_ITENS, "Produtos");
    }

    public static void prepararEmissaoPedidoAgrupadoReceituario() {
        SistemaSenior.abrirTela(Form120GPDComponentNames.FR_NOME);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_TRANS_PROD, "90100", Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_TRANS_SERV, " ", Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        SistemaSenior.preencherCampo(Form120GPDComponentNames.FD_REPRES, "320", Tecla.TAB);
        SistemaSenior.clicar(Form120GPDComponentNames.BT_INSERIR);
    }

    public static void prepararProdutoREC100() {
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Produto", "REC100", "Quantidade", "2,00000");
        SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Embalagem", "1", "Cód. Espécie/Cultura", "14", "Cod. Praga/Problema", "1", "Cód. Diagnóstico", "111111111", "Cód. Tipo apl.", "1");
    }
    


    public void filtrarResponsavelTecnico(final boolean fecharTela) {
        SistemaSenior.abrirTela(Form113REMComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form113REMComponentNames.FD_SITUACAO, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form113REMComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.clicar(Form113REMComponentNames.FD_RESPONSAVEL_TECNICO);
        SistemaSenior.teclar(Tecla.F3);
        SistemaSenior.conferirQuantidadeLinhasGrade(Form113REMComponentNames.GD_PESQUISA_DE_REGISTRO, 3);
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_PESQUISA_DE_REGISTRO, "Responsável técnico", "4", "Repres.", "5");
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_PESQUISA_DE_REGISTRO, "Responsável técnico", "3", "Repres.", "3");
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_PESQUISA_DE_REGISTRO, "Responsável técnico", "1", "Repres.", "320");
        SistemaSenior.teclar(Tecla.ESC);
        if (fecharTela) {
            MetodosComuns.fecharTela(Form113REMComponentNames.FR_NOME);
        }
    }

    public static void informarResponsavelEmissaoReceituario() {
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Data receita", SistemaSenior.dataAtual(), "Código cliente", "173");
        SistemaSenior.preencherLinhaCorrenteGrade(Form113REMComponentNames.GD_DADOS_DA_RECEITA, "Código  responsável", "1", "Complemento local aplicação", "Teste complemento local aplicação", "Embalagem", "2");
    }

    public static void processarReceituario() {
        SistemaSenior.clicar(Form113REMComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Receitas Processadas com Sucesso.", "&Ok");
    }

    public static void emitirReceituario() {
        SistemaSenior.clicar(Form113REMComponentNames.BT_EMITIR_RECEITAS);
        MetodosComuns.fecharTela(Form113REMComponentNames.FR_RELATORIO_DA_RECEITA);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Receitas emitidas com sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form113REMComponentNames.FR_NOME);
    }

    public static void prepararEmissaoReceituario(boolean reaproveitarReceita, final String situacaoReceita, final boolean receituarioNotaFiscal, final String numeroDocumento) {
        SistemaSenior.abrirTela(Form113REMComponentNames.FR_NOME);
        if (reaproveitarReceita) {
            SistemaSenior.marcarCaixaAtribuicao(Form113REMComponentNames.CB_REAPROVEITAR_RECEITA);
        }
        SistemaSenior.preencherCampo(Form113REMComponentNames.FD_SITUACAO, situacaoReceita, Tecla.TAB);
        SistemaSenior.preencherCampo(Form113REMComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form113REMComponentNames.FD_CLIENTE, "173", Tecla.TAB);
        if (receituarioNotaFiscal) {
            SistemaSenior.selecionarItem(Form113REMComponentNames.RG_ORIGEM_DA_EMISSAO, "Nota Fiscal");
            SistemaSenior.preencherCampo(Form113REMComponentNames.FD_SERIE, "NFE", Tecla.TAB);
            SistemaSenior.preencherCampo(Form113REMComponentNames.FD_NOTA_FISCAL, numeroDocumento, Tecla.TAB);
        } else {
            SistemaSenior.selecionarItem(Form113REMComponentNames.RG_ORIGEM_DA_EMISSAO, "Pedido");
            SistemaSenior.preencherCampo(Form113REMComponentNames.FD_PEDIDO, numeroDocumento, Tecla.TAB);
        }
        SistemaSenior.clicar(Form113REMComponentNames.BT_MOSTRAR);
    }

    public static void cancelarReceitas(final String confirmarNumeroReceitaCancelamento) {
        SistemaSenior.clicar(Form113REMComponentNames.BT_CANCELAR_RECEITAS);
        SistemaSenior.conferirCaixaMensagem("Confirmação", confirmarNumeroReceitaCancelamento, "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Receitas canceladas com sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form113REMComponentNames.FR_NOME);
    }

    public static void parametrizarCalculoAreaReceita(final String formaCalculoAreaReceita) {
        SistemaSenior.abrirTela(Form113UMDComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form113UMDComponentNames.FD_CODIGO, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form113UMDComponentNames.FD_FORMA_CALCULO_AREA_NA_RECEITA, formaCalculoAreaReceita, Tecla.TAB);
        SistemaSenior.clicar(Form113UMDComponentNames.BT_ALTERAR);
        MetodosComuns.fecharTela(Form113UMDComponentNames.FR_NOME);
    }

    public static void posicionarProdutoReceituario(final String produto, final String quantidade) {
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_ITENS_DA_RECEITA, "Produto", produto, "Quantidade", quantidade);
    }

    public static void excluirCaracteristicaItemReceita(final int quantidadeRegistro) {
        for (int i = 0; i < quantidadeRegistro; i++) {
            SistemaSenior.teclarCombinacao(Tecla.CTRL, Tecla.DELETE);
            SistemaSenior.conferirCaixaMensagem("Confirmação de exclusão", "Deseja excluir o registro?", "&Sim");
        }
    }

    public void posicionarCaracteristicaItemReceita(final String codigoCaracteristica) {
        SistemaSenior.posicionarLinhaGradePorValor(Form113REMComponentNames.GD_CARACTERISTICAS_DO_ITEM, "Característica", codigoCaracteristica);
    }

    public int obterMenorNumeroReceitaCancelada() {
        final String[][] obterNumeroReceitaCencelada = SistemaSeniorComTransacao.recuperaValoresBaseDados("SELECT MIN(NUMREC) FROM E113RCA WHERE NUMREC IN (SELECT NUMREC FROM E113REM WHERE CODSIT = 3 AND CODAGR = 1 AND CODART = 2 AND E113RCA.NUMREC = E113REM.NUMREC)");
        final int numeroReceitaCancelada = Integer.parseInt(obterNumeroReceitaCencelada[0][0]);
        return numeroReceitaCancelada;
    }
    
    public static void inserirCabecalhoNFSaidaF140PRE(String codigocliente, String transacaoServico, String numeropedido) {
    	SistemaSenior.abrirTela("F140PRE_RFNF");
          SistemaSenior.teclar(Tecla.TAB);
          SistemaSenior.teclar(Tecla.TAB);
          SistemaSenior.preencherCampo(Form140PREComponentNames.FD_CLIENTE, codigocliente, Tecla.TAB);	      
          SistemaSenior.conferirCaixaMensagem("Confirmação", "Valor do limite de crédito do cliente estourado. Continuar?", "&Sim");
          SistemaSenior.preencherCampo(Form140PREComponentNames.FD_TRANS_SERV, transacaoServico, Tecla.TAB);
          SistemaSenior.preencherCampo(Form140PREComponentNames.FD_PEDIDO, numeropedido, Tecla.TAB);
          SistemaSenior.clicar(Form140PREComponentNames.BT_MOSTRAR);
    }

}
