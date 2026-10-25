# wxy-scroll-view 介绍

基于插件市场[area_scroll 自适应高度的区域滚动组件](https://ext.dcloud.net.cn/plugin?id=4121) 增强改进而来,到此非常感谢原作者，提供了很好思路,非常适应开发聊天程序类

>**重要说明:** 
> 1. 当前自适应方案缺陷：由于开始将滚动区域设置为0(height或width)，然后再渲染后计算尺寸，明显感觉到延迟。而且外围空间变化时需要再重新计算，又要延迟
> 2. 最佳自适应方案:解决问题模型仍然是滚动区域有个父容器，仍然是通过父容器来自适应外围空间，上面方案设置width和height为0是防止滚动区域撑大父容器。**现在是父窗口相对定位，滚动区域绝对定位。**由于绝对定位脱离文档流，同样不影响父容器。相比上面计算方案**不存在延迟，而且能动态自适应外围空间**
> 3. 通过最佳自适应方案，我们不用这个封装组件也可以很容易实现自适应滚动区域，而且封装组件的scroll-into-view支持范围受限，而原生scroll-view则不存在任何问题，这个组件应该是最后一次更新了。

## 组件改进

1. 增加横向自适应,优化了样式
2. 为同步官方scroll-view组件，所以属性和事件都同名
3. 增加了内容导致尺寸改变后会重新计算的功能
4. 增加了微信小程序使用注意事项
5. 修复了原组件参数文档错误，如showNav正确应该show-nav等

## 平台兼容性

目前测试过APP、微信小程序和H5，其它应该也支持，毕竟只使用基本组件。

## 参数说明
|属性名			|类型	|默认值	|说明													|
|:-:			|:-:	|:-:	|:-:													|
|show-nav		|Boolean|false	|自动填充状态栏，用于自定义导航时填充系统导航栏房租覆盖	|
|background		|String	|#fff	|填充系统栏的颜色										|
|show-load		|Boolean|false	|false													|
|list			|Array	|[]		|用于计算的list数组										|
|load-time		|Number	|0		|动画延时时长,单位是毫秒								|
		
|事件称名		|说明											|返回值	|
|:-:			|:-:											|:-:	|
|scrolltolower	|在官方基础上增加了自动控制页数和加载更多功能	|event	|
|其它原生事件	|同官方											|event	|

## 使用案例

```vue
<wxy-scroll-view scroll-y>
	<view class="msg-item" v-for="(item, index) of 100" :key="index">
		<image class="msg-item-img" src="../../static/icon/logo.png" mode="widthFix"></image>
		<view class="msg-item-content">
			<view class="msg-item-content-head">张三</view>
			<view class="msg-item-content-main">大家好啊</view>
		</view>
		<view class="msg-item-sign">
			<view class="msg-item-sign-time">10:15</view>
			<view class="msg-item-sign-badge"><uni-badge :text="index" type="error" size="small"></uni-badge></view>
		</view>
	</view>
</wxy-scroll-view>
```

具体可参考项目中演示，页面路径是pages/index/scroll.vue

主要演示了只使用区域滚动和使用顶栏+滚动+工具栏经典聊天布局，通过H5,APP和微信小程序测试，代码中注意事项也进行了说明