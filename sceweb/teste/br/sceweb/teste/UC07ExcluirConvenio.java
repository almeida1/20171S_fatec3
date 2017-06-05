package br.sceweb.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Convenio;
import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.MySQLConvenioDAO;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.IConvenioDAO;
import br.sceweb.modelo.IEmpresaDAO;
import br.sceweb.modelo.MySQLEmpresaDAO;

public class UC07ExcluirConvenio {
	static IConvenioDAO convenioDAO;
	static Convenio convenio;
	static IEmpresaDAO empresaDAO;
	static Empresa empresa;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaDAO.getEmpresaDAO();
		convenioDAO = fabricaDAO.getConvenioDAO();
		empresa = new Empresa();
		
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("81965361000174");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
		empresaDAO.adiciona(empresa);
		convenio = new Convenio("81965361000174","03/05/2016", "20/05/2016");
		convenioDAO.adiciona(convenio);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("81965361000174");
	}

	@Test
	public void CT01UC07ExcluirConvenio_com_sucesso() {
		assertEquals(1,convenioDAO.exclui("81965361000174"));
	}

}
