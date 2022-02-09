<!DOCTYPE html>
<html>
<head>
    <title>Facebook Login JavaScript Example</title>
    <meta charset="UTF-8">
</head>
<body>
<script>

    function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
        console.log('statusChangeCallback');
        console.log(response);                   // The current login status of the person.
        if (response.status === 'connected') {   // Logged into your webpage and Facebook.
            testAPI();
        } else {                                 // Not logged into your webpage or we are unable to tell.
            document.getElementById('status').innerHTML = 'Please log ' +
                'into this webpage.';

        }
    }


    function checkLoginState() {               // Called when a person is finished with the Login Button.
        FB.getLoginStatus(function (response) {   // See the onlogin handler
            statusChangeCallback(response);
        });
    }


    window.fbAsyncInit = function () {
        FB.init({
            appId: '1315390985573293',
            cookie: true,                     // Enable cookies to allow the server to access the session.
            xfbml: true,                     // Parse social plugins on this webpage.
            version: 'v12.0'           // Use this Graph API version for this call.
        });


        /*FB.getLoginStatus(function (response) {   // Called after the JS SDK has been initialized.
            statusChangeCallback(response);        // Returns the login status.
        });*/
    };

    function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function (response) {
            console.log('Successful login for: ' + response.name);
            document.getElementById('status').innerHTML =
                'Thanks for logging in, ' + response.name + '!';
        });
    }
    var accessToken;
    //授权代码
    function auth() {
        //获取登录状态
        FB.getLoginStatus(function (response) {
            console.log("getLoginStatus...")
            console.log(response);
            console.log("getLoginStatus end..")
            //如果状态是已登录
            if (response.status == 'connected') {
                consloe.log("connected");
                //获取用户信息
                FB.api('/me', function (response) {
                    console.log('Successful login for: ' + response.name);
                });
            } else {
                console.log("login in")
                //登录状态是未登录则调用登录并请求授权
                FB.login(function (response) {
                    console.log(response)
                    if(response.status=='connected'){
                        accessToken = response.authResponse.accessToken;
                        console.log("accessToken:"+accessToken);
                        FB.api('/me', function (response) {
                            console.log("me..")
                            console.log(response);
                        });
                    }
                    else{
                        console.log("login fail")
                    }

                }, {scope: 'public_profile,email,ads_read,ads_management,business_management'});
                //
            }
        },true);
    }
    function logout(){
        console.log("logout...")
        FB.logout(function(response) {
            console("logout success...")
        });
    }

    function auth2(){
        var requestUri = new Windows.Foundation.Uri(
            "https://www.facebook.com/v12.0/dialog/oauth?client_id=1315390985573293&display=popup&response_type=code&redirect_uri=https://www.mengxi.love/fbAccreditCallback");
        Windows.Security.Authentication.Web.WebAuthenticationBroker.authenticateAsync(
            options,
            requestUri)
            .done(function (result) {
                    // Handle the response from the Login Dialog
                }
            );
    }
</script>


<!-- The JS SDK Login Button -->

<#--
<fb:login-button scope="public_profile,email,ads_management,ads_read,business_management" onlogin="checkLoginState();">
</fb:login-button>
-->

<button onclick="auth2();">授权</button>
<button onclick="logout();">登出</button>
<div id="status">
</div>

<!-- Load the JS SDK asynchronously -->
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
</body>
</html>