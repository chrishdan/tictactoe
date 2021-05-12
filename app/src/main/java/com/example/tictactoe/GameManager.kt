package com.example.tictactoe

import android.content.Intent
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.api.GameService


object GameManager {


    val StartingGameState: GameState =
        mutableListOf(mutableListOf("0", "0", "0"), mutableListOf("0", "0", "0"),mutableListOf("0", "0", "0"))

    fun makeMove(position: Position){
        val state = GameHolder.game?.state

            state?.get(position.row)!![position.column] = MarkerHolder.marker.toString()

            updateGame(GameHolder.game!!.gameId, state)
    }

    fun hasGameDraw(): Boolean {
        val state = GameHolder.game!!.state

        val countNulls0: Int = state.count {it[0].contains("0")}
        val countNulls1: Int = state.count {it[1].contains("0")}
        val countNulls2: Int = state.count {it[2].contains("0")}
        val nullCount: Int = countNulls0 + countNulls1 + countNulls2

        val drawValue = 0

        if (nullCount == drawValue) {
            return true
        }
        return false
    }

    fun hasGameEnded(): Boolean {
        if (GameHolder.game?.state?.get(0)!![0] == "X"
            && GameHolder.game?.state?.get(0)!![1] == "X"
            && GameHolder.game?.state?.get(0)!![2] == "X") {
            return true
        }else if (GameHolder.game?.state?.get(0)!![0] == "O"
            && GameHolder.game?.state?.get(0)!![1] == "O"
            && GameHolder.game?.state?.get(0)!![2] == "O") {
            return true
        }else if (GameHolder.game?.state?.get(1)!![0] == "X"
                && GameHolder.game?.state?.get(1)!![1] == "X"
                && GameHolder.game?.state?.get(1)!![2] == "X") {
                return true
        }else if (GameHolder.game?.state?.get(1)!![0] == "O"
            && GameHolder.game?.state?.get(1)!![1] == "O"
            && GameHolder.game?.state?.get(1)!![2] == "O") {
            return true

        } else if (GameHolder.game?.state?.get(2)!![0] == "X"
            && GameHolder.game?.state?.get(2)!![1]== "X"
            && GameHolder.game?.state?.get(2)!![2] == "X") {
            return true
        } else if (GameHolder.game?.state?.get(2)!![0] == "O"
            && GameHolder.game?.state?.get(2)!![1]== "O"
            && GameHolder.game?.state?.get(2)!![2] == "O") {
            return true

        } else if (GameHolder.game?.state?.get(0)!![0]== "X"
            && GameHolder.game?.state?.get(1)!![0]== "X"
            && GameHolder.game?.state?.get(2)!![0]== "X") {
            return true
        } else if (GameHolder.game?.state?.get(0)!![0]== "O"
            && GameHolder.game?.state?.get(1)!![0]== "O"
            && GameHolder.game?.state?.get(2)!![0]== "O") {
            return true

        } else if (GameHolder.game?.state?.get(0)!![1] == "X"
            && GameHolder.game?.state?.get(1)!![1]== "X"
            && GameHolder.game?.state?.get(2)!![1]== "X") {
            return true

        } else if (GameHolder.game?.state?.get(0)!![1] == "O"
            && GameHolder.game?.state?.get(1)!![1]== "O"
            && GameHolder.game?.state?.get(2)!![1]== "O") {
            return true

        } else if (GameHolder.game?.state?.get(0)!![2]== "X"
            && GameHolder.game?.state?.get(1)!![2]== "X"
            && GameHolder.game?.state?.get(2)!![2]== "X") {
            return true
        } else if (GameHolder.game?.state?.get(0)!![2]== "O"
            && GameHolder.game?.state?.get(1)!![2]== "O"
            && GameHolder.game?.state?.get(2)!![2]== "O") {
            return true

        } else if (GameHolder.game?.state?.get(0)!![0]== "X"
            && GameHolder.game?.state?.get(1)!![1]== "X"
            && GameHolder.game?.state?.get(2)!![2]== "X") {
            return true
        } else if (GameHolder.game?.state?.get(0)!![0]== "O"
            && GameHolder.game?.state?.get(1)!![1]== "O"
            && GameHolder.game?.state?.get(2)!![2]== "O") {
            return true

        } else if (GameHolder.game?.state?.get(2)!![0]== "X"
            && GameHolder.game?.state?.get(1)!![1]== "X"
            && GameHolder.game?.state?.get(2)!![0]== "X") {
            return true
        } else if (GameHolder.game?.state?.get(2)!![0]== "O"
                && GameHolder.game?.state?.get(1)!![1]== "O"
                && GameHolder.game?.state?.get(2)!![0]== "O") {
                 return true
        }

        return false

    }

        fun createGame(player: String) {

            GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
                if (err != null) {
                } else {
                    GameHolder.game = game

                    val intent = Intent(MainActivity.context, GameActivity::class.java)
                    MainActivity.context.startActivity(intent)
                    // if creategame works, we test if joingame works
                    //joinGame("test2", game!!.gameId)

                }
            }

        }

        fun joinGame(player: String, gameId: String) {
            GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
                if (err != null) {
                } else {
                    GameHolder.game = game

                    val intent = Intent(MainActivity.context, GameActivity::class.java)
                    MainActivity.context.startActivity(intent)
                    // if joingame works, we test if updategame works properly
                    //val NewGameState: GameState = listOf(listOf(88, 0, 0), listOf(0, 0, 79), listOf(0, 0, 0))
                    //updateGame(game!!.gameId, NewGameState)


                }
            }

        }

        fun updateGame(gameId: String, state: GameState) {
            GameService.updateGame(gameId, state) { game: Game?, err: Int? ->
                if (err != null) {
                } else {

                    GameHolder.game = game
                    // if updategame works we test if pollgame works
                    //pollGame(game!!.gameId)

                }
            }

        }


        fun pollGame(gameId: String) {
            GameService.pollGame(gameId) { game: Game?, err: Int? ->
                if (err != null) {

                } else {

                    GameHolder.game = game
                }
            }

        }

}

