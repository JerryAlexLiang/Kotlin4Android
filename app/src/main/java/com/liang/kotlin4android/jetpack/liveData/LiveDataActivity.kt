package com.liang.kotlin4android.jetpack.liveData

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 * 创建日期:2020/8/19 on 1:41 PM
 * 描述: LiveData是JetPack提供的一种响应式编程组件，它可以包含任何类型的数据，并在数据发生变化的时候通知给观察者；
 * 通常，LiveData与ViewModel结合在一起使用
 * 作者: 杨亮
 */
class LiveDataActivity : BaseActivity() {

    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context, LiveDataActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "LiveDataActivity"

        private const val COUNT_SP = "counter_reserved"
    }

    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: LiveDataViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        title = "JetPack组件集合-LiveData响应式编程组件"

        //获取SharedPreferences实例
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(COUNT_SP, 0)

        val user = User("丁程鑫", "马嘉祺", 18)

        //1、绝对不能将Activity的实例传给ViewModel，因为ViewModel的生命周期是长于Activity的，
        // 如果把Activity的实例传给ViewModel，就有可能会因为Activity无法释放而造成内存泄露

        //2、在CounterActivity中，我们一直使用的都是在Activity中手动获取ViewModel中的数据这种交互方式，
        // 但是ViewModel却无法将数据的变化主动通知给Activity,解决办法：LiveData
        // LiveData可以包含任何类型的数据，并在数据发生变化的时候通知给观察者。
        // 也就是说，将计数器的计数使用LiveData来包装，然后再Activity中去观察它，就可以主动地将数据变化通知给Activity了。


        //1、注意：绝对不能直接去创建ViewModel的实例，而是使用ViewModelProviders来获取CounterViewModel的实例
        //因为ViewModel有其独立的生命周期，并且生命周期要长于Activity
        //如果在onCreate()方法中创建ViewModel的实例，那么每次onCreate()方法执行的时候，ViewModel都会创建一个新的实例，这样当手机屏幕发生旋转的时候，就无法保留其中的数据了
//        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //传参ViewModel，这里of额外传入了CounterViewModelFactory参数，这里将读取到的countReserved传给了CounterViewModelFactory的构造函数
        //注意，这一步是非常重要的，只有用这种写法，才能将计数值最终传给CounterViewModel的构造函数。
        viewModel = ViewModelProviders.of(this, LiveDataViewModelFactory(countReserved, user))
            .get(LiveDataViewModel::class.java)


        btnPlusOne2.setOnClickListener {
            viewModel.plusOne()
        }

        btnClear2.setOnClickListener {
            viewModel.clear()
        }

        //关键：观察LiveData数据变化
        //这里调用了viewModel.counter的observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer)方法来观察数据的变化；
        //counter变量是一个LiveData对象，任何LiveData对象都可以调用它的observe()方法来观察数据的变化
        //observe()方法接收两个参数：
        // 第一个参数是LifecycleOwner对象（Activity、Fragment本身就是一个LifecycleOwner对象）
        // 第二个参数是一个Observer接口，当counter中的数据发生变化的时候，就会回调到这里，因此在这里将最新的计数更新到界面上即可。

        //这样的写法是更加科学，更加合理的，也不用担心ViewModel的内部会不会开启线程执行耗时逻辑
        //不过需要注意的是：如果需要在子线程中给LiveData设置数据，一定要调用postValue()方法，不能再使用setValue()方法
//        viewModel.counter.observe(this, Observer { count ->
//            tvCounter2.text = count.toString()
//        })

        //lifecycle-livedata-ktx是一个专门为Kotlin语言设计的库，
        // 在2.2.0版本中加入了对observe()方法的语法扩展，可以使用下面的语法结构的observe()方法
        viewModel.counter.observe(this) { count ->
            tvCounter2.text = count.toString()
        }

        //LiveData的转换方法 map()方法
        viewModel.userName.observe(this) { name ->
            tvUserName.text = name
        }

        btnLiveDataMap.setOnClickListener {
            viewModel.mapTrans()
        }

        //LiveData的转换方法 switchMap()方法
        viewModel.mUser.observe(this) { mUser ->
            tvUserName.text = mUser.firstName
        }

        btnLiveDataSwitchMapGetUser.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.userName2.observe(this) { name2 ->
            tvUserName2.text = name2
        }
    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            //注意，调用LiveData的getValue()方法所获得的的数据可能是空的，因此使用?:操作符，当获取的数据为空的时候，就用0来作为默认计数
            putInt(COUNT_SP, viewModel.counter.value ?: 0)
        }
    }

    /**
     * 总结：
     * 1、LiveData之所以能够成为Activity与ViewModel之间通信的桥梁，并且还不会有内存泄漏的风险，靠的就是LifeCycles组件；
     * 2、LiveData在内部使用了LifeCycles组件来自我感知生命周期的变化，从而可以在Activity销毁的时候及时释放引用，避免产生内存泄漏问题；
     * 3、另外，由于要减少性能消耗，当Activity处于不可见状态的时候，如果LiveData中的数据发生了变化，是不会通知给观察者的，
     * 只有当Activity重新恢复可见状态时，才会将数据通知给观察者，而LiveData之所以能够实现这种细节的优化，依靠的还是LifeCycles组件；
     * 4、一个细节：如果在Activity处于不可见状态的时候，LiveData发生了多次数据变化，当Activity恢复可见状态时，
     * 只有最新的那份数据才会通知给观察者，前面的数据在这种情况下相当于已经过期了，会被直接丢弃。
     */

}