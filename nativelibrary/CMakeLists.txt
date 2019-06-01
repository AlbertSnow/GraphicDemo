# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)


project(TestNative)

set(TestNative_VERSION_MAJOR 1)
set(TestNative_VERSION_MINOR 0)

set(PROJECT_SOURCE_DIR src/main/cpp)
set(PROJECT_SOURCE_DIR src/main/gen)

# configure a header file to pass some of the CMake settings
# to the source code
configure_file(
        "${PROJECT_SOURCE_DIR}/Test.h.in"
        "${PROJECT_BINARY_DIR}/Test.h"
)

# add the binary tree to the search path for include files
# so that we will find TutorialConfig.h
include_directories("${PROJECT_BINARY_DIR}")

# add the executable
add_executable(TestNative Test.cpp)