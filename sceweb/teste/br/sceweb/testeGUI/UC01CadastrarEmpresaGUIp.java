package br.sceweb.testeGUI;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.IEmpresaDAO;
import br.sceweb.modelo.MySQLEmpresaDAO;

@RunWith(Parameterized.class)
public class UC01CadastrarEmpresaGUIp {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private String cnpj;
	private String nomeDaEmpresa;
	private String nomeFantasia;
	private String endereco;
	private String telefone;
	private String responsavel;
	private String telefoneResponsavel;
	private String setor;
	private String email;
	private String resultadoEsperado;
	
	public UC01CadastrarEmpresaGUIp(String n, String c, String f, String e, String t, String r, String tr, String s, String em, String re){
		this.cnpj = c;
		this.nomeDaEmpresa = n;
		this.nomeFantasia = f;
		this.endereco = e;
		this.telefone = t;
		this.responsavel = r;
		this.telefoneResponsavel = tr;
		this.setor = s;
		this.email = em;
		this.resultadoEsperado = re;
	}
	  @Parameters
	    public static Collection data() {
	        return Arrays.asList(new Object[][] { 
	        	{"Open Informatica Ltda","50658639000137","Open Informatica","Rua Aguia de Haia, 2432","121212","jose carlos","111221","contabilidade","jose@gmail.com","cadastro realizado com sucesso"},
	        	{"Nova Empresa Ltda","56241412000178","Nova Empresa","Rua Taquari, 2222","121212","maira silva","111221","faturamento","jose@gmail.com","cadastro realizado com sucesso"},
	        	{"Nova Empresa Ltda","","Nova Empresa","Rua Taquari, 2222","121212","maira silva","111221","faturamento","jose@gmail.com", "CNPJ inválido!"},
	        	
	        });
	    }
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "WebContent/WEB-INF/lib/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


	}

	@Test
	public void testUC01CadastrarEmpresaGUI() throws Exception {
		driver.get(baseUrl + "/sceweb/visao/FormEmpresa.jsp");
		driver.findElement(By.id("campo")).clear();
		driver.findElement(By.id("campo")).sendKeys(nomeDaEmpresa);
		driver.findElement(By.name("txtCNPJ")).clear();
		driver.findElement(By.name("txtCNPJ")).sendKeys(cnpj);
		driver.findElement(By.name("txtNomeFantasia")).clear();
		driver.findElement(By.name("txtNomeFantasia")).sendKeys(nomeFantasia);
		driver.findElement(By.name("txtEndereco")).clear();
		driver.findElement(By.name("txtEndereco")).sendKeys(endereco);
		driver.findElement(By.name("txtTelefone")).clear();
		driver.findElement(By.name("txtTelefone")).sendKeys("telefone");
		driver.findElement(By.name("txtResponsavel")).clear();
		driver.findElement(By.name("txtResponsavel")).sendKeys(responsavel);
		driver.findElement(By.name("txtTelefoneResponsavel")).clear();
		driver.findElement(By.name("txtTelefoneResponsavel")).sendKeys(telefoneResponsavel);
		driver.findElement(By.name("txtSetor")).clear();
		driver.findElement(By.name("txtSetor")).sendKeys(setor);
		driver.findElement(By.name("txtEmail")).clear();
		driver.findElement(By.name("txtEmail")).sendKeys(email);
		driver.findElement(By.id("botao")).click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
			assertEquals(resultadoEsperado, driver.findElement(By.id("mensagem")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		IEmpresaDAO empresaDAO = fabricaDAO.getEmpresaDAO();
		empresaDAO.exclui("50658639000137");
		empresaDAO.exclui("56241412000178");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	

}
