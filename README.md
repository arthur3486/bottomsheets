# BottomSheets

> ***Android library designed to enrich your application with the beautiful stutter-free Material Design Bottom Sheets***

BottomSheets will help you make your application more appealing to your end users with its sleek stutter-free implementation of the Material Bottom Sheets.

[ ![Download](https://api.bintray.com/packages/arthurimsacc/maven/bottomsheets-core/images/download.svg) ](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)
![](https://img.shields.io/badge/API-18%2B-green.svg?style=flat)
![](https://travis-ci.org/arthur3486/bottomsheets.svg?branch=master)

## Contents

* [Demo](#demo)
* [Getting Started](#getting-started)
* [Structure](#structure)
* [Basic Custom Implementation](#basic-custom-implementation)
* [Action Picker Implementation](#action-picker-implementation)
* [Custom Action Picker Implementation](#custom-action-picker-implementation)
* [Advanced Use](#advanced-use)
* [Contribution](#contribution)
* [Hall of Fame](#hall-of-fame)
* [License](#license)

## Demo

* Demo Video

<div style="dispaly:flex">
	<a href="https://www.youtube.com/watch?v=hfvWiqZiqUU">
		<img src="https://github.com/arthur3486/bottomsheets/blob/master/5.jpg" width="60%">
	</a>
</div>

* Screenshots

<div style="dispaly:flex">
    <img src="https://github.com/arthur3486/bottomsheets/blob/master/1.jpg" width="24%">
    <img src="https://github.com/arthur3486/bottomsheets/blob/master/2.jpg" width="24%">
    <img src="https://github.com/arthur3486/bottomsheets/blob/master/3.jpg" width="24%">
    <img src="https://github.com/arthur3486/bottomsheets/blob/master/4.jpg" width="24%">
</div>

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
    implementation "com.arthurivanets.bottomsheet:bottomsheets-core:1.0.0"
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

6. Proceed with the implementation.
> ***See: [Structure](#structure), [Basic Custom Implementation](#basic-custom-implementation)***

## Structure

The library is comprised of 3 modules, namely:

* [`bottomsheets-core`](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion) - core functionality (Required)
* [`bottomsheets-sheets`](https://bintray.com/arthurimsacc/maven/bottomsheets-sheets/_latestVersion) - concrete implementations of the common bottom sheet types (Optional)
* [`bottomsheets-ktx`](https://bintray.com/arthurimsacc/maven/bottomsheets-ktx/_latestVersion) - common extensions and utils (Optional)

The first module - [`bottomsheets-core`](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion) - is a base module the other modules depend on, it is the starting point for all of your custom bottom sheet implementations and is a required module. This module provides you with the base classes required for the bottom sheet implementation, such as the [`BaseBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-core/src/main/java/com/arthurivanets/bottomsheets/BaseBottomSheet.java) which should be extended by your custom implementations of the bottom sheet and [`BottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-core/src/main/java/com/arthurivanets/bottomsheets/BottomSheet.java) contract which exposes its supported public APIs, here you will also find the [`Config.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-core/src/main/java/com/arthurivanets/bottomsheets/config/Config.java) class which will help you customize the bottom sheet.

The second module - [`bottomsheets-sheets`](https://bintray.com/arthurimsacc/maven/bottomsheets-sheets/_latestVersion) - is an optional module which includes the implementations of the most common bottom sheet types. Here you will find the [`ActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/ActionPickerBottomSheet.java), [`CustomActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/CustomActionPickerBottomSheet.java), as well as the coresponding configuration class - [`Config.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/config/Config.java). This module depends on the [`bottomsheets-core`](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion) and the [`Adapster`](https://github.com/arthur3486/adapster) library.

The third and last module - [`bottomsheets-ktx`](https://bintray.com/arthurimsacc/maven/bottomsheets-ktx/_latestVersion) - is a collection of the extensions and general utils. Here you'll be able to find the [`BottomSheetsExtensions.kt`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-ktx/src/main/java/com/arthurivanets/bottomsheets/ktx/BottomSheetsExtensions.kt) which will simplify the creation of the common bottom sheet types in your Activities and Fragments. This module depends on the [`bottomsheets-core`](https://bintray.com/arthurimsacc/maven/bottomsheets-core/_latestVersion), [`bottomsheets-sheets`](https://bintray.com/arthurimsacc/maven/bottomsheets-sheets/_latestVersion) and the [`Adapster`](https://github.com/arthur3486/adapster) library.

## Basic Custom Implementation

**IMPORTANT**: In order to prevent the visual inconsistencies which might be caused by certain UI elements of the Application Theme, it is recommended that you specify the Application/Activity Theme without the Action Bar (any variant of the `Theme.NoActionBar` will suffice). You might also want to make your Status Bar translucent for more immersive experience.

In order to implement a basic custom bottom sheet you need to follow three simple steps:
1) Create a new class and extend it from the [`BaseBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-core/src/main/java/com/arthurivanets/bottomsheets/BaseBottomSheet.java).
2) Provide the custom content view for your bottom sheet in the [`onCreateSheetContentView(...)`](https://github.com/arthur3486/bottomsheets/blob/master/app/src/main/java/com/arthurivanets/demo/ui/widget/SimpleCustomBottomSheet.kt#L34).
3) Use the created bottom sheet in your Activity/Fragment.

So, let's create a custom bottom sheet class - [`SimpleCustomBottomSheet`](https://github.com/arthur3486/bottomsheets/blob/master/app/src/main/java/com/arthurivanets/demo/ui/widget/SimpleCustomBottomSheet.kt) and provide our own content view:

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
class SimpleCustomBottomSheet(
    hostActivity : Activity,
    config : BaseConfig = Config.Builder(hostActivity).build()
) : BaseBottomSheet(hostActivity, config) {

    override fun onCreateSheetContentView(context : Context) : View {
        return LayoutInflater.from(context).inflate(
            R.layout.view_simple_custom_bottom_sheet,
            this,
            false
        )
    }

}
````

</p></details><br>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public class SimpleCustomBottomSheet extends BaseBottomSheet {

    public SimpleCustomBottomSheet(@NonNull Activity hostActivity) {
        this(hostActivity, new Config.Builder(hostActivity).build());
    }

    public SimpleCustomBottomSheet(@NonNull Activity hostActivity, @NonNull BaseConfig config) {
        super(hostActivity, config);
    }

    @NonNull
    @Override
    public final View onCreateSheetContentView(@NonNull Context context) {
        return LayoutInflater.from(context).inflate(
            R.layout.view_simple_custom_bottom_sheet,
            this,
            false
        );
    }

}
````

</p></details><br>

And now let's use the created [`SimpleCustomBottomSheet`](https://github.com/arthur3486/bottomsheets/blob/master/app/src/main/java/com/arthurivanets/demo/ui/widget/SimpleCustomBottomSheet.kt) in our Activity:

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>

````kotlin
class MainActivity : AppCompatActivity() {

    private var bottomSheet : BaseBottomSheet? = null

    private fun showCustomBottomSheet() {
        //...
        bottomSheet = SimpleCustomBottomSheet(this).also(BottomSheet::show)
    }

}
````

</p></details><br>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public class MainActivity extends AppCompatActivity {

    private BottomSheet bottomSheet;

    private void showCustomBottomSheet() {
        //...
        bottomSheet = new SimpleCustomBottomSheet(this);
        bottomSheet.show();
    }

}
````

</p></details><br>

## Action Picker Implementation

The implementation of the Action Picker Bottom Sheet becomes a trivial task with [`ActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/ActionPickerBottomSheet.java), as all you need to do here is simply provide a list of options and a bottom sheet configuration.

Let's use the [`ActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/ActionPickerBottomSheet.java) in our Activity:

* **With [`bottomsheets-ktx`](https://bintray.com/arthurimsacc/maven/bottomsheets-ktx/_latestVersion)**

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>

````kotlin
class MainActivity : AppCompatActivity() {

    private var bottomSheet : BottomSheet? = null

    private fun showActionsBottomSheet() {
        bottomSheet = showActionPickerBottomSheet(
            options = getActionOptions(),
            onItemSelectedListener = OnItemSelectedListener {
                // do something...
            }
        )
    }
	
    fun getActionOptions() : List<Option> {
        // your options
    }

}
````

</p></details><br>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public class MainActivity extends AppCompatActivity {

    private BottomSheet bottomSheet;

    private void showActionsBottomSheet() {
        bottomSheet = BottomSheetsUtils.showActionPickerBottomSheet(
            this,
            getActionOptions(),
            new OnItemSelectedListener<Option>() {
                @Override
                public void onItemSelected(@NonNull Option item) {
                    // do something
                }    
            }
        );
    }

    private List<Option> getActionOptions() {
        // your options
    } 

}
````

</p></details><br>

* **Without [`bottomsheets-ktx`](https://bintray.com/arthurimsacc/maven/bottomsheets-ktx/_latestVersion)**

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>

````kotlin
class MainActivity : AppCompatActivity() {

    private var bottomSheet : BottomSheet? = null

    private fun showActionsBottomSheet() {
        bottomSheet = ActionPickerBottomSheet.init(
            this,
            getActionOptions().map(::ActionItem),
            Config.Builder(this).build()
        )
        bottomSheet.setOnItemSelectedListener { 
            // do something...
        }
        bottomSheet.show()
    }

    fun getActionOptions() : List<Option> {
        // your options
    }

}
````

</p></details><br>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public class MainActivity extends AppCompatActivity {

    private BottomSheet bottomSheet;

    private void showActionsBottomSheet() {
        bottomSheet = ActionPickerBottomSheet.init(
            this,
            getActionOptions(),
            new Config.Builder(this).build()
        );
        bottomSheet.setOnItemSelectedListener(new OnItemSelectedListener<ActionItem>() {
            @Override
            public void onItemSelected(@NonNull ActionItem item) {
                // do something
            }
        });
        bottomSheet.show();
    }

    private List<ActionItem> getActionOptions() {
        // your options wrapped into ActionItem(-s)
    }

}
````

</p></details><br>

## Custom Action Picker Implementation

The implementation of the Custom Action Picker Bottom Sheet is not that much different from the [`Action Picker Implemenetation`](#action-picker-implementation), you just need to use the [`CustomActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/CustomActionPickerBottomSheet.java) instead of the [`ActionPickerBottomSheet.java`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/ActionPickerBottomSheet.java) in conjunction with your custom [`BaseActionItem`](https://github.com/arthur3486/bottomsheets/blob/master/bottomsheets-sheets/src/main/java/com/arthurivanets/bottomsheets/sheets/adapters/actionpicker/BaseActionItem.java)-based Items and that's it.

See the example of a [Custom Action Picker in Action](https://github.com/arthur3486/bottomsheets/blob/master/app/src/main/java/com/arthurivanets/demo/ui/MainActivity.kt#L99).

## Advanced Use

See the [Sample app](https://github.com/arthur3486/bottomsheets/tree/master/app/src/main/java/com/arthurivanets/demo).

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
