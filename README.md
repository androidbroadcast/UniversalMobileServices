# UniversalMobileServices [IN PROGRESS]

Unified API that make common way to use different mobile services for different apps store: Google Play, Huawei AppGallery, Amazon App Store, etc

Right now supported:
- [Huawei AppGallery](https://appgallery.huawei.com) + [Huawei Mobile Services (HMS)](https://huaweimobileservices.com/)
- [Google Play](https://play.google.com/store) + [Google Play Services (GMS)](https://developers.google.com/android/guides/overview) + [Firebase](https://firebase.google.com/)

Supported services:
| Service | GMS | HMS |
|-|-|-|
| Push Messages | [Firebase Cloud Messages](https://firebase.google.com/docs/cloud-messaging)| [Push Kit](https://developer.huawei.com/consumer/en/hms/huawei-pushkit/) |
| Analytics | [Google Analytics](https://firebase.google.com/docs/analytics) | [Analytics Kit](https://developer.huawei.com/consumer/en/hms/huawei-analyticskit/) |
| Remote Config | [Firebase Remote Config](https://firebase.google.com/docs/remote-config) | [HMS Remote Config](https://developer.huawei.com/consumer/en/doc/development/AppGallery-connect-Guides/agc-remoteconfig-introduction-0000001055149778) |
| Core | - | - |
