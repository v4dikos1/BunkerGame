package com.example.bunker

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bunker.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), FragmentChangeListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view);


        val color: Int = ContextCompat.getColor(this, R.color.black)
        toolbar.navigationIcon?.setColorFilter(color, PorterDuff.Mode.DST_ATOP)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, StartFragment(), StartFragment.toString())
            //.addToBackStack(StartFragment.toString())
            .commit()

        toolbar.visibility = View.GONE

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.person_page -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, InGameFragment(), InGameFragment.toString())
                        .commit()

                    true
                }
                R.id.people_page -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, PeopleFragment(), PeopleFragment.toString())
                        .commit()

                    true
                }
                R.id.disaster_page -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_container,
                            DisasterFragment(),
                            DisasterFragment.toString()
                        )
                        .commit()
                    true
                }
                R.id.log_page -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, LogFragment(), LogFragment.toString())
                        .commit()
                    true
                }
                else -> false
            }
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE

        navigationView.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawer(GravityCompat.START)

            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, GameFragment(), GameFragment.toString())
                        .commit()
                    findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
                        View.GONE
                }
                R.id.settings -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, SettingsFragment())
                        .commit()
                }
                R.id.rules -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, RulesFragment())
                        .commit()
                }
                R.id.about -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, AboutFragment())
                        .commit()
                }
            }
            true
        }
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, fragment.toString())
            .addToBackStack(fragment.toString())
            .commit()

    }

    override fun setVisibleToolbar() {
        toolbar.visibility = View.VISIBLE
    }

    override fun setInvisibleToolbar() {
        toolbar.visibility = View.GONE
    }

    override fun setVisibleBottomNav() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
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
