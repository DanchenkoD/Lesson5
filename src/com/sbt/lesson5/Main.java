package com.sbt.lesson5;

import java.util.Scanner;

public class Main {

    private static boolean repeat = true;
    private static boolean pinFlag = false;
    private static String pinCod = "";

    public static void main(String[] args) throws TerminalException, TerminalServerException {
        String s = "";
        TerminalServer terminalServer = new TerminalServer();
        Scanner scanner = new Scanner(System.in);
        while (repeat) {
            while (!pinFlag) {
                System.out.println("Введите пин-код: ");
                try {
                    pinCod = scanner.nextLine();
                    pinFlag = terminalServer.pinValidator(pinCod);
                } catch (PinValidatorException | TerminalException | TerminalServerException e) {
                    System.out.println("Произошла ошибка " + e.getMessage());
                } finally {
                    if (pinFlag) {
                        System.out.println("Пин код введен верно");
                    }
                    //else System.out.println("Верно");
                }
            }

            System.out.println("Введите команду: \n 1 - Проверить состояние счета \n 2 - Положить деньги \n 3 - Снять деньги \n 4 - Ввести пин-код \n 0 - Выход ");
            s = scanner.nextLine();

            switch (s) {
                case "1":
                    if (pinFlag) {
                        double money;
                        try {
                            money = terminalServer.getBalans(pinCod);
                            System.out.println("Ваш баланс: " + money);
                        } catch (PinValidatorException | TerminalException | TerminalServerException e) {
                            System.out.println("Произошла ошибка " + e.getMessage());
                        } finally {
                            ;
                            /*if (pinFlag) {
                                //System.out.println("Пин код введен верно");
                            }*/
                            //else System.out.println("Верно");
                        }
                        break;
                    }

                case "2":
                    if (pinFlag) {
                        System.out.println("Введите сумму которую вносите на счет: ");
                        s = scanner.nextLine();
                        try {
                            System.out.println("Ваш баланс: " +terminalServer.deposit(Double.parseDouble(s), pinCod));
                        } catch (PinValidatorException | TerminalException | TerminalServerException e) {
                            System.out.println("Произошла ошибка " + e.getMessage());
                        } finally {
                            ;
                        }

                        break;
                    }
                case "3":
                    if (pinFlag) {
                        System.out.println("Введите сумму которую хотите снять со счета: ");
                        s = scanner.nextLine();
                        try {
                            System.out.println("Ваш баланс: " +terminalServer.cashOut(Double.parseDouble(s), pinCod));
                        } catch (PinValidatorException | TerminalException | TerminalServerException e) {
                            System.out.println("Произошла ошибка " + e.getMessage());
                        } finally {
                            ;
                        }

                        break;
                    }
                case "4":
                    //pinValidator(money, pin, pinValid);
                    pinFlag = false;
                    break;
                case "0":
                    System.out.println("bye");
                    repeat = false;
                    break;
                default:
                    throw new TerminalException("Неизвестная команда.");
            }

        }
    }

}

