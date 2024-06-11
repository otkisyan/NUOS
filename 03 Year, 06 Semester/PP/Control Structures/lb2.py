import math

# Составить программу табулирования функции на промежутке [a,b] с интервалом h. 
# В реализации следует применить ветвление и циклы.
# y = e^{tgx}-1
eps = 0.1
def expression(x):
    # The math.exp() method returns E raised to the power of x (E^x).
    # 'E' is the base of the natural system of logarithms (approximately 2.718282)
    return math.exp(math.tan(x)) - 1

def calculateSteps(a, b, h):
    return int((b - a) / h + 1)

def isNearAsymptote(x):
   return abs(math.cos(x)) < eps 

def tabulation(a, b, h):
    n = calculateSteps(a, b, h)
    for i in range(n):
        x = a + i * h
        if not isNearAsymptote(x):
            y = expression(x)
            print(f"x = {x:.4f}, y = {y:.4f}")
        else:
            print(f"x = {x:.4f}, y: Не определено")

def main(): 

    a = float(input("a: "))
    b = float(input("b: "))
    h = float(input("h: "))
    tabulation(a, b, h)

if __name__ == "__main__":
    main()