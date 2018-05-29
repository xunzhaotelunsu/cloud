package com.baosight.cloud.tools.fs.impl.fastdfs;

import lombok.Data;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * Created by yang on 2017/12/11.
 */
@ConfigurationProperties(prefix = "fastdfs")
@Data
public class FastDFSProperties {

    int connectionTimeout = 5;//second

    int networkTimeout = 30;//second

    String charset = "UTF-8";

    boolean httpAntiStealToken = false;

    String httpSecurityKey = "FastDFS1234567890";

    int trackerHttpPort = 80;

    String trackerServers;

    public Properties toProperties(){
        Properties prop = new Properties();
        prop.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, this.connectionTimeout);
        prop.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, this.networkTimeout);
        prop.put(ClientGlobal.PROP_KEY_CHARSET, this.charset);
        prop.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, this.httpAntiStealToken);
        prop.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, this.httpSecurityKey);
        prop.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, this.trackerHttpPort);
        prop.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, this.trackerServers);
        return prop;
    }


}
