package org.sopt.seminar

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class DividerItemDecoration(
    context: Context?,
    resId: Int,
    val paddingLeft: Int,
    val paddingRight: Int
) : ItemDecoration() {
    private var mDivider: Drawable? = null
    init {
        mDivider = context?.let { ContextCompat.getDrawable(it, resId) }
    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - parent.paddingRight - paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)
            mDivider?.let {
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }
}