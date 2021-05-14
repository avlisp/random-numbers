package com.ingoox.random

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var minInput: TextInputLayout
    private lateinit var maxInput: TextInputLayout
    private val randomizer = Randomizer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references for views.
        resultTextView = findViewById(R.id.result_text)
        minInput = findViewById(R.id.min_edit)
        maxInput = findViewById(R.id.max_edit)

        // Restore UI state.
        savedInstanceState?.getString("result")?.let { resultTextView.text = it }
        savedInstanceState?.getString("min")?.let { setInput(minInput, it) }
        savedInstanceState?.getString("max")?.let { setInput(maxInput, it) }

        val generateButton: Button = findViewById(R.id.generate_button)
        generateButton.setOnClickListener { generate() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", resultTextView.text.toString())
        outState.putString("min", getInput(minInput))
        outState.putString("max", getInput(maxInput))
    }

    private fun generate() {
        val randomNumber = if (hasInput(minInput) and hasInput(maxInput)) {
            val min = getInput(minInput).toInt()
            val max = getInput(maxInput).toInt()

            // Invert min and max on the inputs if necessary.
            if (min > max) {
                setInput(minInput, max.toString())
                setInput(maxInput, min.toString())
            }
            randomizer.random(min, max)
        } else {
            setInput(minInput, "1")
            setInput(maxInput, "6")
            randomizer.random(1, 6)
        }

        resultTextView.text = randomNumber.toString()
    }

    /** Returns the text that inputLayout is displaying. */
    private fun getInput(inputLayout: TextInputLayout): String {
        return inputLayout.editText?.text.toString()
    }

    /** Sets the text to be displayed on inputLayout. */
    private fun setInput(inputLayout: TextInputLayout, text: String) {
        inputLayout.editText?.setText(text)
    }

    /** Returns true if inputLayout has some text. */
    private fun hasInput(inputLayout: TextInputLayout): Boolean {
        return inputLayout.editText?.text.toString().isNotBlank()
    }
}
