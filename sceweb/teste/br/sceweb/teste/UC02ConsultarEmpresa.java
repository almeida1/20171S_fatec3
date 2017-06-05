package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.MySQLEmpresaDAO;

public class UC02ConsultarEmpresa {

	private static Empresa empresa ;
	private static MySQLEmpresaDAO empresaDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOFactory fabricaMySQL = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaMySQL.getEmpresaDAO();
		empresa = new Empresa();
		
		empresa.setNomeDaEmpresa("empresax");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresax");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("222");
		empresaDAO.adiciona(empresa);
	}
	@Test
	public void CT01UC02FBConsultar_empresa_com_sucesso() {
		assertTrue(empresa.equals(empresaDAO.consultaEmpresa("89424232000180")));
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}

}