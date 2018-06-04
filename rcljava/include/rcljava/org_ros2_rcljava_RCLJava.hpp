// Copyright 2016-2017 Esteve Fernandez <esteve@apache.org>
// Copyright 2016-2018 Mickael Gaillard <mick.gaillard@gmail.com>
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/* DO NOT EDIT THIS FILE - it is machine generated */

/* Header for class org_ros2_rcljava_RCLJava */

#ifndef RCLJAVA__ORG_ROS2_RCLJAVA_RCLJAVA_HPP_
#define RCLJAVA__ORG_ROS2_RCLJAVA_RCLJAVA_HPP_

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeRCLJavaInit
 * Signature: ([Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeRCLJavaInit
  (JNIEnv *, jclass, jobjectArray);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeGetRMWIdentifier
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeGetRMWIdentifier
  (JNIEnv *, jclass);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeOk
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeOk
  (JNIEnv *, jclass);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeShutdown
 * Signature: ()V
 */
JNIEXPORT void JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeShutdown
  (JNIEnv *, jclass);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeGetNodeNames
 * Signature: ()Ljava/util/List;
 */
JNIEXPORT jobject JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeGetNodeNames
  (JNIEnv *, jclass);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeConvertQoSProfileToHandle
 * Signature: (IIIIZ)J
 */
JNIEXPORT jlong JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeConvertQoSProfileToHandle
  (JNIEnv *, jclass, jint, jint, jint, jint, jboolean);

/*
 * Class:     org_ros2_rcljava_RCLJava
 * Method:    nativeDisposeQoSProfile
 * Signature: (J)V
 */
JNIEXPORT void JNICALL
  Java_org_ros2_rcljava_RCLJava_nativeDisposeQoSProfile
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif  // RCLJAVA__ORG_ROS2_RCLJAVA_RCLJAVA_HPP_