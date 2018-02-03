package com.osms.monitoring.process;

import com.osms.monitoring.util.OSValidator;
import com.osms.monitoring.util.ShellCommander;
import com.osms.monitoring.util.SystemInfo;

/**
 * Created by david100gom on 2018. 2. 3.
 *
 * Github : https://github.com/david100gom
 */
public class BaseValidator {

    public static void start() {

        try {

            System.out.println("OS Type : "+ OSValidator.getOSType());
            // shell script must be located at local filesystem, shell script link info is parameter
            String returnString = ShellCommander.shellCmd("/Users/administrator/Downloads/network.sh");
            System.out.println("Network : "+returnString);

            SystemInfo.getInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
