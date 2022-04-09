### THE-SOPT-30th Android Part

## [ seminar 목차 ]

[1️⃣ Week](#seminar1-level1)





---
### 1️⃣ Week
## seminar1-level1

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
    
 ## seminar1 level2 , level3-1
 
 * ### SignInActivity
 
     ```kotlin
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

      ```
      
    * ##### registerForActivityResult()를 통해 회원가입에서 입력했던 아이디, 비밀번호가 입력되게 함
    
  * ### ScrollView, ImageView, DataBinding

     ```kotlin
     
     <data>

        <variable
            name="user"
            type="org.sopt.seminar.User" />
    </data>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".HomeActivity">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <ImageView
                android:id="@+id/img_profile"
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:layout_marginHorizontal="120dp"
                android:layout_marginTop="50dp"
                android:src="@drawable/profile"
                app:layout_constraintDimensionRatio="1:1"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
            <TextView
                .
                .
                android:text="@{user.age}"
                .
                ./>
     ```
      * ##### constraintDimensionRatio로 사진 비율 1:1 , DataBinding으로 User 데이터 생성, ScrollView 구현
 
* ### 🤟 ViewBinding과 DataBinding의 개념
    ```
    [DataBinding]

    -데이터 바인딩의 주목적은 UI 레이아웃의 뷰를 앱 코드에 저장된 데이터와 연결하는 간단한 방법을 제공
    -데이터와 뷰를 연결하는 작업을 레이아웃에서 
    -버튼과 같은 UI 컨트롤을 UI컨트롤러 또는 ViewModel 인스턴스와 같은 다른 객체의 이벤트나 리스너 함수에 연결 시키는 편리한 방법도 존재
    -특히 LiveData 컴포넌트와 같이 사용 될 때 이점이 배가 됨
    -xml단에서 <layout> 태그를 사용하여 만든 것만 처리

    [ViewBinding]

    -findViewById()사용보다 Null 안전에 있어 아래와 같은 장점이 존재
    -뷰 결합은 뷰의 직접 참조를 생성하므로 유효하지 않은 뷰 ID로 인해 null 포인터 예외가 발생할 위험이 없음
    -레이아웃의 일부 구성에만 뷰가 있는 경우 결합 클래스에서 참조를 포함하는 필드가 @Nullable로 표시
    -뷰바인딩은 데이터바인딩에 비해 주석처리가 필요하지 않으므로 더 빠른 컴파일 속도를 가짐
    -하지만 뷰바인딩은 레이아웃 변수 또는 레이아웃 표현식을 지원하지 않으므로 XML 레이아웃 파일에서 직접 동적 UI 콘텐츠를 선언하는 데 사용 불가능
    -양방향 데이터 결합 지원 불가

    ```
 ## 📸 실행 화면
 
로그인|회원가입|
|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/62291759/162580908-7576f2d4-44ad-4f69-a8f7-d1c1918d1129.gif" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/62291759/162581244-ae823402-b344-4a66-a84d-bac34766536e.gif" width="250" height="500"/>|
