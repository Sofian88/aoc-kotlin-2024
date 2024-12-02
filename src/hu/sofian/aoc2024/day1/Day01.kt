package hu.sofian.aoc2024.day1

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput
import kotlin.math.abs

val leftList = mutableListOf<Long>()
val rightList = mutableListOf<Long>()

fun main() {


    fun initialReading(input: List<String>) {
        leftList.clear()
        rightList.clear()
        for (line in input) {
            line.split("   ").also {
                leftList += it[0].toLong()
                rightList += it[1].toLong()
            }
        }
    }

    fun part1(input: List<String>): Long {
        initialReading(input)
        leftList.sortBy { it }
        rightList.sortBy { it }

        val result = leftList.foldIndexed(0L) { index, acc, item ->
            abs(item - rightList[index]) + acc
        }

        return result
    }

    fun part2(input: List<String>): Long {
        initialReading(input)
        val result = leftList.fold(0L) { acc, item ->
            item * rightList.count { it == item } + acc
        }

        return result
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day01_test"))) == 11L)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day01_test"))) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
