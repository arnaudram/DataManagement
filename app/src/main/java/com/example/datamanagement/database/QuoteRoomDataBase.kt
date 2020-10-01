package com.example.datamanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.datamanagement.dao.QuoteDao
import com.example.datamanagement.data.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Quote::class],version = 1,exportSchema = false)
 abstract class QuoteRoomDataBase:RoomDatabase() {
    abstract val quoteDao:QuoteDao
   //populating the database
    class QuoteDataBaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
       override fun onOpen(db: SupportSQLiteDatabase) {
           super.onOpen(db)
           if (::singleDatabase.isInitialized){
               val quoteDao= singleDatabase.quoteDao
               scope.launch {
                   quoteDao.deleteAll()
                   var quote=Quote(word = "When your are almost get there,you are more likely to surrender")
                   quoteDao.insert(quote)
                   quote= Quote("dont you dare slow down")
                   quoteDao.insert(quote)
               }
           }
       }
   }
}
private lateinit var singleDatabase: QuoteRoomDataBase
fun getSingleDataBase(context:Context,coroutineScope: CoroutineScope):QuoteRoomDataBase{
     if (!::singleDatabase.isInitialized){
         synchronized(QuoteRoomDataBase::class){
             singleDatabase= Room.databaseBuilder(context.applicationContext,QuoteRoomDataBase::class.java,"quote_database")

                 .addCallback(QuoteRoomDataBase.QuoteDataBaseCallback(coroutineScope))
                 .build()
         }
     }
    return singleDatabase
}