package test.java.com.goeuro.goEuroPageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Class with configuration and main actions on page with found travel results and costs
 */

public class FoundResultsPage {
	private final WebDriver driver;
	private final WebDriverWait wait = null;
	By tableWithPricesOnly = By.xpath("//*[@id='results-train']//td[@class='price-cell']//div[@class='price-cell-content ']");

	PricesContainer pricesFromTable = new PricesContainer();

	public FoundResultsPage(WebDriver driver, WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableWithPricesOnly));
        this.driver = driver;
	}
		
	private void findPrices ()
	{
        List <WebElement> prices = driver.findElements(tableWithPricesOnly);
        for (WebElement price : prices)
        {
        	String intPart =  price.findElement(By.className("currency-beforecomma")).getText();
        	String fractPart =  price.findElement(By.xpath("//span/span[4]")).getText();
        	pricesFromTable.add(intPart,fractPart);
        }
	}
	
	public boolean isPricesSortedAcs ()
	{
		findPrices();
		return pricesFromTable.isSorted();
	}
	
	class PricesContainer
	{
		final String currencySeparator = ".";
		List <Double> prices;
		
		PricesContainer()
		{
			prices = new ArrayList <Double>();
		}

		void add (String currencyInteger, String currencyFraction)
		{
			prices.add(price(currencyInteger, currencyFraction));
		}

		
		Double price(String currencyInteger, String currencyFraction)
		{
			return Double.parseDouble(currencyInteger + currencySeparator + currencyFraction);
		}
		
		public boolean isSorted ()
		{
		    boolean sorted = true;        
		    for (int i = 1; i < prices.size(); i++) {
		        if (prices.get(i-1).compareTo(prices.get(i)) > 0) 
		        	return false;
		    }
		    return sorted;
		}
	}

}
