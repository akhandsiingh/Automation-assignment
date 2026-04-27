# Amazon Automation Testing (Selenium + TestNG)

## Overview

This project automates key user flows on Amazon using Selenium WebDriver with Java.
It covers searching products, extracting prices, and adding items to the cart with parallel execution.

---

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager

---

## Features

* Search for products (iPhone, Samsung Galaxy)
* Extract product price dynamically
* Add product to cart
* Run tests in parallel using TestNG
* Handles dynamic UI changes and tab switching

---

## Project Structure

```
automation-assignment
 ├── src/test/java/org.testmu.tests
 │    ├── AmazonIphoneTest.java
 │    └── AmazonGalaxyTest.java
 ├── testng.xml
 ├── pom.xml
 └── README.md
```

---

## Setup

1. Clone the repository

```
git clone <your-repo-link>
cd automation-assignment
```

2. Install dependencies

```
mvn clean install
```

---

## Run Tests

Run using Maven:

```
mvn test
```

Or run `testng.xml` directly from IDE.

---

## Test Scenarios

Test Case 1:

* Search for iPhone
* Open first product
* Extract price
* Add to cart

Test Case 2:

* Search for Samsung Galaxy
* Open first product
* Extract price
* Add to cart

---

## Key Points

* Uses explicit waits for stability
* Handles both new tab and same tab navigation
* Uses dynamic selectors for reliability
* Supports parallel execution

---

## Notes

* Amazon UI may vary slightly based on region
* Some warnings in console do not affect execution

---

## Author

Akhand Singh
