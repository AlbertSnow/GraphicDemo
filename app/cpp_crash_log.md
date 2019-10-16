返回值String，但实际 native没有return对象
     
2019-10-16 17:55:03.604 1163-1163/? E/wificond: vivo add tx_good 867083 tx_bad 2684 rx_good 2165256 tx_retry 234626
2019-10-16 17:55:05.167 17033-17033/com.albertsnow.graphicdemo A/libc: Fatal signal 5 (SIGTRAP), code 1 in tid 17033 (now.graphicdemo), pid 17033 (now.graphicdemo)
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: Build fingerprint: 'vivo/PD1709/PD1709:8.1.0/OPM1.171019.011/compil08122107:user/release-keys'
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: Revision: '0'
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: ABI: 'arm64'
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: pid: 17033, tid: 17033, name: now.graphicdemo  >>> com.albertsnow.graphicdemo <<<
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG: signal 5 (SIGTRAP), code 1 (TRAP_BRKPT), fault addr 0x7e63b885cc
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x0   0000007e7f6d4880  x1   0000007fcb23a2b4  x2   0000007fcb23a2b8  x3   0000007e7f6c3e00
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x4   0000007fcb23a710  x5   0000007e64ee5623  x6   0000000000000002  x7   7470656378452074
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x8   920052d2a6e75600  x9   920052d2a6e75600  x10  0000000000430000  x11  0000000000000058
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x12  0000000000000008  x13  0000007effc166c0  x14  0000007f0524fa40  x15  0000007effc3c898
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x16  0000007e646002c0  x17  0000007e63b885bc  x18  0000000000000022  x19  0000007e7f6c3e00
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x20  0000007e7f0ba320  x21  0000007e7f6c3e00  x22  0000007fcb23a55c  x23  0000007e64ee5623
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x24  0000000000000008  x25  0000007f0524fa40  x26  0000007e7f6c3ea0  x27  0000000000000002
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     x28  0000000000000000  x29  0000007fcb23a388  x30  0000007e7085ed0c
2019-10-16 17:55:05.256 17109-17109/? A/DEBUG:     sp   0000007fcb23a280  pc   0000007e63b885cc  pstate 0000000060000000
2019-10-16 17:55:05.258 17109-17109/? A/DEBUG: backtrace:
2019-10-16 17:55:05.258 17109-17109/? A/DEBUG:     #00 pc 00000000000005cc  /data/app/com.albertsnow.graphicdemo-DNClcC8h9W8ZnRhLgwTW4A==/lib/arm64/libNativeException.so (Java_com_albertsnow_graphicdemo_jni_exception_TestException_testException+16)
2019-10-16 17:55:05.258 17109-17109/? A/DEBUG:     #01 pc 0000000000011d08  /data/app/com.albertsnow.graphicdemo-DNClcC8h9W8ZnRhLgwTW4A==/oat/arm64/base.odex (offset 0x11000)
2019-10-16 17:55:05.464 1142-1261/? E/ThermalEngine: vivo_thermal_parameter_config :VivoThermal:read type:0  buf: 
     


返回值String，在native生命中写成了NULL
2019-10-16 17:14:49.167 15404-15404/com.albertsnow.graphicdemo A/libc: Fatal signal 6 (SIGABRT), code -6 in tid 15404 (now.graphicdemo), pid 15404 (now.graphicdemo)
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: Build fingerprint: 'vivo/PD1709/PD1709:8.1.0/OPM1.171019.011/compil08122107:user/release-keys'
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: Revision: '0'
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: ABI: 'arm64'
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: pid: 15404, tid: 15404, name: now.graphicdemo  >>> com.albertsnow.graphicdemo <<<
2019-10-16 17:14:49.235 15471-15471/? A/DEBUG: signal 6 (SIGABRT), code -6 (SI_TKILL), fault addr --------
2019-10-16 17:14:49.239 15471-15471/? A/DEBUG: Abort message: 'java_vm_ext.cc:534] JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x7e7f6d4880'