# weex-wechat

> Wechat(auth, pay, share) plugin for weex.

## 准备工作

```bash
weexpack plugin install weex-wechat
```
## android 配置

### 添加 `jitpack.io` 到 android/build.gradle

```gradle
allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

### 集成微信登录和分享

集成微信登录和分享，首先创建一个包名为 'wxapi'，并且添加一个名为 [WXEntryActivity](android/WXEntryActivity.java) 的类。

添加下面代码到 `AndroidManifest.xml`:

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

### 集成微信支付

在 `wxapi`包中添加文件名为 [WXPayEntryActivity](android/WXPayEntryActivity.java) 的类

添加下面代码到 `AndroidManifest.xml`:

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

## IOS 配置

在Xcode中，选择你的工程设置项，选中“TARGETS”一栏，在“info”标签栏的“URL type“添加“URL scheme”为你所注册的应用程序id（如下图所示）。

![URL_TYPE](https://user-images.githubusercontent.com/330368/32264784-3cbe0b8a-beae-11e7-9c75-aab901b4cdab.jpg)

iOS 9 往后, 在 Targets > info > Custom iOS Target Properties 中 添加 wechat 和 weixin 到 LSApplicationQueriesSchemes. 或者 直接编辑 Info.plist:

```plist
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>weixin</string>
  <string>wechat</string>
</array>
```

添加如下代码到 AppDelegate.m:

```objc
- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    return  [WXApi handleOpenURL:url delegate:[WeChatManager shareInstance]];
}

- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    return [WXApi handleOpenURL:url delegate:[WeChatManager shareInstance]];
}
```

## 参考资料

[react-native-wechat](https://github.com/yorkie/react-native-wechat)

[WeexErosFramework](https://github.com/aa453509345/WeexErosFramework)

[微信开放平台](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417674108&token=&lang=zh_CN)

## license

[MIT](http://opensource.org/licenses/MIT)