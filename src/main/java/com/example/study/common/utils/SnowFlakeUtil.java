package com.example.study.common.utils;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;

import java.util.Calendar;

public class SnowFlakeUtil {

	public static final long EPOCH;

	private static final long SEQUENCE_BITS = 12L;

	private static final long WORKER_ID_BITS = 10L;

	private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

	private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

	private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

	private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

	private  static  TimeService timeService = new TimeService();

	private static long workerId;

	private static int maxTolerateTimeDifferenceMilliseconds = 10;

	static {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.NOVEMBER, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		EPOCH = calendar.getTimeInMillis();
	}

	private byte sequenceOffset;

	private long sequence;

	private long lastMilliseconds;


	public static void setWorkerId(final long workerId) {
		Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
		SnowFlakeUtil.workerId = workerId;
	}


	public static void setMaxTolerateTimeDifferenceMilliseconds(final int maxTolerateTimeDifferenceMilliseconds) {
		SnowFlakeUtil.maxTolerateTimeDifferenceMilliseconds = maxTolerateTimeDifferenceMilliseconds;
	}

	public synchronized Number generateKey() {
		long currentMilliseconds = timeService.getCurrentMillis();
		if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
			currentMilliseconds = timeService.getCurrentMillis();
		}
		if (lastMilliseconds == currentMilliseconds) {
			if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
				currentMilliseconds = waitUntilNextTime(currentMilliseconds);
			}
		} else {
			vibrateSequenceOffset();
			sequence = sequenceOffset;
		}
		lastMilliseconds = currentMilliseconds;
		return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
	}

	@SneakyThrows
	private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
		if (lastMilliseconds <= currentMilliseconds) {
			return false;
		}
		long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
		Preconditions.checkState(timeDifferenceMilliseconds < maxTolerateTimeDifferenceMilliseconds,
				"Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
		Thread.sleep(timeDifferenceMilliseconds);
		return true;
	}

	private long waitUntilNextTime(final long lastTime) {
		long result = timeService.getCurrentMillis();
		while (result <= lastTime) {
			result = timeService.getCurrentMillis();
		}
		return result;
	}

	private void vibrateSequenceOffset() {
		sequenceOffset = (byte) (~sequenceOffset & 1);
	}

	protected static class TimeService {
		public long getCurrentMillis() {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 以后数据库ID统一用string类型
	 */
	public static class ID {
		public static SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil();
		public static String nextId() {
			String id = snowFlakeUtil.generateKey().toString();
			return id;
		}
	}


	public static void main(String[] args) {
		for (int i = 0;i<5;i++){
			System.out.println(i+" ID: "+ SnowFlakeUtil.ID.nextId());
		}
	}

}
