package org.sopt.seminar.presentation.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.seminar.RequestSignIn
import org.sopt.seminar.ResponseSignIn
import org.sopt.seminar.ServiceCreator
import org.sopt.seminar.main.MainActivity
import org.sopt.seminar.databinding.ActivitySignInBinding
import org.sopt.seminar.showToast
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign

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
          loginNetwork()
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseSignIn> { //실제 서버통신을 비동기적으로 요청
            override fun onResponse( //Callback 익명클래스 선언
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data //null값 올 수 있으므로 nullable 타입

                    Toast.makeText(
                        this@SignInActivity,
                        "${data?.email}님 반갑습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") //오류처리 코드
            }
        })
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

/*
if (!binding.etId.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()) {
    showToast("로그인 성공")
    goHome()
} else
showToast("아이디/비밀번호를 확인해주세요")*/
