package com.o7solutions.roomdb

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.o7solutions.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var arrayTask = arrayListOf<String>("Tasks")
    var arrayDate = arrayListOf<String>("Dates")
    var baseAdapter : BaseAdapterClass = BaseAdapterClass(arrayTask,arrayDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lvNotes.adapter = baseAdapter
        binding.fabAdd.setOnClickListener() {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_alert_dialogue)
            val etEnterUpdate = dialog.findViewById<EditText>(R.id.etEnterupdate)
            val btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
            val datePicker = dialog.findViewById<DatePicker>(R.id.dp)
            btnUpdate.setOnClickListener {
                if (etEnterUpdate.text.toString().isEmpty()) {
                    etEnterUpdate.error = "Enter Text"
                    return@setOnClickListener
                }
                val day = datePicker.dayOfMonth
                val month = datePicker.month
                val year = datePicker.year
                if (day == 0 || month == 0 || year == 0) {
                    Toast.makeText(this, "Pick Date ", Toast.LENGTH_SHORT).show()
                } else {
                    arrayTask.add(etEnterUpdate.text.toString())
                    arrayDate.add("$day/$month/$year")
                    baseAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding.lvNotes.setOnItemLongClickListener { adapterView, view, i, l ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete")
            builder.setPositiveButton("yes",{_,_ ->
                arrayTask.removeAt(i)
                arrayDate.removeAt(i)
                baseAdapter.notifyDataSetChanged()
            })
            builder.setNegativeButton("No",{_,_ ->

            }).show()
       return@setOnItemLongClickListener true
        }
        binding.lvNotes.setOnItemClickListener { _, _, position, _ ->
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_task_editor)
            val tv_Date_Detail = dialog.findViewById<TextView>(R.id.tv_Date_Detail)
            val tv_Task_Detail = dialog.findViewById<TextView>(R.id.tv_Task_detail)
            val btn_Update_Task_Detail_ = dialog.findViewById<Button>(R.id.btn_Update_Task_Detail)
            tv_Task_Detail.text = arrayTask[position]
            tv_Date_Detail.text = arrayDate[position]
            btn_Update_Task_Detail_.setOnClickListener(){
                if (tv_Task_Detail.text.isNullOrEmpty() ){
                    tv_Task_Detail.error = "Enter Task "
                }
                if (tv_Date_Detail.text.isNullOrEmpty() ){
                    tv_Date_Detail.error = "Enter Date "
                }
            else{
               arrayTask[position] =  tv_Task_Detail.text.toString()
               arrayDate[position] =  tv_Date_Detail.text.toString()
            }
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

            dialog.show()
        }


    }




    }
