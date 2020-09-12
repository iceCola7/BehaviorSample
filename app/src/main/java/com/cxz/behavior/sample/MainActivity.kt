package com.cxz.behavior.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.cxz.behavior.sample.adapter.MyFragmentPagerAdapter
import com.cxz.behavior.sample.ext.dp2px
import com.cxz.behavior.sample.fragment.SongFragment
import com.cxz.behavior.sample.fragment.TabFragment
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    private val mTitles = arrayOf(
        "热门", "专辑", "视屏", "资讯"
    )

    private val mFragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTranslucentForImageView(this, 0, null)

        initView()
    }

    private fun initView() {
        mFragments.add(SongFragment())
        mFragments.add(TabFragment.newInstance("我是专辑页面"))
        mFragments.add(TabFragment.newInstance("我是视屏页面"))
        mFragments.add(TabFragment.newInstance("我是资讯页面"))

        val fragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, mFragments)
        viewPager.adapter = fragmentPagerAdapter
        stl.setViewPager(viewPager, mTitles)

        // 反射修改最少滑动距离
        try {
            val mTouchSlop: Field = ViewPager::class.java.getDeclaredField("mTouchSlop")
            mTouchSlop.isAccessible = true
            mTouchSlop.setInt(viewPager, dp2px(50f))
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        viewPager.offscreenPageLimit = mFragments.size
    }

}