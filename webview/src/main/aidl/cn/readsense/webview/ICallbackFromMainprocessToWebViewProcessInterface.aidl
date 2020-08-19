// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package cn.readsense.webview;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackName, String response);
}
