package senior.erp.componentNames.suprimentos;

/**
 *
 */
public final class Form420GOCComponentNames {

    /**
     * Campo <b>Safra</b>
     */
    public static final String FD_SAFRA = "ECodSaf";

    /**
     * Campo <b>Cotação Fechada</b>
     */
    public static final String FD_COTACAO_FECHADA = "EFecMoe";

    /**
     * Campo <b>Valor Cotação</b>
     */
    public static final String FD_VALOR_COTACAO = "ECotMoe";

    /**
     * Campo <b>Data Cotação</b>
     */
    public static final String FD_DATA_COTACAO = "EDatMoe";

    /**
     * Campo <b>Moeda</b>
     */
    public static final String FD_MOEDA = "ECodMoe";

    /**
     * Campo <b>Carteira</b>
     */
    public static final String FD_CARTEIRA = "ECodCrt";

    /**
     * Campo <b>Origem Mercadoria</b>
     */
    public static final String FD_ORIGEM_MERCADORIA = "ESeqOrm";

    /**
     * Campo <b>Tem Parcelas Especiais</b>
     */
    public static final String FD_TEM_PARCELAS_ESPECIAIS = "ETemPar";

    /**
     * Campo <b>Redespacho</b>
     */
    public static final String FD_REDESPACHO = "ECodRed";

    /**
     * Campo <b>Transportadora</b>
     */
    public static final String FD_TRANSPORTADORA = "ECodTra";

    /**
     * Campo <b>Pedido Fornecedor</b>
     */
    public static final String FD_PEDIDO_FORNECEDOR = "EPedFor";

    /**
     * Campo <b>Contrato do Fornecedor</b>
     */
    public static final String FD_CONTRATO_DO_FORNECEDOR = "ESeqCto";

    /**
     * Campo <b>Condição de Pagamento</b>
     */
    public static final String FD_CONDICAO_DE_PAGAMENTO = "ECodCpg";

    /**
     * Grid <b>Serviços</b>
     */
    public static final String GD_SERVICOS = "GridIso";

    /**
     * Grid <b>Produtos</b>
     */
    public static final String GD_PRODUTOS = "GridIpo";

    /**
     * Aba <b>Guias</b>
     */
    public static final String TB_GUIAS = "Guias";

    /**
     * Botão <b>Fechar</b>
     */
    public static final String BT_FECHAR = "Fechar";

    /**
     * Botão <b>Sair</b>
     */
    public static final String BT_SAIR = "Sair";

    /**
     * Formulário <b>Ordem de Compra Agrupada</b>
     */
    public static final String FR_NOME = "F420GOC_SCOC";

    /**
     * Campo <b>Fornecedor</b>
     */
    public static final String FD_FORNECEDOR = "ECodFor";

    /**
     * Campo <b>Transação Serviços</b>
     */
    public static final String FD_TRANSACAO_SERVICOS = "ETnsSer";
    
    /**
     * Botão <b>Processar</b>
     */
    public static final String BT_PROCESSAR = "Processar";

    /**
     * Campo <b>Transação Produtos</b>
     */
    public static final String FD_TRANSACAO_PRODUTOS = "ETnsPro";

    /**
     * Botão <b>Recalcular</b>
     */
    public static final String BT_RECALCULAR = "Recalcular";

    /**
     * Botão <b>Valores (W)</b>
     */
    public static final String BT_VALORES = "btValores";

    /**
     * Campo <b>Nº Ordem Compra</b>
     */
    public static final String FD_N_ORDEM_COMPRA = "ENumOCp";

    /**
     * Campo <b>Situação</b>
     */
    public static final String FD_SITUACAO = "ESitOcp";

    /**
     * Botão <b>Excluir</b>
     */
    public static final String BT_EXLUIR = "Excluir";

    /**
     * Botão <b>Cancelar</b>
     */
    public static final String BT_CANCELAR = "Cancelar";
    
    /**
     * Botão <b>Aprovar</b>
     */
    public static final String BT_APROVAR = "Aprovar";
    
    /**
     * Utilizado nos cenários 20,21,22 do TCSCOC0001_OrdemCompraAgrupada
     * @author felipe.ravizza
     *
     */
    public enum tipoValidacao {
        VALIDADETABELA,NORMAL,DESVINCULARTABELA;
    }   
    
    private Form420GOCComponentNames() {
        //Components Names
    }

}