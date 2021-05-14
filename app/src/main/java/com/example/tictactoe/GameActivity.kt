package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var screenUpdater: Boolean = true

var p1score: Int = 0
var p2score: Int = 0

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

    private lateinit var player1Score: TextView
    private lateinit var player2Score: TextView

    private lateinit var binding: ActivityGameBinding

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

        player1Score = binding.player1Score
        player2Score = binding.player2Score

        one00.setOnClickListener { onBoxClicked(one00, Position(0, 0)) }
        two01.setOnClickListener { onBoxClicked(two01, Position(0, 1)) }
        three02.setOnClickListener { onBoxClicked(three02, Position(0, 2)) }
        four10.setOnClickListener { onBoxClicked(four10, Position(1, 0)) }
        five11.setOnClickListener { onBoxClicked(five11, Position(1, 1)) }
        six12.setOnClickListener { onBoxClicked(six12, Position(1, 2)) }
        seven20.setOnClickListener { onBoxClicked(seven20, Position(2, 0)) }
        eight21.setOnClickListener { onBoxClicked(eight21, Position(2, 1)) }
        nine22.setOnClickListener { onBoxClicked(nine22, Position(2, 2)) }


        MarkerHolder.marker = "X"

        if (GameHolder.game?.players?.count() == 2) {
            MarkerHolder.marker = "O"
        }

        binding.resetview.setOnClickListener {
            resetState()
            updateScreen()
        }

        binding.resetview.isVisible = false

        updateScreen()

    }


    private fun onBoxClicked(box: TextView, position: Position) {
        turnChecker()

        if (turnChecker() == true) {
            GameManager.makeMove(position)
        } else {
            println("Its not your turn 11111")
        }
    }

    // Receives winner = "X" or "O" or "DRAW" and shows it on the screen
    private fun gameEndChecker(winner: String) {
        val players = GameHolder.game!!.players
        val p1win = "${players[0]} wins!"
        val p2win = "${players[1]} wins"
        val draw = "DRAW!"

        when (winner) {
            "X" -> {
                binding.winnerview.text = p1win
            }
            "O" -> {
                binding.winnerview.text = p2win
            }
            "DRAW" -> {
                binding.winnerview.text = draw
            }

        }

    }

    private fun turnChecker(): Boolean {

        var checkTurn = false
        val state = GameHolder.game!!.state

        val countNulls0: Int = state.count { it[0].contains("0") }
        val countNulls1: Int = state.count { it[1].contains("0") }
        val countNulls2: Int = state.count { it[2].contains("0") }
        val nullCount: Int = countNulls0 + countNulls1 + countNulls2

        println("$nullCount")

        val listX = listOf<Int>(9, 7, 5, 3, 1)
        val listO = listOf<Int>(8, 6, 4, 2)

        if (listX.contains(nullCount)) {
            if (MarkerHolder.marker == "X") {
                checkTurn = true
            }
        } else if (listO.contains(nullCount)) {
            if (MarkerHolder.marker == "O") {
                checkTurn = true
            }
        }
        return checkTurn
    }

    // Adds score to winner and resets the board, both players need to press it to play again
    private fun resetState() {
        // Had a bug with adding score, worked the best adding score when reset is pressed
        if (GameManager.hasGameEnded() == "X") {
            p1score++
        } else if (GameManager.hasGameEnded() == "O") {
            p2score++
        }

        val resetwinner = ""
        binding.winnerview.text = resetwinner

        one00.text = ""
        two01.text = ""
        three02.text = ""
        four10.text = ""
        five11.text = ""
        six12.text = ""
        seven20.text = ""
        eight21.text = ""
        nine22.text = ""

        one00.isEnabled = true
        two01.isEnabled = true
        three02.isEnabled = true
        four10.isEnabled = true
        five11.isEnabled = true
        six12.isEnabled = true
        seven20.isEnabled = true
        eight21.isEnabled = true
        nine22.isEnabled = true

        GameManager.updateGame(GameHolder.game!!.gameId, GameManager.StartingGameState)

        binding.resetview.isVisible = false
        screenUpdater = true

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
        // loops every 0.3 second as long as screenUpdater is true
        CoroutineScope(IO).launch {
            while (screenUpdater == true) {
                delay(300)
                GameHolder.game?.let { GameManager.pollGame(it.gameId) }
                this@GameActivity.runOnUiThread {
                    if (GameHolder.game?.players?.count() == 2) {
                        binding.player2Name.text = GameHolder.game?.players?.get(1)
                    }
                    val state = GameHolder.game!!.state
                    one00.text = state[0][0].takeUnless { it == "0" }
                    two01.text = state[0][1].takeUnless { it == "0" }
                    three02.text = state[0][2].takeUnless { it == "0" }
                    four10.text = state[1][0].takeUnless { it == "0" }
                    five11.text = state[1][1].takeUnless { it == "0" }
                    six12.text = state[1][2].takeUnless { it == "0" }
                    seven20.text = state[2][0].takeUnless { it == "0" }
                    eight21.text = state[2][1].takeUnless { it == "0" }
                    nine22.text = state[2][2].takeUnless { it == "0" }

                    val scoretext1 = "Score: ${p1score} "
                    val scoretext2 = "Score: ${p2score} "
                    player1Score.text = scoretext1
                    player2Score.text = scoretext2

                    // If game is over, we receive "X", "O" or "DRAW" from hasGameEnded and pass this onto gameEndChecker
                    val winner = GameManager.hasGameEnded().takeUnless { it == "" }
                    if (winner != null) {
                        disableBoard()
                        gameEndChecker(winner)
                        binding.resetview.isVisible = true
                        screenUpdater = false

                    }

                }
            }

        }
    }

}