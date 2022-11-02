package com.zulekha.floatie

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zulekha.floatie.databinding.ActivityMainBinding
import com.zulekha.floatie.pexty.floatingapp.FloatingPermissions
import com.zulekha.floatie.pexty.floatingapp.window.BaseWindow
import com.zulekha.floatie.pexty.floatingapp.window.WindowManager

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var fab: FloatingActionButton
    private lateinit var baseWindow: BaseWindow

        @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fab?.setOnClickListener {
            FloatingPermissions.take(this)
            baseWindow.open()
        }

        baseWindow = BaseWindow(this, 500, 500)
        baseWindow.windowLifecycleListener = object : BaseWindow.WindowLifecycleListener {
            override fun onOpen(baseWindow: BaseWindow) {
                println("Window opened $baseWindow")
            }

            override fun onQuit(baseWindow: BaseWindow) {
                baseWindow.kill()
                println("Window closed $baseWindow")
            }
        }
        baseWindow.windowFocusListener = object : BaseWindow.WindowFocusListener {
            override fun onFocused(baseWindow: BaseWindow) {
                println("Window focused $baseWindow")
            }

            override fun onUnfocused(baseWindow: BaseWindow) {
                println("Window unfocused $baseWindow")
            }
        }
    }

    override fun onDestroy() {
        WindowManager.softKillAll()
        super.onDestroy()
    }
}