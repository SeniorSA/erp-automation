package senior.erp.componentNames.suprimentos;

/**
 * @author rafael.pacheco
 */
public final class Form440GNEComponentNames {

    private Form440GNEComponentNames() {
        //Components Names
    }

    /**
     * Formul�rio <b>Nota Fiscal de Entrada Agrupada</b>
     */
    public static final String FR_NOME = "F440GNE_SRNF";

    /**
     * Campo <b>Fornecedor</b>
     */
    public static final String FD_FORNECEDOR = "ECodFor";

    /**
     * Campo <b>Trans Servi�o</b>
     */
    public static final String FD_TRANS_SERVICO = "ETnsSer";

    /**
     * Grid <b>Grid Produtos</b>
     */
    public static final String GD_PRODUTOS = "GridPro";

    /**
     * Grid <b>Grid Servi�os</b>
     */
    public static final String GD_SERVICOS = "GridSer";

    /**
     * Bot�o <b>Processar</b>
     */
    public static final String BT_PROCESSAR = "Processar";
    /**
     * Bot�o <b>C�lculos (X)</b>
     */
    public static final String BT_CALCULOS_PRODUTOS = "BCalculosProdutos";

    /**
     * Bot�o <b>C�lculos (X)</b>
     */
    public static final String BT_CALCULOS_SERVICOS = "BCalculosServicos";

    /**
     * Campo <b>Entrada</b>
     */
    public static final String FD_ENTRADA = "EDatEnt";

    /**
     * Campo<b>Nota Fiscal</b>
     */
    public static final String FD_NOTA_FISCAL = "ENumNfc";

    /**
     * Tabulador <b>Itens</b>
     */
    public static final String TB_ITENS = "TNItens";

    /**
     * Bot�o <b>Cancelar</b>
     */
    public static final String BT_CANCELAR = "Cancelar";

    /**
     * Campo <b>S�rie</b>
     */
    public static final String FD_SERIE = "ECodSnf";

    /**
     * Bot�o <b>Fechar</b>
     */
    public static final String BT_FECHAR = "Fechar";

    /**
     * Campo <b>Tipo</b>
     */
    public static final String FD_TIPO = "ETipNfe";

    /**
     * Campo <b>Transa��o</b>
     */
    public static final String FD_TRANS_PRODUTO = "ETnsPro";

    /**
     * Campo <b>Trans. Prod.</b>
     */
    public static final String FD_TRANS_PROD = "ETnsPro";

    /**
     * Campo <b>Origem Mercadoria</b>
     */
    public static final String FD_ORIGEM_MERCADORIA = "ESeqOrm";
    
    /**
     * Bot�o <b>Nota Sa�da</b>
     */
    public static final String BT_NOTA_SAIDA = "btNotaSaida";

    /**
     * Bot�o <b>Dist.Lote</b>
     */
    public static final String BT_DIST_LOTE = "DistLote";

    /**
     * Bot�o <b>Excluir</b>
     */
    public static final String BT_EXCLUIR = "Excluir";
    
    /**
     * Bot�o <b>Valores</b>
     */
    public static final String BT_VALORES = "BValores";

    /**
     * Campo <b>Chave NF-e</b>
     */
    public static final String FD_CHAVE_NFE = "EChvNel";

    /**
     * Campo <b>Emiss�o</b>
     */
    public static final String FD_EMISSAO = "EDatEmi";

	/**
     * Bot�o <b>Ok</b>
     */
    public static final String BT_OK = "Ok";
    
    /**
     * Campo <b>Informe a Qtd. Lote</b>
     */
    public static final String FD_QTD_LOTE = "EVlrEnt";
    
    /**
     * Campo <b>Data Perman�mcia</b>
     */
    public static final String FD_DATA_PERMANENCIA = "EDatPta";
    
    /** 
     *  Radio Button Op��es 
     */ 
    public static final String RB_OPCOES = "RGOpcoes";
    
    /**
     * Campo <b>Ver. Doc. Eletr�nico</b>
     */
    public static final String FD_VER_DOC_ELETRONICO = "EVerDoc";
    
    /**
     * Utilizado nos cen�rios 13,14,15 do TCSRNF0014_NotasFiscaisEntradaAgrupada
     * @author felipe.ravizza
     *
     */
    public enum tipoValidacao {
        VALIDADETABELA,NORMAL,DESVINCULARTABELA;
    }
    
	/**
     * Bot�o <b>Recalcular</b>
     */
    public static final String BT_RECALCULAR = "BtRecalcular";

}
