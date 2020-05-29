package com.zero.serializabledemo.serializable;

import java.io.Serializable;

/**
 * Author:qyg
 * DATE:2020/5/27 14:43
 * Description：
 **/

public class Single implements Serializable {
    private static final long serialVersionUID = 1L;
    private static boolean flag = false;

    private Single() {
        synchronized (Single.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("单例模式被侵犯！");
            }

        }
    }

    private static Single single;

    public static Single getInstance() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }

        }
        return single;
    }

    private Object readResolve() {
        return single;
    }
}
