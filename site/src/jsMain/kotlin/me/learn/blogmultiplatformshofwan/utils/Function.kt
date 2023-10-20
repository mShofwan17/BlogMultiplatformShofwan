package me.learn.blogmultiplatformshofwan.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.localStorage
import me.learn.blogmultiplatformshofwan.navigation.Screen
import org.w3c.dom.get
import org.w3c.dom.set

@Composable
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    val remembered = remember { localStorage["remember"].toBoolean() }
    val userId = remember { localStorage["userId"] }
    var userIdExist by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        userIdExist = if (!userId.isNullOrEmpty()) checkUserId(id = userId) else false
        if (!remembered || !userIdExist) {
            context.router.navigateTo(Screen.Admin.Login.route)
        }
    }

    if (remembered && userIdExist) content()
    else println("Loading..")

}

fun logout(){
    localStorage["remember"] = false.toString()
    localStorage["userId"] = ""
    localStorage["username"] = ""
}