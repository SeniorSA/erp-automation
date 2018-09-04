package senior.erp.utils;

import org.junit.Assert;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.SystemMessageButtons;
import senior.erp.SystemMessageTitles;
import senior.erp.TCBaseERP;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;
import senior.erp.componentNames.suprimentos.Form435MDTComponentNames;
import senior.erp.componentNames.suprimentos.Form439FIXComponentNames;
import senior.erp.componentNames.suprimentos.Form440GNEComponentNames;

/**
 * Facilitador para testes de suprimentos que possuem m�todos comuns em seus
 * casos de testes.
 * 
 *
 */
public class FacadeSuprimentos {

    public static void processarEntradaSaidaBalanca(final boolean fecharTela) {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nenhum modelo de relat�rio ligado a esta tela, ou todo(s) o(s) modelos ligados est�o inativos.", "OK");
        if (fecharTela) {
            TCBaseERP.fecharTela(Form435CCCComponentNames.FR_NOME);
        }
    }

    public static void parametrizarSituacaoContrato(final String situacaoContrato, final int codigoEmpresa, final int codigoFornecedor1, final int codigoFornecedor2, final String numeroContrato, final String numeroContrato2) {
        SistemaSeniorComTransacao.executarSQL("update e460ctr set sitctr='" + situacaoContrato + "' where codemp=" + codigoEmpresa + " and codfil between 1 and 10 and codfor in (" + codigoFornecedor1 + "," + codigoFornecedor2 + ") and numctr not in ('" + numeroContrato + "','" + numeroContrato2 + "')");
    }

    public static void parametrizarSituacaoTodosContrato(final String situacaoContrato, final int codigoEmpresa) {
        SistemaSeniorComTransacao.executarSQL("update e460ctr set sitctr='" + situacaoContrato + "' where codemp=" + codigoEmpresa + " and codfil between 1 and 10 ");
    }

