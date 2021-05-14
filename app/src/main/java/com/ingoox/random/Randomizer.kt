package com.ingoox.random

class Randomizer {
    private var randomNumber = 0
    private var previousNumber = 0

    /** Returns a random number between a and b. Accepts b <= a. */
    fun random(a: Int, b: Int): Int {
        if (a == b) return a
        val range = if (a < b) a..b else b..a

        while (randomNumber == previousNumber) {
            randomNumber = range.random()
        }
        previousNumber = randomNumber
        return randomNumber
    }
}
