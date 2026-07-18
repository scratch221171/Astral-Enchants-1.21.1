import org.slf4j.event.Level

plugins {
    idea
    alias(libs.plugins.neo.moddev)
    alias(libs.plugins.spotless)
}

val modId = "astralenchant"
group = "net.scratch221171.astralenchant"

version = libs.versions.mod.version
base.archivesName = modId

tasks.named<Wrapper>("wrapper").configure {
    distributionType = Wrapper.DistributionType.BIN
}

repositories {
    mavenLocal()

    maven(url = "https://api.modrinth.com/maven") {
        content { includeGroup("maven.modrinth") }
    }
    maven(url = "https://cursemaven.com") {
        content { includeGroup("curse.maven") }
    }
    maven(url = "https://maven.parchmentmc.org")

    maven(url = "https://maven.blamejared.com/") // Patchouli, Ars, JEI
    maven(url = "https://maven.fabricmc.net")
    maven(url = "https://maven.shadowsoffire.dev/releases") // Apotheosis
    maven(url = "https://maven.su5ed.dev/releases")
    maven(url = "https://maven.theillusivec4.top/") // Curios
    maven(url = "https://maven.wispforest.io/releases") //  Accessories
    maven(url = "https://modmaven.dev/") // JEI, Jade
    mavenCentral()
}

val datagenModule: SourceSet = sourceSets.create("datagen")

sourceSets {
    main {
        resources {
            srcDir("src/generated/resources")
        }
    }
    getByName("datagen") {
        val main: SourceSet by main
        compileClasspath += main.compileClasspath
        compileClasspath += main.output
        runtimeClasspath += main.runtimeClasspath
        runtimeClasspath += main.output
    }
}

// Sets up a dependency configuration called 'localRuntime'.
// This configuration should be used instead of 'runtimeOnly' to declare
// a dependency that will be present for runtime testing but that is
// "optional", meaning it will not be pulled by dependents of this mod.
configurations.apply {
    runtimeClasspath.get().extendsFrom(create("localRuntime"))

    named("datagenCompileClasspath") {
        extendsFrom(compileClasspath.get())
    }
}

neoForge {
    // Specify the version of NeoForge to use.
    version = libs.versions.neo.version.get()

    parchment {
        mappingsVersion = libs.versions.parchment.map.get()
        minecraftVersion = libs.versions.parchment.mc.get()
    }

    // This line is optional. Access Transformers are automatically detected
    // accessTransformers = project.files("src/main/resources/META-INF/accesstransformer.cfg")

    // Default run configurations.
    // These can be tweaked, removed, or duplicated as needed.
    runs {
        create("client").apply {
            client()
            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
            jvmArgument("-Dmixin.debug.export=true")
        }

        create("server").apply {
            server()
            gameDirectory = file("run-server")
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        // This run config launches GameTestServer and runs all registered gametests, then exits.
        // By default, the server will crash when no gametests are provided.
        // The gametest system is also enabled by default for other run configs under the /test command.
        create("gameTestServer").apply {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("data").apply {
            data()
            sourceSet = datagenModule

            // example of overriding the workingDirectory set in configureEach above, uncomment if you want to use it
            gameDirectory = rootProject.file("run-data")

            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            programArguments.addAll(
                "--mod",
                modId,
                "--all",
                "--output",
                file("src/generated/resources/").absolutePath,
                "--existing",
                file("src/main/resources").absolutePath,
            )
        }

        // applies to all the run configs above
        configureEach {
            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            logLevel = Level.DEBUG
        }
    }

    mods {
        // define mod <-> source bindings
        // these are used to tell the game which sources are for which mod
        // multi mod projects should define one per mod
        create(modId) {
            sourceSet(sourceSets.main.get())
            sourceSet(datagenModule)
        }
    }
}

dependencies {
    implementation(libs.bundles.mods.impl)
    compileOnly(libs.bundles.mods.compile)
    runtimeOnly(libs.bundles.mods.runtime)
}

// This block of code expands all declared replace properties in the specified resource targets.
// A missing property will result in an error. Properties are expanded using ${} Groovy notation.
val generateModMetadata: TaskProvider<ProcessResources> =
    tasks.register("generateModMetadata", ProcessResources::class.java) {
        val mcVersion: String = libs.versions.minecraft.get()
        val neoVersion: String = libs.versions.neo.version.get()
        val replaceProperties: Map<String, String> = mapOf(
            "minecraft_version" to mcVersion,
            "minecraft_version_range" to "[$mcVersion]",
            "neo_version" to neoVersion,
            "neo_version_range" to "[$neoVersion,)",
            "loader_version_range" to "[1,)",
            "mod_id" to modId,
            "mod_name" to "Astral Enchant",
            "mod_license" to "MIT",
            "mod_version" to libs.versions.mod.version.get(),
            "mod_authors" to "scratch221171",
            "mod_description" to "Adds several unique enchantments",
            "accessories_version_range" to "[1.1.0-beta,)",
            "l2hostility_version_range" to "[3.0.16,)",
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}
// Include the output of "generateModMetadata" as an input directory for the build
// this works with both building through Gradle and the IDE.
sourceSets.main
    .get()
    .resources
    .srcDir(generateModMetadata)
// To avoid having to run "generateModMetadata" manually, make it run on every project reload
neoForge.ideSyncTask(generateModMetadata)

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        exclude("**/.cache/**")
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

spotless {
    java {
        palantirJavaFormat("2.90.0").formatJavadoc(true)
        endWithNewline()
        formatAnnotations()
        importOrder()
        removeUnusedImports()
    }
}
