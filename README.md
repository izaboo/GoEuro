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
 - preview veryfing acsending sorting for multipage result
 - add fine reporting

Note: Main comments are inside code itself.

This project realizes the following challenge:

GoEuro lists the travel results sorted by cheapest, fastest or by time (departure/arrival). Please create an automated test that will make a search (Eg.: Berlin ­ Prague) and will verify that the sorting by price is correct.
