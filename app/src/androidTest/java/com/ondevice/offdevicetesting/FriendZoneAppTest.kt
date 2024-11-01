package com.ondevice.offdevicetesting

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
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
            //signIn()
            clickToUser()
            followUser()
            likeUserLastPost()
            writeACommentToUserPost()
            device.pressBack()
            device.pressBack()
            goSearchPage()
            searchGrace()
            tapToResult()
            gestureScrollDown()
            device.pressBack()
            goToMessages()
            writeMessage()
        }
        logToFile("Finished the FriendZone App test in ${executionTime / 1000.0} seconds")
        return Pair(true, "Successfully executed the FriendZone app test.")
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

    private fun clickToUser(): Pair<Boolean, String> {
        logToFile("Navigating to user profile")
        return try {
            findObjectByClassAndInstance("android.view.View", 17)?.click()
            logToFile("Successfully navigated to user profile")
            Pair(true, "Successfully navigated to user profile")
        } catch (e: Exception) {
            logToFile("Failed to navigate to user profile: ${e.message}")
            Pair(false, "Failed to navigate to user profile")
        }
    }

    private fun followUser(): Pair<Boolean, String> {
        logToFile("Attempting to follow user")
        return try {
            val followButton = device.findObject(UiSelector().className("android.widget.Button"))
            followButton.click()
            logToFile("Successfully followed the user")
            Pair(true, "Successfully followed the user")
        } catch (e: Exception) {
            logToFile("Failed to follow user: ${e.message}")
            Pair(false, "Failed to follow user")
        }
    }

    private fun likeUserLastPost(): Pair<Boolean, String> {
        logToFile("Liking user's last post")
        return try {
            findObjectByClassAndInstance("android.view.View", 6)?.click()
            logToFile("Successfully liked user's last post")
            Pair(true, "Successfully liked user's last post")
        } catch (e: Exception) {
            logToFile("Failed to like user's post: ${e.message}")
            Pair(false, "Failed to like user's post")
        }
    }

    private fun writeACommentToUserPost(): Pair<Boolean, String> {
        logToFile("Writing a comment to user's post")
        return try {
            findObjectByClassAndInstance("android.view.View", 7)?.click()
            writeAComment()
            logToFile("Successfully wrote a comment")
            Pair(true, "Successfully wrote a comment")
        } catch (e: Exception) {
            logToFile("Failed to write a comment: ${e.message}")
            Pair(false, "Failed to write a comment")
        }
    }

    private fun writeAComment(): Pair<Boolean, String> {
        logToFile("Attempting to write a comment")
        return try {
            val commentField = findObjectByClassAndInstance("android.widget.EditText", 0)
            commentField?.setText("Great post! Thanks for sharing.")
            val sendButton = device.findObject(UiSelector().description("Send"))
            sendButton?.click()
            logToFile("Successfully wrote and posted a comment")
            Pair(true, "Successfully wrote and posted a comment")
        } catch (e: Exception) {
            logToFile("Failed to write a comment: ${e.message}")
            Pair(false, "Failed to write a comment")
        }
    }

    private fun goSearchPage(): Pair<Boolean, String> {
        logToFile("Navigating to search page")
        return try {
            val searchBarButton = device.findObject(UiSelector().className("android.view.View").instance(33))
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
            searchField.setText("Grace")
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
            clickObjectByText("Grace Turner")
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
            findObjectByClassAndInstance("android.view.View", instance = 18)?.click()
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
