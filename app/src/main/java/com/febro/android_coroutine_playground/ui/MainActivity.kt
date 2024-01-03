package com.febro.android_coroutine_playground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.febro.android_coroutine_playground.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

suspend fun main()  {
    val allNames = getUserFirstNames(listOf("mary", "john", "peter"))
    println(allNames)
// callCoroutine()
}

suspend fun callCoroutine() {
    coroutineScope {
        joinAll(
            async { coroutine(1, 500) },
            async {  coroutine(2, 300) }
        )
    }
}

suspend fun coroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work")
    delay(delay)
    println("Coroutine $number has finished")
}
// this is a new modification, dude! 123
// ASYNC AND AWAIT ALL TO RUN ALL COROUTINES AT ONCE
suspend fun getUserFirstNames(userIds: List<String>):List<String> {
    val firstNames = mutableListOf<Deferred<String>>()

    coroutineScope {
        for(id in userIds) {
            val firstName = async {
                getFirstName(id)
            }
            firstNames.add(firstName)
        }
    }

    return firstNames.awaitAll()
}

suspend fun getFirstName(userId: String = "john doe"): String {
    delay(1000L)
    return userId
}
