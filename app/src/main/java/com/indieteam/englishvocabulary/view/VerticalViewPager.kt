package com.indieteam.englishvocabulary.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VerticalViewPager : ViewPager {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        setPageTransformer(true, VerticalPage())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun customXY(motionEvent: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = motionEvent.y / height * width
        val newY = motionEvent.x / width * height
        motionEvent.setLocation(newX, newY)

        return motionEvent
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        customXY(ev)
        return super.onInterceptTouchEvent(customXY(ev))
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        return super.onTouchEvent(customXY(motionEvent))
    }

    inner class VerticalPage : PageTransformer {

        override fun transformPage(view: View, position: Float) {
            if (position < -1) {
                view.alpha = 0f
            } else if (position <= 1) {
                view.alpha = 1f
                view.translationX = view.width * -position
                view.translationY = position * view.height
            } else {
                view.alpha = 0f
            }
        }
    }
}