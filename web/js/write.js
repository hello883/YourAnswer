var config={
    "el":"#write-app",
    "data":{
        // 用户id
        "type": "",
        "typeId": 1,
        "title":"",
        "text":"",
        "img":"",
        "ownerId":"2"
    },
    "methods":{
        
        insert(){
            debugger
            var url = serverUrl + "/post/insertPost"
            +"?img="+this.img
            +"&ownerId="+this.ownerId
            +"&text="+this.text
            +"&title="+this.title
            +"&type="+this.type
            +"&typeId="+this.typeId

            axios.get(url)
            .then(function(response){
                // window.alert(url)
                window.alert(this.vue.response.data.msg)
                location.href = "../html/course.html"
          
            })
            .catch()
        }
    },
    mounted(){
        debugger
        // 传过来userid
        var p = location.search
        var array = p.split("=")
        this.ownerId = array[1]
    }
}
var vue=new Vue(config)