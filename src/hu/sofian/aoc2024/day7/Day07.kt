package hu.sofian.aoc2024.day7

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput


data class Equation(val x: Long, val numbers: List<Long>)
enum class Type { SUM, MUL, CONCAT }


fun main() {

    fun parseInput(input: List<String>): List<Equation> {
        val equations = mutableListOf<Equation>()
        input.forEach { row ->
            with(row.split(":")) {
                equations.add(Equation(this[0].toLong(), this[1].trim().split(" ").map { it.toLong() }))
            }
        }
        return equations
    }

    fun rep(reps: MutableList<List<Type>>, size: Int, item: MutableList<Type>, count: Int) {
        if (count < size) {
            for (i in 0..<Type.entries.size) {
                if (count < item.size) {
                    item[count] = Type.entries[i]
                } else {
                    item += Type.entries[i]
                }
                rep(reps, size, item, count + 1)
            }
        } else {
            val finalItems = MutableList(item.size) { index: Int ->
                item[index]
            }
            reps.add(finalItems)
        }
    }

    fun part1(input: List<String>): Long {
        val equations = parseInput(input)
        var properEquationResults = 0L
        for (equation in equations) {
            val combinations = mutableListOf<List<Type>>()
            rep(combinations, equation.numbers.size - 1, ArrayList(equation.numbers.size - 1), 0)
            for (combination in combinations) {
                if (equation.numbers.reduceIndexed { index, acc, l ->
                        when (combination[index - 1]) {
                            Type.SUM -> acc + l
                            Type.MUL -> acc * l
                            else -> acc
                        }
                    } == equation.x) {
                    properEquationResults += equation.x
                    break
                }
            }
        }
        return properEquationResults

    }


    fun part2(input: List<String>): Long {
        val equations = parseInput(input)
        var properEquationResults = 0L
        for (equation in equations) {
            val combinations = mutableListOf<List<Type>>()
            rep(combinations, equation.numbers.size - 1, ArrayList(equation.numbers.size - 1), 0)
            for (combination in combinations) {
                if (equation.numbers.reduceIndexed { index, acc, l ->
                        when (combination[index - 1]) {
                            Type.SUM -> acc + l
                            Type.MUL -> acc * l
                            Type.CONCAT -> (acc.toString() + l.toString()).toLong()
                        }
                    } == equation.x) {
                    properEquationResults += equation.x
                    break
                }
            }
        }
        return properEquationResults
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day07_test"))) == 3749L)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day07_test"))) == 11387L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
