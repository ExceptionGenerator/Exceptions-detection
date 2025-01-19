package com.shiv.exception.registry;

import java.util.prefs.Preferences;

public class RegistryManager {
    private static final Preferences preferencesRoot = Preferences.systemRoot();

    /**
     * url - RnXKSEDifB0SomHKFfL94eQit48zhtjN/sr5TdVbZpozZ0ykJFleRmh4+gynZYGi
     * uname - FMzj6YT+k93nFmwHLQaS6Q==
     * pwd - Lokd/mfFlzyDvq5V6ZH7vw==
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        System.out.println(Inet4Address.getLocalHost().getHostName());
//        String str = "-secretKey=86WlctxUpvh1sLgAGPmZyQ==";
//        System.out.println(str.substring(str.indexOf("=") + 1));
//        System.out.println(str.substring(0,str.indexOf("=")).substring(1));
//        System.out.println(Arrays.toString(str.split("=")));
//        System.out.println(preferencesRoot);
//        preferencesRoot.put("sgs-db-url","null");
//        preferencesRoot.put("sgs-db-username","null");
        System.out.println(preferencesRoot.get("sgs-db-url",null));
        System.out.println(preferencesRoot.get("sgs-db-username",null));
        System.out.println(preferencesRoot.get("sgs-db-password",null));
        System.out.println(preferencesRoot.get("sgs-central-db-url",null));
        System.out.println(preferencesRoot.get("sgs-central-db-username",null));
        System.out.println(preferencesRoot.get("sgs-central-db-password",null));
//        preferencesRoot.put("sgs-db-url","CuNSgRUydkKIhNMHBizcyI+wI6XbGoVKSwRjoG+Nk1Xdh1xqNCXtBx83bP/ePYGN");
//        preferencesRoot.put("sgs-db-username","yn2QjYeVcbgFVhs8gm2mow==");
//        preferencesRoot.put("sgs-db-password","p+ufexOF9DQfnpDMquxmEg==");
    }
}
