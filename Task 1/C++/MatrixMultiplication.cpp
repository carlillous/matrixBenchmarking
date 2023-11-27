#include <vector>
#include <cstdlib>
#include <cassert>
#include <iostream>
#include "MatrixUtilities.h"

MatrixMultiplication::Operands::Operands() {
    n = 2048;
    a = std::vector<std::vector<double>>(n, std::vector<double>(n));
    b = std::vector<std::vector<double>>(n, std::vector<double>(n));
    c = std::vector<std::vector<double>>(n, std::vector<double>(n));

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {

            a[i][j] = (double) rand() / RAND_MAX;
            b[i][j] = (double) rand() / RAND_MAX;
            c[i][j] = 0;
        }
    }


}


std::vector<std::vector<double>> MatrixMultiplication::execute(Operands operands) {
    assert(operands.a.size() == operands.b.size());
    int n = operands.a.size();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                operands.c[i][j] += operands.a[i][k] * operands.b[k][j];
            }
        }
    }
    return operands.c;
}