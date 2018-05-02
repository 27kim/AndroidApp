// IPlayService.aidl
package com.example.a27aidl_module;

// Declare any non-default types here with import statements

interface IPlayService {
  int currentPosition();
  int getMaxDuration();
  void start();
  void stop();
  int getMediaStatus();
}
