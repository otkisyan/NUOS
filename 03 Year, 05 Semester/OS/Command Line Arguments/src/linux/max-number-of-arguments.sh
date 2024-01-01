#!/bin/bash

arg="test"

num_args=80500

while true; do
  args=()

  for ((i = 0; i < num_args; i++)); do
    args+=("$arg")
  done

  ../a.out "${args[@]}"
  
  return_code=$?

  if [ $return_code -eq 126 ]; then
    break
  fi

  ((num_args++))
done

echo "Number of args: $num_args"