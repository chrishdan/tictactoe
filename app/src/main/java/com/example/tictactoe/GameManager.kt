package com.example.tictactoe

import android.content.Intent
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.api.GameService


object GameManager {

    var player: String? = null
    var state: GameState? = null

    val StartingGameState: GameState = listOf(listOf("0", "0", "0"), listOf("0", "0", "0"), listOf("0", "0", "0"))

    fun createGame(player: String) {

        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                println("${err} Failed to create game 11111")
            } else {

                println("${game} Successfully created game 11111")

                GameHolder.game = game

                val intent = Intent(MainActivity.context, GameActivity::class.java)
                MainActivity.context.startActivity(intent)
                // if creategame works, we test if joingame works
                //joinGame("test2", game!!.gameId)

            }
        }

    }

    fun joinGame(player: String, gameId: String) {
        GameService.joinGame(player,gameId) { game: Game?, err: Int? ->
            if (err != null) {
                println("Error joining game 11111")

            } else {
                println("${game} Successfully joined game 11111")

                GameHolder.game = game

                val intent = Intent(MainActivity.context, GameActivity::class.java)
                MainActivity.context.startActivity(intent)
                // if joingame works, we test if updategame works properly
                //val NewGameState: GameState = listOf(listOf(88, 0, 0), listOf(0, 0, 79), listOf(0, 0, 0))
                //updateGame(game!!.gameId, NewGameState)


            }
        }

    }

    fun updateGame(gameId: String, state:GameState) {
        GameService.updateGame(gameId, state) { game: Game?, err: Int? ->
            if (err != null) {
                println("failed to update 11111")
            } else {
                println("${game} successful update 11111")

                // if updategame works we test if pollgame works
                //pollGame(game!!.gameId)

            }
        }

    }


    fun pollGame(gameId: String) {
        GameService.pollGame(gameId) { game: Game?, err: Int? ->
            if (err != null) {
                println("failed to poll 11111")
            } else {
                println("${game}  successful poll 11111")

            }
        }

    }

}
