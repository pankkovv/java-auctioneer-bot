package ru.pankkovv.auctioneerBot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.pankkovv.auctioneerBot.model.Auction;
import ru.pankkovv.auctioneerBot.model.Lot;

class NonCommandServiceTest {
    static NonCommandService service;

    @BeforeAll
    public static void assistant() {
        service = new NonCommandService();
        Auction.admin.add("admin");
    }

    @BeforeEach
    public void createLot(){
        Auction.lot = Lot.builder().name("ring").startPrice(100.0F).currentPrice(100.0F).step(10.0F).build();
    }

    @Test
    public void createLotAdminTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Лот успешно создан(обновлен)!")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "admin", "ring 100 10");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void updateLotAdminTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Лот успешно создан(обновлен)!")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "admin", "ring 200 20");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void createBetTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("userNameTest, ваша ставка 220.0₽ успешно принята!")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "userNameTest", "220");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void deleteBetTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("userNameTest, ваша ставка отменена")
                .photo(new InputFile()).build();

        CallbackQuery cbqOne = new CallbackQuery();
        Message msgOne = new Message();
        msgOne.setCaption("cancel_btn");
        cbqOne.setData("cancel_btn");
        cbqOne.setMessage(msgOne);

        service.nonCommandExecute(1L, "userNameTest", "220");
        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "userNameTest", cbqOne);

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void getLotTest() {
        Lot expectedLot = Lot.builder().name("ring").startPrice(100.0F).currentPrice(100.0F).step(10.0F).build();

        CallbackQuery cbq = new CallbackQuery();
        Message msg = new Message();
        msg.setCaption("lot_btn");
        cbq.setData("lot_btn");
        cbq.setMessage(msg);

        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "admin", cbq);

        Assertions.assertEquals("Актуальная информация по лоту:\n" + expectedLot, actualSend.getCaption());
    }

    @Test
    public void deleteLotAdminTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Лот успешно удален!")
                .photo(new InputFile()).build();

        CallbackQuery cbqOne = new CallbackQuery();
        Message msgOne = new Message();
        msgOne.setCaption("delete_btn");
        cbqOne.setData("delete_btn");
        cbqOne.setMessage(msgOne);

        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "admin", cbqOne);

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void createLotErrTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Для выполнения данной команды необходимы права администратора.")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "userName", "ring 100 10");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void updateLotErrTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Для выполнения данной команды необходимы права администратора.")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "userName", "ring 200 20");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void getLotErrTest() {
        Auction.lot = null;
        String expectedLot = "Ни один лот еще не зарегистрирован.";

        CallbackQuery cbq = new CallbackQuery();
        Message msg = new Message();
        msg.setCaption("lot_btn");
        cbq.setData("lot_btn");
        cbq.setMessage(msg);

        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "userName", cbq);

        Assertions.assertEquals(expectedLot, actualSend.getCaption());
    }

    @Test
    public void deleteLotErrTest() {
        Auction.lot = null;
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Для выполнения данной команды необходимы права администратора.")
                .photo(new InputFile()).build();

        CallbackQuery cbqOne = new CallbackQuery();
        Message msgOne = new Message();
        msgOne.setCaption("delete_btn");
        cbqOne.setData("delete_btn");
        cbqOne.setMessage(msgOne);

        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "userName", cbqOne);

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void createBetErrTest() {
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Напомню, если вы хотите предложить свою цену за лот, необходимо выполнить следующие условия:\n" +
                        "1. Ваша цена не может быть меньше актуальной стоимости лота \n" +
                        "2. Увеличение цены лота не может быть меньше установленного шага торгов \n" +
                        "3. Увеличение цены лота должно быть кратно установленному шагу торгов\n\n" +
                        "Возможно кто-то предложил ставку выше, актуальная цена на данный момент: 100.0₽\n\n" +
                        "Попробуйте еще раз \uD83D\uDE0A")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "userName", "195");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void createBetWithoutLotTest() {
        Auction.lot = null;
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Ни один лот еще не зарегистрирован.")
                .photo(new InputFile()).build();

        SendPhoto actualSend = service.nonCommandExecute(1L, "userName", "220");

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }

    @Test
    public void deleteBetWithoutLotTest() {
        Auction.lot = null;
        SendPhoto expectedSend = SendPhoto.builder()
                .chatId("1")
                .caption("Ни один лот еще не зарегистрирован.")
                .photo(new InputFile()).build();

        CallbackQuery cbqOne = new CallbackQuery();
        Message msgOne = new Message();
        msgOne.setCaption("cancel_btn");
        cbqOne.setData("cancel_btn");
        cbqOne.setMessage(msgOne);
        SendPhoto actualSend = (SendPhoto) service.nonCommandExecute(1L, "userName", cbqOne);

        Assertions.assertEquals(expectedSend.getCaption(), actualSend.getCaption());
    }
}