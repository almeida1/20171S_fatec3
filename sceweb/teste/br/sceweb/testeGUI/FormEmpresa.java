package br.sceweb.testeGUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormEmpresa {
	private WebDriver driver;
	private String baseUrl;
		
	public FormEmpresa (WebDriver driver){
		this.driver = driver;
		baseUrl = "http://localhost:8080/";
	}
	
	public void abre(){
		driver.get(baseUrl + "/sceweb/visao/FormEmpresa.jsp");
	}
	public void cadastra(String n, String c, String f, String e, String t, String r, String tr, String s, String em, String re){
		
		driver.findElement(By.id("campo")).clear();
		driver.findElement(By.id("campo")).sendKeys(n);
		driver.findElement(By.name("txtCNPJ")).clear();
		driver.findElement(By.name("txtCNPJ")).sendKeys(c);
		driver.findElement(By.name("txtNomeFantasia")).clear();
		driver.findElement(By.name("txtNomeFantasia")).sendKeys(f);
		driver.findElement(By.name("txtEndereco")).clear();
		driver.findElement(By.name("txtEndereco")).sendKeys(e);
		driver.findElement(By.name("txtTelefone")).clear();
		driver.findElement(By.name("txtTelefone")).sendKeys(t);
		driver.findElement(By.name("txtResponsavel")).clear();
		driver.findElement(By.name("txtResponsavel")).sendKeys(r);
		driver.findElement(By.name("txtTelefoneResponsavel")).clear();
		driver.findElement(By.name("txtTelefoneResponsavel")).sendKeys(tr);
		driver.findElement(By.name("txtSetor")).clear();
		driver.findElement(By.name("txtSetor")).sendKeys(s);
		driver.findElement(By.name("txtEmail")).clear();
		driver.findElement(By.name("txtEmail")).sendKeys(em);
		driver.findElement(By.id("botao")).click();
		
	}
	public void excluir(String cnpj){
		driver.findElement(By.name("txtCNPJ")).clear();
		driver.findElement(By.name("txtCNPJ")).sendKeys(cnpj);
		driver.findElement(By.name("btnExcluir")).click();
	}
}

