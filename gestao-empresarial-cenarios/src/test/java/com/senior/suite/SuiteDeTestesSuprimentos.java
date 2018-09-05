package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.suprimentos.entradaBalanca.TCSREB0002_EntradaBalancaViaOrdemCompraAGRO2;
import senior.erp.testes.suprimentos.faturamentoDoLeite.TCSUP0001_ValidarNotaLeite;
import senior.erp.testes.suprimentos.notasFiscaisEntrada.TCSRNF0015_NotasFiscaisEntradaAgrupada;
import senior.erp.testes.suprimentos.solicitacaoCompra.TCSCSC0001_SolicitacaoCompraProduto;

/**
 * @author roberto.debarba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	TCSREB0002_EntradaBalancaViaOrdemCompraAGRO2.class,
	TCSUP0001_ValidarNotaLeite.class,
	TCSRNF0015_NotasFiscaisEntradaAgrupada.class,
	TCSCSC0001_SolicitacaoCompraProduto.class,
})
public class SuiteDeTestesSuprimentos {

}
