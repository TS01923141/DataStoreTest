package com.example.datastoretest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.datastoretest.R
import com.example.datastoretest.model.extension.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//參考https://developer.android.com/topic/libraries/architecture/datastore#kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val data = runBlocking { dataStore.data.first() }
        lifecycleScope.launch {
            dataStore.data.first()
            //You should also handle IOExceptions here.
        }
    }
}