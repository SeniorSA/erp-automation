package senior.erp.utils;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.TipoParametroSQL;

/**
 * Facilitador para testes de manufatura que possuem métodos comuns em seus casos de testes.
 * 
 *
 */
public class FacadeManufatura {
    
    public static int obterQuantidadeEstoque(String campoRecuperar, String CodigoEmpresa, String codigoProduto, String derivacao, String deposito) {
        final String[][] obterQtdeReservadaEstoque = SistemaSeniorComTransacao.recuperaValoresBaseDados("select "+campoRecuperar+" from E210EST where codemp = ? and codpro = ? and codder = ? and coddep = ?",SistemaSenior.parametroSql(TipoParametroSQL.STRING, CodigoEmpresa),  SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoProduto), SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao), SistemaSenior.parametroSql(TipoParametroSQL.STRING, deposito));
        final int qtdeReservadaEstoque = Integer.parseInt(obterQtdeReservadaEstoque[0][0]);
        return qtdeReservadaEstoque;
    }
    
    
    public static int obterQuantidadeEstoqueLote(String campoRecuperar, String tabelaSelect, String codigoProduto, String derivacao, String deposito, String codigoLote) {
        final String[][] obterQtdeReservadaEstoqueLote = SistemaSeniorComTransacao.recuperaValoresBaseDados("select "+campoRecuperar+" from "+tabelaSelect+" where codemp = 160 and codpro = ? and codder = ? and coddep = ? and codlot = ?", SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoProduto), SistemaSenior.parametroSql(TipoParametroSQL.STRING, derivacao), SistemaSenior.parametroSql(TipoParametroSQL.STRING, deposito), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoLote));
        final int qtdeReservadaEstoqueLote = Integer.parseInt(obterQtdeReservadaEstoqueLote[0][0]);
        return qtdeReservadaEstoqueLote;
    }
    
}
