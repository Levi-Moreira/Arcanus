# Arcanus
A nice utility library to help encrypt data within shared prefs.

Sometimes we need to store sensitive information in our device and using plain-text is as good as printing the information to logcat. Arcanus uses the Android keystore to store an encrypted key for your data, that way you can store the encrypted data anywhere and even if someone gets hold of it they won't be able to decrypt the information. This tights the information to the device making it impossible to use it outside the device where they key is stored. 

From the [official](https://developer.android.com/training/articles/keystore) page:

> Android Keystore system protects key material from unauthorized use.

The data encrypted using the keystore cannot be used outside of the Android device where it was encrypted, therefore making the information safe. 

# Download

To add this to your project simply add Arcanus as a dependency:

```gradle
implementation 'com.levimoreira.arcanus:arcanus:0.1.0'
````

# Usage

Using Arcanus is very simple. Initialize the KeyStore in your Application class:

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Arcanus.init(this, "Alias", "Company", "Organization")
    }
}
```

Once the keystore is initialized you can store and retrieve data from a shared prefs instance inside Arcanus:

```kotlin
val sensitiveData = "SensitiveData"
Arcanus.addString(this, SECRET_KEY, sensitiveData)
val retrievedData = Arcanus.getString(this, SECRET_KEY)
```
 
 Arcanus will encrypt your data and save it in a shared prefs file. 
 Don't worry, even if someone gets hold of that file they won't be able to decipher it without the key.
 
 # License
 
     Copyright (c) 2019 Levi Moreira
 
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
 
