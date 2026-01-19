# AYANWORKS – Sovio QA Automation Assignment

This repository contains the manual and automation test execution for the AYANWORKS QA Intern assignment on Sovio dev environment.

**Application URL:**  
- https://dev.sovio.id/

---

## ✅ Scope Covered (as per assignment)

1) **# Manual Test Cases Link : **
   
- Google sheet : https://docs.google.com/spreadsheets/d/1Aolz7OUccasM0G9M-PdLz5q9RSZgYFKDyLhMlEEhU2g/edit?usp=sharing

- User Registration
- Sign In
- Passkey Registration
- Sign-in using Passkey
- UI/UX checks
- Input validations
- Negative scenarios & edge cases

2) ### Automation Testing (Selenium + Java)
Automated flows:
- Registration (valid & invalid inputs)
- Sign in using password (valid & invalid scenarios)
- Passkey registration flow trigger
- Sign-in using passkey flow trigger

---

## 🛠 Tech Stack / Tools Used
- Java 17
- Selenium WebDriver
- TestNG Framework
- Maven
- Page Object Model (POM)
- ChromeDriver (via Selenium Manager)

---

## 📁 Project Structure

```text
src/test/java/com/testing/
  base/
    BaseTest.java
  pages/
    LoginPage.java
    RegisterPage.java
    DashboardPage.java
    ProfilePage.java
    HeaderPage.java
    SignInPage.java
  tests/
    RegistrationTests.java
    LoginTests.java
    PasskeyTests.java
    PasskeyLoginTests.java
  utils/
    WaitUtils.java

✅ Pre-requisites

Java installed (Java 17 recommended)
Maven installed and configured in system PATH
Google Chrome browser installed

To verify:

java -version
mvn -version

▶️ How to Run Tests
1) Run all tests
mvn clean test

2) Run individual test class
mvn -Dtest=RegistrationTests test
mvn -Dtest=LoginTests test
mvn -Dtest=PasskeyTests test
mvn -Dtest=PasskeyLoginTests test

🔐 Note on Passkey (WebAuthn) Automation

Passkey authentication triggers browser/OS level dialogs such as:
Google Password Manager
Windows Hello / External Security Key

These system dialogs are not part of HTML DOM and cannot be fully automated using Selenium.

✅ Hence, automation validates:

Proper navigation to passkey module
Correct UI controls visibility
Triggering of passkey flow
No blocker errors

This approach follows standard QA automation best practices for WebAuthn/passkey flows.

3) **🐞 Bug Reporting**
Link to sheet : https://docs.google.com/spreadsheets/d/1lmb6nzkfnQu5LwzxjuiPmn9LQ70wvr74PtKfX6X4K3Q/edit?usp=sharing


👤 Author
Trupti Utpat
QA Intern Applicant – AYANWORKS Assignment


