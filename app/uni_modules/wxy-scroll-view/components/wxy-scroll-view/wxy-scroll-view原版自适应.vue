<template>
	<view class="uniareascroll" id="mysrcoll">
		<view class="solloNav" id="solloNav">
			<view class="windowTitle" :style="'height:' + CustomBar + 'px;background-color:' + backgroundColor + ';'" v-if="showNav"></view>
			<view class="navData"><slot name="scrollTop"></slot></view>
		</view>
		<scroll-view
			id="uniareascrollscroll"
			:class="{ 'scroll-y': scrollY, 'scroll-x': scrollX }"
			:scroll-y="scrollY"
			:scroll-x="scrollX"
			:upper-threshold="upperThreshold"
			:lower-threshold="lowerThreshold"
			:scroll-top="scrollTop"
			:scroll-left="scrollLeft"
			:scroll-into-view="scrollIntoView"
			:scroll-with-animation="scrollWithAnimation"
			:style="[scrollStyle]"
			@scrolltoupper="scrolltoupper"
			@scrolltolower="scrolltolower"
			@scroll="scroll"
			:enable-back-to-top="enableBackToTop"
			:show-scrollbar="showScrollbar"
			:refresher-enabled="refresherEnabled"
			:refresher-threshold="refresherThreshold"
			@refresherpulling="refresherpulling"
			@refresherrefresh="refresherrefresh"
			@refresherrestore="refresherrestore"
			@refresherabort="refresherabort"
		>
			<slot></slot>
			<view class="loading" v-show="showLoad">
				<view class="loadtext" v-show="beginChange && list.length > 0"><slot name="loading">加载中...</slot></view>
				<view class="loadtext" v-show="(!beginChange && !listIsChange) || list.length < 1"><slot name="loadend">已经到底了~</slot></view>
			</view>
		</scroll-view>
		<view class="scrollBar"><slot name="scrollBar"></slot></view>
	</view>
</template>

<script>
/**
 * 自适应scroll-view来源于https://ext.dcloud.net.cn/plugin?id=4121
 * 自适应的关键是纵向时开始height=0,若是横向时开始width=0，然后通过节点信息来决定宽度和高度，支持所有布局
 * 经测试:H5和APP支持scrollTop和scrollIntoView滚动,而微信小程序只支持scrollTop，想实现scrollIntoView就要用原生组件，封装不知道为什么,自适应高度可查看组件即可
 */

//以下函数来自于uView-UI2.0版，在此感谢
// 添加单位，如果有%，rpx，px,em,vw,vh,vmax,vmin等单位结尾或者值为auto，直接返回，否则加上px单位结尾
function padUnit(value = 'auto', unit = 'px') {
	value = String(value);
	// 用uView内置验证规则中的number判断是否为数值
	if (value == 0) return value; //0不用再加单位
	return /^[\+-]?(\d+\.?\d*|\.\d+|\d\.\d+e\+\d+)$/.test(value) ? `${value}${unit}` : value;
}
/**
 * 判断是否为空
 */
