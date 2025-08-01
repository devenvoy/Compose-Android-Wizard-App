# Keep main function
#-keep class MainKt {
#    public static void main(java.lang.String[]);
#}

# Compose
-keep class androidx.compose.** { *; }
-keepnames class androidx.compose.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-keepnames class kotlinx.coroutines.** { *; }

# Serialization
-keep class kotlinx.serialization.** { *; }
-keepnames class kotlinx.serialization.** { *; }
-keepclassmembers class * {
#    @kotlinx.serialization.Serializable *;
}

# Keep all classes with @Composable
-keepclasseswithmembers class * {
#    @androidx.compose.runtime.Composable *;
}

# Avoid stripping reflection-targeted classes
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod

# Disable obfuscation/optimization for now (you can turn back on later)
-dontobfuscate
-dontoptimize
-dontpreverify

# Optional: ignore known harmless warnings
-ignorewarnings
