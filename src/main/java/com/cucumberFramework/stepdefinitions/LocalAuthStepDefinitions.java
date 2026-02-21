package com.cucumberFramework.stepdefinitions;

import com.cucumberFramework.helper.LocalProfileHelper;
import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.pageObjects.LocalAuthPage;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocalAuthStepDefinitions {

	private LocalAuthPage authPage;
	private WaitHelper waitHelper;

	private LocalAuthPage getAuthPage() {
		if (authPage == null) authPage = new LocalAuthPage(TestBase.driver);
		return authPage;
	}

	private WaitHelper getWaitHelper() {
		if (waitHelper == null) waitHelper = new WaitHelper(TestBase.driver);
		return waitHelper;
	}

	@Given("^I am on the local login page$")
	public void i_am_on_the_local_login_page() throws Throwable {
		String baseUrl = LocalProfileHelper.getLocalWebBaseUrl();
		TestBase.driver.get(baseUrl + "login.html");
		getWaitHelper().WaitForElement(getAuthPage().usernameInput, 10);
	}

	@When("^I login using the default local profile$")
	public void i_login_using_the_default_local_profile() throws Throwable {
		String user = LocalProfileHelper.getDefaultUsername();
		String pass = LocalProfileHelper.getDefaultPassword();
		getAuthPage().enterUsername(user);
		getAuthPage().enterPassword(pass);
		getAuthPage().clickLogin();
	}

	@When("^I login with username \"([^\"]*)\" and password \"([^\"]*)\" from local profiles$")
	public void i_login_with_username_and_password_from_local_profiles(String username, String password) throws Throwable {
		getAuthPage().enterUsername(username);
		getAuthPage().enterPassword(password);
		getAuthPage().clickLogin();
	}

	@Then("^I should be on the dashboard and see I am logged in$")
	public void i_should_be_on_the_dashboard_and_see_i_am_logged_in() throws Throwable {
		getWaitHelper().WaitForElement(getAuthPage().logoutButton, 10);
		getAuthPage().logoutButton.isDisplayed();
	}

	@And("^I sign out from the dashboard$")
	public void i_sign_out_from_the_dashboard() throws Throwable {
		getAuthPage().clickLogout();
	}

	@Then("^I should be back on the local login page$")
	public void i_should_be_back_on_the_local_login_page() throws Throwable {
		getWaitHelper().WaitForElement(getAuthPage().usernameInput, 10);
		getAuthPage().usernameInput.isDisplayed();
	}

	@When("^I go to the signup page$")
	public void i_go_to_the_signup_page() throws Throwable {
		getAuthPage().clickSignupLink();
		getWaitHelper().WaitForElement(getAuthPage().signupUsernameInput, 10);
	}

	@When("^I create an account using the signup local profile$")
	public void i_create_an_account_using_the_signup_local_profile() throws Throwable {
		getAuthPage().enterSignupUsername(LocalProfileHelper.getSignupUsername());
		getAuthPage().enterSignupEmail(LocalProfileHelper.getSignupEmail());
		getAuthPage().enterSignupPassword(LocalProfileHelper.getSignupPassword());
		getAuthPage().enterSignupConfirm(LocalProfileHelper.getSignupPassword());
		getAuthPage().clickSignupButton();
	}

	@Then("^I should be redirected to the login page$")
	public void i_should_be_redirected_to_the_login_page() throws Throwable {
		Thread.sleep(1500);
		getWaitHelper().WaitForElement(getAuthPage().usernameInput, 10);
		getAuthPage().usernameInput.isDisplayed();
	}
}
