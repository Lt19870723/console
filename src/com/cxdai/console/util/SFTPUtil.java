package com.cxdai.console.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * <p>
 * Description: SFTP工具 <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/26
 * @title cxdai_interface
 * @package com.cxdai.console.util
 */
public class SFTPUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JSch jsch = new JSch();
    private Session session = null;
    private Channel channel = null;
    private ChannelSftp c = null;

    private String sftpHostName = PropertiesUtil.getValue("custody_sftpHostName");
    private String sftpUserName = PropertiesUtil.getValue("custody_sftpUserName");
    private String sftpPassword = PropertiesUtil.getValue("custody_sftpPassword");
    private String sftpDirectory = PropertiesUtil.getValue("custody_sftpDirectory");
    private String sftpPort = PropertiesUtil.getValue("custody_sftpPort");


    public SFTPUtil() {

    }

    /**
     * 连接FTP
     * @return
     * @throws SftpException
     * @throws JSchException
     */
    public SFTPUtil connect() throws SftpException, JSchException {
        //Now connect and SFTP to the SFTP Server
        try {
            //Create a session sending through our username and password
            session = jsch.getSession(sftpUserName, sftpHostName, Integer.parseInt(sftpPort));
            logger.info("***   FTP Session created.   ***");
            session.setPassword(sftpPassword);

            //Security.addProvider(new com.sun.crypto.provider.SunJCE());
            //Setup Strict HostKeyChecking to no so we dont get the
            //unknown host key exception
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            logger.info("***   Session connected.   ***");

            //Open the SFTP channel
            logger.info("***   Opening FTP Channel.   ***");
            channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;

            //Change to the remote directory
            logger.info("***   Changing to FTP remote dir: " + sftpDirectory + "   ***");
            c.cd(sftpDirectory);

        } catch (Exception e) {
            logger.error("***   Unable to connect to FTP server. " + e.toString() + "   ***");
            close();
            throw e;
        } finally {

            logger.info("***   SFTP Process Complete.   ***");
        }
        return this;
    }

    /**
     * 下载文件
     *
     * @param filename        下载文件名称
     * @param outputDirectory 文件保存目录
     * @throws SftpException
     * @throws JSchException
     */
    public SFTPUtil download(String filename, String outputDirectory) throws SftpException, JSchException {
        logger.info("***   Creating FTP session.   ***");

        try {
            //Get file from SFTP
            try {
                logger.info("***   Download file as remote filename: " + filename + "   ***");

                c.get(filename, outputDirectory);
            } catch (Exception e) {
                logger.error("***   Download remote file failed. " + e.toString() + "   ***");
                throw e;
            }
        } catch (Exception e) {
            logger.error("***   Unable to connect to FTP server. " + e.toString() + "   ***");
            // 关闭SFTP连接
            close();
            throw e;
        }
        return this;
    }

    /**
     * 关闭FTP连接
     *
     * @return
     */
    public SFTPUtil close() {
        //
        //Disconnect from the FTP server
        //
        try {
            if (session != null)
                session.disconnect();

            if (channel != null)
                channel.disconnect();

            if (c != null)
                c.quit();
        } catch (Exception exc) {
            logger.error("***   Unable to disconnect from FTP server. " + exc.toString() + "   ***");
        }
        logger.info("***   SFTP Process Complete.   ***");
        return this;
    }
}
