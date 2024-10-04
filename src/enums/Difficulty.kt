package enums

enum class Difficulty {
    EASY,
    NORMAL,
    HARD;

    companion object {
        private val map: Map<String, Difficulty> = mapOf("easy" to EASY, "normal" to NORMAL, "hard" to HARD, "1" to EASY, "2" to NORMAL, "3" to HARD)

        fun get(difficulty: String?) : Difficulty? {
            return map[difficulty?.lowercase()]
        }


        fun getKeys() : Set<String> {
            return map.keys
        }
    }
}