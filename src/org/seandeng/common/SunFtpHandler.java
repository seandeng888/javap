package org.seandeng.common;

import org.seandeng.util.DateTimeUtil;
import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * FTP服务器文件处理类。包括连接及关闭FTP服务器、下载及上传文件。
 *
 *  @author dengsc
 */
public class SunFtpHandler {

    private String localfilename;
    private String remotefilename;
    private FtpClient ftpClient;

            /**
             * 服务器连接.
             * @param ip 服务器IP
             * @param user 用户名
             * @param password 密码
             * @param path 服务器路径
             * @author dengsc
             * @date   2014-8-15
             */
    public void connectServer(String ip, String user, String password, String path) throws Exception {

        ftpClient = new FtpClient(ip);
        ftpClient.login(user, password);
        ftpClient.binary();
        if (path.length() != 0) {
            ftpClient.cd(path);
        }
        ftpClient.binary();
    }
    /**
     * 关闭连接
     * @author dengsc
     * @date   2014-8-15
     */
    public void closeConnect() throws Exception {
        if (null!=ftpClient) {
            ftpClient.closeServer();
        }
    }
    /**
     * 上传文件
     * @param localFile 本地文件
     * @param remoteFile 远程文件
     * @date   2012-7-11
     */
    public void upload(String localFile, String remoteFile) throws Exception {

        TelnetOutputStream os = null;
        FileInputStream is = null;
        try {
            //将远程文件加入输出流中
            os = ftpClient.put(remoteFile);
            //获取本地文件的输入流
            File file_in = new File(localFile);
            is = new FileInputStream(file_in);
            //创建一个缓冲区
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
        } catch (IOException ex) {
            throw ex;
        } finally{
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                throw e;
            } finally {
                try {
                    if(os != null){
                        os.close();
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 下载文件
     * @param remoteFile 远程文件路径(服务器端)
     * @param localFile 本地文件路径(客户端)
     * @date   2012-7-11
     */
    public void download(String remoteFile, String localFile) throws Exception {

        TelnetInputStream is = null;
        FileOutputStream os = null;
        try {
            //获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
            is = ftpClient.get(remoteFile);
            File file_in = new File(localFile);
            os = new FileOutputStream(file_in);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
        } catch (IOException ex) {
            throw ex;
        } finally{
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                throw e;
            } finally {
                try {
                    if(os != null){
                        os.close();
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 获取特定目录下所有文件。
     * @param path
     * @return
     * @throws Exception
     */
    public List getFileList(String path) throws Exception {

        List list = new ArrayList();
        DataInputStream dis;
        dis = new DataInputStream(this.ftpClient.nameList(path));
        String filename = "";
        while ((filename = dis.readLine()) != null) {
            list.add(filename);
        }
        return list;
    }



    /**
     * 下载特定目录下的所有文件。
     * @param remotePath 远程文件路径(服务器端)
     * @param localPath 本地文件路径(客户端)
     * @date   2012-7-11
     */
    public List downloadAndDeleteAllFiles(String ip, String user, String password,String remotePath, String localPath) throws Exception {

        List<File> localFileList = new ArrayList<File>();
        TelnetInputStream is = null;
        FileOutputStream os = null;
        try {
            this.connectServer(ip, user, password, remotePath);
            List<String> remoteFileNameList = this.getFileList(remotePath);
            List<String> gotRemoteFileNameList = new ArrayList();
            for (String remoteFileName : remoteFileNameList) {
                try {
                    try {
                        is = this.ftpClient.get(remoteFileName);
                    } catch (Exception e) {
                        continue;
                    }
                    if (!((remoteFileName.endsWith("pdf")) || (remoteFileName.endsWith("xml")))) {
                        continue;
                    }
                    if (!new File(localPath).exists()) {
                        new File(localPath).mkdir();
                    }
                    File file_in = new File(localPath + File.separator + remoteFileName);
                    localFileList.add(file_in);
                    gotRemoteFileNameList.add(remoteFileName);
                    os = new FileOutputStream(file_in);
                    byte[] bytes = new byte[1024];
                    int c;
                    while ((c = is.read(bytes)) != -1) {
                        os.write(bytes, 0, c);
                    }
                } catch (IOException ex) {
                    throw ex;
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        // throw e;
                    } finally {
                        try {
                            if (os != null) {
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            // throw e;
                        }
                    }
                }
            }
            if (gotRemoteFileNameList.size()>0) {
                String today = DateTimeUtil.dateToString(new Date(), "yyyyMMdd HHmmss");
                ftpClient.ascii();
                ftpClient.sendServer("MKD " + today + "\r\n");
                ftpClient.readServerResponse();     // 防止创建相同目录会报错的问题。
                ftpClient.binary();
                // 下载后备份文件到当天目录。
                for (String remoteFileName:gotRemoteFileNameList) {
                    moveFile2Dir(remoteFileName, today);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                this.closeConnect();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return localFileList;
    }

    /**
     * 备份文件到当天目录。
     * @param remoteFileName
     */
    public boolean moveFile2Dir(String remoteFileName, String toPath) throws Exception {
        ftpClient.rename(remoteFileName, toPath + File.separator + remoteFileName);
        return true;
    }

    /**
     * 删除文件。
     * @param remoteFileName
     */
    public boolean deleteFile(String remoteFileName) throws Exception {
        ftpClient.ascii();
        ftpClient.sendServer("dele " + remoteFileName + "\r\n");
        ftpClient.readServerResponse();
        ftpClient.binary();
        return true;
    }
    /**
     * 删除ftp目录
     * @param remoteFilePath
     */
    public boolean deleteDirectory(String remoteFilePath) throws Exception {

        ftpClient.ascii();
        ftpClient.sendServer("xrmd " + remoteFilePath + "\r\n");
        ftpClient.readServerResponse();
        ftpClient.binary();
        return true;
    }
    /**
     * 上传文件.
     * @param localAbsoluteFileName 本地文件(全路径）
     * @param remoteFileName 远程文件（文件名称）
     * @date   2012-7-11
     */
    public void upload(String ip, String user, String password, String remotePath, String[] localAbsoluteFileName, String[] remoteFileName) throws Exception {
        SunFtpHandler fu = new SunFtpHandler();
        try {
            fu.connectServer(ip, user, password, remotePath);
            //上传
            for (int i=0;i<localAbsoluteFileName.length;i++) {
                fu.upload(localAbsoluteFileName[i], remoteFileName[i]);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (fu != null) {
                    fu.closeConnect();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 下载文件
     * @param remoteFile 远程文件路径(服务器端)
     * @param localFile 本地文件路径(客户端)
     * @date   2012-7-11
     */
    public void download(String ip, String user, String password, String remotePath, String[] remoteFile, String localFile[]) throws Exception {
        SunFtpHandler fu = new SunFtpHandler();
        try {
            fu.connectServer(ip, user, password, remotePath);
            //下载
            for (int i = 0; i < remoteFile.length; i++) {
                fu.download(remoteFile[i], localFile[i]);
                // fu.
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (fu != null) {
                    fu.closeConnect();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String agrs[])  {

        SunFtpHandler fu = new SunFtpHandler();

        /*
         * 使用默认的端口号、用户名、密码以及根目录连接FTP服务器
         */
        /*fu.connectServer("172.20.59.173", "outfilesendfy", "outfilesendfy", "/");

        String localfile = "Z:\\1 temp\\1.txt";
        String remotefile = "/1.txt";*/
        //上传
        /*fu.upload(localfile, remotefile);*/


        //下载
        /*for (int i = 0; i < filepath.length; i++) {
            fu.download(filepath[i], localfilepath[i]);
        }*/

        try {
            List list = fu.downloadAndDeleteAllFiles("172.20.59.173", "filereceivefy", "filereceivefy", "/", "z:\\temp\\temp\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        /*String xx = "Z:\\temp\\in\\ga\\201409011030\\b1423a5d1a7d424996d0ff1f54ca527b.xml";
        File file = new File(xx);

        System.out.println(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));*/
    }

}
