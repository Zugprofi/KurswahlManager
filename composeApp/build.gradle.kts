import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("org.apache.poi:poi-ooxml:5.2.5")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation("io.github.smyrgeorge:sqlx4k-mysql:1.0.0")
            //implementation("org.simplekotlinmail:simple-kotlin-mail-core:1.4.0")
            implementation("org.junit.jupiter:junit-jupiter:5.9.3")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.testcontainers:testcontainers:1.19.7")
                implementation("org.testcontainers:mariadb:1.19.7")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            }
        }
    }
}


compose.desktop {
    application {
        mainClass = "hsg.kurswahl.manager.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "hsg.kurswahl.manager"
            packageVersion = "1.0.0"
        }
    }
}