// Имеется в файле таблица "Успеваемость студентов за год".
// Одна строка таблицы соответствует одному студенту и содержит следующие сведения:
// ФИО студента, номер группы, экзаменационные оценки за первый семестр, экзаменационные оценки за второй семестр.
// Упорядочить строки таблицы по номерам групп и найти студентов, у которых средний балл за второй семестр ниже, чем за первый.
// Результаты записать в файл.

// ------------------------ //
// Программа, которая создает текстовый файл

#include <iostream>
#include <stdio.h>
using namespace std;

struct Table
{
    char fullname[100];
    int group;
    int firstMarks[5];
    int secondMarks[5];
};

void FillTable(Table data[], int N, FILE *fl);

int main()
{

    FILE *fl = fopen("table.txt", "w");

    if (!fl)
    {
        cout << "Помилка";
        exit(1);
    }
    const int N = 5;

    Table data[N]{

        {"Новикова Аделина Максимовна", 1151, {5, 2, 3, 1, 2}, {1, 2, 1, 3, 2}},
        {"Воронина Анна Леоновна", 2151, {2, 4, 3, 2, 1}, {5, 4, 5, 5, 4}},
        {"Медведева Елена Михайловна", 4452, {4, 2, 1, 3, 4}, {2, 4, 5, 2, 1}},
        {"Зайцев Пётр Миронович", 1148, {2, 3, 4, 5, 1}, {5, 2, 1, 5, 5}},
        {"Кочергин Алексей Семёнович", 3252, {4, 4, 3, 4, 5}, {2, 1, 3, 4, 2}}

    };

    FillTable(data, N, fl);

    fclose(fl);
}

void FillTable(Table data[], int N, FILE *fl)
{

    for (int i = 0; i < N; i++)
    {

        fprintf(fl, "%s | %d | ", data[i].fullname, data[i].group);

        for (int j = 0; j < N; j++)
        {

            fprintf(fl, "%d ", data[i].firstMarks[j]);
        }

        fprintf(fl, " | ");

        for (int j = 0; j < N; j++)
        {

            fprintf(fl, "%d ", data[i].secondMarks[j]);
        }

        fprintf(fl, "\n");
    }
}

// ----------------------------- //
// программа, обрабатывающая текстовый файл

#include <iostream>
#include <stdio.h>
using namespace std;

struct Table
{
    char surname[100];
    char name[100];
    char patronymic[100];
    int group;
    int firstMarks[5];
    int secondMarks[5];
};

void ReadTable(const int N, Table data[]);
void WriteTable(const int N, Table data[]);
void FindByAverage(const int N, Table data[]);
void SortTable(const int N, Table (&data)[5]);
void ShowTable(const int N, Table data[]);

int main()
{

    const int N = 5;

    Table data[N];

    ReadTable(N, data);
    SortTable(N, data);
    WriteTable(N, data);
    FindByAverage(N, data);
    cout << endl;
}

void ReadTable(const int N, Table data[])
{

    FILE *fl = fopen("table.txt", "r");

    if (!fl)
    {
        cout << "Помилка";
        exit(1);
    }

    for (int i = 0; i < N; i++)
    {

        fscanf(fl, "%s %s %s | %d | %d %d %d %d %d  | %d %d %d %d %d", &data[i].surname, &data[i].name, &data[i].patronymic, &data[i].group,
               &data[i].firstMarks[0], &data[i].firstMarks[1], &data[i].firstMarks[2], &data[i].firstMarks[3], &data[i].firstMarks[4],
               &data[i].secondMarks[0], &data[i].secondMarks[1], &data[i].secondMarks[2], &data[i].secondMarks[3], &data[i].secondMarks[4]);
    }

    fclose(fl);
}

void FindByAverage(const int N, Table data[])
{

    FILE *fl = fopen("table.txt", "r+");

    if (!fl)
    {
        cout << "Помилка";
        exit(1);
    }
    fseek(fl, ftell(fl), SEEK_END);

    fprintf(fl, "\n");
    fprintf(fl, "%s\n\n", "Список студентів у яких середній бал за перший семестр нижче чим за другий: ");

    int averageGrade1 = 0;
    int averageGrade2 = 0;

    for (int i = 0; i < N; i++)
    {

        for (int j = 0; j < N; j++)
        {

            averageGrade1 = averageGrade1 + data[i].firstMarks[j];
            averageGrade2 = averageGrade2 + data[i].secondMarks[j];
        }

        const int N = 5;
        averageGrade1 = averageGrade1 / N;
        averageGrade2 = averageGrade2 / N;

        if (averageGrade2 < averageGrade1)
        {

            fprintf(fl, "%s %s %s\n", data[i].surname, data[i].name, data[i].patronymic);
            // fprintf(fl, "%s %d | %s %d \n", "Середній бал за 1 семестр:", averageGrade1, "Середній бал за 2 семестр:", averageGrade2 );
            // вывод в одну строку

            fprintf(fl, "%s", "Середній бал за 1 семестр: ");
            fprintf(fl, "%d\n", averageGrade1);
            fprintf(fl, "%s", "Середній бал за 2 семестр: ");
            fprintf(fl, "%d\n", averageGrade2);
            fprintf(fl, "%s\n", "");
        }
    }
}

void WriteTable(const int N, Table data[])
{

    FILE *fl = fopen("table.txt", "w");

    for (int i = 0; i < N; i++)
    {

        fprintf(fl, "%s %s %s | %d | ", data[i].surname, data[i].name, data[i].patronymic, data[i].group);

        for (int j = 0; j < N; j++)
        {

            fprintf(fl, "%d ", data[i].firstMarks[j]);
        }
        fprintf(fl, " | ");

        for (int j = 0; j < N; j++)
        {

            fprintf(fl, "%d ", data[i].secondMarks[j]);
        }

        fprintf(fl, "\n");
    }

    fclose(fl);
}

void ShowTable(const int N, Table data[])
{

    for (int i = 0; i < N; i++)
    {

        cout << data[i].surname << " ";
        cout << data[i].name << " ";
        cout << data[i].patronymic << " ";
        cout << " | ";

        cout << data[i].group << " ";
        cout << " | ";

        for (int j = 0; j < N; j++)
        {

            cout << data[i].firstMarks[j] << " ";
        }

        cout << " | ";

        for (int j = 0; j < N; j++)
        {

            cout << data[i].secondMarks[j] << " ";
        }

        cout << endl;
    }
}

void SortTable(const int N, Table (&data)[5])
{

    for (int i = 0; i < N - 1; i++)
    {
        for (int j = 0; j < N - i - 1; j++)
        {

            if (data[j].group < data[j + 1].group)
            {

                swap(data[j], data[j + 1]);
            }
        }
    }
}
