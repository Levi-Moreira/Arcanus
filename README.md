# Arcanus
A nice utility library to help encrypt data within shared prefs.

Sometimes we need to store sensitive information in our device and using plain-text is as good as printing the information to logcat. Arcanus uses the Android keystore to store an encrypted key for your data, that way you can store the encrypted data anywhere and even if someone gets hold of it they won't be able to decrypt the information. This tights the information to the device making it impossible to use it outside the device where they key is stored. 

From the [official](https://developer.android.com/training/articles/keystore) page:

> Android Keystore system protects key material from unauthorized use.

The data encrypted using the keystore cannot be used outside of the Android device where it was encrypted, therefore making the information safe. To use Arcanus for now you'd need to clone this repository.

Initialize the KeyStore in your Application class:

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
val senstiveData = "SensitiveData"
Arcanus.addString(this, SECRET_KEY, senstiveData)
val retrievedData = Arcanus.getString(this, SECRET_KEY)
```
 
 Arcanus will encrypt your data and save it in a shared prefs file. 
 Don't worry, even if someone gets hold of that file they won't be able to decipher it without the key.