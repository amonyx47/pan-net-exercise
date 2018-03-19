package helpers;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.google.common.base.Charsets.*;
import static com.google.common.io.FileWriteMode.*;

public class OpenPortsChecker {

    private final int MAX_NUMBER_OF_THREADS = 50;
    private final String OUTPUT_FILE_NAME = "output.txt";

    @NotNull
    public static Future<PortScanResult> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
        return es.submit(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), timeout);
                socket.close();
                return new PortScanResult(ip, port, true);
            } catch (Exception ex) {
                return null;
            }
        });
    }

    public ArrayList<PortScanResult> run(String ipAddr) throws ExecutionException, InterruptedException {
        File file = new File(OUTPUT_FILE_NAME);
        final ExecutorService es = Executors.newFixedThreadPool(MAX_NUMBER_OF_THREADS);
        final int timeout = 150; //in milliseconds
        ArrayList<PortScanResult> portScanResults = new ArrayList<>();
        ArrayList<PortScanResult> oldPortScanResults = getOldResultsByHost(ipAddr);

            final List<Future<PortScanResult>> futures = new ArrayList();
            for (int port = 1; port <= 65535; port++) {
                futures.add(portIsOpen(es, ipAddr, port, timeout));
            }
            es.shutdown();
            CharSink chs = Files.asCharSink(file, UTF_8, APPEND);
            try
            {
                for (final Future<PortScanResult> f : futures) {
                    if(f.get() != null) {
                        PortScanResult portResult = new PortScanResult(f.get().getIp(), f.get().getPort(), f.get().isOpen());
                        if(!isElementInArray(oldPortScanResults, portResult)) {
                            portScanResults.add(portResult);
                            chs.write(f.get().getIp() + ":");
                            chs.write(f.get().getPort() + ":");
                            chs.write(String.valueOf(f.get().isOpen()));
                            chs.write(System.lineSeparator());
                            System.out.println("wrote " + portResult);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return portScanResults;
    }

    private boolean isElementInArray(ArrayList<PortScanResult> oldPortScanResults, PortScanResult portResult) {
        for (PortScanResult portScanResult : oldPortScanResults) {
             if(portResult.equals(portScanResult)) {
                return true;
             }
        }
        return false;
    }

    public ArrayList<PortScanResult> getOldResultsByHost(String host){
        ArrayList<PortScanResult> oldResults = new ArrayList<>();
        try{
            List<String> lines = Files.readLines(new File(OUTPUT_FILE_NAME), Charsets.UTF_8);
            for (String line : lines) {
                if(!line.equals("")){
                    String[] splittedLine = line.split(":"); //host,port,isOpen
                    if(splittedLine[0].equals(host)) {
                        oldResults.add(new PortScanResult(splittedLine[0], Integer.valueOf(splittedLine[1]), Boolean.valueOf(splittedLine[2])));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return  oldResults;
    }
}
