package com.example.fundamentalskotlin.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.fundamentalskotlin.MainActivity
import com.example.fundamentalskotlin.R


class MovieTopNotification(private val context: Context)  {
    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    init {
        notificationManagerCompat.createNotificationChannel(
            NotificationChannelCompat.Builder(CHANNEL_MOVIE_TOP, NotificationManagerCompat.IMPORTANCE_HIGH)
                .setName(context.getString(R.string.chanel_movie_top))
                .setDescription(context.getString(R.string.chanel_movie_top_description))
                .build()
        )
    }

      fun showNotification(movie: Movie) {
        val contentUri = "https://themoviedb.org/movie/${movie.id}".toUri()
        val movieIntent = Intent(context, MainActivity::class.java).setAction(Intent.ACTION_VIEW)
            .setData(contentUri)

          val pendingIntent = PendingIntent.getActivity(
              context,
              REQUEST_CONTENT,
              movieIntent,
              PendingIntent.FLAG_UPDATE_CURRENT)

          val notification = NotificationCompat.Builder(context, CHANNEL_MOVIE_TOP)
              .setContentTitle(movie.title)
              .setContentText(movie.overview)
              .setSmallIcon(R.drawable.icon)
              .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
              .setContentIntent(pendingIntent)
              .setAutoCancel(true)
              .build()

          notificationManagerCompat.notify(MOVIE_TOP_TAG, movie.id, notification)
    }

    companion object {

        private const val CHANNEL_MOVIE_TOP = "chanel_movie_top"
        private const val REQUEST_CONTENT = 1
        private const val MOVIE_TOP_TAG = "movie_top_rating"
    }

}
