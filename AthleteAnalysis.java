package questionOne;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author NUR ADLIN FADHLINA BINTI 1HEDILISYAM
 */
public class AthleteAnalysis {

	public static void main(String[] args) throws FileNotFoundException {
		String[] name;
		String[] gender;
		int[] age;
		double[] height;
		double[] weight;
		String[] sport;
		String[] medal;

		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number to choose a form of data entry:\n"
				+ "[1]: Read data from a file\n[2]: Enter data manually");
		int choice = input.nextInt();
		if (choice == 1) {

			File data = new File("data.txt");
			Scanner read = new Scanner(data);
			name = new String[20];
			gender = new String[20];
			age = new int[20];
			height = new double[20];
			weight = new double[20];
			sport = new String[20];
			medal = new String[20];

			read.useDelimiter(",");
			int num = read.nextInt();
			read.nextLine();
			for (int i = 0; i < num; i++) {
				name[i] = read.next();
				gender[i] = read.next();
				age[i] = Integer.parseInt(read.next());
				height[i] = Double.parseDouble(read.next());
				weight[i] = Double.parseDouble(read.next());
				sport[i] = read.next();
				String medal1 = read.nextLine();
				String medal2 = medal1.substring(1, medal1.length());
				medal[i] = medal2;

			}

			input.close();
			ageAnalysis(gender, age);// calculating the ratio between the gender, mean, and standard deviation of the
										// age
			heightAnalysis(gender, height);// calculating the ratio, mean, and standard deviation of the height
			weightAnalysis(gender, weight);// calculating the ratio, mean, and standard deviation of the weight
			ageFilter(age, gender, name);// finding the oldest and youngest athlete
			sportAnalysis(sport);// calculating the sports available
			medalAnanlysis(medal, gender);// calculating the medals obtained by the athletes
			medalSportAnalysis(gender, sport, medal);// calculating the medals obtained by the athletes for each sport
			printDetails(name, gender, age, height, weight, sport, medal); /// printing the athlete's details
		} else if (choice == 2) {

			System.out.println("Enter the number of entry:");
			int sum = input.nextInt();
			name = new String[sum];
			gender = new String[sum];
			age = new int[sum];
			height = new double[sum];
			weight = new double[sum];
			sport = new String[sum];
			medal = new String[sum];
			// sportList;
			input(name, gender, age, height, weight, sport, medal);// inserting the data to the array
			ageAnalysis(gender, age);// calculating the ratio between the gender, mean, and standard deviation of the
										// age
			heightAnalysis(gender, height);// calculating the ratio, mean, and standard deviation of the height
			weightAnalysis(gender, weight);// calculating the ratio, mean, and standard deviation of the weight
			ageFilter(age, gender, name);// finding the oldest and youngest athlete
			sportAnalysis(sport);// calculating the sports available
			medalAnanlysis(medal, gender);// calculating the medals obtained by the athletes
			medalSportAnalysis(gender, sport, medal);// calculating the medals obtained by the athletes for each sport
			printDetails(name, gender, age, height, weight, sport, medal); /// printing the athlete's details
		} else {
			System.out.println("Invalid choice. Please run again.");

		}

	}

	public static void input(String[] name, String[] gender, int[] age, double[] height, double[] weight,
			String[] sport, String[] medal) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < name.length; i++) {
			System.out.println("Athele " + (i + 1) + " details:");
			System.out.println("Enter the athele's name: ");
			name[i] = input.next();

			System.out.println("Enter the athele's gender (type in 'female' or 'male'): ");
			while (true) {
				String geninput = input.next();
				if (geninput.equalsIgnoreCase("Female") || geninput.equalsIgnoreCase("Male")
						|| geninput.equalsIgnoreCase("f") || geninput.equalsIgnoreCase("m")) {
					gender[i] = geninput;
					break;
				} else {
					System.out.println("Invalid input. Please enter accordingly.");
				}
			}

			System.out.println("Enter the athele's age: ");
			age[i] = input.nextInt();

			System.out.println("Enter the athele's height: ");
			height[i] = input.nextDouble();

			System.out.println("Enter the athele's weight: ");
			weight[i] = input.nextDouble();

			System.out.println("Enter the athele's sport: ");
			sport[i] = input.next();

			System.out.println("Enter the athele's medal (type in either gold, silver or bronze): ");
			while (true) {
				String medalinput = input.next();
				if (medalinput.equalsIgnoreCase("gold") || medalinput.equalsIgnoreCase("silver")
						|| medalinput.equalsIgnoreCase("bronze")) {
					medal[i] = medalinput;
					break;
				} else {
					System.out.println("Invalid input. Please enter accordingly.");
				}
			}
			System.out.println();
		}
	}
