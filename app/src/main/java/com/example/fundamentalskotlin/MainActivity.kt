package com.example.fundamentalskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hw2.setOnClickListener {
            val movieDetailsActivity = Intent(this, MovieDetailsActivity::class.java)
            startActivity(movieDetailsActivity)
        }
        iv_shape.setOnClickListener {
            val movieDetailsActivity = Intent(this, MovieDetailsActivity::class.java)
            startActivity(movieDetailsActivity)
        }
    }
}
