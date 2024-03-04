package com.programming.techie.javasftpserver.module;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ComponentScan(JavaSftpserverModule.MODULE_PACKAGE)
public class JavaSftpserverModule {
    public static final String MODULE_PACKAGE = "com.programming.techie.javasftpserver";

    private JavaSftpserverModule() {
        // Removing public constructor
    }

}
