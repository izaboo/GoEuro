package main.java.com.goeuro;

		import org.openqa.selenium.By;
		import org.openqa.selenium.NoSuchElementException;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.support.ui.WebDriverWait;

public class MainSearchPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	By fromFilter = By.id("from_filter");
	By toFilter = By.id("to_filter");
	By airbnb = By.xpath("//label[@for='hotel-checkbox__airbnb']/span[@class='custom checkbox checked']");
	By booking = By.xpath("//label[@for='hotel-checkbox__booking']/span[@class='custom checkbox checked']");
	By submitSearchBtn = By.id("search-form__submit-btn");

	public MainSearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);

		if (driver.getTitle().matches("Search & Compare .* | GoEuro")) {
			throw new IllegalStateException("This is not main search page");
		}
	}

	public MainSearchPage enterFromDestination(String fromDest) {
		driver.findElement(fromFilter).sendKeys(fromDest);
		return this;
	}

	public MainSearchPage enterToDestination(String toDest) {
		driver.findElement(toFilter).sendKeys(toDest);
		return this;
	}

	public MainSearchPage unselectAirbnb() {
		try
		{
			WebElement isChecked = driver.findElement(airbnb);
			if (isChecked != null)
				driver.findElement(airbnb).click();
			return this;
		}
		catch (NoSuchElementException e)
		{
			//do nothing - since checkbox is not selected
		}
		return this;
	}

	public MainSearchPage unselectBooking() {
		try
		{
			WebElement isChecked = driver.findElement(booking);
			if (isChecked != null)
				driver.findElement(booking).click();
			return this;
		}
		catch (NoSuchElementException e)
		{
			//do nothing - since checkbox is not selected
		}
		return this;
	}

	public FoundResultsPage submitSearch() {

		driver.findElement(submitSearchBtn).submit();
		return new FoundResultsPage(driver, wait);
	}


	public FoundResultsPage findAvailableTrips (String from, String to) {
		enterFromDestination(from);
		enterToDestination(to);
		unselectAirbnb();
		unselectBooking();
		return submitSearch();
	}

}
