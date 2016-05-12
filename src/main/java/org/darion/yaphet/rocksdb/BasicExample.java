package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * https://github.com/facebook/rocksdb/wiki/RocksJava-Basics
 */
public class BasicExample {

    public static void main(String[] args) throws IOException {
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behavior of a database.
        Options options = new Options().setCreateIfMissing(true);
        RocksDB db = null;
        try {
            // a factory method that returns a RocksDB instance
            db = RocksDB.open(options, "/tmp/db");
            // do something
        } catch (RocksDBException e) {
            // do some error handling
        }

        for (String line : Files.readAllLines(Paths.get("/usr/share/dict/words"))) {
            try {
                db.put(line.toLowerCase().getBytes(), line.toUpperCase().getBytes());
            } catch (RocksDBException e) {
                e.printStackTrace();
            }
        }

        db.close();
    }
}
