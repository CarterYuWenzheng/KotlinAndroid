# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

add_library( # Sets the name of the library.
      # 设置so文件名称.
       Java2C

       # Sets the library as a shared library.
       SHARED
       # 设置这个so文件为共享.

       # Provides a relative path to your source file(s).
       # 设置这个so文件为共享.
       src/main/jni/Java2C.c)

find_library( # Sets the name of the path variable.
       log-lib

       # Specifies the name of the NDK library that
       # you want CMake to locate.
       log )

target_link_libraries( # Specifies the target library.
            # 制定目标库.
            Java2C

            # Links the target library to the log library
            # included in the NDK.
            ${log-lib} )