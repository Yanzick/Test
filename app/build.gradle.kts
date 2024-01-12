plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test"
        minSdk = 30
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.1.0")
    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.0")
    {
        exclude(module= "support-v4")
    }
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")
    implementation("com.google.android.gms:play-services-nearby:18.0.0")
    implementation("androidx.work:work-runtime-ktx:2.7.0")

}
