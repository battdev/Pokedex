import com.battdev.gradle.plugins.configureAndroidLibraryWithCompose
import com.battdev.gradle.plugins.configureKotlinOptions
import com.battdev.gradle.plugins.configureSpotless
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin class for configuring an Android library project with Jetpack Compose.
 *
 * This plugin extends the functionality of the Android Gradle Plugin to set up and configure
 * a project for Android library development with Jetpack Compose UI framework.
 *
 * After applying the plugin, it configures the project for Android library development
 * with Jetpack Compose.
 */
class AndroidLibraryUiPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply {
            plugin("com.android.library")
            plugin("kotlin-android")
        }

        configureAndroidLibraryWithCompose()
        configureKotlinOptions()
        configureSpotless()
    }
}
