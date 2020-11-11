package application.model;

import java.util.Random;

public enum KlotzTypeModel {
	LKlotz, IKlotz, ZKlotz, OKlotz, TKlotz, NoKlotz;
	
	private static final KlotzTypeModel[] VALUES = {LKlotz, IKlotz, ZKlotz, OKlotz, TKlotz};
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();
	
	public static KlotzTypeModel randomKlotzType() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}
}
