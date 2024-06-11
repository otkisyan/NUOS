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

# Создать консольную программу, удовлетворяющую следующим требованиям:
# - Расширить предметную область, которая предоставлена, 
# использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
# - Согласно здравому смыслу сделать перегрузку операторов 
# - Каждый класс должен иметь название и информативный состав, отражающий смысл.
# - Наследование следует применять только тогда, когда это имеет смысл, в качестве 
# базового класса рекомендуется использовать абстрактные классы.
# - Скрытые (внутренние) атрибуты (поля или методы) тоже использовать при необходимости.
# - Консольное меню должно быть минимальным

from abc import ABC, abstractmethod

class Drink(ABC):
    id_counter = 0
    def __init__(self, name, producer, price, packaging, warehouse):
        self.id = Drink.id_counter
        Drink.id_counter += 1
        self.name=name
        self.producer=producer
        self.price=price
        self.packaging=packaging
        self.warehouse=warehouse
        
    def __del__(self):
        print(f"Drink with id {self.id} has been destroyed")
        
    def __str__(self):
        return (f"ID: {self.id}, Name: {self.name}, Producer: {self.producer}, Price: {self.price} "
        f"Packaging: {self.packaging}, Warehouse: {self.warehouse}, Type: {self.get_type()}")
        
    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return (self.name == other.name and
                    self.producer == other.producer and
                    self.price == other.price and
                    self.packaging == other.packaging and
                    self.warehouse == other.warehouse)
        return False

    @abstractmethod
    def get_type(self):
        pass


class Soda(Drink):
    def __init__(self, name, producer, price, packaging, warehouse, flavor):
        super().__init__(name, producer, price, packaging, warehouse)
        self.flavor = flavor
    
    def __str__(self):
        return super().__str__() + f", Flavor: {self.flavor}"

    def get_type(self):
        return "Soda"

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return (self.name == other.name and
                    self.producer == other.producer and
                    self.price == other.price and
                    self.packaging == other.packaging and
                    self.warehouse == other.warehouse and
                    self.flavor == other.flavor)
        return False


class EnergyDrink(Drink):
    def __init__(self, name, producer, price, packaging, warehouse, caffeine_content):
        super().__init__(name, producer, price, packaging, warehouse)
        self.caffeine_content = caffeine_content
     
    def __str__(self):
        return super().__str__() + f", Caffeine Content: {self.caffeine_content} mg"

    def get_type(self):
        return "Energy Drink"

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return (self.name == other.name and
                    self.producer == other.producer and
                    self.price == other.price and
                    self.packaging == other.packaging and
                    self.warehouse == other.warehouse and
                    self.caffeine_content == other.caffeine_content)
        return False

class SportsDrink(Drink):
    def __init__(self, name, producer, price, packaging, warehouse, electrolytes):
        super().__init__(name, producer, price, packaging, warehouse)
        self.electrolytes = electrolytes
    
    def __str__(self):
        return super().__str__() + f", Electrolytes: {self.electrolytes} mg"

    def get_type(self):
        return "Sports Drink"

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return (self.name == other.name and
                    self.producer == other.producer and
                    self.price == other.price and
                    self.packaging == other.packaging and
                    self.warehouse == other.warehouse and
                    self.electrolytes == other.electrolytes)
        return False

class Water(Drink):
    def __init__(self, name, producer, price, packaging, warehouse, source):
        super().__init__(name, producer, price, packaging, warehouse)
        self.source = source

    def __str__(self):
        return super().__str__() + f", Source: {self.source}"

    def get_type(self):
        return "Water"

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return (self.name == other.name and
                    self.producer == other.producer and
                    self.price == other.price and
                    self.packaging == other.packaging and
                    self.warehouse == other.warehouse and
                    self.source == other.source)
        return False
    
