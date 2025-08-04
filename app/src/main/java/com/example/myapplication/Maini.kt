package com.example.myapplication

import kotlin.system.measureNanoTime

inline fun runInline(times: Int, action: () -> Unit) {
    repeat(times) {
        action()
    }
}

fun runNoInline(times: Int, action: () -> Unit) {
    repeat(times) {
        action()
    }
}

fun main() {
    val iterations = 10_000_000
    var counter = 0

    println("Benchmark started...")


    val inlineDurationNs = measureNanoTime {
        runInline(iterations) {
            counter += 1
        }
    }
    println("Inline duration: ${inlineDurationNs / 1_000_000_000.0} s")

    counter = 0


    val noinlineDurationNs = measureNanoTime {
        runNoInline(iterations) {
            counter += 1
        }
    }
    println("Non-inline duration: ${noinlineDurationNs / 1_000_000_000.0} s")
}
