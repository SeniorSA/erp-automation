package senior.erp.utils;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.TipoParametroSQL;

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
}
