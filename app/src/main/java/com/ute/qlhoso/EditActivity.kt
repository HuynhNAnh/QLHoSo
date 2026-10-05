package com.ute.qlhoso

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
    }

    private lateinit var edtName: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        edtName = findViewById(R.id.edtName)
        btnSave = findViewById(R.id.btnSave)

        // 1. Nhận tên hiện tại từ Intent và điền sẵn vào EditText
        val currentName = intent.getStringExtra(EXTRA_NAME) ?: ""
        if (currentName.isNotEmpty() && currentName != getString(R.string.default_name)) {
            edtName.setText(currentName)
            edtName.setSelection(edtName.text.length)
        }

        // 2. Xử lý khi bấm nút "Lưu & Quay lại"
        btnSave.setOnClickListener {
            val updatedName = edtName.text.toString().trim()
            if (updatedName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Đóng gói tên mới qua setResult(RESULT_OK) và gọi finish()
            val resultIntent = Intent().apply {
                putExtra(EXTRA_NAME, updatedName)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
