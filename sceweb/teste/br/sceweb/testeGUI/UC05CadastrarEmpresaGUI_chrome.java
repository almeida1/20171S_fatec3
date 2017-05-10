package br.sceweb.testeGUI;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sceweb.modelo.EmpresaDAO;
/**
 * script de teste para chrome merge de conflito
 * @author esadv6
 *
 */
public class UC05CadastrarEmpresaGUI_chrome {
	static private WebDriver driver;
	static private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/professor/git/20171s_fatec31/sceweb/WebContent/WEB-INF/lib/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.exclui("50658639000137");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void testCT01UC01CadastrarEmpresa_com_sucesso() throws Exception {
		driver.get(baseUrl + "/sceweb/visao/FormEmpresa.jsp");
		driver.findElement(By.id("campo")).clear();
		driver.findElement(By.id("campo")).sendKeys("Open Informatica Ltda");
		driver.findElement(By.name("txtCNPJ")).clear();
		driver.findElement(By.name("txtCNPJ")).sendKeys("50658639000137");
		driver.findElement(By.name("txtNomeFantasia")).clear();
		driver.findElement(By.name("txtNomeFantasia")).sendKeys("Open Informatica");
		driver.findElement(By.name("txtEndereco")).clear();
		driver.findElement(By.name("txtEndereco")).sendKeys("Rua Aguia de Haia, 2432");
		driver.findElement(By.name("txtTelefone")).clear();
		driver.findElement(By.name("txtTelefone")).sendKeys("121212");
		driver.findElement(By.name("txtResponsavel")).clear();
		driver.findElement(By.name("txtResponsavel")).sendKeys("jose carlos");
		driver.findElement(By.name("txtTelefoneResponsavel")).clear();
		driver.findElement(By.name("txtTelefoneResponsavel")).sendKeys("111221");
		driver.findElement(By.name("txtSetor")).clear();
		driver.findElement(By.name("txtSetor")).sendKeys("contabilidade");
		driver.findElement(By.name("txtEmail")).clear();
		driver.findElement(By.name("txtEmail")).sendKeys("jose@gmail.com");
		driver.findElement(By.id("botao")).click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
			assertEquals("cadastro realizado com sucesso", driver.findElement(By.id("mensagem")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

}
