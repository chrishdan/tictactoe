package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.dialogs.CreateGameDialog
import com.example.tictactoe.dialogs.JoinGameDialog

class GameHolder {
    companion object {
        var game: Game? = null
    }
}

class MarkerHolder {
    companion object {
        var marker: String? = ""
    }
}

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var context: MainActivity
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.createGameBtn.setOnClickListener {
            createNewGame()


        }

        binding.joinGameBtn.setOnClickListener {
            joinGame()

        }

        // testing if gameservice works properly
        //GameManager.createGame("test")


    }

    private fun createNewGame() {
        val dlg = CreateGameDialog()
        dlg.show(supportFragmentManager, "CreateGameDialogFragment")
    }

    private fun joinGame() {
        val dlg = JoinGameDialog()
        dlg.show(supportFragmentManager, "JoinGameDialogFragment")
    }


}