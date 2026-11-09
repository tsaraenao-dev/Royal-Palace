package com.royalpalace.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PoissonEngineTest {
    @Test
    fun zeroExpectedGoalsMeansZeroGoalsWithProbabilityOne() {
        assertEquals(1.0, PoissonEngine.probability(0, 0.0), 1e-9)
    }

    @Test
    fun scoreMatrixIsSortedFromMostLikely() {
        val scores = PoissonEngine.scoreMatrix(1.4, 1.1)
        assertTrue(scores.isNotEmpty())
        assertTrue(scores.zipWithNext().all { (a, b) -> a.probability >= b.probability })
    }

    @Test
    fun probabilitiesAreValid() {
        val p = PoissonEngine.probability(2, 1.5)
        assertTrue(p in 0.0..1.0)
    }
}
