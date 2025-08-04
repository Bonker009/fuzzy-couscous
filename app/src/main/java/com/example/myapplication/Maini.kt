package com.example.myapplication
import kotlin.system.measureNanoTime

inline fun runInline(times: Int, action: () -> Unit) {
    repeat(times) {
        action()
    }
}

inline fun runWithNoinline(
    times: Int,
    noinline action: () -> Unit
) {
    repeat(times) {
        action()
    }
}

fun runNormal(times: Int, action: () -> Unit) {
    repeat(times) {
        action()
    }
}

fun main() {
    val iterations = 10_000_000
    var counter = 0

    println("Benchmark started...")

    // Inline test
    val inlineTime = measureNanoTime {
        runInline(iterations) {
            counter += 1
        }
    }
    println("Inline duration: ${inlineTime / 1_000_000_000.0} s")

    // Reset
    counter = 0

    // Noinline test
    val noinlineTime = measureNanoTime {
        runWithNoinline(iterations) {
            counter += 1
        }
    }
    println("Noinline duration: ${noinlineTime / 1_000_000_000.0} s")

    // Reset
    counter = 0

    // Normal test
    val normalTime = measureNanoTime {
        runNormal(iterations) {
            counter += 1
        }
    }
    println("Normal (non-inline) duration: ${normalTime / 1_000_000_000.0} s")
}
