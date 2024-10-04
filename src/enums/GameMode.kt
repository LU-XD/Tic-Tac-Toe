package enums

enum class GameMode {
    SINGLEPLAYER,
    MULTIPLAYER;

    companion object {
        private val map: Map<Int, GameMode> = mapOf(1 to SINGLEPLAYER, 2 to MULTIPLAYER);

        fun get(mode: Int) : GameMode {
            return map.getValue(mode)
        }

    }
}