package senior.erp.utils;

import com.senior.framework.testes.EstadoComponente;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.SystemMessageButtons;
import senior.erp.SystemMessageTitles;
import senior.erp.componentNames.cadastros.Form000RPFComponentNames;
import senior.erp.componentNames.cadastros.Form070EFNComponentNames;
import senior.erp.componentNames.financas.Form301BCDComponentNames;
import senior.erp.componentNames.financas.Form301BMAComponentNames;
import senior.erp.componentNames.financas.Form301BMDComponentNames;
import senior.erp.componentNames.financas.Form301CXBComponentNames;
import senior.erp.componentNames.financas.Form301SELComponentNames;
import senior.erp.componentNames.financas.Form302CCRComponentNames;
import senior.erp.componentNames.financas.Form303HISComponentNames;
import senior.erp.componentNames.financas.Form310RTBComponentNames;
import senior.erp.componentNames.financas.Form320DTCComponentNames;
import senior.erp.componentNames.financas.Form501BAAComponentNames;
import senior.erp.componentNames.financas.Form501LOTComponentNames;
import senior.erp.componentNames.financas.Form501SBAComponentNames;
import senior.erp.componentNames.financas.Form501SBLComponentNames;
import senior.erp.componentNames.financas.Form502CTIComponentNames;
import senior.erp.componentNames.financas.Form502SCIComponentNames;
import senior.erp.componentNames.financas.Form600ELCComponentNames;
import senior.erp.componentNames.financas.Form610PFLComponentNames;

public class FacadeFinancas {

