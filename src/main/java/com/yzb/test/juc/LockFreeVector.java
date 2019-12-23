package com.yzb.test.juc;

import lombok.AllArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 无锁的Vector实现
 *
 * 可以根据需求动态扩展其内部空间
 */
public class LockFreeVector<E> {
    static final int N_BUCKET = 30;
    static final int FIRST_BUCKET_SIZE = 8;

    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private AtomicReference<Descriptor<E>> descriptor;

    @AllArgsConstructor
    static class Descriptor<E> {
        int size;
        volatile WriteDescriptor<E> writeOp;

        public void completeWrite() {
            WriteDescriptor tempOp = writeOp;
            if (tempOp != null) {
                tempOp.doIt();
                writeOp = null;
            }
        }
    }

    @AllArgsConstructor
    static class WriteDescriptor<E> {
        E oldV;
        E newV;
        AtomicReferenceArray<E> addr;
        int addrIdx;

        public void doIt() {
            addr.compareAndSet(addrIdx, oldV, newV);
        }
    }

    public LockFreeVector() {
        buckets = new AtomicReferenceArray<>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<>(FIRST_BUCKET_SIZE));
        descriptor = new AtomicReference<>(new Descriptor<>(0, null));
    }

    public void push(E e) {
        Descriptor<E> desc;
        Descriptor<E> newd;

//        do {
//            desc = descriptor.get();
//            desc.completeWrite();
//
//            int pos = desc.size + FIRST_BUCKET_SIZE;
//            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
//
//        } while ();
    }
}
