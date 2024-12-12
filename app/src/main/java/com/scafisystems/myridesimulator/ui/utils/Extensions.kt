package com.scafisystems.myridesimulator.ui.utils

fun String.toMinutes(): String {
    return try {
        String.format(
            "%.2f",
            this.toInt() / 60.0
        )
    } catch (e: Exception) {
        this
    }
}

fun Double.toKm(): String {
    return try {
        String.format(
            "%.2f",
            this / 1000.0
        )
    } catch (e: Exception) {
        this.toString()
    }
}

fun String.toTwoChar(): String {
    return try {
        String.format(
            "%.2f",
            this.toDouble()
        )
    } catch (e: Exception) {
        this
    }
}