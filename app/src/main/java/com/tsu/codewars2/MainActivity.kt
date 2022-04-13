package com.tsu.codewars2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tsu.codewars2.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val viewbinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewbinding.root)
        viewbinding.button2.setOnClickListener {


            val values = viewbinding.textView.text.toString()
            if(values.isEmpty())
            {
                viewbinding.textView2.text="Введите данные"
                throw NumberFormatException("Пустой массив, введите данные")
            }

            var numbers= parseEditText(values )
            var output: IntArray = intArrayOf()
            output = sumParts(numbers)
            viewModel.add(numbers.contentToString(), output.contentToString())


            viewbinding.textView2.text=output.joinToString()
        }

        initView()


    }

     fun parseEditText(values:String): IntArray {

        var numbers: IntArray = intArrayOf()
        val lstValues: List<String> = values.split(" ").map { it.trim() }
        var arr = lstValues.toTypedArray()
        println(arr[0])
        numbers = arr.map { it.toInt() }.toIntArray()
        println(numbers.contentToString())
         return numbers

    }


    private fun initView() = with(viewbinding) {
        viewModel.isError.observe(this@MainActivity){isError->
            if (isError==true) {
                Toast.makeText(this@MainActivity, "Ошибка записи", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun sumParts(ls: IntArray): IntArray {
        if (ls.isEmpty()) {
            throw NumberFormatException("Пустой массив, введите данные")
        }
        var count = 0
        if (ls.maxOrNull()!! < 0 && ls.minOrNull()!! == Int.MIN_VALUE) {
            throw IllegalArgumentException("Слишком маленькое число")
        }
        ls.forEach { if (it == Int.MIN_VALUE) count++ }

        if (count > 1) {
            throw IllegalArgumentException("Слишком маленькое число")
        }
        if (ls.maxOrNull() == Int.MAX_VALUE && ls.minOrNull()!! >= 0) {
            throw IllegalArgumentException("Слишком большое число")
        }

        var sumPartArray = IntArray(ls.size + 1) { 0 }
        ls.reverse()
        for (i in 1..ls.size) {
            sumPartArray[i] = sumPartArray[i - 1] + ls[i - 1]


        }
        sumPartArray.reverse()
        return sumPartArray
    }
}