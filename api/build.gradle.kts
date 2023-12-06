plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "An abstraction form sending emails"

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koncurrent.later.core)
                api(libs.kollections.interoperable)
                api(libs.identifier.comm) // TODO: Remove this after you have purged rogue email from comm
                api(libs.identifier.brands) // TODO: Remove this from raven-api, to raven-markup-core coz templates do require branding
                api(kotlinx.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.coroutines)
            }
        }
    }
}