package com.ingoox.random

class Randomize
{
    private var randomNumber = 0
    private var previousNumber = 0

    /* Return a random number different from the previous one. */
    fun getRandomNumber(range: IntRange = 1..10): Int
    {
        if (range.first == range.last) return range.first

        while (randomNumber == previousNumber)
        {
            randomNumber = range.random()
        }
        previousNumber = randomNumber

        return randomNumber
    }
}
