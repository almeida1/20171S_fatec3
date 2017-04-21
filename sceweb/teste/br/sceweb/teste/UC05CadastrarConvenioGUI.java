package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sceweb.modelo.ConvenioDAO;
import br.sceweb.modelo.EmpresaDAO;

public class UC05CadastrarConvenioGUI {
  private static WebDriver driver;
  private static String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static FormConvenio formConvenio;
  @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"C:/Users/esadv6/git/20171S_fatec3/sceweb/WebContent/WEB-INF/lib/geckodriver.exe");
				
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		formConvenio = new FormConvenio(driver); 
	}

  @Test
  public void testUC05CadastrarConvenio() throws Exception {
	    formConvenio.abre();
		formConvenio.cadastra("53236636000101","21/04/2017","21/04/2018");
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
	convenioDAO.exclui("50658639000137");
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
}
