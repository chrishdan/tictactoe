package com.example.tictactoe

import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import org.junit.Test

import org.junit.Assert.*

class ExampleInstrumentedTest {

    var gameState: Game? = null
    val firstPlayer:String = "Player1"
    val secondPlayer:String = "Player2"
    val StartingGameState: GameState =
        mutableListOf(
            mutableListOf("0", "0", "0"),
            mutableListOf("0", "0", "0"),
            mutableListOf("0", "0", "0")
        )

    /*
    @Test
    fun createGame(){
        GameService.createGame(firstPlayer,StartingGameState ){ state:Game?, err:Int? ->
            gameState = state
            assertNotNull(state)
            assertNotNull(state?.gameId)
            assertEquals(firstPlayer, state?.players?.get(0))
        }
    }

    fun JoinGame(){
        GameService.joinGame(secondPlayer, gameState!!.gameId) {state:Game?, err:Int? ->
            gameState = state
            assertEquals(firstPlayer, state?.players?.get(0))
        }
    }
    */
}