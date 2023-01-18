package com.example.mindsharpener

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private var firstNumber: Int = 0
    private var secondNumber: Int = 0
    private var operator: String = ""
    private var point: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (R.id.radioGroup).setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton -> generateNumbers(1)
                R.id.radioButton2 -> generateNumbers(2)
                R.id.radioButton3 -> generateNumbers(3)
            }
        })

        button.setOnClickListener {
            val userAnswer = inputText.text.toString().toInt()
            val correctAnswer = calculateAnswer()
            if (userAnswer == correctAnswer) {
                point++
                Toast.makeText(this, "Correct! Points: $point", Toast.LENGTH_SHORT).show()
            } else {
                point--
                Toast.makeText(this, "Incorrect! Points: $point", Toast.LENGTH_SHORT).show()
            }
            generateQuestion()
        }
    }

    private fun generateNumbers(digit: Int) {
        val random = Random()
        (R.id.textView4) = random.nextInt(10.toDouble().pow(digit).toInt() - 1) + 1
        (R.id.textView6) = random.nextInt(10.toDouble().pow(digit).toInt() - 1) + 1
        (R.id.TextView5) = when (random.nextInt(4)) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> "+"
        }
        generateQuestion()
    }

    private fun generateQuestion() {
        editTextTextPersonName.text = "$(R.id.textView4) $(R.id.TextView5) $(R.id.textView6)"
    }

    private fun calculateAnswer(): Int {
        return when (R.id.TextView5) {
            "+" -> (R.id.textView4) + (R.id.textView6)
            "-" -> (R.id.textView4) - (R.id.textView6)
            "*" -> (R.id.textView4) * (R.id.textView6)
            "/" -> (R.id.textView4) / (R.id.textView6)
            else -> 0
        }
    }
}