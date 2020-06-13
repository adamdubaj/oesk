package oesk.algorithms;

import fr.bmartel.speedtest.utils.SpeedTestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileServer {
    private static String fileName = SpeedTestUtils.generateFileName() + ".txt";
    public static final int SOCKET_TIMEOUT = 100000;
    //public static final String SPEED_TEST_SERVER_URI_DOWNLOAD = "http://ipv4.ikoula.testdebit.info/10M.iso";
    public static final String SPEED_TEST_SERVER_URI_DOWNLOAD = "ftp://speedtest.tele2.net/10MB.zip";
    //public static final String SPEED_TEST_SERVER_URI_UPLOAD = "http://ipv4.ikoula.testdebit.info/";
    public static final String SPEED_TEST_SERVER_URI_UPLOAD = "ftp://speedtest.tele2.net/upload/" + fileName;
    public static final Logger LOGGER = LogManager.getLogger(FileServer.class.getName());
    public static final int UPLOAD_FILE_SIZE = 1000000;
    public static final int MAX_TEST_DURATION = 120000;
    public static final int SPEED_TEST_REPORT_INTERVAL = 10;
}
