package hu.sofian.aoc2024.day6

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput


val directions = listOf('^' to Pair(0, -1), '>' to Pair(1, 0), 'ˇ' to Pair(0, 1), '<' to Pair(-1, 0))
var start: Pair<Pair<Int, Int>, Pair<Int, Int>>? = null
private operator fun Pair<Int, Int>.plus(second: Pair<Int, Int>) =
    this.first + second.first to this.second + second.second


fun main() {

    fun parseInput(input: List<String>): Array<CharArray> {
        start = null
        return Array(input.size) { row ->
            CharArray(input[row].length) { column ->
                if (start == null) {
                    val startingPoint = directions.find { input[column][row] == it.first }
                    if (startingPoint != null) {
                        start = (row to column) to startingPoint.second
                    }
                }
                input[column][row]
            }
        }
    }

    tailrec fun visit(
        currentPosition: Pair<Pair<Int, Int>, Pair<Int, Int>>,
        visitedNodes: MutableSet<Pair<Int, Int>>,
        map: Array<CharArray>
    ) {
        visitedNodes.add(currentPosition.first)
        val next = currentPosition.first + currentPosition.second
        if(next.first !in map.first().indices || next.second !in map.indices) return
        if (map[next.first][next.second] == '#') {
            val nextDirection = (directions.indexOfFirst { it.second == currentPosition.second } + 1) % directions.size
            directions[nextDirection].first.println()
            val rotatePosition =
                currentPosition.first + directions[nextDirection].second to directions[nextDirection].second
            visit(rotatePosition, visitedNodes, map)
        } else {
            visit(next to currentPosition.second, visitedNodes, map)
        }
    }

    fun part1(input: List<String>): Int {
        val map = parseInput(input)
        if (start == null) throw Exception("Part 1 failed, missing start")
        val currentPosition = start!!
        val visitedNodes = mutableSetOf(start!!.first)
        visit(currentPosition, visitedNodes, map)
        return visitedNodes.size
    }


    fun part2(input: List<String>): Long {
        parseInput(input)

        return 0
    }


    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day06_test"))) == 41)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day06_test"))) == 0L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
