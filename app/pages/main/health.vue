<template>
	<view>
		<view>

			<view class="head">
				<u-picker 
					:show="show" 
					:columns="columns" 			
					@change="change"
					@cancel="cancel"
					@confirm="confirm"
				></u-picker>
				<u-button @click="show = true" text="患者信息" size="large"></u-button>
			</view>
			
			<view>
				<view class="text3">
				<u--text
						:text="text_name"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
						align="center"
						>
						
						</u--text>
					
						<u--text
							text="男"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
							align="center"
						></u--text>
					
				</view>		
			</view>
		</view>
		
		<view class="charts-box">
			<view class="chart01">
				<view class="chart1">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData"
						update=true
					/>
				</view>
				
				<view class="chart2">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData2"
						update=true
					/>
				</view>
				
			</view>
			
			<view class="chart02">
				<view class="chart3">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData3"
						update=true
					/>
				</view>
				<view class="chart4">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData4"
						update=true
					/>
				</view>
			</view>
			<view class="chart03">
				<view class="chart5">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData5"
						update=true
					/>
				</view>
				<view class="chart6">
					<qiun-data-charts
						type="line"
						:opts="opts"
						:chartData="chartData6"
						update=true
					/>
				</view>
			</view>
		 
		</view>
		
		<view class="text">
			<view class="text2">
				<u-input inputAlign="right" v-model.number="health_height">
					<u--text
						text="身高(厘米)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
					
				<u-input  inputAlign="right" v-model.number="health_weight">
					<u--text
						text="体重(千克)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
			</view>
			
			<view class="text2">
				<u-input  inputAlign="right" v-model="health_kpa">
					<u--text
						text="血压(mmHg)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
				
				<u-input  inputAlign="right" v-model="health_heart">
					<u--text
						text="心率(次/分)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
			</view>
			
			<view class="text3">
				<u-input  inputAlign="right" v-model="health_sport">
					<u--text
						text="运动(小时)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
				
				<u-input  inputAlign="right" v-model="health_sleep">
					<u--text
						text="睡眠(小时)"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
			</view>
			
			<view>
				<u-button type="primary"  class="set" text="保存" @click="save"></u-button>
			</view>
		</view>
		
		<view class="content">
			
			<view class="content1">
				<u-text  block:true text="电子病历单" size="30" align="center"></u-text>
			</view>
			<view class="report" v-for="(item, index) in list" :key="index">
			<u-collapse
					@change="change"
					@close="close"
					@open="open"
				>
				<view class="inquiry">
					<view>
						{{item.time}}
					</view>
					
					<view class="inquiry1">
						{{item.illness}}
					</view>
				</view>
				
					<u-collapse-item
						title="查看信息"
						name="item.name"
					>	
									
					<u-input  inputAlign="right" v-model="list_illness.medicine">
						<u--text
							text="用药"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
								
								
								
					<u-input  inputAlign="right" v-model="list_illness.diet">
						<u--text
							text="忌口"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
					
					<u-input  inputAlign="right" v-model="list_illness.illness">
						<u--text
							text="病因"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
					
					<u-input  inputAlign="right" v-model="list_illness.cure">
						<u--text
							text="治疗方案"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
					
					
				</u-collapse-item>
								   
			</u-collapse>  
			</view>
		</view>
		
		<view>
				<u-tabbar
					:value="value"
					
					:fixed="true"
					:placeholder="true"
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
  data() {
	
    return {
		list_illness:{
			"illness":"牙髓发生炎症性病变，产生的牙髓炎导致牙齿疼痛",
			"medicine":"甲硝唑片，阿莫西林胶囊",
			"diet":"忌冷热、辛辣食品",
			"cure":"保存牙髓，稳妥治疗，服用药品一周后复诊",
		},
		value:1,
		health_height:'',
		health_weight:'',
		health_kpa:'',
		health_heart:'',
		health_sport:'',
		health_sleep:'',
		
		text_name:'陈乐',
		
		username:'user1',
		patientname:'陈乐',
		
		disabled: true,
		show: false,
		
		list:[
			// {
			// 	date:"23.03.14",
			// name:"1",
			//  illness:"蛀牙"
			// }
		],
		show: false,
		columns: [[]],
		selecValue: '陈冬',
		chartData:{},
	  
	 
	 //     chartData1: {
	 		  
	 		//   categories: ["2018","2019","2020","2021","2022","2023"],
	 		//   series: [
	 		//     {
	 		//       name: "身高",
	 		//       data: [],
	 		//     },
	 		// ]
	 	 //  },
		 
		chartData1: {},
		chartData2: {},
		chartData3: {},
		chartData4: {},
		chartData5: {},
		chartData6: {},
	  
      //这里的 opts 是图表类型 type="line" 的全部配置参数，您可以将此配置复制到 config-ucharts.js 文件中下标为 ['line'] 的节点中来覆盖全局默认参数。实际应用过程中 opts 只需传入与全局默认参数中不一致的【某一个属性】即可实现同类型的图表显示不同的样式，达到页面简洁的需求。
      opts: {
        timing: "easeOut",
        duration: 1000,
        rotate: false,
        rotateLock: false,
        color: ["#1890FF","#91CB74","#FAC858","#EE6666","#73C0DE","#3CA272","#FC8452","#9A60B4","#ea7ccc"],
        padding: [15,10,0,15],
        fontSize: 13,
        fontColor: "#666666",
        dataLabel: true,
        dataPointShape: true,
        dataPointShapeType: "solid",
        touchMoveLimit: 60,
        enableScroll: false,
        enableMarkLine: false,
        legend: {
          show: true,
          position: "bottom",
          float: "center",
          padding: 5,
          margin: 5,
          backgroundColor: "rgba(0,0,0,0)",
          borderColor: "rgba(0,0,0,0)",
          borderWidth: 0,
          fontSize: 13,
          fontColor: "#666666",
          lineHeight: 11,
          hiddenColor: "#CECECE",
          itemGap: 10
        },
        xAxis: {
          disableGrid: true,
          disabled: false,
          axisLine: true,
          axisLineColor: "#CCCCCC",
          calibration: false,
          fontColor: "#666666",
          fontSize: 13,
          lineHeight: 20,
          marginTop: 0,
          rotateLabel: false,
          rotateAngle: 45,
          itemCount: 5,
          boundaryGap: "center",
          splitNumber: 5,
          gridColor: "#CCCCCC",
          gridType: "solid",
          dashLength: 4,
          gridEval: 1,
          scrollShow: false,
          scrollAlign: "left",
          scrollColor: "#A6A6A6",
          scrollBackgroundColor: "#EFEBEF",
          title: "",
          titleFontSize: 13,
          titleOffsetY: 0,
          titleOffsetX: 0,
          titleFontColor: "#666666",
          format: ""
        },
        yAxis: {
          gridType: "dash",
          dashLength: 2,
          disabled: false,
          disableGrid: false,
          splitNumber: 5,
          gridColor: "#CCCCCC",
          padding: 10,
          showTitle: false,
          data: []
        },
        extra: {
          line: {
            type: "straight",
            width: 2,
            activeType: "hollow",
            linearType: "none",
            onShadow: false,
            animation: "vertical"
          },
          tooltip: {
            showBox: true,
            showArrow: true,
            showCategory: false,
            borderWidth: 0,
            borderRadius: 0,
            borderColor: "#000000",
            borderOpacity: 0.7,
            bgColor: "#000000",
            bgOpacity: 0.7,
            gridType: "solid",
            dashLength: 4,
            gridColor: "#CCCCCC",
            boxPadding: 3,
            fontSize: 13,
            lineHeight: 20,
            fontColor: "#FFFFFF",
            legendShow: true,
            legendShape: "auto",
            splitLine: true,
            horizentalLine: false,
            xAxisLabel: false,
            yAxisLabel: false,
            labelBgColor: "#FFFFFF",
            labelBgOpacity: 0.7,
            labelFontColor: "#666666"
          },
          markLine: {
            type: "solid",
            dashLength: 4,
            data: []
          }
        }
      }
    };
  },
 onLoad(){
 	uni.hideTabBar()
 },
  onReady() {
 //   this.getServerData();
	this.getdata()
	this.getdata_picker()
	this.getdata_report()
  },
  
  methods: {
		getdata_report(){
			uni.request({
						url:'http://10.0.2.2:8080/illness/SelectIllness',
						header: {
					 		"Content-Type": "application/json"
					 	},
					data:({
						username: 'user1',
				  	}),
				  	method: 'GET', 
				  	success: (res) => {
						console.log(res.data)
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
						//	console.log(result); //2023-04-18
						}
						console.log(this.list)
					}
				  	})
		
		},
	  	//下拉框相关方法
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
		close() {
	  		// console.log('close');
	  		this.show = false
	  	},
	  	confirm(e) {
		//	console.log('confirm', e.value),
	  		this.show = false,
			this.text_name = e.value[0]
			//console.log(e.value)
			uni.request({
					url:'http://10.0.2.2:8080/Health/SelectHealthByUserName',
					header: {
				 		"Content-Type": "application/json"
				 	},
				data:({
					username: this.username,
					patient: e.value
			  	}),
			  	method: 'GET', 
			  	success: (res) => {
			//		console.log(res)
			//		console.log(res.data[0].time)
					let count = res.data.length
					let time0 = res.data.map(item => item.time)
					let height = res.data.map(item => item.height)
					let weight = res.data.map(item => item.weight)
					let kpa = res.data.map(item => item.kpa)
					let heart = res.data.map(item => item.heartbeat)
					let sport = res.data.map(item => item.sports)
					let sleep = res.data.map(item => item.sleep)
					
					let time=[]
					
					for(let i=0;i<count;i++){
						let date = new Date(time0[i]);
						// 使用Date对象的方法获取年月日
						// let year = date.getFullYear(); //获取年份
						let month = date.getMonth() + 1; //获取月份，注意要加1
						let day = date.getDate(); //获取日期
						// 拼接成字符串，并补0
						//let result = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
						let result = (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
						console.log(result); //2023-04-18
						time.push(result)
					}
					
					
				//	console.log(time)
					let res1={
						categories:time,
						series: [
							{
								name: "身高(厘米)",
								data: height
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					let res2={
						categories:time,
						series: [
							{
								name: "体重（千克）",
								data: weight
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					let res3={
						categories:time,
						series: [
							{
								name: "血压（mmHg）",
								data: kpa
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					let res4={
						categories:time,
						series: [
							{
								name: "心跳（次/分）",
								data: heart
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					let res5={
						categories:time,
						series: [
							{
								name: "运动（小时/周）",
								data: sport
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					let res6={
						categories:time,
						series: [
							{
								name: "睡眠（小时/天）",
								data: sleep
						        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
						    }
						]
					}
					this.chartData = JSON.parse(JSON.stringify(res1));
					this.chartData2 = JSON.parse(JSON.stringify(res2));
					this.chartData3 = JSON.parse(JSON.stringify(res3));
					this.chartData4 = JSON.parse(JSON.stringify(res4));
					this.chartData5 = JSON.parse(JSON.stringify(res5));
					this.chartData6 = JSON.parse(JSON.stringify(res6));
					
					this.getdata();
				}
			  	})
	  	},
		change(){},
		cancel() {
			// console.log('cancel');
			this.show = false
		},
		
		getdata_picker(){
			uni.request({
				url:'http://10.0.2.2:8080/Health/SelectPatientByUserName',
				header: {
					"Content-Type": "application/json"
				},
				data:({
					username: "user1"
				}),
				method:'GET',
				success: (res) => {
				//	console.log(res)
				//	console.log(res.data[2])
					let obj = res.data
					let arr = Object.values(obj)
					let count = arr.length
				//	console.log(arr[2])
					for(let j=0;j<count;j++){
						this.columns[0].push(arr[j]);
					}
				//	console.log(this.columns)
				}
			})
		},
		
		//折线图数据
		getdata(){
			uni.request({
				url:'http://10.0.2.2:8080/Health/SelectHealthByUserName',
				header: {
			 		"Content-Type": "application/json"
			 	},
			data:({
				username: this.username,
				patient: this.patientname
		  	}),
		  	method: 'GET', 
		  	success: (res) => {
		//		console.log(res)
		//		console.log(res.data[0].time)
				let count = res.data.length
				let time0 = res.data.map(item => item.time)
				let height = res.data.map(item => item.height)
				let weight = res.data.map(item => item.weight)
				let kpa = res.data.map(item => item.kpa)
				let heart = res.data.map(item => item.heartbeat)
				let sport = res.data.map(item => item.sports)
				let sleep = res.data.map(item => item.sleep)
				
				
				let time=[]
				
				for(let i=0;i<count;i++){
					let date = new Date(time0[i]);
					// 使用Date对象的方法获取年月日
					// let year = date.getFullYear(); //获取年份
					let month = date.getMonth() + 1; //获取月份，注意要加1
					let day = date.getDate(); //获取日期
					// 拼接成字符串，并补0
					let result = (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
				//	console.log(result); //2023-04-18
					time.push(result)
				}
				
				
			//	console.log(time)
				let res1={
					categories:time,
					series: [
						{
							name: "身高(厘米)",
							data: height
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				let res2={
					categories:time,
					series: [
						{
							name: "体重（千克）",
							data: weight
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				let res3={
					categories:time,
					series: [
						{
							name: "血压（mmHg）",
							data: kpa
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				let res4={
					categories:time,
					series: [
						{
							name: "心跳（次/分）",
							data: heart
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				let res5={
					categories:time,
					series: [
						{
							name: "运动（小时/周）",
							data: sport
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				let res6={
					categories:time,
					series: [
						{
							name: "睡眠（小时/天）",
							data: sleep
					        // data: [res.data[0].height,res.data[1].height,res.data[2].height,res.data[3].height,res.data[4].height]
					    }
					]
				}
				this.chartData = JSON.parse(JSON.stringify(res1));
				this.chartData2 = JSON.parse(JSON.stringify(res2));
				this.chartData3 = JSON.parse(JSON.stringify(res3));
				this.chartData4 = JSON.parse(JSON.stringify(res4));
				this.chartData5 = JSON.parse(JSON.stringify(res5));
				this.chartData6 = JSON.parse(JSON.stringify(res6));
			}
		  	})
		  },
		
	  save(){
		  let date = new Date();
		  uni.request({
			url:'http://10.0.2.2:8080/Health/InsertHealth',
			header: {
			 		"Content-Type": "application/json"
			 	},
			data:JSON.stringify({
				username: this.username,
				patient: this.patientname,
				height: this.health_height,
				weight: this.health_weight,
				kpa: this.health_kpa,
				heart: this.health_heart,
				sport: this.health_sport,
				sleep: this.health_sleep,
				time: date
			}),
			method: 'POST', 
			success: 
				function (res) {
					console.log(res)
					
				}
			})
	  },
	  
	  open1() {
	           // console.log('open');
	         },
		close1() {
	           this.show = false
	           // console.log('close');
	         },
	  open(e) {
	      // console.log('open', e)
	  },
	  close(e) {
	      // console.log('close', e)
	  },
	  change(e) {
	    // console.log('change', e)
	  },
	  selectOne(options) {
	              this.selecValue = options.label
	          },
	          useOutClickSide() {
	              this.$refs.easySelect.hideOptions && this.$refs.easySelect.hideOptions()
	          }
  //   getServerData() {
  //     //模拟从服务器获取数据时的延时
  //     setTimeout(() => {
  //       //模拟服务器返回数据，如果数据格式和标准格式不同，需自行按下面的格式拼接
  //       let res = {
  //           categories: ["2018","2019","2020","2021","2022","2023"],
  //           series: [
  //             {
  //               name: "身高",
  //               data: [172,173,172,172,172,172]
  //             },
              
  //           ]
  //         };
  //       this.chartData1 = JSON.parse(JSON.stringify(res));
  //     }, 500);
  //   },
   }
};
</script>

<style scoped>
	
	.head{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.head1{
		display: flex;
		text-align: center;
		width: 250px;
	}
	.content{
		margin-top: 20px;
		margin-left: 5px;
		margin-right: 5px;
		display: flex;
		flex-wrap: wrap;
		text-align: center;
		
		/* background: #000000; */
		border: 1px solid;
	}
	
	
  /* 请根据实际需求修改父元素尺寸，组件自动识别宽高 */
  .charts-box {
    width: 100%;
    height: 300px;
  }
	.text{
		margin-top: 20px;
	}
	.text1{
		display: flex;
		flex-direction: row;
	}
	
	.report{
		width: 100%;
	}
  
	.text2{
		display: flex;
		flex-direction: row;
	}
	
	.text3{
		display: flex;
		flex-direction: row;
	}
	
	.set{
		margin-top: 20px;
		width: 300px;
	}
	
	.chart01{
		display: flex;
		flex-direction: row;
	}
	
	.chart02{
		display: flex;
		flex-direction: row;
	}
	
	.chart03{
		display: flex;
		flex-direction: row;
	}
	.chart1{
		width: 50%;
		height: 100px;
	}
	.chart2{
		width: 50%;
		height: 100px;
	}
	.chart3{
		width: 50%;
		height: 100px;
	}
	.chart4{
		width: 50%;
		height: 100px;
	}
	.chart5{
		width: 50%;
		height: 100px;
	}
	.chart6{
		width: 50%;
		height: 100px;
	}
	
	.inquiry{
		display: flex;
		flex-direction: row;
		
	}
	
	.inquiry1{
		display: flex;
		margin-left: 20px;
	}
	
	.content1{
		width: 100%;
	}
</style>