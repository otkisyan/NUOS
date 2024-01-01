#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
  int i;
  for (i = 0; i < argc; i++) {
    printf("argv[%i]=%s\n", i, argv[i]);
  }

  // long max_arg_length = sysconf(_SC_ARG_MAX);
  // printf("ARG_MAX: %ld\n", max_arg_length);
  // printf("Number of args: %d\n", argc);

  // int average_arg_length = 4;
  // long max_num_arguments = max_arg_length / average_arg_length;
  // printf("Maximum number of arguments: %ld\n", max_num_arguments);
  return 0;
}
