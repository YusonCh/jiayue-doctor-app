<template>
	<view class="qy-select-ep">
		<view class="mask" v-if="active" @click.stop="maskClick"></view>
		<view class="qy-select-ep-select" :class="{ active: active }">
			<!-- 禁用mask -->
			<view class="uni-disabled" v-if="disabled"></view>
			<!-- <view v-if="multiple" class="select-tags">
				<view v-for="item in currentObj" class="tag">{{item[slabel]}}<uni-icons type="closeempty" color="#666" @click="deleteItem" size="12"></uni-icons></view>
			</view> -->
			<template v-if="multiple">
				<view v-for="item in currentObj" class="tag">
					{{item[slabel]}} 
					<!-- <text style="color:#666;font-size: 16px;" @click="deleteItem(item)">X</text> -->
					<uni-icons type="closeempty" color="#666" @click="deleteItem(item)" size="12"></uni-icons>
				</view>
			</template>
			<input v-model="inputValue" ref="inputRef" class="qy-select-ep-input" readonly="readonly" type="text" :focus="active" :class="{ active: modelValue != '' && modelValue != placeholder }" :disabled="disabled" :placeholder="placeholderTemp" @input="onInput" @focus="inFocus" />
			<view class="qy-select-icon" v-if="!multiple" @click.stop="clickIcon(modelValue && active)">
				<uni-icons v-if="modelValue && active" type="close" size="18" color="rgb(153, 153, 153)"></uni-icons>
				<uni-icons v-else :type="active ? 'top' : 'bottom'" size="18" color="rgb(153, 153, 153)"></uni-icons>
			</view>
		</view>
		<scroll-view class="qy-select-options" :scroll-y="true" v-if="active">
			<view v-if="!list || !list.length" class="nosearch">暂无匹配数据</view>
			<view v-else class="qy-select-item" v-for="(item, index) in list" :key="index" :class="{ selected:(multiple && modelValue && modelValue.indexOf(item[svalue])>-1) || modelValue == item[svalue] }" @click.stop="selectItem(item)">{{ item[slabel] }}</view>
			<view v-if="total>0 && total>options.length && maxPage>1" class="pagination">
				共{{total}}条
				<button class="mini-btn" size="mini" @click.stop="prev" :disabled="currentPage<=1">
					<uni-icons type="left" size="25" :color="currentPage<=1?'#afafaf':'#666'"></uni-icons>
					<!-- <text :style="'color:'+(currentPage<=1?'#afafaf':'#666')+';font-size: 20px;'">&lt</text> -->
				</button>
				{{currentPage}}
				<button class="mini-btn" size="mini" @click.stop="next" :disabled="currentPage>=maxPage">
					<uni-icons type="right" size="25" :color="currentPage>=maxPage?'#afafaf':'#666'"></uni-icons>
					<!-- <text :style="'color:'+(currentPage>=maxPage?'#afafaf':'#666')+';font-size: 20px;'">&gt</text> -->
				</button>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		name: 'qy-select',
		props: {
			modelValue: {
				type: [Number,String,Array],
				required: false,
				default: ()=>null
			},
			disabled: {
				type: Boolean,
				default: false
			},
			multiple: {
				type: Boolean,
				default: false
			},
			options: {
				type: Array,
				default () {
					return [];
				}
			},
			placeholder: {
				type: String,
				default: '请选择'
			},
			slabel: {
				type: String,
				default: 'label'
			},
			svalue: {
				type: String,
				default: 'value'
			},
			total: {
				type: [Number,String],
				default: 0
			},
			maxPage: {
				type: [Number,String],
				default: 99999
			}
		},
		data() {
			return {
				inputValue: '',
				active: false, //是否激活，
				isblank: false,
				placeholderTemp: '', //备用占位
				currentObj: null, //当前选择对象
				currentPage: 1
			};
		},
		mounted() {
			this.$nextTick(() => {
				this.placeholderTemp = this.placeholder;
				if (this.modelValue && !this.multiple && !this.currentObj) {
					this.currentObj = this.options.find(item => item[this.svalue] === this.modelValue);
					this.inputValue = (this.currentObj && this.currentObj[this.slabel]) || '';
				}
			});
		},
		computed: {
			list() {
				// console.log(this.options);
				if(this.total>0) return this.options;
				return this.options.filter(item => {
					let cond = item[this.slabel].indexOf(this.inputValue) > -1;
					return cond || !this.inputValue;
				});
			},
			// show() {
			// 	let flag = this.active && this.isfocus;
			// 	console.log(this.active,this.ismove,this.isfocus);
			// 	if (flag) {
			// 		flag = this.active && this.isfocus;
			// 	}
			// 	return flag;
			// 	// return true;
			// }
		},
		watch: {
			show(val) {
				if (val) {
					if (this.inputValue) this.placeholderTemp = this.inputValue;
					this.inputValue = '';
				} else if(!this.multiple){
					this.inputValue = (this.currentObj && this.currentObj[this.slabel]) || '';
				}
			},
			label(val) {
				if(!this.multiple)
				this.inputValue = val;
			},
			options(nVal, oVal) {
				if (!oVal && this.modelValue) {
					this.clacInputValue();
				}
			},
			modelValue(nVal, oVal) {
				if (!oVal && this.options && this.options.length) {
					this.clacInputValue();
				}
			}
		},
		methods: {
			clacInputValue() {
				if(this.multiple && this.modelValue){
					this.currentObj = this.options.filter(item =>this.modelValue.indexOf(item[this.svalue])>=0);
				}else{
					let obj = this.options.find(item => this.modelValue == item[this.svalue]);
					if (obj) {
						this.currentObj = obj;
						this.inputValue = obj[this.slabel];
					}
				}
			},
			//点击组件
			// select() {
			// 	if (this.disabled) return;
			// 	this.active = !this.active;
			// },
			onInput(evt){
				this.$emit('input', evt.detail.value);
			},
			maskClick(){
				setTimeout(() => {
					this.active = false;
					if (this.currentObj && !this.multiple) {
						this.inputValue = this.currentObj[this.slabel];
					}else{
						this.inputValue = [];
					}
				}, 100);
			},
			// inBlur() {
			// 	setTimeout(() => {
			// 		if(this.ignoreMove)return this.ignoreMove=false;	
			// 		this.isfocus = false
			// 		if (this.currentObj) {
			// 			this.inputValue = this.currentObj[this.slabel];;
			// 		}
			// 	}, 100);
			// },
			inFocus(evt) {
				if (!this.active) {
					// this.isfocus = true;
					// this.ismove = true;
					this.active = true;
				}
			},
			//移除数据
			clickIcon(flag) {
				if (flag && !this.multiple) {
					this.currentObj = null;
					this.inputValue = '';
					this.$emit('update:model-value', '');
					this.$emit('change', '');
					// this.$emit('onPageChange', 1);
					this.$emit('input', '');
					this.placeholderTemp = this.placeholder;
					this.currentPage = 1;
				}else if(!this.active){
					this.active=true;
				}else{
					this.active=false;
				}
				// this.select();
			},
			selectItem(item) {
				if(this.multiple){
					if(!this.currentObj){
						this.currentObj = [];
					}else if(this.modelValue && this.modelValue.indexOf(item[this.svalue])>-1){
						uni.showToast({ title: item[this.slabel]+'已被选择', duration: 2000, icon: 'none' });
						return;
					}
					this.currentObj.push(item);
					this.inputValue = '';
					let selectArray = this.currentObj.map(item=>item[this.svalue])
					this.$emit('update:model-value', selectArray);
					this.$emit('change', selectArray);
				}else{
					this.currentObj = item;
					this.inputValue = this.currentObj[this.slabel];
					let value = item[this.svalue];
					this.$emit('update:model-value', value);
					this.$emit('change', value);
				}
				this.active = false;
				// this.isfocus = false;
			},
			prev() {
				this.currentPage > 1 && this.$emit('onPageChange', --this.currentPage);
			},
			next() {
				this.currentPage < this.maxPage && this.$emit('onPageChange', ++this.currentPage);
			},
			deleteItem(item){
				let idx = this.modelValue.indexOf(item[this.svalue]);
				this.currentObj.splice(idx,1);
				let selectArray = this.currentObj.map(item=>item[this.svalue])
				this.$emit('update:model-value', selectArray);
				this.$emit('change', selectArray);
				console.log('deleteItem');
			}
			// selectNothing() {
			// 	console.log('selectNothing');
			// 	this.isblank = true;
			// }
		}
	};
