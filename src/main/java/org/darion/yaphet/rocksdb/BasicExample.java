package org.darion.yaphet.rocksdb;

import org.rocksdb.ColumnFamilyOptions;
import org.rocksdb.Options;
import org.rocksdb.ReadOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;
import org.rocksdb.SkipListMemTableConfig;
import org.rocksdb.Snapshot;
import org.rocksdb.WriteOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://github.com/facebook/rocksdb/wiki/RocksJava-Basics
 */
public class BasicExample {

    public static void main(String[] args) throws IOException, RocksDBException {

        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behavior of a database.
        Options options = new Options().setCreateIfMissing(true);
        options = options.setWalDir("");
        System.out.println("WAL : " + options.walDir());

        SkipListMemTableConfig memTableConfig = new SkipListMemTableConfig();

        ColumnFamilyOptions columnFamilyOptions = new ColumnFamilyOptions();
        System.out.println(columnFamilyOptions.arenaBlockSize());

        // a factory method that returns a RocksDB instance
        RocksDB client = RocksDB.open(options, "/tmp/basic.db");

//        for (String line : Files.readAllLines(Paths.get("/usr/share/dict/words"))) {
//            client.put(line.toLowerCase().getBytes(), line.toUpperCase().getBytes());
//        }

        System.out.println(new String(client.get("workman".getBytes())));

        List<byte[]> keys = Stream.of("workman", "worked", "working", "worker")
                .map(String::getBytes).collect(Collectors.toList());
        for (Map.Entry<byte[], byte[]> entry : client.multiGet(keys).entrySet()) {
            String key = new String(entry.getKey());
            String val = new String(entry.getValue());
            System.out.printf("%-7s --> %-7s\n", key, val);
        }


        RocksIterator iterator = client.newIterator();
        iterator.seekToFirst();
        iterator.seekToLast();

        ReadOptions readOptions = new ReadOptions();
        Snapshot snapshot = readOptions.snapshot();
        System.out.println(snapshot.getSequenceNumber());

        WriteOptions writeOptions = new WriteOptions();
        writeOptions.setDisableWAL(false);

        client.close();
    }
}
