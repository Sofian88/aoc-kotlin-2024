package hu.sofian.aoc2024.day5

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput


val rules = mutableListOf<Pair<Long, Long>>()
val updates = mutableListOf<List<Long>>()

fun main() {

    fun parseInput(input: List<String>) {
        rules.clear()
        updates.clear()
        input.subList(0, input.indexOf("")).forEach {
            val rulePair = it.split("|")
            rules += rulePair[0].toLong() to rulePair[1].toLong()
        }
        input.subList(input.indexOf("") + 1, input.size).forEach {
            updates += it.split(",").map { it.toLong() }
        }
    }


    fun part1(input: List<String>): Long {
        parseInput(input)
        return 0
    }

    fun part2(input: List<String>): Int {

        return 0
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day05_test"))) == 143L)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day05_test2"))) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
