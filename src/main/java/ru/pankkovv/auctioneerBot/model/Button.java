package ru.pankkovv.auctioneerBot.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pankkovv.auctioneerBot.enums.ButtonData;
import ru.pankkovv.auctioneerBot.enums.ButtonMessage;

import java.util.ArrayList;
import java.util.List;

public class Button {

    public static InlineKeyboardMarkup getAllButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFive = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineSix = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineSeven = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineEight = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineNine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTen = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineEleven = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwelve = new ArrayList<>();


        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton createBetButton = new InlineKeyboardButton();
        createBetButton.setText(ButtonMessage.CREATE_BET.label);
        createBetButton.setCallbackData(ButtonData.CREATE_BET_BTN.label);

        InlineKeyboardButton getBetButton = new InlineKeyboardButton();
        getBetButton.setText(ButtonMessage.GET_BET.label);
        getBetButton.setCallbackData(ButtonData.GET_BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(ButtonMessage.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        InlineKeyboardButton regButton = new InlineKeyboardButton();
        regButton.setText(ButtonMessage.REGISTRATION.label);
        regButton.setCallbackData(ButtonData.REGISTRATION_BTN.label);

        InlineKeyboardButton createButton = new InlineKeyboardButton();
        createButton.setText(ButtonMessage.CREATE.label);
        createButton.setCallbackData(ButtonData.CREATE_BTN.label);

        InlineKeyboardButton updateButton = new InlineKeyboardButton();
        updateButton.setText(ButtonMessage.UPDATE.label);
        updateButton.setCallbackData(ButtonData.UPDATE_BTN.label);

        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText(ButtonMessage.DELETE.label);
        deleteButton.setCallbackData(ButtonData.DELETE_BTN.label);

        InlineKeyboardButton tableButton = new InlineKeyboardButton();
        tableButton.setText(ButtonMessage.TABLE.label);
        tableButton.setCallbackData(ButtonData.TABLE_BTN.label);

        InlineKeyboardButton downloadButton = new InlineKeyboardButton();
        downloadButton.setText(ButtonMessage.DOWNLOAD_TABLE.label);
        downloadButton.setCallbackData(ButtonData.DOWNLOAD_BTN.label);

        InlineKeyboardButton clearButton = new InlineKeyboardButton();
        clearButton.setText(ButtonMessage.CLEAR_BIDDING.label);
        clearButton.setCallbackData(ButtonData.CLEAR_BIDDING_BTN.label);


        rowInLineOne.add(tableButton);
        rowInLineTwo.add(createButton);
        rowInLineThree.add(updateButton);
        rowInLineFour.add(deleteButton);
        rowInLineFive.add(startButton);
        rowInLineSix.add(helpButton);
        rowInLineSeven.add(lotButton);
        rowInLineEight.add(getBetButton);
        rowInLineNine.add(createBetButton);
        rowInLineTen.add(cancelButton);
        rowInLineEleven.add(downloadButton);
        rowInLineTwelve.add(clearButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);
        rowsInLine.add(rowInLineFive);
        rowsInLine.add(rowInLineSix);
        rowsInLine.add(rowInLineSeven);
        rowsInLine.add(rowInLineEight);
        rowsInLine.add(rowInLineNine);
        rowsInLine.add(rowInLineTen);
        rowsInLine.add(rowInLineEleven);
        rowsInLine.add(rowInLineTwelve);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getStartButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFive = new ArrayList<>();

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton createBetButton = new InlineKeyboardButton();
        createBetButton.setText(ButtonMessage.CREATE_BET.label);
        createBetButton.setCallbackData(ButtonData.CREATE_BET_BTN.label);

        InlineKeyboardButton getBetButton = new InlineKeyboardButton();
        getBetButton.setText(ButtonMessage.GET_BET.label);
        getBetButton.setCallbackData(ButtonData.GET_BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(ButtonMessage.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        rowInLineOne.add(lotButton);
        rowInLineTwo.add(getBetButton);
        rowInLineThree.add(createBetButton);
        rowInLineFour.add(cancelButton);
        rowInLineFive.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);
        rowsInLine.add(rowInLineFive);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getHelpButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFive = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton createBetButton = new InlineKeyboardButton();
        createBetButton.setText(ButtonMessage.CREATE_BET.label);
        createBetButton.setCallbackData(ButtonData.CREATE_BET_BTN.label);

        InlineKeyboardButton getBetButton = new InlineKeyboardButton();
        getBetButton.setText(ButtonMessage.GET_BET.label);
        getBetButton.setCallbackData(ButtonData.GET_BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(ButtonMessage.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(lotButton);
        rowInLineThree.add(getBetButton);
        rowInLineFour.add(createBetButton);
        rowInLineFive.add(cancelButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);
        rowsInLine.add(rowInLineFive);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getLotButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFive = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton createBetButton = new InlineKeyboardButton();
        createBetButton.setText(ButtonMessage.CREATE_BET.label);
        createBetButton.setCallbackData(ButtonData.CREATE_BET_BTN.label);

        InlineKeyboardButton getBetButton = new InlineKeyboardButton();
        getBetButton.setText(ButtonMessage.GET_BET.label);
        getBetButton.setCallbackData(ButtonData.GET_BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(ButtonMessage.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(getBetButton);
        rowInLineThree.add(createBetButton);
        rowInLineFour.add(cancelButton);
        rowInLineFive.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);
        rowsInLine.add(rowInLineFive);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getCreateBetButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(lotButton);
        rowInLineThree.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getBetButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(ButtonMessage.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(lotButton);
        rowInLineThree.add(cancelButton);
        rowInLineFour.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getCancelButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(ButtonMessage.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(ButtonMessage.CREATE_BET.label);
        betButton.setCallbackData(ButtonData.CREATE_BET_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(lotButton);
        rowInLineThree.add(betButton);
        rowInLineFour.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getChoiceButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();

        InlineKeyboardButton yesClearButton = new InlineKeyboardButton();
        yesClearButton.setText(ButtonMessage.YES_CLEAR.label);
        yesClearButton.setCallbackData(ButtonData.YES_CLEAR_BTN.label);

        InlineKeyboardButton noButton = new InlineKeyboardButton();
        noButton.setText(ButtonMessage.NO.label);
        noButton.setCallbackData(ButtonData.NO_BTN.label);


        rowInLineOne.add(yesClearButton);
        rowInLineTwo.add(noButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }
}
