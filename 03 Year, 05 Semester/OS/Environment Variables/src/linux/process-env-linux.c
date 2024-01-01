#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[], char *arge[]) {

  setenv("TEST_ENV", "test", 1);
  printf("TEST_ENV: %s\n", getenv("TEST_ENV"));

  // unsetenv("TEST_ENV");
  // printf("TEST_ENV : %s\n", getenv("TEST_ENV"));
  return 0;
}
