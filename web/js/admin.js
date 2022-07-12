var config = {
    el: "#app",
    data: {
        type: 0,
        typeId: "",
        postInfo: [{title:"未搜索", text:"请先搜索"}]
    },
    methods:{
        search(){
            var url = serverUrl + "/post/getPostList?"
            +"type="+this.type
            +"&typeId="+this.typeId
            +"&userId="
            axios.get(url)
            .then(function(response){
                // console.log(response);
                var d = response.data.data
                this.vue.posInfo = d
                console.log(this.vue.posInfo);
            }).catch()
        },
        deletep(id){
            var url2 = serverUrl + "/post/deletePost?id=" + id
            axios.get(url2)
            .then(function(response){
                window.alert(response.data.msg)
                this.vue.search()
            })
            .catch()
        }
    },
    mounted(){
        // debugger
    }
}

var vue = new Vue(config)