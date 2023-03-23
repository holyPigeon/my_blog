<template>
    <div id="wrapper" class="container-fluid">
        <img alt="Vue logo" src="../../assets/logo.png">
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">상품 목록</h4>
                    </div>

                    <table class="text-center fs-4" style="width: 100%; border: 1px solid #444444;">
                        <tr>
                            <td>Item Id</td>
                            <td>Item Name</td>
                            <td>Item Price</td>
                            <td>Item Quantity</td>
                        </tr>
                        <tr v-for="(item, index) in itemList.itemData" :key="index">
                            <td>{{ item.id }}</td>
                            <td>{{ item.name }}</td>
                            <td>{{ item.price }}</td>
                            <td>{{ item.quantity }}</td>
                        </tr>
                    </table>

                    <!-- <div class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                        </div> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'itemList',
    data() {
        return {
            itemList: {
                count: -1,
                itemData: [
                    {
                        // id: '',
                        // name: '',
                        // price: -1,
                        // quantity: -1,
                    }
                ],
            }
        }
    },
    // methods: {
    //     submitForm() {
    //         const data = this.formData;
    //         axios.post('/member/join', data)
    //             .then((res) => {
    //                 console.log(res);
    //                 this.$router.push('/');
    //             }).catch((err) => {
    //                 let errMsg = JSON.stringify(err.response.data.message);
    //                 errMsg = errMsg.substring(1, errMsg.length - 1);
    //                 console.log("errMsg -> " + errMsg);
    //                 alert(errMsg);
    //             });
    //     },
    // },
    beforeMount() {
        axios.get('/item/list')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.itemList.count = res.data.count;
                this.itemList.itemData = [...res.data.data];
            }).catch((err) => {
                JSON.stringify("err => " + err);
            });
    },
}

</script>
 
<style>
input::placeholder {
    font-size: 14px;
}
</style>