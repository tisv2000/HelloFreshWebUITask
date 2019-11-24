# HelloFresh Web UI automation technical task

###  Prerequisites
- JDK8 or higher
- Maven v3.5.0
- Git v2.14
- Browsers (Chrome v76, Firefox v70)
- Lombok plugin for IDE 
    
### How to run the tests and generate a report
    cd <your_projects_directory>
    git clone https://github.com/tisv2000/HelloFreshWebUITask.git HelloFreshWebUITask
    cd HelloFreshWebUITask
    mvn clean test
    mvn allure:report
    
### Where to find the report, screenshots and logs
- Report can be found in `target/site/allure-maven` (the report file is `index.html`)
- Screenshots are taken only for failed tests and are attached to the report
- Logging system has 3 appenders:
    - file appender, which logs information in a file in`target/logs`
    - console appender, which prints information to a console
    - allure appender, which captures test log output and store it as attachment in allure report

### Configuration
- To run tests against a different browser:
    - change `driver.name` property  in `src/main/resources/default-config.properties`
    - or change `driver.name` property  in `src/test/resources/config.properties`
    - or use the following command:`mvn clean test -Ddriver.name=<browser name>`
- To run tests against a different environment:
    - change `base.url` property in `src/test/resources/config.properties`
    - or use the following command: `mvn clean test -Dbase.url=<url>`

### Dependencies
- Selenium to manage a browser
- TestNG to develop tests
- Allure to generate a report
- Apache commons to generate random strings
- Lombok to avoid boilerplate code
- Logback for logging

### Personal notes
- DriverFactory creates driver instances, DriverHolder is responsible for adding/removing them into/from ThreadLocal variable and BaseTest and BasePage classes only wrap DriverHolder class' methods.
- I understand how the design pattern Factory should work and I'm not applying it in the proper way. If I had more time I would create an abstract factory and an implementation for each browser.
- On the assumption of security reason, I decided to suppress password printing in logs, but leave it in allure report (you can see it when a step is unfolded)
- This solution supports 2 platforms - macOS and Linux, as well as platform auto-detection.