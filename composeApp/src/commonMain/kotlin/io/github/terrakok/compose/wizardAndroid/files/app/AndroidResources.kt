package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class AndroidManifest(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/src/main/AndroidManifest.xml"
    override val content = """
        <?xml version="1.0" encoding="utf-8"?>
        <manifest xmlns:android="http://schemas.android.com/apk/res/android">
        
            <application
                android:name=".AndroidApp"
                android:icon="@android:drawable/ic_menu_compass"
                android:label="${info.name}"
                android:theme="@style/Theme.AppCompat.DayNight.NoActionBar">
                <activity
                    android:name=".MainActivity"
                    android:configChanges="orientation|screenSize|screenLayout|keyboardHidden"
                    android:launchMode="singleInstance"
                    android:windowSoftInputMode="adjustResize"
                    android:exported="true">
                    <intent-filter>
                        <action android:name="android.intent.action.MAIN" />
                        <category android:name="android.intent.category.LAUNCHER" />
                    </intent-filter>
                </activity>
            </application>
        
        </manifest>
    """.trimIndent()
}

class DrawableResource(): ProjectFile{
    override val path = ""
    override val content = """
        
    """.trimIndent()
}