var config = {
    el: "#t-app",
    data: {
        name: "",
        courseList: {}
    },
    methods:{
        search(){
            var url = serverUrl + "/course/selectByN?s=" + this.name
            axios.get(url)
            .then(function(response){
                debugger
                this.courseList = response.data.data
                location.href = "../html/home.html?list="+this.courseList
            })
            .catch()
        }
    }
}

var vue = new Vue(config)