<template>
	<view>
		<view>
		      <u-notice-bar :text="text1" font-size="20"></u-notice-bar>
		    </view>
		<view class="picture">
			<u-swiper
				:list="list3"
				indicator
			    indicatorMode="line"
				height="200"
	            circular 
		    ></u-swiper>
		</view >
			
		<view class="top">
			<view class="search">
				<view class="form">
					<u--input
						placeholder="请输入医院/科室/医生姓名"
						prefixIcon="search"
						prefixIconStyle="font-size: 20px;
						color: #909399"
						shape = "circle"
					></u--input>
				</view>
				
				<view class="button">
					<u-button type="primary" :plain="true" text="搜索" size = "normal" @tap="search"></u-button>
				</view>
			</view>
		</view>
		
		<view class="main">
			
				<u-button type="primary" :plain="true" text="专家名医" size = "large" @tap="famous"></u-button>
			
		</view>
		
		<view class="middle_tittle">
			<view class="middle_text">
				<u--text text="按科室查询" size = "35"></u--text>
			</view>
			
		</view>
		
		<view class="department">
			<u-scroll-list>
				<view class="scroll-list">
					<view
						class="scroll-list__line"
						v-for="(item, index) in menuArr"
						:key="index"
					>
						<view
							class="scroll-list__line__item"
							v-for="(item1, index1) in item"
							:key="index1"
							:class="[(index1 === item.length - 1) && 'scroll-list__line__item--no-margin-right']"
							
						>
							<image
								class="scroll-list__line__item__image"
								:src="item1.icon"
								mode=""
								@click="selectDepartment(item1.name)"
							></image>
							<text class="scroll-list__line__item__text">{{ item1.name }}</text>
						</view>
					</view>
				</view>
			</u-scroll-list>
		</view>
		
		
		<view>
				<u-tabbar
					:value="value"
					:fixed="true"
					:placeholder="false"
					activeColor="#d81e06"
					:safeAreaInsetBottom="false"
				>
					
					<u-tabbar-item text="首页" icon="home" @click = 'click' ></u-tabbar-item>
					<u-tabbar-item text="健康档案" icon="photo" @click = 'click'></u-tabbar-item>
					<u-tabbar-item text="我的" icon="account" @click = 'click'></u-tabbar-item>
					
				</u-tabbar>
		</view>
			 
		
	</view>
	
</template>

