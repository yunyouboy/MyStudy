package cn.readsense.mystudy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Author:qyg
 * DATE:2020/5/22 16:16
 * Description：
 **/
public class ReenterSelfLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        /* 是否处于占用状态*/
        @Override
        protected boolean isHeldExclusively() {
            return getState() > 0;
        }

        /* 当状态为0的时候获取锁*/
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                setState(getState() + 1);
                return true;
            } else {
                return false;
            }
        }

        /* 释放锁，将状态设置为0*/
        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(getState() - 1);
            if (getState() == 0) {
                setExclusiveOwnerThread(null);
            }
            return true;
        }

        /* 返回一个Condition，每个condition都包含了一个condition队列*/
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }
    
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public Condition newCondition() {
        return sync.newCondition();
    }
}
