<template>
	<view class="main">
		<view class="tittle">
			<view>
				<u-text text="患者:陈乐" size="30"></u-text>
			</view>
			
		</view>
		<view>
			<view class="text3">
						<u-input  inputAlign="right" v-model="list.age">
							<u--text
								text="年龄"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
							
						</u-input>
									
						<u-input  inputAlign="right" v-model="list.sex">
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
						<u-input  inputAlign="right" v-model="list.height">
							<u--text
								text="身高/cm"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
									
						<u-input  inputAlign="right" v-model="list.weight">
							<u--text
								text="体重/kg"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
					</view>
								
								
					<u-input  inputAlign="right" v-model="list.allergy">
						<u--text
							text="过敏史"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
									
					<u-input  inputAlign="right" v-model="list.medicine" >
						<u--text
							text="用药史"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
								
								
								
					<u-input  inputAlign="right" v-model="list.diet">
						<u--text
							text="忌口"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
									
					<u-input  inputAlign="right" v-model="list.illness" >
						<u--text
							text="病史"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
			
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:
					{
						"age":"20",
						"sex":"男",
						"height":"178",
						"weight":"75",
						"allergy":"无",
						"medicine":"无",
						"diet":"无",
						"illness":"无",
					}
				
			}
		},
		methods: {
			rate(){
				uni.switchTab({
					url:"/pages/main/index"
				})
			}
		}
	}
</script>

<style>
	
.main{
	width: 400px;
	margin-left: 45px;
	margin-right: 45px;
}

.tittle{
	margin-top: 150px;
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
	width: 100px;
}

.text3{
		display: flex;
		flex-direction: row;
	}
</style>