<script>
	export default {
		// props:{
		// 		tabBarList:{
		// 		type:Array,
		// 		default:uni.getStorageSync('tabBarList')
		// 	}
		// },
		onLoad(){
			uni.hideTabBar(),
			this.getDate(),
			this.getDepartment()
		},
		data() {
			return {
				
				value:0,
				
				borderTop: true,
				inactiveColor: '#909399',
				activeColor: '#328CFA',
				
				text1: '欢迎您，xxx，今天是xxxxxx',
								
				list3: [
					'/static/background3.jpg',
				    '/static/background1.jpg',
				    '/static/background2.jpg',
				],
				menuArr: [
					[{
						name: '',
						icon: '/static/department/department1.png'
					},
					{
						name: '',
						icon: '/static/department/department15.png'
					}, {
						name: '',
						icon: '/static/department/department3.png'
					}, {
						name: '',
						icon: '/static/department/department4.png'
					}, {
						name: '',
						icon: '/static/department/department5.png'
					}, {
						name: '',
						icon: '/static/department/department6.png'
					}, {
						name: '',
						icon: '/static/department/department7.png'
					}
					],
					[{
						name: '',
						icon: '/static/department/department8.png',
					},{
						name: '',
						icon: '/static/department/department9.png'
					},{
						name: '',
						icon: '/static/department/department10.png'
					}, {
						name: '',
						icon: '/static/department/department11.png'
					}, {
						name: '',
						icon: '/static/department/department12.png'
					}, {
						name: '',
						icon: '/static/department/department13.png'
					}, {
						name: '',
						icon: '/static/department/department14.png'
					}]
					]

			}
		},
		methods:{
			getDepartment(){
				uni.request({
				 	url:'http://10.0.2.2:8080/department/selectDepartment',
					//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"GET",
				 	success: (res) => { //如果访问接口成功就会进入success
				 	//	console.log(res.data)
				 	//	console.log("接口调用成功")
						
						let list = res.data
					//	console.log(list);
						
						let list3 = list.slice(0,7)
						let list4 = list.slice(7,14)
						
					//	console.log(list3, list4)
						for(let i=0;i<list3.length;i++){
							this.menuArr[0][i].name=list3[i]
							this.menuArr[1][i].name=list4[i]
						}
					//	console.log(this.menuArr[0])
						
				 	},
				 	fail:(err)=>{ //如果访问接口失败就会进入fail	
				 		console.log("调用接口失败"),
				 		this.msg = err
				 	}
				 })
			},
			
			getDate(){
				let date = new Date();
				// 使用Date对象的方法获取年月日
				let year = date.getFullYear(); //获取年份
				let month = date.getMonth() + 1; //获取月份，注意要加1
				let day = date.getDate(); //获取日期
				// 拼接成字符串，并补0
				let result = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
				
				uni.getStorage({
					key: 'usertype',
					success: function (res) {
						this.usertype = res.data;
						// console.log(this.usertype);
					}
				});
				let a = "欢迎您  ";
				let b = "今天是  ";
				let c = a + b + result; // c = "HelloWorld";
				this.text1=c;
				
				this.value== getApp().globalData.value; 
				// console.log(this.value);
				
			},
			selectDepartment(name){
				//console.log(name)
				uni.setStorageSync("department",name)
				uni.navigateTo({
					url:"/pages/inquiry/doctor_select_common"
				})
			},
			click(e) {
				//console.log('click1', e);
				let type = uni.getStorageSync('usertype')
				console.log(type)
				if(e==0){
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_doctor',
						}),
						getApp().globalData.value = 0
					}else if(type==1){
						uni.switchTab({
							url:'/pages/main/index',
						}),
						getApp().globalData.value = 0
					}else{
						uni.switchTab({
							url:'/pages/main/index_doctor',
						}),
						getApp().globalData.value = 0
					}
				}
				else if(e==1){
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_data',
						}),
						getApp().globalData.value = 1
					}else if(type==1){
						uni.switchTab({
							url:'/pages/main/health'
						}),
						getApp().globalData.value = 1
					}else{
						uni.switchTab({
							url:'/pages/main/health_doctor'
						}),
						getApp().globalData.value = 1
					}
					
				}
				else{
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_user'
						}),
						getApp().globalData.value = 2
					}else if(type==2){
						uni.switchTab({
							url:'/pages/main/personal_doctor',
						}),
						getApp().globalData.value = 2
					}else{
						uni.switchTab({
							url:'/pages/main/personal_patient',
							
						}),
						getApp().globalData.value = 2
					}
					
				}
				
			},
			test(){
			//	 var value = uni.getStorageSync('tabBarList');
			//	 console.log(value)
			},
			select(){
				uni.navigateTo({
					url:"/pages/inquiry/doctor_select"
				})
			},//科室查询
			search(){
				uni.navigateTo({
					url:"/pages/inquiry/doctor_select"
				})
			},
			famous(){
				uni.navigateTo({
					url:'/pages/inquiry/doctor_select_famous'
				})
			},
			common(){
				uni.navigateTo({
					url:'/pages/inquiry/doctor_select_common'
				})
			},
			left() {
						console.log('left');
					},
					right() {
						console.log('right');
					},
					
		}
	}
</script>

<style lang="scss">
	
.department{
	margin-top: 20px;
}

.middle_tittle{
	border: 1px solid gainsboro;
	margin-top: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.middle_text{
	width: 150px;
	height:30px;
	margin-bottom: 10px
}

.scroll-list {
	@include flex(column);

	&__goods-item {
		margin-right: 20px;

		&__image {
			width: 80px;
			height: 80px;
			margin-top: 30px;
		}

		&__text {
			color: #f56c6c;
			text-align: center;
			font-size: 12px;
			margin-top: 5px;
		}
	}

	&__show-more {
		background-color: #fff0f0;
		border-radius: 3px;
		padding: 3px 6px;
		@include flex(column);
		align-items: center;

		&__text {
			font-size: 12px;
			width: 12px;
			color: #f56c6c;
			line-height: 16px;
		}
	}

	&__line {
		@include flex;
		margin-top: 10px;

		&__item {
			margin-right: 15px;

			&__image {
				width: 61px;
				height: 48px;
			}

			&__text {
				margin-top: 5px;
				color: $u-content-color;
				font-size: 12px;
				text-align: center;
			}

			&--no-margin-right {
				margin-right: 0;
			}
		}
	}
}

.picture{
	
}

.main{
	margin-top: 50px;
	display: flex;
	margin-left: 100px;
	margin-right: 100px;
	flex-direction: row;
	
}
.search{
	margin-top: 20px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	
}
.main_button1{

}
.form{
	
}

.button{
	margin-left: 10%;
}



</style>
