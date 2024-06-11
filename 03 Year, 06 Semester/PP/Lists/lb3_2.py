import math
import matplotlib.pyplot as plt
# 1. Для функции y = e^{tgx}-1 построить 2d график функции.
# 2. Для построения графика применить библиотеку Matplotlib.

eps = 0.1
def expression(x):
    # The math.exp() method returns E raised to the power of x (E^x).
    # 'E' is the base of the natural system of logarithms (approximately 2.718282)
    return math.exp(math.tan(x)) - 1

def calculateSteps(a, b, h):
    return int((b - a) / h + 1)

def isNearAsymptote(x):
    return abs(math.cos(x)) < eps 

def plotGraph(a, b, h):
    x_values = []
    y_values = []
    n = calculateSteps(a, b ,h)
    for i in range(n):
        x = a + i * h
        if not isNearAsymptote(x):
            try:
                y = expression(x)
            except (OverflowError, ValueError) as e:
                print(f"x = {x:.4f}, y: Error = {e}")
                continue 
            # NaN = not a number, inf = infinity
            if not math.isnan(y) and not math.isinf(y):
                y_values.append(y) 
                x_values.append(x) 
                print(f"x = {x:.4f}, y = {y:.4f}")
            else:
                print(f"x = {x:.4f}, y не определено")
        else:
            print(f"x = {x:.4f}, y не определено")
            
    plt.xlim(a, b)
    plt.ylim(-10, 10)
    plt.plot(x_values, y_values, 'o')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Graph: y = e^{tgx}-1')
    plt.grid(True)
    plt.show()

def main(): 

    a = float(input("a: "))
    b = float(input("b: "))
    h = float(input("h: "))
    plotGraph(a, b, h)

if __name__ == "__main__":
    main()
