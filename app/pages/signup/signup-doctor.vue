<template>
	<view class="main">
		<u--form :model="form" ref="uForm" >
			<u-form-item label="用户名" prop="username" label-width="150">
				<u-input v-model="form.username" placeholder="请输入用户名" />
			</u-form-item>
		
			<u-form-item label="密码" prop="password" label-width="150">
				<u-input v-model="form.password" type="password" placeholder="请输入密码" />
			</u-form-item>
			
			<u-form-item label="密码" prop="password" label-width="150">
				<u-input v-model="form.password0" type="password" placeholder="请确认密码" />
				
			</u-form-item>
			
			<u-form-item label="联系方式" label-width="150">
				<u-input v-model="form.connect" placeholder="请输入联系方式" />
			</u-form-item>
			
			<u-form-item label="身份证后六位" label-width="150">
				<u-input v-model="form.id" placeholder="请输入居民身份证" />
			</u-form-item>
			
			<u-form-item label="请选择医院" prop="password" label-width="150">
				<u-button class="hospital_button" type="normal" :text="hospital" @click="show_hospital=true"></u-button>
				<u-picker :show="show_hospital" :columns="columns_hospital" @cancel="cancel" @confirm="confirm_hospital" @change="change"></u-picker>
			</u-form-item>
			
			<u-form-item label="请选择科室" prop="password" label-width="150">
				<u-button class="hospital_button" type="normal" :text="department" @click="show_department=true"></u-button>
				<u-picker :show="show_department" :columns="columns_department" @cancel="cancel" @confirm="confirm_department" @change="change"></u-picker>
			</u-form-item>
			
			<u-form-item label="请选择职称" prop="password" label-width="150">
				<u-button class="hospital_button" type="normal" :text="level" @click="show_level=true"></u-button>
				<u-picker :show="show_level" :columns="columns_level" @cancel="cancel" @confirm="confirm_level" @change="change"></u-picker>
			</u-form-item>
			
		</u--form>
		
		<view>
				<u-button type="primary" text="注册" @click="submit"></u-button>
		</view>
		
		<u-popup :show="show" mode="center"  @close="close" >
		       <view class="u-alert-item">
		       	<u-alert
		       		description="两次密码不一致,请重新输入"
		       		type="error"
		       		showIcon
		       	></u-alert>
		       </view>
			</u-popup>
		
		

	</view>
</template>

<script>
	export default {
		data() {
			return {
				hospital:'医院',
				department:'科室',
				level:'职称',
				
				show:false,
				
				show_hospital: false,
				show_department:false,
				show_level:false,
				
				columns_hospital: [
				    []
				],
				columns_department: [
				    []
				],
				columns_level: [
				    []
				],
				form: {
					username: '',
					password: '',
					id:'',
					password0:'',
					connect:'',
					hospital:'',
					department:'',
					level:''
				},
				loading: true,
			}
		},
		onLoad(){
				this.getInformation();
		},
		
		methods: {
			getInformation(){
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
											this.columns_hospital[0].push(list2[i])
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
									for(let i=0;i<length;i++){
										this.columns_department[0].push(list2[i])
									}
								//	console.log(this.columns[0])
				},
				fail:(err)=>{ //如果访问接口失败就会进入fail	
					console.log("调用接口失败"),
					this.msg = err
				}
			}),
			uni.request({
				url:'http://10.0.2.2:8080/department/selectLevel',
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
										this.columns_level[0].push(list2[i])
									}
								//	console.log(this.columns[0])
				},
				fail:(err)=>{ //如果访问接口失败就会进入fail	
					console.log("调用接口失败"),
					this.msg = err
				}
			})
			},
			change(e) {
				// console.log('change', e);
			},
			confirm_hospital(e) {
			//	console.log('confirm', e);
				this.hospital = e.value[0]
				this.form.hospital = e.value[0]
				this.show_hospital = false
			},
			confirm_department(e) {
			//	console.log('confirm', e);
				this.department = e.value[0]
				this.form.department = e.value[0]
				this.show_department = false
			},
			confirm_level(e) {
			//	console.log('confirm', e);
			this.form.level = e.value[0]
				this.level = e.value[0]
				this.show_level = false
			},
			cancel() {
				// console.log('cancel');
				this.show_level = false
				this.show_department = false
				this.show_hospital = false
			},
			submit(){
				if(this.form.password==this.form.password0){
					uni.request({
					 url:'http://10.0.2.2:8080/register_doctor/InsertDoctor',
					  data: {
					    user_name: this.form.username, 
						password:this.form.password,
						usertype:2,
						id:this.form.id,
						connect:this.form.connect,
						department:this.form.department,
						level:this.form.level,
						hospital:this.form.hospital
					  },
					  method: 'POST',
					  header: {
					    'Content-Type': 'application/json' //设置请求头，根据你的后端要求
					  },
					  success: (res) => {
					    console.log(res.data); //打印后端返回的数据
					  },
					  fail: (err) => {
					    console.error(err); //打印错误信息
					  }
					});
				}else{
					console.log("密码不一致");
					this.show = true
				}
				// uni.redirectTo({
				// 	url:"../index/index"
				// }),
				

			},
			close() {
				this.show = false
				// console.log('close');
			}
		}
	}
</script>

<style>
	
	.main{
		margin-top: 100px;
		margin-left: 80px;
		margin-right: 80px;
	}
	
	.regigster{
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.content{
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	
	.hospital_button{
		border: 1px;
	}
</style>
