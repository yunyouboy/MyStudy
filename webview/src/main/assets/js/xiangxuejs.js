var xiangxuejs = {};
xiangxuejs.os = {};
xiangxuejs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
xiangxuejs.os.isAndroid = !xiangxuejs.os.isIOS;

xiangxuejs.takeNativeAction = function(commandname, parameters){
    console.log("xiangxuejs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.mywebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.mywebview.postMessage(JSON.stringify(request))
    }
}

window.xiangxuejs = xiangxuejs;
