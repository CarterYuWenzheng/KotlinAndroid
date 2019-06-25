//
// Created by 于文政 on 2019/6/3.
//
#include "com_carter_androiddemo_Java2CJNI.h"

JNIEXPORT jstring JNICALL
Java_com_carter_androiddemo_Java2CJNI_java2C
  (JNIEnv *env, jobject instance)
  {
    return (*env)->NewStringUTF(env,"I am From Native C.");
  }
