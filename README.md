# java-auctioneer-bot

## Описание приложения

Бот-аукционер - помощник в проведении аукционов для тг-каналов. 
Проект позволяет быстро организовать онлайн-торги для редких и особенных товаров.

Запись торгов происходит в exel файл (CSV формат) для определения победителя торгов и
последующего подведения статистики канала.

В данный момент осуществлена возможность проведения 
аукциона только для одной позиции лота за время торгов.

----

## Основные функции

#### Функции юзера:

- Вернуться в главное меню
- Посмотреть информацию с инструкцией использования
- Посмотреть торгуемый лот
- Сделать ставку
- Посмотреть актуальную ставку 
- Отменить свою ставку

#### Функции админа:

Помимо функций простого пользователя админ также может:

- Создать/обновить/удалить лот торгов
- Посмотреть/очистить таблицу торгов
- Скачать exel-файл с записью торгов
____

## Пример java-кода:

```java
try {
    Utils.containsLot();
    Utils.containsBet(userName);
    text = String.format(CommandMessage.CANCEL.label, userName);

    bidding.remove(userName);
    lot.setCurrentPrice(bidding.values().stream()
        .max(Comparator.comparing(Float::valueOf))
        .orElse(lot.getStartPrice()));

    sendPhoto.setCaption(text);
    sendPhoto.setPhoto(new InputFile(new File("imgBtn/cancel.jpg")));
} catch (LotNotFoundException e) {
    text = e.getMessage();
    sendPhoto.setCaption(text);
    sendPhoto.setPhoto(new InputFile(new File("imgBtn/notFoundLot.jpg")));
}
```
----
## Стек
- JDK 11
- Spring Boot
- Maven
- Lombok
- JUnit 5
- Telegram APIs
