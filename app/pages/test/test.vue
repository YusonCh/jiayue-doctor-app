<template>
    <view>
        <form>
            <view class="cu-bar bg-white margin-top">
                <view class="action">
                    图片上传
                </view>
                <view class="action">
                    {{imgList.length}}/4
                </view>
            </view>
            <view class="cu-form-group">
                <view class="grid col-4 grid-square flex-sub">
                    <view class="bg-img" v-for="(item,index) in imgList" :key="index" @tap="ViewImage" :data-url="item">
                     <image v-if="item" :src="item" mode="aspectFill"></image>
                        <view class="cu-tag bg-red" @tap.stop="DelImg" :data-index="index">
                            <text class='cuIcon-close'></text>
                        </view>
                    </view>
                    <view class="solids" @tap="ChooseImage" v-if="imgList.length<4">
                        <text class='cuIcon-cameraadd'></text>
                    </view>
                </view>
            </view>
            
        </form>
    </view>
</template>
<script>
    export default {
        data() {
            return {
                imgList: [],
            //  modalName: null,
            };
        },
        methods: {
        
            ChooseImage() {
                const _this = this
                uni.chooseImage({
                    count: 4, //默认9
                    sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
                    sourceType: ['album'], //从相册选择
                    success: (res) => {
                         const tempFilePaths = res.tempFilePaths;
                             const uploadTask = uni.uploadFile({
                              url : 'http://47.xxx.xxx.78:8088/chemApp/work/upload.action',
                              filePath: tempFilePaths[0],
                              name: 'file',
                             
                              success: function (uploadFileRes) {
                               console.log(uploadFileRes);
                               _this.imgList = [..._this.imgList, uploadFileRes.data]
                               
                              }
                             });
                       
                    }
                });
            },
            ViewImage(e) {
                uni.previewImage({
                    urls: this.imgList,
                    current: e.currentTarget.dataset.url
                });
            },
            //删除
            DelImg(e) {
                uni.showModal({
                    title: '删除',
                    content: '确定要删除这张图片？',
                    cancelText: '取消',
                    confirmText: '删除',
                    success: res => {
                        if (res.confirm) {
                            this.imgList.splice(e.currentTarget.dataset.index, 1)
                        }
                    }
                })
            },
        }
    }
</script>
 
<style>
@import "../../colorui/main.css";
@import "../../colorui/icon.css";
.cu-form-group .title {
   min-width: calc(4em + 15px);
}
</style>