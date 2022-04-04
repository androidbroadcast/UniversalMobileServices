-keep class com.huawei.agconnect.remoteconfig.*{*;}
-keepclassmembers class **{
    public <init>(android.content.Context, com.huawei.agconnect.AGConnectInstance);
}
-keepclassmembers class com.huawei.agconnect.remoteconfig.internal.server.**{*;}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
