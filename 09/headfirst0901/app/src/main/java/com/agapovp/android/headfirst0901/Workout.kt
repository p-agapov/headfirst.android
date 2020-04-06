package com.agapovp.android.headfirst0901

class Workout private constructor(
    val name: String,
    val description: String
) {
    override fun toString(): String {
        return this.name
    }

    companion object {
        val workouts = arrayOf(
            Workout(
                "The Limb Loosener",
                """5 Handstand push-ups
                    |10 1-legged squats
                    |15 Pull-ups
                """.trimMargin()
            ),
            Workout(
                "Core Agony",
                """100 Pull-ups
                    |100 Push-ups
                    |100 Sit-ups
                    |100 Squats
                """.trimMargin()
            ),
            Workout(
                "The Wimp Special",
                """5 Pull-ups
                    |10 Push-ups
                    |15 Squats
                """.trimMargin()
            ),
            Workout(
                "Strength and Length",
                """500 meter run
                    |21 x 1.5 pood kettleball swing
                    |21 x pull-ups
                """.trimMargin()
            )
        )
    }
}
