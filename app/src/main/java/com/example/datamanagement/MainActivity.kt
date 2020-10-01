package com.example.datamanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.datamanagement.adapter.QuoteAdaper
import com.example.datamanagement.data.Quote
import com.example.datamanagement.viewmodels.QuoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var quoteViewModel: QuoteViewModel
    private val requestCode_create_quote=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]

        quoteViewModel.allQuotes.observe(this, Observer {
            it?.let {
                val adapter=QuoteAdaper(it)
                recycleview.adapter=adapter
                recycleview.setHasFixedSize(true)

            }
        })
        floating_button.setOnClickListener {
            Intent(this,CreateQuoteActivity::class.java).apply {
                startActivityForResult(this,requestCode_create_quote)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       if (requestCode==requestCode_create_quote && resultCode==Activity.RESULT_OK){
           data?.let { 
               val quote=it.getStringExtra(CreateQuoteActivity.EXTRA_REPLY)
               quoteViewModel.insert(Quote(quote))
           }
                    
       }
        else Toast.makeText(this, "Can not save empty Quote", Toast.LENGTH_LONG).show()
    }
}