package com.wjf.beacontower.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author xiaom
 * Time: 2019/12/30 17:42
 * Author: Lin.Li
 * Description:
 */
public final class CloseUtils {

    private CloseUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
