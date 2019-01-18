package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.controladoria.contabilidade.Teste001_Lotes;


@RunWith(Suite.class)
@SuiteClasses({
	Teste001_Lotes.class,
	
})
public class SuiteDeTestesControladoria {

}
