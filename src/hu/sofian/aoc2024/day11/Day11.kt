package hu.sofian.aoc2024.day11

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput

fun main() {

    fun parseInputMapped(input: List<String>): MutableMap<Long, Long> {
        val stones = mutableMapOf<Long, Long>()
        input.forEach { line ->
            stones += line.split(" ").map { it.toLong() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        }
        return stones
    }

    tailrec fun blinkMapped(counter: Int, maxcount: Int, stones: MutableMap<Long, Long>): Long {
        counter.println()
        if (counter == maxcount) return stones.values.sum()
        val newMap = mutableMapOf<Long, Long>()
        val iterator = stones.keys.iterator()
        while (iterator.hasNext()) {
            val stone = iterator.next()
            if (stone == 0L) {
                newMap[1] = newMap.getOrDefault(1, 0) + stones[stone]!!
                continue
            }
            if (stone.toString().length % 2 == 0) {
                val splittableStone = stone.toString()
                val left = stone.toString().substring(0, (splittableStone.length / 2)).toLong()
                val right = stone.toString().substring(splittableStone.length / 2, splittableStone.length).toLong()
                newMap[left] = newMap.getOrDefault(left, 0) + stones[stone]!!
                newMap[right] = newMap.getOrDefault(right, 0) + stones[stone]!!
            } else {
                val newValue = stone * 2024L
                newMap[newValue] = newMap.getOrDefault(newValue, 0) + stones[stone]!!
            }
        }
        return blinkMapped(counter + 1, maxcount, newMap)
    }

    fun part1(input: List<String>): Long {
        val stones = parseInputMapped(input)
        val result = blinkMapped(0, 25, stones)
        return result
    }

    fun part2(input: List<String>): Long {
        val stones = parseInputMapped(input)
        return blinkMapped(0, 75, stones)
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day11_test"))) == 55312L)


// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
