pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyBookApplication"
include(":app")
include(":core:data")
include(":core:database")
include(":core:design")
include(":core:network")
include(":feature:bookdetail")
include(":feature:booklist")
