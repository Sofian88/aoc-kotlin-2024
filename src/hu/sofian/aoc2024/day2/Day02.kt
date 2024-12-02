package hu.sofian.aoc2024.day2

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

val reports = mutableListOf<List<Int>>()

fun main() {
    fun initialReading(input: List<String>) {
        reports.clear()
        for (line in input) {
            reports += line.split(" ").map { it.toInt() }
        }
    }

    fun isRowCorrect(pairsToCheck: List<Int>): Boolean {
        val pairs = pairsToCheck.windowed(2, 1, false)
        return pairs.all { it[0] - it[1] in 1..3 } || pairs.all { it[1] - it[0] in 1..3 }
    }

    fun part1(input: List<String>): Int {
        initialReading(input)
        val result = reports.count { levels ->
            isRowCorrect(levels)
        }
        return result
    }

    fun part2(input: List<String>): Int {

        initialReading(input)
        val result = reports.count { levels ->
            for (i in 0..levels.lastIndex) {
                val listWithoutThisElement = levels.toMutableList().apply { removeAt(i) }
                if (isRowCorrect(listWithoutThisElement)) {
                    return@count true
                }
            }
            return@count false
        }

        return result
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day02_test"))) == 2)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day02_test"))) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
