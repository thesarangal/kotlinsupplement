# KotlinSupplement
Collection of Advance Kotlin Extension Functions

KotlinSupplement is a simple library with collection of advance useful Kotlin extension functions.


Current Version: [![](https://jitpack.io/v/thesarangal/kotlinsupplement.svg)](https://jitpack.io/#thesarangal/kotlinsupplement)

### Collection of Extension Functions for:

- Activity
- Context
- Date Format Conversion
- Format Conversion
- Graphics
- Safety Function for String, Array and Collections etc
- String
- Validations
- Misc

### How to To get a Git project into your build

#### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

#### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.thesarangal:kotlinsupplement:VERSION_CODE'
	}

Done! The first time you request a project JitPack checks out the code, builds it and serves the build artifacts (jar, aar).

### How to use KotlinSupplement
#### I. Show Toast with Extension Function

    Kotlin:

    /* Any Class which is extended from Context Class */
    toast("Welcome to KotlinSupplement")

    /* If there is reference of Context */
    context.toast("Welcome to KotlinSupplement")

## Contributions

Contributions to this library are welcome. If you find a bug or have a feature request,
please open an issue on
the [GitHub repository](https://github.com/thesarangal/kotlinsupplement).

## License

This library is released under the [MIT License](https://opensource.org/licenses/MIT).
You are free to use, modify, and distribute the library for commercial and non-commercial purposes, with attribution to the author.

#### Developed with ❤ by Sarangal
