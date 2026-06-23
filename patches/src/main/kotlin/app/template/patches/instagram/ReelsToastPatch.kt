package app.template.patches.instagram

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.template.patches.shared.Constants.COMPATIBILITY_INSTAGRAM

private const val EXTENSION_CLASS = "Lapp/template/extension/extension/ReelsToastHelper;"

@Suppress("unused")
val reelsToastPatch = bytecodePatch(
    name = "Reels tab toast",
    description = "Affiche un petit message quand on clique sur l'onglet Reels",
    default = true
) {
    compatibleWith(COMPATIBILITY_INSTAGRAM)

    extendWith("extensions/extension.mpe")

    execute {
        ReelsTabClickFingerprint.method.addInstructions(
            40,
            """
                invoke-static {v3}, $EXTENSION_CLASS->showToast(Landroid/content/Context;)V
            """
        )
    }
}
