package senior.erp;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ReexecutarTeste {

    public static class Reexecutar implements TestRule {

        private static final String TIME_OUT = "Time out";
        private static final String NULLPOINTER = "NullPointerException";
        private static final String READ_TIMED_OUT = "Read timed out";
        private static final String JAVA_LANG_NULLPOINTER = "java.lang.NullPointerException";
        private static final String APLICACAO_NAO_ESTA_CONECTADA = "Aplicação não está conectada.";
        private static final String EXCEDEU_LIMITE_TIMEOUT = "Evento excedeu limite de Timeout (160000).";
        private static final String THERE_IS_NO_ACTIVE_TRANSACTION = "There is no active transaction";

        public int contadorReexecucao;

        public Reexecutar(int contadorReexecucao) {
            this.contadorReexecucao = contadorReexecucao;
        }

        public Statement apply(Statement base, Description description) {
            return statement(base, description);
        }

        public Statement statement(final Statement base, final Description description) {
            return new Statement() {

                @Override
                public void evaluate() throws Throwable {
                    Throwable caughtThrowable = null;

                    for (int i = 0; i < contadorReexecucao; i++) {
                        try {
                            base.evaluate();
                            return;
                        } catch (Throwable t) {
                            caughtThrowable = t;
                            if (caughtThrowable.getMessage().contains(TIME_OUT) || caughtThrowable.getMessage().contains(NULLPOINTER) || caughtThrowable.getMessage().contains(READ_TIMED_OUT) || caughtThrowable.getMessage().contains(JAVA_LANG_NULLPOINTER) || caughtThrowable.getMessage().contains(APLICACAO_NAO_ESTA_CONECTADA) || caughtThrowable.getMessage().contains(EXCEDEU_LIMITE_TIMEOUT) || caughtThrowable.getMessage().contains(THERE_IS_NO_ACTIVE_TRANSACTION)) {
                                System.err.println(description.getDisplayName() + ": reexecutando " + (i + 1) + " falha");
                            } else {
                                throw caughtThrowable;
                            }
                        }
                    }
                    System.err.println(description.getDisplayName() + ": tentativa de reexecucao finalizada apos " + contadorReexecucao + " falhas");
                    throw caughtThrowable;
                }
            };
        }
    }

    /**
     * Para reexecutar o cenário, deve existir a anotação @Rule dentro do caso de testes desejado, conforme abaixo:
     * 
     * @Rule
     *       public Reexecutar reexecutar = new Reexecutar(3);
     */

}
