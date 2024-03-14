/**
 * Copyright 2024 Anton Kuzmin http://github.com/antonkuzmn1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.db.fm;

import org.springframework.stereotype.Service;

@Service
public class FmSshService {

    // @Value("${fmadmin.login}")
    // private String login;

    // @Value("${fmadmin.password}")
    // private String password;

    // private JSch jsch = new JSch();

    // private int port = 22;

    // @Value("${fmadmin.command}")
    // private String command;

    // public Session session(String fmIp) throws JSchException {
    //     System.out.println("[FmSshService][session]");
    //     Session session = this.jsch.getSession(this.login, fmIp, this.port);
    //     Properties config = new Properties();
    //     config.put("StrictHostKeyChecking", "no");
    //     session.setConfig(config);
    //     session.setPassword(this.password);
    //     return session;
    // }

    // public String get(String fmIp) throws JSchException, IOException {
    //     System.out.println("[FmSshService][get]");
    //     Session session = session(fmIp);
    //     session.connect();
    //     Channel channel = session.openChannel("exec");
    //     ((ChannelExec) channel).setCommand(this.command);
    //     InputStream commandOutput = channel.getInputStream();
    //     channel.connect();
    //     BufferedReader reader = new BufferedReader(new InputStreamReader(commandOutput));
    //     String output = reader.readLine();
    //     channel.disconnect();
    //     session.disconnect();
    //     return output;
    // }

}
