package com.cucumberFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for local login and signup pages (no Amazon/external sites).
 * Automation uses local profiles only.
 */
public class LocalAuthPage {

	private WebDriver driver;

	// Login page
	@FindBy(id = "username")
	public WebElement usernameInput;

	@FindBy(id = "password")
	public WebElement passwordInput;

	@FindBy(id = "loginBtn")
	public WebElement loginButton;

	@FindBy(id = "signupLink")
	public WebElement signupLink;

	// Signup page
	@FindBy(id = "signup-username")
	public WebElement signupUsernameInput;

	@FindBy(id = "signup-email")
	public WebElement signupEmailInput;

	@FindBy(id = "signup-password")
	public WebElement signupPasswordInput;

	@FindBy(id = "signup-confirm")
	public WebElement signupConfirmInput;

	@FindBy(id = "signupBtn")
	public WebElement signupButton;

	@FindBy(id = "loginLink")
	public WebElement loginLink;

	// Dashboard (after login)
	@FindBy(id = "logoutBtn")
	public WebElement logoutButton;

	@FindBy(id = "displayUser")
	public WebElement displayUser;

	public LocalAuthPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		usernameInput.clear();
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void clickSignupLink() {
		signupLink.click();
	}

	public void enterSignupUsername(String username) {
		signupUsernameInput.clear();
		signupUsernameInput.sendKeys(username);
	}

	public void enterSignupEmail(String email) {
		signupEmailInput.clear();
		signupEmailInput.sendKeys(email);
	}

	public void enterSignupPassword(String password) {
		signupPasswordInput.clear();
		signupPasswordInput.sendKeys(password);
	}

	public void enterSignupConfirm(String password) {
		signupConfirmInput.clear();
		signupConfirmInput.sendKeys(password);
	}

	public void clickSignupButton() {
		signupButton.click();
	}

	public void clickLogout() {
		logoutButton.click();
	}
}
