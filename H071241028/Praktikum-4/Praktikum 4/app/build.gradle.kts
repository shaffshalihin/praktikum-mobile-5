plugins {
    alias(libs.plugins.android.application)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.praktikum_3"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.praktikum_3"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.register("fixResNames") {
    doLast {
        val dir = File("D:/Praktikum3/app/src/main/res/drawable")
        val map = mapOf(
            "_1984.jpeg" to "book_1984.jpeg",
            "AnimalFarm.jpeg" to "animal_farm.jpeg",
            "BraveNewWorld.jpeg" to "brave_new_world.jpeg",
            "CrimeandPunishment.jpeg" to "crime_and_punishment.jpeg",
            "HarryPotter.jpeg" to "harry_potter.jpeg",
            "PrideandPrejudice.jpeg" to "pride_and_prejudice.jpeg",
            "TheAlchemist.jpeg" to "the_alchemist.jpeg",
            "TheCatcherintheRye.jpeg" to "the_catcher_in_the_rye.jpeg",
            "TheDaVinciCode.jpeg" to "the_da_vinci_code.jpeg",
            "TheGreatGatsby.jpeg" to "the_great_gatsby.jpeg",
            "TheHobbit.jpeg" to "the_hobbit.jpeg",
            "TheLittlePrince.jpeg" to "the_little_prince.jpeg",
            "TheLordoftheRings.jpeg" to "the_lord_of_the_rings.jpeg",
            "ThinkingFastandSlow.jpeg" to "thinking_fast_and_slow.jpeg",
            "ToKillaMockingbird.jpg" to "to_kill_a_mockingbird.jpg"
        )
        map.forEach { (old, new) ->
            val oldFile = File(dir, old)
            val newFile = File(dir, new)
            if (oldFile.exists()) {
                if (newFile.exists() && oldFile.absolutePath != newFile.absolutePath) newFile.delete()
                if (oldFile.renameTo(newFile)) {
                    println("Renamed $old to $new")
                } else {
                    println("Failed to rename $old")
                }
            }
        }
    }
}
