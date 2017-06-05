package br.sceweb.testeGUI;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sceweb.modelo.MySQLConvenioDAO;
import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.IConvenioDAO;
import br.sceweb.modelo.IEmpresaDAO;
import br.sceweb.modelo.MySQLEmpresaDAO;

public class UC05CadastrarConvenioGUI2 {

	private static WebDriver driver;
	private static String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private static FormConvenio formConvenio;
	private static IEmpresaDAO empresaDAO;
	private static Empresa empresa;
	private static Workbook workbook;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "WebContent/WEB-INF/lib/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		System.setProperty("webdriver.gecko.driver",
//				"C:/Users/esadv6/git/20171S_fatec3/sceweb/WebContent/WEB-INF/lib/geckodriver.exe");
//
//		driver = new FirefoxDriver();
//		baseUrl = "http://localhost:8080/";
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaDAO.getEmpresaDAO();
		
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
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData2, "Planilha1");
		formConvenio.abre();
		
		for (int i = 1; i < 3; i++) {
			// linha x coluna
			 
			System.out.println("celula = " + ExcelUtils.getCellData(i, 3));
			formConvenio.cadastra(ExcelUtils.getCellData(i, 3), ExcelUtils.getCellData(i, 4),
					ExcelUtils.getCellData(i, 5));
			Thread.sleep(5000);
			try {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
				assertEquals(ExcelUtils.getCellData(1, 6), driver.findElement(By.id("mensagem")).getText());
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			
		}
	}

	@After
	public void tearDown() throws Exception {
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		IConvenioDAO convenioDAO = fabricaDAO.getConvenioDAO();
		convenioDAO.exclui("89424232000180");
		empresaDAO.exclui("89424232000180");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
