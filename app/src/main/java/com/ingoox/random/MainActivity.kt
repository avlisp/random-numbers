package com.ingoox.random

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    private lateinit var numberTextView: TextView
    private lateinit var minEditText: EditText
    private lateinit var maxEditText: EditText
    private val randomize = Randomize()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberTextView = findViewById(R.id.number_text)
        minEditText = findViewById(R.id.min_edit)
        maxEditText = findViewById(R.id.max_edit)

        val randomizeButton: Button = findViewById(R.id.randomize_button)
        randomizeButton.setOnClickListener { randomize() }
    }

    private fun randomize()
    {
        var range = 1..6

        if (hasInput()) range = getInput()
        if (isInverted(range)) range = invert(range)

        updateInput(range)
        val number = randomize.getRandomNumber(range)
        updateOutput(number)
    }

    /* Return true if user entered a range, false otherwise. */
    private fun hasInput(): Boolean
    {
        return minEditText.text.isNotBlank() and maxEditText.text.isNotBlank()
    }

    private fun getInput(): IntRange
    {
        return getInt(minEditText)..getInt(maxEditText)
    }

    private fun getInt(editText: EditText): Int
    {
        return editText.text.toString().toInt()
    }

    /* Update input views with a given range. */
    private fun updateInput(range: IntRange)
    {
        minEditText.setText(range.first.toString())
        maxEditText.setText(range.last.toString())
    }

    private fun updateOutput(number: Int)
    {
        numberTextView.text = number.toString()
    }

    /* Return true if first value of range is greater than the last one. */
    private fun isInverted(range: IntRange): Boolean
    {
        return range.first > range.last
    }

    private fun invert(range: IntRange): IntRange
    {
        return range.last..range.first
    }
}
