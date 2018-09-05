package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.mercado.notaFiscalSaida.TCRFNFPRE0001_NFSaidaIndiv;
import senior.erp.testes.mercado.pedidos.TCRVPE0001_PedidoAgrupadoPED;

/**
 * @author marcel.fortunato
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	TCRFNFPRE0001_NFSaidaIndiv.class,
	TCRVPE0001_PedidoAgrupadoPED.class,
    })

public class SuiteDeTestesMercado {
    
}
