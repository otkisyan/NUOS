# 1. Создать проект, который должен содержать класс согласно требованиям:
# 
# Drink: id, Наименование, Производитель, Цена, Тип, Упаковка, Склад.
# Составить список объектов. Вывести:
# a) список напитков заданного производителя;
# b) список напитков заданного наименования, цена которых не превышает заданную;
# c) список напитков определенного типа, отсортированных по цене.

# 2. Реализовать в классе методы:
# - конструктор по умолчанию;
# - деструктор для освобождения памяти (с сообщением об уничтожении объекта);
# - функцию формирования строки информации об объекте.
# 3. Создать проект для демонстрации работы: сформировать объекты со
# значениями-константами и с введенными с клавиатуры значениями полей объекта.

class Drink:
    id_counter = 0
    def __init__(self, name, producer, price, drink_type, packaging, warehouse):
        self.id = Drink.id_counter
        Drink.id_counter += 1
        self.name=name
        self.producer=producer
        self.price=price
        self.type=drink_type
        self.packaging=packaging
        self.warehouse=warehouse
        
    def __del__(self):
        print(f"Drink with id {self.id} has been destroyed")
        
    def __str__(self):
        return (f"ID: {self.id}, Name: {self.name}, Producer: {self.producer}, Price: {self.price} "
        f"Type: {self.type}, Packaging: {self.packaging}, Warehouse: {self.warehouse}")
        
class DrinkFactory:
    
    @staticmethod
    def create_drink(name, producer, price, drink_type, packaging, warehouse):
        return Drink(name, producer, price, drink_type, packaging, warehouse)
    
    @staticmethod
    def create_sample_drinks_list():
        return [
        Drink("Coca-Cola", "The Coca-Cola Company", 1.50, "Soda", "Can", "A1"),
        Drink("Pepsi", "PepsiCo", 1.50, "Soda", "Bottle", "A2"),
        Drink("Pepsi", "PepsiCo", 2.00, "Soda", "Bottle", "A2"),
        Drink("Sprite", "The Coca-Cola Company", 1.25, "Soda", "Can", "A1"),
        Drink("Fanta", "The Coca-Cola Company", 1.25, "Soda", "Bottle", "A2"),
        Drink("Mountain Dew", "PepsiCo", 1.75, "Soda", "Bottle", "A2"),
        Drink("Red Bull", "Red Bull GmbH", 2.50, "Energy Drink", "Can", "B1"),
        Drink("Monster Energy", "Monster Beverage Corporation", 2.50, "Energy Drink", "Can", "B1"),
        Drink("Gatorade", "PepsiCo", 2.00, "Sports Drink", "Bottle", "B2"),
        Drink("Powerade", "The Coca-Cola Company", 2.00, "Sports Drink", "Bottle", "B2"),
        Drink("Water", "Aquafina", 1.00, "Water", "Bottle", "C1")]
    
class Controller:
    
    def __init__(self, drinks):
        self.drinks = drinks
       
    def handle_menu_choice(self, choice):
        match choice:
            case 1:
                self.find_drinks_by_producer()
            case 2:
                self.find_drinks_by_name_and_price_is_not_higher()
            case 3:
                self.find_drinks_by_type_sorted_by_price_ascending()
            case 4:
                self.add_new_drink()
            case 0:
                return
            case _:
                print("Помилка")
    
    @staticmethod
    def print_drinks(drinks):
        for drink in drinks:
            print(drink)
    
    def find_drinks_by_producer(self):
        producer = input("Введіть назву виробника: ")
        drinks_result = Logic.find_drinks_by_producer(self.drinks, producer)
        if not drinks_result:
            print("Напоїв за заданими критеріями не знайдено")
        else:
            Controller.print_drinks(drinks_result)
                
    def find_drinks_by_name_and_price_is_not_higher(self):
        name = input("Введіть ім'я шуканого напою: ")
        try:
            price = float(input("Введіть ціну: "))
        except ValueError:
            print("Введіть ціле або дробне число!")
        drinks_result = Logic.find_drinks_by_name_and_price_is_not_higher(self.drinks, name, price)
        if not drinks_result:
            print("Напоїв за заданими критеріями не знайдено")
        else:
            Controller.print_drinks(drinks_result)
                    
    def find_drinks_by_type_sorted_by_price_ascending(self):
        drink_type = input("Введіть тип шуканого напою: ")
        drinks_result = Logic.find_drinks_by_type_sorted_by_price_ascending(self.drinks, drink_type) 
        if not drinks_result:
            print("Напоїв за заданими критеріями не знайдено")
        else: 
            Controller.print_drinks(drinks_result)
                   
    def add_new_drink(self):
        name = input("Введіть назву напою: ")
        producer = input("Введіть виробника напою: ")
        price = float(input("Введіть ціну напою: "))
        drink_type = input("Введіт тип напою: ")
        packaging = input("Введіть упаковку напою: ")
        warehouse = input("Введіть склад напою: ") 
        self.drinks.append(DrinkFactory.create_drink(name, producer, price, drink_type, packaging, warehouse))
           
class Logic:
   
    # a) список напитков заданного производителя;
    @staticmethod
    def find_drinks_by_producer(drinks, producer):
        return [drink for drink in drinks if drink.producer.lower() == producer.lower()]
   
    # b) список напитков заданного наименования, цена которых не превышает заданную;
    @staticmethod
    def find_drinks_by_name_and_price_is_not_higher(drinks, name, price):
        return [drink for drink in drinks if drink.name.lower() == name.lower() and drink.price <= price]
    
    # c) список напитков определенного типа, отсортированных по цене.
    @staticmethod
    def find_drinks_by_type_sorted_by_price_ascending(drinks, drink_type):
        return sorted([drink for drink in drinks if drink.type.lower() == drink_type.lower()],
                      key=lambda drink: drink.price)
    
def main():
    controller = Controller(DrinkFactory.create_sample_drinks_list())
    while True:
        print("1 - Пошук напоїв заданого виробника \n"
            "2 - Пошук напоїв за ім'ям, ціна яких не перевищує задану \n"
            "3 - Пошук напоїв певного типу, відсортованих за ціною \n"
            "4 - Додати новий напій"
            )
        try:
            choice = int(input("Оберіть пункт меню: "))
        except ValueError:
            print("Неправильний ввід")
            continue
        if choice == 0:
            break
        else:
            controller.handle_menu_choice(choice)
                
    
if __name__ == "__main__":
    main()
