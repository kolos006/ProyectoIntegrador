// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Redirigir la carpeta 'build' fuera de OneDrive para evitar bloqueos de archivos en Windows
val externalBuildDir = file("C:/tmp/PrestaKey_build")
allprojects {
    layout.buildDirectory.set(externalBuildDir.resolve(project.name))
}
