package senior.erp.utils;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.TipoParametroSQL;

public class FacadeMercado {

	public static int obterQtdeDisponivelEstoque(String codigoProduto, String derivacao, String deposito) {
		final String[][] obterQtdeDisponivelEstoque = SistemaSeniorComTransacao.recuperaValoresBaseDados(
				"select qtdest from E210EST " + "where codemp = 120 and codpro = ? and codder = ? and coddep = ?",
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoProduto),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao),
				SistemaSenior.parametroSql(TipoParametroSQL.STRING, deposito));
		final int qtdeDisponivelEstoque = Integer.parseInt(obterQtdeDisponivelEstoque[0][0]);
		return qtdeDisponivelEstoque;
	}

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

}
