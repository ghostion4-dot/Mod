package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BackupWorldProcedure {
    public static String pathOfExe = "";
    public static String pathToBackup = "";
    public static int count = 0;
    public static void BackupWorldFile() throws IOException {
        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "save-all");
        File config = new File("config/backup.txt");
        File configFolder = new File("config");
        System.out.println(configFolder.getAbsolutePath());
        if (pathOfExe.isEmpty()) {
            Scanner readFile = new Scanner(config);
            if (readFile.hasNext()) {
                String line = readFile.nextLine();
                line = line.replace(" ", "");
                String[] splitLine = line.split(":");
                if (splitLine.length > 1) {
                    pathOfExe = splitLine[1];
                } else {
                    System.out.println("Add a path to the executable file");
                }
            } else {
                try (FileWriter bw = new FileWriter(config)) {
                    bw.write("Absolute path to executable: \nRelative path to backup file: ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } if (pathToBackup.isEmpty()) {
            Scanner readFile = new Scanner(config);
            if (readFile.hasNext()) {
                String line = readFile.nextLine();
                System.out.println(line);
                if (readFile.hasNext()) {
                    line = readFile.nextLine();
                    System.out.println(line);
                }
                line = line.replace(" ", "");
                String[] splitLine = line.split(":");
                System.out.println(line);
                if (splitLine.length > 1) {
                    pathToBackup = splitLine[1];
                } else {
                    System.out.println("Add a path to the backup file");
                    System.out.println(Arrays.toString(splitLine));
                }
            } else {
                try (FileWriter bw = new FileWriter(config)) {
                    bw.write("Absolute path to executable: \nRelative path to backup file: ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } if (!pathToBackup.isEmpty() & !pathOfExe.isEmpty()) {
            try {
                count = ((Toolsarmorprogression.tickCounter/24000));
                ProcessBuilder processBuilder = new ProcessBuilder(pathOfExe, configFolder.getAbsolutePath() , pathToBackup + "/world" + count + ".tar.gz");
                processBuilder.inheritIO();
                Process process = processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
