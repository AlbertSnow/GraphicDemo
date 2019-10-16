
C

```
10-16 15:30:03.591  2509  2509 F libc    : Fatal signal 6 (SIGABRT), code -6 in tid 2509 (now.graphicdemo), pid 2509 (now.graphicdemo)
10-16 15:30:03.677  2595  2595 F DEBUG   : *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
10-16 15:30:03.677  2595  2595 F DEBUG   : Build fingerprint: 'vivo/PD1709/PD1709:8.1.0/OPM1.171019.011/compil08122107:user/release-keys'
10-16 15:30:03.677  2595  2595 F DEBUG   : Revision: '0'
10-16 15:30:03.677  2595  2595 F DEBUG   : ABI: 'arm64'
10-16 15:30:03.677  2595  2595 F DEBUG   : pid: 2509, tid: 2509, name: now.graphicdemo  >>> com.albertsnow.graphicdemo <<<
10-16 15:30:03.677  2595  2595 F DEBUG   : signal 6 (SIGABRT), code -6 (SI_TKILL), fault addr --------
10-16 15:30:03.679  2595  2595 F DEBUG   : Abort message: 'java_vm_ext.cc:534] JNI DETECTED ERROR IN APPLICATION: can't call void com.albertsnow.graphicdemo.jni.exception.TestException.makeException() on instance of java.lang.Class<com.albertsnow.graphicdemo.jni.exception.TestException>'
10-16 15:30:03.679  2595  2595 F DEBUG   :     x0   0000000000000000  x1   00000000000009cd  x2   0000000000000006  x3   0000000000000008
10-16 15:30:03.679  2595  2595 F DEBUG   :     x4   0000000003ca0fc8  x5   0000000003ca0fc8  x6   0000000003ca0fc8  x7   0000000003ca1108
10-16 15:30:03.679  2595  2595 F DEBUG   :     x8   0000000000000083  x9   0000000010000000  x10  0000007fcb239680  x11  920052d2a6e75600
10-16 15:30:03.679  2595  2595 F DEBUG   :     x12  920052d2a6e75600  x13  0000000000000020  x14  ffffffffffffffdf  x15  0000007effc3c898
10-16 15:30:03.680  2595  2595 F DEBUG   :     x16  00000056bc0dafa8  x17  0000007effbbf37c  x18  0000000000000037  x19  00000000000009cd
10-16 15:30:03.680  2595  2595 F DEBUG   :     x20  00000000000009cd  x21  0000000000000083  x22  0000000000000058  x23  0000007fcb239719
10-16 15:30:03.680  2595  2595 F DEBUG   :     x24  0000000000000011  x25  0000007e7ec58530  x26  0000000000000d06  x27  0000007fcb2397d0
10-16 15:30:03.680  2595  2595 F DEBUG   :     x28  0000000000000043  x29  0000007fcb2396c0  x30  0000007effb68700
10-16 15:30:03.680  2595  2595 F DEBUG   :     sp   0000007fcb239680  pc   0000007effb68728  pstate 0000000060000000
10-16 15:30:03.686  2595  2595 F DEBUG   :
10-16 15:30:03.686  2595  2595 F DEBUG   : backtrace:
10-16 15:30:03.686  2595  2595 F DEBUG   :     #00 pc 000000000001e728  /system/lib64/libc.so (abort+120)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #01 pc 00000000004732cc  /system/lib64/libart.so (art::Runtime::Abort(char const*)+552)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #02 pc 000000000056b804  /system/lib64/libart.so (android::base::LogMessage::~LogMessage()+852)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #03 pc 00000000002fd250  /system/lib64/libart.so (art::JavaVMExt::JniAbort(char const*, char const*)+1700)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #04 pc 00000000002fd3b4  /system/lib64/libart.so (art::JavaVMExt::JniAbortV(char const*, char const*, std::__va_list)+116)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #05 pc 000000000010e950  /system/lib64/libart.so (art::ScopedCheck::AbortF(char const*, ...)+148)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #06 pc 00000000001125f8  /system/lib64/libart.so (art::ScopedCheck::CheckMethodAndSig(art::ScopedObjectAccess&, _jobject*, _jclass*, _jmethodID*, art::Primitive::Type, art::InvokeType)+1928)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #07 pc 0000000000110ca4  /system/lib64/libart.so (art::CheckJNI::CallMethodV(char const*, _JNIEnv*, _jobject*, _jclass*, _jmethodID*, std::__va_list, art::Primitive::Type, art::InvokeType)+696)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #08 pc 00000000000ff73c  /system/lib64/libart.so (art::CheckJNI::CallVoidMethodV(_JNIEnv*, _jobject*, _jmethodID*, std::__va_list)+92)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #09 pc 00000000000009f8  /data/app/com.albertsnow.graphicdemo-KO_lXd3enYMaQ6R68HZ8Iw==/lib/arm64/libNativeException.so (_JNIEnv::CallVoidMethod(_jobject*, _jmethodID*, ...)+208)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #10 pc 0000000000000868  /data/app/com.albertsnow.graphicdemo-KO_lXd3enYMaQ6R68HZ8Iw==/lib/arm64/libNativeException.so (Java_com_albertsnow_graphicdemo_jni_exception_TestException_testException+172)
10-16 15:30:03.686  2595  2595 F DEBUG   :     #11 pc 0000000000011d08  /data/app/com.albertsnow.graphicdemo-KO_lXd3enYMaQ6R68HZ8Iw==/oat/arm64/base.odex (offset 0x11000)


```