/**
 * Created by hetiewei on 2017/2/28.
 */
Vue.http.options.emulateJSON = true;

var loginPage = new Vue({
    el: '#loginPage',
    data: {
        'username': '',
        'password': ''
    },
    methods: {
        login: function(event){
            var ok = $('#form').parsley().isValid({force: true});
            if(!ok){
                return;
            }
            var datas={
                username: this.username,
                password: this.password
            };
            this.$http.post('/login',datas).then(function(response){
                if(response.data.status){
                    alert("登录成功！");
                    window.open("/home/index.html", '_self');
                }else if(response.data.msg != ""){
                    console.log("登录失败！");
                    $("#errorMsg").html("用户名或密码错误！");
                    $("#errorMsg").show();
                }
            }, function(response){
                console.log('error');
            })
        }
    }
});
