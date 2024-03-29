# Test Plan: Online Calculator

## 1. Introduction

### 1.1 Problem Statement
The online calculator under consideration is a web-based application embedded within an iframe. It features a single display screen and has a maximum digit limit of 9 digits. The goal is to thoroughly test the calculator for accuracy, user interface responsiveness, and robust handling of various inputs.

## 2. Test Objectives

### 2.1 Test Objectives
The primary objectives of the testing effort are:
- Validate the correctness of basic arithmetic operations (addition, subtraction, multiplication, division).
- Ensure the calculator handles a variety of input scenarios gracefully, including decimals, negative numbers.
- Identify and address potential issues related to the maximum digit limit in both input and output.
- Evaluate the user interface responsiveness and the persistence of calculations on the display screen.

## 3. Test Scope

### 3.1 Inclusions
The test scope includes:
- Positive tests to verify the accuracy of arithmetic operations.
- Negative tests to ensure the calculator rejects invalid inputs and displays appropriate error messages.
- Boundary tests to assess the behavior of the calculator at its limits, including the maximum digit limit.
- User interface responsiveness testing.

### 3.2 Exclusions
The test will not cover:
- Integration testing with other systems.
- Performance testing for large-scale calculations.
- Storing values in memory for the calculator (This will be tested manually)

## 4. Test Environment

### 4.1 Hardware
Testing will be conducted on standard desktops and laptops with varying operating systems (Windows, macOS, Linux).

### 4.2 Software
- Web Browsers: Chrome, Firefox
- Selenium WebDriver for browser automation
- Java programming language for test script development
- Appropriate WebDriver drivers for each browser

## 5. Test Approach

### 5.1 Positive Tests

1. **Basic Arithmetic Operations:**
    - Verify the correctness of addition, subtraction, multiplication, and division with two 9-digit numbers.

2. **Decimal Numbers:**
    - Validate the calculator's handling of decimal numbers in arithmetic operations.

3. **Square roots of Numbers:**
    - Test the accuracy the decimal points and the root of the values provided.

### 5.2 Negative Tests

1. **Divide By Zero operation:**
    - Attempt to divide a number by 0.
    - Expected: Verify the calculator provides an informative error message.

2. **Unsupported Operations:**
    - Attempt to perform a square root of a negative number.
    - Expected: Verify the calculator provides an informative error message.

### 5.3 Boundary Tests

1. **Convert a number to a percentage:**
    - Input a number and press the % sign.
    - Expected: Confirm that the calculator converts the value to the correct percentage figure.

2. **Minimum Digits in Result:**
    - Multiply two 1-digit numbers.
    - Expected: Ensure the calculator displays the correct single-digit result.

3. **Larger Decimal Number Precision:**
    - Add 2 numbers with large decimals.
    - Expected: The decimal result should be handled with precision

4. **Division by 1 returns the same value:**
    - Divide a number by 1.
    - Expected: The result should be the number itself

5. **Empty Display:**
    - Clear the display and perform an operation.
    - Expected: Verify that the calculator handles calculations correctly even after the display is cleared.

### 5.4 User Interface Responsiveness

1. **Display Persistence:**
    - Perform multiple operations consecutively without clearing the display.
    - Expected: Confirm that the calculator maintains the correct result for each operation.

2. **Input Speed:**
    - Rapidly input a series of calculations.
    - Expected: Evaluate how well the calculator handles rapid user inputs and updates the display.

## 5.5 Testing Approach for Interacting with iframe Element

### 5.5.1 Problem Statement Recap

The online calculator under consideration poses a unique challenge due to its implementation within an iframe element. Iframes can complicate the traditional approach to automation testing, making it challenging to interact directly with the calculator's elements using standard WebDriver methods.

### 5.5.2 Approach

To overcome the challenges posed by the iframe element, a specialized testing approach has been devised. Instead of relying solely on standard WebDriver interactions, this approach leverages JavaScript's capability to execute keypress events. By utilizing JavaScript's ability to manipulate the Document Object Model (DOM), we can effectively send input values to the calculator embedded within the iframe.

### 5.5.3 Implementation Details

1. **JavaScript Execution:**
   - Utilizing the `executeScript` method provided by WebDriver, JavaScript snippets are injected into the iframe's context. This allows us to simulate keypress events and interact with the calculator's input elements.

2. **executeKeyPressEvents Function:**
   - A custom JavaScript function, `executeKeyPressEvents`, has been developed to simulate the entry of numerical and operational values into the calculator. This function is designed to ensure compatibility across different browsers and iframe configurations.

3. **Dynamic Element Identification:**
   - Since direct interaction with iframe elements may not be straightforward, the testing approach dynamically identifies calculator elements by inspecting the iframe's content. This allows for a more flexible and adaptive testing strategy.

### 5.5.4 Advantages

- **Compatibility:** The JavaScript approach is less dependent on the structure of the iframe, making it more adaptable to changes in the application's layout.

- **Flexibility:** By executing keypress events, we bypass some limitations associated with direct WebDriver interactions within iframes, ensuring a more robust testing process.

- **Maintainability:** The use of dynamic element identification reduces the impact of changes in the iframe structure, resulting in a more maintainable test suite.

### 5.5.5 Limitations

- **Dependency on JavaScript Execution:** The success of this approach is contingent on the browser's ability to execute JavaScript, which is generally a standard feature but may have limitations in specific environments.


### 5.5.6 Considerations for Future Updates

As the online calculator application evolves, this testing approach will be continuously reviewed and adapted to accommodate any changes in the iframe structure or functionality. Regular compatibility checks with new browser versions will also be conducted to ensure the continued effectiveness of this testing strategy.

This approach offers a pragmatic solution to the challenges posed by iframes, providing a robust foundation for testing the online calculator with the necessary flexibility to adapt to changes in its implementation.

## 6. Test Execution

### 6.1 Test Execution Schedule
The testing will be conducted in multiple phases to cover positive, negative, and boundary scenarios. Each phase will focus on specific types of tests.

### 6.2 Test Execution Criteria
- All test cases should be executed on different browsers (Chrome, Firefox).
- Test cases should pass on the latest stable browser versions.
- Test cases have been organized in test profiles to run each test suite on their own.

### 6.3 Test Pass/Fail Criteria
A test case is considered passed if the calculator provides the expected result and behavior. It is considered failed if the calculator crashes, hangs, or produces incorrect results. Failed test cases must be documented with detailed information such as screenshots on the observed behavior.

## 7. Test Deliverables

### 7.1 Test Reports
Detailed test reports will be generated after the completion of each testing phase. The reports will include information on test scenarios, test results, and any issues identified.

### 7.2 Defect Reports
If defects are identified during testing, detailed defect reports will be created. Each report will include information on the defect, steps to reproduce, and severity.

## 8. Risks and Assumptions

### 8.1 Risks
- Browser compatibility issues may impact test execution.
- Changes in the calculator application during the testing phase.

### 8.2 Assumptions
- The calculator application is stable and functional at the beginning of the testing phase.
- Access to the calculator application is available throughout the testing period.

## 9. Approval

This test plan is to be reviewed and approved by the project stakeholders before testing commences.

---

**Project Stakeholder Sign-off:**

*[Space for Stakeholder Signatures]*

---

## Revision History

| Version | Date              | Description                 | Author             |
|---------|-------------------|-----------------------------|--------------------|
| 1.0     | 24th January 2024 | Online Calculator Test Plan | Stephen K. Mutinda |