package impactQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage  {
	private WebDriver driver;

	public SearchPage(WebDriver d) {
		this.driver=d;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(name = "q")

	private WebElement search;

	public WebElement getSearch() {
		return search;
	}

	@FindBy(name = "btnK")

	private WebElement btn;

	public WebElement getBtn() {
		return btn;
	}

	public void TypeText() {

		{
			getSearch().sendKeys("qa");
		}
		
	}
  public void click()
  {
	  getBtn().click();
  }
}
