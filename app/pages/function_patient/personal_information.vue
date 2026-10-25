<template>
	<view class="index">
		<view class="index-user">
			<view class="index-user-img">
				<view class="index-user-content">
					<image :src="scu" @click="changeImage"></image>
					<u--text :text="username" size="40" align="center"></u--text>
				</view>
				
			</view>		
	

			<view>	
				<u-gap
					height="40"
					bgColor="#fff"
				></u-gap>
				<view class="main">
					<u-cell-group>
						<u-cell
							:titleStyle="{fontWeight: 500}"
							@click="open1()"
							:title="title1"
							isLink
							size="large"
						>
						
						</u-cell>
						<u-popup :show="show1" @close="close" mode="center" >
						    <view class="pop">
						       <u--text size = "30" type = "info" text="修改昵称"></u--text>
						       	
						       <view class="find">
						       	<u-input v-model="name0" placeholder="请输入昵称" />
						       	
						       </view>
						       
						       <u-button
						       	type="success"
						       	text="保存"
						       	:plain="true"
						       	customStyle="width: 100%"
						       	@click="name()"
						       	></u-button>
						         </view>
						</u-popup>
						<u-cell
							:titleStyle="{fontWeight: 500}"
							@click="open2()"
							:title="title2"
							isLink
							size="large"
						>
						</u-cell>
						<u-popup :show="show2" @close="close" mode="center" >
						    <view class="pop">
						        <u--text size = "30" type = "info" text="修改联系方式"></u--text>
						    		
						    	<view class="find">
						    		<u-input v-model="connect0" placeholder="请输入联系方式" />
						    		
						    	</view>
								
						    	<u-button
						    		type="success"
						    		text="保存"
						    		:plain="true"
						    		customStyle="width: 100%"
						    		@click="connect()"
						    		></u-button>
						         </view>
						    
						</u-popup>
						<u-cell
							:titleStyle="{fontWeight: 500}"
							@click="open3()"
							:title="title3"
							isLink
							size="large"
						>
						</u-cell>
						<u-popup :show="show3" @close="close" mode="center" >
						    <view class="pop">
						        <u--text size = "30" type = "info" text="修改密码"></u--text>
						        	
						        <view class="find">
						        	<u-input v-model="password1" placeholder="请输入新密码" />
						        	<u-input v-model="password2" placeholder="请重复新密码" />
						        </view>
						        
						        <u-button
						        	type="success"
						        	text="保存"
						        	:plain="true"
						        	customStyle="width: 100%"
						        	@click="password()"
						        	></u-button>
						        
						         </view>
						</u-popup>
					</u-cell-group>
				</view>
			</view>
		</view>
			
			<view class="bottom_button">
				<u-button type="error" text="退出" customStyle="width: 200rpx" @tap="exit"></u-button>
			</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				username:'',
				scu:'',
				
				disabled: true,
				show: false,
					
				show1:false,
				show2:false,
				show3:false,
				
				name0:'',
				password1:'',
				password2:'',
				connect0:'',
				
				title1:'修改昵称',
				title2:'联系方式',
				title3:'修改密码',
					
					list: [
						{
							show:false,
							title: '修改昵称',
							iconUrl: 'https://cdn.uviewui.com/uview/demo/popup/modeCenter.png'
						},
						{
							
							show:false,
							title: '联系方式',
							iconUrl: 'https://cdn.uviewui.com/uview/demo/popup/modeCenter.png'
						},
						{
							show:false,
							title: '修改密码',
							iconUrl: 'https://cdn.uviewui.com/uview/demo/popup/modeCenter.png'
						},
					]

			}
		},
		onLoad() {
			this.get();
		},
		methods: {
			
			changeImage(){
				
	
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					// success: function (res) { //用function的方式会找不到send方法
					success: (res) => {
						imgToBase64(res)
						//console.log(JSON.stringify(res.tempFilePaths));
						
					}
				})
				function imgToBase64(res){
						plus.io.resolveLocalFileSystemURL(res.tempFilePaths[0], function(entry){
							entry.file( function(file){
								let user = uni.getStorageSync("username")
								let fileReader = new plus.io.FileReader();
								fileReader.readAsDataURL( file );
								fileReader.onloadend = function(evt) {
									
								
									//console.log(evt.target.result,'转换evt');
									
									let img = evt.target.result
									console.log(typeof(img))
									let that = this
									uni.request({
												url:'http://10.0.2.2:8080/register_patient/updateImage',
												header: {
													"Content-Type": "application/json"
												},
												data:({
													username :user,
													image :img
												}),
												method: 'POST', 
												success: function(res) {
													console.log(res.data)
													uni.request({
														url:'http://10.0.2.2:8080/register_patient/SelectPatient',
														header: {
															"Content-Type": "application/json"
														},
														data:({
															username: user
														}),
														method:'GET',
														success: (res) => {
															console.log(res)
															this.scu = res.data[0].image
															this.username = res.data[0].name
															
														//	console.log(res.data[2])
															
														//	console.log(this.columns)
														}
													})
													//that.get()
												}
											})
								}
							});
						})
					};
					
				// // uni.chooseImage({
				// // 					count: 1, //count: 6, //默认9
				// // 					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
				// // 					sourceType: ['album'], //从相册选择
				// // 					success: function(res) {
				// // 						console.log(JSON.stringify(res.tempFilePaths));
				 
				// // 					}
				// // 				});
				// //this.show1 = true
				// 	uni.chooseImage({
				// 	count: 1, //count: 6, //默认9
				// 	sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
				// 	sourceType: ['album'], //从相册选择
				// 	success: (res) => {
				// 		imgToBase64(res)
				// 		let user = uni.getStorageSync("username")
				// 		let temp = res
				// 		//console.log(typeof(temp))
				// 		let that = this
				// 		uni.request({
				// 			url:'http://10.0.2.2:8080/register_patient/updateImage',
				// 			header: {
				// 				"Content-Type": "application/json"
				// 			},
				// 			data:({
				// 				username :user,
				// 				image :temp
				// 			}),
				// 			method: 'POST', 
				// 			success: function(res) {
				// 				console.log(res.data)
							
				// 				//that.get()
				// 			}
				// 		})
				// 	},

					
				// });		
				// 	function imgToBase64(res){
				// 						plus.io.resolveLocalFileSystemURL(res.tempFilePaths[0], function(entry){
				// 							entry.file( function(file){
				// 								let fileReader = new plus.io.FileReader();
				// 								fileReader.readAsDataURL( file );
				// 								fileReader.onloadend = function(evt) {
				// 									let temp = evt.target.result
				// 									uni.setStorageSync("base64",temp)
													
				// 										//console.log(evt.target.result,'转换evt');
				// 									}
				// 								});
				// 							})
				// 						};

			},
			
			name(){
				let user = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/register_patient/updateName',
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username :user,
						name :this.name0
					}),
					method: 'POST', 
					success: function(res) {
						//console.log(res.data)
						this.show1 = false
						this.get()
					}.bind(this)
				})
				
				
			},
			connect(){
				let user = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/register_patient/updateConnect',
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username :user,
						connect :this.connect0
					}),
					method: 'POST', 
					success: function(res) {
						//console.log(res.data)
						this.show2 = false
					}.bind(this)
				})
				
			},
			password(){
				let user = uni.getStorageSync("username")
				if(this.password1==this.password2){
					uni.request({
						url:'http://10.0.2.2:8080/user/UpdatePassword',
						header: {
							"Content-Type": "application/json"
						},
						data:({
							username :user,
							password :this.password1
						}),
						method: 'POST', 
						success: function(res) {
							//console.log(res.data)
							this.show3 = false;
						}.bind(this)
					})
				}else{
					
				}
				
			},
			upload(){
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['camera'], //从相册选择
					// success: function (res) { //用function的方式会找不到send方法
					success: (res) => {
						imgToBase64(res)
						//console.log(JSON.stringify(res.tempFilePaths));
						const filePaths = res.tempFilePaths;
						for (let i = 0; i < filePaths.length; i++) {
							this.send(filePaths[i], 1)
						}
					}
				})
				function imgToBase64(res){
						plus.io.resolveLocalFileSystemURL(res.tempFilePaths[0], function(entry){
							entry.file( function(file){
								let fileReader = new plus.io.FileReader();
								fileReader.readAsDataURL( file );
								fileReader.onloadend = function(evt) {
									let temp = evt.target.result
									
									
									//console.log(evt.target.result,'转换evt');
								}
							});
						})
					};
			},
			get(){
				let user = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/register_patient/SelectPatient',
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username: user
					}),
					method:'GET',
					success: (res) => {
						console.log(res)
						this.scu = res.data[0].image
						this.username = res.data[0].name
						
					//	console.log(res.data[2])
						
					//	console.log(this.columns)
					}
				})
			},
			
			navigateBack() {
				uni.navigateBack()
			},
			open1() {
				this.show1 = true
			},
			open2() {
				this.show2 = true
			},
			open3() {
				this.show3 = true
			},
			close() {
				this.show = false
				// console.log('close');
			},
			exit(){
				uni.redirectTo({
					url:"../index/index"
				})
			}

		}
	}
</script>
 
<style>
	.index-user-img{
		background-color: antiquewhite;
		text-align: center;
		justify-content: center;
	}
	.index-user-content{
		margin-bottom: 50px;
	}
	
	.index-user-content image{
		margin-top: 50px;
		width: 100px;
		height: 100px;
		border-radius: 100%;
	}
	
	.index-user-list{
		display: flex;
		justify-content: center;
	}
	
	.bottom_button{
		margin-top: 100px;
	}
	
	.button1{
		margin-bottom: 20px;
		width: 200px;
	}
	.button2{
		margin-bottom: 20px;
		width: 200px;
	}
	.button3{
		margin-bottom: 20px;
		width: 200px;
	}
	.button4{
		margin-bottom: 20px;
		width: 200px;
	}
	.button5{
		margin-bottom: 20px;
		width: 200px;
	}
	.u-popup-slot {
		width: 200px;
		height: 150px;
		@include flex;
		justify-content: center;
		align-items: center;
	}
	.pop{
		margin: 20px;
	}

	
</style>