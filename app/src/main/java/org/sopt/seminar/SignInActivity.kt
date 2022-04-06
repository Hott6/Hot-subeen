package org.sopt.seminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.seminar.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginEvent()
        signupEvent()
    }

    private fun loginEvent(){
        binding.btnLogin.setOnClickListener{
            if(!binding.etId.text.isNullOrBlank()&&!binding.etPw.text.isNullOrBlank()) {
                showToast("로그인 성공")
                goHome()
            }else
                showToast("아이디/비밀번호를 확인해주세요")
        }
    }

    private fun signupEvent(){
        
    }

    private fun goHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}