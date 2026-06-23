package app.template.patches.instagram

import app.morphe.patcher.extensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.fingerprint.fingerprint
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint ciblant LX/00ZT -> onClick dans classes2.dex (Instagram 430.x)
 *
 * Identifié par deux strings présentes uniquement dans cette méthode :
 *  - "ON_CLICK_HANLDER_INVOKED"  (typo intentionnelle d'Instagram)
 *  - "MainTabControllerImpl.onTabUpdated: "
 */
val reelsTabClickFingerprint = fingerprint {
    returns("V")
    parameters("Landroid/view/View;")
    strings(
        "ON_CLICK_HANLDER_INVOKED",
        "MainTabControllerImpl.onTabUpdated: "
    )
    opcodes(
        Opcode.CONST,
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.MOVE_OBJECT_FROM16,
        Opcode.IGET_OBJECT
    )
}

/**
 * Patch : affiche un message Toast aléatoire quand l'utilisateur
 * clique sur l'onglet Reels dans la barre de navigation d'Instagram.
 *
 * Point d'injection : index 40 dans onClick de LX/00ZT
 * (juste avant sget-object v0, LX/03li;->A0A = constante onglet Reels)
 * v3 = InstagramMainActivity (Context), disponible depuis l'instruction [19]
 */
val reelsToastPatch = bytecodePatch(
    name = "Reels tab toast",
    description = "Affiche un petit message sympa quand on clique sur l'onglet Reels"
) {
    execute {
        val method = reelsTabClickFingerprint.method

        method.addInstruction(
            40,
            "invoke-static {v3}, " +
            "Lapp/template/extension/ReelsToastHelper;->showToast(Landroid/content/Context;)V"
        )
    }
}
