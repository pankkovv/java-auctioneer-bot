package ru.pankkovv.auctioneerBot.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pankkovv.auctioneerBot.enums.ButtonData;
import ru.pankkovv.auctioneerBot.enums.Command;

import java.util.ArrayList;
import java.util.List;

public class Button {

    public static InlineKeyboardMarkup getAllButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(Command.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(Command.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(Command.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(Command.BET.label);
        betButton.setCallbackData(ButtonData.BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(Command.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        InlineKeyboardButton regButton = new InlineKeyboardButton();
        regButton.setText(Command.REGISTRATION.label);
        regButton.setCallbackData(ButtonData.REGISTRATION_BTN.label);

        InlineKeyboardButton createButton = new InlineKeyboardButton();
        createButton.setText(Command.CREATE.label);
        createButton.setCallbackData(ButtonData.CREATE_BTN.label);

        InlineKeyboardButton updateButton = new InlineKeyboardButton();
        updateButton.setText(Command.UPDATE.label);
        updateButton.setCallbackData(ButtonData.UPDATE_BTN.label);

        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText(Command.DELETE.label);
        deleteButton.setCallbackData(ButtonData.DELETE_BTN.label);

        InlineKeyboardButton tableButton = new InlineKeyboardButton();
        tableButton.setText(Command.TABLE.label);
        tableButton.setCallbackData(ButtonData.TABLE_BTN.label);

        rowInLineOne.add(createButton);
        rowInLineOne.add(updateButton);
        rowInLineOne.add(deleteButton);
        rowInLineOne.add(tableButton);

        rowInLineTwo.add(startButton);
        rowInLineTwo.add(helpButton);
        rowInLineTwo.add(lotButton);

        rowInLineThree.add(betButton);
        rowInLineThree.add(cancelButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);

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

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(Command.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(Command.BET.label);
        betButton.setCallbackData(ButtonData.BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(Command.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(Command.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        rowInLineOne.add(lotButton);
        rowInLineTwo.add(betButton);
        rowInLineThree.add(cancelButton);
        rowInLineFour.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

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

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(Command.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(Command.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(Command.BET.label);
        betButton.setCallbackData(ButtonData.BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(Command.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(lotButton);
        rowInLineThree.add(betButton);
        rowInLineFour.add(cancelButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

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

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(Command.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(Command.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(Command.BET.label);
        betButton.setCallbackData(ButtonData.BET_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(Command.CANCEL.label);
        cancelButton.setCallbackData(ButtonData.CANCEL_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(betButton);
        rowInLineThree.add(cancelButton);
        rowInLineFour.add(helpButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

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
        startButton.setText(Command.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(Command.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(Command.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(Command.CANCEL.label);
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
        startButton.setText(Command.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(Command.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton lotButton = new InlineKeyboardButton();
        lotButton.setText(Command.LOT.label);
        lotButton.setCallbackData(ButtonData.LOT_BTN.label);

        InlineKeyboardButton betButton = new InlineKeyboardButton();
        betButton.setText(Command.BET.label);
        betButton.setCallbackData(ButtonData.BET_BTN.label);

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
}
