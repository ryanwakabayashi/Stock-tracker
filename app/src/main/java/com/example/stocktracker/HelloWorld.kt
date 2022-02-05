package com.example.stocktracker

import kotlinx.coroutines.*
import yahoofinance.Stock
import yahoofinance.YahooFinance
import java.lang.IndexOutOfBoundsException
import java.math.BigDecimal
import kotlin.random.Random

fun main(){
    runBlocking {
//        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//            println("Exception handled: ${throwable.localizedMessage}")
//        }
//
//        val job = GlobalScope.launch(Dispatchers.IO + myHandler) {
//            println("Throwing exception from job")
//            throw IndexOutOfBoundsException("exception in coroutine")
//        }
//        job.join()

        val deffered = GlobalScope.async {
            println("Throwing exception from async")
            throw ArithmeticException("Exception from async")
        }

        try {
            deffered.await()
        } catch (e: ArithmeticException){
            println("Caught ArithmeticException ${e.localizedMessage}")
        }

    }


}


suspend fun getStocks() : BigDecimal {
    var st : BigDecimal = BigDecimal("1")
    var price : BigDecimal = BigDecimal("2")
    var stock : Stock = YahooFinance.get("TSLA")
    price = stock.quote.price
    return price
}