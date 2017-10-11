# base_mvp
android快速开发框架 基于mvp设计模式
引用方式：
在Android Studio里面配置Jitpack插件

 在项目的根目录下的 build.gradle 文件里面添加
     1： 在 dependencies 标签下面添加 

          classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'

     2： 在repositories 标签下面添加

         maven { url "https://jitpack.io" }
         
 最后在app或者你要使用的库的 build.gradle 文件里面的dependencies标签下面添加
 
     compile 'com.github.Android-xiaole:base_mvp:v1.0.0'
