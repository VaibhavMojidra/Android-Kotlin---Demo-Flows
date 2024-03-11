package com.vaibhavmojidra.androidkotlindemoflows

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myFlow= flow<Int> {
            for (i in 1..100){
                emit(i) //This is producer
                delay(1000L)
            }
        }

        val textView=findViewById<TextView>(R.id.myTextView)
        CoroutineScope(Dispatchers.Main).launch {
            myFlow.collect{
                textView.text="Current value is $it" //This is consumer
            }
        }
    }
}