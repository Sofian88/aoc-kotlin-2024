package hu.sofian.aoc2024.day8

import hu.sofian.aoc2024.*


data class Antenna(val name: Char, val position: Pair<Int, Int>)


fun main() {

    fun parseInput(input: List<String>): List<Antenna> {
        val antennas = mutableListOf<Antenna>()
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, columnValue ->
                if (columnValue != '.') {
                    antennas.add(Antenna(columnValue, Pair(x, y)))
                }
            }
        }
        return antennas
    }

    tailrec fun generateAntinodes(
        currentAntenna: Antenna,
        remainingAntennas: List<Antenna>,
        antinodes: MutableSet<Pair<Int, Int>>,
        rangeMax: Pair<Int, Int>
    ) {
        if (remainingAntennas.isEmpty()) return
        remainingAntennas.forEach {
            val diff = currentAntenna.position - it.position
            val antinode1 = diff + currentAntenna.position
            val antinode2 = it.position - diff
            if (antinode1 in rangeMax) {
                antinodes.add(antinode1)
            }

            if (antinode2 in rangeMax) {
                antinodes.add(antinode2)
            }
        }
        generateAntinodes(remainingAntennas.first(), remainingAntennas.drop(1), antinodes, rangeMax)
    }

    tailrec fun generateMultipleAntinodes(
        currentAntenna: Antenna,
        remainingAntennas: List<Antenna>,
        antinodes: MutableSet<Pair<Int, Int>>,
        rangeMax: Pair<Int, Int>
    ) {
        if (remainingAntennas.isEmpty()) return
        remainingAntennas.forEach {
            val diff = currentAntenna.position - it.position
            var antinode1 = diff + currentAntenna.position
            var antinode2 = it.position - diff

            while (antinode1 in rangeMax) {
                antinodes.add(antinode1)
                antinode1 += diff
            }

            while (antinode2 in rangeMax) {
                antinodes.add(antinode2)
                antinode2 -= diff
            }

            antinodes.add(currentAntenna.position)
            antinodes.add(it.position)
        }
        generateMultipleAntinodes(remainingAntennas.first(), remainingAntennas.drop(1), antinodes, rangeMax)
    }


    fun part1(input: List<String>): Int {
        val rangeMax = input.size - 1 to input[0].length - 1
        val frequencyGroups = parseInput(input).groupBy { it.name }.filter { it.value.size > 1 }
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        frequencyGroups.keys.forEach {
            val group = frequencyGroups[it]!!
            generateAntinodes(group.first(), group.drop(1), antinodes, rangeMax)
        }
        return antinodes.size
    }


    fun part2(input: List<String>): Int {
        val rangeMax = input.size - 1 to input[0].length - 1
        val frequencyGroups = parseInput(input).groupBy { it.name }.filter { it.value.size > 1 }
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        frequencyGroups.keys.forEach {
            val group = frequencyGroups[it]!!
            generateMultipleAntinodes(group.first(), group.drop(1), antinodes, rangeMax)
        }
        return antinodes.size
    }


// Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day08_test"))) == 14)

// Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day08_test"))) == 34)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
