package com.waz.zclient.preferences

import android.content.Context
import android.support.v7.preference.{EditTextPreference, R}
import android.util.AttributeSet
import com.waz.service.ZMessaging
import com.waz.utils.events.Signal
import com.waz.zclient.{Injectable, WireContext}
import com.waz.ZLog.ImplicitTag._
import com.waz.utils.returning

import scala.concurrent.duration._
import scala.util.Try

/**
  * Custom preference to view and modify ping interval in PingIntervalService.
  *
  * This interval is no longer stored in SharedPreferences, so we need to modify loading and persisting logic.
  */
class PingIntervalPref(context: Context, attrs: AttributeSet)
    extends EditTextPreference(context, attrs) with Injectable {

  setDialogLayoutResource(android.support.v7.preference.R.layout.preference_dialog_edittext)

  lazy implicit val wContext = WireContext(context)
  lazy implicit val injector = wContext.injector

  val pingIntervalService = inject[Signal[ZMessaging]].map(_.pingInterval)
  val interval = returning(pingIntervalService.flatMap(_.interval))(_.disableAutowiring())

  override def getPersistedString(default: String) = interval.currentValue.fold("0")(_.toMillis.toString)

  override def persistString(value: String): Boolean = {
    if (shouldPersist()) {
      Try(value.toLong.millis) foreach { interval =>
        pingIntervalService.currentValue foreach { _.setPingInterval(interval) }
      }
      return true
    }
    false
  }
}
