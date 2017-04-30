# RxKeyboard
RxJava2 bindings for android keyboard's `InputMethodManager`

[![Build Status](https://img.shields.io/travis/g4s8/RxKeyboard.svg?style=flat-square)](https://travis-ci.org/g4s8/RxKeyboard)
[![License](https://img.shields.io/github/license/g4s8/RxKeyboard.svg?style=flat-square)](https://github.com/g4s8/RxKeyboard/blob/master/LICENSE)
[![Bintray](https://img.shields.io/bintray/v/g4s8/maven-android/com.g4s8.rxkeyboard.svg?style=flat-square)](https://bintray.com/g4s8/maven-android/com.g4s8.rxkeyboard/_latestVersion)

## Dependencies
depends on RxJava2 '2.0.7' version:
```gradle
dependencies {
  "io.reactivex.rxjava2:rxjava:2.0.7"
}
```
See [latest release](https://github.com/g4s8/RxKeyboard/releases/latest) for build dependency

## Usage
### show android keyboard async
```java
final RxSoftKeyboard keyboard = new RxAndroidSoftKeyboard(context);
keyboard.show(editTex, ShowFlags.NONE).subscribe(() -> callback());
```
### hide android keyboard async
```java
final RxSoftKeyboard keyboard = new RxAndroidSoftKeyboard(context);
keyboard.hide(view.getWindowToken(), ShowFlags.NONE).subscribe(() -> callback());
```
