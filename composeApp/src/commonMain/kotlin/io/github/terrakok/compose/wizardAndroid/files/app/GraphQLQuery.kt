package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizard.ProjectFile

class GraphQLQuery : ProjectFile {
    override val path = "app/src/graphql/HelloQuery.graphql"
    override val content = """
        query HelloQuery {
          hello
        }
    """.trimIndent()
}
