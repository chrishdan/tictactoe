package com.example.tictactoe

import android.content.Intent
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.api.GameService


object GameManager {

    val StartingGameState: GameState =
        mutableListOf(
            mutableListOf("0", "0", "0"),
            mutableListOf("0", "0", "0"),
            mutableListOf("0", "0", "0")
        )

    fun makeMove(position: Position) {
        val state = GameHolder.game?.state

        state?.get(position.row)!![position.column] = MarkerHolder.marker.toString()

        updateGame(GameHolder.game!!.gameId, state)
    }

    fun hasGameEnded(): String {
        val state = GameHolder.game!!.state

        val countNulls0: Int = state.count { it[0].contains("0") }
        val countNulls1: Int = state.count { it[1].contains("0") }
        val countNulls2: Int = state.count { it[2].contains("0") }
        val nullCount: Int = countNulls0 + countNulls1 + countNulls2
        val drawValue = 0

        if (nullCount == drawValue) {
            return "DRAW"
        }

        if (state[0][0] == "X"
            && state[0][1] == "X"
            && state[0][2] == "X"
        ) {
            return "X"
        } else if (state[0][0] == "O"
            && state[0][1] == "O"
            && state[0][2] == "O"
        ) {
            return "O"
        } else if (state[1][0] == "X"
            && state[1][1] == "X"
            && state[1][2] == "X"
        ) {
            return "X"
        } else if (state[1][0] == "O"
            && state[1][1] == "O"
            && state[1][2] == "O"
        ) {
            return "O"

        } else if (state[2][0] == "X"
            && state[2][1] == "X"
            && state[2][2] == "X"
        ) {
            return "X"
        } else if (state[2][0] == "O"
            && state[2][1] == "O"
            && state[2][2] == "O"
        ) {
            return "O"

        } else if (state[0][0] == "X"
            && state[1][0] == "X"
            && state[2][0] == "X"
        ) {
            return "X"
        } else if (state[0][0] == "O"
            && state[1][0] == "O"
            && state[2][0] == "O"
        ) {
            return "O"

        } else if (state[0][1] == "X"
            && state[1][1] == "X"
            && state[2][1] == "X"
        ) {
            return "X"

        } else if (state[0][1] == "O"
            && state[1][1] == "O"
            && state[2][1] == "O"
        ) {
            return "O"

        } else if (state[0][2] == "X"
            && state[1][2] == "X"
            && state[2][2] == "X"
        ) {
            return "X"
        } else if (state[0][2] == "O"
            && state[1][2] == "O"
            && state[2][2] == "O"
        ) {
            return "O"

        } else if (state[0][0] == "X"
            && state[1][1] == "X"
            && state[2][2] == "X"
        ) {
            return "X"
        } else if (state[0][0] == "O"
            && state[1][1] == "O"
            && state[2][2] == "O"
        ) {
            return "O"

        } else if (state[0][2] == "X"
            && state[1][1] == "X"
            && state[2][0] == "X"
        ) {
            return "X"
        } else if (state[0][2] == "O"
            && state[1][1] == "O"
            && state[2][0] == "O"
        ) {
            return "O"
        }

        return ""

    }

    fun createGame(player: String) {

        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                println("Error creating game")
            } else {
                GameHolder.game = game

                val intent = Intent(MainActivity.context, GameActivity::class.java)
                MainActivity.context.startActivity(intent)
            }
        }

    }

    fun joinGame(player: String, gameId: String) {
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if (err != null) {
                println("Error joining game")
            } else {
                GameHolder.game = game

                val intent = Intent(MainActivity.context, GameActivity::class.java)
                MainActivity.context.startActivity(intent)

            }
        }

    }

    fun updateGame(gameId: String, state: GameState) {
        GameService.updateGame(gameId, state) { game: Game?, err: Int? ->
            if (err != null) {
                println("Error updating game")
            } else {

                GameHolder.game = game
            }
        }

    }


    fun pollGame(gameId: String) {
        GameService.pollGame(gameId) { game: Game?, err: Int? ->
            if (err != null) {
                println("Error polling game")

            } else {
                GameHolder.game = game
            }
        }

    }

}

