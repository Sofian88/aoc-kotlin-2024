package hu.sofian.aoc2024.day4

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

val xmas = arrayOf('X', 'M', 'A', 'S')
val x_mas = arrayOf('M', 'A', 'S')

typealias Direction = Pair<Short, Short>

fun main() {

    fun parseInput(input: List<String>): Array<CharArray> = Array(input.size) { columnIndex ->
        CharArray(input[columnIndex].length) { input[columnIndex][it] }
    }

    tailrec fun findXMas(
        row: Int,
        column: Int,
        currentCharIndex: Int,
        charMatrix: Array<CharArray>,
        direction: Direction
    ): Int {
        val nextCharIndex = currentCharIndex + 1
        if (row == -1 || column == -1 || row >= charMatrix.size || column >= charMatrix[row].size) return 0
        if (xmas[currentCharIndex] == charMatrix[row][column] && currentCharIndex == xmas.size - 1) return 1
        if (xmas[currentCharIndex] != charMatrix[row][column]) return 0
        return findXMas(row + direction.first, column + direction.second, nextCharIndex, charMatrix, direction)
    }


    fun findMas(
        row: Int,
        column: Int,
        charMatrix: Array<CharArray>,
    ): Int {
        if (row == 0 || column == 0 || row == charMatrix.size - 1 || column == charMatrix[row].size - 1) return 0
        if ((charMatrix[row - 1][column - 1] != charMatrix[row + 1][column + 1] &&
                    (charMatrix[row - 1][column - 1] == x_mas.first() || charMatrix[row - 1][column - 1] == x_mas.last())) &&
            (charMatrix[row + 1][column + 1] == x_mas.first() || charMatrix[row + 1][column + 1] == x_mas.last()) &&
            (charMatrix[row - 1][column + 1] != charMatrix[row + 1][column - 1] &&
                    (charMatrix[row - 1][column + 1] == x_mas.first() || charMatrix[row - 1][column + 1] == x_mas.last()) &&
                    (charMatrix[row + 1][column - 1] == x_mas.first() || charMatrix[row + 1][column - 1] == x_mas.last()))
        ) return 1
        return 0
    }

    fun part1(input: List<String>): Int {
        val charMatrix = parseInput(input)
        val startingPoints = mutableListOf<Pair<Int, Int>>()
        charMatrix.forEachIndexed { row, chars ->
            chars.forEachIndexed { column, c ->
                if (c == xmas[0]) {
                    startingPoints.add(Pair(row, column))
                }
            }
        }
        val wordCount = startingPoints.sumOf { (row, column) ->
            findXMas(row, column, 0, charMatrix, Direction(1, 0)) +
                    findXMas(row, column, 0, charMatrix, Direction(1, 1)) +
                    findXMas(row, column, 0, charMatrix, Direction(1, -1)) +
                    findXMas(row, column, 0, charMatrix, Direction(0, 1)) +
                    findXMas(row, column, 0, charMatrix, Direction(0, -1)) +
                    findXMas(row, column, 0, charMatrix, Direction(-1, 0)) +
                    findXMas(row, column, 0, charMatrix, Direction(-1, 1)) +
                    findXMas(row, column, 0, charMatrix, Direction(-1, -1))
        }

        return wordCount
    }

    fun part2(input: List<String>): Int {
        val charMatrix = parseInput(input)
        val startingPoints = mutableListOf<Pair<Int, Int>>()
        charMatrix.forEachIndexed { row, chars ->
            chars.forEachIndexed { column, c ->
                if (c == x_mas[1]) {
                    startingPoints.add(Pair(row, column))
                }
            }
        }
        val wordCount = startingPoints.sumOf { (row, column) ->
            findMas(row, column, charMatrix)
        }

        return wordCount
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day04_test"))) == 18)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day04_test2"))) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
