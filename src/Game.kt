import enums.Difficulty
import enums.GameMode
import util.Move

class Game(private val mode: GameMode, private val difficulty: Difficulty?) {
    private val map: Map = Map()
    private var represent: Char = 'X'
    private var gameIsRunning: Boolean = true

    companion object {
        fun start() {
            println("WELCOME TO TIC-TAC-TOE")
            var gameMode: Int? = null
            var difficulty: String? = null

            while (gameMode == null || gameMode !in 1..2) {
                println("Type in 1 if you want to play alone or 2 if you have a mate to play with!")
                gameMode = readln().toIntOrNull()
            }
            if (GameMode.get(gameMode) == GameMode.SINGLEPLAYER) {
                while (difficulty == null || !difficulty.lowercase().equals(Difficulty.getKeys())) {
                    println("Please choose the difficulty (Easy, Normal, Hard):")
                    difficulty = readln()
                }
            }

            Game(GameMode.get(gameMode), Difficulty.get(difficulty)).run()
        }
    }

    fun run() {
        var winner: Char? = null
        while (gameIsRunning) {
            map.print()
            map.doMove(prepareMove())
            winner = map.checkForEnd()
            if (winner != null) gameIsRunning = false
            represent = if (represent == 'X') 'O' else 'X'
        }
        map.print()
        if (mode == GameMode.SINGLEPLAYER) {
            if (winner == 'X') {
                println("Congratulation! You won")
            } else {
                println("Ohh no, the AI won :(")
            }
        } else {
            println("Player $winner won!")
        }
    }

    private fun prepareMove() : Move {
        if (mode == GameMode.SINGLEPLAYER && represent == 'O') {
            return map.prepareAIMove()
        }

        var row: Int? = null
        var column: Int? = null
        var move: Move? = null

        while (!map.moveIsValid(move)) {
            while (row == null || column == null) {
                println("Please type in the row and column you want to put your Move. (Example: '1,2')")
                println("Rows and Columns are numbered from 1 to 3")
                val input: List<String> = readln().replace(" ", "").split(",")
                if (input.size == 2) {
                    if (input[0].toIntOrNull() != null) row = input[0].toInt() - 1
                    if (input[1].toIntOrNull() != null) column = input[1].toInt() - 1
                }
            }
            move = Move(represent = represent, row = row, column = column)
            row = null
            column = null
        }

        return move!!
    }


}