package senior.erp.testes.controladoria.contabilidade;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.controladoria.Form640LOTComponentNames;

/**
 * 
 * <b>Pré-condições:</b><br>
 * Utilizar a base: SAPIENSTESTE <br>
 * Usuário e senha: carlos.eduardo/wurscke <br>
 * Empresa e filial: 1/1 <br>
 * Proprietária: padrão 
 * 
 * @author carlos.eduardo
 */
public class TCCCCT0001_Lotes {

	@BeforeClass
	public static void preExecucaoCasodeTeste() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.WURSCHKE);
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
	
	// Testar a inclusão manual de um lote
	@Test
	public void teste0001_CadastramentoLotes(){
		SistemaSenior.abrirTela(Form640LOTComponentNames.FR_LOTES);
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_NUMERO_LOTE, "0", Tecla.TAB);
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_FILIAL, "1", Tecla.TAB); 
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_DATA_LOTE, "27/06/2017", Tecla.TAB); 		
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_DATA_FIXA, "S", Tecla.TAB); 
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_TOTAL, "100,00", Tecla.TAB);
		SistemaSenior.preencherCampo(Form640LOTComponentNames.FD_DESCRICAO, "Cadastro de Lotes", Tecla.TAB); 
		SistemaSenior.clicar(Form640LOTComponentNames.BT_PROCESAR);		
		SistemaSenior.fecharTela(Form640LOTComponentNames.FR_LOTES);
		
		SistemaSeniorComTransacao.executarSQLQuery("select 1 from E640LOT "
				+ "where CODEMP = '1' "
				+ "  and TIPLCT = '1' "
				+ "  and ORILCT = 'MAN' "
				+ "  and CODFIL = '1' "
				+ "  and DATLOT = ? "
				+ "  and DATFIX = 'S' "
				+ "  and DESLOT = 'Cadastro de Lotes' "
				+ "  and TOTDEB = '0' "
				+ "  and TOTCRE = '0' "
				+ "  and TOTINF = '100' "
				+ "  and TOTLCT = '0' "
				+ "  and USULOT = '5'"
				+ "  and CODUSU = '5' "
				+ "  and SITLOT = '1'", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, "27/06/2017"));
	}

}
