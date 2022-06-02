package com.example.rickandmorty.util

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.slidingpanelayout.widget.SlidingPaneLayout

class CustomOnBackPressed(private val slidingPaneLayout: SlidingPaneLayout) :
    OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) { }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}
