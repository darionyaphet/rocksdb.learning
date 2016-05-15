package org.darion.yaphet.rocksdb;

import org.rocksdb.ReadOptions;

public class TailingIterator {
    public static void main(String[] args) {
        ReadOptions readOptions = new ReadOptions();
        readOptions = readOptions.setTailing(true);


    }
}
