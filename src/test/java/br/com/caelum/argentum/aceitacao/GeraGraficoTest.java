package br.com.caelum.argentum.aceitacao;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GeraGraficoTest {
	
	private static final String URL = "http://localhost:8080/fj22-argentum-web/index.xhtml";
	
	private WebDriver driver;
	
	@Test
	public void testeAoGerarGraficoSemTituloUmaMensagemEApresentada(){
		driver = new FirefoxDriver();
		driver.get(URL);
		WebElement titulo = driver.findElement(By.id("dadosTitulo:titulo"));
		
		titulo.sendKeys("");
		titulo.submit();
		
		boolean existeMensagem = driver.getPageSource().contains("o valor é necessário");
		
		Assert.assertTrue(existeMensagem);
		
		driver.close();
	}
	

}
