package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//teste
@RunWith(Suite.class)
@SuiteClasses({
	SuiteDeTestesCadastros.class,
	SuiteDeTestesControladoria.class,
	SuiteDeTestesFinancas.class,
	SuiteDeTestesMercado.class,
	SuiteDeTestesSuprimentos.class
})


public class ExecutarTodosOsTestes {

}
