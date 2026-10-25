<template>
	<view>
		<u-tabbar :list="tabBarList" :active-color="activeColor" :inactive-color="inactiveColor"  :height="84"
			:border-top="borderTop">
		</u-tabbar>
	</view>
</template>
<script>
	import store from '@/store'
	export default {
		props:{
			tabBarList:{
				type:Array,
				default:uni.getStorageSync('tabBarList')
			}
		},
		data() {
			return {
				borderTop: true,
				inactiveColor: '#909399',
				activeColor: '#328CFA',
			}
		},
	}
</script>


<style>

</style>
