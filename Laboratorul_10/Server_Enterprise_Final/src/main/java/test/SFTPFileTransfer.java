package test;

import com.jcraft.jsch.*;

public class SFTPFileTransfer {
    private static final String LOCAL_FILE = "screenshot.xml";
    private static final String REMOTE_FILE = "/home/mkyong/remote/screenshot.xml";
    private static final String KNOWN_HOST = "/home/mkyong/.ssh/known_hosts";
    private static final String REMOTE_HOST = "1.2.3.4";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 2000;

    public static void sendXML() throws JSchException{
        Session jschSession = null;

        try {
            JSch jsch = new JSch();
            jsch.setKnownHosts(KNOWN_HOST);
            jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);

            // authenticate using private key
            // jsch.addIdentity("/home/mkyong/.ssh/id_rsa");

            // authenticate using password
            jschSession.setPassword(PASSWORD);

            // 10 seconds session timeout
            jschSession.connect(SESSION_TIMEOUT);
            Channel sftp = jschSession.openChannel("sftp");

            // 5 seconds timeout
            sftp.connect(CHANNEL_TIMEOUT);
            ChannelSftp channelSftp = (ChannelSftp) sftp;

            // transfer file from local to remote server
            channelSftp.put(LOCAL_FILE, REMOTE_FILE);
            channelSftp.exit();

        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }
    }

}