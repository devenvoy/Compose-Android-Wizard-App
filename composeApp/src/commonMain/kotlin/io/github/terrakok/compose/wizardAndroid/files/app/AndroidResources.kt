package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class ColorResource(): ProjectFile {
    override val path = "app/src/main/res/values/colors.xml"
    override val content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    <color name="colorPrimary">#ff0079ff</color>
    <color name="colorPrimaryDark">#EAEAEA</color>
    <color name="colorAccent">#ff0079ff</color>
    <color name="ripple_foreground">#33FFFFFF</color>
    <color name="red">#ff3a31</color>
    <color name="gray">#4A4A4A</color>
    <color name="red_alpha">#4dff3a31</color>
</resources>
    """.trimIndent()
}

class DimenResource(): ProjectFile {
    override val path = "app/src/main/res/values/dimens.xml"
    override val content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!--<dimen name="dialpad_button_size">68dp</dimen>
    <dimen name="dialpad_button_size_small">50dp</dimen>
    <dimen name="incoming_call_arrow_size">50dp</dimen>
    <dimen name="incoming_call_button_size">72dp</dimen>
    <dimen name="call_notification_button_size">40dp</dimen>
    <dimen name="incoming_call_avatar_size">168dp</dimen>
    <dimen name="end_call_button_margin">34dp</dimen>
    <dimen name="dialpad_bottom_padding">114dp</dimen>
    <dimen name="call_control_max_height">240dp</dimen>
    <dimen name="call_control_button_size">72dp</dimen>
    <dimen name="caller_avatar_max_size">100dp</dimen>
    <dimen name="recyclerview_fab_padding">100dp</dimen>-->
</resources>
    """.trimIndent()
}

class StringResource(info: AndroidProjectInfo): ProjectFile {
    override val path = "app/src/main/res/values/strings.xml"
    override val content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">${info.name}</string>
    <string name="in_app_name">#${info.name}</string>
</resources>
    """.trimIndent()
}

class ThemeResource(info: AndroidProjectInfo): ProjectFile {
    override val path = "app/src/main/res/values/themes.xml"
    override val content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.${info.safeName}" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorPrimary</item>
        <item name="colorOnPrimary">@color/white</item>
    </style>

    <style name="TransparentActivityTheme" parent="Theme.AppCompat.NoActionBar">
        <item name="android:windowBackground">#00000000</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowIsFloating">false</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowDisablePreview">true</item>
        <item name="android:colorBackgroundCacheHint">@null</item>
    </style>
</resources>
    """.trimIndent()
}

class ThemeNightResource(info: AndroidProjectInfo): ProjectFile {
    override val path = "app/src/main/res/values-night/themes.xml"
    override val content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.${info.safeName}" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorPrimary</item>
        <item name="colorOnPrimary">@color/white</item>
    </style>

    <style name="TransparentActivityTheme" parent="Theme.AppCompat.NoActionBar">
        <item name="android:windowBackground">#00000000</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowIsFloating">false</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowDisablePreview">true</item>
        <item name="android:colorBackgroundCacheHint">@null</item>
    </style>
</resources>
    """.trimIndent()
}