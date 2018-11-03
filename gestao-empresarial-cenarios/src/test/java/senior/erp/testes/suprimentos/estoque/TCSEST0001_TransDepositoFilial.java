import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.TCBaseERPNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class TCSEST0001_TransDepositoFilial {
 
@BeforeClass 
public static void setUpClass(){ 
try {
	SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.X);
} catch (Exception e) {
	if (e.getMessage().contains("Timeout")) {
		SistemaSenior.finalizarSistema();
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.X);
	}
}
TCBaseERPNucleo.selecionarEmpresaFilial(EMPRESA, FILIAL);
} 

@AfterClass 
public static void tearDownClass(){ 
SistemaSenior.finalizarSistema();
} 

@Before 
public void setUp(){ 
SistemaSeniorComTransacao.iniciarTransacao();
} 

@After 
public void tearDown(){ 
TCBaseERPNucleo.reverterTransacao(SystemName.SAPIENS, SystemUsers.X);
} 

@Test 
public void testScenario01(){ 
SistemaSenior.abrirTela("NF210TPA_SECE");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.preencherCampo("ETnsSai", "90279");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Sel.",CaixaAtribuicao.DESMARCADO,"Produto","35046.0008");
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Produto","35046.0005","Derivação","001","Dep.Origem","D001-01","Dep.Destino","D001-02","Documento","0","Lote Fab.","","Qtdade","2","Qtdade","2,00000");
SistemaSenior.clicar("Processar");//&Processar
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.preencherLinhaCorrenteGrade("GridDist", "Lote Fab.","");
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.clicar("Saldo");//Saldo Lot&e
SistemaSenior.posicionarLinhaGradePorValor("GridCsl","Origem","35","Família","35046","Lote Fab.","008/17","Produto","35046.0005","Descrição Produto","ANTIBACTERIANO BORGAL 50ML - PADRAO","Derivação","001","UM","FR","Depósito","D001-01","Saldo Lote","193,00000","Data Validade","30/04/2019","Data Fab. Lote","31/05/2017","Qtd. Reservada","0,00000","Qtd. Bloqueada","0,00000","Qtd.Res.Analise","0,00000","Qtd. Embalada","0,00000","Dimensão 1","0,00000","Dimensão 2","0,00000","Dimensão 3","0,00000","Dimensão 4","0,00000","Dimensão 5","0,00000","Dimensão 6","0,00000","Lote Original","","Grupo Estoque","59457","Grupo Produção","","Grupo Comercial","C07","Descrição Depósito","AREA DE VENDAS - RONDON","Status Lote","","% Germinação","0,00","% Pureza","0,00","% Umidade","0,00","Data Teste","00/00/0000","Endereçam.","","Safra","","Tratamento","","Categoria Lote","","Beneficiamento","","Peneira","0","Espécie/cultura","0","Cultivar","0","Termo Conformidade","","Atestado Ori. Genética","","Cert. Sementes","","Boletim Análise Sementes","","Amostra","","Lote de Fabricante","","Fabricante","","Lote Fabricante","","Data Valid. Fabr.","00/00/0000","Cód. Prod. Fabr.","","Marca","","Cód. Fis.","7896185979360","Descr. Fis.","ANTIBACTERIANO BORGAL 50ML");
SistemaSenior.clicar("Ok");//&OK
SistemaSenior.clicar("btOK");//&OK
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.fecharTela("F210TPA");
SistemaSenior.abrirTela("MFSelFil");
SistemaSenior.clicar("BtnOk");//&Ok
SistemaSenior.abrirTela("NF210TPA_SECE");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.preencherCampo("ETnsSai", "90279");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Sel.",CaixaAtribuicao.DESMARCADO,"Produto","35046.0005","Derivação","001","Dep.Origem","D001-01","Dep.Destino","D001-02","Documento","0");
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Lote Fab.","","Qtdade","0,00000");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.preencherLinhaCorrenteGrade("GridOri", "Qtdade","1","Qtdade","1,00000");
SistemaSenior.clicar("Processar");//&Processar
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.preencherLinhaCorrenteGrade("GridDist", "Lote Fab.","");
SistemaSenior.clicar("Saldo");//Saldo Lot&e
SistemaSenior.posicionarLinhaGradePorValor("GridCsl","Origem","35","Família","35046","Lote Fab.","008/17","Produto","35046.0005","Descrição Produto","ANTIBACTERIANO BORGAL 50ML - PADRAO","Derivação","001","UM","FR","Depósito","D001-01","Saldo Lote","193,00000","Data Validade","30/04/2019","Data Fab. Lote","31/05/2017","Qtd. Reservada","0,00000","Qtd. Bloqueada","0,00000","Qtd.Res.Analise","0,00000","Qtd. Embalada","0,00000","Dimensão 1","0,00000","Dimensão 2","0,00000","Dimensão 3","0,00000","Dimensão 4","0,00000","Dimensão 5","0,00000","Dimensão 6","0,00000","Lote Original","","Grupo Estoque","59457","Grupo Produção","","Grupo Comercial","C07","Descrição Depósito","AREA DE VENDAS - RONDON","Status Lote","","% Germinação","0,00","% Pureza","0,00","% Umidade","0,00","Data Teste","00/00/0000","Endereçam.","","Safra","","Tratamento","","Categoria Lote","","Beneficiamento","","Peneira","0","Espécie/cultura","0","Cultivar","0","Termo Conformidade","","Atestado Ori. Genética","","Cert. Sementes","","Boletim Análise Sementes","","Amostra","","Lote de Fabricante","","Fabricante","","Lote Fabricante","","Data Valid. Fabr.","00/00/0000","Cód. Prod. Fabr.","","Marca","","Cód. Fis.","7896185979360","Descr. Fis.","ANTIBACTERIANO BORGAL 50ML");
SistemaSenior.posicionarLinhaGradePorValor("GridCsl","Origem","35","Família","35046","Lote Fab.","008/17","Produto","35046.0005","Descrição Produto","ANTIBACTERIANO BORGAL 50ML - PADRAO","Derivação","001","UM","FR","Depósito","D001-01","Saldo Lote","193,00000","Data Validade","30/04/2019","Data Fab. Lote","31/05/2017","Qtd. Reservada","0,00000","Qtd. Bloqueada","0,00000","Qtd.Res.Analise","0,00000","Qtd. Embalada","0,00000","Dimensão 1","0,00000","Dimensão 2","0,00000","Dimensão 3","0,00000","Dimensão 4","0,00000","Dimensão 5","0,00000","Dimensão 6","0,00000","Lote Original","","Grupo Estoque","59457","Grupo Produção","","Grupo Comercial","C07","Descrição Depósito","AREA DE VENDAS - RONDON","Status Lote","","% Germinação","0,00","% Pureza","0,00","% Umidade","0,00","Data Teste","00/00/0000","Endereçam.","","Safra","","Tratamento","","Categoria Lote","","Beneficiamento","","Peneira","0","Espécie/cultura","0","Cultivar","0","Termo Conformidade","","Atestado Ori. Genética","","Cert. Sementes","","Boletim Análise Sementes","","Amostra","","Lote de Fabricante","","Fabricante","","Lote Fabricante","","Data Valid. Fabr.","00/00/0000","Cód. Prod. Fabr.","","Marca","","Cód. Fis.","7896185979360","Descr. Fis.","ANTIBACTERIANO BORGAL 50ML");
SistemaSenior.posicionarLinhaGradePorValor("GridCsl","Origem","35","Família","35046","Lote Fab.","008/17","Produto","35046.0005","Descrição Produto","ANTIBACTERIANO BORGAL 50ML - PADRAO","Derivação","001","UM","FR","Depósito","D001-01","Saldo Lote","193,00000","Data Validade","30/04/2019","Data Fab. Lote","31/05/2017","Qtd. Reservada","0,00000","Qtd. Bloqueada","0,00000","Qtd.Res.Analise","0,00000","Qtd. Embalada","0,00000","Dimensão 1","0,00000","Dimensão 2","0,00000","Dimensão 3","0,00000","Dimensão 4","0,00000","Dimensão 5","0,00000","Dimensão 6","0,00000","Lote Original","","Grupo Estoque","59457","Grupo Produção","","Grupo Comercial","C07","Descrição Depósito","AREA DE VENDAS - RONDON","Status Lote","","% Germinação","0,00","% Pureza","0,00","% Umidade","0,00","Data Teste","00/00/0000","Endereçam.","","Safra","","Tratamento","","Categoria Lote","","Beneficiamento","","Peneira","0","Espécie/cultura","0","Cultivar","0","Termo Conformidade","","Atestado Ori. Genética","","Cert. Sementes","","Boletim Análise Sementes","","Amostra","","Lote de Fabricante","","Fabricante","","Lote Fabricante","","Data Valid. Fabr.","00/00/0000","Cód. Prod. Fabr.","","Marca","","Cód. Fis.","7896185979360","Descr. Fis.","ANTIBACTERIANO BORGAL 50ML");
SistemaSenior.clicar("Ok");//&OK
SistemaSenior.clicar("btOK");//&OK
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
} 

 
}