var config = {
    el: "#app",
    data: {
        name:"",
        tagList:[]
    },
    methods:{
        selectAll(){
            var url = serverUrl + "/tag/getAll"
            axios.get(url)
            .then(function(response){
                this.vue.tagList = response.data.data
                console.log(this.vue.tagList);
            })
            .catch()
        },
        addTag(){
            var url = serverUrl + "/tag/add?name=" + this.name
            axios.get(url)
            .then(function(response){
                // console.log(response);
                if(response.data.msg){
                    window.alert(response.data.msg)
                }else{
                    window.alert("添加失败，已有该标签")
                }
                this.vue.selectAll()
            }).catch(function(e){
                window.alert("添加失败，已有该标签")
            })
        }
    },
    mounted(){
        var url = serverUrl + "/tag/getAll"
		axios.get(url)
		.then(function(response){
			this.vue.tagList = response.data.data
			console.log(this.vue.tagList);
		})
		.catch()
    }
}

var vue = new Vue(config)