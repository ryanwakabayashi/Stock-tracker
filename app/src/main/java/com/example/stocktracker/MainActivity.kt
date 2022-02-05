package com.example.stocktracker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import yahoofinance.Stock
import yahoofinance.YahooFinance

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tbutton1 = findViewById<Button>(R.id.pullStockButton)
        var test1 : Stock?

        val listener = View.OnClickListener { e ->
            coroutineScope.launch {
                val stockDeferred = coroutineScope.async(Dispatchers.IO) { getStock() }
                test1 = stockDeferred.await()
                Toast.makeText(this@MainActivity, test1.toString(), Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "main log ${test1}")
            }
        }
        tbutton1.setOnClickListener(listener)
    }
}

fun getStock() : Stock {
    return YahooFinance.get("ETH-USD")
}


