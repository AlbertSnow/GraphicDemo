cmake_minimum_required(VERSION 3.4.1)


project(NativeException)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -static-libstdc++")
set(CMAKE_CXX_STANDARD_REQUIRED TRUE)
set(SOURCE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp")

INCLUDE_DIRECTORIES(${SOURCE_DIR})
LINK_DIRECTORIES(${SOURCE_DIR})

add_library( NativeException
        SHARED
        ${SOURCE_DIR}/com_albertsnow_graphicdemo_jni_exception_TestException.cpp
#        ${SOURCE_DIR}/TestTemplate.cpp
#        ${SOURCE_DIR}/TestInheritance.cpp
#        ${SOURCE_DIR}/TestException.cpp
#        ${SOURCE_DIR}/TestFile.cpp
        )

TARGET_LINK_LIBRARIES( NativeException
        log
        )


add_library(test_jni
        SHARED
        ${SOURCE_DIR}/com_albertsnow_graphicdemo_jni_TestJNI.cpp
        )

TARGET_LINK_LIBRARIES( test_jni
        log
        )


add_library(my_looper
        SHARED
        ${SOURCE_DIR}/loop/MyLooper.cpp
        ${SOURCE_DIR}/com_albertsnow_graphicdemo_looper_MyLooper.cpp
        )
TARGET_LINK_LIBRARIES(my_looper
        log)