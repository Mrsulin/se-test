package com.slc.jvm.security;

/**
 * @author slc
 */
public class SecurityManagerTest {

    public static void main(String[] args) {
        SecurityManager securityManager = new SecurityManager();
        System.setSecurityManager(securityManager);


        System.out.println(System.getProperty("java.version"));
    }
}
