plugins {
    id("persistence-convention")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("org.alexcawl.template")
        }
    }
}