package P1;

import java.util.Arrays;
import java.util.List;

public class Time{
	// Abstraction function:
	// 表示一个时间点
	
	// Representation invariant:
	// year，month, day, hour, minute分别记录年、月、日、时、分
	
	// Safety from rep exposure:
	// 所有域均为private final
	// 使用防御性拷贝
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	protected void checkRep() {
		assert this.year >= 0;
		assert this.month >= 0 && this.month <= 12;
		assert this.day >= 0 && this.day <= 30;
		assert this.hour >= 0 && this.hour < 24;
		assert this.minute >= 0 && this.minute < 60;
	}

	//constructor
	private Time(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public static Time getNewTime(int year, int month, int day, int hour, int minute) {
		return new Time(year, month, day, hour, minute);
	}
	
	public static Time getNewTime(String time) {
		List<String> information = Arrays.asList(time.split(" "));
		List<String> information1 = Arrays.asList(information.get(0).split("-"));
		List<String> information2 = Arrays.asList(information.get(1).split(":"));

		return Time.getNewTime(Integer.parseInt(information1.get(0)), Integer.parseInt(information1.get(1)), Integer.parseInt(information1.get(2)),
				Integer.parseInt(information2.get(0)), Integer.parseInt(information2.get(1)));
	}
	
	//methods
	public int getYear() {
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}
	
	/**
	 * 判断时间是否合规
	 * @param t 时间的字符表示
	 * @return 合规返回true，否则返回false
	 */
	public static boolean isLegalTime(String t) {
		List<String> time = Arrays.asList(t.split(" "));
		if (time.size() != 2) {
			return false;
		}
		List<String> big = Arrays.asList(time.get(0).split("-"));
		List<String> small = Arrays.asList(time.get(1).split(":"));

		if (big.size() != 3 || small.size() != 2) {
			return false;
		}

		if (big.get(0).matches("[0-9]+") == false) {
			return false;
		}
		if (big.get(1).matches("[0-9]+") == false) {
			return false;
		}
		if (big.get(2).matches("[0-9]+") == false) {
			return false;
		}
		if (small.get(0).matches("[0-9]+") == false) {
			return false;
		}
		if (small.get(1).matches("[0-9]+") == false) {
			return false;
		}
		
		return true;
	}

	
	/**
	 * 增加时间
	 * @param time 需要增加的时间
	 */
	public void addTime(Time time) {
		this.minute += time.getMinute();
		if (this.minute < 0) {
			this.minute += 60;
			this.hour -= 1;
		}
		if (this.minute >= 60) {
			this.minute -= 60;
			this.hour += 1;
		}
		
		this.hour += time.getHour();
		if (this.hour < 0) {
			this.hour += 24;
			this.day -= 1;
		}
		if (this.hour >= 24) {
			this.hour -= 24;
			this.day += 1;
		}
		
		this.day += time.getDay();
		if (this.day <= 0) {
			this.day += 30;
			this.month -= 1;
		}
		if (this.day > 30) {
			this.day -= 30;
			this.month += 1;
		}
		
		this.month += time.getMonth();
		if (this.month <= 0) {
			this.month += 12;
			this.year -= 1;
		}
		if (this.month > 12) {
			this.month -= 12;
			this.year += 1;
		}
		
		this.year += time.getYear();

		checkRep();
	}

	/**
	 * 检查两个时间是否在同一天
	 * @param time 时间
	 * @return 如果相同返回true,否则返回false
	 */
	public boolean sameday(Time time) {
		return this.year == time.getYear() && this.month == time.getMonth() && this.day == time.getDay();
	}
	
	/**
	 * 比较两个时间的早晚
	 * @param time 另一个时间
	 * @return 若更晚则返回1，更早返回-1，时间相同返回0
	 */
	public int compareto(Time time) {
		if (this.year > time.getYear()) {
			return 1;
		}
		if (this.year < time.getYear()) {
			return -1;
		}
		if (this.month > time.getMonth()) {
			return 1;
		}
		if (this.month < time.getMonth()) {
			return -1;
		}
		if (this.day > time.getDay()) {
			return 1;
		}
		if (this.day < time.getDay()) {
			return -1;
		}
		if (this.hour > time.getHour()) {
			return 1;
		}
		if (this.hour < time.getHour()) {
			return -1;
		}
		if (this.minute > time.getMinute()) {
			return 1;
		}
		if (this.minute < time.getMinute()) {
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		return String.format("%04d", this.year) + "-" + String.format("%02d", this.month) + "-"
				+ String.format("%02d", this.day) + " " + String.format("%02d", this.hour) + ":"
				+ String.format("%02d", this.minute);
	}
	
}
