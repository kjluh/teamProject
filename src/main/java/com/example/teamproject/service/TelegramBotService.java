package com.example.teamproject.service;

import com.example.teamproject.entities.TypeOfPet;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {

    private TelegramBot telegramBot;

    private AdoptiveParentService adoptiveParentService;

    public TelegramBotService(TelegramBot telegramBot, AdoptiveParentService adoptiveParentService) {
        this.telegramBot = telegramBot;
        this.adoptiveParentService = adoptiveParentService;
    }

    /**
     * Метод показывает пользователю кнопу "записать данные" в чате бота, использует {@link InlineKeyboardButton}
     *
     * @return возвращает созданную кнопку
     */
    public InlineKeyboardButton saveInfo() {  // метод записи данных
        InlineKeyboardButton button = new InlineKeyboardButton("записать данные");
        button.callbackData("записать данные");
        return button;
    }

    /**
     * Метод приветствует пользователя.
     * Затем показывает пользователю созданные кнопки 2-го этапа в чате с ботом, использует {@link InlineKeyboardButton}
     *
     * @param chatId принимает ID чата, где отобразит кнопки
     */
    public void takePetFromShelter(Long chatId) {  // кнопки этапа 2, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила знакомства с животным до того, как забрать его из приюта.");
        button1.callbackData("Правила знакомства");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять животное из приюта.");
        button2.callbackData("Список документов");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Список рекомендаций по транспортировке животного.");
        button3.callbackData("транспортировка животного");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для щенка/котенка.");
        button4.callbackData("дома для щенка");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослого животного.");
        button5.callbackData("дома для собаки");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение). ");
        button6.callbackData("дома для собаки с ограничениями");
        InlineKeyboardButton button7 = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой");
        button7.callbackData("советы кинолога");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним.");
        button8.callbackData("список кинологов");
        InlineKeyboardButton button9 = new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать собаку из приюта.");
        button9.callbackData("список причин для отказа");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);
        keyboard.addRow(button5);
        keyboard.addRow(button6);
        if (adoptiveParentService.findAdoptiveParentByChatId(chatId).getTypeOfPet().equals(TypeOfPet.DOG) ||
                adoptiveParentService.findAdoptiveParentByChatId(chatId).getTypeOfPet() == null) {
            keyboard.addRow(button7);
            keyboard.addRow(button8);
        }
        keyboard.addRow(button9);
        keyboard.addRow(saveInfo());
        keyboard.addRow(helpVolunteers());
        keyboard.addRow(mainMenu());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    /**
     * Метод приветствует пользователя.
     * Затем показывает пользователю созданные кнопки 3-го этапа в чате с ботом, использует {@link InlineKeyboardButton}
     *
     * @param chatId принимает ID чата, где отобразит кнопки
     */
    public void sendReport(Long chatId) {  // кнопки этапа 3, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Форма ежедневного отчёта");
        button1.callbackData("Форма ежедневного отчёта");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Прислать ежедневный отчёт");
        button2.callbackData("принимаем отчет");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(helpVolunteers());
        keyboard.addRow(mainMenu());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    /**
     * Метод приветствует пользователя.
     * Затем показывает пользователю начальное меню, использует {@link InlineKeyboardButton}
     *
     * @param chatId принимает ID чата, где отобразит кнопки
     */
    public void firstMenu(Long chatId) { // меню начальное, кейсы 1/2/3
        SendMessage helloMessage = new SendMessage(chatId, "Привет,  тут должна быть информация о боте. Выберите интересующий пункт из меню: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте!");
        button1.callbackData("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять животного из приюта?");
        button2.callbackData("2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        button3.callbackData("3");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1, button2);
        keyboard.addRow(button3, helpVolunteers());
        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    /**
     * Метод приветствует пользователя.
     * Затем показывает пользователю созданные кнопки 3-го этапа в чате с ботом, использует {@link InlineKeyboardButton}
     *
     * @param chatId принимает ID чата, где отобразит кнопки
     */
    public void shelterInfo(Long chatId) { // кнопки этапа 1, кейсы между 1 и 2

        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("инфа о приюте");
        button1.callbackData("инфа о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("расписание работы");
        button2.callbackData("расписание работы");
        InlineKeyboardButton button3 = new InlineKeyboardButton("рекомендации о ТБ");
        button3.callbackData("рекомендации о ТБ");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(saveInfo());
        keyboard.addRow(helpVolunteers());
        keyboard.addRow(mainMenu());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    /**
     * Метод показывает пользователю кнопу "Позвать волонтера" в чате бота, использует {@link InlineKeyboardButton}
     *
     * @return возвращает созданную кнопку
     */
    public InlineKeyboardButton helpVolunteers() { // метод позвать волонтера
        InlineKeyboardButton button = new InlineKeyboardButton("Позвать волонтера");
        button.callbackData("позвать волонтера");
        return button;
    }

    /**
     * Метод показывает пользователю кнопу "Главное меню" для возврата в основное меню в чате бота,
     * использует {@link InlineKeyboardButton}
     *
     * @return возвращает созданную кнопку
     */
    public InlineKeyboardButton mainMenu() { // // возврат в главное меню
        InlineKeyboardButton button = new InlineKeyboardButton("Главное меню");
        button.callbackData("Главное меню");
        return button;
    }

    /**
     * Меню из кнопок для выбора типа животного в приюте, сразу после выбора сохраняет нового потенциального клиента в ДБ
     * @param chatId ID чата пользователя
     */
    public void catOrDogMenu(Long chatId) { // меню начальное, кейсы 1/2/3
        SendMessage helloMessage = new SendMessage(chatId, "Выберите какой приют Вам нужен");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Кошачий");
        button1.callbackData("cat");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Собачий");
        button2.callbackData("dog");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1, button2);
        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }
}
