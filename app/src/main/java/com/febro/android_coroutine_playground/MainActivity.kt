package com.febro.android_coroutine_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main() = runBlocking {
    joinAll(
        async { coroutine(1, 500) },
        async {  coroutine(2, 300) }
    )
}

suspend fun coroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work")
    delay(delay)
    println("Coroutine $number has finished")
}
