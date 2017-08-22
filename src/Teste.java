import java.util.Calendar;

import br.com.caelum.exception.DaoException;
import br.com.caelum.exception.JdbcConnectionException;
import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.GenericDao;
import br.com.caelum.jdbc.dao.IGenericDao;
import br.com.caelum.tarefas.modelo.Contato;
import br.com.caelum.tarefas.modelo.Tarefa;

public class Teste {

	public static void main(String[] args) throws JdbcConnectionException, DaoException {


		IGenericDao<Tarefa> dao = new GenericDao<Tarefa>(ConnectionFactory.getInstance());
		
		Tarefa tarefa = new Tarefa();

		tarefa.setDescricao("teste6");
		tarefa.setFinalizado(true);
		Calendar data = Calendar.getInstance();
		tarefa.setDataFinalizacao(data);
		
		dao.save(tarefa);
		
		IGenericDao<Contato> daoC = new GenericDao<Contato>(ConnectionFactory.getInstance());
		
		Contato contato = new Contato();
		
		contato.setNome("Francisco Neto");
		contato.setEndereco("Algum local");
		contato.setEmail("teste@teste.com");
		contato.setDataNascimento(Calendar.getInstance());
		
		daoC.save(contato);
		
	}

}
