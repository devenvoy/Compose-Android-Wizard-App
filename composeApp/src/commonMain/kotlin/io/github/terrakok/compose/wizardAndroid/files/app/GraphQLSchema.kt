package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizard.ProjectFile

class GraphQLSchema : ProjectFile {
    override val path = "app/src/main/graphql/schema.graphqls"
    override val content = """
        type Query {
          hello: String!
        }
    """.trimIndent()
}
