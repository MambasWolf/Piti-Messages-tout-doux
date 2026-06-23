group = "app.mambaswolf.patches"

patches {
    about {
        name = "Piti Messages Tout Doux"
        description = "Affiche un message quand on clique sur l'onglet Reels"
        source = "https://github.com/MambasWolf/Piti-Messages-tout-doux"
        author = "MambasWolf"
        contact = "na"
        website = "https://github.com/MambasWolf/Piti-Messages-tout-doux"
        license = "GPLv3"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

val patchListGeneratorClasspath: Configuration by configurations.creating

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"
        dependsOn(build)
        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    publish {
        dependsOn("generatePatchesList")
    }
}
