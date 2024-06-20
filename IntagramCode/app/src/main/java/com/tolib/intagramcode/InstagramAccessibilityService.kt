package com.tolib.intagramcode

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.lifecycle.ViewModelProvider
import com.tolib.intagramcode.viewmodel.MainViewModel

class InstagramAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val eventType = event?.eventType
        val eventText = event?.text?.joinToString(", ") { it.toString() }
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            eventText?.contains("Instagram") == true
        ) {
            val nodeInfo = event.source ?:return
            val view = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/profile_tab")
            view.first().performAction(AccessibilityNodeInfo.ACTION_CLICK)
            val view1 = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/action_bar_large_title_auto_size")
            val accountName = view1.first().text.toString()
            val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(MainViewModel::class.java)
            viewModel.insertAccount(accountName)
        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

}
