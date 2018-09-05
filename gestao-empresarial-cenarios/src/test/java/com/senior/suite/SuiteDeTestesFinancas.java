package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.financas.contasPagar.contasPagar.TCFPCP0003_CompensacaoTCP;
import senior.erp.financas.contasPagar.contasPagar.TCFPCP0001_EntradaManutencaoTitulosCP;
import senior.erp.financas.contasPagar.contasPagar.TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque;
import senior.erp.financas.contasReceber.contasReceber.TCFRCR0007_InclusaoChequeApop;
import senior.erp.financas.contasReceber.contasReceber.TCFRCR0008_EntradaTitulosCR;

/**
 * @author roberto.debarba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	TCFRCR0007_InclusaoChequeApop.class,
	TCFRCR0008_EntradaTitulosCR.class,
	TCFPCP0003_CompensacaoTCP.class,
	TCFPCP0001_EntradaManutencaoTitulosCP.class,
	TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque.class,
	
})
public class SuiteDeTestesFinancas {

}
