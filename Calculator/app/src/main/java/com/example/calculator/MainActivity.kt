package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var numberButtons: LinearLayout

    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private val half = 0.5
    private var operator: String = ""
    private var scientificCalculatorOperator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        setUpButtons()
    }

    private fun setUpButtons() {
        // Set up the number buttons
        numberButtons = findViewById(R.id.number_buttons)
        for (i in 0 until 10) {
            val button = numberButtons.findViewById<Button>(resources.getIdentifier("button_$i", "id", packageName))
            button?.setOnClickListener { onNumberButtonClick(i) }
        }

        // Set up the operator buttons
        val add=findViewById<Button>(R.id.plus_button)
        add?.setOnClickListener{onOperatorButtonClick("+")}
        val sub=findViewById<Button>(R.id.minus_button)
        sub?.setOnClickListener{onOperatorButtonClick("-")}
        val mul=findViewById<Button>(R.id.multiply_button)
        mul?.setOnClickListener{onOperatorButtonClick("*")}
        val div=findViewById<Button>(R.id.divide_button)
        div?.setOnClickListener{onOperatorButtonClick("/")}
        val power=findViewById<Button>(R.id.power_button)
        power?.setOnClickListener{onOperatorButtonClick("^")}
        val root=findViewById<Button>(R.id.square_root_button)
        root?.setOnClickListener{onOperatorButtonClick("sqrt")}

        // Set up the scientific calculator buttons
        val sine=findViewById<Button>(R.id.sin_button)
        sine?.setOnClickListener{onOperatorButtonClick("sin")}
        val cos=findViewById<Button>(R.id.cos_button)
        cos?.setOnClickListener{onOperatorButtonClick("cos")}
        val tan=findViewById<Button>(R.id.tan_button)
        tan?.setOnClickListener{onOperatorButtonClick("tan")}
        val inv=findViewById<Button>(R.id.inv_button)
        inv?.setOnClickListener{onOperatorButtonClick("inv")}
        val e=findViewById<Button>(R.id.e_button)
        e?.setOnClickListener{onOperatorButtonClick("e")}
        val pi=findViewById<Button>(R.id.pi_button)
        pi?.setOnClickListener{onOperatorButtonClick("pi")}
        val deg=findViewById<Button>(R.id.deg_button)
        deg?.setOnClickListener{onOperatorButtonClick("deg")}
        val log=findViewById<Button>(R.id.log_button)
        log?.setOnClickListener{onOperatorButtonClick("log")}
        val ac=findViewById<Button>(R.id.ac_button)
        ac?.setOnClickListener{clearAll()}
        val decimal = findViewById<Button>(R.id.decimal_button)
        decimal?.setOnClickListener { onDecimalButtonClick() }
        val equalsButton = findViewById<Button>(R.id.equals_button)
        equalsButton.setOnClickListener { calculate() }

        val backspaceButton = findViewById<Button>(R.id.backspace_button)
        backspaceButton.setOnClickListener {
            backspace()
        }
    }

    private fun backspace() {
        val displayText = display.text.toString()
        if (displayText.isNotEmpty()) {
            display.text = displayText.substring(0, displayText.length - 1)
        }
    }

    private fun onDecimalButtonClick() {
        display.text.toString()
        display.append(".")

    }
    private fun onNumberButtonClick(number: Int) {
        display.append(number.toString())
    }

    private fun onOperatorButtonClick(operator: String) {
        this.operator = operator
        display.append(operator)
    }

    private fun calculate() {
        val input = display.text.toString()
        val parts = input.split(Regex("[-+*/^]"))
        if (parts.size == 2) {
            firstNumber = parts[0].toDouble()
            secondNumber = parts[1].toDouble()

        }

        val result: Double = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            "^" -> firstNumber.pow(secondNumber)
            "sqrt" -> firstNumber.pow(half)
            "sin" -> sin(Math.toRadians(firstNumber))
            "cos" -> cos(Math.toRadians(firstNumber))
            "tan" -> tan(Math.toRadians(firstNumber))
            "inv" -> 1/firstNumber
            "e" -> exp(1.0)
            "pi" -> Math.PI
            "deg" -> Math.toDegrees(firstNumber)
            "log" -> ln(firstNumber)
            else -> 0.0
        }
        display.text = result.toString()

    }

    private fun clearAll() {
        display.text = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operator = ""
        scientificCalculatorOperator = ""
    }
}
