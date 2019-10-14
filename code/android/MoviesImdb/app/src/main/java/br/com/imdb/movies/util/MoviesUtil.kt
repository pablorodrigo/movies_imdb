package br.com.imdb.movies.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by pablo on 13/10/2019.
 */
object MoviesUtil {

    fun dateFormat(text: String?): String? {

        //check version of android to update Movie date
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")
            val movieDate = LocalDate.parse(text, DateTimeFormatter.ISO_DATE)
            movieDate?.format(formatter)
        } else {
            val formatter = SimpleDateFormat("EEE, dd MMM yyyy");
            formatter.format(text)
        }

    }

}