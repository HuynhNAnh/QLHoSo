package com.ute.qlhoso

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvCurrentName: TextView
    private lateinit var btnEditProfile: Button

    // Đón nhận tên mới được trả về từ EditActivity và cập nhật lại giao diện
    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedName = result.data?.getStringExtra(EditActivity.EXTRA_NAME)
            if (!updatedName.isNullOrBlank()) {
                tvCurrentName.text = updatedName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCurrentName = findViewById(R.id.tvCurrentName)
        btnEditProfile = findViewById(R.id.btnEditProfile)

        // Nút bấm 'Chỉnh sửa thông tin' -> Gửi dữ liệu hiện tại sang EditActivity
        btnEditProfile.setOnClickListener {
            val currentName = tvCurrentName.text.toString()
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra(EditActivity.EXTRA_NAME, currentName)
            }
            editProfileLauncher.launch(intent)
        }
    }
}