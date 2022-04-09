### THE-SOPT-30th Android Part

## [ seminar 목차 ]

[1️⃣ Week](#level1)





---
### 1️⃣ Week
## level1

* ### SignInActivity

    ```kotlin
     private fun loginEvent() {
        binding.btnLogin.setOnClickListener {
            if (!binding.etId.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()) {
                showToast("로그인 성공")
                goHome()
            } else
                showToast("아이디/비밀번호를 확인해주세요")
         }
      }
    
    ```
    
    * ##### isNullOrBlank()을 통해 입력여부 확인하기 , 조건이 맞다면 goHome() 호출하여 HomeActivity로 이동
    * ##### ContextUtil을 사용하여 Toast 메세지 간단히 나타내기 : showToast()
    
 * ### SignUpActivity

    ```kotlin
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
    
    ```
    
    * ##### isNullOrBlank()을 통해 입력여부 확인하기 , finish()로 스택에서 나오기
    * ##### intent, putExtra를 통해 입력한 아이디, 비밀번호 정보를 intent 화면전환을 통해 값을 전달해줌 -> "Result_OK" result_code 전달
    * ##### ContextUtil을 사용하여 Toast 메세지 간단히 나타내기 : showToast()
    
