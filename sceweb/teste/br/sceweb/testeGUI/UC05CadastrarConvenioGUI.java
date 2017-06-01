package br.sceweb.testeGUI;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.sceweb.modelo.ConvenioDAO;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC05CadastrarConvenioGUI {
  private static WebDriver driver;
  private static String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private static FormConvenio formConvenio;
  private static EmpresaDAO empresaDAO;
  private static Empresa empresa;
  @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    System.setProperty("webdriver.gecko.driver",
				"C:/Users/esadv6/git/20171S_fatec3/sceweb/WebContent/WEB-INF/lib/geckodriver.exe");
				
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("Open Informatica Ltda.");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("Open Informatica");
		empresa.setEndereco("Rua Taquari");
		empresa.setTelefone("2222");
		empresaDAO.adiciona(empresa);
		formConvenio = new FormConvenio(driver); 
	}

  @Test
  public void testUC05CadastrarConvenio() throws Exception {
	    formConvenio.abre();
		formConvenio.cadastra("89424232000180","21/04/2017","21/04/2018");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
			assertEquals("cadastro realizado com sucesso", driver.findElement(By.id("mensagem")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
  }

  @After
  public void tearDown() throws Exception {
	ConvenioDAO convenioDAO = new ConvenioDAO();
	convenioDAO.exclui("89424232000180");
	empresaDAO.exclui("89424232000180");
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
}
