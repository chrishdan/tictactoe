package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.player1Name.text = GameHolder.game?.players?.get(0)
        binding.showgameId.text = GameHolder.game?.gameId

        if (GameHolder.game?.players?.count() != 1) {
            binding.player2Name.text = GameHolder.game?.players?.get(1)
        }



    }

}