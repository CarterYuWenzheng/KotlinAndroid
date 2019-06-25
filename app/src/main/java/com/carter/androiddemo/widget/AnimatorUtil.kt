package com.carter.androiddemo.widget

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.View
import android.view.animation.AccelerateInterpolator

class AnimatorUtil {
    private val FAST_OUT_SLOW_IN_INTERPOLATOR = LinearOutSlowInInterpolator()

    private val LINER_INTERPOLATOR = AccelerateInterpolator()


    /**
     * 显示view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    fun scaleShow(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start()
    }

    /**
     * 隐藏view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    fun scaleHide(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start()
    }

    /**
     * 显示view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    fun translateShow(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .translationY(0f)
                .setDuration(400)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start()
    }

    /**
     * 隐藏view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    fun translateHide(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .translationY(350f)
                .setDuration(400)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start()
    }
}