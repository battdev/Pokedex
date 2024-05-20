plugins {
    `kotlin-dsl`
}

group = "com.battdev.gradle.plugins.convention"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.spotless)
}

gradlePlugin {
    plugins {
        register("KotlinJvmPlugin") {
            id = "com.battdev.gradle.plugins.jvm.library"
            implementationClass = "KotlinJvmPlugin"
        }
        register("AndroidHiltPlugin") {
            id = "com.battdev.gradle.plugins.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
        register("AndroidLibraryPlugin") {
            id = "com.battdev.gradle.plugins.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("AndroidLibraryUiPlugin") {
            id = "com.battdev.gradle.plugins.android.library.compose"
            implementationClass = "AndroidLibraryUiPlugin"
        }
        register("AndroidApplicationPlugin") {
            id = "com.battdev.gradle.plugins.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
    }
}