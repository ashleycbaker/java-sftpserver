package com.programming.techie.javasftpserver;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.sshd.common.util.security.bouncycastle.BouncyCastleGeneratorHostKeyProvider;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

@Service
@Slf4j
public class MySftpServer {

    private SshServer sshd;

    @PostConstruct
    public void startServer() throws IOException {
        start();
    }

    private void start() throws IOException {
        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(2222);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("host.ser")));
        sshd.setPasswordAuthenticator((username, password, session) -> username.equals("test") && password.equals("password"));
        sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(Paths.get("authorized_keys")));
        SftpSubsystemFactory factory = new SftpSubsystemFactory();
        factory.addSftpEventListener(new MySfpEventListener());
        sshd.setSubsystemFactories(Collections.singletonList(factory));
        sshd.start();
        sshd.isStarted();
        log.info("SFTP server started");
    }

    @PreDestroy
    private void stop(){
        if(sshd != null && sshd.isStarted()){
            try {
                sshd.close();
            } catch (IOException e) {
                log.error("Unable to close SSHD Server", e);
            }
        }
    }
}
