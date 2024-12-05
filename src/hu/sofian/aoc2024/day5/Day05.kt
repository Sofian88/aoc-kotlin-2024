package hu.sofian.aoc2024.day5

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput
import java.util.Collections


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
        val correctUpdates = mutableListOf<List<Long>>()
        updates.forEach { update ->
            if (update.all { pageNumber ->
                    val applicableRules = rules.filter { it.first == pageNumber }
                    applicableRules.all {
                        update.indexOf(it.second) == -1 || (update.indexOf(pageNumber) < update.indexOf(
                            it.second
                        ))
                    }
                }) {
                correctUpdates += update
            }
        }
        val result = correctUpdates.sumOf { it[(it.size - 1) / 2] }
        return result
    }

    fun part2(input: List<String>): Long {
        parseInput(input)
        val inCorrectUpdates = mutableListOf<List<Long>>()
        updates.forEach { update ->
            if (!update.all { pageNumber ->
                    val applicableRules = rules.filter { it.first == pageNumber }
                    applicableRules.all {
                        update.indexOf(it.second) == -1 || (update.indexOf(pageNumber) < update.indexOf(
                            it.second
                        ))
                    }
                }) {
                inCorrectUpdates += update
            }
        }
        val fixedUpdates = mutableListOf<List<Long>>()
        inCorrectUpdates.forEach { update ->
            val fixedUpdate = update.toMutableList()
            do {
                fixedUpdate.forEach { pageNumber ->
                    val applicableRule = rules.filter {
                        it.first == pageNumber && fixedUpdate.indexOf(it.second) != -1 && (fixedUpdate.indexOf(it.first) > fixedUpdate.indexOf(
                            it.second
                        ))
                    }.maxByOrNull { fixedUpdate.indexOf(it.second) }
                    if (applicableRule != null) {
                        Collections.swap(
                            fixedUpdate, fixedUpdate.indexOf(applicableRule.first), fixedUpdate.indexOf(
                                applicableRule.second
                            )
                        )
                    }
                }
            } while (!fixedUpdate.all { pageNumber ->
                    val applicableRules = rules.filter { it.first == pageNumber }
                    applicableRules.all {
                        fixedUpdate.indexOf(it.second) == -1 || (fixedUpdate.indexOf(it.first) < fixedUpdate.indexOf(
                            it.second
                        ))
                    }
                })
            fixedUpdates += fixedUpdate
        }

        val result = fixedUpdates.sumOf { it[(it.size - 1) / 2] }
        return result
    }


    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day05_test"))) == 143L)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day05_test"))) == 123L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
