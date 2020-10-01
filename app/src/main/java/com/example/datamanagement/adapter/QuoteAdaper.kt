package com.example.datamanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datamanagement.R
import com.example.datamanagement.data.Quote
import kotlinx.android.synthetic.main.item_quote.view.*
import java.util.zip.Inflater

class QuoteAdaper(private val quotes:List<Quote>):
    RecyclerView.Adapter<QuoteAdaper.ItemQuoteViewHolder>() {

    class ItemQuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Quote) {
            itemView.quote.text=item.word
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemQuoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_quote,parent,false)
        return  ItemQuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemQuoteViewHolder, position: Int) {
       val item=quotes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return quotes.size
    }
}