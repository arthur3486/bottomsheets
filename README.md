# BottomSheets

> ***Android library designed to enrich your application with the beautiful stutter-free Material Design Bottom Sheets***

BottomSheets will help you make your application more appealing to your end user with its sleek stutter-free implementation of the Bottom Sheets.

[ ![Download](https://api.bintray.com/packages/arthurimsacc/maven/bottomsheets-core/images/download.svg) ](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)
![](https://img.shields.io/badge/API-18%2B-green.svg?style=flat)
![](https://travis-ci.org/arthur3486/bottomsheets.svg?branch=master)

## Contents

* [Demo](#demo-youtube)
* [Getting Started](#getting-started)
* [Public APIs](#)//TODO <--- ?
* [Basic Custom Implementation](#)//TODO <---
* [Action Picker Implementation](#)//TODO <---
* [Custom Action Picker Implementation](#)//TODO <---
* [Advanced Use](#advanced-use)
* [Contribution](#contribution)
* [Hall of Fame](#hall-of-fame)
* [License](#license)

## Demo (YouTube)

[![YouTube Video]( /*TODO Preview Image to be place here */ )](https://youtu.be/hfvWiqZiqUU)
//TODO <--- A few Screenshots ( + GIFs) to be placed here...
//TODO <--- Bottom Sheets Speed Comparison [stock vs BottomSheets] (animation)

## Getting Started

1. Make sure that you've added the `jcenter()` repository to your top-level `build.gradle` file.

````groovy
buildscript {
    //...
    repositories {
        //...
        jcenter()
    }
    //...
}
````

2. Add the library dependency to your module-level `build.gradle` file. 
> ***Latest version:*** [ ![Download](https://api.bintray.com/packages/arthurimsacc/maven/bottomsheets-core/images/download.svg) ](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion)

````groovy
ext {
    //...
    bottomSheetsLibraryVersion = "1.0.0"
}

dependencies {
    //...
    implementation //TODO <--- the proper package name to be placed here
}
````

3. Enable the **jetifier** and **androidX** support in the top-level `gradle.properties` file.

````groovy
//...
android.enableJetifier=true
android.useAndroidX=true
//....
````

4. Update your `compileSdkVersion` in the module-level `build.gradle` file to **28+**.

````groovy
//...
android {
    //...
    compileSdkVersion 28
    //...
}
//...
````

5. Update your `com.android.support.appcompat.*` dependency to the new `androidx.appcompat.*` alternative.

````groovy
//...
dependencies {
    //...
    implementation "androidx.appcompat:appcompat:1.0.2"
    //...
}
//...
````

//TODO <---
Important <- Theme.NoActionBar <--- !!!

6. Proceed with the implementation. //TODO <---
> ***See: [Basic Implementation](#)*** //TODO <--- to be replaced with the corresponding section references

## Basic Implementation

//TODO <--- to be done

## Action Picker Implementation

//TODO <--- to be done

## Custom Action Picker Implementation

//TODO <--- to be done

## Advanced Use

//TODO <--- to be done

- **More uses**
<br>See the [Sample app](https://github.com/arthur3486/bottomsheets/tree/master/app/src/main/java/com/arthurivanets/demo).

## Contribution

See the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Hall of Fame

<table>
    <tbody>
        <tr>
            <td valign="middle;">
	            <a href="https://play.google.com/store/apps/details?id=com.arthurivanets.reminder">
                    <img src="https://lh3.googleusercontent.com/uD65OOIxM1-khzDI5OowwdzjD9j8CgelcH9mBGAZWIAsAyoTtEE7smUH9GAf3mCg8AA=s360" width="70" height="70"/>
                </a>
            </td>
            <td valign="middle;"><b>Reminder</b></td>
        </tr>
        <tr>
            <td valign="middle;">
                <a href="https://play.google.com/store/apps/details?id=com.arthurivanets.owly">
	                <img src="https://lh3.googleusercontent.com/FHaz_qNghV02MpQBEnR4K3yVGsbS_0qcUsEHidzfujI3V01zyLp6yo7oK0-ymILdRk9k=s360-rw" width="70" height="70"/>
                </a>
            </td>
            <td valign="middle;"><b>Owly</b></td>
    	</tr>
    </tbody>
</table>


> Using BottomSheets in your app and want it to get listed here? Email me at arthur.ivanets.l@gmail.com!

## License

BottomSheets library is licensed under the [Apache 2.0 License](LICENSE).
