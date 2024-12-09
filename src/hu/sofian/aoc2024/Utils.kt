package hu.sofian.aoc2024

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

operator fun Pair<Int, Int>.plus(second: Pair<Int, Int>) =
    this.first + second.first to this.second + second.second

operator fun Pair<Int, Int>.minus(second: Pair<Int, Int>) =
    this.first - second.first to this.second - second.second


operator fun Pair<Int, Int>.contains(value: Pair<Int, Int>) : Boolean{
    return value.first in 0..this.first && value.second in 0..this.second
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int){
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}