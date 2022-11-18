package web.tools.driver.hardcode;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.Test;

class DriverUnzip {
    @Test
    public static void unZip() throws IOException {
        String directory = "target/driver/selenium-" + DriverLoad.getSeleniumVersion();
        String archivePath = "target/driver/selenium" + DriverLoad.getSeleniumVersion() + ".zip";
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(archivePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(directory + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            fis.close();
            File file
                    = new File(archivePath);
            if (file.delete()) System.out.println("The archive has been successfully deleted");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
