package hu.sofian.aoc2024.day6

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput


val directions = mapOf('^' to Pair(0, -1), '>' to Pair(1, 0), 'ˇ' to Pair(0, -1), '<' to Pair(-1, 0))
fun main() {

    fun parseInput(input: List<String>): Array<BooleanArray> = Array(input.size) { columnIndex ->
        BooleanArray(input[columnIndex].length) { input[columnIndex][it] == '#' }
    }

    fun findStartingPont(input: List<String>): Pair<Int, Int> {
        for (row in input.indices) {
            for (column in input[row].indices) {
                if (directions.keys.any { input[row][column] == it }) return column to row
            }
        }
        throw Exception("Invalid input")
    }

    fun part1(input: List<String>): Long {
        val map = parseInput(input)
        val startingPoint = findStartingPont(input)

        return 0
    }

    fun part2(input: List<String>): Long {
        parseInput(input)

        return 0
    }


    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day06_test"))) == 41L)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day06_test"))) == 0L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
