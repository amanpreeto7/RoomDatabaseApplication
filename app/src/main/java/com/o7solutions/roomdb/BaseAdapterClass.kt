package com.o7solutions.roomdb

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.o7solutions.roomdb.databinding.ItemAdapterBinding

class BaseAdapterClass(var arrayList: ArrayList<TodoEntity>, var activity: Activity): BaseAdapter() {
    override fun getCount(): Int {
       return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return "Any"
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//        var initView = LayoutInflater.from(p2?.context).inflate(R.layout.item_adapter,p2,false)
        var initView = ItemAdapterBinding.inflate(activity.layoutInflater)
//        var tvName :TextView = initView.findViewById(R.id.tv_Task)
//        var tvDate :TextView = initView.findViewById(R.id.tv_Date)
       // initView.tvTask.setText(arrayList[p0].id.toString())
       // initView.tvDate.setText(arrayList[p0].date.toString())

        initView.todo = arrayList[p0]
        return initView.root
    }
}