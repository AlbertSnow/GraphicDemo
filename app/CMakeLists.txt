cmake_minimum_required(VERSION 3.4.1)


project(NativeException)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -static-libstdc++")
set(CMAKE_CXX_STANDARD_REQUIRED TRUE)

set(SOURCE_DIR /Users/apple/WorkSpace/GraphicDemo/app/src/main/cpp)

INCLUDE_DIRECTORIES(${SOURCE_DIR})
LINK_DIRECTORIES(${SOURCE_DIR})

add_library( NativeException
        SHARED
        ${SOURCE_DIR}/com_albertsnow_graphicdemo_jni_TestException.cpp
        )

TARGET_LINK_LIBRARIES( NativeException
        log
        )