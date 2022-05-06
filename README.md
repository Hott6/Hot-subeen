### THE-SOPT-30th Android Part

## [ seminar 목차 ]

[1️⃣ Seminar](#seminar1-level1)

[2️⃣ Seminar](#seminar2-level1)

[3️⃣ Seminar](#seminar3-level1)

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



---
### 2️⃣ Week
## seminar2-level1

* ### HomeActivity

    ```kotlin
    
      supportFragmentManager.beginTransaction().add(R.id.fragment_home, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (position == REPO_POSITION) {
                transaction.replace(R.id.fragment_home, followerFragment).commit()
                position = FOLLOWER_POSITION
            }
        }
        binding.btnRepo.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (position == FOLLOWER_POSITION) {
                transaction.replace(R.id.fragment_home, repoFragment).commit()
                position = REPO_POSITION
            }
        }
    
    ```
    
    * ##### initTransactionEvent()구현 
    * ##### 팔로워 목록 Fragment가 기본값이며 레포지토리 목록 버튼을 누를 시 Fragment 전환됨

* ### item_repo_list.xml

    ```kotlin
    
         <TextView
            ...
            android:ellipsize="end"
            android:ems="8"
            android:maxLines="1"
            android:text="@{repo.repo}"
            android:textColor="@color/black"
            android:textSize="17sp"
            android:textStyle="bold"
            ... />

        <TextView
            ...
            android:ellipsize="end"
            android:ems="7"
            android:maxLines="1"
            android:text="@{repo.introduction}"
            android:textColor="@color/black"
            android:textSize="15sp"
            ... />
   
     ```
     
     * ##### 설명이 길어 글씨가 길어질 때 뒤에 "..."으로 표시하기
     * ##### ems , maxLines , ellipsize 속성 사용
 
 * ### fragment_repo.xml

    ```kotlin
   
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    
    ```
    
    * ##### xml에서 GridLayoutManager설정 -> .kt파일에서도 설정 가능!!!!
    
 ## seminar2-level2 
 
 * ### FollowerFragment : 팔로워 이름과 설명을 name, introduce로 DetailActivity로 전달

    ```kotlin
   
        private fun itemClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val name = followerAdapter.currentList[position].name
                val introduce = followerAdapter.currentList[position].introduction
                val intent = Intent(context, DetailActivity::class.java)
                    .putExtra("name", name)
                    .putExtra("introduction", introduce)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

          })
        }
    
    ```
    

* ### ItemDecoration으로 리스트 간 간격 및 구분선 주기

    ```kotlin
   
        private fun recyclerViewDecoration() {
                with(binding) {
                    rvFollower.addItemDecoration(VerticalItemDecorator(10))
                    rvFollower.addItemDecoration(HorizontalItemDecorator(10))
                }
         }
         
    ```
    
* ### RecyclerView Item 이동 구현 : ItemTouchHelper() 사용

    ```kotlin
  
        val callback = MyTouchHelperCallback(followerAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvFollower)
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerAdapter.FollowerViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
         
    ```
 ## seminar2-level3 
 
 * ### BaseFragment : 보일러 플레이트 코드 개선

    ```kotlin
    
        abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
        Fragment() {
        private var _binding: B? = null
        val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.lifecycleOwner = this
            initAdapter()
        }

        abstract fun initAdapter()

        override fun onDestroyView() {
            _binding = null
            super.onDestroyView()
            }

        }
    
    ```
    
     * ##### Fragment 생명주기를 이용하여 onCreateView에서는 view에 대한 초기화 작업을 수행
     * ##### onViewCreated에서는 recyclerView에 실제 adapter를 붙여 데이터를 보여주게끔 구현

* ### ListAdapter, DIFFUTIL : notifyDataSetChanged 문제점 해결

    ```kotlin
    
      class RepoViewHolder(
        private val binding: ItemRepoListBinding
      ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(repoData: RepoData) {
            binding.repo = repoData
        }
      }
    
    
        companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<RepoData>() {
            override fun areItemsTheSame(
                oldItem: RepoData,
                newItem: RepoData
            ): Boolean {
                return oldItem.repo == newItem.repo
            }

            override fun areContentsTheSame(
                oldItem: RepoData,
                newItem: RepoData
            ): Boolean {
                return oldItem == newItem
            }
          }
        }
    
    ```
    
     * ##### Fragment,item_xxx_list 모두 DataBinding으로 : repo
     * ##### DIFFUTIL로 기존의 데이터 리스트와 교체할 데이터 리스트를 비교하여 실질적으로 업데이트가 필요한 아이템을 추려 notifyDataSetChanged 문제점 보완
     
 * ### 🙀 notifyDataSetChanged 문제점
    
    #### 수천개의 데이터 중 단 한 개의 데이터만 바뀐다면 notifyDataSetChanged()의 사용은 비효율적이다.
    #### 변경된 데이터의 position을 인자로 넘겨주어 해당 데이터만 변경하는 notifyItemChanged가 있지만 역시나 position을 찾아 넘겨주며 하나하나 값을 변경하는 번거로운 일이 발생한다.

    #### 이때 아이템의 변경을 감지하고 갱신하는 역할 "DIFFUTIL" 을 사용한다. DIFFUTIL은 oldList와 newList를 비교하여 차이를 계산하고, newList로 갱신해주는 유틸리티 클래스이다.

    #### 이 클래스를 사용하면 아이템 변경의 구체적인 상황에 따라 Adapter의 적절한 메소드를 호출하지 않아도 된다.
    

* ### ListAdapter ,DIFFUTIL 관련 내용

    * ListAdapter는 DiffUtil을 활용하여 리스트를 업데이트할 수 있는 기능을 추가한 Adapter, 기존 어댑터와 비교해서 추가로 DiffUtil 기능에 대한 콜백 기능 클래스만 구현하면 되므로 생산성, 효율성을 높일 수 있다.
    
    * https://velog.io/@l2hyunwoo/Android-RecyclerView-DiffUtil-ListAdapter

    * https://velog.io/@deepblue/RecyclerView%EC%9D%98-notifyDataSetChanged

 ## 📸 실행 화면
 
DetailActivity|아이템 이동|
|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/62291759/164503699-460b534a-e4b4-4333-aa11-df157deb6e9a.gif" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/62291759/164504741-0aa07e65-d9dc-437b-abb5-66c7caf7c505.gif" width="250" height="500"/>|

### 📝 seminar2 알게된 점 📝

##### ‣  보일러플레이트 코드 개선 : BaseActivity, BaseFragment

##### ‣ ItemTouchHelper() , ItemDecoration() 내용

##### ‣ ListAdapter, DIFFUTIL, notifyDataSetChanged 복습 및 차이점 

##### ‣ Fragment 생명주기 : onViewCreated()


---
### 3️⃣ Week
## seminar3-level1

* ### ProFileFragment

    ```kotlin
    
       private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepoFragment()

        childFragmentManager.beginTransaction().add(R.id.fragment_profile, followerFragment)
            .commit()
        binding.btnFollower.isSelected = true //처음 화면 보여질 시에
        binding.btnFollower.setTextColor(Color.BLACK)

        binding.btnFollower.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_profile, followerFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnFollower.isSelected = true;
            binding.btnRepo.isSelected = false;
            binding.btnRepo.setTextColor(Color.GRAY)
            binding.btnFollower.setTextColor(Color.BLACK)
        }
        binding.btnRepo.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_profile, repositoryFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnRepo.isSelected = true
            binding.btnFollower.isSelected = false
            binding.btnFollower.setTextColor(Color.GRAY)
            binding.btnRepo.setTextColor(Color.BLACK)
         }
      }
    
    ```
     * ##### button, textView 클릭 시 색 변경 로직 코드
     * ##### childFragmentManager()로 한 뷰에 follower, repository 프래그먼트 연결

* ### HomeFragment

    ```kotlin
    
         private fun initTabLayout() {
            val tabLable = listOf("팔로잉", "팔로워")

            TabLayoutMediator(binding.homeTablayout, binding.vpHome) { tab, position ->
                tab.text = tabLable[position]
            }.attach()
        }
        
    ```
    #### fragment_home.xml
    
    ```kotlin
    
        <com.google.android.material.tabs.TabLayout
        android:id="@+id/home_tablayout"
        ...
        app:tabIndicatorColor="@color/sopt_main_purple"
        app:tabMode="fixed"
        app:tabSelectedTextColor="@color/sopt_main_purple" />
    
    ```
     * ##### TabLayout 설정
     * ##### indicator 색상 설정 및 클릭 시 text 색상 변경
     
     
* ### ViewPagerAdapter(Main)

    ```kotlin
    
    class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> HomeFragment()
            else -> CameraFragment()
            }
        }
    }
    
   ```
    * ##### viewPagerAdapter 파일 분리 후 사용
 
 ## seminar3-level2

* ### NestedScrollableHost - framgent_home.xml

  ```kotlin
  
    <org.sopt.seminar.NestedScrollableHost
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/home_tablayout">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_home"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#FAFAFA" />
    </org.sopt.seminar.NestedScrollableHost>
  
  ```
   * ##### NestedScrollableHost 파일 생성 후 fragment_home에 적용 -> ViewPager2 중첩 스크롤 문제 해결
   
  ## seminar3-level3
  
* ### CameraFragment

  ```kotlin
     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  ```
   * ##### 카메라 갤러리 접근을 위한 권한을 AndroidManifest.xml에 추가
   
   
  ```kotlin
  
        private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                var currentImageUri = it.data?.data
                Glide.with(requireActivity()).load(currentImageUri).into(binding.ivGalleryImage)

            } else if (it.resultCode == RESULT_CANCELED) {
                requireActivity().showToast("사진 선택 취소")
            } else {
                requireActivity().showToast("사진 첨부 실패")
          }
     }
  ```
  * ##### resultCode, registerForActivityResult()를 통해 사진을 갤러리에서 가져온 후 "ivGalleryImage"에 넣기

 ## 📸 실행 화면
 
Profile,Home|Camera|
|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/62291759/164503699-460b534a-e4b4-4333-aa11-df157deb6e9a.gif" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/62291759/164504741-0aa07e65-d9dc-437b-abb5-66c7caf7c505.gif" width="250" height="500"/>|

### 📝 seminar3 알게된 점 📝

##### ‣  nestedscrollableHost 개념

##### ‣ shapeDrawable으로 간단한 도형을 만들 수 있고 적용할 수 있다.

##### ‣ 갤러리에서 사진가져와서 이미지뷰로 보여줄 수 있다.
