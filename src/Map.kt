import util.Move

class Map {
    private var map: List<MutableList<Char>> = listOf(mutableListOf(' ', ' ', ' '), mutableListOf(' ', ' ', ' '), mutableListOf(' ', ' ', ' '))

    fun print() {
        for ((i, row) in map.withIndex()) {
            val rowString: String = " ".plus(row.joinToString(" | ")).removeSuffix("|")
            println(rowString)
            if (i < map.size - 1) {
                println("———————————")
            }
        }
    }

    fun doMove(move: Move) {
        map[move.row][move.column] = move.represent
    }

    fun moveIsValid(move: Move?) : Boolean {
        if (move == null) return false
        return map[move.row][move.column] == ' '
    }

    fun checkForEnd(): Char? {
        for (row in map) {
            if (row[0] == row[1] && row[1] == row[2]) {
                if (row[0] in listOf('X', 'O')) return row[0]
            }
        }

        for (i in 0..2) {
            if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                if (map[0][i] in listOf('X', 'O')) return map[0][i]
            }
        }

        if ((map[0][0] == map[1][1] && map[1][1] == map[2][2]) || (map[0][2] == map[1][1] && map[1][1] == map[2][0])) {
            if (map[1][1] in listOf('X', 'O')) return map[1][1]
        }

        return null
    }

    fun prepareAIMove(): Move {
        return Move(' ', 0, 0) //TODO
    }
}