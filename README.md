Repository for hosting GoEuro test assignment project.

General overview of the approach:
 - Selenium Webdriver + TestNG + Maven are used sor setting up and building project as well as organize tests
 - pageObject pattern was used to describe and incapsulate behaviour of pages within corresponded classes
 - 1 main class for tests preparation (driver init, opening start page, closing browser) used
 - tests' classes inherite from preparations
 - standart testng reports used with surefile pluging as builder

Further improvements which can be made
 - implement testing of different combinations of transport and modes of allocating results
 - preview situation when 0 results are found
 - enlarge reports

Note: Main comments are inside code itself.
