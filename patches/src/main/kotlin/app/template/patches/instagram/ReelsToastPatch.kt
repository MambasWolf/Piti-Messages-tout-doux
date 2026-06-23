package app.template.patches.instagram

import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.fingerprint.Fingerprint
import app.morphe.patcher.compatibility.Compatibility
import app.morphe.patcher.compatibility.AppTarget

val INSTAGRAM_COMPATIBILITY = Compatibility(
    name = "Instagram",
    packageName = "com.instagram.android",
    targets = listOf(
        AppTarget(version = "430.0.0.53.80")
    )
)

val reelsToastPatch = bytecodePatch(
    name = "Reels tab toast",
    description = "Affiche un petit message quand on clique sur l'onglet Reels",
    default = true
) {
    compatibleWith(INSTAGRAM_COMPATIBILITY)

    extendWith("reels-toast.mpe")

    execute {
        val reelsTabClickFingerprint = Fingerprint(
            returnType = "V",
            parameters = listOf("Landroid/view/View;"),
            strings = listOf(
                "ON_CLICK_HANLDER_INVOKED",
                "MainTabControllerImpl.onTabUpdated: "
            )
        )

        // Injection à l'index 40 : juste avant le dispatch sur l'onglet Reels
        // v3 = InstagramMainActivity (Context), disponible depuis l'instruction [19]
        reelsTabClickFingerprint.method.addInstructions(
            40,
            """
                invoke-static {v3}, Lapp/template/extension/ReelsToastHelper;->showToast(Landroid/content/Context;)V
            """
        )
    }
}
