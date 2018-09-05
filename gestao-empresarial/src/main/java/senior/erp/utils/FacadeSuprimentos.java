package senior.erp.utils;

import org.junit.Assert;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.EstadoCelula;
import com.senior.framework.testes.EstadoComponente;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemMessageButtons;
import senior.erp.SystemMessageTitles;
import senior.erp.componentNames.cadastros.Form099UCPComponentNames;
import senior.erp.componentNames.financas.Form502CCPComponentNames;
import senior.erp.componentNames.financas.Form502SCPComponentNames;
import senior.erp.componentNames.financas.Form504CONComponentNames;
import senior.erp.componentNames.mercado.Form140CANComponentNames;
import senior.erp.componentNames.suprimentos.Form000INEComponentNames;
import senior.erp.componentNames.suprimentos.Form210STPComponentNames;
import senior.erp.componentNames.suprimentos.Form210TPAComponentNames;
import senior.erp.componentNames.suprimentos.Form211LIGComponentNames;
import senior.erp.componentNames.suprimentos.Form215FESComponentNames;
import senior.erp.componentNames.suprimentos.Form250CTC_CPComponentNames;
import senior.erp.componentNames.suprimentos.Form250CTC_CRComponentNames;
import senior.erp.componentNames.suprimentos.Form250TCTComponentNames;
import senior.erp.componentNames.suprimentos.Form410CEAComponentNames;
import senior.erp.componentNames.suprimentos.Form410CPAComponentNames;
import senior.erp.componentNames.suprimentos.Form420APRComponentNames;
import senior.erp.componentNames.suprimentos.Form420GOCComponentNames;
import senior.erp.componentNames.suprimentos.Form435BLOComponentNames;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;
import senior.erp.componentNames.suprimentos.Form435MDTComponentNames;
import senior.erp.componentNames.suprimentos.Form439FIXComponentNames;
import senior.erp.componentNames.suprimentos.Form439SFXComponentNames;
import senior.erp.componentNames.suprimentos.Form440CIPComponentNames;
import senior.erp.componentNames.suprimentos.Form440CISComponentNames;
import senior.erp.componentNames.suprimentos.Form440GNEComponentNames;
import senior.erp.componentNames.suprimentos.Form440VALComponentNames;
import senior.erp.componentNames.suprimentos.Form441CNEComponentNames;
import senior.erp.componentNames.suprimentos.Form445PRCComponentNames;
import senior.erp.componentNames.suprimentos.Form460CXOComponentNames;
import senior.erp.componentNames.suprimentos.Form460PFOComponentNames;
import senior.erp.componentNames.suprimentos.Form461GNCComponentNames;

/**
 * Facilitador para testes de suprimentos que possuem métodos comuns em seus
 * casos de testes.
 * 
 *
 */
public class FacadeSuprimentos {

   public static void parametrizarSituacaoTodosContrato(final String situacaoContrato, final int codigoEmpresa) {
        SistemaSeniorComTransacao.executarSQL("update e460ctr set sitctr='" + situacaoContrato + "' where codemp=" + codigoEmpresa + " and codfil between 1 and 10 ");
    }

