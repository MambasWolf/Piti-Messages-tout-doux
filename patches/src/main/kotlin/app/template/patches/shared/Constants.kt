package app.template.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_EXAMPLE = Compatibility(
        name = "XYZ app",
        packageName = "com.example.app",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xFF0045,
        targets = listOf(
            AppTarget(version = "2.0.0"),
            AppTarget(version = "1.0.2")
        )
    )

    val COMPATIBILITY_INSTAGRAM = Compatibility(
        name = "Instagram",
        packageName = "com.instagram.android",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xE1306C,
        targets = listOf(
            AppTarget(version = "430.0.0.53.80")
        )
    )
}
