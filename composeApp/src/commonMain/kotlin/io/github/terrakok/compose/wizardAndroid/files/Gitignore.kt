package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizard.ProjectFile

class Gitignore : ProjectFile {
    override val path = ".gitignore"
    override val content = """
*.iml
.gradle
/local.properties
/.idea/caches
/.idea/libraries
/.idea/modules.xml
/.idea/workspace.xml
/.idea/navEditor.xml
/.idea/assetWizardSettings.xml
.DS_Store
/build
/captures
.externalNativeBuild
.cxx
local.properties
"""
}