# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)



set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -static-libstdc++")

set(CMAKE_CXX_STANDARD_REQUIRED TRUE)

#############

# Attetion please
# My custom variable, you should change to youself path

#############
#set(OpenCV_DIR "/Users/apple/CWorkSpace/OpenCV/opencv")
#
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/include)
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/android_dir)
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/modules)
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/modules/include)
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/build)
#
#LINK_LIBRARIES("/Users/apple/WorkSpace/GraphicDemo/opencvlibrary/libs/arm64-v8a")


#set(OpenCV_DIR "/Users/apple/CWorkSpace/OpenCV/opencv")
#INCLUDE_DIRECTORIES(${OpenCV_DIR}/include)

set(OPENCV_HOME "../opencv4-ndk19/sdk")

LINK_DIRECTORIES(${OPENCV_HOME}/native/libs/arm64-v8a)
INCLUDE_DIRECTORIES(${OPENCV_HOME}/native/jni/include)

add_definitions(-D__ANDROID_API__=21)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             graphic

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/com/albert/opencvlibrary/PictureUtils.cpp
             src/main/cpp/com/albert/opencvlibrary/DebugUtils.cpp
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )



# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                        graphic
                        opencv_java4

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )