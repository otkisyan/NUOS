package part1;

public class Executor {

    // Из строки удалить все слова указанной длины, которые начинаются на согласную букву.
    public String modifyString(int wordLength, String text) {

        String[] stringArray = text.split("[,.:; ]+");
        StringBuilder stringB = new StringBuilder();

        for (String i : stringArray) {

            // Если слово не начинается с согласной и его длина не соответствует введенной длине пользователя,
            // то добавить его в StringBuilder

            if ((i.length() != wordLength) && (!isConsonant(i.charAt(0)))) {

                stringB.append(i).append(" ");
            }

            // Если слово начинается с согласной, а его длина не соответствует введенной длине пользователя,
            // то добавить его в StringBuilder

            if ((i.length() != wordLength) && (isConsonant(i.charAt(0)))) {

                stringB.append(i).append(" ");
            }

            // Если слово не начинается с согласной, а его длинна соответствует введенной длине пользователя,
            // то добавить его в StringBuilder

            if ((!isConsonant(i.charAt(0)) && (i.length() == wordLength))) {

                stringB.append(i).append(" ");
            }

        }

        return stringB.toString().trim();
    }

    public boolean isConsonant(char letter) {

        return switch (Character.toLowerCase(letter)) {

            case 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
                    'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z' -> true;

            default -> false;

        };

       /*String consonants = "bcdfghjklmnpqrstvwxyz";
       return consonants.contains(Character.toString(Character.toLowerCase(letter)));*/


    }
}