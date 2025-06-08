package com.ags.quickupdates.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Common {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(isoString: String): String {
        return try {
            val instant = Instant.parse(isoString)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                .withZone(ZoneId.systemDefault())
            formatter.format(instant)
        } catch (e: Exception) {
            ""
        }
    }

}