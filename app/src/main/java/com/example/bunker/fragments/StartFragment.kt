package com.example.bunker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bunker.R
import com.example.bunker.fragments.FragmentChangeListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StartFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fc: FragmentChangeListener = activity as FragmentChangeListener

        view.findViewById<Button>(R.id.registration_btn).setOnClickListener {
            fc.replaceFragment(RegistrationFragment())
        }

        view.findViewById<Button>(R.id.enter_button).setOnClickListener {
            fc.clearBackStack()
            fc.replaceFragment(WelcomeFragment())
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}