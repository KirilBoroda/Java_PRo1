package webs.hillel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "log4j2.xml");

        CoffeeOrderBoard ord = new CoffeeOrderBoard();
        logger.info("Creating CoffeeOrderBoard instance");

        try {
            ord.add("Vladimir");
            logger.info("Added order for Vladimir");

            ord.add("Oleg");
            logger.info("Added order for Oleg");

            ord.add("Andrew");
            logger.info("Added order for Andrew");

            ord.add("Rambo");
            logger.info("Added order for Rambo");

            logger.info(ord.draw());

            ord.deliver();
            logger.info("Delivered the next order");

            logger.info(ord.draw());

            ord.deliver(2);
            logger.info("Delivered order with orderNumber 2");

            logger.info(ord.draw());

            ord.deliver(4);
            logger.info("Delivered order with orderNumber 4");

            logger.info(ord.draw());
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
        }
    }
}
