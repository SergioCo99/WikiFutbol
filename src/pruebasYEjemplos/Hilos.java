package pruebasYEjemplos;

public class Hilos {

	public static void method1() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m1");
		}
	}

	public static void method2() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m2");
		}
	}

	public static void method3() {
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m3");
		}
	}

	public static void method4() {
		Thread thread4 = new Thread() {
			public void run() {
				for (int i = 0; i < 15; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("m4");
				}
			}
		};
		thread4.start();
	}

	public static void main(String[] args) {
		method4();

		Thread thread1 = new Thread() {
			public void run() {
				method1();
			}
		};
		thread1.start();

		Thread thread2 = new Thread() {
			public void run() {
				try {
					thread1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				method2();
			}
		};
		thread2.start();

		Thread thread3 = new Thread() {
			public void run() {

				method3();
			}
		};
		thread3.start();
	}
}