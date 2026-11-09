package com.royalpalace.app

import kotlin.math.exp
import kotlin.math.pow

object PoissonEngine {
    fun probability(goals: Int, expectedGoals: Double): Double {
        require(goals >= 0) { "Goals cannot be negative" }
        require(expectedGoals >= 0.0 && expectedGoals.isFinite()) {
            "Expected goals must be a finite non-negative number"
        }
        var factorial = 1.0
        for (i in 2..goals) factorial *= i
        return exp(-expectedGoals) * expectedGoals.pow(goals) / factorial
    }

    fun scoreMatrix(homeXg: Double, awayXg: Double, maxGoals: Int = 6): List<ScoreProbability> {
        require(maxGoals >= 0)
        val result = mutableListOf<ScoreProbability>()
        for (home in 0..maxGoals) {
            for (away in 0..maxGoals) {
                result += ScoreProbability(
                    homeGoals = home,
                    awayGoals = away,
                    probability = probability(home, homeXg) * probability(away, awayXg)
                )
            }
        }
        return result.sortedByDescending { it.probability }
    }
}

data class ScoreProbability(
    val homeGoals: Int,
    val awayGoals: Int,
    val probability: Double
)
