package senior.erp.exemplosTestes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.ReexecutarTeste.Reexecutar;
import senior.erp.SystemMessageTitles;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.TCBaseERP;
import senior.erp.TCBaseERPNucleo;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;
import senior.erp.utils.FacadeSuprimentos;

/**
 * Cenário que trata o assunto XYZ.
 * 
 * Pré-condições: XYZ.
 *
 */
public class TCSREB0001_ExemploCasoDeTeste {

    @Rule
    public Reexecutar reexecutar = new Reexecutar(3);

    /**
     * Inicializa sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        try {
            SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
        } catch (Exception e) {
            if (e.getMessage().contains("Timeout")) {
                SistemaSenior.finalizarSistema();
                SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
            }
        }
    }

    /**
     * Finaliza sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transação dos cenários.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação dos cenários.
     */
    @After
    public void tearDown() {
        TCBaseERPNucleo.reverterTransacao(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS);
    }

    /**
     * Cenário destinado ao assunto XYZ.
     */
    @Test
    public void testScenario0001_ExemploTeste() {
        TCBaseERPNucleo.selecionarEmpresaFilial(170, 1);

		SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "CEN-0001", Tecla.TAB);
		SistemaSenior.selecionarItem(Form435CCCComponentNames.RG_DOCUMENTO_EMITIR, "&Nota Fiscal");
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_NOME_MOTORISTA, "JOSE", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_FORNECEDOR, "333", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DEPOSITO, "1", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PRODUTO, "CEV0001", Tecla.TAB);
		SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_DERIVACAO, "0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_SAFRA, "2014", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90409", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_MOSTRAR);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "11000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "6000", Tecla.TAB);
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_CONTRATOS, "Nº Interno", "14");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_CONTRATOS, "Sel.", CaixaAtribuicao.MARCADO);
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Contrato", "14", "Fornecedor Part.", "333");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Produção própria", "S", "Chave NFP-e", "42171148254353000144551230000006941875738151", "Emissão", SistemaSenior.dataAtual());
        SistemaSenior.posicionarLinhaGradePorValor(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Contrato", "14", "Fornecedor Part.", "334");
        SistemaSenior.preencherLinhaCorrenteGrade(Form435CCCComponentNames.GD_FORNECEDORES_PARTICIPANTES, "Produção própria", "S", "Chave NFP-e", "42171148254353000144551230000006931875738151", "Emissão", SistemaSenior.dataAtual());
        FacadeSuprimentos.processarEntradaSaidaBalanca(true);

        SistemaSenior.abrirTela(Form435CCCComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PLACA, "CEN-0001", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_PESO, "1000", Tecla.TAB);
        SistemaSenior.preencherCampo(Form435CCCComponentNames.FD_TRANSACAO, "90409", Tecla.TAB);
        SistemaSenior.clicar(Form435CCCComponentNames.BT_PROCESSAR);
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_CONFIRMACAO, "Confirma processamento?", "Sim");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_ADVERTENCIA, "Processado com sucesso!", "OK");
        SistemaSenior.conferirCaixaMensagem(SystemMessageTitles.MS_AVISO, "Nenhum modelo de relatório ligado a esta tela, ou todo(s) o(s) modelos ligados estão inativos.", "OK");
        TCBaseERP.fecharTela(Form435CCCComponentNames.FR_NOME);
        //Validações
    }

}
