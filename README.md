# 简介
CustomAssortedView of Setting on AndroidTV
适用于AndroidTV/投影平台的列表设置项自定义组合View
## 目前包括四种view：
### 1、DefaultItemLayout:
默认实现跳转功能的设置项View
### 2、SeekBarItemLayout:
带有SeekBar滑动/进度条的设置项View
### 3、SelectItemLayout:
可弹出多项选择的设置项View
### 4、SwitchItemLayout:
带有Switch开关的设置项View

# 集成方法：
[![](https://jitpack.io/v/LankyBin/CustomAssortedView.svg)](https://jitpack.io/#LankyBin/CustomAssortedView)

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.LankyBin:CustomAssortedView:V1.1.1'
	}
