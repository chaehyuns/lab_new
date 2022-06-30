package com.chaehyun.lab_new

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email.DISPLAY_NAME
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.chaehyun.lab_new.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                Log.i("jch", "cursorsize...${DISPLAY_NAME.length}")
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null)

                Log.d("jch", "cursorsize...${DISPLAY_NAME?.length}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    Log.d("jch","name: $name, phone: $phone")
                }

            }
        }
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        requestContactsLauncher.launch(intent)
    }
}