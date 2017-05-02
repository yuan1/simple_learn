/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.utils;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;
/**
 * 系统信息获取
 * Created by 李明元 on 2016/12/3.
 */
public class SystemInfo {
    /**系统名*/
    private String os_name;
    /**系统架构*/
    private String os_arch ;
    /**系统版本号*/
    private String os_version ;
    /**系统IP*/
    private String os_ip ;
    /**系统MAC地址*/
    private String os_mac;
    /**系统时间*/
    private Date os_date;
    /**系统CPU个数*/
    private Integer os_cpus ;
    /**系统用户名*/
    private String os_user_name;
    /**用户的当前工作目录*/
    private String os_user_dir;
    /**用户的主目录*/
    private String os_user_home;

    /**Java的运行环境版本*/
    private String java_version ;
    /**java默认的临时文件路径*/
    private String java_io_tmpdir;

    /**java 平台*/
    private String sun_desktop ;

    /**文件分隔符  在 unix 系统中是＂／＂*/
    private String file_separator;
    /**路径分隔符  在 unix 系统中是＂:＂*/
    private String path_separator;
    /**行分隔符   在 unix 系统中是＂/n＂*/
    private String line_separator;

    /**服务context**/
    private String server_context ;
    /**服务器名*/
    private String server_name;
    /**服务器端口*/
    private Integer server_port;
    /**服务器地址*/
    private String server_addr;
    /**获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址*/
    private String server_host;
    /**服务协议*/
    private String server_protocol;

    public static SystemInfo SYSTEMINFO;

    public static SystemInfo getInstance(){
        if(SYSTEMINFO == null){
            SYSTEMINFO = new SystemInfo();
        }
        return SYSTEMINFO;
    }


    public static SystemInfo getInstance(HttpServletRequest request){
        if(SYSTEMINFO == null){
            SYSTEMINFO = new SystemInfo(request);
        }
        else {
            SYSTEMINFO.ServerInfo(request);
        }
        return SYSTEMINFO;
    }

    private SystemInfo() {
        super();
        init();
    }

    public SystemInfo(HttpServletRequest request) {
        super();
        init();
        /**
         * 额外信息
         */
        ServerInfo(request);
    }


    /**
     * 初始化基本属性
     */
    private void init(){
        Properties props=System.getProperties();

        this.java_version = props.getProperty("java.version");
        this.java_io_tmpdir = props.getProperty("java.io.tmpdir");
        this.os_name = props.getProperty("os.name");
        this.os_arch = props.getProperty("os.arch");
        this.os_version = props.getProperty("os.version");
        this.file_separator = props.getProperty("file.separator");
        this.path_separator = props.getProperty("path.separator");
        this.line_separator = props.getProperty("line.separator");

        this.os_user_name = props.getProperty("user.name");
        this.os_user_home = props.getProperty("user.home");
        this.os_user_dir = props.getProperty("user.dir");

        this.sun_desktop = props.getProperty("sun.desktop");

        this.os_date = new Date();
        this.os_cpus = Runtime.getRuntime().availableProcessors();

        try {
            ipMac();
        } catch (Exception e) {
            this.os_ip = "";
            this.os_mac = "";
        }
    }

    /**
     * 获取ip和mac地址
     * @throws Exception
     */
    @SuppressWarnings("resource")
    private void ipMac() throws Exception{
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        ni.getInetAddresses().nextElement().getAddress();
        byte[] mac = ni.getHardwareAddress();
        String sIP = address.getHostAddress();
        String sMAC = "";
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; i++) {
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                    (i < mac.length - 1) ? "-" : "").toString();

        }
        SystemInfo.this.os_ip = sIP;
        SystemInfo.this.os_mac = sMAC;
    }

    /**
     * 获取服务器信息
     * @param request
     */
    public void ServerInfo(HttpServletRequest request){
        this.server_name = request.getServerName();
        this.server_port = request.getServerPort();
        this.server_addr = request.getRemoteAddr();
        this.server_host = request.getRemoteHost();
        this.server_protocol = request.getProtocol();
        this.server_context = request.getContextPath();
    }

    public String getOs_name() {
        return os_name;
    }

    public String getOs_arch() {
        return os_arch;
    }

    public String gOss_version() {
        return os_version;
    }

    public String getOs_ip() {
        return os_ip;
    }

    public String getOs_mac() {
        return os_mac;
    }

    public Date getOs_date() {
        return os_date;
    }

    public Integer getOs_cpus() {
        return os_cpus;
    }

    public String getOs_user_name() {
        return os_user_name;
    }

    public String getOs_user_dir() {
        return os_user_dir;
    }

    public String getOs_user_home() {
        return os_user_home;
    }

    public String getJava_version() {
        return java_version;
    }

    public String getJava_io_tmpdir() {
        return java_io_tmpdir;
    }

    public String getSun_desktop() {
        return sun_desktop;
    }

    public String getFile_separator() {
        return file_separator;
    }

    public String getPath_separator() {
        return path_separator;
    }

    public String getLine_separator() {
        return line_separator;
    }

    public String getServer_context() {
        return server_context;
    }

    public String getServer_name() {
        return server_name;
    }

    public Integer getServer_port() {
        return server_port;
    }

    public String getServer_addr() {
        return server_addr;
    }

    public String getServer_host() {
        return server_host;
    }

    public String getServer_protocol() {
        return server_protocol;
    }

    public String getOs_version() {
        return os_version;
    }
}