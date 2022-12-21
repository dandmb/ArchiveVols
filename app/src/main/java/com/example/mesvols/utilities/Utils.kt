package com.example.mesvols.utilities


/**
 * Created by sergio on 07/11/2021
 * All rights reserved GoodBarber
 *
 * TOMAKE this work put this dependencies in the build.gradle:
 *
 *     implementation 'commons-io:commons-io:2.4'
 *     implementation 'com.google.code.gson:gson:2.8.6'
 */

const val TAG: String = "Utils"

class Utils private constructor() {

    companion object {


        fun formatFlightDuration(time: Long): String? {
            var time = time
            if (time < 1) return ""
            time /= 60
            val hour = time / 60
            val minute = time % 60
            val duration = StringBuilder()
            return if (hour > 0) {
                duration.append(hour).append("h")
                if (minute < 10) {
                    duration.append("0").append(minute).toString()
                } else duration.append(minute).toString()
            } else {
                if (minute < 10) {
                    duration.append("0").append(minute).append("min").toString()
                } else duration.append(minute).append("min").toString()
            }
        }

    }
}