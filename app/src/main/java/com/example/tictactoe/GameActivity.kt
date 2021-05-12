package com.example.tictactoe

import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.databinding.ActivityGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var screenUpdater: Boolean = true

class GameActivity : AppCompatActivity() {

    private lateinit var one00: TextView
    private lateinit var two01: TextView
    private lateinit var three02: TextView
    private lateinit var four10: TextView
    private lateinit var five11: TextView
    private lateinit var six12: TextView
    private lateinit var seven20: TextView
    private lateinit var eight21: TextView
    private lateinit var nine22: TextView

    private lateinit var binding:ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.player1Name.text = GameHolder.game?.players?.get(0)
        binding.showgameId.text = GameHolder.game?.gameId

        one00 = binding.view00
        two01 = binding.view01
        three02 = binding.view02
        four10 = binding.view10
        five11 = binding.view11
        six12 = binding.view12
        seven20 = binding.view20
        eight21 = binding.view21
        nine22 = binding.view22

        one00.setOnClickListener { onBoxClicked(one00,  Position(0, 0)) }
        two01.setOnClickListener { onBoxClicked(two01, Position(0, 1)) }
        three02.setOnClickListener { onBoxClicked(three02, Position(0, 2)) }
        four10.setOnClickListener { onBoxClicked(four10, Position(1, 0))}
        five11.setOnClickListener { onBoxClicked(five11, Position(1, 1)) }
        six12.setOnClickListener { onBoxClicked(six12, Position(1, 2)) }
        seven20.setOnClickListener { onBoxClicked(seven20, Position(2, 0))}
        eight21.setOnClickListener { onBoxClicked(eight21, Position(2, 1)) }
        nine22.setOnClickListener { onBoxClicked(nine22, Position(2, 2)) }

        MarkerHolder.marker = "X"

        if (GameHolder.game?.players?.count() == 2) {
            MarkerHolder.marker = "O"
        }

        updateScreen()

    }

    private fun onBoxClicked(box: TextView, position: Position) {
        turnChecker()

        if (turnChecker() == true) {
            GameManager.makeMove(position)
        } else{
            println("Its not your turn 11111")
        }
    }

    private fun gameEndChecker(): String {
        var winner = ""
        val winGame = GameManager.hasGameEnded()
        val drawGame = GameManager.hasGameDraw()

        if (winGame == false) {
            if (drawGame == true) {
                winner = "DRAW"
            }

        }else if (winGame == true) {
            if (MarkerHolder.marker == "X")
            {
                winner = GameHolder.game!!.players[0]
            }else {
                winner = GameHolder.game!!.players[1]
            }

        }
        return winner
    }

    private fun turnChecker(): Boolean {

        var checkTurn = false
        val state = GameHolder.game!!.state

        val countNulls0: Int = state.count {it[0].contains("0")}
        val countNulls1: Int = state.count {it[1].contains("0")}
        val countNulls2: Int = state.count {it[2].contains("0")}
        val nullCount: Int = countNulls0 + countNulls1 + countNulls2

        println("$nullCount")

        val listX = listOf<Int>(9, 7, 5, 3, 1)
        val listO = listOf<Int>(8, 6, 4, 2)

        if (listX.contains(nullCount)) {
            if (MarkerHolder.marker == "X") {
                checkTurn = true
            }
        } else if (listO.contains(nullCount) ) {
            if   (MarkerHolder.marker == "O") {
                checkTurn = true
            }
        }
        return checkTurn
    }


    private fun resetState() {
        one00.text = ""
        two01.text = ""
        three02.text = ""
        four10.text = ""
        five11.text = ""
        six12.text = ""
        seven20.text = ""
        eight21.text = ""
        nine22.text = ""

        one00.background = null
        two01.background = null
        three02.background = null
        four10.background = null
        five11.background = null
        six12.background = null
        seven20.background = null
        eight21.background = null
        nine22.background = null

        one00.isEnabled = true
        two01.isEnabled = true
        three02.isEnabled = true
        four10.isEnabled = true
        five11.isEnabled = true
        six12.isEnabled = true
        seven20.isEnabled = true
        eight21.isEnabled = true
        nine22.isEnabled = true
    }

    private fun disableBoard() {

        one00.isEnabled = false
        two01.isEnabled = false
        three02.isEnabled = false
        four10.isEnabled = false
        five11.isEnabled = false
        six12.isEnabled = false
        seven20.isEnabled = false
        eight21.isEnabled = false
        nine22.isEnabled = false

    }

    private fun updateScreen() {
        CoroutineScope(IO).launch {
            while (screenUpdater == true) {
                delay(300)
                GameHolder.game?.let { GameManager.pollGame(it.gameId) }
                this@GameActivity.runOnUiThread {
                    if (GameHolder.game?.players?.count() == 2) {
                        binding.player2Name.text = GameHolder.game?.players?.get(1)
                    }
                    one00.text = GameHolder.game?.state?.get(0)!![0].takeUnless { it == "0" }
                    two01.text = GameHolder.game?.state?.get(0)!![1].takeUnless { it == "0" }
                    three02.text = GameHolder.game?.state?.get(0)!![2].takeUnless { it == "0" }
                    four10.text = GameHolder.game?.state?.get(1)!![0].takeUnless { it == "0" }
                    five11.text = GameHolder.game?.state?.get(1)!![1].takeUnless { it == "0" }
                    six12.text = GameHolder.game?.state?.get(1)!![2].takeUnless { it == "0" }
                    seven20.text = GameHolder.game?.state?.get(2)!![0].takeUnless { it == "0" }
                    eight21.text = GameHolder.game?.state?.get(2)!![1].takeUnless { it == "0" }
                    nine22.text = GameHolder.game?.state?.get(2)!![2].takeUnless { it == "0" }

                    val gameWinner = gameEndChecker()
                    if (gameWinner != "") {
                        disableBoard()
                        if (gameWinner == "X") {
                            println("${GameHolder.game!!.players[0]} ($gameWinner) wins! 11111")
                        } else if (gameWinner == "O") {
                            println("${GameHolder.game!!.players[1]} ($gameWinner) wins! 11111")
                        } else {
                            println("$gameWinner 11111!")
                        }

                        screenUpdater = false
                        resetState()



                    }

                }

            }
        }
    }

}