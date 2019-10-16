package com.zang.quizapp.interfaces

import android.view.View

interface IOnRecyclerViewItemClickListener {
    fun onClick(view: View, position: Int)
}