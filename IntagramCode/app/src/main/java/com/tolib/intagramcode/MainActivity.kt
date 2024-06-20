package com.tolib.intagramcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.accessibility.AccessibilityManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.tolib.intagramcode.databinding.ActivityMainBinding
import com.tolib.intagramcode.viewmodel.MainViewModel
import com.tolib.intagramcode.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(application))[MainViewModel::class.java]

        viewModel.accounts.observe(this) { accounts ->
            if (accounts.isNotEmpty()) {
                val lastAccount = accounts.last()
                binding.text.text = String.format("Last account: %s",lastAccount.username)
            }
        }
        binding.btnStart.setOnClickListener{
            if (!isAccessibilityServiceEnabled()) {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            } else {
                val intent = packageManager.getLaunchIntentForPackage("com.instagram.android")
                startActivity(intent)
            }

        }
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        val colonSplitter = TextUtils.SimpleStringSplitter(':')

        colonSplitter.setString(enabledServices)

        while (colonSplitter.hasNext()) {
            val componentName = colonSplitter.next()

            if (componentName.equals("${packageName}/${InstagramAccessibilityService::class.java.canonicalName}", ignoreCase = true)) {
                return true
            }
        }

        return false
    }
}