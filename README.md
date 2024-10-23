# Testing Instructions for Android Applications

This document outlines the steps for creating and running test cases for Android applications under the directory `app/src/androidTest/java/com.ondevice.offdevicetesting`.

## 1. Creating a Test Class

To begin, create a file named `<Name>Test.kt` in the directory mentioned above. The initial structure of the file should be as follows:

```kotlin
package com.ondevice.offdevicetesting

class <Name>Test {
}
```

Next, ensure that the new test class inherits from the available `BaseTestClass` to leverage basic functionality. You'll also need to provide the package name of the application you want to test as an argument and import the necessary dependencies.

Here is an example of the updated test class:

```kotlin
package com.ondevice.offdevicetesting

import org.junit.Test

class <Name>Test : BaseTestClass("<package_name_of_the_application_you_want_to_test>") {
}
```

## 2. Creating a Test Function

To create a simple test, add the following function to your test class:

```kotlin
package com.ondevice.offdevicetesting

import org.junit.Test

class <Name>Test : BaseTestClass("<package_name_of_the_application_you_want_to_test>") {

    @Test
    fun test_1() {
        // Number of iterations, you can set it to 1 for basic tests.
        val number_of_itr = 1
        val test_case_name = "Name of the test case"

        // Execute the test and store the result
        val result = executeTest(::function_that_performs_the_test, number_of_itr, test_case_name)

        // Assert the result
        assertEquals("The test went well", result)
    }

    // Function to perform the test logic
    private fun function_that_performs_the_test(iterations: Int): Pair<Boolean, String> {
        // Add your test logic here

        // Return a Pair indicating the result (Boolean) and a message (String)
        return Pair(true, "The test went well")
    }
}
```

### Notes:
- The `@Test` annotation indicates the function is a test.
- The `function_that_performs_the_test` method contains the logic for your test. It returns a `Pair` where the first value (`Boolean`) shows if the test passed, and the second (`String`) contains the success or error message.
- The variable `number_of_itr` is set to `1` by default. Modify this as needed, but for most cases, 1 iteration is sufficient.

## 3. Running a Test Case

Before running a test case, ensure the application you want to test is manually launched. Once the application is running, you can start executing the test case.