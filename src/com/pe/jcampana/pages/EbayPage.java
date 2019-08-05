/**
 * 
 */
package com.pe.jcampana.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Jose Campana
 *
 *Esta clase almacenara localizadores 
 *y metodos de la pagina de Ebay
 *
 */
public class EbayPage {

	WebDriver driver; 
	
	//gh-tb ui-autocomplete-input // caja texto busqueda principal
	
	
	By cajaBusqueda =  By.id("gh-ac"); // caja texto busqueda principal
	By botonBuscar = By.id("gh-btn"); // boton buscar 	
	By checkBoxPuma = By.id("w_1564948586893_cbx"); // caja checkbox Puma  
	By checkBoxTalla10 = By.id("w_1564950135931_cbx"); // caja checkbox talla 10 
	By cantResultado = By.className("srp-controls__count-heading"); // label cantidad resultado de la busqueda 
	By linkOrdenarPrecioAscendente = By.xpath("//DIV[@class='srp-sort']/UL/LI[4]/A"); //control ordenado asc
	By linkOrdenarPrecioDescendente = By.xpath("//DIV[@class='srp-sort']/UL/LI[5]/A"); //control ordenado desc
	By checkBoxResultados = By.xpath("//div[contains(@class, 'srp-controls__control--legacy')][1]");
	
	public EbayPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public void ingresarPagina() {
		driver.get("https://www.ebay.com/");
	}
	
	public void busqueda(String search) {
		driver.findElement(cajaBusqueda).sendKeys(search); ;
	} 
	
	public void escogerMarca() {
		driver.findElement(checkBoxPuma).click();
	} 
	
	public void escogerTallazapato() {
		driver.findElement(checkBoxTalla10).click();		
	}
	
	public String devuelveCantidadResultados() {
		return driver.findElement(cantResultado).getText(); 
	}
	
	public void ordenarResultado() {
		driver.findElement(linkOrdenarPrecioAscendente).click(); 
	}
	
	public double obtenerPrecioProducto(int numeroProducto) {
		String precioProducto = devuelvePrecio( driver.findElement(By.xpath("//li[contains(@class,'s-item')]["+numeroProducto+"]//span[contains(@class,'s-item__price')]")).getText() );
		String precioShipping = devuelvePrecio( driver.findElement(By.xpath("//li[contains(@class,'s-item')]["+numeroProducto+"]//span[contains(@class,'s-item__shipping')]")).getText() ); 
		
		double precioProd = Double.parseDouble(precioProducto);
		double precioShip = Double.parseDouble(precioShipping);
		
		return precioProd+precioShip;
	}
	
	
	private String devuelvePrecio(String precio) {		
		return precio.replace("+","")
				.replace("$", "")
				.replace(",","")
				.replace(" shipping","")
				.replace(" por el envio", "")
				.replace(" ", "");
	}

	
	public String obtenerNombreProducto(int nro) {
		return driver.findElement( By.xpath("//li[contains(@class,'s-item')]["+nro+"]//h3[contains(@class,'s-item__title')]" )).getText();
	}
	
	
	public void ordenarPrecioAsc() {
		WebElement webElement = driver.findElement(checkBoxResultados);
		Actions action = new Actions(driver);
		action.moveToElement(webElement).perform();
		driver.findElement(checkBoxResultados).click();
		
	}
	
} 
