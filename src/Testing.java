import Control.RobotInstructionInterface;

public class Testing {
    private static RobotInstructionInterface entity;

    public static void main(String[] args) {
        entity = new RobotInstructionInterface();
        execute();
    }

    /**
     * Demo method to showcase functionalies.
     */
    private static void execute() {
        int speed = 10;
        try {
            while (true) {
                entity.getPortList();
                entity.connectSerial(0);
//                System.out.println("laukiu 5 sek");
//                Thread.sleep(5000);

                entity.actuate(0, 5);
                //Thread.sleep(50);

                for (int i = 0; i < 10; i++) {

                    entity.actuate(1, speed);
                    //Thread.sleep(50);

                }


                for (int i = 0; i < 10; i++) {
                    entity.actuate(1, -speed);
                    //Thread.sleep(50);

                }
                for (int i = 0; i < 10; i++) {
                    entity.actuate(2, speed);
                    //Thread.sleep(50);

                }
                for (int i = 0; i < 10; i++) {
                    entity.actuate(2, -speed);
                    //Thread.sleep(50);

                }
                for (int i = 0; i < 10; i++) {
                    entity.actuate(3, speed);
                    //Thread.sleep(50);

                }
                for (int i = 0; i < 10; i++) {
                    entity.actuate(3, -speed);
                    //Thread.sleep(50);

                }


//            Thread t1 = new Thread(() -> {
//                try {
//                    for (int i = 0;i<20;i++) {
//                        entity.actuate(1, 9);
//                        //Thread.sleep(500);
//
//                    }
//                } catch (Exception e) {
//                    // handle: log or throw in a wrapped RuntimeException
//                    throw new RuntimeException("InterruptedException caught in lambda", e);
//                }
//            });
//            t1.run();




        /*
        int n, n2;
        try(Scanner reader = new Scanner(System.in)) {
            while (true) {
                if(reader.hasNextInt()) {
                    n = reader.nextInt();
                    n2 = reader.nextInt();

                    actuate(n,n2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        actuate(0,10);
        actuate(1,-10);*/
                Thread.sleep(500);

                entity.disconnectSerial();
                System.out.println("atsijungiau");
                Thread.sleep(2000);
                System.out.println("pabaiga");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
