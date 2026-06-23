package app.template.patches.example

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.StringComparisonType
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

object AdLoaderFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf("Ljava/lang/String;", "I", "L"),
    filters = listOf(
        fieldAccess(
            opcode = Opcode.IGET,
            definingClass = "this",
            type = "Ljava/util/Map;"
        ),
        string("showBannerAds"),
        methodCall(
            definingClass = "Ljava/lang/String;",
            name = "equals",
        ),
        opcode(Opcode.MOVE_RESULT, MatchAfterImmediately()),
        literal(1337),
        opcode(Opcode.IF_EQ),
    ),
    custom = { _, classDef ->
        classDef.type == "Lcom/some/app/ads/AdsLoader;"
    }
)
