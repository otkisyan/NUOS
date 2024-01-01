#include <stdio.h>

int main(int argc, char *argv[], char *arge[]) {
  int i;
  for (i = 0; arge[i]; i++)
    printf("%s\n", arge[i]);
  return 0;
}
