package senior.erp;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import senior.erp.componentNames.cadastros.Form000EDIComponentNames;
import senior.erp.componentNames.cadastros.Form000PGSComponentNames;
import br.com.senior.asas.testinterfaces.ITestButton;
import br.com.senior.asas.testinterfaces.ITestLabel;
import br.com.senior.asas.testinterfaces.ITestMessage;
import br.com.senior.qa.logger.LoggerFramework;
import br.com.senior.qa.properties.ApplicationProperties;

import com.senior.framework.testes.CaixaMensagem;
import com.senior.framework.testes.EstadoComponente;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;
import com.senior.framework.testes.smoking.AccessibleSystem;

/**
 *
 */
public class TCBaseERP extends TCBaseERPNucleo {

    /**
     * Script FAST para trocar o usuário do sistema
     * 
     * @param nome
     *            Informar nome do novo usuário
     * @param senha
     *            Informar senha do novo usuário
     */
    public static void trocarUsuario(String nome, String senha) {

        // Expandir painel de navegação, se necessário
        ITestButton btExpandirPainelNavegacao = (ITestButton) commandManager.getChild("SBMax");
        if (btExpandirPainelNavegacao.isVisible()) {
            btExpandirPainelNavegacao.click();
        }

        // Expandir painel de informação, se necessário
        ITestButton btExpandirPainelInfo = (ITestButton) commandManager.getChild("sbInfoPaneMinMax");
        ITestLabel labelUsuario = (ITestLabel) commandManager.getChild("InfoPanelLabel1");
        if (!labelUsuario.isVisible()) {
            btExpandirPainelInfo.click();
        }

        //Quebra a string, pois é possivel que o usuario tenha "(ADM)" no final do label
        String[] usuario = labelUsuario.getText().split(" ");
        if (!usuario[0].equalsIgnoreCase(nome)) {
            teclar(Tecla.F7);
            preencherCampo("ENomUsu", nome, Tecla.TAB);
            preencherCampo("ESenUsu", senha, Tecla.TAB);
            clicar("BtnOk");
        }
    }

