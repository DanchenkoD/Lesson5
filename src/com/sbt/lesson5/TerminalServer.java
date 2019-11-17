package com.sbt.lesson5;

public class TerminalServer {
    private double money = 100000;
    private String pin = "4321";
    private long block = 0;
    private int attempt = 0;


    public boolean pinValidator(String pin) throws TerminalException, TerminalServerException, PinValidatorException {
        if (System.currentTimeMillis() < block + 5000) {
            throw new PinValidatorException("Время блокировки не истекло!");
        } else {
            if (pin.equals(this.pin)) {
                return true;
            } else {
                attempt++;
                if (attempt >= 3) {
                    System.out.println("Неверный пин-код, ждите 5 сек.");
                    block = System.currentTimeMillis();
                    throw new PinValidatorException("Неверный пин-код, ждите 5 сек!");
                } else {
                    System.out.println("Неверный пин-код, попробуйте еще раз (Осталось " + (3 - attempt) + " попытки): ");
                    return false;
                }

            }
        }
    }

    public double getBalans(String pin) throws TerminalException, TerminalServerException, PinValidatorException {
        if (pin.equals(this.pin)) {
            return this.money;
        } else {
            throw new PinValidatorException("Неверный пин-код!");
        }
    }

    public double deposit(double money, String pin) throws TerminalException, TerminalServerException, PinValidatorException {
        if (pin.equals(this.pin)) {
            if (money % 100 == 0) {
                this.money = this.money + money;
                return this.money;
            } else {
                throw new TerminalException("Сумма не кратна 100! Введите сумму кратную 100.");
            }
        } else {
            throw new PinValidatorException("Неверный пин-код!");
        }
    }

    public double cashOut(double money, String pin) throws
            TerminalException, TerminalServerException, PinValidatorException {
        if (pin.equals(this.pin)) {
            if (money % 100 == 0) {
                if (money<=this.money) {
                    this.money = this.money - money;
                    return this.money;
                }
                else {
                    throw new TerminalServerException("На счете недостаточно средств!");
                }
            } else {
                throw new TerminalException("Сумма не кратна 100! Введите сумму кратную 100.");
            }
        } else {
            throw new PinValidatorException("Неверный пин-код!");
        }
    }
}



