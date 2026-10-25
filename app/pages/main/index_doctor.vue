<template>
	<view class="index">
		<view>
		      <u-notice-bar :text="text1" font-size="20"></u-notice-bar>
		</view>
		
		<view class="new_box" style="margin-top: 20upx;">
			<view class="bbox">
				<view class="list-box" v-for="(item,index) in list" :key="index" @click="openup(index)">
					<u-popup :show="show1" @close="close1" @open="open1" mode="center">
						<view class="pop">
							<view class="tittle">
								<view>
									<u-text text="问诊报告" size="30"></u-text>
								</view>
								
							</view>
							<view>
									<view class="text3">
										<u-input  inputAlign="right" v-model="name">
											<u--text
												text="姓名"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
										</u-input>
													
										<u-input  inputAlign="right" v-model="sex">
											<u--text
												text="性别"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
										</u-input>
									</view>
												
									<view class="text3">
										<u-input  inputAlign="right" v-model="height">
											<u--text
												text="身高"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
											{{item.height}}
										</u-input>
													
										<u-input  inputAlign="right" v-model="weight">
											<u--text
												text="体重"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
										</u-input>
									</view>
												
									<u-input  inputAlign="right" v-model="reason">
										<u--text
											text="病因"
											slot="prefix"
											margin="0 3px 0 0"
											type="tips"
											bold:true
											size="30"
										></u--text>
										{{item.medicine}}
									</u-input>	
									
													
									<u-input  inputAlign="right" v-model="medicine">
										<u--text
											text="用药"
											slot="prefix"
											margin="0 3px 0 0"
											type="tips"
											bold:true
											size="30"
										></u--text>
										{{item.medicine}}
									</u-input>
												
												
												
									<u-input  inputAlign="right" v-model="diet">
										<u--text
											text="忌口"
											slot="prefix"
											margin="0 3px 0 0"
											type="tips"
											bold:true
											size="30"
										></u--text>
									</u-input>
								
									<u-input  inputAlign="right" v-model="cure">
										<u--text
											text="治疗方案"
											slot="prefix"
											margin="0 3px 0 0"
											type="tips"
											bold:true
											size="30"
										></u--text>
									</u-input>
									
							</view>
							<view>
								<u-button @click="show1=false"> 关闭</u-button>
								<u-button @click="chat()"> 查看记录</u-button>
							</view>
							
						</view>
						
						
						
					</u-popup>
					<view class="list-ed">
						<view class="list-right">
							<view class="list-name">{{item.name}}
								<u-badge :isDot="true" :show = "item.show" type="error"></u-badge>
								{{item.id}}
							</view>
							<view class="list-da">
								<view class="list-da1">
									<view>病症：<text>{{item.illness}}</text></view>
								</view>
								<view class="list-da2">
									<view>问诊时间：<text>{{item.time}}</text></view>
								</view>
								
							</view>
						</view>
					</view>
				</view>
				
			</view>
		</view>
		<view>
				<u-tabbar
					:value="value"
					:fixed="true"
					:placeholder="false"
					activeColor="#d81e06"
					:safeAreaInsetBottom="false"
				>
					
					<u-tabbar-item text="问诊记录" icon="home" @click = 'click' ></u-tabbar-item>
					<u-tabbar-item text="健康档案" icon="photo" @click = 'click'></u-tabbar-item>
					<u-tabbar-item text="我的" icon="account" @click = 'click'></u-tabbar-item>
					
				</u-tabbar>
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				illid:0,
				name:'',
				sex:'',
				height:'',
				weight:'',
				reason:'',
				medicine:'',
				diet:'',
				cure:'',
				temp_user:'',
				show2:true,
				type:"warning",
				value:100,
				value:0,
				selecValue: '小陈',
				show1:false,
				src:"../../static/logo.png",
				text1: '欢迎您，xxx，今天是xxxxxx',
				list:[],
				list1: [{
						course_id: "001",
						title: "陈医生",
						course: "外科",
						level:"副主任医师",
						good_at:"皮肤病",
						rate:"90%",
						time:"90s",
						src : "../../static/logo.png"
					},
					{
						course_id: "002",
						title: "王医生",
						course: "内科",
						level:"主治医师",
						src : "../../static/logo.png"
					},
					{
						course_id: "003",
						title: "王医生",
						course: "脑科",
						level:"主任医师",
						src : "../../static/logo.png"
					},
					{
						course_id: "004",
						title: "刘医生",
						course: "脑科",
						level:"医师",
						src : "../../static/logo.png"
					},
					
				],
				doctor:'',
				selectId:[],
				show: false,
				    columns: [
						[],
						[]
					],
				    columnData: [
				    ]
			};
		},
		onReady(){
			this.getDate();
			this.getDepartment();
			this.getdata_report()
		},
		onLoad(){
			uni.hideTabBar()
		},
		methods: {
			chat(e){
				//console.log("sds"+e)
				// uni.setStorageSync("illid",e)
				// uni.setStorageSync("us",a)
				uni.navigateTo({
					url:"/pages/chat/index"
				})
				
			},
			
			getdata_report(){
				let name = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/register_doctor/SelectDoctor', 
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username: name
					}),
					method: 'GET', 
					success:function(res){
						let a  = res.data[0].name
						this.doctor = a
						uni.request({
								url:'http://10.0.2.2:8080/illness/SelectIllnessByDoctor',
								header: {
									"Content-Type": "application/json"
								},
							data:({
								username: this.doctor,
							}),
							 	method: 'GET', 
							 	success: (res) => {
									this.list = res.data
									let count = res.data.length
									
									for(let i=0;i<count;i++){
										let time=this.list[i].time; 
										let date = new Date(time);
										// 使用Date对象的方法获取年月日
										let year = date.getFullYear(); //获取年份
										let month = date.getMonth() + 1; //获取月份，注意要加1
										let day = date.getDate(); //获取日期
										// 拼接成字符串，并补0
										let result = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
										this.list[i].time = result;
										this.list[i].show = true;
										if(this.list[i].end == 1){
											this.list[i].show = false
										};
										const self = this
										uni.request({
											url:'http://10.0.2.2:8080/Health/GetPatient',
											header: {
											 	"Content-Type": "application/json"
											},
											data:({
												username:this.list[i].user_name,
												name:this.list[i].name
											}),
											method: 'GET', 
											success: function(res){
												self.list[i].sex = res.data[0].sex
												self.list[i].height = res.data[0].height
												self.list[i].weight = res.data[0].weight
												console.log(self.list[i].user_name)
												this.temp_user = self.list[i].user_name
											}
										})
									//	console.log(result); //2023-04-18
									}
									console.log(this.list)
								}
							  	})
									
					}.bind(this)
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
			getDepartment(){
					uni.request({
				  	url:'http://10.0.2.2:8080/department/selectHospital',
				 	//安卓模拟器的本机地址，等同于127.0.0.1
				  	method:"GET",
				  	success: (res) => { //如果访问接口成功就会进入success
				  	//	console.log(res.data)
				  	//	console.log("接口调用成功")
						let list2 = res.data
						//console.log(res.data)
						//console.log(list2)
						let length = list2.length
						for(let i=0;i<length;i++){
							this.columns[0].push(list2[i])
						}
					//	console.log(this.columns[0])
				  	},
				  	fail:(err)=>{ //如果访问接口失败就会进入fail	
				  		console.log("调用接口失败"),
				  		this.msg = err
				  	}
				  }),
				  uni.request({
				   	url:'http://10.0.2.2:8080/department/selectDepartment',
				  	//安卓模拟器的本机地址，等同于127.0.0.1
				   	method:"GET",
				   	success: (res) => { //如果访问接口成功就会进入success
				   	//	console.log(res.data)
				   	//	console.log("接口调用成功")
				  		let list2 = res.data
				  		//console.log(res.data)
				  		//console.log(list2)
				  		let length = list2.length
						let length0 = this.columns[0].length
						let list_temp = this.columns[1]
						for(let j=0;j<length0;j++){
							this.columnData.push([])
							for(let i=0;i<length;i++){
								this.columnData[j].push(list2[i])	
							}
						}
				  		for(let i=0;i<length;i++){
				  			this.columns[1].push(list2[i])	
				  		}
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
			openup(e){
				this.show1=true
				this.illid = this.list[e].id
				this.reason =  this.list[e].reason
				this.name = this.list[e].name
				this.sex =  this.list[e].sex
				this.height =  this.list[e].height
				this.weight =  this.list[e].weight
				this.medicine =  this.list[e].medicine
				this.diet =  this.list[e].diet
				this.cure =  this.list[e].cure
				
				uni.setStorageSync("illid",this.list[e].id)
				uni.setStorageSync("us",this.list[e].user_name)
				
				console.log("sdsasdasdd"+this.list[e].user_name)
			},
			open1() {
			         // console.log('open');
			},
			close1() {
				this.show1 = false
			// console.log('close');
			},
			clearData(){
				this.list=[];
			},
			search(){
				uni.request({
				 	url:'http://10.0.2.2:8080/register_doctor/SelectDoctorName'+`?name=${this.value}`,
					//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"GET",
					header: {
						"Content-Type": "application/json"
					},
					data:'',
				 	success: (res) => { //如果访问接口成功就会进入success
						console.log(res.data)
					//	console.log("接口调用成功")
						this.clearData();
						let length = res.data.length
						for(let i=0;i<length;i++){
							uni.request({
										url:'http://10.0.2.2:8080/register_doctor/SelectDoctor', 
										header: {
											"Content-Type": "application/json"
										},
										data:({
											username: res.data[i]
										}),
										method: 'GET', 
										success: (res) => {
							//			console.log(res.data)
										let length = res.data.length
										for(let i=0;i<length;i++){
											this.list.push(res.data[i])
										}	
										}
									})
						}
						
				 	},
				 	fail:(err)=>{ //如果访问接口失败就会进入fail	
				 		console.log("调用接口失败"),
				 		this.msg = err
				 	}
				 })
				// uni.redirectTo({
				// 	url:"/pages/doctor/doctor_select"
				// })
			},
			selectOne(options) {
			            this.selecValue = options.label
			        },
			        useOutClickSide() {
			            this.$refs.easySelect.hideOptions && this.$refs.easySelect.hideOptions()
			        },
			
			change(e) {
				this.show = true
			},

			changeHandler(e) {
			    const {
			        columnIndex,
			        value,
			        values, // values为当前变化列的数组内容
					index,
					// 微信小程序无法将picker实例传出来，只能通过ref操作
			        picker = this.$refs.uPicker
			    } = e
			    // 当第一列值发生变化时，变化第二列(后一列)对应的选项
			    if (columnIndex === 0) {
			    // picker为选择器this实例，变化第二列对应的选项
			        picker.setColumnValues(1, this.columnData[index])
			    }
			},
				// 回调参数为包含columnIndex、value、values
			confirm(e) {
		    //  console.log('confirm', e)
				this.show = false
			},
			cancel() {
				// console.log('cancel');	
				this.show = false
			}

		
		}
	};
</script>
 
<style>
	page{
		background-color: #eee;
	}
	
	.on{
		
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	
	.on_button{
		margin-left: 25%;
		margin-right: 25%;
		
		width:200px;
	}
	.selde-q {
		
		width: 18upx;
		height: 18upx;
		border-radius: 50%;
		background: #ffffff;
		margin-left: 6upx;
	}
 
	.noselde-q {
		
		border: 1px solid #959595;
		width: 16upx;
		height: 16upx;
		border-radius: 50%;
		background: #FFFFFF;
		margin-left: 6upx;
	}
	.list-box {
		
		display: flex;
		flex-direction: column;
		background-color: #fff;
		margin: 0upx 16upx 16upx 16upx;
		padding: 16upx;
		border-radius: 10upx;
	}
 
	.list-ed {
		font-size: 40rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
 
	.list-left {
		margin-right: 16upx;
	}
 
	.list-img {
		
		width: 160upx;
		height: 160upx;
		margin: 0upx 16upx 0upx 0upx;
	}
 
	.list-right {
		
		display: flex;
		flex-direction: column;
		width: 510upx;
		height: 110upx;
	}
 
	.list-right-img {
		width: 140upx;
		height: 38upx;
	}
 
	.noadsop {
		width: 120upx;
		height: 32upx;
	}
 
	.list-head {
		
		display: flex;
		justify-content: flex-end;
		margin-bottom: 10upx;
	}
 
	.list-name {
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		margin-bottom: 10upx;
	}
 
	.list-da {
		
		display: flex;
		flex-direction: row;
		font-size: 26upx;
	}

 
	.sure {
		
		background: #FF6000;
		color: #FFFFFF;
		width: 600upx;
		height: 70upx;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 40upx;
		margin: 60upx auto;
	}
	
	.search{
		margin-top: 20px;
		display: flex;
		flex-direction: row;
		justify-content: center;
		
	}
	
	.list-da1{
		width: 300px;
	}
	
	.list-da2{
		width: 300px;
	}
	
	.pop-button{
		display: flex;
		flex-direction: row;
		
	}
	.pop{
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	
	.tittle{
		
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.table{
		
		margin-left: 50px;
		margin-right: 50px;
	}
	
	.button{
		margin-top: 20px;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		
		width: 100%;
	}
	
	.button1{
		width: 120px;
	}
	
	.text3{
			display: flex;
			flex-direction: row;
		}
	
</style>