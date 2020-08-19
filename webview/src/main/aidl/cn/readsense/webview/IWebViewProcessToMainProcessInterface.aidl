// IWebViewProcessToMainProcessInterface.aidl
package cn.readsense.webview;

import cn.readsense.webview.ICallbackFromMainprocessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName, String jsonParams,in ICallbackFromMainprocessToWebViewProcessInterface callback);
}
