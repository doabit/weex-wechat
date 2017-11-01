# weex-wechat [中文版](https://github.com/doabit/weex-wechat/blob/master/README-zh.md)

> Wechat(auth, pay, share) plugin for weex.

## getting start

```bash
weexpack plugin install weex-wechat
```
## setup for android

### add `jitpack.io` to android/build.gradle

```gradle
allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

### Integrating with login and share

If you are going to integrate login or share functions, you need to create a package named 'wxapi' in your application package and a class named [WXEntryActivity](https://github.com/doabit/weex-wechat/blob/master/android/WXEntryActivity.java)

Then add the following node to `AndroidManifest.xml`:

```xml
<manifest>
  <application>
    <activity
      android:name=".wxapi.WXEntryActivity"
      android:label="@string/app_name"
      android:exported="true"
    />
  </application>
</manifest>
```

### Integrating the WeChat Payment

If you are going to integrate payment functionality by using this library, then
create a package named also `wxapi` in your application package and a class named
[WXPayEntryActivity](https://github.com/doabit/weex-wechat/blob/master/android/WXPayEntryActivity.java)

Then add the following node to `AndroidManifest.xml`:

```xml
<manifest>
  <application>
    <activity
      android:name=".wxapi.WXPayEntryActivity"
      android:label="@string/app_name"
      android:exported="true"
    />
  </application>
</manifest>
```

## setup for ios

Add "URL Schema" as your app id for "URL type" in Targets > info, See the following screenshot for the view on your XCode.

![URL_TYPE](https://user-images.githubusercontent.com/330368/32264784-3cbe0b8a-beae-11e7-9c75-aab901b4cdab.jpg)

On iOS 9, add wechat and weixin into LSApplicationQueriesSchemes in Targets > info > Custom iOS Target Properties. Or edit Info.plist then add:

```plist
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>weixin</string>
  <string>wechat</string>
</array>
```

Then copy the following in AppDelegate.m:

```objc
- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    return  [WXApi handleOpenURL:url delegate:[WeChatManager shareInstance]];
}

- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    return [WXApi handleOpenURL:url delegate:[WeChatManager shareInstance]];
}
```

## reference resources

[react-native-wechat](https://github.com/yorkie/react-native-wechat)

[WeexErosFramework](https://github.com/aa453509345/WeexErosFramework)

[WeChat Open Platform](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417674108&token=&lang=zh_CN)

## Author

[@doabit](https://github.com/doabit)

## license

[MIT](http://opensource.org/licenses/MIT)