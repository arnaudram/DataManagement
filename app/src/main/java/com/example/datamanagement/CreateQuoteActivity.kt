package com.example.datamanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datamanagement.R
import kotlinx.android.synthetic.main.activity_create_quote.*

class CreateQuoteActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_REPLY="com.example.datamanagement.REPLY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_quote)

        button_save.setOnClickListener {
            val sendItent= Intent()
            if(edit_quote.text.toString().isEmpty()){
                setResult(Activity.RESULT_CANCELED,sendItent)
            }else
                {val quote=edit_quote.text.toString()
                    sendItent.putExtra(EXTRA_REPLY,quote)
                    setResult(Activity.RESULT_OK,sendItent)
                }
            finish()
        }
    }
}