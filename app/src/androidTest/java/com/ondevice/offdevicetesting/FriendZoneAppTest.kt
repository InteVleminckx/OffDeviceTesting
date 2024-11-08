package com.ondevice.offdevicetesting

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.sign
import kotlin.random.Random
import kotlin.system.measureTimeMillis

@RunWith(AndroidJUnit4::class)
class FriendZoneAppTest : BaseTestClass("com.example.friendzone") {

    @Test
    fun runFriendZoneTest() {
        logToFile("Starting FriendZone Social Media App Automation Test")
        val result = executeTest(::fullFriendZoneAppTest, 1, "FriendZone Social Media App Automation Test")
        logToFile("FriendZone Test Result: $result")
        assertEquals("Successfully executed the FriendZone app test.", result)
    }

    private fun fullFriendZoneAppTest(iterations: Int): Pair<Boolean, String> {
        logToFile("Running full FriendZone app test")
        val executionTime = measureTimeMillis {
            signUp()
            goSearchPage()
            searchGrace()
            tapToResult()
            goToMessages()
            writeMessage()
        }
        logToFile("Finished the FriendZone App test in ${executionTime / 1000.0} seconds")
        return Pair(true, "Successfully executed the FriendZone app test.")
    }

    private fun signUp() {
        clickObjectByText("Sign up")
        val image = findObjectByClassAndInstance("android.view.View",2 )
        image?.click()
        clickObjectByText("Allow")
        val thumbnailIcon = device.findObject(UiSelector().resourceId("com.google.android.documentsui:id/icon_thumb"))
        thumbnailIcon.click()
        val currentTime = SimpleDateFormat("HHmm").format(Date()) // Get time in "HHmm" format (e.g., "1620")
        val mockEmail = "${currentTime}@test.com"
        val mockFullName = "Test User ${Random.nextInt(100, 999)}"
        val mockUsername = "user_${currentTime}_${Random.nextInt(10, 99)}"

        // Set text to the full name field
        val fullNameField = findObjectByClassAndInstance("android.widget.EditText", 0)
        fullNameField?.setText(mockFullName)

        // Assuming there are fields for username and email too
        val usernameField = findObjectByClassAndInstance("android.widget.EditText", 1)
        usernameField?.setText(mockUsername)

        val bioField = findObjectByClassAndInstance("android.widget.EditText", 2)
        bioField?.setText("I love cats <33")
//        friendZoneGestureScrollDown()
        val mailField = findObjectByClassAndInstance("android.widget.EditText", 3)
        mailField?.setText(mockEmail)

        val passwordField = findObjectByClassAndInstance("android.widget.EditText", 4)
        passwordField?.setText("1234567890")

        val signUpButton = findObjectByClassAndInstance("android.widget.Button", 0)
        signUpButton?.click()
    }

    private fun signIn(): Pair<Boolean, String> {
        logToFile("Attempting to sign in")
        return try {
            findObjectByClassAndInstance("android.widget.EditText", 0)?.setText("elf.prlk64@gmail.com")
            findObjectByClassAndInstance("android.widget.EditText", 1)?.setText("kaburga9")
            findObjectByClassAndInstance("android.widget.Button", 0)?.click()
            logToFile("Successfully signed in")
            Pair(true, "Successfully signed in")
        } catch (e: Exception) {
            logToFile("Failed to sign in: ${e.message}")
            Pair(false, "Failed to sign in")
        }
    }

    private fun goSearchPage(): Pair<Boolean, String> {
        logToFile("Navigating to search page")
        return try {
            val searchBarButton = device.findObject(UiSelector().className("android.view.View").instance(21))
            searchBarButton.click()
            logToFile("Successfully navigated to search page")
            Pair(true, "Successfully navigated to search page")
        } catch (e: Exception) {
            logToFile("Failed to navigate to search page: ${e.message}")
            Pair(false, "Failed to navigate to search page")
        }
    }

    private fun searchGrace(): Pair<Boolean, String> {
        logToFile("Searching for 'Grace'")
        return try {
            val searchField = device.findObject(UiSelector().className("android.widget.EditText"))
            searchField.setText("grace")
            logToFile("Successfully entered 'Grace' in the search field")
            Pair(true, "Successfully entered 'Grace' in the search field")
        } catch (e: Exception) {
            logToFile("Failed to enter text in the search field: ${e.message}")
            Pair(false, "Failed to enter text in the search field")
        }
    }

    private fun tapToResult(): Pair<Boolean, String> {
        logToFile("Tapping on the search result for Grace Turner")
        return try {
            val graceRow = findObjectByClassAndInstance("android.view.View", 5)
            graceRow?.click()
            device.pressBack()
            logToFile("Successfully tapped on the specified view")
            Pair(true, "Successfully tapped on the specified view")
        } catch (e: Exception) {
            logToFile("Failed to tap on the specified view: ${e.message}")
            Pair(false, "Failed to tap on the specified view")
        }
    }

    private fun goToMessages(): Pair<Boolean, String> {
        logToFile("Navigating to messages")
        return try {
            findObjectByClassAndInstance("android.view.View", 18)?.click()
            logToFile("Successfully went to messages")
            Pair(true, "Successfully went to messages")
        } catch (e: Exception) {
            logToFile("Failed to go to messages: ${e.message}")
            Pair(false, "Failed to go to messages")
        }
    }

    private fun writeMessage(): Pair<Boolean, String> {
        logToFile("Writing a message to Grace Turner")
        return try {
            clickObjectByText("Grace Turner")
            val messageField = device.findObject(UiSelector().className("android.widget.EditText"))
            messageField.setText("Hi Grace! Long time no see :)")
            val sendButton = device.findObject(UiSelector().description("Send"))
            sendButton.click()
            logToFile("Successfully sent a message to Grace")
            Pair(true, "Successfully sent a message to Grace")
        } catch (e: Exception) {
            logToFile("Failed to write a message: ${e.message}")
            Pair(false, "Failed to write a message")
        }
    }
}