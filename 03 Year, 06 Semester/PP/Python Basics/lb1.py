import math

def calculateExpression(x):
 
    # log10 - base 10 logarithm (lg)
    # log - natural logarithm (ln)
    # \sin\left(\frac{x + 2.3 \cdot \log_{10}(x + 1)}{\sqrt{2 \cdot \log(x)} + \cos(x)}\right)
    
    return math.sin((x + 2.3 * math.log10(x + 1)) / (math.sqrt(2 * math.log(x)) + math.cos(x)))

def main(): 
    x = float(input("Enter x: "))
    print(calculateExpression(x))

# We can use if __name__ to check whether or not our code is being run vs. imported
## It's a singal to the reader: If I look at a file and I don't see if __name__ main
## I assume that I cannot or should not try to run it, it makes me think it's supposed
## to be a library or only meant to be imported.
if __name__ == "__main__":
    main()

## when run, then __name__: __main__
## 
## when import packagename.lb1
## then __name__: packagename.lb1