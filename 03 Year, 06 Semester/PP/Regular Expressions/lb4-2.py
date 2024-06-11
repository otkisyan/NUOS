# Написать регулярное выражение, определяющее, является ли эта строка датой в формате "dd/mm/yyyyy",
# начиная с 1600 до 9999 года включительно.

import re
## | = or
## ^ = asserts position at start of a line
## $ = asserts position at the end of a line 
## \d = any digit [0-9]
# {n} - quantifier matches the previous token exactly n times

## 0[1-9] = 01 - 09
## or
## [12][0-9] = 10-29
## or
## 3[01] = 3 and 0 or 1 after (30-31)

## 0[1-9] = 01-09
## or 1[0-2] = 10-12

## 1[6-9] = 16 - 19
## \d{2} - expecting two numbers in a row
## [2-9] = 2-9 \d expecting three numbers in a row (2231 for example)
pattern = r'^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(1[6-9]\d{2}|[2-9]\d{3})$'

def is_valid_date(string):
    return bool(re.match(pattern, string))

def main():
    
    date = "28/02/2500"
    if is_valid_date(date):
        print(f"{date} є дійсною датою.")
    else:
        print(f"{date} не є дійсною датою.")

if __name__ == "__main__":
    main()
