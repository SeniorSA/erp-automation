package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.cadastros.controladoria.Teste001_GerarRegrasContabeis;
import senior.erp.testes.cadastros.financas.TCDAFI0001_CadastroDeIndice;

@RunWith(Suite.class)
@SuiteClasses({
	Teste001_GerarRegrasContabeis.class,
    TCDAFI0001_CadastroDeIndice.class
})
public class SuiteDeTestesCadastros {

}
