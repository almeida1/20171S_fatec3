package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.MySQLEmpresaDAO;
import br.sceweb.servico.ConfiguraDB;
import br.sceweb.servico.FabricaDeConexoes;

public class UC01CadastrarEmpresa {
	
	static MySQLEmpresaDAO empresaDAO;
	static Empresa empresa;
	static ConfiguraDB configuraDB;
	static FabricaDeConexoes fabricaDeConexoes;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOFactory fabricaMySQL = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaMySQL.getEmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	@Before
	public void setUp() throws Exception {
		empresaDAO.exclui("89424232000180");
	}

	@Test
	public void CT01UC01FBCadastra_com_sucesso() {
		assertEquals(1, empresaDAO.adiciona(empresa));

	}

	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastra_cnpj_ja_cadastrado() {
		empresaDAO.adiciona(empresa);
		empresaDAO.adiciona(empresa);
	}

	@Test
	public void CT03UC01A3Cadastra_empresa_cnpj_invalido() {
		Empresa empresa2 = new Empresa();
		try {
			empresa2.setCnpj("8942423200018");
			fail("deveria disparar uma expection");
		} catch (Exception e) {
			assertEquals("CNPJ inválido!", e.getMessage());
		}
	}

	@Test
	public void CT04UC01A4Cadastra_empresa_sem_nome() {
		Empresa empresa3 = new Empresa();
		try {
			empresa3.setNomeDaEmpresa("");
			fail("deveria disparar uma expection");
		} catch (Exception e) {
			assertEquals("nome da empresa inválido!", e.getMessage());
		}
	}

	@Test
	public void CT05_conecta_db_com_sucesso() {
		String url = "jdbc:mysql://localhost/sceweb";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "";
		configuraDB = new ConfiguraDB(url, driver, usuario, senha);
		fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
		assertNotNull(fabricaDeConexoes.getConnection());
	}

	@Test
	public void CT06_conecta_db_com_erro_url() {
		String url = "jdbc:mysql://localhost/sceweb1";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "";
		configuraDB = new ConfiguraDB(url, driver, usuario, senha);
		fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
		try {
			fabricaDeConexoes.getConnection();
		} catch (Exception e) {
			assertEquals("com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database 'sceweb1'", e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}

}
