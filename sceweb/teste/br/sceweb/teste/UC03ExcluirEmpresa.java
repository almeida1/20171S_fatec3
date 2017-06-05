package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.IEmpresaDAO;


public class UC03ExcluirEmpresa {
	private static Empresa empresa ;
	private static IEmpresaDAO empresaDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaDAO.getEmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresax");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresax");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("222");
		empresaDAO.adiciona(empresa);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void CT01UC03Excluir_empresa_com_sucesso() {
		assertEquals(1,empresaDAO.exclui("89424232000180"));
	}
	@Test
	public void CT01UC03Excluir_empresa_cnpj_invalido() {
		assertEquals(0,empresaDAO.exclui("8942423200018"));
	}
}
