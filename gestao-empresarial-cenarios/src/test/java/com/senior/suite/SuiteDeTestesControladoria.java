package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.controladoria.contabilidade.TCCCCT0001_Lotes;

/**
 * @author roberto.debarba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	TCCCCT0001_Lotes.class,
	
})
public class SuiteDeTestesControladoria {

}
