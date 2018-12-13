import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.TCBaseERPNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste004_BaixaDeTitulos {
 
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
SistemaSenior.abrirTela("NF301BCD_FRCR");
SistemaSenior.teclar(3, Tecla.TAB);
SistemaSenior.preencherCampo("ECodCli", "105.713");
SistemaSenior.clicar("Mostrar");//&Mostrar
SistemaSenior.posicionarLinhaGradePorValor("GridBai","Sel.",CaixaAtribuicao.DESMARCADO,"Filial","1","Cliente","105.713","Título","1118514-01","Tipo ","DUP","Vencimento ","07/12/2018","Forma Pgto","0","Vlr.Aberto","485,00","Vlr.a Baixar","485,00","Vlr.Juros","3,96","Vlr.Multa","0,00","Encargos","0,00","Correção","0,00","Acréscimos","0,00","Vlr.Desconto","0,00","Outros Desc","0,00","Líquido","488,96","Rep.","1","Vlr.Comissão","0,00","Vlr. Base Comissão","0,00","Sit","AB","Trans","90301","Descrição","Entrada de título por Venda a Prazo","Dat.Lib.","12/12/2018","Dia Atr","5","Dia Jrs","5","Portador","UN01","Carteira","99","Moeda","001","Dat. Entrada","24/10/2018","Provável Pgt.","07/12/2018","Sacado","00000000000000","Fórmula Reaj.","","Natureza","0","Observação","","Cta. Fin.","0","Cta. Ctb. Red.","10.180","C. Custo","");
SistemaSenior.posicionarLinhaGradePorValor("GridBai","Sel.",CaixaAtribuicao.DESMARCADO,"Filial","1","Cliente","105.713","Título","1118514-01","Tipo ","DUP","Vencimento ","07/12/2018","Forma Pgto","0","Vlr.Aberto","485,00","Vlr.a Baixar","485,00","Vlr.Juros","3,96","Vlr.Multa","0,00","Encargos","0,00","Correção","0,00","Acréscimos","0,00","Vlr.Desconto","0,00","Outros Desc","0,00","Líquido","488,96","Rep.","1","Vlr.Comissão","0,00","Vlr. Base Comissão","0,00","Sit","AB","Trans","90301","Descrição","Entrada de título por Venda a Prazo","Dat.Lib.","12/12/2018","Dia Atr","5","Dia Jrs","5","Portador","UN01","Carteira","99","Moeda","001","Dat. Entrada","24/10/2018","Provável Pgt.","07/12/2018","Sacado","00000000000000","Fórmula Reaj.","","Natureza","0","Observação","","Cta. Fin.","0","Cta. Ctb. Red.","10.180","C. Custo","");
SistemaSenior.preencherLinhaCorrenteGrade("GridBai", "Sel.",CaixaAtribuicao.MARCADO);
SistemaSenior.selecionarGuia("Orelhas", "Cheques");
SistemaSenior.preencherNovaLinhaGrade("GridChe", "Cliente","105713","Cliente","105.713","Filial","1","Banco","104","Agência","0968","Conta","33455","Cheque","12345","Valor","500","Valor","500,00","CNPJ/CPF Sacado","24102954953","CNPJ/CPF Sacado","00024102954953","Vencimento","17/12/2018");
SistemaSenior.posicionarLinhaGradePorValor("GridChe","Cliente","105.713","Filial","1","Banco","104","Agência","0968","Conta","33455","Cheque","12345","Valor","500,00","CNPJ/CPF Sacado","00024102954953","Vencimento","17/12/2018","Provável Pgt.","17/12/2018","Dias Comp.","5","Taxa Deflação","0,0000","Valor Deflação","0,00","Líquido","500,00","Forma Pgto.","0","Portador","UN01","Carteira","99","Título","10412345","Tipo","CHQ","Trans.","90309","Descrição","Entrada de título de cheque/cartao","Código Barras","","Observação","");
SistemaSenior.clicar("Selecao");//S&eleção
SistemaSenior.preencherCampo("ECtaDpr", "01CAIXA 01");
SistemaSenior.teclar(Tecla.TAB);
SistemaSenior.clicar("Ok");//&Ok
SistemaSenior.clicar("Processar");//&Processar
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.clicar("button2");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.fecharTela("F301BCD");
} 

 
}