    /**
     * Retorna data atual
     * <p><b>Deprecado, utilizar o método SistemaSenior.calcularData(formato, alterarData);</b>
     * 
     * @param somarDias
     *            Número de dias a somar na data atual
     * @return Data no formado dd/mm/yyyy
     */
    public static String obterDataHoje(int somarDias) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, somarDias);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(c.getTime());
    }

    /**
     * Retorna o mês e o ano atual
     * 
     * @return Data no formado mm/yyyy
     */
    public static String obterMesAnoAtual() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        DateFormat df = new SimpleDateFormat("MM/yyyy");
        return df.format(c.getTime());
    }

    /**
     * Forçar falha em cenário não implementado
     */
    public void testeNaoImplementado() {
        Assert.fail("Este teste não foi implementado");
    }

    /**
     * Script FAST para manipular o estado de um identificador (ativar ou desativar)
     * 
     * @param modulo
     *            Módulo do identificador
     * @param nome
     *            Nome do identificador
     * @param estado
     *            A=ativar, I=inativar
     */
    public static void manipularIdentificador(String modulo, String nome, String estado) {
        abrirTela("F098REG");
        preencherCampo("DEModSis1", modulo, Tecla.TAB);
        preencherCampo("DEIdeReg1", nome, Tecla.TAB);
        preencherCampo("DESitReg1", estado, Tecla.TAB);
        fecharTela("F098REG");
    }

    /**
     * Altera diretórios de geração de arquivos eletrônicos (XMLs) via comando update.
     * 
     * @param codigoEmpresa
     * @param codigoFilial
     * @param codigoSerie
     * @param nomeCampoDir
     */
    public static void alterarDiretorioSerieNF(final int codigoEmpresa, final int codigoFilial, final String codigoSerie, final String nomeCampoDir) {
        String update = buildUpdate(nomeCampoDir);
        SistemaSeniorComTransacao.executarSQL(update, SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, Integer.toString(codigoEmpresa)), SistemaSenior.parametroSql(TipoParametroSQL.INTEGER, Integer.toString(codigoFilial)), SistemaSenior.parametroSql(TipoParametroSQL.STRING, codigoSerie));
    }

    private static String buildUpdate(final String nomeCampoDir) {
        StringBuilder update = new StringBuilder();
        update.append("update e020snf set ");
        update.append(nomeCampoDir);
        update.append(" = '");
        update.append(obterCaminhoRelGerado(true));
        update.append("' where codemp=? and codfil=? and codsnf=?");
        return update.toString();
    }

    /**
     * Fecha a janela informada se ela for a janela ativa.
     * <p>
     * <b>Exemplo:</b>
     * </p>
     * <p>
     * <code>fecharTela("F120GPD");</code>
     * </p>
     * 
     * @param tela
     *            informar o nome da tela que está ativa
     */
    public static void fecharTela(String tela) {
        SistemaSenior.fecharTela(tela);

        // Tratativa para fechar automaticamente os diálogos de confirmação de saída
        JanelaAtiva j = new JanelaAtiva();
        if (j.isJanelaConfirmacaoSair()) {
            try {
                getManipuladorDeComandos().getApplication().getMessage().click("Sim");
            } catch (Exception e) {
                getManipuladorDeComandos().getApplication().getMessage().click("Cancelar");
            }
        } else if (isJanelaAvisoOuConfirmacao(j)) {
            ITestMessage mensagem = commandManager.getApplication().getMessage();
            if (mensagem.getDescription().contains("Sair")) {
                try {
                    mensagem.click("Sim");
                } catch (Exception e) {
                    mensagem.click("Sair");
                }
            } else if (mensagem.getDescription().contains("Finalizar")) {
                try {
                    mensagem.click("Sim");
                } catch (Exception e) {
                    mensagem.click("Finalizar");
                }
            } else {
                try {
                    mensagem.click("Sim");
                } catch (Exception e) {
                    mensagem.click("Ok");
                }
            }

        }
    }

    private static boolean isJanelaAvisoOuConfirmacao(JanelaAtiva j) {
        if (j.getTitulo().equals("Aviso") || j.getTitulo().equals("Confirmação")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna o caminho das massas de dados (framework.properties).
     * 
     * @param caminhoAbsoluto caminhoAbsoluto
     * 
     * @return ApplicationProperties.getDataMassPath();
     */
    public static String obterCaminhoMassa(boolean caminhoAbsoluto) {
        if (caminhoAbsoluto) {
            File f = new File(ApplicationProperties.getFrameworkTestesDir() + File.separator + ApplicationProperties.getDataMassPath());
            return f.getAbsolutePath();
        }
        return ApplicationProperties.getDataMassPath();
    }

    /**
     * Retorna o caminho dos relatórios/arquivos gerados (framework.properties).
     * 
     * @param caminhoAbsoluto caminhoAbsoluto
     * 
     * @return ApplicationProperties.getReportGeneratedPath();
     */
    public static String obterCaminhoRelGerado(boolean caminhoAbsoluto) {
        if (caminhoAbsoluto) {
            File f = new File(ApplicationProperties.getFrameworkTestesDir() + File.separator + ApplicationProperties.getReportGeneratedPath());
            return f.getAbsolutePath();
        }
        return ApplicationProperties.getReportGeneratedPath();
    }

    /**
     * Retorna o caminho dos relatórios/arquivos esperados (framework.properties).
     * 
     * @return ApplicationProperties.getReportExpectedPath();
     */
    public static String obterCaminhoRelEsperado() {
        return ApplicationProperties.getReportExpectedPath();
    }

    /**
     * Retorna o caminho dos arquivos Webservices (XMLs de entrada) do framework.properties.
     * 
     * @return ApplicationProperties.getTemplatesWebServicePath();
     */
    public static String obterCaminhoArquivosWS() {
        return ApplicationProperties.getTemplatesWebServicePath();
    }

    /**
     * Verifica se um determinado caractere ou um conjunto de caracteres existem em um arquivo texto.<br>
     * Se não existir, o teste irá falhar.<br>
     * 
     * <p>
     * <b>Exemplo:</b>
     * </p>
     * <p>
     * <code>conferirSeExisteEmRelatorioTxt("c:/sped.txt", "|0400|1101|Compra para Industrialização|");</code>
     * </p>
     * 
     * @param relatorioGerado
     *            caminho e nome do relatório que deve ser utilizado na pesquisa
     * @param termo
     *            caractere ou conjunto de caracteres que serão pesquisados no relatório
     */
    public static void conferirSeExisteEmRelatorioTxt(String relatorioGerado, String termo) {

        LoggerFramework.addDebug("Executando o comando: conferirSeExisteEmRelatorioTxt(relatório = " + relatorioGerado + ", termo = " + termo + ")");

        int resultado = 0;
        int numeroLinhas = 0;

        File arq = new File(relatorioGerado);

        if (arq.exists()) {
            LoggerFramework.addDebug("Arquivo encontrado: '" + relatorioGerado + "'");
            LoggerFramework.addDebug("Procurando pelo termo: '" + termo + "'");
            try {
                FileReader reader = new FileReader(relatorioGerado);
                BufferedReader leitor = new BufferedReader(reader);

                while (leitor.ready()) {
                    String linha = leitor.readLine();
                    numeroLinhas++;

                    if (linha.contains(termo)) {
                        LoggerFramework.addDebug("Encontrou: linha [" + numeroLinhas + "] Conteúdo: " + linha);
                        resultado++;
                    }
                }

                leitor.close();
                reader.close();

            } catch (Exception erro) {
                LoggerFramework.addDebug("Ocorreu um erro ao ler o arquivo");
                erro.printStackTrace();
            }

            if (resultado != 0) {
                LoggerFramework.addDebug("Encontrados " + resultado + " resultados");
            } else {
                LoggerFramework.addDebug("O termo '" + termo + "' não foi encontrado no arquivo: '" + relatorioGerado + "'");
                fail("O termo '" + termo + "' não foi encontrado no arquivo: '" + relatorioGerado + "'");
            }

        } else {
            LoggerFramework.addDebug("O arquivo não foi encontrado: " + relatorioGerado);
            fail("O arquivo não foi encontrado: " + relatorioGerado);
        }

    }

    /**
     * Exclui o arquivo informado (não gera exceção se não existir).<br>
     * <p>
     * Deve ser informado o nome completo do arquivo, seguido de sua extensão, ex.:
     * CancelamentoInutilizacaoDocEletronicos.log.<br>
     * <p>
     * O arquivo não será removido se o seu nome for informado parcialmente, ex.: CancelamentoInutilizacao.
     * 
     * @param caminhoDoArquivo caminhoDoArquivo
     */
    public static void removerArquivo(String caminhoDoArquivo) {
        new File(caminhoDoArquivo).delete();
        LoggerFramework.addDebug("Arquivo removido: " + caminhoDoArquivo);
    }

    /**
     * Retorna o caminho da pasta em que serão importadas as tabelas dinâmicas
     * 
     * @return ApplicationProperties.getReportGeneratedPath() + TabelasDinamicas;
     */
    public static String obterCaminhoTabelasDinamicas() {
        File f = new File(ApplicationProperties.getFrameworkTestesDir() + File.separator + "\\tabelasDinamicas");
        return f.getAbsolutePath();
    }

    /**
     * Exclui todos os arquivos no caminho informado
     * 
     * @param caminho dos arquivos
     */
    public static void removerTodosArquivos(String caminhoDosArquivos) {
        File pasta = new File(caminhoDosArquivos);
        if (pasta.isDirectory()) {
            File[] listaAquivos = pasta.listFiles();
            for (File aquivo : listaAquivos) {
                aquivo.delete();
            }
            LoggerFramework.addDebug("Arquivos removidos no caminho: " + caminhoDosArquivos);
        } else Assert.fail("O caminho dos arquivos não foi encontrado.");
    }

    /**
     * Verifica se existe arquivos no caminho informado
     * 
     * @param caminho dos arquivos
     */
    public static void existeAquivosDiretorio(String caminhoDosArquivos) {
        File pasta = new File(caminhoDosArquivos);
        if (pasta.isDirectory()) {
            if (pasta.listFiles().length > 0) LoggerFramework.addDebug("Arquivos removidos no caminho: " + caminhoDosArquivos);
            else Assert.fail("Diretório de arquivos " + caminhoDosArquivos + ", não possui nenhum arquivo.");
        } else Assert.fail("O caminho dos arquivos não foi encontrado.");
    }

    /**
     * Retorna nome do arquivo completo dado um prefixo estático. Útil para obter nomes de arquivos quando leva
     * data/hora na composição do nome
     * 
     * @param prefixoNome
     *            Caminho + prefixo do nome do arquivo
     * @return Nome do primeiro arquivo encontrado com o prefixo informado dentro do caminho
     * 
     */
    public static String obterNomeArquivoPeloPrefixo(String prefixoNome) {
        String pastaStr = (new File(prefixoNome)).getParent();
        String prefixo = (new File(prefixoNome)).getName();

        if (pastaStr == null) new RuntimeException("Pasta do caminho informado é inválida: " + prefixoNome);

        File pasta = new File(pastaStr);

        for (File f : pasta.listFiles()) {
            if (f.isFile()) {
                if (f.getName().startsWith(prefixo)) return f.getName();
            }
        }

        return prefixoNome;
    }

    /**
     * Retorna uma data no último dia do mês atual
     * 
     * @param formato
     *            da data (ex: dd/MM/yyyy)
     * @return data formatada
     */
    public String retornarUltimoDiaMesAtual(String formato) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date today = cal.getTime();

        DateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(today);
    }

    /**
     * Retorna uma data no primeiro dia do mês atual
     * 
     * @param formato
     *            da data (ex: dd/MM/yyyy)
     * @return data formatada
     */
    public static String retornarPrimeiroDiaMesAtual(String formato) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        DateFormat sdf = new SimpleDateFormat(formato);

        return sdf.format(cal.getTime());
    }

    /**
     * Confere o valor da TAG de um arquivo XML.<br>
     * <br>Este comando deve ser utilizado quando somente nenhuma outra TAG do XML tiver o mesmo nome, ou seja, cada TAG terá um nome único e não duplicado.<br>
     * <br><b>Exemplo:</b><br>
     * <code>conferirTagArquivoXml("arquivo.xml", "500", "tag1", "tag2", "tag2");</code>
     * <code>conferirTagArquivoXml("arquivo.xml", "500", "tag1 nItem='1'", "tag2", "tag2");</code>
     * 
     * @param arquivoXml Caminho do arquivo XML para conferir.
     * @param valorEsperado Valor esperado na TAG informada.
     * @param tags Caminho das TAGs com valor para conferir.
     */
    public static void conferirTagArquivoXml(final String arquivoXml, final String valorEsperado, final String... tags) {
        conferirTagArquivoXml(1, 1, true, arquivoXml, valorEsperado, false, tags);
    }
    
    /**
     * Confere a existência de determinada quantidade de TAGs de um arquivo XML.<br>
     * <br><b>ATENÇÃO: Este comando não valida o conteúdo da TAG!</b> Ele valida se determinada TAG apareceu quantas vezes é
     * esperada.<br>
     * 
     * <br>Não pode informar o valor da tag para validação, mas sim, a quantidade de vezes que a TAG se repete no XML.<br>
     * <br><b>Exemplo:</b><br>
     * Um XML com quantidade de parcelas geradas = a 2 parcelas.
     * É esperado que no arquivo contenham as seguintes TAGs referente a parcelas:
     * <br>pag - detPag - tPag - 01
     * <br>pag - detPag - tPag - 02 <br>
     * <br>Note que a TAG detPag é exibida duas vezes e os seus conteúdo são diferentes, mas este comando NÃO valida o conteúdo da TAG!<br>
     * 
     * <b><br>O comando ficará assim:<br></b>
     * 
     * <br><code>conferirTagArquivoXml(2, "arquivo.xml", "pag", "detPag", "tPag");</code> <- <b>NOTE que</b> o valor da TAG
     * não foi passado, apenas passado o nome da TAG, aonde o número 2 no início do comando, refere-se a quantidade de
     * vezes que a TAG tPag aparece no XML.
     * 
     * @param quantidadeTags quantidade de TAGs que deverão existir no XML gerado.
     * @param arquivoXml Caminho do arquivo XML para conferir.
     * @param tags Caminho das TAGs para conferir a quantidade delas no XML.
     */
    public static void conferirTagArquivoXml(final int quantidadeTags, final String arquivoXml, final String... tags) {
        conferirTagArquivoXml(quantidadeTags, 1, false, arquivoXml, null, false, tags);
    }
    
    /**
     * Confere o valor da TAG de um arquivo XML.<br>
     * <br>Este comando deve ser utilizado quando MAIS DE uma TAG do XML tiverem o mesmo nome.<br>
     * <br><b>Exemplo:</b><br>
     * Um XML com quantidade de parcelas geradas = a 2 parcelas.
     * É esperado que no arquivo contenham as seguintes TAGs referente a parcelas:
     * <br>pag - detPag - tPag - 01
     * <br>pag - detPag - tPag - 02 <br>
     * <br>Note que a TAG detPag é exibida duas vezes e seus valores são diferentes.<br>
     * 
     * <b><br>O comando ficará assim, para conferir as duas TAGs e seus valores:</b>
     * <br><code>conferirTagArquivoXml(2, 1, "arquivo.xml", "01", "pag", "detPag", "tPag");</code> - <b>Onde</b> o primeiro parâmetro que está com o valor 2, significa a quantidade de TAGs "tPag" que deverão existir no XML. O segundo parâmetro que está com o valor 1, indica que a primeira TAG detPag do XML, conterá o valor 01.</br>
     * <br><code>conferirTagArquivoXml(2, 2, "arquivo.xml", "02", "pag", "detPag", "tPag");</code> - <b>Onde</b> o primeiro parâmetro que está com o valor 2, significa a quantidade de TAGs "tPag" que deverão existir no XML. O segundo parâmetro que está com o valor 2, indica que a segunda TAG detPag do XML, conterá o valor 02.</br>
     * 
     * <br>O comando também pode ser utilizado para conferir TAGs iguais e que contenham o mesmo valor.<br>
     * <br><b>Exemplo:</b><br>
     * É esperado que no arquivo contenham as seguintes TAGs referente a parcelas:
     * <br>pag - detPag - tPag - 03
     * <br>pag - detPag - tPag - 03 <br>
     * <br>Note que a TAG detPag é exibida duas vezes e seus valores são iguais.<br>
     * 
     * <b><br>O comando ficaria assim, para conferir as duas TAGs e seus valores:</b>
     * <br><code>conferirTagArquivoXml(2, 1, "arquivo.xml", "03", "pag", "detPag", "tPag");</code> - <b>Onde</b> o primeiro parâmetro que está com o valor 2, significa a quantidade de TAGs "tPag" que deverão existir no XML. O segundo parâmetro que está com o valor 1, indica que a primeira TAG detPag do XML, conterá o valor 03.</br>
     * <br><code>conferirTagArquivoXml(2, 2, "arquivo.xml", "03", "pag", "detPag", "tPag");</code> - <b>Onde</b> o primeiro parâmetro que está com o valor 2, significa a quantidade de TAGs "tPag" que deverão existir no XML. O segundo parâmetro que está com o valor 2, indica que a segunda TAG detPag do XML, também irá conter o valor 03.</br>
     * 
     * @param quantidadeTags quantidade de TAGs a serem validadas.
     * @param posicaoIndiceTag numero da posicao da tag dentro do XML.
     * @param arquivoXml Caminho do arquivo XML para conferir.
     * @param valorEsperado Valor esperado na TAG informada.
     * @param tags Caminho das TAGs com valor para conferir.
     * 
     */
    public static void conferirTagArquivoXml(final int quantidadeTags, final int posicaoIndiceTag, final String arquivoXml, final String valorEsperado, final String... tags) {
        conferirTagArquivoXml(quantidadeTags, posicaoIndiceTag, true, arquivoXml, valorEsperado, false, tags);
    }
    
    /**
     * Confere o valor da TAG de um arquivo XML.<br>
     * <b>Exemplo:</b><br>
     * <code>conferirTagArquivoXml("arquivo.xml", "500", "tag1", "tag2", "tag2");</code>
     * <code>conferirTagArquivoXml("arquivo.xml", "500", "tag1 nItem='1'", "tag2", "tag2");</code>
     * 
     * @param quantidade quantidade de TAGs a serem validadas.
     * @param posicaoIndiceTag numero da posicao da tag dentro do XML.
     * @param validarValorTags indicativo se irá validar ou não o valor encontrado na TAG.
     * @param arquivoXml Caminho do arquivo XML para conferir.
     * @param valorEsperado Valor esperado na TAG informada.
     * @param valorParcial Considera apenas parte do valor.
     * @param tags Caminho das TAGs com valor para conferir.
     */
    public static void conferirTagArquivoXml(final int quantidade, final int indice, final boolean validarValorTags, final String arquivoXml, final String valorEsperado, final boolean valorParcial, final String... tags) {
        File arquivo = new File(ApplicationProperties.getReportGeneratedPath() + File.separator + arquivoXml);
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(arquivo);

            final XPathFactory xPathfactory = XPathFactory.newInstance();
            final XPath xpath = xPathfactory.newXPath();

            final XPathExpression xPathExpression = xpath.compile(mountXPathExpression(tags));

            final NodeList nodeListResult = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

            if (nodeListResult.getLength() > quantidade) {
                Assert.fail("O caminho de TAGs informado retorna mais de " + quantidade + " valor(es)");
            }
            if (nodeListResult.getLength() < quantidade) {
                Assert.fail("O caminho de TAGs informado não foi encontrado. Era esperado encontrar " + quantidade + " TAG(s) mas encontrou " + nodeListResult.getLength() + " TAG(s)");
            }
            try {
                if (validarValorTags) {
                    final String resultValue = nodeListResult.item(indice - 1).getTextContent();
                    boolean isMatch = false;
                    if (valorParcial) {
                        isMatch = resultValue.toUpperCase().contains(valorEsperado.toUpperCase());
                    } else {
                        isMatch = resultValue.equalsIgnoreCase(valorEsperado);
                    }
                    if (!isMatch) {
                        Assert.fail("O valor da TAG não confere com o esperado. Esperado: " + valorEsperado + ". Encontrado: " + resultValue);
                    }
                }
            } catch (Exception e) {
                StringBuilder tagsFormatadas = new StringBuilder();
                for (int i = 0; i < tags.length; i++) {
                    tagsFormatadas.append(tags[i]).append(", ");
                }
                throw new RuntimeException("O indíce de número " + indice + " passado para encontrar as TAGs " + tagsFormatadas.toString() + "com o valor \"" + valorEsperado + "\" não foi encontrado. Verifique se as TAGs foram geradas e execute o teste novamente.");
            }
        } catch (SAXException | ParserConfigurationException | XPathExpressionException e) {
            throw new RuntimeException("Não é possível conferir múltiplas Tags em um único comando conferirTagArquivoXml. \nCaso for necessário conferir várias Tags, deve-se utilizar um comando conferirTagArquivoXml para cada Tag a ser conferida.", e);
        } catch (final IOException fnf) {
            Assert.fail("O arquivo informado não foi encontrado: " + arquivoXml);
        }
    }

    private static String mountXPathExpression(final String[] tags) {
        final StringBuilder xPathExpression = new StringBuilder("/");
        for (final String tag : tags) {
            final String[] splittedTag = splitTag(tag);
            final StringBuilder xPathElement = mountNodeElement(splittedTag);

            xPathExpression.append("/").append(xPathElement);
        }

        return xPathExpression.append("/text()").toString();
    }

    private static String[] splitTag(final String tagElement) {
        String[] tag;
        if ((tag = tagElement.trim().split(" ")).length > 1) {
            return tag;
        }

        return new String[] { tagElement };
    }

    private static StringBuilder mountNodeElement(final String[] splittedTag) {
        final int TAG_NAME_INDEX = 0;
        final StringBuilder xPathElement = new StringBuilder(splittedTag[TAG_NAME_INDEX]);
        for (int tagAttributeIndex = 1; tagAttributeIndex < splittedTag.length; tagAttributeIndex++) {
            xPathElement.append("[@").append(splittedTag[tagAttributeIndex]);
            if (tagAttributeIndex < splittedTag.length - 1) {
                xPathElement.append(" and ");
            }

            xPathElement.append("]");
        }
        return xPathElement;
    }

    /**
     * <b>Executa Webservices via menu Multicamadas importando os dados de um XML.</b><br>
     * Importante: Para utilização deste método, é necessário entrar no sistema com o parametro
     * <i><b>-mcdebug</b></i>;<br>
     * 
     * @param arqXML Caminho + Nome do arquivo XML que será importado. Ex.: "w:\ArqServicoX.xml"
     * @param srvServico Nome do serviço que será executado
     * @param srvPorta Nome da porta que será executado
     * 
     *
     */
    public static void executarWebservicesXML(String arqXML, String srvServico, String srvPorta) {
        executarWebservicesXML(arqXML, srvServico, srvPorta, 10000, null);
    }

    /**
     * <b>Executa Webservices via menu Multicamadas importando os dados de um XML.</b><br>
     * Importante: Para utilização deste método, é necessário entrar no sistema com o parametro
     * <i><b>-mcdebug</b></i>;<br>
     * 
     * @param arqXML Caminho + Nome do arquivo XML que será importado. Ex.: "w:\ArqServicoX.xml"
     * @param srvServico Nome do serviço que será executado
     * @param srvPorta Nome da porta que será executado
     * @param xPausar o tempo que levara antes de fechar a tela.
     * 
     *
     */
    public static void executarWebservicesXML(String arqXML, String srvServico, String srvPorta, int xPausar) {
        executarWebservicesXML(arqXML, srvServico, srvPorta, xPausar, null);
    }

    /**
     * <b>Executa Webservices via menu Multicamadas importando os dados de um XML.</b><br>
     * Salva o arquivo XML de resposta no final da execução com o nome informado.<br>
     * Importante: Para utilização deste método, é necessário entrar no sistema com o parametro
     * <i><b>-mcdebug</b></i>;<br>
     * 
     * @param arqXML Caminho + Nome do arquivo XML que será importado. Ex.: "w:\ArqServicoX.xml"
     * @param srvServico Nome do serviço que será executado
     * @param srvPorta Nome da porta que será executado
     * @param xPausar o tempo que levara antes de fechar a tela.
     * @param nomeArquivoResposta nome para salvar o arquivo resposta ao final da execução
     * 
     *
     */
    public static void executarWebservicesXML(String arqXML, String srvServico, String srvPorta, int xPausar, String nomeArquivoResposta) {
        teclarCombinacao(Tecla.CTRL, Tecla.F11);

        preencherCampo("ENomeWS", srvServico, Tecla.TAB); //Nome do serviço
        preencherCampo("EPorta", srvPorta, Tecla.TAB); //Nome da porta

        marcarCaixaAtribuicao("CBMostrarForm"); //Marca checkbox Abrir Form do Web Service

        clicar("BtnExecutar"); //Botão executar
        clicar("btLoadXML"); //Botão Carregar XML

        abrirArquivo(ApplicationProperties.getFrameworkTestesDir() + File.separator + obterCaminhoArquivosWS() + "\\" + arqXML);
        pausar(500);

        clicar("btRun"); //Botão Executar
        pausarWebservice(xPausar);

        if (nomeArquivoResposta != null) {
            clicar("btResponseXML"); //Salvar Arquivo
            salvarArquivo((obterCaminhoRelGerado(true)) + "/" + nomeArquivoResposta);
        }

        clicar("btSair"); //Fecha tela do serviço
        fecharJanelasPendentes();
    }

    /**
     * Pausa durante execução de Webservice.<br>
     * Apos executar metodo pausar verifica se btLoadXML ja está visivel, se não adicona mais 2 segundos. <br>
     * Numero maximo de tentativas = 10. <br>
     * <p>
     * <b> Exemplo: </b>
     * </p>
     * <p>
     * pausarWebservice(5000); <br>
     * <br>
     * No exemplo irá aguardar por 5 segundos.<br>
     * Se o tempo não for suficiente, irá aguardar mais 2 segundos. São feitas 10 tentativas antes de terminar.
     * </p>
     * 
     * @param xPausar Tempo de pausa
     * 
     */
    public static void pausarWebservice(int xPausar) {
        pausarWebservice(xPausar, 0, null);
    }

    /**
     * Pausa durante execução de Webservice.<br>
     * Apos executar metodo pausar verifica se btLoadXML ja está visivel,
     * se não adicona mais 2 segundos. <br>
     * Confere Caixa de Mesagem apos a execução do processo.<br>
     * Numero maximo de tentativas = 10. <br>
     * <p>
     * <b> Exemplo: </b>
     * </p>
     * <p>
     * pausarWebservice(5000, criarGabaritoMensagem("Aviso", "Processo concluido com sucesso!", "Ok")); <br>
     * <br>
     * No exemplo irá aguardar por 5 segundos.<br>
     * Se o tempo não for suficiente, irá aguardar mais 2 segundos.<br>
     * São feitas 10 tentativas antes de terminar.<br>
     * Apos executar o processe confere a mensagem informada.<br>
     * </p>
     * 
     * @param xPausar Tempo de pausa
     * 
     * @param xMensagens Lista de Caixas
     */
    public static void pausarWebservice(int xPausar, List<CaixaMensagem> xMensagens) {
        pausarWebservice(xPausar, 0, xMensagens);
    }

    /**
     * Metodo recursivo de pausa ao executar Webservice<br>
     * <br>
     * Apos executar metodo pausar verifica se btLoadXML ja está visivel, se não adicona mais 2s.<br>
     * Numero maximo de tentativas = 10.<br>
     * <br>
     * Confere a caixa de mensagem no fim do preocesso.<br>
     * Se a mensagem for igual a 'null', ignora a verificação.<br>
     * 
     * @param xPausar tempo de pausa
     * @param xNumTentativa numero atual da tentativa
     * @param xMensagens Lista de caixas de mensagens a serem tratadas
     * 
     */
    private static void pausarWebservice(int xPausar, int xNumTentativa, List<CaixaMensagem> xMensagens) {
        if (xNumTentativa < 10) {
            pausar(xPausar);

            try {
                if (xMensagens != null) {
                    for (int i = 0; i < xMensagens.size(); i++) {
                        conferirCaixaMensagem(xMensagens.get(i).getTitle(), xMensagens.get(i).getMessage(), xMensagens.get(i).getDescriptionButton());
                    }
                    xMensagens = null;
                }
                conferirEstadoComponente("btLoadXML", EstadoComponente.VISIVEL);
            } catch (Exception e) {
                pausarWebservice(2000, ++xNumTentativa, xMensagens);
            }
        }
    }

    /**
     * Manipula o <b>valor</b> dos <b>parametros globais</b> informados.<br>
     * <p>
     * <b> Exemplo: </b>
     * </p>
     * <p>
     * manipularParametroGlobal("PedVisUsu", "S", "AltDatEnt", "S", "AnlPedBlo", "N");<br>
     * <br>
     * </p>
     * 
     * @param parametros Nome do parametro global, valor(S ou N).
     */
    public static void manipularParametroGlobal(String... parametros) {
        if (!(parametros.length % 2 == 0)) {
            Assert.fail("Verifique os parâmetros!");
        }

        SistemaSenior.abrirTela(Form000PGSComponentNames.FR_NOME);

        for (int i = 0; i < parametros.length; i += 2) {
            SistemaSenior.preencherCampo(Form000PGSComponentNames.FD_VALOR, parametros[i], Tecla.TAB);
            SistemaSenior.clicar(Form000PGSComponentNames.BT_MOSTRAR);
            SistemaSenior.preencherLinhaCorrenteGrade(Form000PGSComponentNames.GD_PARAMETRO, "Valor", parametros[i + 1]);
        }

        SistemaSenior.preencherCampo(Form000PGSComponentNames.FD_VALOR, "", Tecla.TAB);
        SistemaSenior.clicar(Form000PGSComponentNames.BT_MOSTRAR);

        SistemaSenior.clicar(Form000PGSComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem("Advertência", parametros.length / 2 + " parâmetro(s) global(is) alterado(s)!", "OK");

        TCBaseERP.fecharTela(Form000PGSComponentNames.FR_NOME);
    }

    /**
     * Altera para a data atual a última nota fiscal gerada para determinada série de notas.
     * 
     * @param codigoEmpresa
     * @param codigoFilial
     * @param codigoSerie
     *
     */
    public static void alterarDataUltimaNotaFiscalGerada(final String codigoEmpresa, final String codigoFilial, final String codigoSerie) {
        SistemaSeniorComTransacao.executarSQL("update e020snf set ultdat=? where codemp=" + codigoEmpresa + " and codfil=" + codigoFilial + " and codsnf='" + codigoSerie + "'", SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
    }

    /**
     * Retorna a hora atual no formato do banco de dados.
     * 
     * @return data no formato do banco de dados.
     */
    public static int retornarHoraAtual() {
        String horaAtual = SistemaSenior.horaAtual();
        String horas = horaAtual.substring(0, 2);
        String minutos = horaAtual.substring(3, 5);
        return Integer.parseInt(horas) * 60 + Integer.parseInt(minutos);
    }

    /**
     * Remove arquivos XMLs gerados no diretório.
     * 
     * @param nomeArquivoGerado
     */
    public static void removerArquivoXMLGerado(final String nomeArquivoGerado) {
        TCBaseERP.removerArquivo(TCBaseERP.obterCaminhoRelGerado(true) + File.separator + TCBaseERP.obterNomeArquivoPeloPrefixo(TCBaseERP.obterCaminhoRelGerado(true) + nomeArquivoGerado));
    }

    /**
     * Gera um número aleatório para utilizar dinamicamente em testes automatizados.
     * 
     * @return
     */
    public static int gerarNumeroAleatorio() {
        int numeroAleatorio = (int) (3500 + Math.random() * 1000);
        return numeroAleatorio;
    }

    /**
     * Gera um número de CNPJ aleatoriamente.
     * 
     * @return
     */
    public static String gerarCNPJ() {
        int digito1 = 0, digito2 = 0, resto = 0;
        String nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
        //numeros gerados
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);
        int n10 = numeroAleatorio.nextInt(10);
        int n11 = numeroAleatorio.nextInt(10);
        int n12 = numeroAleatorio.nextInt(10);
        int soma = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;
        int valor = (soma / 11) * 11;
        digito1 = soma - valor;
        //Primeiro resto da divisão por 11.
        resto = (digito1 % 11);
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        int soma2 = digito1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;
        int valor2 = (soma2 / 11) * 11;
        digito2 = soma2 - valor2;
        //Primeiro resto da divisão por 11.
        resto = (digito2 % 11);
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        //Conctenando os numeros
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + "." + String.valueOf(n3) + String.valueOf(n4) + String.valueOf(n5) + "." + String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8) + "/" + String.valueOf(n9) + String.valueOf(n10) + String.valueOf(n11) + String.valueOf(n12) + "-";
        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        numeroGerado = numerosContatenados + nDigResult;
        return numeroGerado;
    }

    /**
     * Confere o valor inexistente da TAG de um arquivo XML.<br>
     * <b>Exemplo:</b><br>
     * <code>conferirTagArquivoXmlInexistente("arquivo.xml",  "tag1", "tag2");</code>
     * 
     * @param arquivoXml Caminho do arquivo XML para conferir.
     * @param tags Caminho das TAGs com valor para conferir.
     */
    public static void conferirTagArquivoXmlInexistente(final String arquivoXml, final String... tags) {
        File arquivo = new File(ApplicationProperties.getReportGeneratedPath() + File.separator + arquivoXml);

        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(arquivo);

            final XPathFactory xPathfactory = XPathFactory.newInstance();
            final XPath xpath = xPathfactory.newXPath();

            final XPathExpression xPathExpression = xpath.compile(mountXPathExpression(tags));

            final NodeList nodeListResult = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

            if (nodeListResult.getLength() >= 1) {
                Assert.fail("O caminho de TAGs informado foi encontrado");
            }

        } catch (SAXException | ParserConfigurationException | XPathExpressionException e) {
            e.printStackTrace();
        } catch (final IOException fnf) {
            Assert.fail("O arquivo informado não foi encontrado: " + arquivoXml);
        }
    }

    /**
     * Salva arquivos de logs exibidos na tela F000EDI.<br>
     * <p>
     * O nome do arquivo será salvo no diretório de relatórios gerados, conforme "caminho.relatorios.gerados" definido
     * no framework.properties.<br>
     * <p>
     * O nome do arquivo será salvo com o nome padrão sugerido pelo sistema.
     * Posteriormente é possível conferir este relatório gerado, comparando-o com um arquivo gabarito.
     */
    public static void salvarArquivoTelaLog() {
        SistemaSenior.clicarMenu("Arquivo", "Salvar Como");
        SistemaSenior.salvarArquivo(TCBaseERP.obterCaminhoRelGerado(true));
        SistemaSenior.teclar(Tecla.ENTER);
        SistemaSenior.clicarMenu("Arquivo", "Salvar Como");
        SistemaSenior.salvarArquivo(TCBaseERP.obterCaminhoRelGerado(true) + "\\log.LOG");
        TCBaseERP.fecharTela(Form000EDIComponentNames.FR_NOME);
    }

    /**
     * Valida se existe uma mensagem sendo exibida em tela, utilizar para tratar exceções (como mensagens de pedido
     * bloqueado, por exemplo)
     * 
     * @param mensagemEsperada
     * @return
     */
    public static String validarSeExisteMensagem(String mensagemEsperada) {
        if (AccessibleSystem.getCommandManager().getApplication().hasMessage()) {
            final ITestMessage existeMensagem = AccessibleSystem.getCommandManager().getApplication().getMessage();
            if (existeMensagem.getDescription().equals(mensagemEsperada)) {
                TCBaseERPNucleo.getManipuladorDeComandos().getApplication().getMessage().click("&Ok");
            }
        }
        return mensagemEsperada;
    }

}
