package senior.erp.testes.suprimentos.estoque;

import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.MetodosComuns;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class TCSEST0001_TransDepositoFilial {

	@BeforeClass
	public static void preExecucaoCasodeTeste() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
		MetodosComuns.selecionarEmpresaFilial(1, 1);
	}

	@AfterClass
	public static void posExecucaoCasodeTeste() {
		SistemaSenior.finalizarSistema();
	}

	@Before
	public void preExecucaoCenario() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	@After
	public void posExecucaoCenario() {
		MetodosComuns.reverterTransacao();
	}

	@Test
	public void testScenario01() {
		SistemaSenior.abrirTela("NF210TPA_SECE");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("ETnsSai", "90279");
		SistemaSenior.teclar(3, Tecla.TAB);
		// SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Sel.",
		// CaixaAtribuicao.DESMARCADO, "Produto", "");
		// SistemaSenior.clicar("btCustom_1");// OK
		SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Produto", "35046.0005", "Deriva��o", "001", "Dep.Origem",
				"D001-01", "Dep.Destino", "D001-02", "Documento", "0", "Lote Fab.", "", "Qtdade", "5", "Qtdade",
				"5,00000", "Usu.Res.", "147", "Cta. Financeira", "0");
		SistemaSenior.clicar("Processar");// &Processar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("Saldo");// Saldo Lot&e
		SistemaSenior.preencherLinhaCorrenteGrade("GridCsl", "Origem", "35", "Fam�lia", "35046", "Lote Fab.", "008/17",
				"Produto", "35046.0005", "Descri��o Produto", "ANTIBACTERIANO BORGAL 50ML - PADRAO", "Deriva��o", "001",
				"UM", "FR", "Dep�sito", "D001-01", "Saldo Lote", "180,00000", "Data Validade", "30/04/2019",
				"Data Fab. Lote", "31/05/2017", "Qtd. Reservada", "0,00000", "Qtd. Bloqueada", "0,00000",
				"Qtd.Res.Analise", "0,00000", "Qtd. Embalada", "0,00000", "Dimens�o 1", "0,00000", "Dimens�o 2",
				"0,00000", "Dimens�o 3", "0,00000", "Dimens�o 4", "0,00000", "Dimens�o 5", "0,00000", "Dimens�o 6",
				"0,00000", "Lote Original", "", "Grupo Estoque", "59457", "Grupo Produ��o", "", "Grupo Comercial",
				"C07", "Descri��o Dep�sito", "AREA DE VENDAS - RONDON", "Status Lote", "", "% Germina��o", "0,00",
				"% Pureza", "0,00", "% Umidade", "0,00", "Data Teste", "00/00/0000", "Endere�am.", "", "Safra", "",
				"Tratamento", "", "Categoria Lote", "", "Beneficiamento", "", "Peneira", "0", "Esp�cie/cultura", "0",
				"Cultivar", "0", "Termo Conformidade", "", "Atestado Ori. Gen�tica", "", "Cert. Sementes", "",
				"Boletim An�lise Sementes", "", "Amostra", "", "Lote de Fabricante", "", "Fabricante", "",
				"Lote Fabricante", "", "Data Valid. Fabr.", "00/00/0000", "C�d. Prod. Fabr.", "", "Marca", "",
				"C�d. Fis.", "7896185979360", "Descr. Fis.", "ANTIBACTERIANO BORGAL 50ML");
		SistemaSenior.clicar("Ok");// &OK
		SistemaSenior.clicar("btOK");// &OK
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
	}

}