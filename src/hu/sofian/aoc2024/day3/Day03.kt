package hu.sofian.aoc2024.day3

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

val results = mutableListOf<Long>()
val pattern = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
val numbers = "[0-9]{1,3}".toRegex()

fun main() {

    fun part1(input: List<String>): Long {
        results.clear()
        for (line in input) {
            val matches = pattern.findAll(line)
            matches.map { it.value }.forEach {
                results += numbers.findAll(it).map { it.value.toLong() }.reduce(Long::times)
            }
        }
       return results.sum()
    }

    fun part2(input: List<String>): Int {


        return 0
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day03_test"))) == 161L)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day03_test"))) == 0)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
