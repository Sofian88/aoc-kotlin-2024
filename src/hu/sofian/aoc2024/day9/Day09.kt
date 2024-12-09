package hu.sofian.aoc2024.day9

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput
import hu.sofian.aoc2024.swap

fun main() {

    fun parseInput(input: List<String>): List<Int> {
        val diskMap = mutableListOf<Int>()
        input.forEach { line ->
            line.forEachIndexed { index, c ->
                val isSpace = (index % 2) != 0
                if (isSpace) {
                    diskMap.addAll(Array(c.digitToInt()) { -1 }.toList())
                } else {
                    diskMap.addAll(Array(c.digitToInt()) { index / 2 }.toList())
                }
            }
        }
        return diskMap
    }

    fun parseInputAsList(input: List<String>): List<List<Int>> {
        val diskMap = mutableListOf<List<Int>>()
        input.forEach { line ->
            line.forEachIndexed { index, c ->
                val isSpace = (index % 2) != 0
                if (isSpace) {
                    diskMap.add(Array(c.digitToInt()) { -1 }.toList())
                } else {
                    diskMap.add(Array(c.digitToInt()) { index / 2 }.toList())
                }
            }
        }
        diskMap.removeIf { it.isEmpty() }
        return diskMap
    }

    tailrec fun reorder(diskMap: MutableList<Int>) {
        val swapCoordinates = diskMap.indexOfFirst { it == -1 } to diskMap.indexOfLast { it != -1 }
        if (swapCoordinates.first >= swapCoordinates.second) return
        diskMap.swap(swapCoordinates.first, swapCoordinates.second)
        reorder(diskMap)
    }

    tailrec fun defragment(diskMap: MutableList<List<Int>>, processed: MutableSet<Int>) {
        val lastFile = diskMap.findLast { it[0] != -1 && !processed.contains(it[0]) }
        if (lastFile == null) return
        for (i in diskMap.indices) {
            if (diskMap[i][0] == -1 && diskMap[i].size >= lastFile.size) {
                if (i < diskMap.indexOf(lastFile)) {
                    // split the interval if necessary
                    if (diskMap[i].size > lastFile.size) {
                        diskMap.add(i + 1, generateSequence { -1 }.take(diskMap[i].size - lastFile.size).toList())
                        diskMap[i] = generateSequence { -1 }.take(lastFile.size).toList()
                    }
                    diskMap.swap(i, diskMap.indexOf(lastFile))
                }
                break
            }
        }
        processed.add(lastFile[0])
        defragment(diskMap, processed)
    }


    fun part1(input: List<String>): Long {
        val diskMap = parseInput(input).toMutableList()
        reorder(diskMap)
        return diskMap.foldIndexed(0L) { index, acc, c ->
            if (c != -1) {
                acc + (c * index)
            } else {
                acc
            }
        }
    }


    fun part2(input: List<String>): Long {
        val diskMap = parseInputAsList(input).toMutableList()
        defragment(diskMap, mutableSetOf())
        val sum = diskMap.flatten().foldIndexed(0L) { index, acc, c ->
            if (c != -1) {
                acc + (c * index)
            } else {
                acc
            }
        }
        return sum
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day09_test"))) == 1928L)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day09_test"))) == 2858L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
