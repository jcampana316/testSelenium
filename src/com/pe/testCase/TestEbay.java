package com.pe.testCase;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.pe.jcampana.pages.EbayPage;


public class TestEbay {
	
	static WebDriver driver = new FirefoxDriver();

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("Inicio prueba Ebay");		
		try {
			pruebaEbay();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			terminarPrueba();
		}

		
	}

	private static void terminarPrueba() {
		// TODO Auto-generated method stub
		driver.quit();
	}

	@Test
	private static void pruebaEbay() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "E:\\Wksp_Eclipse\\Selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		Thread.sleep(3000); // esperar 3 seg 
		
		EbayPage ebay = new EbayPage(driver);
		ebay.ingresarPagina();
		ebay.busqueda("shoes");
		ebay.escogerMarca();
		ebay.escogerTallazapato();
		//ebay.devuelveCantidadResultados();
		System.out.println("Cantidad de resultados : "+ ebay.devuelveCantidadResultados());
		verificarProductos(ebay);		
		imprimir5Resultados(ebay);
		ordernarPrecioAsc(ebay);
		
	}

	private static void imprimir5Resultados(EbayPage ebay) {
		System.out.println("5 productos :");
		for (int i = 1; i <= 5; i++) {
			System.out.println(ebay.obtenerNombreProducto(i)+": "+ebay.obtenerPrecioProducto(i));
		}
		
	}

	private static void verificarProductos(EbayPage obj  ) {

			double precio1 = obj.obtenerPrecioProducto(1);
			double precio2 = obj.obtenerPrecioProducto(2);
			double precio3 = obj.obtenerPrecioProducto(3);
			double precio4 = obj.obtenerPrecioProducto(4);
			double precio5 = obj.obtenerPrecioProducto(5);


			Assert.assertTrue( "Precio del producto 1 debe ser menor al del producto 2" , precio1 < precio2);
			Assert.assertTrue("Precio del producto 2 debe ser menor al del producto 3", precio2 < precio3);
			Assert.assertTrue("Precio del producto 3 debe ser menor al del producto 4", precio3 < precio4);
			Assert.assertTrue("Precio del producto 4 debe ser menor al del producto 5", precio4 < precio5);

			System.out.println("Los 5 primeros resultados ordenados ");
			
		
	}
	
	private static void ordernarPrecioAsc(EbayPage obj) {
		obj.ordenarPrecioAsc(); 
	}
	
	

}
