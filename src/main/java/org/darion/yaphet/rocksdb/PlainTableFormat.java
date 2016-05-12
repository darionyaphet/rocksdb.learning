package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

/**
 * Right now we have two types of tables: "plain table" and "block based table".
 */
public class PlainTableFormat {
    public static void main(String[] args) {
        RocksDB.loadLibrary();
        Options options = new Options();

        // To enjoy the benefits provided by plain table, you have to enable
        // allow_mmap_reads for plain table.
        options.allowMmapReads();
    }
}
