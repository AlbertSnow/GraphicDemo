cmake_minimum_required(VERSION 3.4.1)


project(NativeException)

set(SOURCE_DIR /Users/apple/WorkSpace/GraphicDemo/app/src/main/cpp)

INCLUDE_DIRECTORIES(${SOURCE_DIR})
LINK_DIRECTORIES(${SOURCE_DIR})

add_library( NativeException
        SHARED
        ${SOURCE_DIR}/com_albertsnow_graphicdemo_jni_exception_TestException.cpp
        ${SOURCE_DIR}/TestTemplate.cpp
        ${SOURCE_DIR}/TestInheritance.cpp
        ${SOURCE_DIR}/TestException.cpp
        ${SOURCE_DIR}/TestFile.cpp
        )

TARGET_LINK_LIBRARIES( NativeException
        log
        )