</script>

<style lang="scss" scoped>
	:deep(.uni-input-input) {
		cursor: pointer;
		padding: 10px 0;
	}

	:deep(.uni-scroll-view) {
		max-height: 400px;
		background-color: white;
		border-bottom-left-radius: 2px;
		border-bottom-right-radius: 2px;
		// border-left: 1px solid #dfdfdf;
		// border-right: 1px solid #dfdfdf;
		// border: 1px solid #dfdfdf;
	}
	.mask{
		position: fixed;
		height:100vh;
		width:100vw;
		top:0;
		left:0;
		background-color: rgba(0,0,0,.8);
		z-index: 9;
	}
	.tag{
		border-width: 0.03125rem;
		border-style: solid;
		border-color: #8f939c;
		padding-left: 4px;
		padding-right: 2px;
		border-radius: 4px;
		color:#666;
		margin-top: 2px;
		background-color:#fafafc;
		margin-right: 4px;
	}
	.qy-select-ep {
		position: relative;
		width:100%;
		.active{
			z-index: 10  !important;
		}

		.qy-select-input {
			opacity: 0;
			position: absolute;
			z-index: -111;
		}

		.qy-select-ep-select {
			cursor: pointer;
			user-select: none;
			position: relative;
			z-index: 3;
			min-height: 36px;
			padding: 0 10px;
			box-sizing: border-box;
			border-radius: 2px;
			border: 1px solid rgb(229, 229, 229);
			display: flex;
			align-items: center;
			flex-wrap: wrap;
			font-size: 14px;
			color: #999;
			background: #fff;

			.uni-disabled {
				position: absolute;
				left: 0;
				width: 100%;
				height: 100%;
				z-index: 19;
				cursor: no-drop;
				background: rgba(255, 255, 255, 0.5);
			}
			// .select-tags{
			// 	padding-right: 28px;
			// 	display: flex;
			// 	align-items: center;
			// 	flex-wrap: wrap;
			// }
			.qy-select-ep-input {
				font-size: 14px;
				line-height: 34px;
				min-width: 100px;
				height: 34px;
				-webkit-appearance: none;
				background-image: none;
				box-sizing: border-box;
				color: #606266;
				display: inline-block;
				outline: none;
				transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
				width: 0;
				flex:1;
				padding-right: 24px;

				&.active {
					color: #333;
				}
			}

			.qy-select-icon {
				position: absolute;
				right: 0px;
				top: 0px;
				z-index: 9;
				height: 36px;
				width: 36px;
				display: flex;
				align-items: center;
				justify-content: center;

				:deep(.uni-icons) {
					padding: 6px;
				}
			}
		}

		// options部分
		.qy-select-options {
			user-select: none;
			position: absolute;
			top: calc(100% - 4px);
			left: 0;
			width: 100%;
			max-height: 400px;
			border-radius: 0 0px 2px 2px;
			// border: 1px solid rgb(229, 229, 229);
			box-shadow: 0 2px 5px 0 rgba(229, 229, 229,.5);
			background: #fff;
			padding: 5px 0;
			box-sizing: border-box;
			z-index: 10;

			.qy-select-item {
				padding: 0 10px;
				box-sizing: border-box;
				cursor: pointer;
				line-height: 2.5;
				transition: 0.3s;
				font-size: 14px;
				border-top: 1px solid rgba(223, 223, 223,.5);
				border-left: 1px solid rgba(223, 223, 223,.5);
				border-right: 1px solid rgba(223, 223, 223,.5);

				&.active {
					background: #007aff;
					color: #fff;
				}

				&:hover {
					background-color: #f5f7fa;
				}
			}

			.selected {
				color: #409eff;
				font-weight: 700;
			}

			.nosearch {
				font-size: 14px;
				text-align: center;
				color: #666;
				padding: 5px 0;
				border-top: 1px solid rgba(223, 223, 223,.5);
				border-left: 1px solid rgba(223, 223, 223,.5);
				border-right: 1px solid rgba(223, 223, 223,.5);
			}

			.pagination {
				display: flex;
				justify-content: center;
				align-items: center;
				padding: 2px 0;
				border-top: 1px solid rgba(223, 223, 223,.5);
				border-left: 1px solid rgba(223, 223, 223,.5);
				border-right: 1px solid rgba(223, 223, 223,.5);

				// .uni-icons {
				// 	background-color: #ededed;
				// 	cursor: pointer;
				// 	margin-left: 10px;
				// }
				.mini-btn {
					color: #333333;
					background-color: #ededed;
					// margin: 10rpx 0rpx;
					margin:0 5px;
					min-height: 30px;
					max-height: 30px;
					max-width: 30px;
					border-width: 0px !important;
					display: flex;
					align-items: center;
					flex-direction: column;
					justify-content: center;
				}
			}
		}
	}
</style>