//
	public static void ageAnalysis(String[] gender, int[] age) {
		int mCount = 0;
		int fCount = 0;

		for (String gen : gender) {
			if (gen.equalsIgnoreCase("Female") || gen.equalsIgnoreCase("f")) {
				fCount++;
			} else {
				mCount++;
			}
		}

		// 1. Calculate the gender ratio
		double ratio = mCount / fCount;
		System.out.println(
				"\nThe ratio between the female and male athletes are: " + String.format("%.2f", ratio) + "\n");

		// AGE BASED ON GENDER

		double fAgeSum = 0;
		double mAgeSum = 0;
		int[] fAge = new int[gender.length];
		int[] mAge = new int[gender.length];
		for (int i = 0; i < gender.length; i++) {
			if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
				fAgeSum += age[i];
				fAge[i] = age[i];
			} else {
				mAgeSum += age[i];
				mAge[i] = age[i];
			}
		}
		// 2. the mean of the group’s age for each gender

		double fAgeMean = fAgeSum / fCount;
		double mAgeMean = mAgeSum / mCount;
		System.out.println("The mean of the age for female athletes are " + String.format("%.2f", fAgeMean));
		System.out.println("The mean of the age for male athletes are " + String.format("%.2f", mAgeMean) + "\n");

		// 2. the standard deviation of the group’s age for each gender
		double fAgeDiffSq = 0;
		for (int i = 0; i < fAge.length; i++) {
			if (fAge[i] != 0) {
				fAgeDiffSq += Math.pow((fAge[i] - fAgeMean), 2);
			}
		}
		double divAgefDiffSq = fAgeDiffSq / (fCount);
		double fAgeSd = Math.sqrt(divAgefDiffSq);

		double mAgeDiffSq = 0;
		for (int i = 0; i < mAge.length; i++) {
			if (mAge[i] != 0) {
				mAgeDiffSq += Math.pow((mAge[i] - mAgeMean), 2);
			}
		}
		double divAgemDiffSq = mAgeDiffSq / mAge.length;
		double mAgeSd = Math.sqrt(divAgemDiffSq);

		System.out
				.println("The standard deviation of the age for female athletes are " + String.format("%.2f", fAgeSd));
		System.out.println(
				"The standard deviation of the age for male athletes are " + String.format("%.2f", mAgeSd) + "\n");
	}

	public static void heightAnalysis(String[] gender, double[] height) {
		double mCount = 0;
		double fCount = 0;

		for (String gen : gender) {
			if (gen.equalsIgnoreCase("Female") || gen.equalsIgnoreCase("f")) {
				fCount++;
			} else {
				mCount++;
			}
		}
		// HEIGHT BASED ON GENDER
		double fHeightSum = 0;
		double mHeightSum = 0;
		double[] fHeight = new double[height.length];
		double[] mHeight = new double[height.length];
		for (int i = 0; i < gender.length; i++) {
			if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
				fHeightSum += height[i];
				fHeight[i] = height[i];
			} else {
				mHeightSum += height[i];
				mHeight[i] = height[i];
			}
		}
		// 3. the mean of the group’s height for each gender
		double fHeightMean = fHeightSum / fCount;
		double mHeightMean = mHeightSum / mCount;
		System.out.println("The mean of the height for female athletes are " + String.format("%.2f", fHeightMean));
		System.out.println("The mean of the height for male athletes are " + String.format("%.2f", mHeightMean) + "\n");

		// 3. the standard deviation of the group’s height for each gender
		double fHeightDiffSq = 0;
		for (int i = 0; i < fHeight.length; i++) {
			if (fHeight[i] != 0) {
				fHeightDiffSq += Math.pow((fHeight[i] - fHeightMean), 2);
			}
		}
		double divHeightfDiffSq = fHeightDiffSq / (fCount);
		double fHeightSd = Math.sqrt(divHeightfDiffSq);

		double mHeightDiffSq = 0;
		for (int i = 0; i < mHeight.length; i++) {
			if (mHeight[i] != 0) {
				mHeightDiffSq += Math.pow((mHeight[i] - mHeightMean), 2);
			}
		}
		double divHeightmDiffSq = mHeightDiffSq / mHeight.length;
		double mHeightSd = Math.sqrt(divHeightmDiffSq);

		System.out.println(
				"The standard deviation of the height for female athletes are " + String.format("%.2f", fHeightSd));
		System.out.println("The standard deviation of the height for male athletes are "
				+ String.format("%.2f", mHeightSd) + "\n");
	}

	public static void weightAnalysis(String[] gender, double[] weight) {

		double mCount = 0;
		double fCount = 0;

		for (String gen : gender) {
			if (gen.equalsIgnoreCase("Female") || gen.equalsIgnoreCase("f")) {
				fCount++;
			} else {
				mCount++;
			}
		}
		// WEIGHT BASED ON GENDER
		double fWeightSum = 0;
		double mWeightSum = 0;
		double[] fWeight = new double[weight.length];
		double[] mWeight = new double[weight.length];
		for (int i = 0; i < gender.length; i++) {
			if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
				fWeightSum += weight[i];
				fWeight[i] = weight[i];
			} else {
				mWeightSum += weight[i];
				mWeight[i] = weight[i];
			}
		}
		// 3. the mean of the group’s weight for each gender
		double fWeightMean = fWeightSum / fCount;
		double mWeightMean = mWeightSum / mCount;
		System.out.println("The mean of the weight for female athletes are " + String.format("%.2f", fWeightMean));
		System.out.println("The mean of the weight for male athletes are " + String.format("%.2f", mWeightMean) + "\n");

		// 3. the standard deviation of the group’s weight for each gender
		double fWeightDiffSq = 0;
		for (int i = 0; i < fWeight.length; i++) {
			if (fWeight[i] != 0) {
				fWeightDiffSq += Math.pow((fWeight[i] - fWeightMean), 2);
			}
		}
		double divWeightfDiffSq = fWeightDiffSq / (fCount);
		double fWeightSd = Math.sqrt(divWeightfDiffSq);

		double mWeightDiffSq = 0;
		for (int i = 0; i < mWeight.length; i++) {
			if (mWeight[i] != 0) {
				mWeightDiffSq += Math.pow((mWeight[i] - mWeightMean), 2);
			}
		}
		double divWeightmDiffSq = mWeightDiffSq / mWeight.length;
		double mWeightSd = Math.sqrt(divWeightmDiffSq);

		System.out.println(
				"The standard deviation of the weight for female athletes are " + String.format("%.2f", fWeightSd));
		System.out.println("The standard deviation of the weight for male athletes are "
				+ String.format("%.2f", mWeightSd) + "\n");

	}

	public static void ageFilter(int[] age, String[] gender, String[] name) {
		int fOldest = Integer.MIN_VALUE;
		String fOldestName = "";
		int mOldest = Integer.MIN_VALUE;
		;
		String mOldestName = "";

		int fYoung = Integer.MAX_VALUE;
		int mYoung = Integer.MAX_VALUE;
		String fYoungName = "";
		String mYoungName = "";
		// 5. calculate and indicate the name and age of the oldest athlete for each
		// gender
		for (int i = 0; i < gender.length; i++) {
			if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
				if (age[i] > fOldest) {
					fOldest = age[i];
					fOldestName = name[i];
				}
				if (age[i] < fYoung) {
					fYoung = age[i];
					fYoungName = name[i];
				}
			}
			if (gender[i].equalsIgnoreCase("male") || gender[i].equalsIgnoreCase("m")) {
				if (age[i] > mOldest) {
					mOldest = age[i];
					mOldestName = name[i];
				}
				if (age[i] < mYoung) {
					mYoung = age[i];
					mYoungName = name[i];
				}
			}
		}

		System.out.println("The oldest female athlete is " + fOldestName + " (Age: " + fOldest + ")");
		System.out.println("The oldest male athlete is " + mOldestName + " (Age: " + mOldest + ")" + "\n");

		// 6. calculate and indicate the name and age of the youngest athlete for each
		// gender

		System.out.println("The youngest female athlete is " + fYoungName + " (Age: " + fYoung + ")");
		System.out.println("The youngest male athlete is " + mYoungName + " (Age: " + mYoung + ")" + "\n");
	}

	public static void sportAnalysis(String[] sport) {
		// 7. calculate how many unique sports are available in the data and print them
		// out,

		String[] sportList = new String[sport.length];
		int sportCount = 0;

		for (String s : sport) {
			boolean sportsAvailable = true;
			for (int i = 0; i < sportCount; i++) {
				if (sportList != null && sportList[i].equals(s)) {
					sportsAvailable = false;
					break;
				}
			}
			if (sportsAvailable) {
				sportList[sportCount] = s;
				sportCount++;
			}
		}
		System.out.println("The number of sports available from the data given is " + sportCount);
		System.out.println("Sports available from the data given:");
		for (int i = 0; i < sportCount; i++) {
			System.out.println(sportList[i]);
		}
		System.out.println();
	}

	public static void medalAnanlysis(String[] medal, String[] gender) {
		int fMedalCount = 0;
		int fGold = 0;
		int fSilver = 0;
		int fBronze = 0;

		int mMedalCount = 0;
		int mGold = 0;
		int mSilver = 0;
		int mBronze = 0;
		// 8. calculate how many medals each gender gets in all sports for each medal
		// type
		for (int i = 0; i < gender.length; i++) {
			if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
				fMedalCount++;
				if (medal[i].equalsIgnoreCase("gold")) {
					fGold++;
				} else if (medal[i].equalsIgnoreCase("silver")) {
					fSilver++;
				} else {
					fBronze++;
				}
			} else {
				mMedalCount++;
				if (medal[i].equalsIgnoreCase("gold")) {
					mGold++;
				} else if (medal[i].equalsIgnoreCase("silver")) {
					mSilver++;
				} else {
					mBronze++;
				}
			}
		}
		System.out.println("The amount of medal obtained by male athletes are " + mMedalCount
				+ "\nBelow are the amount of medal obtained by male athletes for each medal type: ");
		System.out.println("GOLD: " + mGold);
		System.out.println("SILVER: " + mSilver);
		System.out.println("BRONZE: " + mBronze + "\n");

		System.out.println("The amount of medal obtained by female athletes are " + fMedalCount
				+ "\nBelow are the amount of medal obtained by female athletes for each medal type: ");
		System.out.println("GOLD: " + fGold);
		System.out.println("SILVER: " + fSilver);
		System.out.println("BRONZE: " + fBronze + "\n");
	}

	public static void medalSportAnalysis(String[] gender, String[] sport, String[] medal) {
		int fMedalCount = 0;
		int fGold = 0;
		int fSilver = 0;
		int fBronze = 0;

		int mMedalCount = 0;
		int mGold = 0;
		int mSilver = 0;
		int mBronze = 0;
		String[] sportList = new String[sport.length];
		int sportCount = 0;
		for (int i = 0; i < sport.length; i++) {
			boolean sportsAvailable = true;
			for (int j = 0; j < sportCount; j++) {
				if (sport[i].equals(sport[j])) {
					sportsAvailable = false;
					break;
				}
			}
			if (sportsAvailable) {
				sportList[sportCount] = sport[i];
				sportCount++;

			}
		}
		String[] sL = new String[sportCount];
		System.arraycopy(sportList, 0, sL, 0, sportCount);
		// 9. calculate how many medals each gender gets in each sport for each medal
		// type
		for (String s : sL) {
			for (int i = 0; i < sL.length; i++) {
				if (sport[i].equals(s)) {
					if (gender[i].equalsIgnoreCase("Female") || gender[i].equalsIgnoreCase("f")) {
						fMedalCount++;
						if (medal[i].equalsIgnoreCase("gold")) {
							fGold++;
						} else if (medal[i].equalsIgnoreCase("silver")) {
							fSilver++;
						} else {
							fBronze++;
						}
					}

					else {
						mMedalCount++;
						if (medal[i].equalsIgnoreCase("gold")) {
							mGold++;
						} else if (medal[i].equalsIgnoreCase("silver")) {
							mSilver++;
						} else {
							mBronze++;
						}

					}
				}
			}
			System.out.println("The amount of medal obtained by male athletes for " + s + " are " + mMedalCount
					+ "\nBelow are the amount of medal obtained by male athletes for " + s + " for each medal type: ");
			System.out.println("GOLD: " + mGold);
			System.out.println("SILVER: " + mSilver);
			System.out.println("BRONZE: " + mBronze + "\n");

			System.out.println("The amount of medal obtained by female athletes for " + s + " are " + fMedalCount
					+ "\nBelow are the amount of medal obtained by female athletes for " + s
					+ " for each medal type: ");
			System.out.println("GOLD: " + fGold);
			System.out.println("SILVER: " + fSilver);
			System.out.println("BRONZE: " + fBronze + "\n");

		}
	}

	public static void printDetails(String[] name, String[] gender, int[] age, double[] height, double[] weight,
			String[] sport, String[] medal) {
		// 10. print the athletes’ details as a table to use for this task
		System.out.println(" =================================================================================");
		System.out.printf("| %-20s | %-6s | %-6s | %-6s | %-6s | %-10s | %-6s |\n", "Name", "Gender", "Age", "Height",
				"Weight", "Sport", "Medal");
		System.out.println(" =================================================================================");

		for (int i = 0; i < name.length; i++) {
			System.out.printf("| %-20s | %-6s | %-6s | %-6s | %-6s | %-10s | %-6s |\n", name[i], gender[i], age[i],
					height[i], weight[i], sport[i], medal[i]);
			System.out.println(" ---------------------------------------------------------------------------------");
		}

	}
	// source:(w3school)
}
