#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[], char *arge[]) {

  printf("Parent process\n");
  setenv("TEST_ENV", "test", 1);
  printf("TEST_ENV: %s\n", getenv("TEST_ENV"));

  // system("echo $TEST_ENV");

  pid_t pid = fork();

  if (pid < 0) {

    perror("fork");
    exit(1);

  } else if (pid == 0) {

    printf("Child process\n");
    printf("TEST_ENV (child): %s\n", getenv("TEST_ENV"));
    exit(0);

  } else {

    int status;
    waitpid(pid, &status, 0);
  }
  return 0;
}
