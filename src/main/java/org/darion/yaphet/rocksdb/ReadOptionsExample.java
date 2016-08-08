package org.darion.yaphet.rocksdb;

import org.rocksdb.ReadOptions;

public class ReadOptionsExample {
    public static void main(String[] args) {
        ReadOptions options = new ReadOptions();
        options.setFillCache(false)
                .setTailing(false)
                .setVerifyChecksums(false);
        //.setSnapshot(new Snapshot(1024L));


    }
}
