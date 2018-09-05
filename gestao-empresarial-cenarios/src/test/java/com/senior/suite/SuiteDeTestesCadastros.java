package com.senior.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import senior.erp.testes.cadastros.financas.TCDAFI0001_CadastroDeIndice;

/**
 * @author roberto.debarba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    TCDAFI0001_CadastroDeIndice.class
})
public class SuiteDeTestesCadastros {

}