    public static void informarPesoEntrada(final boolean temContrato, final String pesoEntrada) {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
        if (!temContrato) {
            SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Filial", "0");
        }
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoEntrada, Tecla.TAB);
    }

    public static void selecionarContratoRecebimento(final String numeroContrato) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Nº Interno", numeroContrato);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CONTRATOS, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public static void realizarSaidaMercadoria(final String placaVeiculoSaida, final String pesoVeiculoSaida) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculoSaida, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoVeiculoSaida, Tecla.TAB);
    }

    public static void informarNotaFiscalProdutor() {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_Nº, "1", Tecla.TAB);
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
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Nº NF Produtor", "1", "Série Doc. Fiscal", "1");
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
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }
    
    /**
     * Em um recebimento de grãos de pessoa física há uma quantidade
     * líquida da pesagem, essa é a quantidade em notas fiscais que deve ser gerada
     * Também existe a quantidade em ordens de compra, que é o saldo do produto,
     * isso também deve bater com a quantidade líquida da pesagem. Essa validação
     * serve apenas para recebimento de grãos de produtor rural
     * 
     * @param codigoDaEmpresa
     *            Código da empresa do recebimento de grãos
     * @param codigoDaFilial
     *            Filial que foi gerada o recebimento de grãos          
     * @param dataDeEntrada
     *            Data da entrada da pesagem
     * @param sequenciaDeEntradaTicket
     *            Sequência do ticket de entrada
     * @param quantidadeDaPesagemParaValidar
     *            Quantidade da pesagem que deve ser validada no método
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
        Assert.assertEquals("A quantidade líquida da pesagem não confere com a soma da quantidade de todos os fornecedores participantes dela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDNOTASFISCAIS]);
        Assert.assertEquals("A quantidade líquida da pesagem não confere com a soma da quantidade de todas os notas fiscais geradas por ela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDORDEMCOMPRA]);
        Assert.assertEquals("A quantidade líquida da pesagem não confere com a soma da quantidade de todas os ordens de compra geradas por ela", quantidadeDaPesagemParaValidar, value);
        value = Double.parseDouble(dadosRecebimento[0][SelectsAGRO.QTDFIMPESAGEM]);
        Assert.assertEquals("A quantidade líquida da pesagem não confere com a quantidade final dela", quantidadeDaPesagemParaValidar, value);      
        
    }
    
    /**
     * Obtém o número de nota fiscal de entrada gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a nota fiscal de entrada gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
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
     * Obtém o número de ordem de compra gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a ordem de compra gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
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
     * Atualiza a posição de todos os campos até encontrar a nova posição para o campo pretendido
     * Caso o campo ainda não foi gravado, insere o mesmo na posição determinada.
     * @param codigoDoUsuario - código do usuário
     * @param idModulo - módulo
     * @param nomeFormulario - nome do formulário
     * @param nomeGride - nome da grade
     * @param nomeCampo - nome do campo
     * @param novaPosicaoCampo - nova posição do campo
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
     * Obtém o número de fixação gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a fixação gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
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
     * Obtém o número de cotação gerada pelo teste, com o intuito de utilizar este dado na validação dos testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar a cotação gerada pelo teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para String quando este valor for utilizado no select de validação.
     * @param empresa
     * @param codigoFornecedor
     * @return
     */
    public static String obterNumeroCotacaoGerada(String empresa, String fornecedor) {
        String[][] obterNumeroCotacaoGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numCot from e410cot where codemp=? and codfor=? and datcot=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, fornecedor), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroCotacaoGerada = obterNumeroCotacaoGerada[0][0];
        return numeroCotacaoGerada;
    }
    
    public static void processarEntradaSaidaBalanca(final boolean fecharTela) {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nenhum modelo de relatório ligado a esta tela, ou todo(s) o(s) modelos ligados estão inativos.", "OK");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form435CCCComponentNames.FR_NOME);
        }
    }

    public static void parametrizarSituacaoContrato(final String situacaoContrato, final int codigoEmpresa, final int codigoFornecedor1, final int codigoFornecedor2, final String numeroContrato, final String numeroContrato2) {
        SistemaSeniorComTransacao.executarSQL("update e460ctr set sitctr='" + situacaoContrato + "' where codemp=" + codigoEmpresa + " and codfil between 1 and 10 and codfor in (" + codigoFornecedor1 + "," + codigoFornecedor2 + ") and numctr not in ('" + numeroContrato + "','" + numeroContrato2 + "')");
    }

    public static void processarFixacao(final boolean produtoClassificacaoFiscal, final boolean fecharTela) {
        SistemaSenior.clicar(Form439FIXComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        if (produtoClassificacaoFiscal) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Produto do item 1 não possui classificação fiscal! ", "&Ok");
        }
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Não existem modelos de relatório cadastrados e ativos ou modelos pré-definidos ligados ao identificador de rotina \"F439FIX\"", "OK");
        if (fecharTela) MetodosComuns.fecharTela(Form439FIXComponentNames.FR_NOME);
    }

    public static void processarFixacao(final boolean produtoClassificacaoFiscal) {
        processarFixacao(produtoClassificacaoFiscal, true);
    }

    public static void selecionarNotaFiscalTicket(final boolean emitirNota) {
        if (emitirNota) {
            SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
        } else {
            SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "Tic&ket");
        }
    }

    public static void informarVeiculoFornecedorEntrada(final String placaVeiculoEntrada, final String codigoFornecedor) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, placaVeiculoEntrada, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "TESTE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
    }

    public static void informarOrigemMercadoria(final String origemMercadoria) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_ORIGEM_MERCADORIA, origemMercadoria, Tecla.TAB);
    }

    public static void informarDepositoProdutoDerivacaoEntrada(final String deposito, final String produto, final String derivacao) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DEPOSITO, deposito, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PRODUTO, produto, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DERIVACAO, derivacao, Tecla.TAB);
    }

    public static void alterarTipoFornecedorCliente(final String tipoFornecedorCliente, final String cpfCnpj, final int codigoFornecedorCliente) {
        SistemaSeniorComTransacao.executarSQL("update e085cli set tipcli=?, cgccpf=? where codcli=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, tipoFornecedorCliente), SistemaSenior.parametroSql(TipoParametroSQL.LONG, cpfCnpj), SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, Integer.toString(codigoFornecedorCliente)));
        SistemaSeniorComTransacao.executarSQL("update e095for set tipfor=?, cgccpf=? where codfor=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, tipoFornecedorCliente), SistemaSenior.parametroSql(TipoParametroSQL.LONG, cpfCnpj), SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, Integer.toString(codigoFornecedorCliente)));
    }

    public static void alterarPercentualClassificacao(final String itemClassificacao, final String percentualApurado) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CLASSIFICACAO, "Item Classificação", itemClassificacao);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CLASSIFICACAO, "% Apurado", percentualApurado);
    }

    public static void validarOpcaoRecebimento(final boolean tipoOpcao) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.conferirEstadoComponente(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, EstadoComponente.DESABILITADO);
        if (tipoOpcao) {
            SistemaSenior.conferirItemSelecionado(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.conferirItemSelecionado(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
        }
        MetodosComuns.fecharTela(Form435CCCComponentNames.FR_NOME);
    }

    public static void validarOpcaoRecebimentoHabilitado(final boolean tipoOpcao) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.conferirEstadoComponente(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, EstadoComponente.HABILITADO);
        if (tipoOpcao) {
            SistemaSenior.conferirItemSelecionado(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "Tic&ket");
        } else {
            SistemaSenior.conferirItemSelecionado(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
        }
        MetodosComuns.fecharTela(Form435CCCComponentNames.FR_NOME);
    }

    public static void alterarSituacoesTicket(final String numeroTicket) {
        SistemaSeniorComTransacao.executarSQL("update e115ces set sitces=5 where codemp=170 and codfil=1 and numtic in (" + numeroTicket + ")");
    }

    public static void parametrizarRecebimentoDoUsuario(final String tipoDocumento) {
        SistemaSeniorComTransacao.executarSQL("update e099cpr set demitr='" + tipoDocumento + "' where codemp=170 and codusu=10");
    }

    public void inserirContrataoParticipacaoFornecedores(final String numeroContrato, final String complementoContrato) {
        SistemaSenior.abrirTela(Form460PFOComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_N_INTERNO, numeroContrato, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_FORNECEDOR, "323", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_OBJETO, complementoContrato, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_TIPO_DE_VALORIZACAO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_CONSISTE_QUANTIDADE, "S", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_CONTRATO_PRIORITARIO_RECEBIMENTO, "S", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_CONTRATO_PRIORITARIO_PAGAMENTO_DIVIDA, "N", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_MOEDA, "01", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_APLICACAO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_DATA_DE_VENCIMENTO_DOS_TITULOS, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_CONDICAO_PAGTO, "030", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_CONTA_FORNECEDOR, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_TRANSACAO_PRODUTO, "90100", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_SAFRA, "2014A", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_SITUACAO, "A", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_GERA_FINANCEIRO, "S", Tecla.TAB);
    }

    public void inserirRoyaltiesContrato(final String valorCredito, final String protocolo) {
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_VALOR_CREDITO, valorCredito, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_PROTOCOLO, protocolo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460PFOComponentNames.FD_FORNECEDOR_ROYALTIES, "326", Tecla.TAB);
    }

    public void inserirParcelasContrato() {
        SistemaSenior.selecionarGuia(Form460PFOComponentNames.TS_AGRUPADOR_DAS_ABAS, "Parcelamento");
        SistemaSenior.preencherLinhaCorrenteGrade(Form460PFOComponentNames.GD_PARCELAMENTO, "Tipo Tít.", "TX", "Série tít.", "Ctr");
    }

    public void inserirProdutoContrato(final String produto, final String quantidade) {
        SistemaSenior.selecionarGuia(Form460PFOComponentNames.TS_AGRUPADOR_DAS_ABAS, "Produtos");
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherNovaLinhaGrade(Form460PFOComponentNames.GD_PRODUTOS, "Produto", produto, "Derivação", "C000", "Depósito", "1", "Quantidade", quantidade, "Preço Unitário", "1,25", "Transgenia", "RR-2");
    }

    public void processarContratoParticipacaoFornecedores() {
        SistemaSenior.clicar(Form460PFOComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Advertência", "Contrato de Compra processado com sucesso!", "Ok");
        MetodosComuns.fecharTela(Form460PFOComponentNames.FR_NOME);
    }

    public void inativarRateioTransacao(final String codigoTransacao) {
        SistemaSeniorComTransacao.executarSQL("insert into e001rat (codemp, codtns, seqrat, tiprsc, crirat, numprj, codfpj, ctafin, ctared, percta, codccu, perrat) values (321, '" + codigoTransacao + "', 1, 'C', 5, 0, 0, 3020, 3020, 100, '130', 100)");
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=2 where codemp=321 and codtns='" + codigoTransacao + "'");
    }

    public void zerarFunruralSenarFornecedor() {
        SistemaSeniorComTransacao.executarSQL("update e095hfo set perfun=0, persen=0 where codemp=321 and codfil=1 and codfor=323");
    }

    public void parametrizarAprovacaoMultinivel(final String situacaoRotina) {
        SistemaSeniorComTransacao.executarSQL("update e068una set situna='" + situacaoRotina + "' where codemp=170 and rotnap=15 and codnap=2 and codusu=45");
    }
    
    public static void ativarRotinaDeCotacaoMultinivel() {
        SistemaSenior.abrirTela("F068FNA");

        SistemaSenior.preencherCampo("ERotNap", "9");

        SistemaSenior.clicar(Form410CEAComponentNames.BT_MOSTRAR);

        SistemaSenior.preencherCampoGrade("GridFna", "Situação", 0, "A", Tecla.SETA_CIMA);

        SistemaSenior.clicar(Form410CEAComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form410CPAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processamento realizado com sucesso!", Form410CEAComponentNames.BT_OK);

        MetodosComuns.fecharTela("F068FNA");
    }
    
    public void gerarOrdemCompraAgrupada(final boolean consisteTabelaPrecoPisCofins, final String numeroOrdemCompra, final String codFornecedor, final String codProduto, final String derivacao, final String Quantidade, final String precoUnitario) {
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);
        SistemaSenior.abrirTela(Form420GOCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_N_ORDEM_COMPRA, numeroOrdemCompra, Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_FORNECEDOR, codFornecedor, Tecla.TAB);
        SistemaSenior.selecionarGuia(Form420GOCComponentNames.TB_GUIAS, "Produtos");

        if (consisteTabelaPrecoPisCofins) {
            SistemaSenior.preencherNovaLinhaGrade(Form420GOCComponentNames.GD_PRODUTOS, SistemaSenior.criarGabaritoMensagens("Aviso", "Erro na busca da tabela de preço \"PISI\" na empresa \"170\": Tabela de Preço (PISI) não cadastrada, inativa, fora do período ou produto - derivação (PISCOFINSIMP - 1) não cadastrado na tabela de preço.", "&Ok"), SistemaSenior.criarSequenciaTeclas(Tecla.SETA_CIMA), "Produto", "PISCOFINSIMP", "Deriv.", "1", "Qtde. Pedida", "1", "Preço Unitário", "2,83");
        } else {
            SistemaSenior.preencherLinhaCorrenteGrade(Form420GOCComponentNames.GD_PRODUTOS, "Produto", codProduto, "Deriv.", derivacao, "Qtde. Pedida", Quantidade, "Preço Unitário", precoUnitario);
            processarOrdemDeCompra(true);
        }
    }

    public static void incluiProdutoSugestaoClassificacaoFiscal(final String transacaoProduto, final String fornecedor, final String produto) {
        SistemaSenior.abrirTela(Form420GOCComponentNames.FR_NOME);

        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_TRANSACAO_PRODUTOS, transacaoProduto, Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);

        SistemaSenior.selecionarGuia(Form420GOCComponentNames.TB_GUIAS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form420GOCComponentNames.GD_PRODUTOS, "Produto", produto, "Deriv.", "X", "Qtde. Pedida", "10", "Preço Unitário", "10");

        processarOrdemDeCompra(false);
        recalcularOrdemDeCompra();

        MetodosComuns.fecharTela(Form420GOCComponentNames.FR_NOME);
    }

    public static void incluiServicoSugestaoClassificacaoFiscal(final String transacaoServico, final String fornecedor, final String servico) {
        SistemaSenior.abrirTela(Form420GOCComponentNames.FR_NOME);

        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_TRANSACAO_SERVICOS, transacaoServico, Tecla.TAB);
        SistemaSenior.preencherCampo(Form420GOCComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);

        SistemaSenior.selecionarGuia(Form420GOCComponentNames.TB_GUIAS, "Serviços");
        SistemaSenior.preencherLinhaCorrenteGrade(Form420GOCComponentNames.GD_SERVICOS, "Serviço", servico, "Quantidade Pedida", "10", "Preço Unitário", "10");

        processarOrdemDeCompra(false);
        recalcularOrdemDeCompra();

        MetodosComuns.fecharTela(Form420GOCComponentNames.FR_NOME);
    }

    public static void recalcularOrdemDeCompra() {
        SistemaSenior.clicar(Form420GOCComponentNames.BT_RECALCULAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja recalcular a Ordem de Compra?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Ordem de Compra recalculada com sucesso.", "&Ok");
    }

    public static void processarOrdemDeCompra(final boolean fecharTela) {
        SistemaSenior.clicar(Form420GOCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Advertência", "Ordem de Compra processada com sucesso!", "&Ok");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form420GOCComponentNames.FR_NOME);
        }
    }

    /**
     * Realiza a alteração do parâmetro "Possui Serviços c/ ICMS/IPI" (E070EMP.COMPSI), localizado no cadastro da
     * empresa.
     * Por padrão, este parâmetro é N = "Não".
     */
    public static void alteraClassificacaoFiscalEmpresa(final String indicativoPossuiServico, final String codigoEmpresa) {
        MetodosComunsNucleo.selecionarEmpresaFilial(10, 1); //Necessário realizar update abaixo logado em outra empresa, campo COMPSI da tabela E070EMP fica em memória.
        SistemaSeniorComTransacao.executarSQL("UPDATE e070emp SET compsi= '" + indicativoPossuiServico + "' WHERE codemp= " + codigoEmpresa + "");
    }

    public static void validarExclusaoComissoesRepresentante() {
        SistemaSenior.abrirTela(Form504CONComponentNames.FR_NOME);

        SistemaSenior.clicar(Form504CONComponentNames.BT_MOSTRAR);
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nenhum registro encontrado. ", "&Ok");

        MetodosComuns.fecharTela(Form504CONComponentNames.FR_NOME);
    }

    public static void validarComissoesRepresentante() {
        SistemaSenior.abrirTela(Form504CONComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form504CONComponentNames.FD_PERIODO_INICIAL, "20/03/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form504CONComponentNames.FD_PERIODO_FINAL, "20/03/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form504CONComponentNames.FD_REPRESENTANTE, "1", Tecla.TAB);

        SistemaSenior.clicar(Form504CONComponentNames.BT_MOSTRAR);

        SistemaSenior.posicionarLinhaGradePorValor(Form504CONComponentNames.GD_COMISSOES, "Repres.", "1", "Dat Base", "20/03/2015", "Vlr.Comissão", "7,49");
    }
    
    public void prepararOCAprovacao(final boolean existeOCAprovacao) {
        SistemaSenior.abrirTela(Form420APRComponentNames.FR_NOME);
        SistemaSenior.selecionarItem(Form420APRComponentNames.CB_OPERACAO, "Aprovar");
        SistemaSenior.preencherCampo(Form420APRComponentNames.FD_FORNECEDOR, "60", Tecla.TAB);
        SistemaSenior.preencherCampo(Form420APRComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form420APRComponentNames.FD_OC, "3", Tecla.TAB);
        SistemaSenior.preencherCampo(Form420APRComponentNames.FD_EMISSAO_INICIO, "00/00/0000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form420APRComponentNames.FD_EMISSAO_FIM, "00/00/0000", Tecla.TAB);
        SistemaSenior.selecionarItem(Form420APRComponentNames.CB_CONT_APROVACAO, "Em Análise");
        SistemaSenior.marcarCaixaAtribuicao(Form420APRComponentNames.CH_FECHAR_APOS_A_ULTIMA_APROVACAO);
        SistemaSenior.clicar(Form420APRComponentNames.BT_MOSTRAR);

        if (existeOCAprovacao) {
            SistemaSenior.conferirLinhaGrade(Form420APRComponentNames.GD_ORDENS_DE_COMPRA, 0, "Ordem de Compra", "3");
        } else {
            SistemaSenior.conferirCaixaMensagem("Advertência", "Nenhuma Ordem de Compra encontrada!", "Ok");
        }
        MetodosComuns.fecharTela(Form420APRComponentNames.FR_NOME);
    }
    
    public static void validarTransferenciaEntreProdutos(final String valorProduto1102, final String valorProduto1103) {
        SistemaSenior.abrirTela(Form211LIGComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form211LIGComponentNames.FD_PERIODO_INICIAL, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form211LIGComponentNames.FD_PERIODO_FINAL, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form211LIGComponentNames.FD_TRANSACAO_S, "90266", Tecla.TAB);

        SistemaSenior.clicar(Form211LIGComponentNames.BT_MOSTRAR);

        SistemaSenior.posicionarLinhaGradePorValor(Form211LIGComponentNames.GD_MOVIMENTOS, "E/S", "E", "Produto", "1102", "Valor Mov", valorProduto1102);
        SistemaSenior.posicionarLinhaGradePorValor(Form211LIGComponentNames.GD_MOVIMENTOS, "E/S", "E", "Produto", "1103", "Valor Mov", valorProduto1103);

        MetodosComuns.fecharTela(Form211LIGComponentNames.FR_NOME);
    }

    public static void realizarFechamentoDosEstoques() {
        SistemaSenior.abrirTela(Form215FESComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form215FESComponentNames.FD_PERIODO_FINAL, SistemaSenior.dataAtual(), Tecla.TAB);

        SistemaSenior.clicar(Form215FESComponentNames.RB_ATUALIZACAO_FECHAMENTO_PERIODO);
        SistemaSenior.clicar(Form215FESComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("*", "Período de fechamento fora do Período Contábil. ", "&Ok");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja realmente processar?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "O produto produzido CMP001, derivação 1 está com um valor de preço de custo zerado. Verifique! ", "&Ok");

        MetodosComuns.fecharTela(Form215FESComponentNames.FR_NOME);
    }

    public static void realizarTransferenciaEntreProdutos() {
        SistemaSenior.abrirTela(Form210TPAComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_DATA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_TRANS_SAIDA, "90266", Tecla.TAB);
        SistemaSenior.selecionarItem(Form210TPAComponentNames.RG_TIPO_TRANSFERENCIA, "Entre Produ&tos");

        SistemaSenior.clicar(Form210TPAComponentNames.BT_SELECAO);

        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_SAIDAS_PRODUTO, "\"1101\",\"00006\"", Tecla.TAB);
        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_SAIDAS_DERIVACAO, "\"1\",\" \"", Tecla.TAB);
        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_SAIDAS_DEPOSITO, "\"1\",\"DEP01\"", Tecla.TAB);
        SistemaSenior.selecionarItem(Form210STPComponentNames.RG_TRANSFERIR, "&Quantidade");
        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_SAIDAS_QUANTIDADE, "10,00", Tecla.TAB);

        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_ENTRADAS_PRODUTO, "1102", Tecla.TAB);
        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_ENTRADAS_DEPOSITO, "DEP01", Tecla.TAB);
        SistemaSenior.preencherCampo(Form210STPComponentNames.FD_ENTRADAS_QUANTIDADE, "10,00", Tecla.TAB);
        SistemaSenior.clicar(Form210STPComponentNames.BT_OK);

        SistemaSenior.clicar(Form210TPAComponentNames.BT_MOSTRAR);

        SistemaSenior.posicionarLinhaGradePorValor(Form210TPAComponentNames.GD_DE_MOVIMENTO_SAIDA, "Produto", "00006");
        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_DE_MOVIMENTO_SAIDA, "Lote Fab.", "0001");

        SistemaSenior.posicionarLinhaGradePorValor(Form210TPAComponentNames.GD_PARA_MOVIMENTO_ENTRADA, "Produto", "1102");
        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_PARA_MOVIMENTO_ENTRADA, "Valor", "0,01");
        SistemaSenior.preencherNovaLinhaGrade(Form210TPAComponentNames.GD_PARA_MOVIMENTO_ENTRADA, "Produto", "1103", "Depósito", "DEP01", "Qtdade", "10", "Valor", "10");

        SistemaSenior.clicar(Form210TPAComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Transferência?", "&Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "&Ok");

        MetodosComuns.fecharTela(Form210TPAComponentNames.FR_NOME);
    }
    
    public static void prepararTransferenciaProdutosLotesIguais() {
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_DATA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_TRANS_SAIDA, "90253", Tecla.TAB);
        SistemaSenior.selecionarItem(Form210TPAComponentNames.RG_TIPO_TRANSFERENCIA, "Entre Produ&tos");

        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_DE_MOVIMENTO_SAIDA, "Produto", "LOT001", "Derivação", "1", "Dep.Origem", "1", "Lote Fab.", "001", "Qtdade", "10");
        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_PARA_MOVIMENTO_ENTRADA, "Produto", "LOT002", "Derivação", "1", "Depósito", "2", "Lote Fab.", "001", "Qtdade", "10", "Valor", "10");

        SistemaSenior.clicar(Form210TPAComponentNames.BT_PROCESSAR);
    }

    public static void prepararTransferenciaProdutosLotesDiferentes() {
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_DATA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form210TPAComponentNames.FD_TRANS_SAIDA, "90253", Tecla.TAB);
        SistemaSenior.selecionarItem(Form210TPAComponentNames.RG_TIPO_TRANSFERENCIA, "Entre Produ&tos");

        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_DE_MOVIMENTO_SAIDA, "Produto", "LOT001", "Derivação", "1", "Dep.Origem", "1", "Lote Fab.", "001", "Qtdade", "10");
        SistemaSenior.preencherLinhaCorrenteGrade(Form210TPAComponentNames.GD_PARA_MOVIMENTO_ENTRADA, "Produto", "LOT002", "Derivação", "1", "Depósito", "2", "Lote Fab.", "002", "Qtdade", "10", "Valor", "10");

        SistemaSenior.clicar(Form210TPAComponentNames.BT_PROCESSAR);
    }
    
    public void prepararInicioInventario(boolean produtosLoteSerie) {
        MetodosComunsNucleo.selecionarEmpresaFilial(50, 1);
        SistemaSenior.abrirTela("F220INI_SEIV");
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo("ECodDep", "DEP03", Tecla.TAB); //Depósito
        SistemaSenior.preencherCampo("EBloMov", "N", Tecla.TAB); //Bloq.Movimen.
        SistemaSenior.selecionarItem("CTipInv", "Todos"); //Opção
        if (produtosLoteSerie) {
            SistemaSenior.preencherCampo("ELotSer", "S", Tecla.TAB); //Lote/Série
        } else {
            SistemaSenior.preencherCampo("ELotSer", "N", Tecla.TAB); //Lote/Série
        }
    }

    public void gerarInventario() {
        SistemaSenior.marcarCaixaAtribuicao("RBLocalizacao"); //Por Depósito (Localização)
        SistemaSenior.clicar("Selecao");
        SistemaSenior.preencherCampo("ECodOri", "MAT", Tecla.TAB); //Origem
        SistemaSenior.preencherCampo("ECodFam", "MM", Tecla.TAB); //Familia
        SistemaSenior.clicar("Ok");
        SistemaSenior.clicar("Mostrar");
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Início de Inventário?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Inventário processado com sucesso!", "OK");
        MetodosComuns.fecharTela("F220INI");
    }

    public void processarInventario(boolean consisteProcessamento) {
        SistemaSenior.clicar("Processar");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Confirma Entrada do Inventário de Estoques", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Entrada do Inventário processada! Será efetuado o acerto automático dos itens com contagem que confira com o estoque ou com contagens anteriores ou que atingiram a \"Qtde Máxima de Contagens para Acerto Automático\".", "OK");
        if (consisteProcessamento) {
            SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "OK");
        }
    }
    
    public static void cadastrarBloqueioProduto(final String codigoFornecedor, final String derivacao, final String quantidadeBloqueio, final boolean filtrarOrdens) {
        SistemaSenior.abrirTela(Form435BLOComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_DESCRICAO, "teste-bloqueio-produto", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_MOTIVO, "101", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_PRODUTO, "CEV0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_DERIVACAO, derivacao, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_QUANTIDADE, quantidadeBloqueio, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_OBSERVACAO, "teste-bloqueio-produto", Tecla.TAB);
        SistemaSenior.clicar(Form435BLOComponentNames.BT_MOSTRAR);
        if (filtrarOrdens) {
            SistemaSenior.selecionarGuia(Form435BLOComponentNames.TS_AGRUPADOR_DAS_ABAS, "Ordens de Compra (2)");
            SistemaSenior.preencherCampo(Form435BLOComponentNames.FD_ORDEM_DE_COMPRA, "49,50,52,54,56,61", Tecla.TAB);
            SistemaSenior.clicar(Form435BLOComponentNames.BT_MOSTRAR);
        }
        SistemaSenior.clicar(Form435BLOComponentNames.FD_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processamento concluído!", "&Ok");
        MetodosComuns.fecharTela(Form435BLOComponentNames.FR_NOME);
    }

    public static void alterarPercentualFornecedorParticipante() {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Fornecedor Part.", "333");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "% Dep.", "100", "Trans. Dep.", "90408", "% Comp. Imed.", "0,00");
    }

    public static void validarBloqueioDeProduto() {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Enquanto existir bloqueio de produtos para o fornecedor 333, não será possível realizar compra imediata. ", "&Ok");
    }

    public static void prepararFixacao(final String codigoFornecedor, final String derivacao, final String numeroContrato, final String filtrarOrdemCompra) {
        SistemaSenior.abrirTela(Form439FIXComponentNames.FR_NOME);
        SistemaSenior.teclar(2, Tecla.ENTER);
        SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_PRODUTO, "CEV0001", Tecla.TAB);
        if (derivacao != null) {
            SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_DERIVACAO, derivacao, Tecla.TAB);
        }
        if (numeroContrato != null) {
            SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_CONTRATO, numeroContrato, Tecla.TAB);
        }
        if (filtrarOrdemCompra != null) {
            SistemaSenior.clicar(Form439FIXComponentNames.BT_SELECAO);
            SistemaSenior.preencherCampo(Form439SFXComponentNames.FD_OC, filtrarOrdemCompra, Tecla.TAB);
            SistemaSenior.clicar(Form439SFXComponentNames.BT_OK);
        }
        SistemaSenior.clicar(Form439FIXComponentNames.BT_MOSTRAR);
    }

    public static void gerarNotaFiscalViaTicket(final boolean validarMensagemBloqueio) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435MDTComponentNames.GD_TICKET, "Placa", "CEN-0011");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435MDTComponentNames.GD_TICKET, "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.clicar(Form435MDTComponentNames.BT_GERAR_NOTA);
        if (validarMensagemBloqueio) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Enquanto existir bloqueio de produtos para o fornecedor 333, não será possível realizar compra imediata. ", "&Ok");
            SistemaSenior.clicar(Form435MDTComponentNames.BT_ENTRADA_BALANCA);

            SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Fornecedor Part.", "333");
            SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "% Dep.", "100", "Trans. Dep.", "90410", "% Comp. Imed.", "0,00");

            SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        } else {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
            MetodosComuns.fecharTela(Form435MDTComponentNames.FR_NOME);
        }
    }

    public final String separadorValores() {
        final String separador = ",";
        return separador;
    }

    public void parametrizarBloqueioProduto(final String situacao, final String descricaoBloqueio1, final String descricaoBloqueio2, final int codigoFornecedor1, final int codigoFornecedor2) {
        SistemaSeniorComTransacao.executarSQL("update e435blo set sitblo='" + situacao + "' where desblo in ('" + descricaoBloqueio1 + "','" + descricaoBloqueio2 + "') and codfor in (" + codigoFornecedor1 + ", " + codigoFornecedor2 + ")");
    }
    


    public static void prepararFixacaoViaOrdemDeCompra(final String codigoFornecedor, final String numeroOrdemDeCompra) {
        SistemaSenior.abrirTela(Form439FIXComponentNames.FR_NOME);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form439FIXComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.clicar(Form439FIXComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form439SFXComponentNames.FD_OC, numeroOrdemDeCompra, Tecla.TAB);
        SistemaSenior.clicar(Form439SFXComponentNames.BT_OK);
        SistemaSenior.clicar(Form439FIXComponentNames.BT_MOSTRAR);
    }

    public static void validarExclusaoTituloFixacao(final String numeroTitulo, final String numeroFixacao) {
        SistemaSenior.abrirTela(Form502CCPComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form502CCPComponentNames.FD_VENCIMENTO_FIM, "00/00/0000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form502CCPComponentNames.FD_TITULO, numeroTitulo, Tecla.TAB);

        SistemaSenior.conferirCaixaMensagem("*", "Registro com valor (\"" + numeroTitulo + "\") não existe. Selecione outro", "Ok");
        SistemaSenior.clicar(Form502CCPComponentNames.BT_CANCELAR);
        SistemaSenior.preencherCampo(Form502CCPComponentNames.FD_VENCIMENTO_FIM, "00/00/0000", Tecla.TAB);
        SistemaSenior.clicar(Form502CCPComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form502SCPComponentNames.FD_N_FIXACAO, numeroFixacao, Tecla.TAB);
        SistemaSenior.clicar(Form502SCPComponentNames.BT_OK);
        SistemaSenior.clicar(Form502CCPComponentNames.BT_MOSTRAR);

        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Nenhum Título Selecionado !", "&Ok");
        MetodosComuns.fecharTela(Form502CCPComponentNames.FR_NOME);
    }

    public void desativarRateioTransacao(final String formaRateio, final String codigoEmpresa, final String codigoTransacao) {
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=" + formaRateio + " where codemp=" + codigoEmpresa + " and codtns='" + codigoTransacao + "'");
    }

    public void processarNotasFiscaisEletronicas() {
        SistemaSenior.clicar(Form140CANComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form140CANComponentNames.BT_PROCESSAR);
    }

    public void conferirMensagemGeracaoXML() {
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração do arquivo de Nota Eletrônica?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "O arquivo do documento eletrônico foi gerado com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form140CANComponentNames.FR_NOME);
    }

    public void filtrarNotasFiscaisSaida(final String serieNF, final String nrInicialNF, final String nrFinalNF) {
        SistemaSenior.abrirTela(Form140CANComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_SERIE, serieNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_INICIAIS, nrInicialNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form140CANComponentNames.FD_NOTAS_FISCAIS_FINAIS, nrFinalNF, Tecla.TAB);
    }

    public void parametrizarRotinaNotaProdutor(final double precoUnitarioProduto, final boolean inserirParametros) {
        if (inserirParametros) {
            SistemaSeniorComTransacao.executarSQL("update e070fil set cprsnp='NFE' where codemp=170 and codfil=1");
            SistemaSeniorComTransacao.executarSQL("update e070ven set intnfe='6' where codemp=170 and codfil=1");
            SistemaSeniorComTransacao.executarSQL("update e031moe set qtddec=5 where codmoe='100'");
            SistemaSeniorComTransacao.executarSQL("insert into e031imh (CODMOE, DATMOE, HORCOT, VLRCOT, VLRPRE, USUGER, DATGER, HORGER, USUATU, DATATU, HORATU) values (100, ?, ?, ?, ?, 10, ?, 720, 0, ?, 0)", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, Integer.toString(MetodosComuns.retornarHoraAtual())), SistemaSenior.parametroSql(TipoParametroSQL.STRING, Double.toString(precoUnitarioProduto)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, Double.toString(precoUnitarioProduto)), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, "31/12/1900"));
            SistemaSeniorComTransacao.executarSQL("update e070emp set indimh='S' where codemp=170");
            SistemaSeniorComTransacao.executarSQL("insert into e070fam (CODEMP, CODFIL, CODFAM, MOEOSC, PEROSC, USUGER, DATGER, HORGER, USUATU, DATATU, HORATU, PREMIN) values (170, 1, 'CEVADA', '100', 30, 67, ?, 608, 0, ?, 0, 0.3)", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, "31/12/1900"));
        } else {
            SistemaSeniorComTransacao.executarSQL("update e070fil set cprsnp='UNI' where codemp=170 and codfil=1");
            SistemaSeniorComTransacao.executarSQL("update e070ven set intnfe='0' where codemp=170 and codfil=1");
            SistemaSeniorComTransacao.executarSQL("update e031moe set qtddec=4 where codmoe='100'");
            SistemaSeniorComTransacao.executarSQL("delete from e031imh where codmoe=100 and datmoe=?", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
            SistemaSeniorComTransacao.executarSQL("update e070emp set indimh=' ' where codemp=170");
        }
    }

    public void validarDiferencaValorNotaProdutor(final String diferencaApurada) {
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Quando o campo \"Valor Nota Produtor\" for informado na tela de seleção (F439SFX): A diferença aceitável entre \"Valor Nota Produtor\" e \"Valor a Fixar\" é R$ 1,00, valor da diferença apurada: " + diferencaApurada + "", "&Ok");
    }

    public static void processarFixacao() {
        SistemaSenior.clicar(Form439FIXComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Não existem modelos de relatório cadastrados e ativos ou modelos pré-definidos ligados ao identificador de rotina \"F439FIX\" ", "OK");
        MetodosComuns.fecharTela(Form439FIXComponentNames.FR_NOME);
    }

    public void excluirFixacao(boolean fecharTela) {
        SistemaSenior.clicar(Form439FIXComponentNames.BT_EXCLUIR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirmar exclusão da fixação? ", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Fixação excluída com sucesso!", SystemMessageButtons.BT_OK);
        if (fecharTela) {
            MetodosComuns.fecharTela(Form439FIXComponentNames.FR_NOME);
        }
    }

    public void filtrarOrdemDeCompra(final String numeroOrdemDeCompra) {
        SistemaSenior.clicar(Form439FIXComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form439SFXComponentNames.FD_OC, numeroOrdemDeCompra, Tecla.TAB);
        SistemaSenior.clicar(Form439SFXComponentNames.BT_OK);
    }
    

    public void validarMensagemConsistencia(final String mensagemConsistencia, final boolean fecharTela) {
        SistemaSenior.clicar(Form250TCTComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, mensagemConsistencia, "&Ok");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form250TCTComponentNames.FR_NOME);
        }
    }

    public void prepararCabecalhoTransferenciaCotaCapital(final String transacaoOrigem, final String cooperadoDestino) {
        SistemaSenior.abrirTela(Form250TCTComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_COOPERADO_ORIGEM, "323", Tecla.TAB);
        SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_TRANSACAO_ORIGEM, transacaoOrigem, Tecla.TAB);
        SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_COOPERADO_DESTINO, cooperadoDestino, Tecla.TAB);
    }

    public void realizarTransferenciaCotaCapital(final boolean validarCampoTransacaoDestino, final String qtdeCotaTransferir, final String observacaoTransferencia) {
        if (validarCampoTransacaoDestino) {
            SistemaSenior.conferirEstadoComponente(Form250TCTComponentNames.FD_TRANSACAO_DESTINO, EstadoComponente.DESABILITADO);
            SistemaSenior.conferirCampo(Form250TCTComponentNames.FD_TRANSACAO_DESTINO, "92220");
        } else {
            SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_TRANSACAO_DESTINO, "92220", Tecla.TAB);
        }
        SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_QTDE_COTA_A_TRANSFERIR, qtdeCotaTransferir, Tecla.TAB);
        if (observacaoTransferencia != null) {
            SistemaSenior.preencherCampo(Form250TCTComponentNames.FD_OBS_TRANSFERENCIA, observacaoTransferencia, Tecla.TAB);
        }
    }

    public void cadastrarValoresCotaCapital(final boolean cadastrar) {
        if (cadastrar) {
            SistemaSeniorComTransacao.executarSQL("insert into e250vct (ideuni,codemp,vlrctc,datvgc,datger,horger,usuger) values (200,170,100,?,?,900,3)", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        } else {
            SistemaSeniorComTransacao.executarSQL("delete from e250vct where ideuni=200 and codemp=170 and vlrctc=100 and horger=900 and usuger=3 and datvgc=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        }
    }
    

    public void prepararMovimentacaoCotaCapitalContaPagar() {
        SistemaSenior.abrirTela(Form250CTC_CPComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form250CTC_CPComponentNames.FD_COOPERADO, "323", Tecla.TAB);
        SistemaSenior.preencherCampo(Form250CTC_CPComponentNames.FD_TRANSACAO, "92250", Tecla.TAB);
        SistemaSenior.preencherCampo(Form250CTC_CPComponentNames.FD_QTDE_COTA, "50", Tecla.TAB);
        SistemaSenior.clicar(Form250CTC_CPComponentNames.BT_INCLUIR);
    }

    public void prepararMovimentacaoCotaCapitalContaReceber(final String cooperado, final String transacao, final String quantidadeCota) {
        SistemaSenior.abrirTela(Form250CTC_CRComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form250CTC_CRComponentNames.FD_COOPERADO, cooperado, Tecla.TAB);
        SistemaSenior.preencherCampo(Form250CTC_CRComponentNames.FD_TRANSACAO, transacao, Tecla.TAB);
        SistemaSenior.preencherCampo(Form250CTC_CRComponentNames.FD_QTDE_COTA, quantidadeCota, Tecla.TAB);
        SistemaSenior.clicar(Form250CTC_CRComponentNames.BT_INCLUIR);
    }

    public void validarLinhasGrade(final String nomeCampo, final String valorCampo) {
        for (int i = 0; i < 4; i++) {
            SistemaSenior.conferirLinhaGrade(Form250CTC_CRComponentNames.GD_GERENCIAMENTO_DE_COTA_CAPITAL, i, nomeCampo, valorCampo);
        }
    }

    public void validarEstadoCampoGrid(final boolean contasPagar, final String nomeCampo, final EstadoCelula estadoCampo, final boolean campoHabilitado) {
        if (contasPagar) {
            SistemaSenior.conferirEstadoCelula(Form250CTC_CPComponentNames.GD_GERENCIAMENTO_DE_COTA_CAPITAL, 0, nomeCampo, estadoCampo);
        } else {
            SistemaSenior.conferirEstadoCelula(Form250CTC_CRComponentNames.GD_GERENCIAMENTO_DE_COTA_CAPITAL, 0, nomeCampo, estadoCampo);
        }
        if (campoHabilitado) {
            SistemaSenior.teclar(Tecla.ESC);
        }
    }

    public void validarMensagemConsistencia(final String mensagem) {
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, mensagem, "&Ok");
    }

    public void informarParcelasCotaCapital(final String numeroParcela, final String tipoTitulo, final String formaPagamento) {
        SistemaSenior.posicionarLinhaGradePorValor(Form250CTC_CRComponentNames.GD_PARCELAS_COTA_CAPITAL, "N. Parcela", numeroParcela);
        SistemaSenior.preencherLinhaCorrenteGrade(Form250CTC_CRComponentNames.GD_PARCELAS_COTA_CAPITAL, SistemaSenior.criarGabaritoMensagens(SystemMessageTitles.MS_CONFIRMACAO, "Deseja aplicar o tipo da parcela para os demais lançamentos?", "&Não", SystemMessageTitles.MS_CONFIRMACAO, "Deseja aplicar a forma de pagamento para as demais parcelas?", "&Não"), "Tipo de título", tipoTitulo, "Forma Pgto.", formaPagamento);
    }

    public void processarMovimentoCotaCapital() {
        SistemaSenior.clicar(Form250CTC_CRComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
        MetodosComuns.fecharTela(Form250CTC_CRComponentNames.FR_NOME);
    }

    public void informarValorParcelas() {
        SistemaSenior.posicionarLinhaGradePorValor(Form250CTC_CRComponentNames.GD_PARCELAS_COTA_CAPITAL, "N. Parcela", "1");
        SistemaSenior.preencherLinhaCorrenteGrade(Form250CTC_CRComponentNames.GD_PARCELAS_COTA_CAPITAL, "Tipo de título", "01", "Forma Pgto.", "1");
    }
    

    public void gerarNota() {
        SistemaSenior.clicar(Form445PRCComponentNames.BT_GERARNOTA);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração da nota fiscal?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "foi gerada com sucesso.", "OK");
        MetodosComuns.fecharTela(Form445PRCComponentNames.FR_NOME);
    }

    /**
     * @param codigoFilial Filial do Crédito do Produtor.
     * @param codigoFornecedor Código do Fornecedor para quem será gerado o crédito.
     * @param seqProcesso Sequência de processo gerado ou a gerar.
     * @param numProcesso Número do Processo, campo opcional caso ainda não exista.
     * @param tipProcesso Tipo de produto a ser utilizado e serve como filtro para as notas para o crédito no processo.
     * @author Grebbo.Alex
     */
    public static void preencherCreditoProcesso(String codigoFilial, String codigoFornecedor, String seqProcesso, String numProcesso, String tipProcesso) {
        SistemaSenior.abrirTela(Form445PRCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_FILIAL, codigoFilial, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_SEQ_PROCESSO, seqProcesso, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_NUMERO_DO_PROCESSO, numProcesso, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_TIPO_DE_CREDITO_ICMS, tipProcesso, Tecla.TAB);
    }

    public static void carregarProcesso(String codigoFilial, String codigoFornecedor, String seqProcesso) {
        SistemaSenior.abrirTela(Form445PRCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_FILIAL, codigoFilial, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form445PRCComponentNames.FD_SEQ_PROCESSO, seqProcesso, Tecla.TAB);
    }

    public static void calcular(boolean fecharTela) {
        SistemaSenior.clicar(Form445PRCComponentNames.BT_CALCULAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o cálculo do crédito?", "Sim");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form445PRCComponentNames.FR_NOME);
        }
    }

    public static void processar() {
        SistemaSenior.clicar(Form445PRCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Advertência", "Dados do processo salvos com sucesso!", "OK");
    }

    public static void gerarParcela(boolean fecharTela) {
        SistemaSenior.clicar(Form445PRCComponentNames.BT_GERAPARCELA);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma a geração das parcelas?", "Sim");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form445PRCComponentNames.FR_NOME);
        }
    }

    public void incluirItemCredito(boolean tipoNotaFiscal, String numeroNotaFiscal, String emissao) {
        if (tipoNotaFiscal) {
            SistemaSenior.preencherNovaLinhaGrade(Form445PRCComponentNames.GD_CREDITO, "Tipo NF", "I", "Nº NF", numeroNotaFiscal, "Série NF", "UNI", "Seq.", "1");
        } else {
            SistemaSenior.preencherNovaLinhaGrade(Form445PRCComponentNames.GD_CREDITO, "Tipo NF", "E", "Tipo Fornecedor", "J", "CNPJ/CPF", "28.193.156/0001-55", "Fornecedor NF Ext.", "PETROBRAS DISTRIBUIDORA S/A", "Estado NF Ext.", "SC", "Série NF Ext.", "1", "Nº NF Ext.", numeroNotaFiscal, "Seq. Item NF Externa", "1", "Nat. Oper. Prod.", "5101", "Emissão", emissao, "Produto", "D001", "Qtd. Rec. NF", "100000", "U.M. Nota Fiscal", "L", "Vlr. Líquido", "200000.99", "Base ICMS", "200000.99", "Vlr. ICMS", "34000.99");
        }
    }

    public void incluirItemProducao(boolean tipoNotaFiscal, String numeroNotaFiscal, String emissao, String qtdeRecebidaNF, String valorICMS) {
        SistemaSenior.selecionarGuia(Form445PRCComponentNames.TB_AGRUPADOR, "Produção");
        if (tipoNotaFiscal) {
            SistemaSenior.preencherNovaLinhaGrade(Form445PRCComponentNames.GD_PRODUCAO, "Tipo NF", "I", "Nº NF", numeroNotaFiscal, "Série NF", "UNI", "Seq.", "1");
        } else {
            SistemaSenior.preencherNovaLinhaGrade(Form445PRCComponentNames.GD_PRODUCAO, "Tipo NF", "E", "Tipo Fornecedor", "J", "CNPJ/CPF", "77118521000172", "Nº NF Ext.", numeroNotaFiscal, "Série NF Ext.", "121", "Seq. Item NF Externa", "1", "Emissão", emissao, "Produto", "CEV0001", "Deriv.", "0001", "Qtde. Rec. NF", qtdeRecebidaNF, "U.M. Nota Fiscal", "KG", "Vlr. Líquido", qtdeRecebidaNF, "Base ICMS", qtdeRecebidaNF, "Vlr. ICMS", valorICMS);
        }
    }
    

    public void realizarManutencaoTicket(final String numeroTicket, final String fornecedor) {
        SistemaSenior.abrirTela(Form435MDTComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435MDTComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435MDTComponentNames.FD_N_TICKET, numeroTicket, Tecla.TAB);
        SistemaSenior.clicar(Form435MDTComponentNames.BT_MOSTRAR);
    }

    public void selecionarTicket(final String numeroTicket) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435MDTComponentNames.GD_TICKET, "Num. Ticket", numeroTicket);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435MDTComponentNames.GD_TICKET, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public void processarTicket() {
        SistemaSenior.clicar(Form435MDTComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
        MetodosComuns.fecharTela(Form435MDTComponentNames.FR_NOME);
    }

    public void selecionarVeiculoParaRecebimento(final String placaVeiculo) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435MDTComponentNames.GD_TICKET, "Placa", placaVeiculo, "Sel.", CaixaAtribuicao.DESMARCADO);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435MDTComponentNames.GD_TICKET, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public static void carregarRecebimentos(final String dataRecebimento) {
        SistemaSenior.abrirTela(Form435MDTComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435MDTComponentNames.FD_DATA_INICIO, dataRecebimento, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435MDTComponentNames.FD_DATA_FIM, dataRecebimento, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435MDTComponentNames.FD_SITUACAO, "5", Tecla.TAB);
        SistemaSenior.clicar(Form435MDTComponentNames.BT_MOSTRAR);
    }

    public static void gerarNotaViaTicket() {
        SistemaSenior.clicar(Form435MDTComponentNames.BT_GERAR_NOTA);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso! Ordens de Compra processadas", "&Ok");
        MetodosComuns.fecharTela(Form435MDTComponentNames.FR_NOME);
    }

    public static void retornarParametrosTaxa() {
        SistemaSeniorComTransacao.executarSQL("update e113ptx set tipfco='A', aplptx=3, coditx=1, tipptx=1, vlrptx=2, perptx=0, indgtt='N', tptptx=' ', tnsptx=' ', indgnf='S', codser='5957' where codemp=170 and codpro='CEV0002'");
    }
    

    public void processarContrato() {
        SistemaSenior.clicar(Form460CXOComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "&Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "&Ok");
        MetodosComuns.fecharTela(Form460CXOComponentNames.FR_NOME);
    }

    public void vincularDesvincularContrato(final String opcao, final String fornecedor, final String numeroContrato) {
        SistemaSenior.abrirTela(Form460CXOComponentNames.FR_NOME);
        SistemaSenior.selecionarItem(Form460CXOComponentNames.CB_OPCAO, opcao);
        SistemaSenior.preencherCampo(Form460CXOComponentNames.FD_FORNECEDOR, fornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460CXOComponentNames.FD_PRODUTO, "CEV0002", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460CXOComponentNames.FD_DERIVACAO, "0002", Tecla.TAB);
        SistemaSenior.preencherCampo(Form460CXOComponentNames.FD_CONTRATO, numeroContrato, Tecla.TAB);
        SistemaSenior.preencherCampo(Form460CXOComponentNames.FD_SEQUENCIA, "1", Tecla.TAB);
        SistemaSenior.clicar(Form460CXOComponentNames.BT_MOSTRAR);
    }

    public void informacoesAdicionaisProdutor() {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90402", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SAFRA, "2014A", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSGENIA, "RR-2", Tecla.TAB);
    }

    public void realizarEntradaMercadoria(final String produto) {
        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "AAA0014", Tecla.TAB);
        SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "João", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, "329", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_ORIGEM_MERCADORIA, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DEPOSITO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PRODUTO, produto, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DERIVACAO, "C000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90403", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SAFRA, "2014A", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSGENIA, "RR-2", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
    }

    public void parametrizarTransacoesSemRateio(final int formaRateio, final boolean rateioPreDefinido) {
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=" + formaRateio + " where codemp=321 and codtns in ('90501','90514')");
        if (rateioPreDefinido) {
            SistemaSeniorComTransacao.executarSQL("insert into e001rat (codemp, codtns, seqrat, tiprsc, crirat, numprj, codfpj, ctafin, ctared, percta, codccu, perrat) values (321, '90514', 22, 'C', 5, 0, 0, 3300, 3300, 100, 2210, 100)");
        } else {
            SistemaSeniorComTransacao.executarSQL("delete from e001rat where codemp=321 and codtns='90514' and seqrat=22");
        }
    }

    public void parametrizarSituacaoContrato(final String situacaoContrato, final String numeroContrato) {
        SistemaSeniorComTransacao.executarSQL("update e460ctr set sitctr='" + situacaoContrato + "' where codemp=321 and codfil in (1,2) and codfor = 329 and numctr not in ('" + numeroContrato + "')");
    }

    public void informarPesoSaidaMercadoria(final String pesoSaida, final String numeroContrato) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoSaida, Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PROTOCOLO, "123", Tecla.TAB);
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Nº Interno", numeroContrato);
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CONTRATOS, "Sel.", CaixaAtribuicao.MARCADO);
    }

    public static void desativarRateioTransacoes() {
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=2 where codemp=170 and codtns in ('90408','90409','90410','90220')");
    }

    public void alterarParametroPesoBalanca(final String alterarPesoBalanca) {
        SistemaSenior.abrirTela(Form099UCPComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form099UCPComponentNames.FD_USUARIO, "10", Tecla.TAB);
        SistemaSenior.preencherCampo(Form099UCPComponentNames.FD_ALTERAR_PESO_QUANTIDADE_INFORMADA_CONTROLE, alterarPesoBalanca, Tecla.TAB);
        SistemaSenior.clicar(Form099UCPComponentNames.BT_ALTERAR);
        MetodosComuns.fecharTela(Form099UCPComponentNames.FR_NOME);
    }

    public void realizarSaidaMercadoriaComNotaFiscalEntrada(final String pesoSaida) {
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "AAA9999", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, pesoSaida, Tecla.TAB);
    }

    public void processarEntradaSaidaBalancaMovimentacaoEstoque(final boolean origemMercadoriaPorto, final String tipoQuantidade, final String efetuarMovimentacao) {
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        if (origemMercadoriaPorto) {
            SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Quantidade final da pesagem é " + tipoQuantidade + " que a quantidade da nota fiscal de entrada, executar o movimento de estoque?", efetuarMovimentacao);
        }
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nenhum modelo de relatório ligado a esta tela, ou todo(s) o(s) modelos ligados estão inativos.", "OK");
        MetodosComuns.fecharTela(Form435CCCComponentNames.FR_NOME);
    }

    public void alterarPercentuaisApuracao(final String percentualApuradoUmidade, final String percentualApuradoPHInicial) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CLASSIFICACAO, "Item Classificação", "1", "Descrição(Item Classificação)", "Umidade");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CLASSIFICACAO, "% Apurado", percentualApuradoUmidade);

        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CLASSIFICACAO, "Item Classificação", "5", "Descrição(Item Classificação)", "PH Inicial");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CLASSIFICACAO, "% Apurado", percentualApuradoPHInicial);
    }

    public void validarPHFinal(final String percentualApurado) {
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CLASSIFICACAO, "Descrição(Item Classificação)", "PH Final", "% Apurado", percentualApurado);
    }
    
    public static void incluirProdutoNotaFiscalSugestaoEnquadramentoIPI(final String situacaoTributariaIPI, final String codigoEnquadramentoSugerido) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        if (situacaoTributariaIPI != null) {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "ENQIPI", "Derivação", "1", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100");
        } else {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "ENQIPI", "Derivação", "1", "Qtde. Recebida", "1", "Preço Unitário", "100");
        }
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 0, "Cód. de enquad.", codigoEnquadramentoSugerido);
    }

    public static void incluirServicoNotaFiscalSugestaoEnquadramentoIPI(final String situacaoTributariaIPI, final String codigoEnquadramentoSugerido) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        if (situacaoTributariaIPI != null) {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "SENQIPI", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100");
        } else {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "SENQIPI", "Qtde. Recebida", "1", "Preço Unitário", "100");
        }
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_SERVICOS, 0, "Cód. de enquad.", codigoEnquadramentoSugerido);
    }

    public static void incluirProdutoNotaFiscalConsistenciaSugestaoEnquadramentoIPI(final String situacaoTributariaIPI, final String codigoEnquadramentoSugerido) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, SistemaSenior.criarGabaritoMensagens("Aviso", "Necessário informar um Enquadramento para a Situação tributária de IPI selecionada.", "&Ok"), "Produto", "ENQIPI", "Derivação", "1", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100");
        SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "ENQIPI", "Derivação", "1", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100", "Cód. de enquad.", codigoEnquadramentoSugerido);
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 0, "Cód. de enquad.", codigoEnquadramentoSugerido);
    }

    public static void incluirServicoNotaFiscalConsistenciaSugestaoEnquadramentoIPI(final String situacaoTributariaIPI, final String codigoEnquadramentoSugerido) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, SistemaSenior.criarGabaritoMensagens("Aviso", "Necessário informar um Enquadramento para a Situação tributária de IPI selecionada.", "&Ok"), "Serviço", "SENQIPI", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100");
        SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "SENQIPI", "Sit. Trib. IPI", situacaoTributariaIPI, "Qtde. Recebida", "1", "Preço Unitário", "100", "Cód. de enquad.", codigoEnquadramentoSugerido);
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_SERVICOS, 0, "Cód. de enquad.", codigoEnquadramentoSugerido);
    }

    public static void prepararNotaFiscalSugestaoEnquadramentoIPI(final String tipoNota) {
        MetodosComunsNucleo.selecionarEmpresaFilial(175, 1);
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNota, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "175", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "4545", Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);
    }

    public static void inserirNFeComConsistenciaChave(final String tipoNF, final String numeroNF) {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "170", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, numeroNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "NFE", Tecla.TAB);

        if (tipoNF.equals("4") || tipoNF.equals("5") || tipoNF.equals("9")) {
            SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TRANS_PRODUTO, "112", Tecla.TAB);
            SistemaSenior.teclar(4, Tecla.TAB);
        } else {
            SistemaSenior.teclar(9, Tecla.TAB);
        }

        validarChaveNFeCTe(tipoNF);
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CANCELAR);

        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "170", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, numeroNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "NFE", Tecla.TAB);

        if (tipoNF.equals("4") || tipoNF.equals("5") || tipoNF.equals("9")) {
            SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TRANS_PRODUTO, "112", Tecla.TAB);
        }

        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "CON001", "Derivação", "10L", "Qtde. Recebida", "1", "Preço Unitário", "10");

        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
        validarChaveNFeCTe(tipoNF);

        finalizarNotaFiscalSugestaoCodigoEnquadramentoIPI();
    }

    public static void validarChaveNFeCTe(final String tipoNF) {
        if (tipoNF.equals("8")) {
            SistemaSenior.conferirCaixaMensagem("Advertência", "Chave CT-e não correspondente com os dados informados!", "&Ok");
        } else {
            SistemaSenior.conferirCaixaMensagem("Advertência", "Chave NF-e não correspondente com os dados informados!", "&Ok");
        }
    }

    public static void inserirNFeSemConsistenciaChave(final String tipoNF) {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNF, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "170", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        if (tipoNF.equals("10")) {
            SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TRANS_PRODUTO, "112", Tecla.TAB);
        }
        SistemaSenior.teclar(9, Tecla.TAB);
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");

        if (tipoNF.equals("6")) {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Transação", "131", "Produto", "CON001", "Derivação", "10L", "Sit. Trib. PIS", "99", "Sit. Trib. COFINS", "99", "Qtde. Recebida", "1", "Preço Unitário", "10");
        } else {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "CON001", "Derivação", "10L", "Sit. Trib. PIS", "99", "Sit. Trib. COFINS", "99", "Qtde. Recebida", "1", "Preço Unitário", "10");
        }

        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
        if (tipoNF.equals("7")) {
            SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja chamar rotina de valorização do produto?", "&Não");
        }
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Fechada com Sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }

    public static void parametrizarConsistenciaChaveNFe(final String consisteChave) {
        MetodosComunsNucleo.selecionarEmpresaFilial(10, 1); //Necessário, campo fica em memória
        SistemaSeniorComTransacao.executarSQL("update e070cpr set conchv='" + consisteChave + "' where codemp=170 and codfil=1");
    }

    public static void desativarRateioTransacao(final String transacao) {
        SistemaSeniorComTransacao.executarSQL("update e001tns set forrat=0 where codemp=170 and codtns='" + transacao + "'");
    }

    public static void fecharNFEntrada(final boolean fecharTela) {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma Fechamento da Nota Fiscal?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nota Fiscal Fechada com Sucesso.", "Ok");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
        }
    }

    public void retornarParametrizacoesTransacoes() {
        SistemaSeniorComTransacao.executarSQL("update E001TCP set codenq=null where codtns in ('112','1949S') and codemp=175");
        SistemaSeniorComTransacao.executarSQL("update E075PRO set codenq='0' where codpro='ENQIPI'");
        SistemaSeniorComTransacao.executarSQL("update E022CLF set codenq=null where codclf='001'");
    }

    public static void finalizarNotaFiscalSugestaoCodigoEnquadramentoIPI() {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CANCELAR);
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }

    public void inserirNotaFiscalViaOrdemCompra() {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "170", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "875", Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);

        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Filial O.C.", "1", "Ordem de Compra ", "3", "Seq.", "1");
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Filial O.C.", "1", "Ordem de Compra ", "3", "Seq.", "1");
    }
    
    public static void prepararNFEntradaViaContratoComercial() {
        SistemaSenior.abrirTela(Form461GNCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_TIPO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_SERIE, "NFE", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_CODIGO_SERIE, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_SUBSERIE_LEGAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_NUMERO, "200001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_FORNECEDOR, "61", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_EMISSAO, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_ENTRADA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_COMPETENCIA, SistemaSenior.calcularData("MM/yyyy", "m+0"), Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_TIPO_CONTRATO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form461GNCComponentNames.FD_CONTRATO, "2", Tecla.TAB);

        SistemaSenior.clicar(Form461GNCComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form461GNCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Nota Fiscal de Entrada gerada com sucesso. Deseja fazer alguma alteração?", "Não");
        MetodosComuns.fecharTela(Form461GNCComponentNames.FR_NOME);
    }
    
    public void filtrarNotaFiscal(final String codigoFornecedor, final String codigoSerie, final String filtroDataNotaFiscal, final String numeroNotaFiscal) {
        SistemaSenior.abrirTela(Form441CNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_SERIE, codigoSerie, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_NOTA_FISCAL, numeroNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_ENTRADA_DE, filtroDataNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_ENTRADA_ATE, filtroDataNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_EMISSAO_DE, filtroDataNotaFiscal, Tecla.TAB);
        SistemaSenior.preencherCampo(Form441CNEComponentNames.FD_EMISSAO_ATE, filtroDataNotaFiscal, Tecla.TAB);
        SistemaSenior.clicar(Form441CNEComponentNames.BT_MOSTRAR);
    }
    

    public void validarDeposito(final String sequenciaNotaFiscal, final String linhaGrid, final String deposito) {
        SistemaSenior.posicionarLinhaGradePorValor(Form000INEComponentNames.GD_PRODUTOS, "Seq. Nfe", sequenciaNotaFiscal);
        SistemaSenior.conferirColunaGrade(Form000INEComponentNames.GD_PRODUTOS, "Depós.", linhaGrid, deposito);

        SistemaSenior.posicionarLinhaGradePorValor(Form000INEComponentNames.GD_NOTAS_FISCAIS_DE_ENTRADA, "Número", "2");
        SistemaSenior.preencherLinhaCorrenteGrade(Form000INEComponentNames.GD_NOTAS_FISCAIS_DE_ENTRADA, "Selecionado", CaixaAtribuicao.MARCADO, "Série", "NFE", "Tipo Nfe", "1", "Trans. Prod.", "1101");
        SistemaSenior.clicar(Form000INEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja processar as notas fiscais", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Notas fiscais de entrada processadas com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form000INEComponentNames.FR_NOME);
    }

    public void processarNota() {
        SistemaSenior.clicar(Form000INEComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form000INEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Deseja processar as notas fiscais", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Notas fiscais de entrada processadas com sucesso.", "Ok");
        MetodosComuns.fecharTela(Form000INEComponentNames.FR_NOME);
    }

    public void validarCondicaoPagamentoParcela(final String condicaoPagamento, final String numeroOC, final String sequenciaOC) {
        SistemaSenior.conferirLinhaGrade(Form000INEComponentNames.GD_NOTAS_FISCAIS_DE_ENTRADA, 0, "Cond. Pgto.", condicaoPagamento, "Cond. Pgto. Sugestão", condicaoPagamento);
        SistemaSenior.conferirLinhaGrade(Form000INEComponentNames.GD_PRODUTOS, 0, "Seq. Nfe", "1", "Filial OC", "1", "Ordem de compra", numeroOC, "Seq. OC", sequenciaOC);
        SistemaSenior.selecionarGuia(Form000INEComponentNames.TS_AGRUPADOR_ABAS, "Parcelas(V)");
    }

    public void conferirEstadoCampoProduto(final String nomeCampo, final EstadoCelula validarEstadoCampo) {
        SistemaSenior.conferirEstadoCelula(Form000INEComponentNames.GD_PRODUTOS, 0, nomeCampo, validarEstadoCampo);
    }

    public void conferirEstadoCampoServico(final String nomeCampo, final EstadoCelula validarEstadoCampo) {
        SistemaSenior.conferirEstadoCelula(Form000INEComponentNames.GD_SERVICOS, 0, nomeCampo, validarEstadoCampo);
    }

    public void informarNotaFiscalConsultar(final String notaFiscalInicio, final String notaFiscalFinal) {
        SistemaSenior.abrirTela(Form000INEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form000INEComponentNames.FD_NOTAS_FISCAIS_INICIO, notaFiscalInicio);
        SistemaSenior.preencherCampo(Form000INEComponentNames.FD_NOTAS_FISCAIS_FIM, notaFiscalFinal);
        SistemaSenior.clicar(Form000INEComponentNames.BT_MOSTRAR);
    }
    
    public String numeroNotaDevolucaoGerada() {
        final String[][] numeroNotaGerada = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numnfc from e440NFC where codemp=170 and codfil=1 and numnfc=(select max(numnfc) from e440nfc where codemp=170 and codfil=1 and codsnf='UNI' and codfor=41) and codsnf='UNI' and codfor=41 and tipnfe=3 and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroNotaDevolucaoGerada = numeroNotaGerada[0][0];
        return numeroNotaDevolucaoGerada;
    }

    public void validaEstadoCampoBotaoValores(final EstadoComponente validaEstadoCampo) {
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_SOMA_FRETE_LIQUIDO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_FRETE, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_PER_ICMS_FRETE, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_ICMS_FRETE, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_SEGURO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_EMBALAGENS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_ENCARGOS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_OUTROS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_ARREDONDAMENTO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_FRETE_DESTACADO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_OUT_DESP_DESTACADO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_FRETE_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_SEGURO_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_OUT_DESP_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440VALComponentNames.FD_VLR_AFRMM, validaEstadoCampo);
    }

    public void validaEstadoCampoBotaoProdutos(final EstadoComponente validaEstadoCampo) {
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_FRETE, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_SEGURO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_EMBALAGENS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_ENCARGOS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_OUTRAS_DESPESAS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_ARREDONDAMENTO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_FRETE_DESTACADO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_OUT_DESPESAS_DESTACADO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_FRETE_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_SEGUROS_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_OUT_DESPESAS_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_AFRMM, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_BASE_IPI, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CIPComponentNames.FD_VLR_IPI, validaEstadoCampo);
    }

    public void validaEstadoCampoBotaoServicos(final EstadoComponente validaEstadoCampo) {
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_ENCARGOS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_OUTRAS_DESPESAS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_ARREDONDAMENTO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_OUT_DESPESAS_DESTACADO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_OUT_DESPESAS_IMPORTACAO, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_BASE_ISS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VALOR_ISS, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_ICMS_SIMPLES_NACIONAL_BASE, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_ICMS_SIMPLES_NACIONAL_VALOR, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_BASE_IPI, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_VLR_IPI, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_PIS_RECUPERAR, validaEstadoCampo);
        SistemaSenior.conferirEstadoComponente(Form440CISComponentNames.FD_COFINS_RECUPERAR, validaEstadoCampo);
    }

    public void gerarNotaEletronicaCTE(final String chaveNota, final String tipoNota, final String serie) {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, tipoNota, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "171", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "1093", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, serie, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_EMISSAO, "14/04/2016", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_CHAVE_NFE, chaveNota, Tecla.TAB);
    }

    public void gerarNotaFiscalCideValores() {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_VALORES);
        SistemaSenior.preencherCampo(Form440VALComponentNames.FD_VALOR_ARREDONDAMENTO, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440VALComponentNames.FD_OUTROS_VALOR, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440VALComponentNames.FD_ENCARGOS_VALOR, "2", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440VALComponentNames.FD_VALOR_OUTROS_DESPESAS_DESTACADO, "2", Tecla.TAB);
        SistemaSenior.clicar(Form440VALComponentNames.BT_PROCESSAR);
    }

    public void gerarNotaFiscalEntradaCide(final boolean adicionarValores) {
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);
        prepararNotaFiscalCIDE();
        if (adicionarValores) {
            gerarNotaFiscalCideValores();
        }
    }

    public void prepararNotaFiscalCIDE() {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "30", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "101010", Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TRANS_SERVICO, "400", Tecla.TAB);
    }

    public void adicionarItemServicoCIDE(final boolean conferirCampoCIDE, final boolean adicionaItemServico) {
        if (adicionaItemServico) {
            SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "Diversos", "Qtde. Recebida", "1", "Preço Unitário", "100");
            if (conferirCampoCIDE) {
                SistemaSenior.conferirEstadoCelula(Form440GNEComponentNames.GD_SERVICOS, 0, "% CIDE Tec.", EstadoCelula.DESABILITADA);
                SistemaSenior.conferirEstadoCelula(Form440GNEComponentNames.GD_SERVICOS, 0, "Vlr. CIDE Tec.", EstadoCelula.DESABILITADA);
            }
        }
    }

    public void adicionarItemProdutoCIDE(final boolean conferirCampoCIDE, final boolean adicionaItemProduto) {
        if (adicionaItemProduto) {
            SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "PISCOFINSIMP", "Derivação", "1", "Qtde. Recebida", "1", "Preço Unitário", "100,00");
            if (conferirCampoCIDE) {
                SistemaSenior.conferirEstadoCelula(Form440GNEComponentNames.GD_PRODUTOS, 0, "% CIDE Tec.", EstadoCelula.DESABILITADA);
                SistemaSenior.conferirEstadoCelula(Form440GNEComponentNames.GD_PRODUTOS, 0, "Vlr. CIDE Tec.", EstadoCelula.DESABILITADA);
            }
        }
    }

    public void prepararNotaFiscalPisCofinsImportacao(final boolean consisteTabelaPrecoPisCofins) {
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "30", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "101010", Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);

        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        if (consisteTabelaPrecoPisCofins) {
            SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, SistemaSenior.criarGabaritoMensagens("Aviso", "Erro na busca da tabela de preço \"PISI\" na empresa \"170\": Tabela de Preço (PISI) não cadastrada, inativa, fora do período ou produto - derivação (PISCOFINSIMP - 1) não cadastrado na tabela de preço.", "&Ok"), SistemaSenior.criarSequenciaTeclas(Tecla.SETA_CIMA), "Produto", "PISCOFINSIMP", "Derivação", "1", "Qtde. Recebida", "1", "Preço Unitário", "2,83");
        } else {
            SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "PISCOFINSIMP", "Derivação", "1", "Qtde. Recebida", "1", "Preço Unitário", "2,83");
        }
    }

    public void excluirNotaFiscal() {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_EXCLUIR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Excluir Nota Fiscal de Entrada? ", "&Sim");
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }

    public void gerarNotaFiscalCalculoDifal(final String diferencialAliquota, final String diferencialAliquotaPse, final String diferencialAliquotaReducao, final boolean tipoRegime) {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "221", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "8787", Tecla.ENTER);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "01", Tecla.TAB);
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Transação", "2101", "Produto", "1101", "Qtde. Recebida", "1", "Preço Unitário", "1000");
        SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, "Transação", "2101", "Produto", "1102", "Qtde. Recebida", "1", "Preço Unitário", "1000");
        SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, "Transação", "2101", "Produto", "1103", "Qtde. Recebida", "1", "Preço Unitário", "1000");

        processarNotaFiscal(false);
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 0, "Produto", "1101");
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CALCULOS_PRODUTOS);

        if (tipoRegime) {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_BASE, "1.000,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_VALOR, "120,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquota);
        } else {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_BASE, "1.000,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_VALOR, "100,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquota);
        }

        SistemaSenior.clicar(Form440CIPComponentNames.BT_SAIR);

        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 1, "Produto", "1102");
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CALCULOS_PRODUTOS);

        if (tipoRegime) {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_BASE, "1.000,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_VALOR, "120,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquotaPse);
        } else {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_BASE, "1.000,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_VALOR, "100,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquotaPse);
        }

        SistemaSenior.clicar(Form440CIPComponentNames.BT_SAIR);

        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 2, "Produto", "1103");
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CALCULOS_PRODUTOS);

        if (tipoRegime) {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_BASE, "583,30");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_VALOR, "70,00");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquotaReducao);
        } else {
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_BASE, "583,30");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_ICMS_SIMPLES_NACIONAL_VALOR, "58,33");
            SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_DIFERENCIAL_ALIQUOTA, diferencialAliquotaReducao);
        }

        SistemaSenior.clicar(Form440CIPComponentNames.BT_SAIR);
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CANCELAR);
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }

    public void gerarNotaFiscalSugestaoIRRF(final String codigoFornecedor, final String percentualIRRF, final String baseIRRF, final String valorIRRF) {
        MetodosComunsNucleo.selecionarEmpresaFilial(200, 1);
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "4646", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "NFE", Tecla.TAB);

        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Produtos");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_PRODUTOS, "Produto", "IRRF", "Qtde. Recebida", "1", "Preço Unitário", "1000");
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_PRODUTOS, 0, "% IRRF", percentualIRRF);
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CALCULOS_PRODUTOS);
        SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_VLR_BIR, baseIRRF);
        SistemaSenior.conferirCampo(Form440CIPComponentNames.FD_VLR_IRF, valorIRRF);
        SistemaSenior.clicar(Form440CIPComponentNames.BT_SAIR);

        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "SIRRF", "Qtde. Recebida", "1", "Preço Unitário", "1000");
        SistemaSenior.conferirLinhaGrade(Form440GNEComponentNames.GD_SERVICOS, 0, "% IRRF", percentualIRRF);
        SistemaSenior.clicar(Form440GNEComponentNames.BT_CALCULOS_SERVICOS);
        SistemaSenior.conferirCampo(Form440CISComponentNames.FD_VLR_BIR, baseIRRF);
        SistemaSenior.conferirCampo(Form440CISComponentNames.FD_VLR_IRF, valorIRRF);
        SistemaSenior.clicar(Form440CISComponentNames.BT_SAIR);

        processarNotaFiscal(true);
    }

    public void processarNotaFiscal(final boolean fecharTela) {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Nota Fiscal de Entrada processada com sucesso!", "&Ok");
        if (fecharTela) {
            MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
        }
    }

    public void parametrizarCalculaINSS(final String valorMaximoRetencao, final String valorMinimoINSS) {
        SistemaSeniorComTransacao.executarSQL("update e070cpr set vlrmri='" + valorMaximoRetencao + "' where codemp=175 and codfil=1");
        SistemaSeniorComTransacao.executarSQL("update e001tcp set cprlin=" + valorMinimoINSS + " where codemp=175 and codtns='1949S'");
    }

    public void fecharNotaFiscalComValorINSS(final boolean temINSS, final String valorINSS) {
        SistemaSenior.clicar(Form440GNEComponentNames.BT_FECHAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Fechamento da Nota Fiscal?", "&Sim");
        if (temINSS) {
            SistemaSenior.conferirCaixaMensagem("Aviso", "O Valor do INSS Retido da nota fiscal de entrada " + valorINSS + " é menor/igual ao limite indicado na transação 10,00, portanto será zerado.", "&Ok");
        }
        SistemaSenior.conferirCaixaMensagem("Aviso", "Nota Fiscal Fechada com Sucesso.", "&Ok");
        MetodosComuns.fecharTela(Form440GNEComponentNames.FR_NOME);
    }

    public void prepararNotaFiscalComValorINSS() {
        SistemaSenior.abrirTela(Form440GNEComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_TIPO, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_FORNECEDOR, "177", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_NOTA_FISCAL, "321987", Tecla.TAB);
        SistemaSenior.preencherCampo(Form440GNEComponentNames.FD_SERIE, "CPR", Tecla.TAB);
    }

    public void incluirServicoINSS(final String quantidadeRecebida) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        SistemaSenior.preencherLinhaCorrenteGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "INS001", "Qtde. Recebida", quantidadeRecebida);
    }

    public void incluirServicoINSS(final String quantidadeRecebida, final String precoUnitario) {
        SistemaSenior.selecionarGuia(Form440GNEComponentNames.TB_ITENS, "Serviços");
        for (int i = 0; i < 2; i++) {
            SistemaSenior.preencherNovaLinhaGrade(Form440GNEComponentNames.GD_SERVICOS, "Serviço", "INS001", "Qtde. Recebida", quantidadeRecebida, "Preço Unitário", precoUnitario);
        }
    }

}
