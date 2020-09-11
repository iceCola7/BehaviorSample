package com.cxz.behavior.sample.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.cxz.behavior.sample.R

/**
 * @author chenxz
 * @date 2020/9/11
 * @desc TitleBar 部分的 Behavior
 */
class TitleBarBehavior : CoordinatorLayout.Behavior<View> {

    // topBar 高度
    private var topBarHeight = 0

    // 滑动内容初始化 TransY
    private var contentTransY = 0F

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        topBarHeight = (context.resources.getDimension(R.dimen.top_bar_height) + statusBarHeight).toInt()
        contentTransY = context.resources.getDimension(R.dimen.content_trans_y)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        // 依赖 Content View
        return dependency.id == R.id.ll_content
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        // 找到Content的依赖引用
        val dependencies = parent.getDependencies(child)
        var dependency: View? = null
        for (view in dependencies) {
            if (view.id == R.id.ll_content) {
                dependency = view
                break
            }
        }
        return if (dependency != null) {
            // 调整TitleBar位置要紧贴Content顶部上面
            adjustPosition(parent, child, dependency)
            true
        } else {
            false
        }
    }

    private fun adjustPosition(parent: CoordinatorLayout, child: View, dependency: View) {
        val lp = child.layoutParams as CoordinatorLayout.LayoutParams
        val left = parent.paddingLeft + lp.leftMargin
        val top = (dependency.y - child.measuredHeight + lp.topMargin).toInt()
        val right = child.measuredWidth + left - parent.paddingRight - lp.rightMargin
        val bottom = (dependency.y - lp.bottomMargin).toInt()
        child.layout(left, top, right, bottom)
    }
}