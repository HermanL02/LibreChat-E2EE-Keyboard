pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven ("https://maven.aliyun.com/repository/public")//jcenter
        maven ("https://maven.aliyun.com/repository/gradle-plugin")//gradle-plugin
        maven ("https://maven.aliyun.com/repository/central")//central
        maven ("https://maven.aliyun.com/repository/google")//google
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ("https://maven.aliyun.com/repository/public")//jcenter
        maven ("https://maven.aliyun.com/repository/gradle-plugin")//gradle-plugin
        maven ("https://maven.aliyun.com/repository/central")//central
        maven ("https://maven.aliyun.com/repository/google")//google
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "fcitx5-android"

include(":lib:common")
include(":lib:fcitx5")
include(":lib:fcitx5-lua")
include(":lib:libime")
include(":lib:fcitx5-chinese-addons")
include(":codegen")
include(":app")
include(":lib:plugin-base")
include(":plugin:anthy")
include(":plugin:clipboard-filter")
include(":plugin:unikey")
include(":plugin:rime")
include(":plugin:hangul")
include(":plugin:chewing")
include(":plugin:sayura")
include(":plugin:jyutping")
