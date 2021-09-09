package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch { downloadUserData() }
        }

        //If you use that code, it can crash 10th click. So we need to Coroutine functions.
        /*btnDownloadUserData.setOnClickListener {
           downloadUserData()
        }*/
    }

    private suspend fun downloadUserData() {

        // If you visible this code, you can see thread names in log.
        //Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

        //If you visible this code, you can see thread names on ui.
        withContext(Dispatchers.Main) {
            for (i in 1..200000) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
                delay(100)
            }
        }

    }
}
