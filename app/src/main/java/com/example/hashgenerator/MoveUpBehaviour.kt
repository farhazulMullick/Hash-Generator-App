package com.example.hashgenerator.com.example.hashgenerator

import android.os.Build
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar.SnackbarLayout


class MoveUpwardBehavior : CoordinatorLayout.Behavior<View?>() {
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return SNACKBAR_BEHAVIOR_ENABLED && dependency is SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = 0f.coerceAtMost(dependency.translationY - dependency.height)
        child.translationY = translationY
        return true
    }

    companion object {
        private var SNACKBAR_BEHAVIOR_ENABLED = false

        init {
            SNACKBAR_BEHAVIOR_ENABLED = Build.VERSION.SDK_INT >= 23
        }
    }
}