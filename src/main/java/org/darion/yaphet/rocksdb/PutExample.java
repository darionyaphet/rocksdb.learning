package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PutExample {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/basic.db");

        try {
            for (String line : Files.readAllLines(Paths.get("/usr/share/dict/words"))) {
                client.put(line.toLowerCase().getBytes(), line.toUpperCase().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
