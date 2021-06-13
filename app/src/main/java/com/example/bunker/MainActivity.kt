package com.example.bunker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bunker.fragments.FragmentChangeListener
import com.example.bunker.fragments.StartFragment

class MainActivity : AppCompatActivity(), FragmentChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, StartFragment(), StartFragment.toString())
            //.addToBackStack(StartFragment.toString())
            .commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, fragment.toString())
            .addToBackStack(fragment.toString())
            .commit()
    }

    override fun replaceFragmentNow(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, fragment.toString())
            .commitNow()
    }

    override fun popBackFragment(fragment: Fragment) {
        supportFragmentManager
            .popBackStack()
    }

    override fun clearBackStack() {
        supportFragmentManager.popBackStack()
    }


}
