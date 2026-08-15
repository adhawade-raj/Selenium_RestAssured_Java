package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import java.util.List;

public class CalendarHandling {

    Utils utils = new Utils();

    By dateClicker = By.xpath("//p[@data-testid='departureDate']");
    By monthDates = By.xpath("(//div[@class='react-calendar__month-view__days'])[1]//button[@type='button']");
    By currentDate = By.xpath("//span[@class='react-calendar__navigation__label__labelText react-calendar__navigation__label__labelText--from']");
    By monthPicker = By.xpath("//button/span[@class='react-calendar__navigation__label__labelText react-calendar__navigation__label__labelText--from']");
    By nextButton = By.xpath("//button[@class='react-calendar__navigation__arrow react-calendar__navigation__next-button']");

    public void openDatePicker() {
        utils.findElement(dateClicker).click();
    }

    public void getCurrentDate() {
        openDatePicker();
        String currentDateValue = utils.findElement(currentDate).getText();
        System.out.println("Current Month & Year: " + currentDateValue);
    }

        public  void selectMonth() {
            openDatePicker();
            String currentMonth = utils.findElement(currentDate).getText();

            while(!currentMonth.equals("October 2026")){
                utils.findElement(nextButton).click();
                currentMonth = utils.findElement(currentDate).getText();
            }

        }

        public void selectDate() {
            openDatePicker();
            utils.findElement(dateClicker).click();
            utils.commonSleep(5);

            // Collect all date buttons in the current month view
            List<WebElement> allRows = utils.findElements(monthDates);
            utils.commonSleep(1);

            for (WebElement row : allRows) {
                String text = row.getText().trim();
                System.out.println("Date found: " + text);

                if(text.contains("21")) {
                    row.click();
                    System.out.println("Selected current date: " + text);
                    break;
                }
            }
        }


    //** Select a specific date from the calendar by providing the month, year, and date as parameters.
    // The method navigates through the calendar until it finds the specified month and year,
    // then selects the specified date. */
        public void datePicker(String Month,String Year, String Date ) throws InterruptedException {
            utils.findElement(dateClicker).click();
            utils.commonSleep(5);

            String currentMonth = utils.findElement(currentDate).getText();

            while(!currentMonth.equals(Month+" "+Year)){
                utils.findElement(nextButton).click();
                currentMonth = utils.findElement(currentDate).getText();
            }

            List<WebElement> allRows = utils.findElements(monthDates);
            utils.commonSleep(1);

            for (WebElement row : allRows) {
                String text = row.getText().trim();
                System.out.println("Date found: " + text);

                if(text.contains(Date)) {
                    row.click();
                    System.out.println("Selected date: " + text);
                    break;
                }
            }
        }
    }
