package com.example.tictactoe.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.GameManager
import com.example.tictactoe.databinding.DialogCreateGameBinding


class CreateGameDialog() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogCreateGameBinding.inflate(inflater)

            builder.apply {
                setTitle("Create game")
                setPositiveButton("Create") { dialog, which ->
                    if(binding.username.text.toString() != ""){
                        GameManager.createGame(binding.username.text.toString())

                    }
                }
                setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
                setView(binding.root)
            }

            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null 11111")
    }

}