    /**
     * Obtém o número do título gerado no contas a receber, com o intuito de utilizar este dado na validação dos
     * testes.
     * Por padrão, é forçado um data de geração = a data atual para somente recuperar o título a receber gerado pelo
     * teste.
     * Por padrão, será forçado recuperar o número como String, não sendo necessário fazer conversões de Integer para
     * String quando este valor for utilizado no select de validação.
     * 
     * @param empresa
     * @param filial
     * @param codigoCliente
     * @return
     */
    public static String obterNumeroTituloContasReceberGerado(String empresa, String filial, String codigoCliente) {
        String[][] obterNumeroTituloContasReceberGerado = SistemaSeniorComTransacao.recuperaValoresBaseDados("select numtit from e301tcr where codemp=? and codfil=? and codcli=? and datger=?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, empresa), SistemaSenior.parametroSql(TipoParametroSQL.STRING, filial), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoCliente), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        final String numeroTituloContasReceberGerado = obterNumeroTituloContasReceberGerado[0][0];
        return numeroTituloContasReceberGerado;
    }
    
    public static void filtrarTitulosBaixasPorCreditos(String tituloAproveitar, String tituloBaixar) {
        SistemaSenior.clicar(Form501BAAComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form501SBAComponentNames.TITULOS_A_APROVEITAR_TITULO, tituloAproveitar, Tecla.TAB);
        SistemaSenior.teclarCombinacao(Tecla.ALT, Tecla.B);
        SistemaSenior.preencherCampo(Form501SBAComponentNames.TITULOS_A_BAIXAR_TITULO, tituloBaixar, Tecla.TAB);
        SistemaSenior.clicar(Form501SBAComponentNames.BT_OK);
        SistemaSenior.clicar(Form501BAAComponentNames.BT_MOSTRAR);
    }

    public static void processarBaixasPorCreditos() {
        SistemaSenior.clicar(Form501BAAComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Baixas processadas com sucesso!", "OK");
        MetodosComuns.fecharTela(Form501BAAComponentNames.FR_NOME);
    }

    public static void efetuarBaixasPorCreditos(String dataBaixa, String codigoFornecedor, String numeroTitulo, String sequencia) {
        SistemaSenior.abrirTela(Form501LOTComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form501LOTComponentNames.FD_DATA_BAIXA, dataBaixa, Tecla.TAB);
        SistemaSenior.preencherCampo(Form501LOTComponentNames.FD_TRANSACAO, "90540", Tecla.TAB);
        SistemaSenior.preencherCampo(Form501LOTComponentNames.FD_CONTA_INTERNA, "BRASIL C/C", Tecla.TAB);
        SistemaSenior.clicar(Form501LOTComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form501SBLComponentNames.FD_FORNECEDOR, codigoFornecedor);
        SistemaSenior.preencherCampo(Form501SBLComponentNames.FD_TITULO, numeroTitulo);
        SistemaSenior.clicar(Form501SBLComponentNames.BT_OK);
        SistemaSenior.clicar(Form501LOTComponentNames.BT_MOSTRAR);
        
        SistemaSenior.preencherLinhaCorrenteGrade(Form501LOTComponentNames.GD_TITULO, "Seq.", sequencia);
    }
    
    public static void efetuarBaixasPorCreditosPessoaJuridica(String numeroTitulo, String sequencia) {
        SistemaSenior.abrirTela(Form501LOTComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form501LOTComponentNames.FD_DATA_BAIXA, "12/11/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form501LOTComponentNames.FD_CONTA_INTERNA, "BRASIL C/C", Tecla.TAB);
        SistemaSenior.clicar(Form501LOTComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form501SBLComponentNames.FD_TITULO, numeroTitulo);
        SistemaSenior.clicar(Form501SBLComponentNames.BT_OK);
        SistemaSenior.clicar(Form501LOTComponentNames.BT_MOSTRAR);
        
        SistemaSenior.preencherLinhaCorrenteGrade(Form501LOTComponentNames.GD_TITULO, "Seq.", sequencia);
    }
    
    public static void processarBaixasPorCreditosF501LOT(String validarCreditoFornecedor) {
        SistemaSenior.clicar(Form501LOTComponentNames.BT_PROCESSAR);
        if (validarCreditoFornecedor != null) {
            SistemaSenior.conferirCaixaMensagem("Confirmação", "Fornecedor 11000 possui saldo de crédito maior que zero. Deseja continuar?", "&Sim");
        }
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Aviso", "Baixas processadas com sucesso", "OK");
        MetodosComuns.fecharTela(Form501LOTComponentNames.FR_NOME);
    }

    public static void prepararBaixasPorCreditos(String dataBaixa, String codigoFornecedor) {
        SistemaSenior.abrirTela(Form501BAAComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form501BAAComponentNames.FD_DATA_BAIXA, dataBaixa, Tecla.TAB);
        SistemaSenior.preencherCampo(Form501BAAComponentNames.FD_TRANSACAO_BAIXA_TITULOS_CREDITO, "90544", Tecla.TAB);
        SistemaSenior.preencherCampo(Form501BAAComponentNames.FD_TRANSACAO_TITULOS_A_BAIXAR, "90543", Tecla.TAB);
        SistemaSenior.preencherCampo(Form501BAAComponentNames.FD_FORNECEDOR, codigoFornecedor, Tecla.TAB);
    }
    
    public static void estornarLancamentoTituloTesouraria() {
        SistemaSenior.abrirTela(Form600ELCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_CONTA_INTERNA, "BRASIL C/C", Tecla.TAB);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_PERIODO_INICIAL, "12/11/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_PERIODO_FINAL, "12/11/2015", Tecla.TAB);
        
        SistemaSenior.selecionarItem(Form600ELCComponentNames.CB_MODALIDADE, "Data Liberação");
        
        SistemaSenior.clicar(Form600ELCComponentNames.BT_MOSTRAR);
        SistemaSenior.clicar(Form600ELCComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form600ELCComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma estorno do(s) Movimento(s) ?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "&Ok");
        
        MetodosComuns.fecharTela(Form600ELCComponentNames.FR_NOME);
    }
    
    public static void consultarTitulosImpostos(String desmarcarTitulosImpostoDeRenda) {
        SistemaSenior.abrirTela(Form502CTIComponentNames.FR_NOME);
        SistemaSenior.selecionarItem(Form502CTIComponentNames.CB_MOSTRAR_TITULOS, "Todos");
        SistemaSenior.preencherCampo(Form502CTIComponentNames.FD_FORNECEDOR, "10019", Tecla.TAB);
        if (desmarcarTitulosImpostoDeRenda != null) {
            SistemaSenior.desmarcarCaixaAtribuicao(Form502CTIComponentNames.CB_IR);
        }
        SistemaSenior.clicar(Form502CTIComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form502SCIComponentNames.FD_EMISSAO_INICIO, "01/11/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form502SCIComponentNames.FD_EMISSAO_FIM, "30/11/2015", Tecla.TAB);
        SistemaSenior.clicar(Form502SCIComponentNames.BT_OK);
        SistemaSenior.clicar(Form502CTIComponentNames.BT_MOSTRAR);
    }
    
    public void distribuirRateio() {
        SistemaSenior.conferirCaixaMensagem("Advertência", "Rateio Incompleto!", "Ok");
        SistemaSenior.preencherLinhaCorrenteGrade("SwwDBGridContas", "Financeira", "3220");
        SistemaSenior.preencherLinhaCorrenteGrade("SwwDBGridCustos", "C.Custo", "2210");
        SistemaSenior.clicar("Ok");
    }

    public void inserirFormaPagamento(final boolean cadastrar) {
        if (cadastrar) {
            SistemaSeniorComTransacao.executarSQL("INSERT into e066fpg (codemp, codfpg, desfpg, abrfpg, venmfp, venfpl, tipfpg, fvefpg, fvedec, indexp, datpal, horpal, codecf, codope, tipcar, exiacr, datatu, horatu, usuatu, datger, horger, usuger, sitfpg, acrfin, vendsc, cprdsc, perdsc, percom, redcom, gerctr, codcli, codban, bnddeb, bndcre, codtpt, tpttef, tptsub, codfin, tptcpr, coninf, ideuni) VALUES (151, 1, '1', '1', 0, 'S', 1, ' ', ' ', 0, ?, 0, ' ', 0, ' ', 'N', ?, 681, 23, ?, 650, 23, 'A', 0, 0, 0, 0, 0, 0, ' ', 0, ' ', ' ', ' ', ' ', ' ', ' ', 0, ' ', ' ', 5894)", SistemaSenior.parametroSql(TipoParametroSQL.DATE, "31/12/1900"), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        } else {
            SistemaSeniorComTransacao.executarSQL("DELETE from e066fpg where codemp=151 and codfpg='1'");
        }
    }
    
    public static void processaCobrancaEscritural(final String portador, final String nomeArquivo, final String[] titulos) {
        SistemaSenior.abrirTela(Form310RTBComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form310RTBComponentNames.FD_PORTADOR, portador, Tecla.TAB);
        SistemaSenior.preencherCampo(Form310RTBComponentNames.FD_NOME_ARQUIVO, MetodosComuns.obterCaminhoMassa(true) + "\\" + nomeArquivo, Tecla.TAB);
        SistemaSenior.clicar(Form310RTBComponentNames.BT_MOSTRAR);

        for (int i = 0; i < titulos.length; i++) {
            SistemaSenior.posicionarLinhaGradePorValor(Form310RTBComponentNames.GD_RETORNO, "Seu nº", titulos[i]);
        }
        SistemaSenior.clicar(Form310RTBComponentNames.BT_PROCESSAR);

        SistemaSenior.clicar(Form301BMAComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma Baixa de Títulos?", "Sim");

        SistemaSenior.clicar(Form301CXBComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma lançamento Tesouraria?", "Sim");

        SistemaSenior.clicar(Form000RPFComponentNames.BT_OK);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Baixas processadas com sucesso!", "Ok");
        MetodosComuns.fecharTela(Form310RTBComponentNames.FR_NOME);
    }

    public static void consultaSituacaoTituloReceber(final String dataInicial, final String dataFinal, final String[] titulos, final String vlrSituacao) {
        SistemaSenior.abrirTela(Form302CCRComponentNames.FR_NOME);

        SistemaSenior.preencherCampo(Form302CCRComponentNames.FD_VENCIMENTO_INICIO, dataInicial, Tecla.TAB);
        SistemaSenior.preencherCampo(Form302CCRComponentNames.FD_VENCIMENTO_FINAL, dataFinal, Tecla.TAB);
        SistemaSenior.clicar(Form302CCRComponentNames.BT_MOSTRAR);

        for (int i = 0; i < titulos.length; i++) {
            SistemaSenior.posicionarLinhaGradePorValor(Form302CCRComponentNames.GD_TITULOS, "Título", titulos[i], "Sit.", vlrSituacao);
        }

        MetodosComuns.fecharTela(Form302CCRComponentNames.FR_NOME);
    }
    
    public void cadastrarBemAnaliseDeCredito(boolean camposOpcionais, boolean bemFinanciado) {
        SistemaSenior.abrirTela(Form303HISComponentNames.FR_NOME);
        SistemaSenior.selecionarGuia(Form303HISComponentNames.TS_AGRUPADOR_DAS_ABAS, "Bens");
        SistemaSenior.preencherCampo(Form303HISComponentNames.FD_CLIENTE, "333", Tecla.TAB);
        SistemaSenior.preencherCampo(Form303HISComponentNames.FD_DESCRICAO_DO_BEM, "Teste Teste Teste Teste Teste Teste Teste Teste Teste TesteX", Tecla.TAB);

        if (camposOpcionais) {
            SistemaSenior.teclar(2, Tecla.TAB);
            if (bemFinanciado) {
                SistemaSenior.preencherCampo(Form303HISComponentNames.FD_BEM_ESTA_FINANCIADO, "S", Tecla.TAB);
            } else {
                SistemaSenior.preencherCampo(Form303HISComponentNames.FD_BEM_ESTA_FINANCIADO, "N");
            }
        } else {
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_DATA_DE_AQUISICAO, SistemaSenior.dataAtual(), Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_VALOR_DO_BEM, "9999999999999,99", Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_BEM_ESTA_FINANCIADO, "S", Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_SALDO_DEVEDOR_FINAN_DO_BEM, "9999999999999,99", Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_VALOR_PARCELA_SALDO_DEVEDOR, "9999999999999,99", Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_NUM_PAR_RESTANTE, "9999", Tecla.TAB);
            SistemaSenior.preencherCampo(Form303HISComponentNames.FD_FINAN_RES_ALIENACAO_BEM, "FINANCEIRA FINANCEIRA FINANCEIRA FINANCEIRA FINANCEIRA FXXXX");
        }
        SistemaSenior.clicar(Form303HISComponentNames.BT_INSERIR);
        MetodosComuns.fecharTela(Form303HISComponentNames.FR_NOME);
    }
    
    public static void efetuarBaixaTituloCR(String numeroTitulo, String valorLiquido) {
        SistemaSenior.abrirTela(Form301BMAComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form301BMAComponentNames.FD_DATA_BAIXA, "17/11/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form301BMAComponentNames.FD_CONTA_INTERNA, "BRASIL C/C", Tecla.TAB);
        SistemaSenior.clicar(Form301BMAComponentNames.BT_SELECAO);
        SistemaSenior.preencherCampo(Form301SELComponentNames.FD_TITULO, numeroTitulo, Tecla.TAB);
        SistemaSenior.clicar(Form301SELComponentNames.BT_OK);
        SistemaSenior.clicar(Form301BMAComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherCampo(Form301BMAComponentNames.FD_LIQUIDO, valorLiquido, Tecla.TAB);
        SistemaSenior.clicar(Form301BMAComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form301BMAComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "Sim");
        SistemaSenior.clicar(Form301CXBComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Lançamento Tesouraria?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Baixas processadas com sucesso!", "Ok");
        MetodosComuns.fecharTela(Form301BMAComponentNames.FR_NOME);
    }
    
    public static void processarEstornoTitulosCR() {
        SistemaSenior.clicar(Form600ELCComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form600ELCComponentNames.BT_PROCESSAR);

        SistemaSenior.conferirCaixaMensagem("Confirmação", "Ao estornar movimentos da Tesouraria com origem no C.Receber é necessário estornar todos os movimentos relacionados. Todos foram marcados, continuar?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma estorno do(s) Movimento(s) ?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "Ok");
        
        MetodosComuns.fecharTela(Form600ELCComponentNames.FR_NOME);
    }

    public static void filtrarEstornoTitulosCR() {
        SistemaSenior.abrirTela(Form600ELCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_CONTA_INTERNA, "BRASIL C/C", Tecla.TAB);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_PERIODO_INICIAL, "17/11/2015", Tecla.TAB);
        SistemaSenior.preencherCampo(Form600ELCComponentNames.FD_PERIODO_FINAL, "17/11/2015", Tecla.TAB);
        
        SistemaSenior.selecionarItem(Form600ELCComponentNames.CB_MODALIDADE, "Data Liberação");
        SistemaSenior.clicar(Form600ELCComponentNames.BT_MOSTRAR);
    }
    
    public void distribuirTitulosAssessoria(final boolean distribuiMesmaAssesoria) {
        if (distribuiMesmaAssesoria) {
            SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", "COBFIN-01");
            SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, SistemaSenior.criarGabaritoMensagens("Confirmação", "Este título pertence ao cliente 153 - EXCLUSIVO FINANÇAS - FINANCEIRA, todos os títulos relacionados a esse cliente que estejam em cobrança serão alterados para a assessoria selecionada. Deseja continuar?", "&Sim"), "Assessoria", "1");
        } else {
            SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", "COBFIN-01");
            SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, "Assessoria", "1");
            SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", "COBFIN-02");
            SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, "Assessoria", "1");
            SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", "COBFIN-03");
            SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, "Assessoria", "1");
        }
    }

    public void prepararDistribuicaoTitulosAssessoria() {
        SistemaSenior.abrirTela(Form320DTCComponentNames.FR_NOME);
        SistemaSenior.selecionarItem(Form320DTCComponentNames.CB_OPCAO, "Distribuir");
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.selecionarItem(Form320DTCComponentNames.RG_DISTRIBUICAO, "Manual");
        SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_DATA_BASE, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_TIPO, "01", Tecla.TAB);
        SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_CLIENTE, "153", Tecla.TAB);
        SistemaSenior.clicar(Form320DTCComponentNames.BT_MOSTRAR);
    }

    /**
     * Aplicando update na tabela deste parâmetro (caso seja executado todo o TCFRCO0002), o testScenario0002 falha,
     * pois o campo fica em memória. Isso ocorre independentemente se for trocado o usuário do sistema e ou
     * empresa/filial.
     * Para atualizar os campos em memória, é necessário que o método abra e feche a tela F320DTC para que os campos
     * sejam atualizados.
     * Por default, o campo DisToa da tabela E070EFN é "S" - Sim.
     * 
     * @param atualizarParametroDistribuicaoTitulosAssCobranca
     * @param valorParametro
     * @author marcel.fortunato
     */
    public void parametrizarDistribuicaoTitulosAssCobranca(final boolean atualizarParametroDistribuicaoTitulosAssCobranca, final String valorParametro) {
        if (atualizarParametroDistribuicaoTitulosAssCobranca) {
            if (valorParametro != "N") {
                SistemaSenior.abrirTela(Form070EFNComponentNames.FR_NOME);
                SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_EMPRESA, "151", Tecla.TAB);
                SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_DIST_TIT_CLIENTE_MESMO_OPERADOR_ASSESSORIA, valorParametro);
                SistemaSenior.clicar(Form070EFNComponentNames.BT_ALTERAR);
                MetodosComuns.fecharTela(Form070EFNComponentNames.FR_NOME);
            } else {
                SistemaSeniorComTransacao.executarSQL("update e070efn set distoa='" + valorParametro + "' where codemp=151");
                SistemaSenior.abrirTela(Form320DTCComponentNames.FR_NOME);
                MetodosComuns.fecharTela(Form320DTCComponentNames.FR_NOME);
            }
        } else {
            SistemaSeniorComTransacao.executarSQL("update e070efn set distoa='" + valorParametro + "' where codemp=151");
        }
    }
    

    public static void confereProjeto(final int linha, final String... projeto) {
        SistemaSenior.conferirLinhaGrade(Form000RPFComponentNames.GD_PROJETOS, linha, projeto);
    }

    static void confereTela000RPF(int linhaProjeto, String projeto, String financeiro, int linhaCentroCusto, String centroCusto) {
        confereProjeto(linhaProjeto, "Projeto", projeto, "Financeiro", financeiro);
        confereCentroCusto(linhaCentroCusto, "C.Custo", centroCusto);
        SistemaSenior.clicar(Form000RPFComponentNames.BT_OK);
    }

    public static void confereCentroCusto(final int linha, final String... centroCusto) {
        SistemaSenior.conferirLinhaGrade(Form000RPFComponentNames.GD_CENTRO_CUSTO, linha, centroCusto);
    }

    public static void preencheProjeto(final String... projeto) {
        SistemaSenior.preencherNovaLinhaGrade(Form000RPFComponentNames.GD_PROJETOS, projeto);
    }

    public static void preencheCentroCusto(final String... centroCusto) {
        SistemaSenior.preencherNovaLinhaGrade(Form000RPFComponentNames.GD_CENTRO_CUSTO, centroCusto);
    }

    public static void marcarTitulosBaixar301BCD() {
        SistemaSenior.abrirTela(Form301BCDComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form301BCDComponentNames.FD_DATA_BAIXA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo(Form301BCDComponentNames.FD_CLIENTE, "999", Tecla.TAB);
        SistemaSenior.clicar(Form301BCDComponentNames.BT_MOSTRAR);
        SistemaSenior.conferirColunaGrade(Form301BCDComponentNames.GD_TITULOS_BAIXAR, "Título", "0-2", "TROCO001", "TROCO002", "TROCO003");
        SistemaSenior.clicar(Form301BCDComponentNames.BT_MARCAR);
    }

    public static void marcarTitulosBaixar301BMD() {
        SistemaSenior.abrirTela(Form301BMDComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form301BMDComponentNames.FD_DATA_BAIXA, SistemaSenior.dataAtual(), Tecla.TAB);
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo(Form301BMDComponentNames.FD_CLIENTE, "999", Tecla.TAB);
        SistemaSenior.clicar(Form301BMDComponentNames.BT_MOSTRAR);
        SistemaSenior.conferirColunaGrade(Form301BMDComponentNames.GD_TITULOS_BAIXAR, "Título", "0-2", "TROCO001", "TROCO002", "TROCO003");
        SistemaSenior.clicar(Form301BMDComponentNames.BT_MARCAR);
    }

    public static void preencheCreditos(final String coluna, final int linha, final String valor, final Tecla... teclas) {
        SistemaSenior.selecionarGuia(Form301BMDComponentNames.TABULADOR, "Créditos(2)");
        SistemaSenior.preencherCampoGrade(Form301BMDComponentNames.GD_CREDITOS, coluna, linha, valor, teclas);
    }

    public static void preencheGridContas301BCD(final String... parametrosGridContas) {
        SistemaSenior.selecionarGuia(Form301BCDComponentNames.TABULADOR, "Contas");
        SistemaSenior.preencherNovaLinhaGrade(Form301BCDComponentNames.GD_CONTAS, parametrosGridContas);
    }

    public static void preencheGridCheques(final String banco, final String agencia, final String conta, final String cheque, final String valor, final String vencimento) {
        SistemaSenior.selecionarGuia(Form301BCDComponentNames.TABULADOR, "Cheques");
        SistemaSenior.preencherNovaLinhaGrade(Form301BCDComponentNames.GD_CHEQUES, "Banco", banco, "Agência", agencia, "Conta", conta, "Cheque", cheque, "Valor", valor, "Vencimento", vencimento);
    }

    public static void preencheGerarCreditos(final String filial, final String titulo, final String tipTitulo, final String vlrOriginal) {
        SistemaSenior.selecionarGuia(Form301BCDComponentNames.TABULADOR, "Gerar Créditos ");
        SistemaSenior.preencherNovaLinhaGrade(Form301BCDComponentNames.GD_GERAR_CREDITOS, "Filial", filial, "Título", titulo, "Tipo Título", tipTitulo, "Vlr Original", vlrOriginal);
    }

    public static void preencheCompensacoes(final int linha, final String coluna, final String valor) {
        SistemaSenior.selecionarGuia(Form301BCDComponentNames.TABULADOR, "Compensações");
        SistemaSenior.preencherCampoGrade(Form301BCDComponentNames.GD_COMPENSACOES, coluna, linha, valor, Tecla.SETA_BAIXO);
    }

    public static void preencheContas301BMD(final String contaInterna, final String valor) {
        SistemaSenior.selecionarGuia(Form301BMDComponentNames.TABULADOR, "Contas");
        SistemaSenior.preencherNovaLinhaGrade(Form301BMDComponentNames.GD_CONTAS, "Conta Interna", contaInterna, "Valor", valor);
    }

    public static void preencheSubstitutos(final String filial, final String titulo, final String tipoTitulo, final String valorOriginal) {
        SistemaSenior.selecionarGuia(Form301BMDComponentNames.TABULADOR, "Substitutos");
        SistemaSenior.preencherNovaLinhaGrade(Form301BMDComponentNames.GD_SUBSTITUTOS, "Filial", filial, "Título", titulo, "Tipo Título", tipoTitulo, "Vlr Original", valorOriginal);
    }

    public static void preencheBaixaCreditos(final int linha, final String coluna, final String valor) {
        SistemaSenior.selecionarGuia(Form301BCDComponentNames.TABULADOR, "Baixar Créditos");
        SistemaSenior.preencherCampoGrade(Form301BCDComponentNames.GD_BAIXAR_CREDITOS, coluna, linha, valor, Tecla.SETA_BAIXO);
    }
    
    public void prepararDistribuicaoTitulosAssessoria(final String opcao, final String tipoDistribuicao, final String tipoTitulo, final String cliente) {
        SistemaSenior.abrirTela(Form320DTCComponentNames.FR_NOME);
        SistemaSenior.selecionarItem(Form320DTCComponentNames.CB_OPCAO, opcao);
        SistemaSenior.teclar(Tecla.TAB);

        if (opcao.equals("Distribuir")) {
            SistemaSenior.selecionarItem(Form320DTCComponentNames.RG_DISTRIBUICAO, tipoDistribuicao);
            SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_DATA_BASE, SistemaSenior.dataAtual(), Tecla.TAB);
        }

        SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_TIPO, tipoTitulo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form320DTCComponentNames.FD_CLIENTE, cliente, Tecla.TAB);
        SistemaSenior.clicar(Form320DTCComponentNames.BT_MOSTRAR);
    }

