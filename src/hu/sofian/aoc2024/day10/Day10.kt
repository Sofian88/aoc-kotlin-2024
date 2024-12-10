package hu.sofian.aoc2024.day10

import hu.sofian.aoc2024.plus
import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

fun main() {

    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    val startingPoints = mutableSetOf<Pair<Int, Int>>()
    var results = 0L

    fun parseInput(input: List<String>): Array<IntArray> {
        return Array(input.size) { row ->
            IntArray(input[row].length) { column ->
                if (input[row][column] == '0') startingPoints.add(Pair(row, column))
                input[row][column].toString().toInt()
            }
        }
    }

    fun findTrailhead(
        currentPosition: Pair<Int, Int>,
        heightMap: Array<IntArray>,
        visited: MutableSet<Pair<Int, Int>>
    ) {
        visited.add(currentPosition)
        if (heightMap[currentPosition.first][currentPosition.second] == 9) {
            results++
            return
        }
        directions.forEach {
            val nextPosition = currentPosition + it
            if (nextPosition !in visited) {
                if (nextPosition.first in heightMap.indices && nextPosition.second in heightMap.first().indices) {
                    if (heightMap[nextPosition.first][nextPosition.second] - heightMap[currentPosition.first][currentPosition.second] == 1) {
                        findTrailhead(nextPosition, heightMap, visited)
                    }
                }
            }
        }
    }

    fun findDistinctTrailhead(
        currentPosition: Pair<Int, Int>,
        heightMap: Array<IntArray>
    ) {
        if (heightMap[currentPosition.first][currentPosition.second] == 9) {
            results++
            return
        }
        directions.forEach {
            val nextPosition = currentPosition + it
            if (nextPosition.first in heightMap.indices && nextPosition.second in heightMap.first().indices) {
                if (heightMap[nextPosition.first][nextPosition.second] - heightMap[currentPosition.first][currentPosition.second] == 1) {
                    findDistinctTrailhead(nextPosition, heightMap)
                }
            }
        }
    }

    fun part1(input: List<String>): Long {
        startingPoints.clear()
        val heightMap = parseInput(input)
        results = 0
        startingPoints.forEach {
            findTrailhead(it, heightMap, mutableSetOf())
        }
        return results
    }


    fun part2(input: List<String>): Long {
        startingPoints.clear()
        val heightMap = parseInput(input)
        results = 0
        startingPoints.forEach {
            findDistinctTrailhead(it, heightMap)
        }
        return results
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day10_test"))) == 36L)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day10_test"))) == 81L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
