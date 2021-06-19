package com.example.bunker.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bunker.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WaitingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fc: FragmentChangeListener = activity as FragmentChangeListener
        val text: String = view.findViewById<TextView>(R.id.code).text as String

        view.findViewById<Button>(R.id.leave_button).setOnClickListener {
            fc.popBackFragment(GameFragment())
        }

        view.findViewById<TextView>(R.id.code).setOnClickListener {
            val clipBoard: ClipboardManager =
                context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("", text)
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(context, "Код скопирован в буфер обмена", Toast.LENGTH_LONG).show()
        }

        view.findViewById<View>(R.id.copy_button).setOnClickListener {
            val clipBoard: ClipboardManager =
                context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("", text)
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(context, "Код скопирован в буфер обмена", Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WaitingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}