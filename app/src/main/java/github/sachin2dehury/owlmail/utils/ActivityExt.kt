package github.sachin2dehury.owlmail.utils

import android.app.AlarmManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.review.ReviewManagerFactory

class SyncService

fun AppCompatActivity.enableSyncService(shouldEnable: Boolean, bundle: PersistableBundle) =
    when (shouldEnable) {
        true -> startSyncJobService(bundle)
        else -> stopSyncJobService()
    }

fun AppCompatActivity.startSyncJobService(bundle: PersistableBundle) {
    val syncJob = JobInfo.Builder(1000, ComponentName(this, SyncService::class.java)).apply {
        setPeriodic(AlarmManager.INTERVAL_FIFTEEN_MINUTES)
        setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        setExtras(bundle)
        setPersisted(true)
    }.build()
    (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).schedule(syncJob)
}

fun AppCompatActivity.stopSyncJobService() =
    (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).cancelAll()

fun AppCompatActivity.inAppReview() {
    val reviewManager = ReviewManagerFactory.create(this)
    val request = reviewManager.requestReviewFlow()
    request.addOnCompleteListener { task ->
        when (task.isSuccessful) {
            true -> {
                val reviewInfo = task.result
                val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                    // Show Message TODO
                }
            }
            else -> {
                // Show Message TODO
            }
        }
    }
}

fun AppCompatActivity.inAppUpdate() = AppUpdateManagerFactory.create(this).apply {
    appUpdateInfo.addOnSuccessListener { doUpdate(this, it) }
}

fun AppCompatActivity.doUpdate(appUpdateManager: AppUpdateManager, appUpdateInfo: AppUpdateInfo) {
    if ((appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE || appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) &&
        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
    ) {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            AppUpdateType.IMMEDIATE,
            this,
            1000
        )
    }
}
