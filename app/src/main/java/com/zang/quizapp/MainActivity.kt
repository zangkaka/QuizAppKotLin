package com.zang.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.zang.quizapp.adapter.CategoryAdapter
import com.zang.quizapp.database.DBHelper
import com.zang.quizapp.helper.SplacesItemDecoration
import com.zang.quizapp.model.Category
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "QUIZ"
        setSupportActionBar(toolbar)

        recycler_category.setHasFixedSize(true)
        recycler_category.layoutManager = GridLayoutManager(this, 2)
        val adapter = CategoryAdapter(this, DBHelper.getInstance(this).allCategorys)
        recycler_category.addItemDecoration(SplacesItemDecoration(4))
        recycler_category.adapter = adapter
    }
}
