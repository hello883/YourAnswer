var config = {
    el: "#app",
    data: {
        name:""
    },
    methods:{
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
            }).catch(function(e){
                window.alert("添加失败，已有该标签")
            })
        }
    }
}

var vue = new Vue(config)