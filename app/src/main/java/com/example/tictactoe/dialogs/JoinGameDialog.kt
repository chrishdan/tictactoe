package com.example.tictactoe.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.GameManager
import com.example.tictactoe.databinding.DialogJoinGameBinding

class JoinGameDialog() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogJoinGameBinding.inflate(inflater)

            builder.apply {
                setTitle("Join game")
                setPositiveButton("Join") { dialog, which ->
                    if(binding.username2.text.toString() != "" && binding.gameId.text.toString() != ""){
                        //listener.onDialogJoinGame(binding.username2.text.toString(), binding.gameId.text.toString())
                        GameManager.joinGame(binding.username2.text.toString(), binding.gameId.text.toString())
                    }
                }
                setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
                setView(binding.root)
            }

            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")
    }

}