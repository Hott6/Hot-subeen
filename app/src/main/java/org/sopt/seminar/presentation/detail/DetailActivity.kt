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
        binding.apply {
            with(intent) {
                Glide.with(this@DetailActivity).load(getStringExtra("profile")).into(ivProfile)
                tvName.text = getStringExtra("name")
                tvId.text = getStringExtra("id")
                tvIntroduce.text = getStringExtra("introduce")
            }

        }

    }
}
