package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	SuiteDeTestesCadastros.class,
	SuiteDeTestesControladoria.class,
	SuiteDeTestesFinancas.class,
	SuiteDeTestesMercado.class,
	SuiteDeTestesSuprimentos.class,
	SuiteDeTestesAgronegocios.class
})


public class ExecutarTodosOsTestes {

}
