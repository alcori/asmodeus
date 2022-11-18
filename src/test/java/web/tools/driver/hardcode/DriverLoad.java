package web.tools.driver.hardcode;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class DriverLoad {

    private static String version;
    private static String url;

    @Test
    public static String getSeleniumVersion() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("pom.xml"));
        while (scanner.hasNext()) {
            String line = scanner.nextLine().toLowerCase();
            if (line.contains("selenium-java")) {
                version = scanner.nextLine().replaceAll("[^0-9.-]", "");
                return version;
            }
        }
        return null;
    }

    @Test
    public void setupSelenium() throws IOException {
        getSeleniumVersion();
        url = "https://github.com/SeleniumHQ/selenium/releases/download/selenium-" + version + "/selenium-java-" + version + ".zip";
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream("target/driver/selenium" + version + ".zip")) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        }
        try {
            DriverUnzip.unZip();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
