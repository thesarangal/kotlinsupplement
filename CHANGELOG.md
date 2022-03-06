# ChangeLog

### Version 1.2.1
#### Date: March 06, 2022

##### Added

- Graphics Extension:
  getResized

- Validation Extension:
  Double.formatDoubleTrail

- Format Extension:
  String.isValidGST

- Misc Extension:
  View.showWithAnim
  View.hideWithAnim
  InputStream.getBytes
  
###### Update

- Activity Extension:
  Activity.openDialerForCall
  Activity.openEmail
  Activity.openURLInBrowser
  Activity.shareTextMessage
  Activity.openAppSettings
  Activity.isServiceRunning
  
- Format Extension:
  String.formatInt
  
- String Extension:
  String.isContainNumber
  
- Validation Extension:
  String.isValidPackageName
  
- Date Format Extension:
  'DATE_TIME_FORMAT' interface replaced with 'DateTimeFormat' object
  
###### Removed

- Validation Extension:
  isPhoneValid(): Use 'isValidPhone()' Instead
  
- Log Extensions Removed

### Version 1.2.0
#### Date: September 27, 2021

- New Date Format Extension Added
  getDateByFormatCustomUTC
  getDateFormatFromLong

- New Format Extension Added
  formatInt
  formatFloat

- New Activity Extension Added
  openAppSettings

- Update: Log Extensions

### Version 1.1.1
#### Date: September 27, 2021

- New Misc Extension Added
  navigateSafely
  
### Version 1.1.0
#### Date: September 13, 2021

- New Activity Extensions Added
  setStatusBarColor
  setNavigationBarColor
  setSystemBarColor

- New Misc Extension Added
  isColorDark

- New Graphics Extension Added
  addGradient

- New String Extensions Added
  haveAnyAlphabetNumber
  isContainNumber

- New Validation Extensions Added
  isPasswordValid
  isValidPackageName
  
### Version 1.0.0
#### Date: June 06, 2021

- Publish to jitpack
- Initial version