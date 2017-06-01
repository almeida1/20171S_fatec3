package br.sceweb.testeGUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormConvenio {
	private WebDriver driver;
	private String baseUrl;
		
	public FormConvenio (WebDriver driver){
		this.driver = driver;
		baseUrl = "http://localhost:8080/";
	}
	
	public void abre(){
		driver.get(baseUrl + "/sceweb/visao/FormConvenio.jsp");
	}
	public void cadastra (String cnpj, String dataI, String dataF){
		driver.findElement(By.id("campo")).clear();
	    driver.findElement(By.id("campo")).sendKeys(cnpj);
	    driver.findElement(By.name("txtDtInicio")).clear();
	    driver.findElement(By.name("txtDtInicio")).sendKeys(dataI);
	    driver.findElement(By.name("txtDtTermino")).clear();
	    driver.findElement(By.name("txtDtTermino")).sendKeys(dataF);
	    driver.findElement(By.id("botao")).click();
	}
	public void excluir (String cnpj){
		driver.findElement(By.id("campo")).clear();
	    driver.findElement(By.id("campo")).sendKeys(cnpj);
	    driver.findElement(By.name("btnExcluir")).click();
	}
}
	