    public static void processarFixacao(final boolean produtoClassificacaoFiscal, final boolean fecharTela) {
        SistemaSenior.clicar(Form439FIXComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        if (produtoClassificacaoFiscal) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Produto do item 1 n�o possui classifica��o fiscal! ", "&Ok");
        }
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "N�o existem modelos de relat�rio cadastrados e ativos ou modelos pr�-definidos ligados ao identificador de rotina \"F439FIX\"", "OK");
        if (fecharTela) TCBaseERP.fecharTela(Form439FIXComponentNames.FR_NOME);
    }

    public static void informarVeiculoFornecedorEntrada(final String placaVeiculoEntrada, final String codigoFornecedor) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculoEntrada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "TESTE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
    }

    public static void informarPesoEntrada(final boolean temContrato, final String pesoEntrada) {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
        if (!temContrato) {
            SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Filial", "0");
        }
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoEntrada, Tecla.TAB);
    }

    public static void selecionarContratoRecebimento(final String numeroContrato) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "N� Interno", numeroContrato);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CONTRATOS, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public static void realizarSaidaMercadoria(final String placaVeiculoSaida, final String pesoVeiculoSaida) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculoSaida, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoVeiculoSaida, Tecla.TAB);
    }

    public static void informarNotaFiscalProdutor() {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_N�, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SERIE_NF_PRODUTOR, "1", Tecla.TAB);
    }

    public static void realizarEntradaFixacao(final String codigoFornecedores, final String numeroContrato, final String quantidadeFixada) {
        SistemaSenior.abrirTela(Form439FIXComponentNames.FR_NOME);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_FORNECEDOR, codigoFornecedores, Tecla.TAB);
        SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_CONTRATO, numeroContrato, Tecla.TAB);
        if (quantidadeFixada != null) {
            SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_QUANTIDADE_FIXADA, quantidadeFixada, Tecla.TAB);
        }
        SistemaSenior.clicar(Form439FIXComponentNames.BT_MOSTRAR);
    }

    public static void selecionarVeiculoRecebimento(final String placaVeiculo) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435MDTComponentNames.GD_TICKET, "Placa", placaVeiculo, "Sel.", CaixaAtribuicao.DESMARCADO);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435MDTComponentNames.GD_TICKET, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public static void inserirFornecedorParticipante() {
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "N� NF Produtor", "1", "S�rie Doc. Fiscal", "1");
    }

    public static void realizarSaidaMercadoria(final String placaVeiculo, final boolean temTransacao, final String peso) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        if (temTransacao) {
            SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90408", Tecla.TAB);
        }
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, peso, Tecla.TAB);
    }

    public static void realizarEntradaMercadoria(final String placaVeiculo, final boolean gerarTicket, final String codigoFornecedor, final boolean temTransacao, final boolean temNF, final String derivacaoProduto, final String peso) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculo, Tecla.TAB);
        if (gerarTicket) {
            SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
        }
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "PEDRO", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DEPOSITO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PRODUTO, "CEV0002", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DERIVACAO, derivacaoProduto, Tecla.TAB);
        if (temTransacao) {
            SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90408", Tecla.TAB);
        }
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SAFRA, "2014", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form435CCCComponentNames.GD_CONTRATOS);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, peso, Tecla.TAB);
    }

    public static void inserirCabecalhoNotaFiscalEntrada(final String tipoNF, final String fornecedor, final String numeroNF, final String serieNF) {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, numeroNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, serieNF, Tecla.TAB);
    }

    public static void fecharNFEntrada() {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma Fechamento da Nota Fiscal?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nota Fiscal Fechada com Sucesso.", SystemMessageButtons.BT_OK);
        TCBaseERP.fecharTela(Form440GNEComponentNames.FR_NOME);
    }
    
    /**
     * Em um recebimento de gr�os de pessoa f�sica h� uma quantidade
     * l�quida da pesagem, essa � a quantidade em notas fiscais que deve ser gerada
     * Tamb�m existe a quantidade em ordens de compra, que � o saldo do produto,
     * isso tamb�m deve bater com a quantidade l�quida da pesagem. Essa valida��o
     * serve apenas para recebimento de gr�os de produtor rural
     * 
     * @param codigoDaEmpresa
     *            C�digo da empresa do recebimento de gr�os
     * @param codigoDaFilial
     *            Filial que foi gerada o recebimento de gr�os          
     * @param dataDeEntrada
     *            Data da entrada da pesagem
     * @param sequenciaDeEntradaTicket
     *            Sequ�ncia do ticket de entrada
     * @param quantidadeDaPesagemParaValidar
     *            Quantidade da pesagem que deve ser validada no m�todo
     */
    public static void garantirQuantidadeDosDocumentosGeradosPeloRecebimentoDeGraos(
            final int codigoDaEmpresa, final int codigoDaFilial, final String dataDeEntrada, final int sequenciaDeEntradaTicket,
            final Double quantidadeDaPesagemParaValidar) {  

        String[][] dadosRecebimento = SistemaSeniorComTransacao.recuperaValoresBaseDados(
                SelectsAGRO.selectDocumentosGeradosRecebimentoGraos,
                SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDaEmpresa)),
                SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDaFilial)),
                SistemaSenior.parametroSql(TipoParametroSQL.DATE, dataDeEntrada),
                SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(sequenciaDeEntradaTicket)));

        Double value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDPARTICIPANTES]);
        Assert.assertEquals("A quantidade l�quida da pesagem n�o confere com a soma da quantidade de todos os fornecedores participantes dela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDNOTASFISCAIS]);
        Assert.assertEquals("A quantidade l�quida da pesagem n�o confere com a soma da quantidade de todas os notas fiscais geradas por ela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDORDEMCOMPRA]);
        Assert.assertEquals("A quantidade l�quida da pesagem n�o confere com a soma da quantidade de todas os ordens de compra geradas por ela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDFIMPESAGEM]);
        Assert.assertEquals("A quantidade l�quida da pesagem n�o confere com a quantidade final dela", quantidadeDaPesagemParaValidar, value);      
        
    }
    
    /**
     * Obt�m o n�mero de nota fiscal de entrada gerada pelo teste, com o intuito de utilizar este dado na valida��o dos testes.
     * Por padr�o, � for�ado um data de gera��o = a data atual para somente recuperar a nota fiscal de entrada gerada pelo teste.
     * Por padr�o, ser� for�ado recuperar o n�mero como String, n�o sendo necess�rio fazer convers�es de Integer para String quando este valor for utilizado no select de valida��o.
     * @param empresa
     * @param filial
     * @param serieNotaFiscal
     * @param codigoFornecedor
     * @return
     */
    public static String obterNumeroNotaFiscalEntradaGerada(String empresa, String filial, String serieNotaFiscal, String codigoFornecedor) {
        String[][] obterNumeroNotaFiscalEntradaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select max(numnfc) from e440nfc where codemp=? and codfil=? and codsnf=? and codfor=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, serieNotaFiscal), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoFornecedor), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroNotaFiscalEntradaGerada = obterNumeroNotaFiscalEntradaGerada[0][0];
        return numeroNotaFiscalEntradaGerada;
    }

    /**
     * Obt�m o n�mero de ordem de compra gerada pelo teste, com o intuito de utilizar este dado na valida��o dos testes.
     * Por padr�o, � for�ado um data de gera��o = a data atual para somente recuperar a ordem de compra gerada pelo teste.
     * Por padr�o, ser� for�ado recuperar o n�mero como String, n�o sendo necess�rio fazer convers�es de Integer para String quando este valor for utilizado no select de valida��o.
     * @param empresa
     * @param filial
     * @param codigoFornecedor
     * @return
     */
    public static String obterNumeroOrdemCompraGerada(String empresa, String filial, String codigoFornecedor) {
        String[][] obterNumeroOrdemCompraGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numOcp from e420ocp where codemp=? and codfil=? and codfor=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoFornecedor), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroOrdemCompraGerada = obterNumeroOrdemCompraGerada[0][0];
        return numeroOrdemCompraGerada;
    }

    /**
     * Reposiciona ou posiciona o campo na grade.
     * Atualiza a posi��o de todos os campos at� encontrar a nova posi��o para o campo pretendido
     * Caso o campo ainda n�o foi gravado, insere o mesmo na posi��o determinada.
     * @param codigoDoUsuario - c�digo do usu�rio
     * @param idModulo - m�dulo
     * @param nomeFormulario - nome do formul�rio
     * @param nomeGride - nome da grade
     * @param nomeCampo - nome do campo
     * @param novaPosicaoCampo - nova posi��o do campo
     */
    public static void reposicionarCampoEmGrade(final int codigoDoUsuario, final int idModulo, final String nomeFormulario,
            final String nomeGride, final String nomeCampo, final int novaPosicaoCampo) {   

        final String[][] obterIndiceGrade = SistemaSeniorComTransacao.recuperaValoresBaseDados(
                "select coalesce(max(indfld), 0) " +
                "from r999grf " +
                "where codusu=? " +
                "and modid=? " +
                "and nomfrm=? " +
                "and nomgri=? " +
                "and fldnam=? ",
                SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDoUsuario)),
                SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(idModulo)),
                SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeFormulario),
                SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeGride),
                SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeCampo));
        final int indiceGrade = Integer.parseInt(obterIndiceGrade[0][0]);
        if (indiceGrade > 0) {
            SistemaSeniorComTransacao.executarSQL(
                    "update r999grf " +
                    "set indfld=indfld + 1 " +
                    "where codusu=? " +
                    "and modid=? " +
                    "and nomfrm=? " +
                    "and nomgri=? " +
                    "and indfld between ? and ?",
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDoUsuario)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(idModulo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeFormulario),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeGride),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(novaPosicaoCampo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(indiceGrade)));
            SistemaSeniorComTransacao.executarSQL(
                    "update r999grf " +
                    "set indfld=? " +
                    "where codusu=? " +
                    "and modid=? " +
                    "and nomfrm=? " +
                    "and nomgri=? " +
                    "and fldnam=? ",
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(novaPosicaoCampo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDoUsuario)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(idModulo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeFormulario),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeGride),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeCampo));
        } else {
            SistemaSeniorComTransacao.executarSQL(
                    "update r999grf " +
                    "set indfld=indfld + 1 " +
                    "where codusu=? " +
                    "and modid=? " +
                    "and nomfrm=? " +
                    "and nomgri=? " +
                    "and indfld>=?",
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDoUsuario)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(idModulo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeFormulario),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeGride),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(novaPosicaoCampo)));
            SistemaSeniorComTransacao.executarSQL(
                    "insert into r999grf " +
                    "(codusu, modid, nomfrm, nomgri, fldnam, width, indfld, kidord) " +
                    "values (?, ?, ?, ?, ?, 100, ?, 0)",
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(codigoDoUsuario)),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(idModulo)),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeFormulario),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeGride),
                    SistemaSenior.parametroSql(TipoParametroSQL.STRING, nomeCampo),
                    SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, String.valueOf(novaPosicaoCampo)));
        }
    }
    
    /**
     * Obt�m o n�mero de fixa��o gerada pelo teste, com o intuito de utilizar este dado na valida��o dos testes.
     * Por padr�o, � for�ado um data de gera��o = a data atual para somente recuperar a fixa��o gerada pelo teste.
     * Por padr�o, ser� for�ado recuperar o n�mero como String, n�o sendo necess�rio fazer convers�es de Integer para String quando este valor for utilizado no select de valida��o.
     * @param empresa
     * @param filial
     * @param codigoFornecedor
     * @return
     */
    public static String obterNumeroFixacaoGerada(String empresa, String filial) {
        String[][] obterNumeroFixacaoGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numFix from e439fix where codemp=? and codfil=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroFixacaoGerada = obterNumeroFixacaoGerada[0][0];
        return numeroFixacaoGerada;
    }
    
    /**
     * Obt�m o n�mero de cota��o gerada pelo teste, com o intuito de utilizar este dado na valida��o dos testes.
     * Por padr�o, � for�ado um data de gera��o = a data atual para somente recuperar a cota��o gerada pelo teste.
     * Por padr�o, ser� for�ado recuperar o n�mero como String, n�o sendo necess�rio fazer convers�es de Integer para String quando este valor for utilizado no select de valida��o.
     * @param empresa
     * @param codigoFornecedor
     * @return
     */
    public static String obterNumeroCotacaoGerada(String empresa, String fornecedor) {
        String[][] obterNumeroCotacaoGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numCot from e410cot where codemp=? and codfor=? and datcot=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroCotacaoGerada = obterNumeroCotacaoGerada[0][0];
        return numeroCotacaoGerada;
    }

}
