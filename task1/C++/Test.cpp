#include "MatrixUtilities.h"
#include <iostream>
#include <sys/time.h>


int main() {
    MatrixMultiplication mult;
    MatrixMultiplication::Operands op;
    MatrixBenchmarking benchmark;

    benchmark.test(mult,op,2,3);


    return 0;
}