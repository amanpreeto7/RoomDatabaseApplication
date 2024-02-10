package com.o7solutions.roomdb

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.o7solutions.roomdb.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var todoEntityList = arrayListOf<TodoEntity>()
    var baseAdapter: BaseAdapterClass = BaseAdapterClass(todoEntityList)
    lateinit var todoDatabase: TodoDatabase
    var simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy, hh:mm:ss")
    var serverDate = "2022-03-07T06:52:04.438Z"
    var serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var date = serverDateFormat.parse(serverDate)
        Log.e("TAG","parsed Date $date")
        todoDatabase = TodoDatabase.getDatabaseInstance(this)

//        todoEntityList.addAll(todoDatabase.todoDao().getTodoEntities())
        getDatabaseValues()
        binding.lvNotes.adapter = baseAdapter
        binding.fabAdd.setOnClickListener() {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_alert_dialogue)
            val etEnterUpdate = dialog.findViewById<EditText>(R.id.etEnterupdate)
            val btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
            val etSelectDate = dialog.findViewById<EditText>(R.id.etSelectDate)
            val etSelectTime = dialog.findViewById<EditText>(R.id.etSelectTime)
            var nextDate = Calendar.getInstance()
            nextDate.add(Calendar.MINUTE, +15)

            Log.e("TAG", "with 15 minutes $nextDate")
            etSelectDate.setOnClickListener{
                DatePickerDialog(this, {_, year, month,dateOfMonth->
                                       println("Date is $year $month $dateOfMonth")
                    //calendar.set(year, month, dateOfMonth)
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, month)
                    var formattedDate = simpleDateFormat.format(calendar.time)
                    etSelectDate.setText(formattedDate)
                },
                    (1900+date.year), date.month, date.date
                    /*calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),*/  ).show()
            }
            etSelectTime.setOnClickListener{
                TimePickerDialog(this, {_, hour, minute->
                                       println("Date is $hour $minute")
                    //calendar.set(year, month, dateOfMonth)
                    calendar.set(Calendar.HOUR, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    var formattedDate = simpleDateFormat.format(calendar.time)
                    etSelectTime.setText(formattedDate)
                },
//                    (1900+date.year), date.month, date.date
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true  ).show()
            }
            btnUpdate.setOnClickListener {
                if (etEnterUpdate.text.toString().isEmpty()) {
                    etEnterUpdate.error = "Enter Text"
                    return@setOnClickListener
                }
                // arrayTask.add(etEnterUpdate.text.toString())
                todoDatabase.todoDao()
                    .insertTodo(TodoEntity(todoItem = etEnterUpdate.text.toString()))
//                    arrayDate.add("$day/$month/$year")
//                baseAdapter.notifyDataSetChanged()
                getDatabaseValues()
                dialog.dismiss()

            }
            dialog.show()
        }
        binding.lvNotes.setOnItemLongClickListener { adapterView, view, i, l ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete")
            builder.setPositiveButton("yes", { _, _ ->
//                arrayTask.removeAt(i)
//                arrayDate.removeAt(i)
                todoDatabase.todoDao().deleteTodoEntity(todoEntityList[i])
                getDatabaseValues()
            })
            builder.setNegativeButton("No", { _, _ ->

            }).show()
            return@setOnItemLongClickListener true
        }
        binding.lvNotes.setOnItemClickListener { _, _, position, _ ->
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.custom_task_editor)
            val tv_Date_Detail = dialog.findViewById<TextView>(R.id.tv_Date_Detail)
            val tv_Task_Detail = dialog.findViewById<TextView>(R.id.tv_Task_detail)
            val btn_Update_Task_Detail = dialog.findViewById<Button>(R.id.btn_Update_Task_Detail)
//            tv_Task_Detail.text = arrayTask[position]
//            tv_Date_Detail.text = arrayDate[position]
            btn_Update_Task_Detail?.setOnClickListener() {
                if (tv_Task_Detail?.text.isNullOrEmpty()) {
                    tv_Task_Detail?.error = "Enter Task "
                }
                if (tv_Date_Detail?.text.isNullOrEmpty()) {
                    tv_Date_Detail?.error = "Enter Date "
                } else {
                    todoDatabase.todoDao()
                        .updateTodoEntity(
                            TodoEntity(
                                id = todoEntityList[position].id,
                                todoItem = tv_Task_Detail?.text.toString()
                            )
                        )
                    dialog.dismiss()
                    getDatabaseValues()
                }
            }
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }
    }

    fun getDatabaseValues() {
        todoEntityList.clear()
        todoEntityList.addAll(todoDatabase.todoDao().getTodoEntities())
        baseAdapter.notifyDataSetChanged()
    }

}
