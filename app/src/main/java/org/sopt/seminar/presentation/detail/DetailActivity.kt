package org.sopt.seminar.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
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
        val profile = intent.getStringExtra("profile")
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val introduce = intent.getStringExtra("introduce")

        binding.apply {
            Glide.with(this@DetailActivity).load(profile).into(ivProfile)
            tvName.text = name
            tvId.text = id
            tvIntroduce.text = introduce
        }

    }
}
