package app.template.patches.instagram

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.string

object ReelsTabClickFingerprint : Fingerprint(
    returnType = "V",
    parameters = listOf("Landroid/view/View;"),
    filters = listOf(
        string("ON_CLICK_HANLDER_INVOKED"),
        string("MainTabControllerImpl.onTabUpdated: ")
    )
)
