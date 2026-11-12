# Requirements

The project requires Java and Google Chrome to be installed.

External libraries are located in the `lib` directory.

These include:

- Gson
- Selenium WebDriver
- dependencies required by Selenium

## Compilation

To compile the project on Windows, run the following:

``` bash
javac -cp ".;lib/*" Main.java Author.java AuthorThread.java AuthorApiClient.java AuthorUiTest.java
```

## Running the Project

To run the project:

``` bash
java -ea -cp ".;lib/*" Main
```
The `-ea` option enables Java assertions

## Project Structure

- `Main.java` - creates authors, starts four parallel threads, and performs a final data comparison.
- `Author.java` - author data model.
- `AuthorThread.java` - performs POST and GET operations for a single author.
- `AuthorApiClient.java` - retrieves a list of all authors directly from the API.
- `AuthorUiTest.java` - interacts with Swagger UI using Selenium and retrieves author data displayed in the UI.
- `lib/` - contains Gson, Selenium WebDriver, and required dependencies.

## Selenium

Simple Selenium code is used due to the required step by the project:
>(...)
>Next, open the UI of the same page and compare the authors displayed in the UI with the data retrieved from the API.

I assume the intention was not to open the UI using Selenium, but I did so to present how Selenium works in Java.

The code in the AuthorUiTest.java file opens the page https://fakerestapi.azurewebsites.net/index.html, makes a GET request to the endpoint /api/v1/Authors, then clicks the “Try it out” and “Execute” buttons, and stores the response body in the variable responseBody.

This allows me to compare the data retrieved directly via a GET request with the data returned by the same endpoint when run in Swagger UI.

## Additional Notes

The comments in the code are written in English to follow best practices and maintain consistency in the code.
The project was originally created in Polish language for the tester internship. 
