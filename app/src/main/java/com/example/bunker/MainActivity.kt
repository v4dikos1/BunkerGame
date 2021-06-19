package com.example.bunker

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
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
import com.example.bunker.fragments.FragmentChangeListener
import com.example.bunker.fragments.GameFragment
import com.example.bunker.fragments.StartFragment
import com.example.bunker.fragments.WelcomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), FragmentChangeListener {

    private lateinit var  toolbar: androidx.appcompat.widget.Toolbar

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

        val navigationView : NavigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener { item ->

            drawerLayout.closeDrawer(GravityCompat.START)

            when (item.itemId){
                R.id.home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_container, WelcomeFragment(), WelcomeFragment.toString())
                            .commit()
                }
            }
            true
        }

        val color : Int = ContextCompat.getColor(this, R.color.black)
        toolbar.navigationIcon?.setColorFilter(color, PorterDuff.Mode.DST_ATOP)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, StartFragment(), StartFragment.toString())
            //.addToBackStack(StartFragment.toString())
            .commit()

        toolbar.visibility = View.GONE
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, fragment.toString())
            .addToBackStack(fragment.toString())
            .commit()

    }

    override fun setVisibleToolbar(){
        toolbar.visibility = View.VISIBLE
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
