package br.sceweb.testeGUI;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import br.sceweb.teste.FormConvenio;

public class RoteiroDeNavegacao {
	private static WebDriver driver;
	private static String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private static FormConvenio formConvenio;

	private static Workbook workbook;
	public void fluxo() throws Exception {
		String acao;
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Planilha1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 3; i++) {
			// linha x coluna
			 
			System.out.println("celula = " + ExcelUtils.getCellData(i, 2));
			acao = ExcelUtils.getCellData(i, 2);
			if (acao.equals("abreFormConvenio")){
				System.setProperty("webdriver.chrome.driver", "C:/Users/esadv6/git/20171s_fatec3/sceweb/WebContent/WEB-INF/lib/chromedriver.exe");
				driver = new ChromeDriver();
				baseUrl = "http://localhost:8080/";
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				formConvenio = new FormConvenio(driver);
				formConvenio.abre();
				Thread.sleep(5000);
			}
			if (acao.equals("cadastrarConvenio")){
				formConvenio.cadastra(ExcelUtils.getCellData(i, 4), ExcelUtils.getCellData(i, 5),
						ExcelUtils.getCellData(i, 6));
				
				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
					//assertEquals(ExcelUtils.getCellData(1, 7), driver.findElement(By.id("mensagem")).getText());
					driver.quit();
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
			
			
		}
	}
	
}