function empty(value) {
	switch (typeof value) {
		case 'undefined':
			return true;
		case 'string':
			if (value.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length == 0) return true;
			break;
		case 'boolean':
			if (!value) return true;
			break;
		case 'number':
			if (value === 0 || isNaN(value)) return true;
			break;
		case 'object':
			if (value === null || value.length === 0) return true;
			for (const i in value) {
				return false;
			}
			return true;
	}
	return false;
}
export default {
	props: {
		scrollX: {
			type: Boolean,
			default: false
		},
		scrollY: {
			type: Boolean,
			default: false
		},
		upperThreshold: {
			type: Number,
			default: 50
		},
		lowerThreshold: {
			type: Number,
			default: 50
		},
		scrollTop: {
			type: Number,
			default: 0
		},
		scrollLeft: {
			type: Number,
			default: 0
		},
		scrollIntoView: {
			type: String,
			default: ''
		},
		scrollWithAnimation: {
			type: Boolean,
			default: false
		},
		enableBackToTop: {
			type: Boolean,
			default: false
		},
		refresherEnabled: {
			type: Boolean,
			default: false
		},
		refresherThreshold: {
			type: Number,
			default: 45
		},
		showScrollbar: {
			type: Boolean,
			default: false
		},
		tabbar_h: {
			type: Number,
			default: 0
		},
		back: {
			type: Boolean,
			default: false
		},
		showNav: {
			type: Boolean,
			default: false
		},

		backgroundColor: {
			type: String,
			default: ''
		},
		showLoad: {
			//是否显示加载更多
			type: Boolean,
			default: false
		},
		list: {
			type: Array,
			default() {
				return [];
			}
		},
		loadTime: {
			type: Number,
			default: 0
		}
	},
	data() {
		return {
			scorll: 1, //状态，暂时无用
			StatusBar: 0, //顶部系统栏高度一般小程序20px
			CustomBar: 0, //系统小程序退出按钮下位置
			Custom: {},
			showLoading: false,
			httpPage: 1, //用来发请求的page
			nowPage: 1, //未请求的页码
			pageChange: true, //判断页面是否有下页
			listIsChange: true, //内容是否改变
			beginChange: false, //触发下拉请求
			// 补充的样式
			scrollStyle: {}, //计算自适应的宽度和高度，为正确计算，要求纵向时开始height=0,若是横向时开始width=0!!!
		};
	},
	methods: {
		getScroll() {
			uni.getSystemInfo({
				success: e => {
					// #ifndef MP
					this.StatusBar = e.statusBarHeight;
					if (e.platform == 'android') {
						this.CustomBar = e.statusBarHeight + 50;
					} else {
						this.CustomBar = e.statusBarHeight + 45;
					}
					// #endif
					// #ifdef MP-WEIXIN
					this.StatusBar = e.statusBarHeight;
					let custom = wx.getMenuButtonBoundingClientRect();
					this.Custom = custom;
					this.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
					// #endif
					// #ifdef MP-ALIPAY
					this.StatusBar = e.statusBarHeight;
					this.CustomBar = e.statusBarHeight + e.titleBarHeight;
					// #endif
					//可用区域总高度
					this.scenH = e.windowHeight;
					//可用区域总宽度
					this.scenW = e.windowWidth;
					this.getDescBox();
				}
			});
		},
		scroll(e) {
			console.log('scroll =>',e.detail);
			this.$emit('scroll', e);
		},
		scrolltoupper(e) {
			this.$emit('scrolltoupper', e);
		},
		scrolltolower(e) {
			if (this.showLoad) {
				this.beginChange = true;
				setTimeout(() => {
					this.httpPage = this.nowPage + 1;
					this.$emit('scrolltolower', { httpPage: this.httpPage, nowPage: this.nowPage, change: this.pageChange });
					this.pageChange = false;
					this.listIsChange = false;
				}, this.loadTime);
			} else this.$emit('scrolltolower', e);
		},
		refresherpulling(e) {
			//自定义下拉刷新控件被下拉
			this.$emit('refresherpulling', e);
		},
		refresherrefresh(e) {
			//自定义下拉刷新被触发
			this.$emit('refresherrefresh', e);
		},
		refresherrestore(e) {
			//自定义下拉刷新被复位
			this.$emit('refresherrestore', e);
		},
		refresherabort(e) {
			//自定义下拉刷新被中止
			this.$emit('refresherabort', e);
		},
		getDescBox() {
			//理解处理逻辑(原是竖向)
			// 1. 设置scroll-view的高度为0，否则scroll-view外部容器会被撑开
			// 2. scroll-view的容器view设置高度和宽度均为100%来适应父组件的容器
			// 3. 组件在mouted，页面在onReady中获取节点，一般数据都是后端动态渲染，建议通过this.$nextTick调用此方法
			// 4. 其实横向类似处理，先设置宽度为0，
			uni.createSelectorQuery()
				.in(this)
				.select('#mysrcoll')
				.boundingClientRect(result => {
					if (result) {
						//正式使用区域高度
						this.yxscorllH = result.height;
						//正式使用区域宽度
						this.yxscorllW = result.width;
					}
				})
				.exec();
			uni.createSelectorQuery()
				.in(this)
				.select('.solloNav')
				.boundingClientRect(result => {
					if (result) {
						//头部自定义区域高度
						this.headerH = result.height;
						//头部自定义区域宽度
						this.headerW = result.width;
					}
				})
				.exec();
			uni.createSelectorQuery()
				.in(this)
				.select('.scrollBar')
				.boundingClientRect(result => {
					if (result) {
						// 底部自定义区域高度
						this.barH = result.height;
						// 底部自定义区域宽度
						this.barW = result.width;
					}
				})
				.exec();
			uni.createSelectorQuery()
				.in(this)
				.select('#uniareascrollscroll')
				.boundingClientRect(result => {
					console.log(this.yxscorllH, this.barH, this.headerH, this.CustomBar);
					if (result) {
						let temp,
							scrollStyle = {};
						if (this.scrollY) {
							if (this.showNav) temp = this.yxscorllH - this.barH - this.headerH - this.CustomBar;
							else temp = this.yxscorllH - this.barH - this.headerH;
							scrollStyle.height = padUnit(temp);
						}
						if (this.scrollX) scrollStyle.width = padUnit(this.yxscorllW);
						this.scrollStyle = scrollStyle;
					}
				})
				.exec();
		},
		// 当slot内容变化导致尺寸需要重要计算，同时需要$nextTick和延时时，否则内容无法渲染
		refresh(time = 100) {
			setTimeout(() => {
				this.$nextTick(() => {
					this.getDescBox();
				});
			}, time);
		}
	},
	watch: {
		list(newVal, oldVal) {
			this.beginChange = false; //改变结束
			if (newVal.length > 0) {
				this.nowPage = this.httpPage;
				this.pageChange = true;
			} else {
				this.pageChange = false;
			}
			if (newVal == oldVal) {
				//判断是否真的内容变化了
				this.listIsChange = false; //内容没改变返回false
			} else {
				this.listIsChange = true;
			}
		}
	},
	mounted() {
		this.$nextTick(() => {
			this.getScroll();
		});
	}
};
</script>

<style lang="scss" scoped>
.uniareascroll {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
	font-size: 12px;
	.loading {
		padding: 0.2em 0 0.5em 0;
		text-align: center;
	}
	.scroll-y {
		height: 0;
	}
	.scroll-x {
		width: 0;
		white-space: nowrap;
	}
	.scrollBar {
		transition: all 0.3s linear;
	}
}
</style>
