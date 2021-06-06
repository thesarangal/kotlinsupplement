# KotlinSupplement
Collection of Advance Kotlin Extension Functions

KotlinSupplement is a simple library with collection of advance usefull Kotlin extension functions.


Current Version: 1.0.0

### Collection of Extension Functions for:

- Acitivity
- Context
- Format Conversion
- Graphics
- LOG
- Safety Function for String, Array and Collections etc
- String
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

### All Extension Functions
#### I. Activity Extensions
##### * setWindowBackground
##### * hideKeyboard
##### * killTheApp
##### * restart
##### * restartApplication
##### * openDialerForCall
##### * openEmail
##### * openURLInBrowser
##### * shareTextMessage
##### * openDirectionInGoogleMap
##### * openLocationInGoogleMap
##### * openAddressInGoogleMap

#### II. Context Extensions
##### * copyTextToClipboard
##### * toast
##### * isGPSEnabled
##### * isLocationNetworkProvider

#### III. Format Extensions
##### * toDoubleFormat
##### * formatDouble
##### * formatDecimal

#### IV. Graphics Extensions
##### * getBitmap
##### * rotateImageIfRequired
##### * modifyOrientation
##### * flip
##### * rotateImage

#### V. LOG Extensions
##### * logger

#### VI. Safety Extensions
##### * isNotNullAndEmpty (For String/CharSequence)
##### * isNotNullAndEmptyTrim (For String/CharSequence)
##### * isNotEmptyTrim (For String/CharSequence)
##### * isNotNullAndEmpty (For Array)
##### * isNotNullAndEmpty (For Collection)
##### * ifNotNull
##### * isNotNullAndEmpty  (For Map)

#### VII. String Extensions
##### * upperCase
##### * lowerCase
##### * capitalize
##### * capitalizeSentence
##### * capitalizeFirstLetter
##### * capitalizeFirstLetterOnly
##### * formatSpaceUnderScore
##### * getLastCharacters
##### * parseColor
##### * setValue (For StringBuilder)
##### * getCurrencySymbol
##### * isContainEnglish
##### * isContainHindi

#### VIII. Validation Extensions
##### * isValidIFSC
##### * isValidUPI
##### * isValidPANCard
##### * isStrongPassword
##### * isPhoneValid

#### IX. Misc Extensions
##### * mixTwoColors



#### Developed with ❤ by Sarangal
