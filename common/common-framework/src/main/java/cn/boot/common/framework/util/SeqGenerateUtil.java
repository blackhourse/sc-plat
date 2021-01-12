package cn.boot.common.framework.util;

import cn.hutool.core.date.DateUtil;

import java.net.InetAddress;
import java.util.Date;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-12
 **/
public class SeqGenerateUtil {
    private final long workerId;
    private final static long twepoch = 1288834974657L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    private final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    private final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    private static class SeqGenerateHelper {
        private final static SeqGenerateUtil sg = new SeqGenerateUtil();
    }

    public SeqGenerateUtil(final long workerId) {
        super();
        if (workerId > SeqGenerateUtil.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", SeqGenerateUtil.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public SeqGenerateUtil() {
        this.workerId = getAddress() % (SeqGenerateUtil.maxWorkerId + 1);
    }

    private static long getAddress() {
        try {
            String currentIpAddress = InetAddress.getLocalHost().getHostAddress();
            String[] str = currentIpAddress.split("\\.");
            StringBuilder hardware = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                hardware.append(str[i]);
            }
            return Long.parseLong(hardware.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2L;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & SeqGenerateUtil.sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << SeqGenerateUtil.workerIdShift)
                | (this.sequence);
        // System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
        // + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
        // + workerId + ",sequence:" + sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static String getId() {
        String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        return format + SeqGenerateHelper.sg.nextId();
    }

    public static void main(String[] args) {
//         SEQGenerate worker2 = new SEQGenerate(10);
        System.out.println(SeqGenerateUtil.getId());
    }
}
