package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.agronegocio.Teste01_ContratoFixo;
import senior.erp.testes.agronegocio.Teste01_ContratoVariavel;


@RunWith(Suite.class)
@SuiteClasses({
	Teste01_ContratoFixo.class,
	Teste01_ContratoVariavel.class,
	
})
public class SuiteDeTestesAgronegocios {

}
