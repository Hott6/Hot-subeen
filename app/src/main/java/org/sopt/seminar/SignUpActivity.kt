package org.sopt.seminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signupEvent()

    }

    private fun signupEvent() {
        with(binding) {
            btnComplete.setOnClickListener {
                if (!binding.etId.text.isNullOrBlank() && !binding.etName.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()) {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", etId.text.toString())
                    intent.putExtra("password", etPw.text.toString())
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    showToast("입력하지 않은 정보가 있습니다.")

                }
            }
        }
    }
}