package com.example.resultapi

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.resultapi.SecondActivity.Contract
import com.example.resultapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        Log.d(MainActivity::class.java.simpleName, "Permission granted: $it")
    }

    private val editMessageLaunccher = registerForActivityResult(SecondActivity.Contract()) {
        Log.d(MainActivity::class.java.simpleName, "Edit result: $it")
        if (it.confirmed) {
            binding.twText.text = it.message
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequestPermission.setOnClickListener { requestPermission() }
        binding.btnEditText.setOnClickListener { editMessgae() }

    }

    private fun editMessgae() {
        editMessageLaunccher.launch(binding.twText.text.toString())
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}