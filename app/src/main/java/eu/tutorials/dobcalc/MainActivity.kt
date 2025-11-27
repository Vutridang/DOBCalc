package eu.tutorials.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDayOfMont ->
                Toast.makeText(
                    this,
                    "Year was $selectedYear, month was ${selectedMonth + 1}, day of month was $selectedDayOfMont",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayOfMont/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectdDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate.time/ 60000

                val differenceInMinutes = currentDateInMinutes - selectdDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )

        dpd.show()
    }
}