package com.zang.quizapp.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.zang.quizapp.R
import com.zang.quizapp.interfaces.IOnRecyclerViewItemClickListener
import com.zang.quizapp.model.Category
import kotlinx.android.synthetic.main.layout_category.view.*

class CategoryAdapter(internal var context: Context, internal var categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_category, p0, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holer: MyViewHolder, position: Int) {
        holer.txt_category_name.text = categoryList[position].name
        holer.setiOnRecyclerViewItemClickListener(object: IOnRecyclerViewItemClickListener{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context, categoryList[position].name, Toast.LENGTH_SHORT).show()
            }
        })
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var txt_category_name: TextView
        internal var card_category: CardView
        internal lateinit var iOnRecyclerViewItemClickListener: IOnRecyclerViewItemClickListener

        fun setiOnRecyclerViewItemClickListener(iOnRecyclerViewItemClickListener: IOnRecyclerViewItemClickListener) {
            this.iOnRecyclerViewItemClickListener = iOnRecyclerViewItemClickListener
        }

        init {
            txt_category_name = itemView.txt_category_name
            card_category = itemView.card_category
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iOnRecyclerViewItemClickListener.onClick(view!!, adapterPosition)
        }

    }
}