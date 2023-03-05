package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile pro;
        try(FileChannel inChannel = FileChannel.open(file.toPath())) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            StringBuilder stri= new StringBuilder();
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    stri.append((char) buffer.get());
                }

            }
            String stra = stri.toString();
            String [] pairs = stra.split("\n");
            String [] fin = new String[pairs.length];
            for (int i = 0; i < pairs.length; i++) {
                fin[i] = pairs[i].split(": ")[1].trim();

            }
            pro = new Profile(fin[0], Integer.parseInt(fin[1]), fin[2], Long.parseLong(fin[3]));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return pro;
    }
}
