package com.ondevice.offdevicetesting

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiSelector
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureTimeMillis

@RunWith(AndroidJUnit4::class)
class NewsAppTest : BaseTestClass("kmp.news.app") {

    @Test
    fun runNewsAppTest() {
        logToFile("Starting News App Automation Test")
        val result = executeTest(::fullNewsAppTest, 1, "News App Automation Test")
        logToFile("News App Test Result: $result")
        assertEquals("Successfully executed the news app test.", result)
    }

    private fun fullNewsAppTest(iterations: Int): Pair<Boolean, String> {
        logToFile("Running full news app test")
        val executionTime = measureTimeMillis {
            gestureScrollDown()
            val specificViewResult = clickSpecificView(10)
            if (!specificViewResult.first) return specificViewResult
            device.pressBack()
            searchInNewsApp()
            goToHome()
        }
        logToFile("Finished the News App test in ${executionTime / 1000.0} seconds")
        return Pair(true, "Successfully executed the news app test.")
    }

    private fun clickSpecificView(instanceNumber: Int): Pair<Boolean, String> {
        logToFile("Attempting to click specific view with instance number $instanceNumber")
        repeat(3) { attempt ->
            try {
                device.waitForIdle(timeout)
                val obj = findObjectByClassAndInstance("android.view.View", instanceNumber)
                if (obj != null) {
                    obj.click()
                    logToFile("Successfully clicked on the specific instance of android.view.View")
                    return Pair(true, "Successfully clicked on the specific instance of android.view.View")
                } else {
                    logToFile("Object found but not interactable. Retrying...")
                }
            } catch (e: Exception) {
                logToFile("Attempt $attempt failed with exception: ${e.message}")
                restartUiAutomationService()
                Thread.sleep(5000)
            }
        }
        logToFile("Failed to click on the specific instance of android.view.View after retries")
        return Pair(false, "Failed to click on the specific instance of android.view.View after retries")
    }

    private fun searchInNewsApp(): Pair<Boolean, String> {
        logToFile("Attempting to search in news app")
        return try {
            val searchButton = device.findObject(UiSelector().text("Search"))
            searchButton.click()

            val searchField = device.findObject(UiSelector().text("Search"))
            searchField.click()

            fillEditText("London")
            clickSpecificView(7)
            device.pressBack()
            clickSpecificView(11)
            saveNewsArticle()
            logToFile("Successfully searched and clicked on news")
            Pair(true, "Successfully searched and clicked on news")
        } catch (e: Exception) {
            logToFile("Failed to search in the news app: ${e.message}")
            Pair(false, "Failed to search in the news app")
        }
    }

    private fun fillEditText(text: String): Pair<Boolean, String> {
        logToFile("Filling EditText with text: $text")
        return try {
            val editText = device.findObject(UiSelector().className("android.widget.EditText"))
            editText.setText(text)
            logToFile("Successfully filled EditText with text: $text")
            Pair(true, "Successfully filled EditText with text: $text")
        } catch (e: Exception) {
            logToFile("Failed to fill EditText: ${e.message}")
            Pair(false, "Failed to fill EditText with text: $text")
        }
    }

    private fun saveNewsArticle(): Pair<Boolean, String> {
        logToFile("Attempting to save news article")
        return try {
            val savedTab = device.findObject(UiSelector().text("Saved"))
            savedTab.click()
            logToFile("Successfully saved and navigated to the saved news")
            Pair(true, "Successfully saved and navigated to the saved news")
        } catch (e: Exception) {
            logToFile("Failed to save the news article: ${e.message}")
            Pair(false, "Failed to save the news article")
        }
    }

    private fun restartUiAutomationService() {
        logToFile("Restarting UiAutomation service...")
        try {
            device.executeShellCommand("am force-stop com.android.commands.uiautomator")
            device.executeShellCommand("am start com.android.commands.uiautomator/.UiAutomatorRunner")
            logToFile("Successfully restarted UiAutomation service")
        } catch (e: Exception) {
            logToFile("Failed to restart UiAutomation service: ${e.message}")
        }
    }

    private fun goToHome(): Pair<Boolean, String> {
        logToFile("Navigating to home/news page")
        return try {
            val newsTab = device.findObject(UiSelector().text("News"))
            newsTab.click()
            logToFile("Successfully navigated to News page")
            Pair(true, "Successfully navigated to News page")
        } catch (e: Exception) {
            logToFile("Failed to navigate to News page: ${e.message}")
            Pair(false, "Failed to navigate to News page")
        }
    }
}
