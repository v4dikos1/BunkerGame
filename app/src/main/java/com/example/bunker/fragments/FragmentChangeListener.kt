package com.example.bunker.fragments

import androidx.fragment.app.Fragment

interface FragmentChangeListener {
   fun replaceFragment(fragment:Fragment)
   fun replaceFragmentNow(fragment: Fragment)
   fun popBackFragment(fragment: Fragment)
   fun clearBackStack()
   fun setVisibleToolbar()
   fun setInvisibleToolbar()
}