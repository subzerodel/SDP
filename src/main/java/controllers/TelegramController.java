package controllers;

import domain.models.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import services.BuyService;
import services.CustomerService;
import services.ShoesService;

import java.util.List;

public class TelegramController extends TelegramLongPollingBot {

    private ShoesService shoesService;
    private CustomerService customerService;
    private BuyService buyService;
    private BotStatus status;
    private final String USERNAME = "omirdegen_bot";
    private final String TOKEN = "1152301580:AAHN-NijwUkolZVzDkOJfgDgjb9lXI1H8SU";
    private Customer cs=new Customer();

    public TelegramController() {
        shoesService = new ShoesService();
        customerService = new CustomerService();
        buyService = new BuyService();

    }


    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String sendingText = "";
        String general="Hello,My name is Tom,Let me help you. Use'/customer' to see all information about Customer.Use'/categories' to see categories of shoes";
        String ask = "Please, enter id to see the customer's information: ";
        String askingAboutShoes = "Please, enter 1 to see lifestyle shoes," +
                "2 to see running shoes," +
                "3 for Soccer," +
                "4 for Basketball," +
                "5 for Golf," +
                "6 for Jordan," +
                "7 for Skateboarding," +
                "8 for Tennis," +
                "9 for Baseball";
        if(update.getMessage().getText().equals("/start")){
            sendMessage.setText(general);
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if (update.getMessage().getText().equals("/customer")) {
            sendMessage.setText(ask);
            status = BotStatus.ASK_ID;
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (update.getMessage().getText().equals("/categories")) {
           sendMessage.setText(askingAboutShoes);
           status=BotStatus.ASK_CATEGORY;
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
                /*try {
                    List<Category> shoes = shoesService.categories();
                    sendingText = shoes.toString();
                    sendMessage.setText(sendingText);
                } catch (Exception e) {
                    System.out.println(" Error ");
                }*/

        }
        else if (update.getMessage().getText().equals("/buy")) {

            status=BotStatus.ASK_EMAIL;
            sendMessage.setText("Enter the email");
//            cs.setEmail(update.getMessage().getText());
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
//         else if(cs.getEmail().equals(update.getMessage().getText())){
//                sendMessage.setText("Enter password");
//
//            }
        else if(status == BotStatus.ASK_EMAIL){
            if(customerService.getCustomerByUsername(update.getMessage().getText())!=null){
                sendMessage.setText("Enter the password");
                status=BotStatus.ASK_PASSWORD;
                try {
                    execute(sendMessage);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
                sendMessage.setText("Error");
                try {
                    execute(sendMessage);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
         else if(status == BotStatus.ASK_ID){
            try{
                long id=Long.parseLong(update.getMessage().getText());
                Customer customer=customerService.getCustomerByID(id);
                sendingText=((customer==null)? "Customer is not found!":customer.toString());
                sendMessage.setText(sendingText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
             try {
                 execute(sendMessage);
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }
        }
        else if(status==BotStatus.ASK_CATEGORY) {
            try {
                long id = Long.parseLong(update.getMessage().getText());
                List<Shoes> shoes = shoesService.getCategory(id);
                sendingText = (shoes == null ? "Category is not found!" : shoes.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            sendMessage.setText(sendingText + "\n");
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }










    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
