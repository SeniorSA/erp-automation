package senior.erp;

import br.com.senior.asas.testinterfaces.ITestApplication;
import br.com.senior.asas.testinterfaces.ITestComponent;
import br.com.senior.asas.testinterfaces.ITestContainer;
import br.com.senior.asas.testinterfaces.ITestWindow;

import com.senior.framework.testes.executor.CommandManager;

/**
 * Representação da janela ativa no momento em que é instanciado
 * 
 * @author Marlo.back
 * 
 */
public class JanelaAtiva {

	ITestContainer container;
	ITestApplication app;

	String nome;
	String titulo;
	String descricao;

	/**
	 * 
	 */
	public JanelaAtiva() {

		CommandManager commandManager = TCBaseERPNucleo.getManipuladorDeComandos();
		container = commandManager.getActiveContainer();
		app = commandManager.getApplication();

		nome = container.getName();

		titulo = (container instanceof ITestWindow) ? ((ITestWindow) container)
				.getWindowTitle() : commandManager.getActiveSheet().getTitle();
		descricao = app.hasMessage() ? app.getMessage().getDescription() : "";
	}

	/**
	 * Nome da janela (formname)
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Título da janela (caption)
	 * 
	 * @return título
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Descrição/mensagem no corpo do diálogo (somente para derivados de messageBox)
	 * 
	 * @return descrição
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Retorna o componente do controle
	 * 
	 * @param nome1 nome1
	 * @return container.getChild(nome1);
	 */
	public ITestComponent getFilho(String nome1) {
		return container.getChild(nome1);
	}

	/**
	 * Retorna se é um diálogo de confirmação de sáida da tela
	 * 
	 * @return confirmacao
	 */
	public boolean isJanelaConfirmacaoSair() {
		boolean confirmacao = nome.equals("FrmMessage") && titulo.equals("Confirmação");

		confirmacao = confirmacao && (descricao.startsWith("Deseja") || // "Deseja (realmente|abandonar) (fechar|sair)"
				descricao.startsWith("Abandonar") // "Abondonar alterações...
				);

		return confirmacao;

	}

	@Override
    public String toString() {
		return String.format("Janela ativa: '%s' - '%s'\nDescrição: '%s'", nome, titulo, descricao);
	}

}
