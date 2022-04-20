package org.sopt.seminar.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seminar.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData() {
        val name = intent.getStringExtra("name")
        val introduction = intent.getStringExtra("introduction")

        binding.apply {
            tvName.text = name
            tvIntroduce.text = introduction
        }

    }
}
