package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.financas.cobrancaEscritural.TCFRCRP0001_RemessaCR;
import senior.erp.testes.financas.contasPagar.TCFPCP0001_EntradaManutencaoTitulosCP;
import senior.erp.testes.financas.contasPagar.TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque;
import senior.erp.testes.financas.contasPagar.TCFPCP0003_CompensacaoTCP;
import senior.erp.testes.financas.contasReceber.TCFRCR0001_EntradaManual;
import senior.erp.testes.financas.contasReceber.TCFRCR0002_AlteracaoVencimentoCR;
import senior.erp.testes.financas.contasReceber.TCFRCR0007_InclusaoChequeApop;
import senior.erp.testes.financas.contasReceber.TCFRCR0008_EntradaTitulosCR;

/**
 * @author roberto.debarba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	TCFRCRP0001_RemessaCR.class,
	TCFPCP0001_EntradaManutencaoTitulosCP.class,
	TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque.class,
	TCFPCP0003_CompensacaoTCP.class,
	TCFRCR0001_EntradaManual.class,
	TCFRCR0002_AlteracaoVencimentoCR.class,
	TCFRCR0007_InclusaoChequeApop.class,
	TCFRCR0008_EntradaTitulosCR.class,
})
public class SuiteDeTestesFinancas {

}
