package com.example.bunker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bunker.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CodeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fc: FragmentChangeListener = activity as FragmentChangeListener

        view.findViewById<Button>(R.id.connect_btn).setOnClickListener {
            fc.replaceFragment(WaitingFragment())
            fc.setInvisibleToolbar()
        }

        view.findViewById<Button>(R.id.back_button_2).setOnClickListener {
            fc.popBackFragment(GameFragment())
            fc.setVisibleToolbar()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}