package org.example;

/**
 * Реализует Шифр Плейфера
 *
 * <p>Шифр Плейфера или квадрат Плейфера — ручная симметричная техника шифрования,
 * в которой впервые использована замена биграмм.</p>
 *
 * @see <a href="https://habr.com/ru/post/444176/">Элементарные шифры на понятном языке</a>
 */
public class PlayfairCipher {

    final private String alphabets = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя’._";
    final private char placeholder = '\'';
    final private int N = 7;
    final private int K = 5;

    /**
     * Удаляет дубликаты букв из ключа шифрования
     *
     * @param keyword ключ шифрования
     * @return ключ шифрования с удаленными дубликатами
     */
    public String removeDuplicates(String keyword) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < keyword.length(); i++) {

            if (!result.toString().contains(keyword.charAt(i) + "")) {

                result.append(keyword.charAt(i));
            }
        }

        return result.toString();
    }

    /**
     * Устанавливает символ-заполнитель между букв в парах, в которых нашлись одинаковые буквы.
     * <p>Если оказывается, что повторяющиеся буквы открытого текста образуют одну пару для шифрования,
     * то между этими буквами вставляется специальная буква-заполнитель, например <code>X</code>.
     * В частности, такое слово как <code>BALLOON</code> будет преобразовано к виду <code>BA LX LO ON</code> </p>
     *
     * @param word        слово подлежащее шифрованию
     * @return обработанное слово с установленными символами-заполнителями
     */
    public String insertPlaceholder(String word) {

        StringBuilder result = new StringBuilder(word);

        // Идем парами, поэтому шаг i += 2
        // Использование условия (i < result.length() - 1) гарантирует, что цикл завершится до того,
        // как будет предпринята попытка доступа к индексу, выходящему за границы объекта StringBuilder.
        // Если бы условием цикла было только (i < result.length()),
        // то на последней итерации i было бы равно result.length() - 1,
        // и попытка доступа к result.charAt(i + 1) привела бы к ошибке IndexOutOfBoundsException.
        for (int i = 0; i < result.length() - 1; i += 2) {

            // Если буква i и i + 1, это одинаковые буквы, добавляем между ними букву-заполнитель
            if (result.charAt(i) == result.charAt(i + 1)) {

                result.insert(i + 1, placeholder);
            }
        }

        // Добавление символа-заполнителя в конец для того, чтобы сделать длину слова четной, гарантирует,
        // что будет четное количество букв для шифрования, что позволит зашифровать все буквы в слове парами.
        // Если длина слова нечетная, останется одна буква, которую нельзя зашифровать в паре, что приведет к неполному шифрованию.
        if (result.length() % 2 != 0) {

            result.append(placeholder);
        }

        // System.out.println(result);
        return result.toString();
    }

    /**
     * Генерирует матрицу для шифрования
     * <p>Матрица создается путем размещения букв, использованных в ключевом слове,
     * слева направо и сверху вниз (повторяющиеся буквы отбрасываются).
     * Оставшиеся буквы алфавита размещаются в естественном порядке.</p>
     *
     * @param keyword ключ шифрования
     * @return матрица для шифрования
     */
    public char[][] generateMatrix(String keyword) {

        char[][] matrix = new char[N][K];
        StringBuilder result = new StringBuilder(keyword);

        for (int i = 0; i < alphabets.length(); i++) {

            char c = alphabets.charAt(i);

            if (result.indexOf("" + c) == -1) {

                result.append(c);
            }

        }

        int index = 0;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                // Заполняем матрицу символами из строки
                matrix[i][j] = result.charAt(index++);
            }

        }

        /*for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.print(matrix[i][j] + "");
            }

            System.out.println();
        }*/

        return matrix;
    }

    /**
     * Находит индекс символа в матрице
     *
     * @param matrix матрица шифрования
     * @param a      символ который нужно найти в матрице
     * @return одномерный массив, в котором под нулевым индексом хранится индекс строки символа,
     * а под первым индексом - индекс столбца
     */
    public int[] getIndex(char[][] matrix, char a) {

        int[] index = new int[2];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == a) {

                    // Под нулевым индексом сохраняем индекс строки
                    // Под первым индексом сохраняем индекс столбца
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
        }

        return index;
    }

    /**
     * Шифрует пароль по правилам Шифра Плейфера
     * <p>Открытый текст шифруется порциями по две буквы в соответствии со следующими правилам:
     * <p>1. Если буквы открытого текста попадают в одну строку матрицы, то каждая из них заменяется следующей буквой
     * (для замены последнего элемента строки матрицы используется первый элемент той же строки).</p>
     *
     * <p>2. Если буквы открытого текста попадают в один и тот же столбец матрицы,
     * то каждая из них заменяется буквой расположенной ниже
     * (для замены последнего элемента столбца используется самый верхний элемент того же столбца).</p>
     *
     * <p>3. Если не выполняется ни одно из приведенных выше условий,
     * (если символы не находятся ни на одной строке, ни на одном столбце),
     * то строим прямоугольник, где наши символы — края диагонали. И меняем углы местами.
     * </p>
     * </p>
     *
     * @param matrix сгенерированная матрица
     * @param word   слово подлежащее шифрованию
     * @see #generateMatrix(String)
     * @see #getIndex(char[][], char)
     */
    public String encryptPassword(char[][] matrix, String word) {

        StringBuilder encryptedPassword = new StringBuilder();

        final int matrixRows = matrix.length;
        final int matrixCols = matrix[0].length;

        char a;
        char b;
        int[] aIndex;
        int[] bIndex;

        for (int i = 0; i < word.length() - 1; i += 2) {

            // Получаем текущую пару символов из пароля
            a = word.charAt(i);
            b = word.charAt(i + 1);

            // Получаем индексы текущей пары символов из матрицы
            aIndex = getIndex(matrix, a);
            bIndex = getIndex(matrix, b);

            // В одинаковой строке, смещение вправо
            if (aIndex[0] == bIndex[0]) {

                // Оператор modulo % используется со значением matrixCols = matrix[0].length,
                // для обработки случая, когда символы достигают конца строки или столбца матрицы.
                // Например, если символ находится в конце строки,
                // то после перемещения на один шаг вправо он должен обернуться к первому столбцу той же строки.
                // Оператор % помогает добиться этого, беря остаток от деления(aIndex[1] + 1) на matrixCols,
                // что гарантирует, что полученное значение всегда будет находиться в диапазоне от 0 до matrixCols,
                // которые являются индексами столбцов матрицы.
                encryptedPassword.append(matrix[aIndex[0]][(aIndex[1] + 1) % matrixCols]);
                encryptedPassword.append(matrix[bIndex[0]][(bIndex[1] + 1) % matrixCols]);
            }

            // В одинаковом столбце, смещение вниз
            else if (aIndex[1] == bIndex[1]) {

                encryptedPassword.append(matrix[(aIndex[0] + 1) % matrixRows][aIndex[1]]);
                encryptedPassword.append(matrix[(bIndex[0] + 1) % matrixRows][bIndex[1]]);
            }

            // В разном рядке и столбце, замена местами индексов столбцов
            else {

                // Когда символы находятся в разных строках и столбцах,
                // процесс шифрования включает в себя обмен индексами строк и столбцов каждого символа.
                // (заменяем каждую букву буквой из той же строки, но в противоположном столбце.)
                // Например, если символ A находится на [row_A, col_A], а символ b - на [row_B, col_B],
                // то после шифрования символ B условно будет расположен на [row_B, col_A], а символ b - на [row_A, col_B].
                encryptedPassword.append(matrix[aIndex[0]][bIndex[1]]);
                encryptedPassword.append(matrix[bIndex[0]][aIndex[1]]);
            }
        }

        return encryptedPassword.toString();
    }

    /**
     * Расшифровывает зашифрованное шифром Плейфера слово.
     *
     * <p>1. Если символы находятся в одной строке,
     * расшифрованные символы получаются при перемещении на один шаг влево в той же строке.</p>
     *
     * <p>2. Если символы находятся в одном столбце,
     * расшифрованные символы получаются путем перемещения на один шаг вверх в том же столбце.</p>
     *
     * <p>3.Если символы не находятся в одной строке или столбце,
     * расшифрованные символы получаются путем взятия символов, которые находятся в той же строке, что и первый символ,
     * и в том же столбце, что и второй символ, и наоборот.</p>
     *
     * @param matrix            сгенерированная матрица
     * @param encryptedPassword зашифрованное слово
     * @return расшифрованное слово
     * @see #generateMatrix(String)
     * @see #encryptPassword(char[][], String)
     * @see #getIndex(char[][], char)
     */
    public String decryptPassword(char[][] matrix, String encryptedPassword) {

        StringBuilder decryptedPassword = new StringBuilder();

        final int matrixRows = matrix.length;
        final int matrixCols = matrix[0].length;
        char a;
        char b;
        int[] aIndex;
        int[] bIndex;

        for (int i = 0; i < encryptedPassword.length() - 1; i += 2) {

            // Получаем текущую пару символов из пароля
            a = encryptedPassword.charAt(i);
            b = encryptedPassword.charAt(i + 1);

            // Получаем индексы текущей пары символов из матрицы
            aIndex = getIndex(matrix, a);
            bIndex = getIndex(matrix, b);

            // Если символы находятся в одной строке, смещение влево
            if (aIndex[0] == bIndex[0]) {

                // Цель добавления matrixCols в выражении (aIndex[1] - 1 + matrixCols) % matrixCols
                // заключается в обработке отрицательных значений, которые могут получиться в результате вычитания aIndex[1] - 1,
                // когда aIndex[1] уже равен 0. Оператор % возвращает остаток после деления,
                // поэтому (aIndex[1] - 1 + matrixCols) % matrixCols всегда будет возвращать положительное значение,
                // которое гарантированно находится в пределах столбцов матрицы [0, matrixCols - 1]
                //
                // Рассмотрим двумерную матрицу с 4 строками и 4 столбцами. Количество столбцов равно matrixCols = 4.
                //
                // Пример 1:
                // aIndex[1] = 2. Это означает, что символ a находится во 2-м столбце.
                // После вычитания 1 получаем aIndex[1] - 1 = 1.
                // Прибавив к результату matrixCols = 4, получаем (aIndex[1] - 1 + matrixCols) = 5.
                // Возьмем остаток от деления с matrixCols = 4, получим (aIndex[1] - 1 + matrixCols) % matrixCols = 1.
                // Таким образом, новый индекс столбца символа a после операции сдвига влево равен 1.
                //
                // Пример 2:
                // aIndex[1] = 0. Это означает, что символ a находится в первом столбце.
                // После вычитания 1 получаем aIndex[1] - 1 = -1.
                // Прибавив к результату matrixCols = 4, получаем (aIndex[1] - 1 + matrixCols) = 3.
                // Возьмем остаток от деления с matrixCols = 4, получим (aIndex[1] - 1 + matrixCols) % matrixCols = 3.
                // Таким образом, новый индекс столбца символа a после операции сдвига влево равен 3.
                decryptedPassword.append(matrix[aIndex[0]][(aIndex[1] - 1 + matrixCols) % matrixCols]);
                decryptedPassword.append(matrix[bIndex[0]][(bIndex[1] - 1 + matrixCols) % matrixCols]);

            }

            // Если символы находятся в одном столбце, смещение вверх
            else if (aIndex[1] == bIndex[1]) {

                decryptedPassword.append(matrix[(aIndex[0] - 1 + matrixRows) % matrixRows][aIndex[1]]);
                decryptedPassword.append(matrix[(bIndex[0] - 1 + matrixRows) % matrixRows][bIndex[1]]);

            }

            // В разном рядке и столбце, замена местами индексов столбцов
            else {

                // В этом случае алгоритм расшифровки и шифрования один и тот же.
                // Это происходит потому, что процесс замены каждой буквы на букву в той же строке,
                // но в противоположном столбце является обратимым.
                decryptedPassword.append(matrix[aIndex[0]][bIndex[1]]);
                decryptedPassword.append(matrix[bIndex[0]][aIndex[1]]);

            }
        }

        // Удаление символа '/'' из полученной строки
        decryptedPassword = new StringBuilder(decryptedPassword.toString().replace(placeholder + "", ""));

        return decryptedPassword.toString();
    }
}
