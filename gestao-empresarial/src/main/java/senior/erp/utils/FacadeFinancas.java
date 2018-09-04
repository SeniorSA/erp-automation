package senior.erp.utils;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.TipoParametroSQL;

public class FacadeFinancas {

    /**
     * Obt�m o n�mero do t�tulo gerado no contas a receber, com o intuito de utilizar este dado na valida��o dos
     * testes.
     * Por padr�o, � for�ado um data de gera��o = a data atual para somente recuperar o t�tulo a receber gerado pelo
     * teste.
     * Por padr�o, ser� for�ado recuperar o n�mero como String, n�o sendo necess�rio fazer convers�es de Integer para
     * String quando este valor for utilizado no select de valida��o.
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
