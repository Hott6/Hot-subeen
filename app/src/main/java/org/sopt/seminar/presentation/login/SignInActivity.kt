package org.sopt.seminar.presentation.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.seminar.MainActivity
import org.sopt.seminar.databinding.ActivitySignInBinding
import org.sopt.seminar.presentation.home.HomeActivity
import org.sopt.seminar.showToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginEvent()
        setSignUp()
        signupEvent()
    }

    private fun loginEvent() {
        binding.btnLogin.setOnClickListener {
            if (!binding.etId.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()) {
                showToast("로그인 성공")
                goHome()
            } else
                showToast("아이디/비밀번호를 확인해주세요")
        }
    }

    private fun setSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val password = result.data?.getStringExtra("password") ?: ""
                    binding.etId.setText(id)
                    binding.etPw.setText(password)
                }
            }
    }

    private fun signupEvent() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}