package senior.erp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.senior.framework.testes.ParametroSQL;
import com.senior.framework.testes.SistemaSeniorComTransacao;

public class AssertivaERP {

    private static final String REGEX_SEPARADOR_DOIS_PONTOS = ":(?=([^\']*\'[^\']*\')*[^\']*$)";
    private static final String PALAVRA_RESERVADA_SQL_AND = " and ";
    private static final String PALAVRA_RESERVADA_SQL_NOT = " not ";
    private static final String PALAVRA_RESERVADA_SQL_LIKE = " like ";
    private static final String PALAVRA_RESERVADA_SQL_OR = " or ";
    private static final String PALAVRA_RESERVADA_SQL_INNER = " inner ";
    private static final String PALAVRA_RESERVADA_SQL_BETWEEN = " between ";
    private static final String PALAVRA_RESERVADA_SQL_WHERE = " where ";
    private static final String REGEX_FIELD_SEPARATOR = ",(?=([^\']*\'[^\']*\')*[^\']*$)";

    /**
     * OBS: Os valores devem ser passados da forma como se esperam que estejam no banco de dados. 
     * Ex: Se o valor que deve estar no banco de dados é 3 deve ser usado 3 na assertiva e não 3.00
     */
    public static void executaValidacao(final String comandoSQL, final String tabela, final String chavesPrimarias, final String valores, int linhasEsperadas, ParametroSQL... parametroSQLs) {
        montaSQL(comandoSQL, tabela, chavesPrimarias, valores, linhasEsperadas, parametroSQLs);
    }

    private static boolean assertiva(final String sql, String valores, int linhasEsperadas, final int campoValor, String campo, boolean verificaSelectCompleto,final int quantidadeCamposValidar, ParametroSQL... parametroSQLs) {
        boolean verificaRetorno = SistemaSeniorComTransacao.validarRegistroBase(sql, valores, linhasEsperadas, campoValor, campo, verificaSelectCompleto, quantidadeCamposValidar, parametroSQLs);
        return verificaRetorno;
    }

    private static void montaSQL(final String comandoSQL, final String tabela, final String chavesPrimarias, final String valores, int linhasEsperadas, ParametroSQL... parametroSQLs) {
        final String[] separadorPrimarias = chavesPrimarias.split(",");
        final String[] separadorValores = valores.split(REGEX_FIELD_SEPARATOR);
        String camposPrimariasPrimeiroSelect = "";
        String camposChavesPrimarias = "";
        String camposValores = "";
        String valoresFinais = "";
        String sql = "";
        String sqlComParametros = "";
        String valor1 = "";
        String primariasSeparadas = "";
        int countPosicaoCampo = -1;
        int countRegistros = 0;
        int countParametros = -1;
//        int countParametrosChavesPrimarias = -1;
        boolean retorno = false;
        boolean verificaRetorno = false;

        if (valores.contains(PALAVRA_RESERVADA_SQL_AND) || valores.contains(PALAVRA_RESERVADA_SQL_BETWEEN) || valores.contains(PALAVRA_RESERVADA_SQL_INNER) || valores.contains(PALAVRA_RESERVADA_SQL_LIKE) || valores.contains(PALAVRA_RESERVADA_SQL_NOT) || valores.contains(PALAVRA_RESERVADA_SQL_OR) || valores.contains(PALAVRA_RESERVADA_SQL_WHERE) ||
                chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_AND) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_BETWEEN) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_INNER) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_LIKE) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_NOT) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_OR) || chavesPrimarias.contains(PALAVRA_RESERVADA_SQL_WHERE)) {
            throw new RuntimeException("Erro na sintaxe do select utilizado, verificar o uso de 'and, like, not, or, inner join, between, where' ou qualquer palavra reservada.");
        } else {
            for (String valor : separadorPrimarias) {
                    camposPrimariasPrimeiroSelect = camposPrimariasPrimeiroSelect + valor.trim().replace(REGEX_SEPARADOR_DOIS_PONTOS, "=").trim() + " AND ";
            }

            for (final String valor : separadorValores) {
                camposValores = camposValores + valor.replace(REGEX_SEPARADOR_DOIS_PONTOS, "=").trim() + " AND ";
            }

            if (countRegistros == 0) {
                String sqlCompleto = "SELECT * FROM " + tabela + " WHERE " + camposPrimariasPrimeiroSelect + camposValores.trim().substring(0, camposValores.length() - 4);
                verificaRetorno = assertiva(sqlCompleto, "", linhasEsperadas, 0, "sqlCompleto", retorno, 0, parametroSQLs);
                retorno = true;
            }
            if (verificaRetorno) {
                for (String valor : separadorPrimarias) {
                    if(valor.contains("?")) {
                        countParametros++;
                        String parametros = parametroSQLs[countParametros].toString();
                        valor = valor.replace("?", parametros) ;
                        valor = replaceData(valor);
                        camposChavesPrimarias = camposChavesPrimarias + valor.trim().replace(REGEX_SEPARADOR_DOIS_PONTOS, "=").trim() + " AND ";
                    } else {
                        camposChavesPrimarias = camposChavesPrimarias + valor.trim().replace(REGEX_SEPARADOR_DOIS_PONTOS, "=").trim() + " AND ";
                    }
                }
                
                if (countPosicaoCampo == -1) {
                    String sqlInicial = "SELECT * FROM " + tabela + " WHERE " + camposChavesPrimarias.substring(0, camposChavesPrimarias.length() - 4);
                    assertiva(sqlInicial, "", linhasEsperadas, 0, "", retorno, 0);
                }
                for (String string : separadorValores) {
                    valoresFinais += string.trim() + " AND ";
                    primariasSeparadas += string.trim().substring(0, 6) + ",";
                    sql = "SELECT " + primariasSeparadas.substring(0, primariasSeparadas.length() - 1) + " FROM " + tabela + " WHERE " + camposChavesPrimarias + " " + valoresFinais.replace(REGEX_SEPARADOR_DOIS_PONTOS, "=").substring(0, valoresFinais.length() - 4).trim();
                    countPosicaoCampo++;
                    countRegistros++;
                    if (string.contains("?")) {
                        countParametros++;
                        valor1 = valor1 + parametroSQLs[countParametros];
                        valor1 = replaceData(valor1);
                        sqlComParametros = sql.replace(sql.substring(sql.length() - 1), valor1.trim()) + ",";
                        sqlComParametros = sqlComParametros.substring(0, sqlComParametros.length() - 1);
                        valoresFinais = valoresFinais.substring(0, valoresFinais.length() - 6) + valor1.trim() + " AND ";
                        assertiva(sqlComParametros.trim(), valores, linhasEsperadas, countPosicaoCampo, string.replace("?", valor1).trim(), retorno, separadorValores.length);
                        valor1 = "";
                    } else {
                        string = string.trim().replace(REGEX_SEPARADOR_DOIS_PONTOS, "=");
                        assertiva(sql, valores, linhasEsperadas, countPosicaoCampo, string.trim(), retorno, separadorValores.length);
                    }
                }
            } else {
                countRegistros++;
            }
        }
    }

    private static String replaceData(String valor1) {
        String database = getDatabaseName();
        return database.equalsIgnoreCase("Oracle") 
                ? valor1.replaceAll("(\\d{4})(-)(\\d{2})(-)(\\d{2})", "'$5/$3/$1'") 
                : valor1.replaceAll("(\\d{4})(-)(\\d{2})(-)(\\d{2})", "'$1-$3-$5'");
    }

    private static String getDatabaseName() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("framework.properties");
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return prop.getProperty("basededados.tipobanco.sapiens");
    }

}
