package hu.sofian.aoc2024.day6

import hu.sofian.aoc2024.plus
import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

val directions = listOf('^' to Pair(-1, 0), '>' to Pair(0, 1), 'ˇ' to Pair(1, 0), '<' to Pair(0, -1))
var start: Pair<Pair<Int, Int>, Pair<Int, Int>>? = null

fun main() {

    fun parseInput(input: List<String>): Array<CharArray> {
        start = null
        return Array(input.size) { row ->
            CharArray(input[row].length) { column ->
                if (start == null) {
                    val startingPoint = directions.find { input[row][column] == it.first }
                    if (startingPoint != null) {
                        start = (row to column) to startingPoint.second
                    }
                }
                input[row][column]
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
        if (next.first !in map.first().indices || next.second !in map.indices) return
        if (map[next.first][next.second] == '#') {
            val nextDirection =
                (directions.indexOfFirst { it.second == currentPosition.second } + 1) % directions.size
            val rotatePosition =
                currentPosition.first to directions[nextDirection].second
            visit(rotatePosition, visitedNodes, map)
        } else {
            visit(next to currentPosition.second, visitedNodes, map)
        }
    }

    tailrec fun findCircle(
        currentPosition: Pair<Pair<Int, Int>, Pair<Int, Int>>,
        map: Array<CharArray>,
        visitedItems: MutableSet<Pair<Pair<Int, Int>, Pair<Int, Int>>>
    ): Int {
        val next = currentPosition.first + currentPosition.second
        if (next.first !in map.indices || next.second !in map.first().indices) return 0

        return if (map[next.first][next.second] == '#') {
            val nextDirection =
                (directions.indexOfFirst { it.second == currentPosition.second } + 1) % directions.size
            val rotatePosition = //getRotatePosition(currentPosition, nextDirection, map)
                currentPosition.first to directions[nextDirection].second
            if (rotatePosition.first.first !in map.indices || rotatePosition.first.second !in map.first().indices) return 0
            if (visitedItems.contains(rotatePosition)) return 1
            visitedItems.add(rotatePosition)
            findCircle(rotatePosition, map, visitedItems)
        } else {
            if (visitedItems.contains(next to currentPosition.second)) return 1
            visitedItems.add(next to currentPosition.second)
            findCircle(next to currentPosition.second, map, visitedItems)
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


    fun part2(input: List<String>): Int {
        val map = parseInput(input)
        if (start == null) throw Exception("Part 2 failed, missing start")
        val currentPosition = start!!
        val visitedNodes = mutableSetOf(start!!.first)
        visit(currentPosition, visitedNodes, map)
        var obstacles = 0
        visitedNodes.forEach {
            val newMap = Array(map.size) { row ->
                CharArray(map[row].size) { column ->
                    if (row == it.first && column == it.second && it != currentPosition.first) {
                        '#'
                    } else {
                        map[row][column]
                    }
                }
            }
            val visitedItems = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>(currentPosition)
            obstacles += findCircle(currentPosition, newMap, visitedItems)
        }

        return obstacles
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day06_test"))) == 41)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day06_test"))) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
