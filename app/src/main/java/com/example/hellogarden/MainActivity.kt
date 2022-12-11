package com.example.hellogarden

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.hellogarden.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    /**
     * Später initialisierte Variable wo wir das ActivityMainBinding
     * aktivieren und das mit der DataBinding Methode
     */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         *  navController wird dem NavHostFragment zugeordnet
         */



        /**
         * für ActionBar zum Ausblenden
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


    }
}