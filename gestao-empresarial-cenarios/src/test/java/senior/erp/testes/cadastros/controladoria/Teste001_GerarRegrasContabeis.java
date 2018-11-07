import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.TCBaseERPNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste001_GerarRegrasContabeis {
 
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
} 

 

@Test 
public void testScenario01(){ 
} 

}
