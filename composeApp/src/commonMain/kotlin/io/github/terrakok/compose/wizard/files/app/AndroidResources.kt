package io.github.terrakok.compose.wizard.files.app

import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo

class AndroidManifest(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/src/androidMain/AndroidManifest.xml"
    override val content = """
        <?xml version="1.0" encoding="utf-8"?>
        <manifest xmlns:android="http://schemas.android.com/apk/res/android">
        
            <application
                android:name=".AndroidApp"
                android:icon="@android:drawable/ic_menu_compass"
                android:label="${info.name}"
                android:theme="@android:style/Theme.Material.Light.NoActionBar">
                <activity
                    android:name=".AppActivity"
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
