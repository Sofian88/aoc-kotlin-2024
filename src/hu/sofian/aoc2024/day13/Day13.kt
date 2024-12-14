package hu.sofian.aoc2024.day13

import hu.sofian.aoc2024.GaussianElimination.lsolve
import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput
import java.math.BigDecimal
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun main() {
    fun parseInput(input: List<String>): MutableList<Pair<Array<DoubleArray>, DoubleArray>> {

        val equations = mutableListOf<Pair<Array<DoubleArray>, DoubleArray>>()
        for (equation in input.windowed(4, 4, true)) {
            val aRow = equation[0].split("+").drop(1).map { it.split(",")[0].toDouble() }
            val a = aRow[0] to aRow[1]
            val bRow = equation[1].split("+").drop(1).map { it.split(",")[0].toDouble() }
            val b = bRow[0] to bRow[1]
            val destRow = equation[2].split("=").drop(1).map { it.split(",")[0].toDouble() }
            val dest = destRow[0] to destRow[1]
            equations.add(
                arrayOf(
                    doubleArrayOf(
                        a.first, b.first
                    ),
                    doubleArrayOf(
                        a.second, b.second
                    )
                ) to doubleArrayOf(dest.first, dest.second)
            )
        }
        return equations
    }

    fun parseDoubleInput(input: List<String>): MutableList<Pair<Array<DoubleArray>, DoubleArray>> {

        val equations = mutableListOf<Pair<Array<DoubleArray>, DoubleArray>>()
        for (equation in input.windowed(4, 4, true)) {
            val aRow = equation[0].split("+").drop(1).map { (it.split(",")[0].toDouble()) }
            val a = aRow[0] to aRow[1]
            val bRow = equation[1].split("+").drop(1).map { it.split(",")[0].toDouble() }
            val b = bRow[0] to bRow[1]
            val destRow = equation[2].split("=").drop(1).map { (it.split(",")[0].toLong() + 10000000000000)  }
            val dest = destRow[0] to destRow[1]
            equations.add(
                arrayOf(
                    doubleArrayOf(
                        a.first, b.first
                    ),
                    doubleArrayOf(
                        a.second, b.second
                    )
                ) to doubleArrayOf(dest.first.toDouble(), dest.second.toDouble())
            )
        }
        return equations
    }

    fun part1(input: List<String>): Long {
        val equations = parseInput(input)
        var sumOfResults = 0L
        equations.forEach {
            val result = lsolve(it.first, it.second)
            val x = result.first().roundToInt()
            val y = result.last().roundToInt()
            if (x <= 100 && y <= 100) {
                if (it.first.first().first().roundToInt() * x + it.first.first().last()
                        .roundToInt() * y == it.second.first().roundToInt()
                ) {
                    sumOfResults += x * 3 + y
                }
            }
        }
        return sumOfResults
    }

    fun part2(input: List<String>): BigDecimal {
        val equations = parseDoubleInput(input)
        var sumOfResults = BigDecimal(0)
        equations.forEachIndexed { index, equation ->
            val result = lsolve(equation.first, equation.second )
            val x = result.first().roundToLong()
            val y = result.last().roundToLong()
            if ((equation.first.first().first().toLong() * x + equation.first.first().last().toLong()
                        * y) == equation.second.first().toLong()
            ) {
                sumOfResults += ((x * 3) + y).toBigDecimal()
            }
        }
        return sumOfResults
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day13_test"))) == 480L)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day13_test"))) >= BigDecimal(480))

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
