var config = {
    el: "#app",
    data: {
        test: false,
        type: 0,
        typeId: "",
        postInfo: [{id: 1,type: "",title:"未搜索", text:"请先搜索"}]
    },
    methods:{
		selectAll(){
            var url = serverUrl + "/post/getAll"
            axios.get(url)
            .then(function(response){
                this.vue.postInfo = response.data.data 
                console.log(this.vue.postInfo);
            })
            .catch()
		},
        // search(){
        //     // debugger
        //     var url = serverUrl + "/post/getPostList?"
        //     +"type="+this.type
        //     +"&typeId="+this.typeId
        //     +"&userId="
        //     axios.get(url)
        //     .then(function(response){
        //         // console.log(response);
        //         var d = response.data.data
                
        //         if(d == []){
        //             this.vue.postInfo[0].title = "没有相关帖子"
        //         }else{
        //             this.vue.posInfo = d
        //             //或者修改另一个data的值触发渲染
        //             this.vue.test = !this.vue.test 
        //         }
        //         console.log(this.vue.posInfo);

        //         //或者修改另一个data的值触发渲染
        //         // this.$forceUpdate()
                
        //         // window.location.reload();
        //         // console.log(this.vue.posInfo[0].title);
        //     }).catch()
        // },
        deletep(id){
            var url2 = serverUrl + "/post/deletePost?id=" + id
            axios.get(url2)
            .then(function(response){
                window.alert(response.data.msg)
                this.vue.selectAll()
            })
            .catch()
        }
    },
    mounted(){
        // debugger
        this.selectAll()
    }
}

var vue = new Vue(config)