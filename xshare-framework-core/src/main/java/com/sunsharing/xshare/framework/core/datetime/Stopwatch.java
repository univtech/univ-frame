/**
 * 
 */
package com.sunsharing.xshare.framework.core.datetime;

/**
 * 秒表。<br>
 * 
 * @author Kison - 2016年11月17日
 */
public final class Stopwatch {

    /** 秒表的开始时间 */
    private long startTime;

    /**
     * 创建秒表，把秒表的开始时间重置为当前系统时间。
     */
    private Stopwatch() {
        reset();
    }

    /**
     * 重置秒表，把秒表的开始时间重置为当前系统时间。
     */
    public void reset() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 开启秒表，把秒表的开始时间重置为当前系统时间。
     * 
     * @return Stopwatch对象
     */
    public static Stopwatch start() {
        return new Stopwatch();
    }

    /**
     * 停止秒表，计算秒表的运行时间。
     * 
     * @return 秒表的运行时间（毫秒）
     */
    public long stop() {
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