    public void distribuirTitulosAssessoria(final String distribuicao, final String[] titulos) {
        switch (distribuicao) {
            // Caso seja automática, o sistema processa sozinho ao selecionar a opção MOSTRAR
            case "Automática":
                SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Na distribuição automática os títulos serão carregados e já processados, mostrando apenas os que não puderam ser distribuídos. Confirma processamento?", SystemMessageButtons.BT_SIM);
                SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Dados processados com sucesso!", SystemMessageButtons.BT_OK);
                break;
            // Caso seja de forma sugerida, o sistema deverá informar a assessoria 1 para todos os movimentos
            case "Sugerida":
                SistemaSenior.conferirColunaGrade(Form320DTCComponentNames.GD_TITULOS, 19, "1-5", "1", "1", "1", "1", "1");
                break;
            // Caso seja manual, deve-se informar manualmente as assessorias desejadas para cada título
            case "Manual":
                for (int i = 0; i < titulos.length; i++) {
                    SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", titulos[i]);
                    SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, "Assessoria", "1");
                }
                break;
            default:
                break;
        }
    }

    public void processarDistribuicaoAssessoria() {
        SistemaSenior.clicar(Form320DTCComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form320DTCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Dados processados com sucesso!", SystemMessageButtons.BT_OK);
        MetodosComuns.fecharTela(Form320DTCComponentNames.FR_NOME);
    }

    /**
     * Para atualizar os campos em memória, é necessário que o método abra e feche a tela F320DTC para que os campos
     * sejam atualizados.
     * Por default, o campo DisToa da tabela E070EFN é "S" - Sim.
     */
    public void parametrizarDistribuicaoTitulosAssCobranca(final String valorParametro) {
        SistemaSenior.abrirTela(Form070EFNComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_EMPRESA, "151", Tecla.TAB);
        SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_DIST_TIT_CLIENTE_MESMO_OPERADOR_ASSESSORIA, valorParametro);
        SistemaSenior.clicar(Form070EFNComponentNames.BT_ALTERAR);
        SistemaSenior.fecharTela(Form070EFNComponentNames.FR_NOME);
        SistemaSenior.abrirTela(Form320DTCComponentNames.FR_NOME);
        MetodosComuns.fecharTela(Form320DTCComponentNames.FR_NOME);
    }

    public void verificaConscistenciaTrocaAssessoria() {
        SistemaSenior.abrirTela(Form070EFNComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_EMPRESA, "151", Tecla.TAB);
        SistemaSenior.preencherCampo(Form070EFNComponentNames.FD_DIST_TIT_CLIENTE_MESMO_OPERADOR_ASSESSORIA, "N");
        SistemaSenior.clicar(Form070EFNComponentNames.BT_ALTERAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "O parâmetro não pode ser alterado, pois já foi realizada alguma distribuição de títulos na empresa 151.", SystemMessageButtons.BT_OK);
        MetodosComuns.fecharTela(Form070EFNComponentNames.FR_NOME);
    }
    
    public void devolucaoTitulos() {
        SistemaSenior.clicar(Form320DTCComponentNames.BT_MARCAR);
        SistemaSenior.clicar(Form320DTCComponentNames.BT_DEVOLVERTIT);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma a devolução de todos os títulos marcados?", SystemMessageButtons.BT_SIM);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Devolução efetuada com sucesso!", SystemMessageButtons.BT_OK);
    }

    public void distribuirTitulosAssessoria(final String distribuicao, final String[] titulos, final String[] mensagem) {
        switch (distribuicao) {
            // Caso seja automática, o sistema processa sozinho ao selecionar a opção MOSTRAR
            case "Automática":
                SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Na distribuição automática os títulos serão carregados e já processados, mostrando apenas os que não puderam ser distribuídos. Confirma processamento?", SystemMessageButtons.BT_SIM);
                SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Dados processados com sucesso", SystemMessageButtons.BT_OK);
                SistemaSenior.fecharTela(Form320DTCComponentNames.FR_NOME);
                break;
            // Caso seja de forma sugerida, o sistema deverá informar a assessoria 1 para todos os movimentos
            case "Sugerida":
                SistemaSenior.conferirColunaGrade(Form320DTCComponentNames.GD_TITULOS, 2, "2-12", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
                break;
            // Caso seja manual, deve-se informar manualmente as assessorias desejadas para cada título
            case "Manual":
                for (int i = 0; i < titulos.length; i++) {
                    SistemaSenior.posicionarLinhaGradePorValor(Form320DTCComponentNames.GD_TITULOS, "Título", titulos[i]);
                    SistemaSenior.preencherLinhaCorrenteGrade(Form320DTCComponentNames.GD_TITULOS, SistemaSenior.criarGabaritoMensagens(SystemMessageTitles.MS_CONFIRMACAO, mensagem[i], SystemMessageButtons.BT_SIM), "Assessoria", "1");
                }
                break;
            default:
                break;
        }
    }

    public static void alterarControleDeContratoParaItens() {
        SistemaSeniorComTransacao.executarSQL("update e160ctr set ctrrea = 'G', ctrift='G' where ctrrea = 'I' and ctrift='I' and codemp = '300' and codfil = '1'");
    }
    
    public static void cadastrarFeriado() {
        SistemaSeniorComTransacao.executarSQL("insert into e025fer values ('01', '01', '2032', '0', '0', 'Confraternização Universal', 'N')");
    }
    
    public static void removerCadastroFeriado() {
        SistemaSeniorComTransacao.executarSQL("delete from e025fer where diafer=1 and mesfer=1 and anofer=2032");
    }
    
    public static void prepararTesouraria() {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_TESOURARIA);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUIR);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_FILIAL_CONTA_INT, "1", Tecla.TAB);
    }

    public static void prepararContasPagar(String dataPagtoComissao) {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_CONTAS_A_PAGAR);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_EFETIVOS_CP);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_PREVISTOS_CP);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_ORDEM_COMPRA_CP);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_CONTRATO_COMPRA_CP);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_COMISSOES_CP);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PAGTO, dataPagtoComissao, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_VENCIMENTO_BASE_CP, "2", Tecla.TAB);
    }
    
    public static void prepararContasPagar(String dataPagtoComissao, String motivoContratoCompra) {
        prepararContasPagar(dataPagtoComissao);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_MOT_CONT_COMPRA, motivoContratoCompra, Tecla.TAB);
    }
    
    public static void prepararContasReceber() {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_CONTAS_A_RECEBER);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_EFETIVOS_CR);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_PREVISTOS_CR);
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_PEDIDO_CR); 
        SistemaSenior.marcarCaixaAtribuicao(Form610PFLComponentNames.CB_INCLUI_CONTRATO_VENDA_CR); 
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_VENCIMENTO_BASE_CR, "2", Tecla.TAB);
    }
    
    public static void prepararContasReceber(String motivoContratoVenda) {
        prepararContasReceber();
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_MOT_CONT_VENDA, motivoContratoVenda, Tecla.TAB);
    }

    public static void processarFluxoDeCaixa() {
        SistemaSenior.clicar(Form610PFLComponentNames.BT_PROCESSAR);
        
        SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Geração do Fluxo de Caixa?", "Sim");
        SistemaSenior.conferirCaixaMensagem("Advertência", "Fluxo de Caixa gerado com sucesso!", "Ok");
    }
    
    public static void prepararFluxoDeCaixaDiario(String periodoDe, String periodoAte, String intervaloDeDias, String dataBase) {
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_EMPRESA, "300", Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO, "D", Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_DE, periodoDe, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_ATE, periodoAte, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_INTERVALO_DE_DIAS, intervaloDeDias, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_DATA_BASE, dataBase, Tecla.TAB);
    }
    
    public static void prepararFluxoDeCaixa(String periodo, String periodoDe, String periodoAte, String dataBase) {
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_EMPRESA, "300", Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_FILIAL, "1", Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO, periodo, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_DE, periodoDe, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_ATE, periodoAte, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_DATA_BASE, dataBase, Tecla.TAB);
    }
    
    public static void prepararMovimentacoesHistoricasContasPagar(String consideraTitulos, String periodoDe, String periodoAte, String percentualIncorporar) {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_MOVIMENTACOES_HISTORICAS);
        SistemaSenior.selecionarItem(Form610PFLComponentNames.CB_CONSIDERA_CP, consideraTitulos);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_DE_CP, periodoDe, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_ATE_CP, periodoAte, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERCENTUAL_INCORPORAR_CP, percentualIncorporar, Tecla.TAB);
    }
    
    public static void prepararMovimentacoesHistoricasContasPagar(String consideraTitulos) {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_MOVIMENTACOES_HISTORICAS);
        SistemaSenior.selecionarItem(Form610PFLComponentNames.CB_CONSIDERA_CP, consideraTitulos);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERIODO_DE_CP, EstadoComponente.DESABILITADO);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERIODO_ATE_CP, EstadoComponente.DESABILITADO);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERCENTUAL_INCORPORAR_CP, EstadoComponente.DESABILITADO);
        
    }

    public static void prepararMovimentacoesHistoricasContasReceber(String consideraTitulos, String periodoDe, String periodoAte, String percentualIncorporar) {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_MOVIMENTACOES_HISTORICAS);
        SistemaSenior.selecionarItem(Form610PFLComponentNames.CB_CONSIDERA_CR, consideraTitulos);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_DE_CR, periodoDe, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERIODO_ATE_CR, periodoAte, Tecla.TAB);
        SistemaSenior.preencherCampo(Form610PFLComponentNames.FD_PERCENTUAL_INCORPORAR_CR, percentualIncorporar, Tecla.TAB);
    }
    
    public static void prepararMovimentacoesHistoricasContasReceber(String consideraTitulos) {
        SistemaSenior.selecionarGuia(Form610PFLComponentNames.TB_TABULADOR, Form610PFLComponentNames.TS_MOVIMENTACOES_HISTORICAS);
        SistemaSenior.selecionarItem(Form610PFLComponentNames.CB_CONSIDERA_CR, consideraTitulos);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERIODO_DE_CR, EstadoComponente.DESABILITADO);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERIODO_ATE_CR, EstadoComponente.DESABILITADO);
        SistemaSenior.conferirEstadoComponente(Form610PFLComponentNames.FD_PERCENTUAL_INCORPORAR_CR, EstadoComponente.DESABILITADO);
    }
}
