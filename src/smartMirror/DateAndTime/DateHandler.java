/**
* @author  Marvin Saße
* @version 0.1
* @since 15.05.2019 
*/

package smartMirror.DateAndTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {

	private int year;
	private int month;
	private String monthName;
	private int numDaysOfMonth;
	private int day;

	public DateHandler() {
		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);// + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		translateMonthToName();
	}

	public DateHandler(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
		translateMonthToName();
	}

	/**
	 * Returns the month name
	 * 
	 * @return the
	 */
	public String getMonthAsName() {

		month -= 1;
		translateMonthToName();
		month += 1;
		return this.monthName;

	}

	/**
	 * Returns the month numberer representing as a string.
	 * 
	 * @return the month number as a string
	 */
	public String getMonth() {

		StringBuilder sMonth = new StringBuilder();

		if (month < 10) {
			sMonth.append("0").append(this.month);
		} else {
			sMonth.append(this.month);
		}

		return sMonth.toString();
	}

	/**
	 * Returns the recent month as an integer
	 * 
	 * @return the month as an integer
	 */
	public int getMonthAsInt() {

		return this.month;

	}

	public String getDay() {

		StringBuilder sDay = new StringBuilder();

		if (this.day < 10) {
			sDay.append("0").append(this.day);
		} else {
			sDay.append(this.day);
		}

		return sDay.toString();
	}

	public int getDayAsInt() {
		return this.day;
	}

	/**
	 * Returns the year as a string representative.
	 * 
	 * @return the year as a string
	 */
	public String getYear() {

		return String.valueOf(this.year);

	}

	/**
	 * Returns the year as integer.
	 * 
	 * @return the year
	 */
	public int getYearAsInt() {

		return this.year;

	}

	public String getFullDate() {

		StringBuilder date = new StringBuilder();
		date.append(getDay()).append(".").append(getMonth()).append(".").append(getYear());

		return date.toString();

	}

	private void translateMonthToName() {

		switch (this.month) {

		case 0:
			this.monthName = "Januar";
			this.numDaysOfMonth = 31;
			break;
		case 1:
			this.monthName = "Februar";
			this.numDaysOfMonth = computeLeapYear();
			break;
		case 2:
			this.monthName = "M�rz";
			this.numDaysOfMonth = 31;
			break;
		case 3:
			this.monthName = "April";
			this.numDaysOfMonth = 30;
			break;
		case 4:
			this.monthName = "Mai";
			this.numDaysOfMonth = 31;
			break;
		case 5:
			this.monthName = "Juni";
			this.numDaysOfMonth = 30;
			break;
		case 6:
			this.monthName = "Juli";
			this.numDaysOfMonth = 31;
			break;
		case 7:
			this.monthName = "August";
			this.numDaysOfMonth = 31;
			break;
		case 8:
			this.monthName = "September";
			this.numDaysOfMonth = 30;
			break;
		case 9:
			this.monthName = "Oktober";
			this.numDaysOfMonth = 31;
			break;
		case 10:
			this.monthName = "November";
			this.numDaysOfMonth = 30;
			break;
		case 11:
			this.monthName = "Dezember";
			this.numDaysOfMonth = 31;
			break;

		}

	}

	public static int getNumbersOfDaysForMonth(int theMonth) {

		int days = 0;

		switch (theMonth) {

		case 0:
			days = 31;
			break;
		case 1:
			days = computeLeapYear();
			break;
		case 2:
			days = 31;
			break;
		case 3:
			days = 30;
			break;
		case 4:
			days = 31;
			break;
		case 5:
			days = 30;
			break;
		case 6:
			days = 31;
			break;
		case 7:
			days = 31;
			break;
		case 8:
			days = 30;
			break;
		case 9:
			days = 31;
			break;
		case 10:
			days = 30;
			break;
		case 11:
			days = 31;
			break;

		}

		return days;

	}

	public static String translateMonthToName(int monat) {

		String monatName = new String();

		switch (monat) {

		case 1:
			monatName = "Januar";
			break;
		case 2:
			monatName = "Februar";
			break;
		case 3:
			monatName = "M�rz";
			break;
		case 4:
			monatName = "April";
			break;
		case 5:
			monatName = "Mai";
			break;
		case 6:
			monatName = "Juni";
			break;
		case 7:
			monatName = "Juli";
			break;
		case 8:
			monatName = "August";
			break;
		case 9:
			monatName = "September";
			break;
		case 10:
			monatName = "Oktober";
			break;
		case 11:
			monatName = "November";
			break;
		case 12:
			monatName = "Dezember";
			break;
		}

		return monatName;

	}

	public int getNumDaysOfMonth() {
		return this.numDaysOfMonth;
	}

	/**
	 * computes if a year is a leap year or not!
	 * 
	 * @return days of February if year is a leap year
	 */
	private static int computeLeapYear() {

		int days = 28;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		if ((year % 400) == 0) {
			days = 29;
		} else if ((year % 100) == 0) {
			days = 29;
		} else if ((year % 4) == 0) {
			days = 29;
		}

		return days;

	}

	public String getFullTime() {

		StringBuilder time = new StringBuilder();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		String myTimeformated = sdf.format(date);
		// System.out.println(myTimeformated);

		time.append(myTimeformated);
		return time.toString();

	}

}
