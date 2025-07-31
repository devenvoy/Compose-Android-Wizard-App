package io.github.terrakok.compose.wizard.files.app

import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo

class IndexHtml(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/src/jsMain/resources/index.html"
    override val content = """
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${info.name}</title>
    <script src="skiko.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link href='styles.css' rel='stylesheet' type='text/css'>
</head>
<body>
<script src="composeApp.js"></script>
</body>
</html>
    """.trimIndent()
}

class IndexCss() : ProjectFile {
    override val path = "composeApp/src/jsMain/resources/styles.css"
    override val content = """
html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    overflow: hidden;
}
    """.trimIndent()
}