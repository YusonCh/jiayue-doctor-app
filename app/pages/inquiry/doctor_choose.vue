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
		
		<view class="new_box" style="margin-top: 20upx;">
			<view class="bbox">
				<view class="list-box" v-for="(item,index) in list" :key="index" @click="show1 = true">
					<view class="list-ed">
						<image class="list-img" :src="item.src" mode="aspectFill"></image>
						<view class="list-right">
							<view class="list-name">{{item.title}}</view>
							<view class="list-da">
								<view class="list-da1">
									<view>科室：<text>{{item.course}}</text></view>
									<view>职称：<text>{{item.level}}</text></view>
									<view>擅长：<text>{{item.good_at}}</text></view>
								</view>
								<view class="list-da2">
									<view>响应时间：<text>{{item.rate}}</text></view>
									<view>好评率：<text>{{item.time}}</text></view>
								</view>
								
								<u-popup :show="show1" @close="close1" @open="open1" mode="center">
									<view class="pop">
										<u-input  inputAlign="right">
											<u--text
												text="请选择就诊患者"
												slot="prefix"
												margin="0 3px 0 0"
												type="tips"
												bold:true
												size="30"
											></u--text>
																				
																				
										</u-input>
										
										<view  class="content2" @click="useOutClickSide">
											 <easy-select ref="easySelect" size="medium" :value="selecValue" @selectOne="selectOne"></easy-select>
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
				text1: '欢迎您，xxx，今天是xxxxxx',
				list: [{
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
				
				selectId:[],
				show: false,
				    columns: [
						['中国', '美国'],
						['深圳', '厦门', '上海', '拉萨']
					],
				    columnData: [
				        ['深圳', '厦门', '上海', '拉萨'],
				        ['得州', '华盛顿', '纽约', '阿拉斯加']
				    ]
			};
		},
		methods: {
			open1() {
			         // console.log('open');
			},
			close1() {
				this.show1 = false
			// console.log('close');
			},
			search(){
				uni.redirectTo({
					url:"/pages/doctor/doctor_select"
				})
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