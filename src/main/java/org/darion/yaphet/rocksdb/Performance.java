package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class Performance {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) throws RocksDBException {


        Options options = new Options()
                .setCreateIfMissing(true)
                .setWriteBufferSize(1024 * 1024 * 1024);

//        MemTableConfig memTableConfig = new HashSkipListMemTableConfig();
//        options.setMemTableConfig(memTableConfig);

//        WriteOptions writeOptions = new WriteOptions()
//                .setDisableWAL(false);

        RocksDB client = RocksDB.open(options, "/tmp/performance.db");
        long start = System.currentTimeMillis();
        for (long index = 0; index < 1024 * 1024 * 64; index++) {

            byte[] array = new byte[8];
            for (int j = 0; j < 8; ++j) {
                int offset = 64 - (j + 1) * 8;
                array[j] = (byte) ((index >> offset) & 0xff);
            }

            client.put(array, array);
        }

        System.out.println(System.currentTimeMillis() - start);
        client.close();
        options.dispose();
    }
}
