# Строка состоит из украинских и латинских букв. 
# Сформировать новую строку, в которой украинские буквы будут в начале слова, латинские - в конце.
# https://www.ssec.wisc.edu/~tomw/java/unicode.html

import re

def clean_string(s):
    # \s - any whitespace character
    # ^ - invert (except)
   return re.sub(r'[^a-zA-Zа-яА-ЯґҐєЄіІїЇ\s]', '', s)
 
def process_string(s):
    cleaned_string = clean_string(s) 
    words = cleaned_string.split()
    result = []
    for word in words:
        ua_chars = ''
        en_chars = ''
        for char in word:
            if ord(char) >= 1024 and ord(char) <= 1169:
                ua_chars += char
            elif ord(char) >= 65 and ord(char) <= 122:
                en_chars += char
        result.append(ua_chars + en_chars)
    return ' '.join(result)

def process_string_with_list_comprehension(s):
    cleaned_string = clean_string(s) 
    words = cleaned_string.split()
    result = []
    for word in words:
        ua_chars = ''.join([char for char in word if ord(char) >= 1024 and ord(char) <= 1169])
        en_chars = ''.join([char for char in word if ord(char) >= 65 and ord(char) <= 122])
        result.append(ua_chars + en_chars)
    return ' '.join(result)

def process_string_with_regex(s):
    result = []
    cleaned_string = clean_string(s)
    # + - one or more
    words = re.findall(r'[a-zA-Zа-яА-ЯґҐєЄіІїЇ]+', cleaned_string)
    for word in words:
        ua_chars = ''.join(re.findall(r'[а-яА-ЯґҐєЄіІїЇ]', word))
        en_chars = ''.join(re.findall(r'[a-zA-Z]', word))
        result.append(ua_chars + en_chars)
    return ' '.join(result)

def main():
    a = "mоГ$рИ8аїfL .jhҐF_vщн@я42, nG2jжп:"
    s = process_string_with_regex(a)
    print("Початковий рядок: " + a)
    print("Новий рядок: " + s)

if __name__ == "__main__":
    main()
