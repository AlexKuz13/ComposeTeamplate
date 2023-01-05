object App {

    const val minSdk = 23
    const val targetSdk = 33
    const val compileSdk = 33
    const val packageName = "com.alexkuz.teamplate"
    private const val title = "teamplate"

    const val code = 1

    val version
        get() = "$major.$minor.$patch"

    val name
        get() = "$title-$major.$minor.$patch"

    private var major = 1
    private var minor = 0
    private var patch = 0
}