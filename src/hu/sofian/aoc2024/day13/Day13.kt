package hu.sofian.aoc2024.day13

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

fun main() {
    fun parseInput(input: List<String>): MutableList<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>> {

        val equations = mutableListOf<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>>()
        for (equation in input.windowed(4, 4, true)) {
            val aRow = equation[0].split("+").drop(1).map { it.split(",")[0].toInt() }
            val a = aRow[0] to aRow[1]
            val bRow = equation[1].split("+").drop(1).map { it.split(",")[0].toInt() }
            val b = bRow[0] to bRow[1]
            val destRow = equation[2].split("=").drop(1).map { it.split(",")[0].toInt() }
            val dest = destRow[0] to destRow[1]
            equations.add(Triple(a, b, dest))
        }
        return equations
    }

    fun part1(input: List<String>): Long {
        val equations = parseInput(input)
        return equations.size.toLong()
    }

    fun part2(input: List<String>): Long {
        return 0L
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day13_test"))) == 0L)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day13_test"))) == 0L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
