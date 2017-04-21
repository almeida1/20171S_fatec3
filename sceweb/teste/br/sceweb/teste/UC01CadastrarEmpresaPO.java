package br.sceweb.teste;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresaPO {
	private static FormEmpresa formEmpresa;
	private static WebDriver driver;
	private static String baseUrl;
	private String resultadoEsperado;
	private StringBuffer verificationErrors = new StringBuffer();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"C:/Users/esadv6/git/20171S_fatec3/sceweb/WebContent/WEB-INF/lib/geckodriver.exe");
				
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		formEmpresa = new FormEmpresa(driver); 
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
	public void CT01CadastrarEmpresa_com_sucesso() {
		formEmpresa.abre();
		formEmpresa.cadastra("Open Informatica Ltda","50658639000137","Open Informatica","Rua Aguia de Haia, 2432","121212","jose carlos","111221","contabilidade","jose@gmail.com","cadastro realizado com sucesso");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
			assertEquals("cadastro realizado com sucesso", driver.findElement(By.id("mensagem")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