class DrinkFactory:
    @staticmethod
    def create_soda(name, producer, price, packaging, warehouse, flavor):
        return Soda(name, producer, price, packaging, warehouse, flavor)
   
    @staticmethod 
    def create_energydrink(name, producer, price, packaging, warehouse, caffeine_content):
        return EnergyDrink(name, producer, price, packaging, warehouse, caffeine_content)
    
    @staticmethod
    def create_sportsdrink(name, producer, price, packaging, warehouse, electrolytes):
        return SportsDrink(name, producer, price, packaging, warehouse, electrolytes)
    
    @staticmethod
    def create_water(name, producer, price, packaging, warehouse, source):
        return Water(name, producer, price, packaging, warehouse, source)
    
    @staticmethod
    def create_sample_drinks_list():
        return [
        Soda("Coca-Cola", "The Coca-Cola Company", 1.50, "Can", "A1", "Cola"),
        Soda("Pepsi", "PepsiCo", 1.50, "Bottle", "A2", "Cola"),
        Soda("Sprite", "The Coca-Cola Company", 1.25, "Can", "A1", "Lemon-Lime"),
        Soda("Fanta", "The Coca-Cola Company", 1.25, "Bottle", "A2", "Orange"),
        Soda("Mountain Dew", "PepsiCo", 1.75, "Bottle", "A2", "Citrus"),
        EnergyDrink("Red Bull", "Red Bull GmbH", 2.50, "Can", "B1", 80),
        EnergyDrink("Monster Energy", "Monster Beverage Corporation", 2.50, "Can", "B1", 160),
        SportsDrink("Gatorade", "PepsiCo", 2.00, "Bottle", "B2", 250),
        SportsDrink("Powerade", "The Coca-Cola Company", 2.00, "Bottle", "B2", 200),
        Water("Water", "Aquafina", 1.00, "Bottle", "C1", "Artesian wells")]

        
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
        price = None
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
        while True:
            drink_type = input("Введіть тип шуканого напою (Soda, Energy Drink, Sports Drink, Water): ").lower()
            if drink_type in ["soda", "energy drink", "sports drink", "water"]:
                break
            else:
                print("Некоректний тип напою. Спробуйте ще раз.")
        drinks_result = Logic.find_drinks_by_type_sorted_by_price_ascending(self.drinks, drink_type)
        if not drinks_result:
            print("Напоїв за заданими критеріями не знайдено")
        else: 
            Controller.print_drinks(drinks_result)
                    
    def add_new_drink(self):
        while True:
            drink_type = input("Введіть тип напою (Soda, Energy Drink, Sports Drink, Water): ").lower()
            if drink_type in ["soda", "energy drink", "sports drink", "water"]:
                break
            else:
                print("Некоректний тип напою. Спробуйте ще раз.")
        name = input("Введіть назву напою: ")
        producer = input("Введіть виробника напою: ")
        price = float(input("Введіть ціну напою: "))
        packaging = input("Введіть упаковку напою: ")
        warehouse = input("Введіть склад напою: ")
        new_drink = None
        if drink_type == "soda":
            flavor = input("Введіть смак напою: ")
            new_drink = DrinkFactory.create_soda(name, producer, price, packaging, warehouse, flavor)
        elif drink_type == "energy drink":
            caffeine_content = float(input("Введіть вміст кофеїну (мг): "))
            new_drink = DrinkFactory.create_energydrink(name, producer, price, packaging, warehouse, caffeine_content)
        elif drink_type == "sports drink":
            electrolytes = float(input("Введіть кількість електролітів (мг): "))
            new_drink = DrinkFactory.create_sportsdrink(name, producer, price, packaging, warehouse, electrolytes)
        elif drink_type == "water":
            source = input("Введіть джерело води: ")
            new_drink = DrinkFactory.create_water(name, producer, price, packaging, warehouse, source)
        self.drinks.append(new_drink)
           
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
        return sorted([drink for drink in drinks if drink.get_type().lower() == drink_type.lower()],
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
