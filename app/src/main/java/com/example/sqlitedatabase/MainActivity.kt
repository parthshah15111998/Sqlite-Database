package com.example.sqlitedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {


            val db = DBHelper(this, null)

            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString()

            db.addName(name, age)
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            binding.etName.text.clear()
            binding.etAge.text.clear()

        }

        binding.btnView.setOnClickListener {

            val db = DBHelper(this, null)

            val cursor = db.getName()
            cursor!!.moveToFirst()
            binding.Name.text = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n"
            binding.Age.text = cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n"
            while(cursor.moveToNext()){
                binding.Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n")
                binding.Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }
            cursor.close()

        }
    }
}

