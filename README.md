# Cucumber BDD Automation Framework

A **Behavior Driven Development (BDD)** automation framework using **Cucumber**, **Selenium**, and **TestNG**, with **local login/signup** flows and **local profiles only**—no external sites or credentials.

---

## Features

- **Local login and signup** – Tests run against built-in HTML pages (no Amazon or external URLs).
- **Local profiles only** – Credentials are read from `src/test/resources/local-profiles.properties` when automation starts.
- **Cucumber (Gherkin)** – Feature files and scenario outlines.
- **Selenium WebDriver** – Chrome, Firefox, or Edge via WebDriverManager (drivers auto-downloaded).
- **TestNG** – Test execution and suite configuration.
- **Reports** – Cucumber pretty/advanced reports and Allure (optional).

---

## Project structure

```
Cucumber-BDD-Automation-Framework-master/
├── pom.xml
├── testNg.xml
├── log4j.properties
├── src/
│   ├── main/java/com/cucumberFramework/
│   │   ├── enums/           # Browsers, OS
│   │   ├── helper/          # WaitHelper, LoggerHelper, LocalProfileHelper, Constants
│   │   ├── pageObjects/     # LocalAuthPage, LoginLogoutPage
│   │   ├── stepdefinitions/ # LocalAuthStepDefinitions, loginLogoutPageStepDefinitions, ServiceHooks
│   │   ├── testBase/        # TestBase (WebDriver setup)
│   │   └── testRunner/      # TestRunner (Cucumber + TestNG)
│   └── test/resources/
│       ├── features/login/  # login_logout.feature
│       ├── web/             # login.html, signup.html, dashboard.html (local UI)
│       ├── local-profiles.properties  # Test credentials (local only)
│       └── drivers/         # Optional; WebDriverManager can replace
├── target/                  # Build and reports (generated, gitignored)
└── README.md
```

---

## Prerequisites

- **Java 8+** (Java 9+ supported; JAXB and module opens are configured in `pom.xml`).
- **Mozilla Firefox** (default) or **Google Chrome** – at least one must be installed for UI tests.
- **Maven 3.x**.

---

## Local profiles (credentials)

All test users are defined in **one place**. Automation uses only this file—no hardcoded credentials in feature files.

**File:** `src/test/resources/local-profiles.properties`

```properties
# Default login profile
default.username=testuser
default.password=testpass123

# Signup profile (create account)
signup.username=newuser
signup.email=newuser@test.local
signup.password=newpass123
```

Edit this file to add or change test users. Do not commit real passwords.

---

## How to run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yoursmadhavaM/Cucumber-BDD-automation-framework.git
   cd Cucumber-BDD-automation-framework
   ```

2. **Run tests (default: Firefox)**
   ```bash
   mvn clean test
   ```

3. **Use Chrome instead**
   ```bash
   mvn clean test -Dbrowser=CHROME
   ```

4. **Generate Cucumber and Allure reports**
   ```bash
   mvn clean verify
   mvn site
   ```
   - Cucumber reports: `target/cucumber-reports/`
   - Allure: `target/site/allure-maven-plugin/` (open `index.html` or run `mvn jetty:run` and open `http://localhost:8080`).

5. **View TestNG reports**
   - `test-output/index.html`
   - `target/surefire-reports/`

---

## Troubleshooting

- **Browser not launching**: Ensure Firefox or Chrome is installed, or run with `-Dbrowser=CHROME|FIREFOX|EDGE`.
- **Local pages not found**: Verify the HTML files exist under `src/test/resources/web/`.
- **WebDriver issues**: If drivers fail to download, clear cached drivers and re-run the tests.

---

## Feature scenarios (current)

- **Login with local profile** – Open local login page → login with default profile → dashboard → sign out.
- **Create account (signup)** – Open login → go to signup → create account with signup profile → redirected to login.
- **Login with examples** – Login using usernames/passwords from the Examples table (e.g. `testuser` / `testpass123`, `newuser` / `newpass123`).

---

## Configuration

- **Browser:** Set in `ServiceHooks` or via `-Dbrowser=CHROME|FIREFOX|EDGE`.
- **Test suite:** `testNg.xml` (references `TestRunner`).
- **Cucumber options:** In `pom.xml` (Surefire `argLine`) and in `TestRunner` (`@CucumberOptions`).

---

## License

Use and modify as needed for your projects.
