package hu.sofian.aoc2024

import kotlin.math.abs

object GaussianElimination {
    private const val EPSILON = 1e-10

    // Gaussian elimination with partial pivoting
    fun lsolve(A: Array<DoubleArray>, b: DoubleArray): DoubleArray {
      //  val A = Array(inA.size) { row -> DoubleArray(inA[row].size) {inA[row][it]} }
      //  val b = inb.copyOf()
        val n = b.size

        for (p in 0..<n) {
            // find pivot row and swap

            var max = p
            for (i in p + 1..<n) {
                if (abs(A[i][p]) > abs(A[max][p])) {
                    max = i
                }
            }
            val temp = A[p]
            A[p] = A[max]
            A[max] = temp
            val t = b[p]
            b[p] = b[max]
            b[max] = t

            // singular or nearly singular
            if (abs(A[p][p]) <= EPSILON) {
                throw ArithmeticException("Matrix is singular or nearly singular")
            }

            // pivot within A and b
            for (i in p + 1..<n) {
                val alpha = A[i][p] / A[p][p]
                b[i] -= alpha * b[p]
                for (j in p..<n) {
                    A[i][j] -= alpha * A[p][j]
                }
            }
        }

        // back substitution
        val x = DoubleArray(n)
        for (i in n - 1 downTo 0) {
            var sum = 0.0
            for (j in i + 1..<n) {
                sum += A[i][j] * x[j]
            }
            x[i] = (b[i] - sum) / A[i][i]
        }
        return x
    }
}