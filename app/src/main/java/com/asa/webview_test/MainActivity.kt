package com.asa.webview_test

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.webkit.WebView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    var mydownloadid:Long = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "pdfテスト"

        button.setOnClickListener {
            val url = "https://flex-crm.com/demofeb18jdu/files/document/dummy.pdf"
            val urlSp = url.split("/")
            val pdfName = urlSp[urlSp.size - 1]
            val file = File(Environment.DIRECTORY_DOWNLOADS,pdfName)

            val request = DownloadManager.Request(
                    Uri.parse("https://flex-crm.com/demofeb18jdu/files/document/dummy.pdf"))
                    .setTitle(pdfName + "のダウンロード")
                    .setMimeType("application/pdf")
                    .setDescription("ダウンロード")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setAllowedOverMetered(true)
                    .setDestinationUri(Uri.fromFile(file))
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pdfName)

            //メディアスキャンを許可する(ギャラリーアプリとかで参照可能になる)
            request.allowScanningByMediaScanner()

            //ダウンロード中・ダウンロード完了時にも通知を表示する
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            mydownloadid = dm.enqueue(request)

            val onDownloadComplete = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
                        if (mydownloadid == id) {
                            val query: DownloadManager.Query = DownloadManager.Query()
                            query.setFilterById(id)
                            var cursor = dm.query(query)
                            if (!cursor.moveToFirst()) {
                                return
                            }

                            var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                            var status = cursor.getInt(columnIndex)
                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
                            } else if (status == DownloadManager.STATUS_FAILED) {
                                Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
                        Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            val intentFilter = IntentFilter()
            intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
            registerReceiver(onDownloadComplete,intentFilter)
        }

        button2.setOnClickListener {
            val url = "https://atmiyauni.ac.in/wp-content/uploads/2020/04/AU-Brochure-update-March-2020.pdf"
//            val url = "https://flex-crm.com/demofeb18jdu/files/document/dummy.pdf"
            val urlSp = url.split("/")
            val pdfName = urlSp[urlSp.size - 1]
            val file = File(Environment.DIRECTORY_DOWNLOADS,pdfName)

            val request = DownloadManager.Request(
                    Uri.parse("https://atmiyauni.ac.in/wp-content/uploads/2020/04/AU-Brochure-update-March-2020.pdf"))
//                    Uri.parse("https://flex-crm.com/demofeb18jdu/files/document/dummy.pdf"))
                    .setTitle(pdfName + "のダウンロード")
                    .setMimeType("application/pdf")
                    .setDescription("ダウンロード")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setAllowedOverMetered(true)
                    .setDestinationUri(Uri.fromFile(file))
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pdfName)

            //メディアスキャンを許可する(ギャラリーアプリとかで参照可能になる)
            request.allowScanningByMediaScanner()

            //ダウンロード中・ダウンロード完了時にも通知を表示する
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            mydownloadid = dm.enqueue(request)

            val onDownloadComplete = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
                        if (mydownloadid == id) {
                            val query: DownloadManager.Query = DownloadManager.Query()
                            query.setFilterById(id)
                            var cursor = dm.query(query)
                            if (!cursor.moveToFirst()) {
                                return
                            }

                            var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                            var status = cursor.getInt(columnIndex)
                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
                            } else if (status == DownloadManager.STATUS_FAILED) {
                                Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
                        Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            val intentFilter = IntentFilter()
            intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
            registerReceiver(onDownloadComplete,intentFilter)
        }

    }
}