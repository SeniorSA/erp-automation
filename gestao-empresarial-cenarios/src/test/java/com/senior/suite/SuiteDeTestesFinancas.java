package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.financas.cobrancaEscritural.TCFRCRP0001_RemessaCR;
import senior.erp.testes.financas.contasPagar.TCFPCP0001_EntradaManutencaoTitulosCP;
import senior.erp.testes.financas.contasPagar.TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque;
import senior.erp.testes.financas.contasPagar.TCFPCP0003_CompensacaoTCP;
import senior.erp.testes.financas.contasReceber.TCFRCR0007_InclusaoChequeApop;
import senior.erp.testes.financas.contasReceber.Teste001_EntradaManual;
import senior.erp.testes.financas.contasReceber.Teste002_AlteracaoVencimentoCR;
import senior.erp.testes.financas.contasReceber.Teste008_EntradaTitulosCR;


@RunWith(Suite.class)
@SuiteClasses({
	TCFRCRP0001_RemessaCR.class,
	TCFPCP0001_EntradaManutencaoTitulosCP.class,
	TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque.class,
	TCFPCP0003_CompensacaoTCP.class,
	Teste001_EntradaManual.class,
	Teste002_AlteracaoVencimentoCR.class,
	TCFRCR0007_InclusaoChequeApop.class,
	Teste008_EntradaTitulosCR.class,
})
public class SuiteDeTestesFinancas {

}
