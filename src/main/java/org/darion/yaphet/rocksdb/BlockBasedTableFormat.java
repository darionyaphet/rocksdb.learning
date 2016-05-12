package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * By default, a database uses block-based table.
 * <p>
 * https://github.com/facebook/rocksdb/wiki/Rocksdb-BlockBasedTable-Format
 */
public class BlockBasedTableFormat {
    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();
        Options options = new Options();
        options.setCreateIfMissing(true);
        options.allowMmapReads();
        options.allowMmapWrites();
        options.allowOsBuffer();
        options.setWalSizeLimitMB(1);
        options = options.setArenaBlockSize(4096);

        RocksDB client = RocksDB.open(options, "/tmp/rocks.db");
        for (long index = 0; index < 1024 * 1024 * 8; index++) {
            if (index % (1024 * 1024) == 0) {
                System.out.println(index);
            }
            client.put((index + "").getBytes(), (index + "").getBytes());
        }
        client.close();
    }
}
