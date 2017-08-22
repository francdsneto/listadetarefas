import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.GenericDao;
import br.com.caelum.tarefas.modelo.Tarefa;

public class Teste {

	public static void main(String[] args) {


		GenericDao dao = new GenericDao(ConnectionFactory.getInstance());

		dao.setClasse(Tarefa.class);
		
		Tarefa tarefa = new Tarefa();

		tarefa.setDescricao("teste3");
		tarefa.setFinalizado(true);
		Calendar data = Calendar.getInstance();
		tarefa.setDataFinalizacao(data);
		
		dao.save(tarefa);
		
	}

}
