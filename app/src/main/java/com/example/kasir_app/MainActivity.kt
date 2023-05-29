package com.example.kasir_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var editTextItemName: EditText
    private lateinit var editTextItemPrice: EditText
    private lateinit var buttonAddItem: Button
    private lateinit var linearLayoutItemList: LinearLayout
    private lateinit var textViewTotalPrice: TextView
    private lateinit var buttonCheckout: Button

    private var itemList: MutableList<Item> = mutableListOf()
    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextItemName = findViewById(R.id.editTextItemName)
        editTextItemPrice = findViewById(R.id.editTextItemPrice)
        buttonAddItem = findViewById(R.id.buttonAddItem)
        linearLayoutItemList = findViewById(R.id.linearLayoutItemList)
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice)
        buttonCheckout = findViewById(R.id.buttonCheckout)

        buttonAddItem.setOnClickListener {
            val name = editTextItemName.text.toString()
            val price = editTextItemPrice.text.toString().toDoubleOrNull()

            if (!name.isNullOrEmpty() && price != null) {
                val item = Item(name, price)
                itemList.add(item)
                addCartItemView(item)
                updateTotalPrice()
                clearInputFields()
            }
        }

        buttonCheckout.setOnClickListener {
            Toast.makeText(this, "CheckOut Berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addCartItemView(item: Item) {
        val textView = TextView(this)
        textView.text = "${item.name}: Rp ${item.price}"
        linearLayoutItemList.addView(textView)
    }

    private fun updateTotalPrice() {
        totalPrice = itemList.sumByDouble { it.price }
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(totalPrice)
        textViewTotalPrice.text = "Total Harga: $formattedPrice"
    }

    private fun clearInputFields() {
        editTextItemName.text.clear()
        editTextItemPrice.text.clear()
    }

    data class Item(val name: String, val price: Double)
}
