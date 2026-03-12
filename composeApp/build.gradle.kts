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
            implementation("org.odftoolkit:odfdom-java:0.13.0")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation("org.junit.jupiter:junit-jupiter:5.9.3")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("org.optaplanner:optaplanner-core:9.44.0.Final")
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.testcontainers:testcontainers:1.19.7")
                implementation("org.testcontainers:mariadb:1.19.7")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                implementation("org.optaplanner:optaplanner-core:9.44.0.Final")
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