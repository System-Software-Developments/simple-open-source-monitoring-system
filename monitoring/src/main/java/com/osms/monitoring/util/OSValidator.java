package com.osms.monitoring.util;

import java.util.Locale;

/**
 * Created by david100gom on 2018. 1. 27.
 *
 * Github : https://github.com/david100gom
 */
public class OSValidator {

    private static OSType osType;

    public enum OSType {
        Windows, Mac, Linux, Solaris, Other
    };

    public static OSType getOSType() {

        String checkOS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);

        if((checkOS.indexOf("mac") >= 0) || (checkOS.indexOf("darwin") >= 0)) {
            osType = OSType.Mac;
        }else if(checkOS.indexOf("win") >= 0) {
            osType = OSType.Windows;
        }else if(checkOS.indexOf("nix") >= 0 || checkOS.indexOf("nux") >= 0 || checkOS.indexOf("aix") > 0) {
            osType = OSType.Linux;
        }else if(checkOS.indexOf("sunos") >= 0) {
            osType = OSType.Solaris;
        }else{
            osType = OSType.Other;
        }

        return osType;
    }
}