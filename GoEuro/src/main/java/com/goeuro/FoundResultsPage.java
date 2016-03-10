package main.java.com.goeuro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.helpers.PrintOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FoundResultsPage {
	private WebDriver driver;
	private WebDriverWait wait;
	By tableWithPricesOnly = By.xpath("//*[@id='results-train']//td[@class='price-cell']//div[@class='price-cell-content ']");

	static HashMap <String, By> mapOfTransportToLocator = new HashMap<String, By>();
	static
	{
		mapOfTransportToLocator.put("train",By.id("tab_train"));
		mapOfTransportToLocator.put("flight",By.id("tab_flight"));
		mapOfTransportToLocator.put("bus",By.id("tab_bus"));
	}

	String tableWithPricesBaseXpath = "//*[@id='transportLocatorHere']//div[@class='price-cell-content ']";

	public FoundResultsPage(WebDriver driver, WebDriverWait wait) {
		this.wait = wait;
		this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableWithPricesOnly));
		this.driver = driver;
	}

	private PricesContainer findPrices (String transport)
	{
		PricesContainer pricesFromTable = new PricesContainer();

		By tableWithPrices = By.xpath(
				tableWithPricesBaseXpath.replaceFirst("transportLocatorHere", "results-" + transport.toLowerCase())
		);
		List <WebElement> prices = driver.findElements(tableWithPrices);
		for (WebElement price : prices)
		{
			WebElement wholePrice = price.findElement(By.className("price-no "));
			pricesFromTable.add(wholePrice);
		}
		return pricesFromTable;
	}


	public void switchTransport (String transport)
	{
		driver.findElement(mapOfTransportToLocator.get(transport)).click();
		wait.until(new ExpectedCondition<Boolean>()
		{
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean)js.executeScript("return jQuery.active == 0");
			}});

	}


	public boolean isPricesSortedAcs (String transport)
	{
		PricesContainer pricesFromTable = findPrices(transport);
		PrintOptions.print(pricesFromTable.prices);
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

		void add (WebElement price)
		{
			List<WebElement> decimalsStyleElements =  price.findElements(By.className("currency-decimals"));
			String intPart =  price.findElement(By.className("currency-beforecomma")).getText();
			String fractPart = decimalsStyleElements.get(1).getText();
			prices.add(price(intPart, fractPart));
		}


		Double price(String currencyInteger, String currencyFraction)
		{
			return Double.parseDouble(currencyInteger + currencySeparator + currencyFraction);
		}

		public boolean isSorted ()
		{
			boolean sorted = true;
			for (int i = 1; i < prices.size(); i++) {
				if (prices.get(i-1) > prices.get(i))
					return false;
			}
			return sorted;
		}
	}

}
