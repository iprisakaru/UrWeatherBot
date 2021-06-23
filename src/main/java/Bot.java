import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "urweatherby_bot";
    }

    @Override
    public String getBotToken() {
        return "1641999245:AAGNkU5o7dTrTMmz7nug2LolsxDfn6Gp5KA";
    }


    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(@NotNull Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        setButtons(sendMessage);
        sendMessage.setText(sendMessage.toString());

    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        Builder tmpBuild = new Builder();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "How can I help you?");
                    break;
                case "/setting":
                    sendMsg(message, "What will be set up?");
                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), tmpBuild));
                    } catch (IOException e) {
                        sendMsg(message, "City not founded");
                    }

            }
        }

    }

    public void setButtons(@NotNull SendMessage sendMessage) {
        ReplyKeyboardMarkup replayKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replayKeyboardMarkup);
        replayKeyboardMarkup.setSelective(true);
        replayKeyboardMarkup.setResizeKeyboard(true);
        replayKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/setting"));

        keyboardRowList.add(keyboardFirstRow);
        replayKeyboardMarkup.setKeyboard(keyboardRowList);

    }


}