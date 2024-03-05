package cloud.robinzon.backend.db.fm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class FmSshService {

    @Value("${fmadmin.login}")
    private String login;

    @Value("${fmadmin.password}")
    private String password;

    private JSch jsch = new JSch();

    private int port = 22;

    @Value("${fmadmin.command}")
    private String command;

    public Session session(String fmIp) throws JSchException {
        System.out.println("[FmSshService][session]");
        Session session = this.jsch.getSession(this.login, fmIp, this.port);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(this.password);
        return session;
    }

    public String get(String fmIp) throws JSchException, IOException {
        System.out.println("[FmSshService][get]");
        Session session = session(fmIp);
        session.connect();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(this.command);
        InputStream commandOutput = channel.getInputStream();
        channel.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(commandOutput));
        String output = reader.readLine();
        channel.disconnect();
        session.disconnect();
        return output;
    }
}
