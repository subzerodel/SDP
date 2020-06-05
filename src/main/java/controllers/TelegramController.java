package controllers;

import domain.models.Category;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.ShoesService;

import java.util.List;

public class TelegramController extends TelegramLongPollingBot {
    private  ShoesService shoesService;
    private final String USERNAME="omirdegen_bot";
    private final String TOKEN="1152301580:AAHN-NijwUkolZVzDkOJfgDgjb9lXI1H8SU";

    public TelegramController(){
        shoesService=new ShoesService();
    }
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String sendingText=" ";
        String ask="Please, enter Categories to see all categories of shoes: ";
        if(update.getMessage().getText().equals("/start")){
            sendMessage.setText(ask);
        }
        else{
            try {
                List<Category> categories=shoesService.categories();
                sendingText=(categories==null? "Categories not found!":categories.toString());
            } catch (Exception e) {
            sendingText=("Error");
            }
            sendMessage.setText(sendingText+"\n"+ask);
        }
        try {
            execute(sendMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
