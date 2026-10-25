<template>
	<view class="index">
		<view>
		      <u-notice-bar :text="text1" font-size="20"></u-notice-bar>
		</view>
		<view class="on">
			<view class="on_button">
				<u-button type="primary" :plain="true" text="科室" shape="circle" size = "normal" @tap="change"></u-button>
				<u-picker :show="show" ref="uPicker" :columns="columns" @cancel="cancel" @confirm="confirm" @change="changeHandler"></u-picker>
			</view>
		</view>
		<view class="top">
			<view class="search">
				<view class="form">
					<u--input
						placeholder="请输入医生姓名"
						prefixIcon="search"
						prefixIconStyle="font-size: 20px;
						color: #909399"
						shape = "circle"
						v-model="value"
					></u--input>
				</view>
				
				<view class="button">
					<u-button type="primary" :plain="true" text="搜索" size = "normal" @tap="search"></u-button>
				</view>
			</view>
		</view>
		
		<view class="new_box" style="margin-top: 20upx;">
			<view class="bbox">
				<view class="list-box" v-for="(item,index) in list" :key="index" @click="selectDoctor(item.name)">
					<view class="list-ed">
						<image class="list-img" :src="src" mode="aspectFill" ></image>
						<view class="list-right">
							<view class="list-name">
								<view class="list">
									{{item.name}} 
								</view>
								
							</view>
							<view class="list-da">
								<view class="list-da1">
									<view>科室：<text>{{item.department}}</text></view>
									<view>职称：<text>{{item.level}}</text></view>
									<view>擅长：<text>{{item.genius}}</text></view>
								</view>
								<view class="list-da2">
									<view>响应时间：<text>{{item.time}}</text></view>
									<view>好评率：<text>{{item.rate}}</text></view>
								</view>
								
								<u-popup :show="show1" @close="close1" @open="open1" mode="center">
									<view class="pop">
											<u--text
												text="请选择就诊患者"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
											
											<view class="list-box" v-for="(item,index) in list_patient" :key="index" @click="show1 = true">
											
												 <view>
													 <u-button type="primary" text="确认" @click="selectPatient(item)" >{{item}}</u-button>
												 </view>
												 
											</view>
										
										<view class="pop-button">
											 <u-button type="primary" text="确认" @click="chat"></u-button>
											 <u-button type="primary" text="取消" @click="show1=false"></u-button>
										</view>
									</view>
								</u-popup>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				selecValue: '小陈',
				show1:false,
				value:'',
				src:"../../static/doctor2.png",
				text1: '欢迎您，xxx，今天是xxxxxx',
				list:[],
				list1: [{
						course_id: "001",
						doctor:"第一人民医院",
						name: "陈山",
						department: "骨伤科",
						level:"副主任医师",
						genius:"腰椎病、脊柱炎",
						rate:"90%",
						time:"5min",
					},
					{
						course_id: "001",
						name: "李政",
						doctor:"第二人民医院",
						department: "外科",
						level:"医师",
						genius:"甲状腺肿瘤",
						rate:"95%",
						time:"1min45s",
					},
					{
						course_id: "001",
						name: "王海",
						doctor:"协和医院",
						department: "心内科",
						level:"主任医师",
						genius:"心律失常",
						rate:"70%",
						time:"6min",
					},
					{
						course_id: "001",
						name: "张泓泽",
						doctor:"第一人民医院",
						department: "内分泌科",
						level:"医师",
						genius:"肾小球疾病",
						rate:"75%",
						time:"7min",
					},
					{
						course_id: "001",
						name: "于浩",
						doctor:"第三人民医院",
						department: "外科",
						level:"副主任医师",
						genius:"皮肤病",
						rate:"96%",
						time:"6min",
					}
					
				],
				list_patient:[],
				
				selectId:[],
				show: false,
				    columns: [
						["全部医院"],
						["全部科室"]
					],
				    columnData: [
						
				    ]
			};
		},
		onReady(){
			this.getDate();
			this.doctor_select();
			this.getDepartment();
			
		},
		onLoad() {
			this.geiPatient();
		},
		methods: {
			selectDoctor(doctor){
			//	console.log(doctor)
				
				uni.setStorageSync("doctor",doctor)
				this.show1 = true
			},
			selectPatient(item){
				uni.navigateTo({
					 url:"../inquiry/doctor_index"
				})
				uni.setStorageSync("patient",item)
			},
			geiPatient(){
				let temp = uni.getStorageSync("username")
				uni.request({
						url:'http://10.0.2.2:8080/patient/getPatient', 
						header: {
							"Content-Type": "application/json"
						},
						data:({
							username: temp
						}),
						method: 'GET', 
						success: (res) => {
						//console.log(res.data)
						let length = res.data.length
						for(let i=0;i<length;i++){
							this.list_patient.push(res.data[i])
						}	
						//console.log(this.list_patient)
						}
					})
			},
			doctor_select(){
//				console.log("open"),
				uni.request({
					url:'http://10.0.2.2:8080/register_doctor/SelectAllDoctor', 
					//安卓模拟器的本机地址，等同于127.0.0.1
					data:'',
					method:"GET",
					success: (res) => { //如果访问接口成功就会进入success
					//console.log("接口调用成功")		
						//console.log(res.data)
						let list_temp=res.data
						let length = list_temp.length
						for(let i=0;i<length;i++){
							let temp = list_temp[i]
							//console.log(typeof(temp))
							//console.log(temp)
							uni.request({
								url:'http://10.0.2.2:8080/register_doctor/SelectDoctor', 
								header: {
									"Content-Type": "application/json"
								},
								data:({
									username: temp
								}),
								method: 'GET', 
								success: (res) => {
								console.log(res.data)
								let length = res.data.length
								for(let i=0;i<length;i++){
									this.list.push(res.data[i])
								}	
								}
							})
						}
						
					}
				})
				
			//	console.log(this.list)
				
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
							this.columnData.push(["全部科室"])
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
			chat(){
				uni.navigateTo({
					 url:"../inquiry/doctor_index"
				})
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
		height: 180upx;
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
		-webkit-box-orient: vertical;
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
	
</style>